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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class AutomataWorld implements World
{
	private int round = 0;
	public static int CELL_SIZE = 1;

	private int sizeX;
	private int sizeY;

	/**
	 * The last cell that was put on a place in the world (memory)
	 */
	private final CellGrid grid;

	Deque<List<PositionedCell>> cells;

	PositionedCell model;

	AutomataWorld(byte rule, int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.grid = new CellGrid(sizeX, sizeY);

		int middle = sizeX / 2;
		model = new PositionedCell(new CellularAutomata(rule, Color.BLACK, 1), new GridPosition(middle, 0));
		cells = new LinkedBlockingDeque<>();
		cells.push(Collections.singletonList(model));
		for(PositionedCell cell : cells.peek())
		{
			grid.placeOnGrid(cell);
		}
	}

	public boolean moveForward()
	{
		round++;
		// Grow one left, grow all down, grow one right
		List<PositionedCell> newCells = new ArrayList<>();
		PositionedCell latest = null;
		for(int i = 0; i < sizeX; i++)
		{
			GridPosition gridPosition = new GridPosition(i, model.position.y + 1);
			if(gridPosition.y < sizeY - 1)
			{
				latest = positionedCell(gridPosition, model.cell.left(gridPosition, grid).get());
				newCells.add(latest);
			}
		}
		model = latest;
		cells.addLast(newCells);
		return newCells.isEmpty();
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

	@Override
	public Iterable<PositionedCell> newCells()
	{
		if(cells.isEmpty())
			return Collections.emptyList();
		return cells.pop();
	}
}
