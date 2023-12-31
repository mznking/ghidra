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

import generic.lsh.LSHMemoryModel;
import ghidra.feature.vt.api.main.VTProgramCorrelator;
import ghidra.feature.vt.api.util.VTAbstractProgramCorrelatorFactory;
import ghidra.feature.vt.api.util.VTOptions;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.listing.Program;

public class SimilarSymbolNameProgramCorrelatorFactory extends VTAbstractProgramCorrelatorFactory {
	public static final String NAME = "Similar Symbol Name Match";

	public static final String MEMORY_MODEL = "Memory model";
	public static final LSHMemoryModel MEMORY_MODEL_DEFAULT = LSHMemoryModel.LARGE;
	public static final String MIN_NAME_LENGTH = "Minimum name length";
	public static final int MIN_NAME_LENGTH_DEFAULT = 6;

	@Override
	public int getPriority() {
		return 9001;
	}

	@Override
	protected VTProgramCorrelator doCreateCorrelator(Program sourceProgram,
			AddressSetView sourceAddressSet, Program destinationProgram,
			AddressSetView destinationAddressSet, VTOptions options) {
		return new SimilarSymbolNameProgramCorrelator(sourceProgram, sourceAddressSet,
			destinationProgram, destinationAddressSet, options);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public VTOptions createDefaultOptions() {
		VTOptions options = new VTOptions(NAME);
		options.setEnum(MEMORY_MODEL, MEMORY_MODEL_DEFAULT);
		options.setInt(MIN_NAME_LENGTH, MIN_NAME_LENGTH_DEFAULT);
		return options;
	}

	@Override
	public String getDescription() {
		return "Compares symbols by iterating over all" +
			" defined function and data symbols meeting the minimum size requirement in" +
			" the source program and looking for similar symbol matches in the destination" +
			" program.  It reports back any that match closely.";
	}
}
