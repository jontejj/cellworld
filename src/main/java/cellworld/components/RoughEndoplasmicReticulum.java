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

import java.util.List;

/**
 * A tunnel like structure that has {@link Ribosome}s attached to it.
 */
public class RoughEndoplasmicReticulum
{
	private final Membrane<Ribosome, Vesicle> membrane;
	List<Ribosome> ribosomes;

	public RoughEndoplasmicReticulum(MembraneTunnel<Vesicle> cytoplasm)
	{
		membrane = new DefaultMembrane<Ribosome, Vesicle>(cytoplasm, this::convert, Strength.of(60), 20);
	}

	void maybeTakeIn(Ribosome ribosome)
	{
		membrane.maybeTakeIn(ribosome);
		membrane.maybeLetOut();
	}

	private Vesicle convert(Ribosome ribosome)
	{
		Protein protein = ribosome.synthesizeProtein();
		Vesicle vesicle = new Vesicle(protein, ribosome.transferStrength());
		return vesicle;
	}
}
