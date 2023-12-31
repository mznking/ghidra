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
package ghidra.app.plugin.core.debug.gui.watch;

import java.awt.Component;
import java.util.Collection;
import java.util.Set;

import docking.DefaultActionContext;
import ghidra.debug.api.watch.WatchRow;

public class DebuggerWatchActionContext extends DefaultActionContext {
	private final Set<WatchRow> sel;

	public DebuggerWatchActionContext(DebuggerWatchesProvider provider,
			Collection<? extends WatchRow> sel, Component sourceComponent) {
		super(provider, sel, sourceComponent);
		this.sel = Set.copyOf(sel);
	}

	public WatchRow getWatchRow() {
		if (sel.size() == 1) {
			return sel.iterator().next();
		}
		return null;
	}

	public Set<WatchRow> getWatchRows() {
		return sel;
	}
}
