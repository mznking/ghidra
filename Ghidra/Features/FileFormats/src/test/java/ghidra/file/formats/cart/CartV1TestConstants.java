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
package ghidra.file.formats.cart;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import generic.test.AbstractGTest;

public class CartV1TestConstants {
	/**
	 * Original content that was CaRT-ed for these tests
	 */
	public static final byte[] TEST_ORIGINAL_DATA = AbstractGTest.bytes(0x00, 0x01, 0x02, 0x03,
		0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12,
		0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20, 0x21,
		0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2a, 0x2b, 0x2c, 0x2d, 0x2e, 0x2f, 0x30,
		0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3a, 0x3b, 0x3c, 0x3d, 0x3e, 0x3f,
		0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e,
		0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x5b, 0x5c, 0x5d,
		0x5e, 0x5f, 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c,
		0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a, 0x7b,
		0x7c, 0x7d, 0x7e, 0x7f, 0x80, 0x81, 0x82, 0x83, 0x84, 0x85, 0x86, 0x87, 0x88, 0x89, 0x8a,
		0x8b, 0x8c, 0x8d, 0x8e, 0x8f, 0x90, 0x91, 0x92, 0x93, 0x94, 0x95, 0x96, 0x97, 0x98, 0x99,
		0x9a, 0x9b, 0x9c, 0x9d, 0x9e, 0x9f, 0xa0, 0xa1, 0xa2, 0xa3, 0xa4, 0xa5, 0xa6, 0xa7, 0xa8,
		0xa9, 0xaa, 0xab, 0xac, 0xad, 0xae, 0xaf, 0xb0, 0xb1, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7,
		0xb8, 0xb9, 0xba, 0xbb, 0xbc, 0xbd, 0xbe, 0xbf, 0xc0, 0xc1, 0xc2, 0xc3, 0xc4, 0xc5, 0xc6,
		0xc7, 0xc8, 0xc9, 0xca, 0xcb, 0xcc, 0xcd, 0xce, 0xcf, 0xd0, 0xd1, 0xd2, 0xd3, 0xd4, 0xd5,
		0xd6, 0xd7, 0xd8, 0xd9, 0xda, 0xdb, 0xdc, 0xdd, 0xde, 0xdf, 0xe0, 0xe1, 0xe2, 0xe3, 0xe4,
		0xe5, 0xe6, 0xe7, 0xe8, 0xe9, 0xea, 0xeb, 0xec, 0xed, 0xee, 0xef, 0xf0, 0xf1, 0xf2, 0xf3,
		0xf4, 0xf5, 0xf6, 0xf7, 0xf8, 0xf9, 0xfa, 0xfb, 0xfc, 0xfd, 0xfe, 0xff);

	/**
	 * Standard key that will be used for testing.
	 * 
	 * Usually matches the default ARC4 key, but may be easily changed here if a new value is used
	 */
	public static final byte[] TEST_STD_KEY = CartV1Constants.DEFAULT_ARC4_KEY;

