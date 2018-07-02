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

import java.util.HashSet;
import java.util.Set;

public class CellWorld implements World
{
	private int round = 0;
	public static int CELL_SIZE = 1;

	private int sizeX;
	private int sizeY;

	/**
	 * The most recent position of the currently growing cells
	 */
	volatile Set<PositionedCell> cells;
	/**
	 * The last cell that was put on a place in the world (memory)
	 */
	private final CellGrid grid;

	CellWorld(Set<PositionedCell> cells, int sizeX, int sizeY)
	{
		this.cells = cells;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.grid = new CellGrid(sizeX, sizeY);
		for(PositionedCell cell : cells)
		{
			grid.placeOnGrid(cell);
		}
	}

	public boolean moveForward()
	{
		round++;
		Set<PositionedCell> newCells = new HashSet<PositionedCell>();
		for(PositionedCell cell : cells)
		{
			// Don't generate new cells if at the border, TODO: handle inside GridPosition and return Optional
			if(cell.position.y > 0)
			{
				GridPosition newPos = cell.position.move(0, -1);
				cell.cell.top(newPos, grid).ifPresent(top -> {
					newCells.add(positionedCell(newPos, top));
				});
			}
			if(cell.position.x > 0)
			{
				GridPosition newPos = cell.position.move(-1, 0);
				cell.cell.left(newPos, grid).ifPresent(left -> {
					newCells.add(positionedCell(newPos, left));
				});
			}
			if(cell.position.x < sizeX - 1)
			{
				GridPosition newPos = cell.position.move(1, 0);
				cell.cell.right(newPos, grid).ifPresent(right -> {
					newCells.add(positionedCell(newPos, right));
				});
			}
			if(cell.position.y < sizeY - 1)
			{
				GridPosition newPos = cell.position.move(0, 1);
				cell.cell.bottom(newPos, grid).ifPresent(bottom -> {
					newCells.add(positionedCell(newPos, bottom));
				});
			}
		}
		cells = newCells;
		return newCells.isEmpty();
	}

	@Override
	public Iterable<PositionedCell> newCells()
	{
		return cells;
	}

	private PositionedCell positionedCell(GridPosition newPos, Cell cell)
	{
		PositionedCell positionedCell = new PositionedCell(cell, newPos);
		return grid.placeOnGrid(positionedCell);
	}

	public int round()
	{
		return round;
	}

	@Override
	public String toString()
	{
		return "At round " + round + ":\n" + grid.toString();
	}
}
