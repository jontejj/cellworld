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

import java.awt.Canvas;
import java.awt.Graphics;

class CellCanvas extends Canvas
{
	private CellWorld world;

	public CellCanvas(CellWorld cellWorld)
	{
		this.world = cellWorld;
	}

	@Override
	public void paint(Graphics g)
	{
		System.out.println("Painting: " + world.cells);
		for(PositionedCell cell : world.cells)
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
		g.fillRect(cell.position.x, cell.position.y, CellWorld.CELL_SIZE, CellWorld.CELL_SIZE);
		// if(currentX >= getToolkit().getScreenSize().getWidth())
	}
}