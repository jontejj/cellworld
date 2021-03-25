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
package cellworld;

import java.awt.Color;
import java.util.Optional;

import cellworld.components.CellMembrane;

/**
 * Types of Cells: 
 * Photoreceptor cell - visual - https://en.wikipedia.org/wiki/Photoreceptor_cell
 * Odor - Smell  - https://en.wikipedia.org/wiki/Olfactory_receptor_neuron
 * Stem cell - https://en.wikipedia.org/wiki/Stem_cell
 * Neuroepithelial_cell - https://en.wikipedia.org/wiki/Neuroepithelial_cell (One usage is taste buds, http://jcb.rupress.org/content/190/3/285)
 * Hair cell - Hearing - https://en.wikipedia.org/wiki/Hair_cell
 * liver cells, 
 * red blood cells, 
 * white blood cells, 
 * muscle cell
 */

/**
 * Related concepts:
 * Axon - https://en.wikipedia.org/wiki/Axon - conducts electrical impulses known as action potentials, away from the nerve cell body
 * Dendrites - https://en.wikipedia.org/wiki/Dendrite
 * Motor Neuron - https://en.wikipedia.org/wiki/Motor_neuron
 * Internal sensors - https://en.wikipedia.org/wiki/Interoceptor - e.g blood pressure / blood oxygen level.
 * https://en.wikipedia.org/wiki/Nociceptor - Pain / Temperature
 * https://en.wikipedia.org/wiki/Proprioception - the sense of the relative position of one's own parts of the body and strength of effort being employed in movement
 */

/**
 * A cell is a 5x5 pixel area. It grows in four different directions.
 * It has a color and it's strength determines if it takes over the neighboring cell when it grows.
 */
abstract class Cell
{
	Color color;
	int strength;
	CellMembrane membrane;

	protected Cell(Color color, int strength)
	{
		this.color = color;
		this.strength = strength;
	}

	protected Cell(Cell parent)
	{
		this.color = parent.color;
		this.strength = parent.strength;
	}

	abstract Optional<? extends Cell> top(GridPosition cellPosition, CellGrid grid);

	abstract Optional<? extends Cell> bottom(GridPosition cellPosition, CellGrid grid);

	abstract Optional<? extends Cell> left(GridPosition cellPosition, CellGrid grid);

	abstract Optional<? extends Cell> right(GridPosition cellPosition, CellGrid grid);

	Color color()
	{
		return color;
	}

	public void tick()
	{
		membrane.tick();
	}

	@Override
	public String toString()
	{
		return color + ":" + strength;
	}
}
