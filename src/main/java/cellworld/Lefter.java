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

import java.util.Optional;

class Lefter extends DefaultCell
{
	// boolean previous = false;
	// private final Color odd;
	// private final Color even;

	Lefter(Cell parent)// , Color odd, Color even)
	{
		super(parent);
		// this.odd = odd;
		// this.even = even;
	}

	@Override
	Optional<? extends Cell> left()
	{
		return Optional.of(this);
	}

	// @Override
	// Color color()
	// {
	// //previous = !previous;
	// //return previous ? even : odd;
	//
	// }
}