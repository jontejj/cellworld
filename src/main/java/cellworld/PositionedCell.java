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

import java.util.Objects;

class PositionedCell
{
	final GridPosition position;
	final Cell cell;

	PositionedCell(Cell cell, GridPosition position)
	{
		this.cell = cell;
		this.position = position;
	}

	@Override
	public String toString()
	{
		return position + ":" + cell;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(position);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		PositionedCell other = (PositionedCell) obj;
		if(position == null)
		{
			if(other.position != null)
				return false;
		}
		else if(!position.equals(other.position))
			return false;
		return true;
	}
}
