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
package ghidra.feature.vt.gui.validator;

import ghidra.app.plugin.core.analysis.validator.PostAnalysisValidator;
import ghidra.app.plugin.core.decompiler.validator.DecompilerParameterIDValidator;
import ghidra.feature.vt.api.main.VTSession;
import ghidra.program.model.listing.Program;

public class DecompilerParameterIDVTPreconditionValidator extends
		VTPostAnalysisPreconditionValidatorAdaptor {

	public DecompilerParameterIDVTPreconditionValidator(Program sourceProgram,
			Program destinationProgram, VTSession existingResults) {
		super(sourceProgram, destinationProgram, existingResults);
	}

	@Override
	protected PostAnalysisValidator createPostAnalysisPreconditionValidator(Program program) {
		return new DecompilerParameterIDValidator(program);
	}
}
