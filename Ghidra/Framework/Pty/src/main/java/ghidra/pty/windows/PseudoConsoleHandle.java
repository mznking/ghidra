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
package ghidra.pty.windows;

import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.COM.COMUtils;

import ghidra.pty.windows.jna.ConsoleApiNative;
import ghidra.pty.windows.jna.ConsoleApiNative.COORD;

public class PseudoConsoleHandle extends Handle {

	protected static class PseudoConsoleState extends State {
		public PseudoConsoleState(HANDLE handle) {
			super(handle);
		}

		@Override
		public void run() {
			ConsoleApiNative.INSTANCE.ClosePseudoConsole(handle);
		}
	}

	public PseudoConsoleHandle(HANDLE handle) {
		super(handle);
	}

	@Override
	protected State newState(HANDLE handle) {
		return new PseudoConsoleState(handle);
	}

	public void resize(short rows, short cols) {
		COORD.ByValue size = new COORD.ByValue();
		size.X = cols;
		size.Y = rows;
		COMUtils.checkRC(ConsoleApiNative.INSTANCE.ResizePseudoConsole(getNative(), size));
	}
}
