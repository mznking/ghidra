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
package ghidra.pcode.exec;

import ghidra.pcode.emu.DefaultPcodeThread.PcodeEmulationLibrary;

/**
 * Exception thrown by {@link PcodeEmulationLibrary#emu_injection_err()}, a p-code userop invoked
 * when client-provided Sleigh code in an injection could not be compiled.
 */
public class InjectionErrorPcodeExecutionException extends PcodeExecutionException {
	public InjectionErrorPcodeExecutionException(PcodeFrame frame, Throwable cause) {
		super("Error compiling injected Sleigh source", frame, cause);
	}
}
