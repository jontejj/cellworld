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
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Main
{
	public static void main(String[] args)
	{
		CellWorld cellWorld = new CellWorld();
		PositionedCell lefter = new PositionedCell(new Lefter(new DefaultCell(Color.BLACK, 1)), new Point(500, 300));

		PositionedCell bottomLefter = new PositionedCell(new Lefter(new DefaultCell(Color.BLACK, 1)), new Point(800, 700));

		cellWorld.cells = Arrays.asList(lefter, bottomLefter);

		final CellFrame cellFrame = new CellFrame();
		final CellCanvas cellCanvas = new CellCanvas(cellWorld);
		cellFrame.add(cellCanvas);
		Window window = new Window(cellFrame);
		cellFrame.setSize(window.getToolkit().getScreenSize());
		window.setVisible(true);
		window.toFront();

		final TimerTask moveWorldForwardTask = new TimerTask(){
			@Override
			public void run()
			{
				try
				{
					EventQueue.invokeAndWait(new Runnable(){

						public void run()
						{
							cellWorld.moveForward();
							cellCanvas.repaint();
						}
					});
				}
				catch(InvocationTargetException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch(InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		new Timer(true).scheduleAtFixedRate(moveWorldForwardTask, 0, 100);
	}
}