	/**
	 * Test CaRT of a 256 byte walk using the standard default key
	 */
	public static final byte[] TEST_CART_GOOD_STD_KEY = AbstractGTest.bytes(0x43, 0x41, 0x52, 0x54,
		0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03, 0x01, 0x04, 0x01, 0x05,
		0x09, 0x02, 0x06, 0x03, 0x01, 0x04, 0x01, 0x05, 0x09, 0x02, 0x06, 0x17, 0x00, 0x00, 0x00,
		0x00, 0x00, 0x00, 0x00, 0xc2, 0xa4, 0xa5, 0x5c, 0x53, 0xd5, 0x43, 0xf7, 0x79, 0x56, 0x37,
		0xf6, 0x55, 0x6c, 0xb4, 0xc0, 0xcc, 0x92, 0xeb, 0x54, 0xfc, 0x6e, 0x01, 0xc1, 0x87, 0xca,
		0x3d, 0x3f, 0x4f, 0x9f, 0xcd, 0x5a, 0x17, 0x55, 0xa0, 0x04, 0x35, 0xe7, 0xad, 0xb6, 0xec,
		0xa2, 0x31, 0x9f, 0x42, 0x73, 0x07, 0x5b, 0x68, 0x7d, 0xa2, 0x95, 0xce, 0x41, 0x5b, 0x09,
		0x00, 0x29, 0xe8, 0x0e, 0x4a, 0x18, 0xac, 0x08, 0x07, 0x2a, 0x3e, 0x9c, 0x90, 0xb7, 0x9e,
		0x4a, 0x0c, 0x46, 0x0a, 0xf5, 0x3f, 0x3e, 0xc8, 0xa7, 0x5d, 0x5b, 0x2d, 0xfb, 0x43, 0xe5,
		0x1f, 0xdb, 0x53, 0x8a, 0xd8, 0xe1, 0xfe, 0x43, 0x8d, 0xd6, 0xce, 0xfc, 0xc3, 0x2e, 0x26,
		0x0c, 0x98, 0xf4, 0x1d, 0x1e, 0x26, 0x4e, 0xf6, 0x15, 0x8c, 0xaa, 0x13, 0xfb, 0xdf, 0xbd,
		0x4f, 0xc7, 0xe8, 0x3c, 0x2c, 0x65, 0x7a, 0x31, 0xef, 0x85, 0x0a, 0xa3, 0x12, 0x0c, 0xe0,
		0xf0, 0x7a, 0x39, 0x27, 0x41, 0xc7, 0x42, 0x2c, 0xeb, 0x9a, 0x29, 0x32, 0xca, 0x6b, 0x03,
		0xe5, 0xa7, 0x51, 0x11, 0xd1, 0xcb, 0xc1, 0x99, 0xc4, 0x46, 0xaf, 0x2e, 0x4b, 0xda, 0x50,
		0x93, 0x87, 0x06, 0x72, 0x54, 0x24, 0xd9, 0x99, 0x36, 0x3a, 0x0c, 0x21, 0x16, 0x35, 0xd1,
		0x2a, 0x49, 0xfa, 0x84, 0xff, 0xeb, 0x71, 0x2a, 0x1f, 0x9d, 0x58, 0xcb, 0xdb, 0xf8, 0xb9,
		0x33, 0x53, 0x61, 0x51, 0xa1, 0x21, 0xa2, 0x4f, 0x1c, 0x8f, 0xad, 0xd6, 0x01, 0x6d, 0x74,
		0x8d, 0xb5, 0xe8, 0x46, 0x0d, 0x72, 0x34, 0x2f, 0x3d, 0x69, 0x50, 0xb4, 0xc8, 0x85, 0xc2,
		0x3f, 0x82, 0x93, 0xfe, 0x7f, 0x70, 0xbe, 0x38, 0x12, 0x8f, 0xaa, 0x3a, 0x59, 0xb9, 0xa2,
		0x8b, 0x0a, 0xfc, 0xc8, 0x1c, 0x84, 0x32, 0x96, 0xcc, 0x5f, 0x8f, 0xb5, 0xee, 0xd9, 0x83,
		0x53, 0x2b, 0x9a, 0x30, 0x32, 0xb6, 0xcf, 0x3e, 0xa9, 0x41, 0x82, 0x9d, 0x4a, 0xa0, 0xda,
		0x79, 0xcb, 0xbc, 0x44, 0x28, 0xbc, 0x13, 0x52, 0xf9, 0x7d, 0x2e, 0xc0, 0x10, 0x0b, 0x5f,
		0x13, 0x61, 0xbb, 0xd0, 0xe6, 0x81, 0x71, 0x92, 0x1f, 0xc2, 0xa4, 0xa7, 0x58, 0x50, 0xd7,
		0x15, 0xa5, 0x79, 0x2f, 0x74, 0x96, 0x34, 0x05, 0xc2, 0x89, 0x9d, 0x8b, 0xcd, 0x08, 0xb0,
		0x76, 0x5e, 0x72, 0x78, 0x19, 0x56, 0x80, 0xb5, 0xbc, 0x34, 0x77, 0x21, 0x2c, 0x00, 0x96,
		0x76, 0x30, 0x3e, 0xba, 0x1a, 0x47, 0x6f, 0x7b, 0xd8, 0x8f, 0xf5, 0xd0, 0x55, 0x47, 0x0e,
		0x17, 0xe0, 0x77, 0x21, 0xda, 0xba, 0x4d, 0x1b, 0x71, 0xaf, 0x44, 0xf0, 0x1d, 0xc0, 0x5d,
		0x88, 0xd5, 0xea, 0xa4, 0x4a, 0xaf, 0xf3, 0xee, 0x88, 0xe1, 0x5c, 0x58, 0x2e, 0xe6, 0x85,
		0x67, 0x66, 0x5c, 0x3a, 0x80, 0x39, 0xbd, 0x99, 0x72, 0x9a, 0xef, 0xd9, 0x2c, 0xa8, 0x86,
		0x00, 0x17, 0x0a, 0x13, 0x5b, 0xd5, 0xbc, 0x09, 0xfa, 0x52, 0x43, 0xa6, 0xe6, 0x74, 0x3f,
		0x7d, 0x1d, 0x9b, 0x0b, 0x7a, 0xa4, 0xc0, 0x76, 0x23, 0xdd, 0x7f, 0x42, 0xf4, 0xeb, 0x43,
		0x54, 0xcd, 0x8a, 0x82, 0xd0, 0x8a, 0x5e, 0xe5, 0x66, 0xaa, 0x3d, 0xb6, 0x24, 0x35, 0xb7,
		0xcc, 0xb6, 0x9a, 0x69, 0x25, 0x8a, 0x82, 0xb8, 0x98, 0xa8, 0x90, 0x78, 0x8f, 0xe2, 0x5b,
		0x77, 0x0b, 0x18, 0xd8, 0xd7, 0xe4, 0x3e, 0xf3, 0x66, 0x20, 0x50, 0x28, 0xa3, 0xc1, 0xf0,
		0xc3, 0x32, 0xe5, 0x63, 0xde, 0x81, 0x11, 0x3e, 0x42, 0x9c, 0xe1, 0xa6, 0x54, 0x52, 0x41,
		0x43, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x48, 0x01, 0x00, 0x00, 0x00, 0x00,
		0x00, 0x00, 0xb7, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00);

