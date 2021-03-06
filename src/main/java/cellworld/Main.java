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
import java.awt.Window;
import java.util.Collections;
import java.util.Set;

public class Main
{
	public static void main(String[] args)
	{
		final CellFrame cellFrame = new CellFrame();
		Window window = new Window(cellFrame);
		cellFrame.setSize(window.getToolkit().getScreenSize());

		// PositionedCell lefter = new PositionedCell(new Lefter(new DefaultCell(Color.BLACK, 1)), new GridPosition(50, 60));
		// (byte) 0b00011110
		byte rule = (byte) 30; // (byte) 0b00110110; // 0b00011110;
		PositionedCell rule30 = new PositionedCell(new CellularAutomata(rule, Color.BLACK, 1), new GridPosition(150, 1));
		// PositionedCell bottomLefter = new PositionedCell(new Lefter(new DefaultCell(Color.BLACK, 1)), new Point(800, 700));
		Set<PositionedCell> cells = Collections.singleton(rule30);
		// Set<PositionedCell> cells = Collections.singleton(lefter);
		// List<PositionedCell> cells = Arrays.asList(lefter, bottomLefter);

		int worldSizeX = cellFrame.getWidth() / CellWorld.CELL_SIZE;
		int worldSizeY = cellFrame.getHeight() / CellWorld.CELL_SIZE;

		World cellWorld = new AutomataWorld(rule, worldSizeX, worldSizeY);
		// World cellWorld = new CellWorld(cells, worldSizeX, worldSizeY);

		final CellCanvas cellCanvas = new CellCanvas(cellWorld);
		cellFrame.add(cellCanvas);
		// window.setVisible(true);
		// window.toFront();

		boolean endOfWorld = false;
		while(!endOfWorld)
		{
			cellCanvas.repaint();
			System.out.println("Running");
			endOfWorld = cellWorld.moveForward();
		}
		System.out.println("End of world reached");
	}
}
