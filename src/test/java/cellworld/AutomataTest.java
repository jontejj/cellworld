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

import org.junit.Test;

public class AutomataTest
{
	@Test
	public void testThat() throws Exception
	{
		byte rule = 5;
		// PositionedCell automata = new PositionedCell(new CellularAutomata(rule, Color.BLACK, 1), new GridPosition(10, 0));

		// CellWorld cellWorld = new CellWorld(Collections.singleton(automata), 100, 20);
		World world = new AutomataWorld(rule, 100, 20);
		for(int i = 0; i < 100; i++)
		{
			System.out.println(world.toString());
			if(world.moveForward())
			{
				break;
			}
		}
	}
}
