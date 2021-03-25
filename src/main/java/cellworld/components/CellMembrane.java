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
package cellworld.components;

import java.util.function.Function;

import cellworld.Body;

/**
 * A cell membrane protects "most" of the contents of the cell. It's made up of two layers. One inner and one outer.
 * It's semi-permiable, because it can both let stuff in and out.
 */
public class CellMembrane extends Membrane<TransferablePart, TransferablePart>
{
	CellMembrane(Body containingBody, Strength strength, int chanceOfTransfer)
	{
		super(containingBody, Function.identity(), strength, chanceOfTransfer);
	}

	Cytoplasm cytoplasm;

	public void tick()
	{
		cytoplasm.tick();
		maybeLetOut();
	}
}
