/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.program.model.data.ISF;

import ghidra.program.model.data.Pointer;

public class IsfPointer implements IsfObject {

	public Integer size;
	public String kind;
	public String endian;

	public IsfPointer(Pointer ptr) {
		size = ptr.hasLanguageDependantLength() ? -1 : IsfUtilities.getLength(ptr);
		kind = "pointer";
		endian = IsfUtilities.getEndianness(ptr);
	}

}
