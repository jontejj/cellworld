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
 * The brain of the cell. https://en.wikipedia.org/wiki/Nucleoplasm#/media/File:Diagram_human_cell_nucleus.svg
 */
public class Nucleolus
{
	/**
	 * TODO: Only animals has an envelope
	 */
	NuclearEnvelope nuclearEnvelope;

	Nucleoplasm nucleoplasm;

	public Nucleolus(Cytoplasm cytoplasm, List<Chromatin> chromatins)
	{
		Strength ribosomeTransferStrength = Strength.of(50);
		nucleoplasm = new Nucleoplasm(chromatins, ribosomeTransferStrength);
		nuclearEnvelope = new NuclearEnvelope(cytoplasm, Strength.of(0), 100);
		// TODO: set nuclearEnvelope for animals?
	}

	void produceRibosome()
	{
		Iterable<Ribosome> newRibosome = nucleoplasm.produceRibosome();
		for(Ribosome ribosome : newRibosome)
		{
			nuclearEnvelope.maybeTakeIn(ribosome);
		}
		nuclearEnvelope.maybeLetOut();
	}
}
