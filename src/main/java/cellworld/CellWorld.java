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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class CellWorld
{
	static int CELL_SIZE = 5;

	/**
	 * The most recent position of the currently growing cells
	 */
	volatile List<PositionedCell> cells;
	/**
	 * The last cell that was put on a place in the world
	 */
	volatile Cell[][] grid;

	protected void moveForward()
	{
		List<PositionedCell> newCells = new ArrayList<PositionedCell>();
		for(PositionedCell cell : cells)
		{
			cell.cell.top().ifPresent(top -> {
				Point newPos = new Point(cell.position);
				newPos.translate(0, CELL_SIZE);
				newCells.add(new PositionedCell(top, newPos));
			});
			cell.cell.bottom().ifPresent(bottom -> {
				Point newPos = new Point(cell.position);
				newPos.translate(0, -CELL_SIZE);
				newCells.add(new PositionedCell(bottom, newPos));
			});
			cell.cell.left().ifPresent(left -> {
				Point newPos = new Point(cell.position);
				newPos.translate(-CELL_SIZE, 0);
				newCells.add(new PositionedCell(left, newPos));
			});
			cell.cell.right().ifPresent(right -> {
				Point newPos = new Point(cell.position);
				newPos.translate(CELL_SIZE, 0);
				newCells.add(new PositionedCell(right, newPos));
			});
		}
		cells = newCells;
	}
}
