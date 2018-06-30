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

public class CellGrid
{
	/**
	 * The last cell that was put on a place in the world (memory)
	 */
	private final Cell[][] grid;

	public CellGrid(int sizeX, int sizeY)
	{
		this.grid = new Cell[sizeY][sizeX];
	}

	Cell above(GridPosition cellPosition)
	{
		return cellPosition.y > 0 ? grid[cellPosition.y - 1][cellPosition.x] : null;
	}

	Cell below(GridPosition cellPosition)
	{
		return cellPosition.y < grid.length - 1 ? grid[cellPosition.y + 1][cellPosition.x] : null;
	}

	Cell toTheLeft(GridPosition cellPosition)
	{
		return cellPosition.x > 0 ? grid[cellPosition.y][cellPosition.x - 1] : null;
	}

	Cell toTheRight(GridPosition cellPosition)
	{
		return cellPosition.x < grid[0].length - 1 ? grid[cellPosition.y][cellPosition.x + 1] : null;
	}

	Cell topLeft(GridPosition cellPosition)
	{
		return cellPosition.x > 0 && cellPosition.y > 0 ? grid[cellPosition.y - 1][cellPosition.x - 1] : null;
	}

	Cell topRight(GridPosition cellPosition)
	{
		return cellPosition.x < grid[0].length - 1 && cellPosition.y > 0 ? grid[cellPosition.y - 1][cellPosition.x + 1] : null;
	}

	Cell bottomLeft(GridPosition cellPosition)
	{
		return cellPosition.x > 0 && cellPosition.y < grid.length - 1 ? grid[cellPosition.y + 1][cellPosition.x - 1] : null;
	}

	Cell bottomRight(GridPosition cellPosition)
	{
		return cellPosition.x < grid[0].length - 1 && cellPosition.y < grid.length - 1 ? grid[cellPosition.y + 1][cellPosition.x + 1] : null;
	}

	PositionedCell placeOnGrid(PositionedCell cell)
	{
		Cell current = grid[cell.position.y][cell.position.x];
		if(current != null)
			if(cell.cell.strength <= current.strength)
				return new PositionedCell(current, cell.position);
		grid[cell.position.y][cell.position.x] = cell.cell;
		return cell;
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for(Cell[] row : grid)
		{
			for(Cell cell : row)
			{
				if(cell != null && cell.color == Color.BLACK)
				{
					str.append('X');
				}
				else
				{
					str.append(' ');
				}
			}
			str.append(System.lineSeparator());
		}
		return str.toString();
	}
}
