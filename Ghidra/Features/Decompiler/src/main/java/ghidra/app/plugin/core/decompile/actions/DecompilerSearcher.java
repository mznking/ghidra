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
package ghidra.app.plugin.core.decompile.actions;

import java.util.List;
import java.util.Objects;

import docking.widgets.*;
import docking.widgets.fieldpanel.field.Field;
import docking.widgets.fieldpanel.support.FieldLocation;
import ghidra.app.decompiler.component.ClangTextField;
import ghidra.app.decompiler.component.DecompilerPanel;

/**
 * A {@link FindDialogSearcher} for searching the text of the decompiler window.
 */
public class DecompilerSearcher implements FindDialogSearcher {

	private DecompilerPanel decompilerPanel;

	/**
	 * Constructor
	 * @param decompilerPanel decompiler panel
	 */
	public DecompilerSearcher(DecompilerPanel decompilerPanel) {
		this.decompilerPanel = decompilerPanel;
	}

	@Override
	public CursorPosition getCursorPosition() {
		FieldLocation fieldLocation = decompilerPanel.getCursorPosition();
		return new DecompilerCursorPosition(fieldLocation);
	}

	@Override
	public CursorPosition getStart() {

		int lineNumber = 0;
		int fieldNumber = 0; // always 0, as the field is the entire line and it is the only field
		int column = 0; // or length for the end
		FieldLocation fieldLocation = new FieldLocation(lineNumber, fieldNumber, 0, column);
		return new DecompilerCursorPosition(fieldLocation);
	}

	@Override
	public CursorPosition getEnd() {

		List<Field> lines = decompilerPanel.getFields();
		int lineNumber = lines.size() - 1;
		ClangTextField textLine = (ClangTextField) lines.get(lineNumber);

		int fieldNumber = 0; // always 0, as the field is the entire line and it is the only field
		int rowCount = textLine.getNumRows();
		int row = rowCount - 1; // 0-based
		int column = textLine.getNumCols(row);
		FieldLocation fieldLocation = new FieldLocation(lineNumber, fieldNumber, row, column);
		return new DecompilerCursorPosition(fieldLocation);
	}

	@Override
	public void setCursorPosition(CursorPosition position) {
		decompilerPanel.setCursorPosition(((DecompilerCursorPosition) position).getFieldLocation());
	}

	@Override
	public void highlightSearchResults(SearchLocation location) {
		decompilerPanel.setSearchResults(location);
	}

	@Override
	public SearchLocation search(String text, CursorPosition position, boolean searchForward,
			boolean useRegex) {
		DecompilerCursorPosition decompilerCursorPosition = (DecompilerCursorPosition) position;
		FieldLocation startLocation =
			getNextSearchStartLocation(decompilerCursorPosition, searchForward);
		return useRegex ? decompilerPanel.searchTextRegex(text, startLocation, searchForward)
				: decompilerPanel.searchText(text, startLocation, searchForward);
	}

	private FieldLocation getNextSearchStartLocation(
			DecompilerCursorPosition decompilerCursorPosition, boolean searchForward) {

		FieldLocation startLocation = decompilerCursorPosition.getFieldLocation();
		FieldBasedSearchLocation currentSearchLocation = decompilerPanel.getSearchResults();
		if (currentSearchLocation == null) {
			return startLocation; // nothing to do; no prior search hit
		}

		//
		// Special Case Handling:  Start the search at the cursor location by default.
		// However, if the cursor location is at the beginning of previous search hit, then
		// move the cursor forward by one character to ensure the previous search hit is not
		// found.
		//
		// Note: for a forward or backward search the cursor is placed at the beginning of the
		// match.
		//
		if (Objects.equals(startLocation, currentSearchLocation.getFieldLocation())) {

			if (searchForward) {
				// Given:
				// -search text: 'fox'
				// -search domain: 'What the |fox say'
				// -a previous search hit just before 'fox'
				//
				// Move the cursor just past the 'f' so the next forward search will not
				// find the current 'fox' hit.  Thus the new search domain for this line
				// will be: "ox say"
				//
				startLocation.col += 1;
			}
			else {
				// Given:
				// -search text: 'fox'
				// -search domain: 'What the |fox say'
				// -a previous search hit just before 'fox'
				//
				// Move the cursor just past the 'o' so the next backward search will not
				// find the current 'fox' hit.  Thus the new search domain for this line
				// will be: "What the fo"
				//
				int length = currentSearchLocation.getMatchLength();
				startLocation.col += length - 1;
			}
		}

		return startLocation;
	}
}
