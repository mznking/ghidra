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
package ghidra.app.util.pdb.pdbapplicator;

import ghidra.app.util.bin.format.pdb2.pdbreader.MsSymbolIterator;
import ghidra.app.util.bin.format.pdb2.pdbreader.PdbException;
import ghidra.app.util.bin.format.pdb2.pdbreader.symbol.AbstractMsSymbol;
import ghidra.app.util.bin.format.pdb2.pdbreader.symbol.AbstractReferenceMsSymbol;
import ghidra.util.exception.AssertException;
import ghidra.util.exception.CancelledException;

/**
 * Applier for {@link AbstractReferenceMsSymbol} symbols.
 */
public class ReferenceSymbolApplier extends MsSymbolApplier {

	private AbstractReferenceMsSymbol symbol;

	/**
	 * Constructor
	 * @param applicator the {@link DefaultPdbApplicator} for which we are working.
	 * @param iter the Iterator containing the symbol sequence being processed
	 */
	public ReferenceSymbolApplier(DefaultPdbApplicator applicator, MsSymbolIterator iter) {
		super(applicator, iter);
		AbstractMsSymbol abstractSymbol = iter.next();
		if (!(abstractSymbol instanceof AbstractReferenceMsSymbol)) {
			throw new AssertException(
				"Invalid symbol type: " + abstractSymbol.getClass().getSimpleName());
		}
		symbol = (AbstractReferenceMsSymbol) abstractSymbol;
	}

	@Override
	void applyTo(MsSymbolApplier applyToApplier) {
		// Do nothing.
	}

	@Override
	void apply() throws CancelledException, PdbException {
		// Potential recursive call via applicator.procSym().
		MsSymbolIterator refIter = getInitializedReferencedSymbolGroupIterator();
		if (refIter == null) {
			throw new PdbException("PDB: Referenced Symbol Error - null refIter");
		}
		applicator.procSym(refIter);
	}

	MsSymbolIterator getInitializedReferencedSymbolGroupIterator() throws PdbException {
		SymbolGroup refSymbolGroup = getReferencedSymbolGroup();
		if (refSymbolGroup == null) {
			return null;
		}
		MsSymbolIterator refIter = refSymbolGroup.getSymbolIterator();
		refIter.initGetByOffset(getOffsetInReferencedSymbolGroup());
		return refIter;
	}

	SymbolGroup getReferencedSymbolGroup() {
		int refModuleNumber = symbol.getModuleIndex();
		return applicator.getSymbolGroupForModule(refModuleNumber);
	}

	long getOffsetInReferencedSymbolGroup() {
		return symbol.getOffsetActualSymbolInDollarDollarSymbols();
	}

}
