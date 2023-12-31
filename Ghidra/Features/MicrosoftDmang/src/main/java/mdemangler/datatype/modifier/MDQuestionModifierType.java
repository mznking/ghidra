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
package mdemangler.datatype.modifier;

import mdemangler.MDException;
import mdemangler.MDMang;
import mdemangler.datatype.MDDataType;
import mdemangler.datatype.MDDataTypeParser;

/**
 * This class represents a modifier data type coded with a question mark within a Microsoft mangled
 *  symbol.
 */
public class MDQuestionModifierType extends MDModifierType {

	public MDQuestionModifierType(MDMang dmang) {
		super(dmang);
		cvMod.setQuestionType();
	}

	@Override
	protected void parseInternal() throws MDException {
		super.parseInternal();
	}

	@Override
	protected MDDataType parseReferencedType() throws MDException {
		return MDDataTypeParser.parsePrimaryDataType(dmang, false);
	}
}
