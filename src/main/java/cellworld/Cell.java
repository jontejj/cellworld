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

/**
 * A cell is a 5x5 pixel area. It grows in four different directions.
 * It has a color and it's strength determines if it takes over the neighboring cell when it grows.
 */
abstract class Cell
{
	Color color;
	int strength;

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

	abstract Optional<? extends Cell> top();

	abstract Optional<? extends Cell> bottom();

	abstract Optional<? extends Cell> left();

	abstract Optional<? extends Cell> right();

	Color color()
	{
		return color;
	}

	@Override
	public String toString()
	{
		return color + ":" + strength;
	}
}
