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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import cellworld.Constants;
import cellworld.MultiInstanceMap;

/**
 * Is a jelly like liquid that can help the contents of the cell to move.
 */
public class Cytoplasm implements MembraneTunnel<Ribosome>
{
	private final Random random = new Random(Constants.SEED);

	// TODO: cytoskeleton
	private final Nucleolus nucleolus;
	private final Deque<Vesicle> vesicles;
	private final MultiInstanceMap<Organelle> organelles;
	private final RoughEndoplasmicReticulum rER;

	public Cytoplasm(List<Chromatin> chromatins)
	{
		nucleolus = new Nucleolus(this, chromatins);
		vesicles = new ArrayDeque<>();
		organelles = new MultiInstanceMap<>();
		// TODO: fill with GolgiApparatus
		rER = new RoughEndoplasmicReticulum(new VesicleContainer());
	}

	void moveInto(Vesicle vesicle, GolgiApparatus golgi)
	{
		golgi.maybeTakeIn(vesicle);
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void receive(Ribosome part)
	{
		rER.maybeTakeIn(part);
	}

	private class VesicleContainer implements MembraneTunnel<Vesicle>
	{
		@Override
		public void receive(Vesicle part)
		{
			vesicles.add(part);
		}
	}

	public void tick()
	{
		nucleolus.produceRibosome();
		List<GolgiApparatus> golgis = organelles.get(GolgiApparatus.class);
		while(!vesicles.isEmpty())
		{
			GolgiApparatus golgi = golgis.get(random.nextInt(golgis.size()));
			golgi.maybeTakeIn(vesicles.pop());
		}
	}
}