	/**
	 * Private key used to create TEST_CART_GOOD_PRIVATE_KEY_ABC
	 */
	public static final String PRIVATE_KEY = "abc";

	/**
	 * Standard key that will be used for testing.
	 * 
	 * Usually matches the default ARC4 key, but may be easily changed here if a new value is used
	 */
	public static final byte[] TEST_PRIVATE_KEY = AbstractGTest.bytes(0x61, 0x62, 0x63, 0x00, 0x00,
		0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00);

	/**
	 * Test CaRT of a 256 byte walk using a private key of "abc"
	 */
	public static final byte[] TEST_CART_GOOD_PRIVATE_KEY_ABC = AbstractGTest.bytes(0x43, 0x41,
		0x52, 0x54, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
		0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x17, 0x00,
		0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0xa2, 0xb0, 0x8c, 0x7f, 0xd4, 0xa9, 0xa5, 0xd5,
		0xb7, 0xfa, 0x7b, 0x6c, 0x6a, 0xa5, 0x8d, 0xdc, 0xe1, 0xfb, 0xc0, 0x1f, 0xf6, 0xbd, 0x02,
		0x81, 0xdf, 0xed, 0x13, 0x4e, 0x75, 0x9f, 0xf6, 0xf6, 0x98, 0x2d, 0x3d, 0x33, 0xf6, 0xe0,
		0xa6, 0x9f, 0xb2, 0xa5, 0x7c, 0xda, 0xcf, 0x83, 0xa1, 0xdf, 0xef, 0xe3, 0x7a, 0xd3, 0x5f,
		0xd2, 0x6c, 0x80, 0x3a, 0x36, 0x89, 0xa8, 0x5b, 0xac, 0xc1, 0xce, 0x8a, 0x45, 0xa9, 0x62,
		0x19, 0x2e, 0x3d, 0x68, 0x3c, 0x0a, 0xa0, 0xcd, 0x06, 0x02, 0x3e, 0x67, 0x3a, 0xbe, 0xe3,
		0xef, 0x93, 0x00, 0x50, 0xf1, 0x9d, 0x9c, 0xce, 0x64, 0xf4, 0x5e, 0x2d, 0x6a, 0x64, 0x76,
		0x6c, 0x3a, 0x83, 0xbd, 0xf9, 0x72, 0x0d, 0xc2, 0x96, 0x92, 0x9a, 0xa1, 0xff, 0xe2, 0xbf,
		0xd1, 0x08, 0x9e, 0xb2, 0xb6, 0x50, 0xb2, 0x7e, 0x1b, 0xd8, 0x38, 0x9b, 0x82, 0xca, 0xc4,
		0xb0, 0xcf, 0x57, 0x23, 0xc7, 0x97, 0x71, 0x9d, 0xa2, 0x31, 0x99, 0x6f, 0x27, 0x5e, 0xdb,
		0xb5, 0x73, 0xf4, 0xae, 0x5d, 0x7d, 0xad, 0x5e, 0xe1, 0xb3, 0x52, 0x5c, 0x81, 0x5a, 0x0e,
		0x0a, 0x51, 0x7f, 0x78, 0x3b, 0x8b, 0x8d, 0x29, 0x8c, 0xbd, 0xe5, 0x68, 0x8b, 0x1e, 0xfa,
		0x53, 0xa0, 0xce, 0x29, 0x6c, 0x83, 0xbd, 0x73, 0x5e, 0x6f, 0x9b, 0x54, 0x5a, 0x20, 0x74,
		0x8e, 0xa6, 0x12, 0x26, 0xe2, 0x62, 0xe9, 0xc0, 0x1a, 0x1e, 0x1c, 0xea, 0x1b, 0x5e, 0xa3,
		0xa2, 0xaa, 0x35, 0xfc, 0x73, 0xd2, 0x2b, 0x51, 0xd1, 0xa9, 0x65, 0x98, 0x23, 0x00, 0x08,
		0xf7, 0xaf, 0x4b, 0x27, 0x90, 0xe8, 0xd0, 0xd0, 0x23, 0x00, 0xe0, 0x2c, 0x11, 0xa7, 0x3a,
		0xca, 0x6e, 0x4f, 0x12, 0xb8, 0x09, 0x49, 0x16, 0x59, 0x59, 0x42, 0xc8, 0xe0, 0x5d, 0x11,
		0xdf, 0x7f, 0x87, 0x02, 0xbf, 0x5b, 0x73, 0x12, 0x95, 0xe9, 0x8c, 0x8b, 0xb1, 0x41, 0x48,
		0xa6, 0xc0, 0x2c, 0x32, 0xed, 0xae, 0x87, 0x2c, 0xc3, 0xd9, 0xfe, 0x37, 0xeb, 0x93, 0x09,
		0x5b, 0x8c, 0x6a, 0xb1, 0xab, 0xc6, 0xbf, 0xac, 0x37, 0x1f, 0x98, 0x01, 0xa2, 0xb2, 0x88,
		0x7c, 0xd6, 0xff, 0xf7, 0xd5, 0xce, 0xb9, 0x1b, 0x0d, 0x03, 0xd3, 0xc4, 0x8d, 0xf8, 0xdd,
		0x9c, 0x53, 0xee, 0xe2, 0xf6, 0x82, 0xae, 0xc4, 0xc1, 0x5a, 0xa1, 0x2a, 0xfe, 0x44, 0xac,
		0x13, 0x48, 0xf1, 0xd2, 0x7d, 0xba, 0xd3, 0x8e, 0xcf, 0x00, 0xed, 0x7d, 0x5b, 0x60, 0x22,
		0x23, 0x74, 0x17, 0xb5, 0x85, 0x19, 0x10, 0x23, 0x77, 0x7a, 0xe2, 0xb7, 0xe8, 0x86, 0x02,
		0x4b, 0xff, 0x9f, 0x91, 0xc5, 0x3e, 0xfd, 0x7c, 0x08, 0x4a, 0x10, 0x54, 0x1e, 0x44, 0xa1,
		0xc3, 0x88, 0x08, 0x75, 0xb8, 0xe2, 0xe4, 0xb6, 0x90, 0xcc, 0x83, 0xde, 0xe1, 0x6c, 0xfd,
		0xdd, 0xd8, 0x6c, 0x89, 0x11, 0x72, 0xb2, 0x02, 0xa2, 0x81, 0x93, 0x84, 0xff, 0x89, 0x41,
		0x2d, 0xc1, 0xcd, 0x2d, 0xc1, 0xeb, 0x67, 0xd6, 0x35, 0x78, 0x4f, 0xcc, 0xa1, 0x32, 0xe5,
		0xe2, 0x4f, 0x38, 0xb1, 0x1f, 0xa2, 0xfa, 0x1c, 0x44, 0xcb, 0x12, 0xef, 0xed, 0xb7, 0xc8,
		0xca, 0x8a, 0x35, 0x6f, 0x97, 0x3c, 0x01, 0x59, 0xd0, 0x3f, 0xa7, 0x44, 0xf6, 0x09, 0x6b,
		0x82, 0xcd, 0x70, 0x49, 0x80, 0xf7, 0x92, 0x60, 0xf7, 0xf1, 0x8d, 0x8f, 0x26, 0x37, 0x82,
		0xb4, 0x73, 0xf0, 0x7a, 0x04, 0xdb, 0x8f, 0x81, 0x74, 0x88, 0xca, 0x3e, 0x2e, 0x78, 0x54,
		0x52, 0x41, 0x43, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x48, 0x01, 0x00, 0x00,
		0x00, 0x00, 0x00, 0x00, 0xb7, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00);

