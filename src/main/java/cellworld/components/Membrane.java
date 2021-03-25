/* Copyright 2018 jonatanjonsson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cellworld.components;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import cellworld.Constants;

/**
 * A protective layer that temporarily can hold things inside of it.
 */
public abstract class Membrane<T extends TransferablePart, Output>
{
	// TODO: implement a Lipid Bilayer too
	private final Random random = new Random(Constants.SEED);

	private final Strength strength;

	private final int chanceOfTransfer;

	private final List<T> partsCurrentlyInside;

	private final MembraneTunnel<Output> tunnel;

	private final Function<T, Output> membraneFunction;

	/**
	 * @param membraneFunction how this membrane converts it's input when it passes through
	 * @param strength indicating how likely this Membrane is to let {@link TransferablePart}s in or out.
	 * @param chanceOfTransfer when a {@link TransferablePart} is {@link Strength strong} enough to be let out/in,
	 *            this tells how likely that is to happen.
	 */
	Membrane(MembraneTunnel<Output> tunnel, Function<T, Output> membraneFunction, Strength strength, int chanceOfTransfer)
	{
		this.tunnel = tunnel;
		this.membraneFunction = membraneFunction;
		partsCurrentlyInside = new LinkedList<>();
		this.strength = strength;
		this.chanceOfTransfer = chanceOfTransfer;
	}

	boolean maybeTakeIn(T transferablePart)
	{
		if(transferablePart.transferStrength().compareTo(strength) >= 0)
		{
			if(random.nextInt(100) >= chanceOfTransfer)
			{
				partsCurrentlyInside.add(transferablePart);
				return true;
			}
		}
		return false;
	}

	void maybeLetOut()
	{
		if(!partsCurrentlyInside.isEmpty() && random.nextInt(100) >= chanceOfTransfer)
		{
			T part = partsCurrentlyInside.remove(0);
			tunnel.receive(membraneFunction.apply(part));
		}
	}
}
