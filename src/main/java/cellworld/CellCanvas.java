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

import static cellworld.CellWorld.CELL_SIZE;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;

class CellCanvas extends Canvas
{
	private World world;

	public CellCanvas(World world)
	{
		this.world = world;
	}

	@Override
	public void paint(Graphics g)
	{
		System.out.println("Painting " + world.round() + ": " + world.newCells());
		for(PositionedCell cell : world.newCells())
		{
			paintCell(cell, g);
		}
	}

	@Override
	public void update(Graphics g)
	{
		paint(g);
	}

	private void paintCell(PositionedCell cell, Graphics g)
	{
		g.setColor(cell.cell.color());
		Point cellPos = toCanvasPoint(cell.position);
		g.fillRect(cellPos.x, cellPos.y, CellWorld.CELL_SIZE, CellWorld.CELL_SIZE);
		// if(currentX >= getToolkit().getScreenSize().getWidth())
	}

	Point toCanvasPoint(GridPosition gridPos)
	{
		return new Point(gridPos.x * CELL_SIZE, gridPos.y * CELL_SIZE);
	}

}