	/**
	 * Original file name of file CaRTed as TEST_CART_GOOD_STD_KEY and
	 * TEST_CART_GOOD_PRIVATE_KEY_ABC
	 */
	public static final String CARTED_FILE_NAME = "CaRT_TestBin";

	/**
	 * Original file size of CaRT-ed test data
	 */
	public static final int CARTED_FILE_SIZE = 256;

	/**
	 * Compressed size of CaRT-ed test data.
	 * Note: This is bigger than the original size because there are no repeated bytes and it
	 * doesn't compress well.
	 */
	public static final int CARTED_COMPRESSED_FILE_SIZE = 267;

	/**
	 * MD5 of the original data
	 */
	public static final String TEST_MD5 = "e2c865db4162bed963bfaa9ef6ac18f0";

	/**
	 * SHA1 of the original data
	 */
	public static final String TEST_SHA1 = "4916d6bdb7f78e6803698cab32d1586ea457dfc8";

	/**
	 * SHA256 of the original data
	 */
	public static final String TEST_SHA256 =
		"40aff2e9d2d8922e47afd4648e6967497158785fbd1da870e7110266bf944880";

	/**
	 * The raw data (JSON string) of the optional header data in the test
	 */
	public static final String OPTIONAL_HEADER_DATA_RAW = "{\"name\":\"" + CARTED_FILE_NAME + "\"}";

