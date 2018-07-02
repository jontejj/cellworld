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

public class CellularAutomata extends DefaultCell
{
	private final byte rule;

	static int risk = 0;

	CellularAutomata(byte rule, Color color, int strength)
	{
		super(color, strength);
		this.rule = rule;
	}

	@Override
	Optional<? extends Cell> bottom(GridPosition cellPosition, CellGrid grid)
	{
		return Optional.of(new CellularAutomata(rule, color(rule, cellPosition, grid), strength));
	}

	@Override
	Optional<? extends Cell> left(GridPosition cellPosition, CellGrid grid)
	{
		return Optional.of(new CellularAutomata(rule, color(rule, cellPosition, grid), strength));
	}

	@Override
	Optional<? extends Cell> right(GridPosition cellPosition, CellGrid grid)
	{
		return Optional.of(new CellularAutomata(rule, color(rule, cellPosition, grid), strength));
	}

	static Color color(byte rule, GridPosition cellPosition, CellGrid grid)
	{
		// System.out.println("Position: " + cellPosition);
		Cell topLeft = grid.topLeft(cellPosition);
		Cell above = grid.above(cellPosition);
		Cell topRight = grid.topRight(cellPosition);

		byte colorPattern = 0;
		colorPattern |= (topRight != null && topRight.color == Color.BLACK) ? 1 : 0;
		colorPattern |= (above != null && above.color == Color.BLACK) ? 2 : 0;
		colorPattern |= (topLeft != null && topLeft.color == Color.BLACK) ? 4 : 0;
		// System.out.println((topRight != null && topRight.color == Color.BLACK) ? "Top right" : "");
		// System.out.println((above != null && above.color == Color.BLACK) ? "Above" : "");
		// System.out.println((topLeft != null && topLeft.color == Color.BLACK) ? "Top left" : "");
		// System.out.println("Rule:" + Integer.toString(rule, 2));
		// System.out.println("Cell value:" + Integer.toString(colorPattern, 2));
		Color color = (1 << colorPattern & rule) > 0 ? Color.BLACK : Color.WHITE;
		if(colorPattern == 0) // TODO: fix the line above so that this case is not necessary
		{
			if((rule & 1) == 1)
			{
				color = Color.BLACK;
			}
			else
			{
				color = Color.WHITE;
			}
		}
		// System.out.println("Color:" + color);
		return color;
	}
}
