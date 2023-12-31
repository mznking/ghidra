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
package ghidra.program.model.lang;

import ghidra.program.model.address.Address;
import ghidra.program.model.listing.AutoParameterType;
import ghidra.program.model.listing.VariableStorage;
import ghidra.program.model.pcode.Varnode;
import ghidra.util.exception.InvalidInputException;

public class DynamicVariableStorage extends VariableStorage {

	/**
	 * <code>INDIRECT_VOID_STORAGE</code> used to identify return storage which is "mapped"
	 * with a data-type of void but was forced indirect with the corresponding use of a
	 * hidden return-storage-parameter.
	 */
	public static final DynamicVariableStorage INDIRECT_VOID_STORAGE = new DynamicVariableStorage();

	private AutoParameterType autoParamType;
	private boolean forcedIndirect;
	private boolean isUnassigned = false;
	private boolean isVoid = false;

	/**
	 * Construct void return storage with forced-indirect flag.
	 */
	private DynamicVariableStorage() {
		super();
		forcedIndirect = true;
		isVoid = true;
	}

	/**
	 * Construct Unassigned dynamic variable storage with an optional auto-parameter type
	 * @param autoParamType auto-parameter type or null if not applicable
	 */
	private DynamicVariableStorage(AutoParameterType autoParamType) {
		super();
		this.autoParamType = autoParamType;
		isUnassigned = true;
	}

	/**
	 * Construct dynamic variable storage
	 * @param program
	 * @param forcedIndirect if true indicates that the parameter has been forced to pass 
	 * as a pointer instead of its raw type
	 */
	private DynamicVariableStorage(boolean forcedIndirect) {
		super();
		this.forcedIndirect = forcedIndirect;
		isUnassigned = true;
	}

	/**
	 * Construct dynamic variable storage with an optional auto-parameter type
	 * @param program
	 * @param autoParamType auto-parameter type or null if not applicable
	 * @param address varnode address
	 * @param size varnode size
	 * @throws InvalidInputException
	 */
	public DynamicVariableStorage(ProgramArchitecture program, AutoParameterType autoParamType,
			Address address, int size) throws InvalidInputException {
		super(program, address, size);
		this.autoParamType = autoParamType;
	}

	/**
	 * Construct dynamic variable storage with an optional auto-parameter type
	 * @param program
	 * @param autoParamType auto-parameter type or null if not applicable
	 * @param varnodes one or more ordered storage varnodes
	 * @throws InvalidInputException if specified varnodes violate storage restrictions
	 */
	public DynamicVariableStorage(ProgramArchitecture program, AutoParameterType autoParamType,
			Varnode... varnodes) throws InvalidInputException {
		super(program, varnodes);
		this.autoParamType = autoParamType;
	}

	/**
	 * Construct dynamic variable storage
	 * @param program
	 * @param forcedIndirect if true indicates that the parameter has been forced to pass 
	 * as a pointer instead of its raw type
	 * @param address varnode address
	 * @param size varnode size
	 * @throws InvalidInputException
	 */
	public DynamicVariableStorage(ProgramArchitecture program, boolean forcedIndirect,
			Address address, int size) throws InvalidInputException {
		super(program, address, size);
		this.forcedIndirect = forcedIndirect;
	}

	/**
	 * Construct dynamic variable storage
	 * @param program
	 * @param forcedIndirect if true indicates that the parameter has been forced to pass 
	 * as a pointer instead of its raw type
	 * @param varnodes one or more ordered storage varnodes
	 * @throws InvalidInputException if specified varnodes violate storage restrictions
	 */
	public DynamicVariableStorage(ProgramArchitecture program, boolean forcedIndirect,
			Varnode... varnodes) throws InvalidInputException {
		super(program, varnodes);
		this.forcedIndirect = forcedIndirect;
	}

	@Override
	public boolean isForcedIndirect() {
		return forcedIndirect;
	}

	@Override
	public boolean isAutoStorage() {
		return autoParamType != null;
	}

	@Override
	public boolean isUnassignedStorage() {
		return isUnassigned;
	}

	@Override
	public boolean isVoidStorage() {
		return isVoid;
	}

	@Override
	public AutoParameterType getAutoParameterType() {
		return autoParamType;
	}

	@Override
	public String toString() {
		String str = super.toString();
		if (forcedIndirect && varnodes != null) {
			str = str + " (ptr)";
		}
		if (autoParamType != null) {
			str = str + " (auto)";
		}
		return str;
	}

	/**
	 * Construct Unassigned dynamic variable storage with an optional auto-parameter type.
	 * NOTE: The {@link #isUnassignedStorage()} method should be used to
	 * detect this type of storage.
	 * @param autoParamType auto-parameter type or null if not applicable
	 * @return Unassigned dynamic variable storage
	 */
	public static DynamicVariableStorage getUnassignedDynamicStorage(
			AutoParameterType autoParamType) {
		return new DynamicVariableStorage(autoParamType);
	}

	/**
	 * Construct Unassigned dynamic variable storage.
	 * NOTE: The {@link #isUnassignedStorage()} method should be used to
	 * detect this type of storage.
	 * @param forcedIndirect if true indicates that the parameter has been forced to pass 
	 * as a pointer instead of its raw type
	 * @return Unassigned dynamic variable storage
	 */
	public static DynamicVariableStorage getUnassignedDynamicStorage(boolean forcedIndirect) {
		return new DynamicVariableStorage(forcedIndirect);
	}
}
