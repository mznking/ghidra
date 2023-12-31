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
package ghidra.features.bsim.gui.search.dialog;

import ghidra.framework.plugintool.ServiceProvider;
import ghidra.program.database.symbol.FunctionSymbol;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Program;
import ghidra.util.table.ProgramLocationTableRowMapper;

/**
 * Maps FunctionSymbols to Functions to get table columns for functions
 */
public class FunctionSymbolToFunctionTableRowMapper
	extends ProgramLocationTableRowMapper<FunctionSymbol, Function> {

	@Override
	public Function map(FunctionSymbol rowObject, Program data, ServiceProvider serviceProvider) {
		return (Function) rowObject.getObject();
	}

}
