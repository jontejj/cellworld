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
import java.util.Optional;

/**
 * A default cell that stops growing in all directions.
 */
class DefaultCell extends Cell
{
	/**
	 * Constructs a default cell that has the same properties as its parent.
	 */
	DefaultCell(Cell parent)
	{
		super(parent);
	}

	/**
	 * A cell without a parent. Can be used as a root cell.
	 */
	DefaultCell(Color color, int strength)
	{
		super(color, strength);
	}

	@Override
	Optional<? extends Cell> top()
	{
		return Optional.empty();
	}

	@Override
	Optional<? extends Cell> bottom()
	{
		return Optional.empty();
	}

	@Override
	Optional<? extends Cell> left()
	{
		return Optional.empty();
	}

	@Override
	Optional<? extends Cell> right()
	{
		return Optional.empty();
	}
}