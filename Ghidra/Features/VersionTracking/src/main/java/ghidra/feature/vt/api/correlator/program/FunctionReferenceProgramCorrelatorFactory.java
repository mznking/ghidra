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
package ghidra.feature.vt.api.correlator.program;

import ghidra.feature.vt.api.main.VTProgramCorrelator;
import ghidra.feature.vt.api.util.VTOptions;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.listing.Program;

/**
 * Factory to call the correlator that correlates functions based on previously accepted function matches.
 */
public class FunctionReferenceProgramCorrelatorFactory
		extends VTAbstractReferenceProgramCorrelatorFactory {
	public FunctionReferenceProgramCorrelatorFactory() {
		setName("Function Reference Match");
		correlatorDescription =
			"Matches functions by the accepted function matches they have in common.";

		// UNCOMMENT if this file gets made
		//helpLocationAnchor = "FunctionReference_Correlator";
	}

	@Override
	protected VTProgramCorrelator doCreateCorrelator(Program sourceProgram,
			AddressSetView sourceAddressSet, Program destinationProgram,
			AddressSetView destinationAddressSet, VTOptions options) {
		return new FunctionReferenceProgramCorrelator(sourceProgram, sourceAddressSet,
			destinationProgram, destinationAddressSet, correlatorName, options);
	}
}
