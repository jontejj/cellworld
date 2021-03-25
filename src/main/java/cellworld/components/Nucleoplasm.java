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

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Iterables;

import cellworld.Constants;

public class Nucleoplasm
{
	private final Random random = new Random(Constants.SEED);
	private final List<Chromatin> chromatins;
	private final Strength transferStrength;

	Nucleoplasm(List<Chromatin> chromatins, Strength transferStrength)
	{
		this.transferStrength = transferStrength;
		checkArgument(!chromatins.isEmpty(), "No chromatins given");
		this.chromatins = chromatins;
	}

	Iterable<Ribosome> produceRibosome()
	{
		Chromatin chromatin = chromatins.get(random.nextInt(chromatins.size()));
		int chromatinSize = Iterables.size(chromatin.strands);
		DNA dna = Iterables.get(chromatin.strands, random.nextInt(chromatinSize));
		mRNA mRNA = transcribe(dna);
		Ribosome ribosome = new Ribosome(mRNA, transferStrength);
		// TODO: generate more than one?
		return Collections.singleton(ribosome);
	}

	public mRNA transcribe(DNA dna)
	{
		// Three base pairs (a codon), creates one amino acid
		// 64 possible codons
		// Allows to create 20 different amino acids
		// Codon -> tRNA (U-A, C-G) -> Amino acid
		throw new UnsupportedOperationException("Not implemented");
	}
}
