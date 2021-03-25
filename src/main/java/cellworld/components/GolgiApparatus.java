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

public class GolgiApparatus extends Organelle
{
	private final DefaultMembrane<Protein, Structure> membrane;
	private final List<Protein> proteins;

	GolgiApparatus(MembraneTunnel<Structure> plasm, Strength strength, int chanceOfTransfer)
	{
		this.membrane = new DefaultMembrane<>(plasm, this::foldProteins, strength, chanceOfTransfer);
		this.proteins = new LinkedList<>();
	}

	public boolean maybeTakeIn(Vesicle vesicle)
	{
		boolean takenIn = membrane.maybeTakeIn(vesicle.protein);
		if(takenIn)
		{
			proteins.add(vesicle.protein);
		}
		return takenIn;
	}

	public Structure foldProteins(Protein p)
	{
		// Randomly add Lipid, Carbohydrate
		throw new UnsupportedOperationException("Not implemented");
	}
}
