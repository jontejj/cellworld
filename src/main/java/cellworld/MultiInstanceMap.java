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

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * A heterogenous map from a parent class to instances of its subclasses
 * 
 * @param <T> the parent class
 */
public class MultiInstanceMap<T>
{
	private Multimap<Class<? extends T>, T> classToInstances = HashMultimap.create();

	@SuppressWarnings("unchecked")
	public void add(T obj)
	{
		classToInstances.put((Class<? extends T>) obj.getClass(), obj);
	}

	@SuppressWarnings("unchecked")
	public <E extends T> List<E> get(Class<E> cls)
	{
		return (List<E>) classToInstances.get(cls);
	}
}
