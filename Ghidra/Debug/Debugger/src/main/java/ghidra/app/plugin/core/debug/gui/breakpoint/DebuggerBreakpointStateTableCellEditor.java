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
package ghidra.app.plugin.core.debug.gui.breakpoint;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.TableCellEditor;

import docking.widgets.table.GTableFilterPanel;
import ghidra.debug.api.breakpoint.LogicalBreakpoint.State;

public abstract class DebuggerBreakpointStateTableCellEditor<T> extends AbstractCellEditor
		implements TableCellEditor, ActionListener {
	private final GTableFilterPanel<T> filterPanel;
	protected final JButton button = new JButton();

	private State value = State.NONE;
	private T row;

	public DebuggerBreakpointStateTableCellEditor(
			GTableFilterPanel<T> filterPanel) {
		this.filterPanel = filterPanel;

		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setOpaque(true);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setUI(new BasicButtonUI());

		button.addActionListener(this);
	}

	@Override
	public Object getCellEditorValue() {
		return value;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column) {
		if (isSelected) {
			button.setBackground(table.getSelectionBackground());
		}
		else {
			// TODO: Alternating colors? Can't inherit GTableCellRenderer....
			button.setBackground(table.getBackground());
		}
		this.row = filterPanel.getRowObject(row);
		this.value = (State) value;
		button.setIcon(this.value.icon);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		value = getToggledState(row, value);
		fireEditingStopped();
	}

	protected abstract State getToggledState(T row, State current);
}
