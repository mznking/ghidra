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
package ghidra.app.services;

import ghidra.dbg.target.TargetConsole;
import ghidra.dbg.target.TargetInterpreter;
import ghidra.debug.api.interpreter.DebuggerInterpreterConnection;
import ghidra.framework.plugintool.ServiceInfo;

@ServiceInfo(
	defaultProviderName = "ghidra.app.plugin.core.debug.gui.interpreters.DebuggerInterpreterPlugin",
	description = "Service for managing debugger interpreter panels")
public interface DebuggerInterpreterService {
	DebuggerInterpreterConnection showConsole(TargetConsole console);

	DebuggerInterpreterConnection showConsole(TargetInterpreter interpreter);
}