	/**
	 * Length of the raw optional header data
	 */
	public static final long OPTIONAL_HEADER_LENGTH = OPTIONAL_HEADER_DATA_RAW.length();

	/**
	 * Raw optional header data as a parsed JSON object
	 */
	public static final JsonObject OPTIONAL_HEADER_DATA =
		new Gson().fromJson(OPTIONAL_HEADER_DATA_RAW, JsonObject.class);

	/**
	 * The raw data (JSON string) of the optional footer data in the test
	 */
	public static final String OPTIONAL_FOOTER_DATA_RAW =
		"{" + "\"length\":\"" + CARTED_FILE_SIZE + "\"," + "\"md5\":\"" + TEST_MD5 + "\"," +
			"\"sha1\":\"" + TEST_SHA1 + "\"," + "\"sha256\":\"" + TEST_SHA256 + "\"" + "}";

	/**
	 * Length of the raw optional footer data
	 */
	public static final long OPTIONAL_FOOTER_LENGTH = OPTIONAL_FOOTER_DATA_RAW.length();

	/**
	 * Raw optional footer data as a parsed JSON object
	 */
	public static final JsonObject OPTIONAL_FOOTER_DATA =
		new Gson().fromJson(OPTIONAL_FOOTER_DATA_RAW, JsonObject.class);
}
