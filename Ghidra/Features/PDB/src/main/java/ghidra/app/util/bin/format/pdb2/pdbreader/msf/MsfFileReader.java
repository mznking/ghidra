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
package ghidra.app.util.bin.format.pdb2.pdbreader.msf;

import java.io.IOException;
import java.io.RandomAccessFile;

import ghidra.app.util.bin.ByteProvider;

/**
 * This class is responsible for reading pages from a {@link RandomAccessFile} for the
 *  {@link Msf} class and its underlying classes.
 */
class MsfFileReader implements AutoCloseable {

	//==============================================================================================
	// Internals
	//==============================================================================================
	private ByteProvider byteProvider;
	private Msf msf;

	//==============================================================================================
	// API
	//==============================================================================================
	/**
	 * Closes this class, including its underlying file resources
	 * @throws IOException under circumstances found when closing a {@link RandomAccessFile}
	 */
	@Override
	public void close() throws IOException {
		if (byteProvider != null) {
			byteProvider.close();
		}
	}

	//==============================================================================================
	// Package-Protected Internals
	//==============================================================================================
	/**
	 * Constructor
	 * @param msf the {@link Msf} for which this class is to be associated
	 * @param byteProvider the ByteProvider providing bytes for the MSF
	 */
	MsfFileReader(Msf msf, ByteProvider byteProvider) {
		this.msf = msf;
		this.byteProvider = byteProvider;
	}

	/**
	 * Reads a single page of bytes from the {@link Msf} and writes it into the bytes array
	 * @param page the page number to read from the file
	 * @param bytes the byte[] into which the data is to be written
	 * @throws IOException on file seek or read, invalid parameters, bad file configuration, or
	 *  inability to read required bytes
	 */
	void readPage(int page, byte[] bytes) throws IOException {
		read(page, 0, msf.getPageSize(), bytes, 0);
	}

	/**
	 * Reads bytes from the {@link Msf} into a byte[]
	 * @param page the page number within which to start the read
	 * @param offset the byte offset within the page to start the read
	 * @param numToRead the total number of bytes to read
	 * @param bytes the byte[] into which the data is to be written
	 * @param bytesOffset the starting offset within the bytes array in which to start writing
	 * @throws IOException on file seek or read, invalid parameters, bad file configuration, or
	 *  inability to read required bytes
	 */
	void read(int page, int offset, int numToRead, byte[] bytes, int bytesOffset)
			throws IOException {

		if (numToRead < 1) {
			throw new IOException("Must request at least one byte in MSF read");
		}

		if (offset < 0 || offset >= msf.getPageSize()) {
			throw new IOException(String.format("Offset must be in range [0, %d) in for MSF read",
				msf.getPageSize()));
		}

		// Calculate true offset within file.
		long fileOffset = offset + page * (long) msf.getPageSize();

		// Fail if file does not contain enough pages for the read--boundary case that assumes
		//  everything beyond the offset in the file belongs to this read.
		if (Msf.floorDivisionWithLog2Divisor(offset + numToRead, msf.getLog2PageSize()) > msf
				.getNumPages()) {
			throw new IOException("Invalid MSF configuration");
		}

		System.arraycopy(byteProvider.readBytes(fileOffset, numToRead), 0, bytes, bytesOffset,
			numToRead);
	}

}
