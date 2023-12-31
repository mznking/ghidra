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
package ghidra.dbg.target.schema;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jdom.JDOMException;
import org.junit.Test;

import ghidra.dbg.target.TargetInterpreter;
import ghidra.dbg.target.TargetProcess;
import ghidra.dbg.target.schema.DefaultTargetObjectSchema.DefaultAttributeSchema;
import ghidra.dbg.target.schema.TargetObjectSchema.*;

public class XmlTargetObjectSchemaTest {
	protected static final String SCHEMA_XML =
		// Do not line-wrap or serialize test will fail
		"""
				<context>
				    <schema name="root" canonical="yes" elementResync="NEVER" attributeResync="ONCE">
				        <interface name="Process" />
				        <interface name="Interpreter" />
				        <element index="reserved" schema="VOID" />
				        <element schema="down1" />
				        <attribute name="some_int" schema="INT" />
				        <attribute name="some_object" schema="OBJECT" required="yes" fixed="yes" hidden="yes" />
				        <attribute schema="ANY" hidden="yes" />
				        <attribute-alias from="_int" to="some_int" />
				    </schema>
				    <schema name="down1" elementResync="ALWAYS" attributeResync="ALWAYS">
				        <element schema="OBJECT" />
				        <attribute schema="VOID" fixed="yes" hidden="yes" />
				    </schema>
				</context>"""; // Cannot have final final new-line or serialize test will fail

	protected static final DefaultSchemaContext CTX = new DefaultSchemaContext();
	protected static final SchemaName NAME_ROOT = new SchemaName("root");
	protected static final SchemaName NAME_DOWN1 = new SchemaName("down1");
	protected static final TargetObjectSchema SCHEMA_ROOT = CTX.builder(NAME_ROOT)
			.addInterface(TargetProcess.class)
			.addInterface(TargetInterpreter.class)
			.setCanonicalContainer(true)
			.addElementSchema("reserved", EnumerableTargetObjectSchema.VOID.getName(), null)
			.addElementSchema("", NAME_DOWN1, null)
			.setElementResyncMode(ResyncMode.NEVER)
			.addAttributeSchema(new DefaultAttributeSchema("some_int",
				EnumerableTargetObjectSchema.INT.getName(), false, false, false), null)
			.addAttributeSchema(new DefaultAttributeSchema("some_object",
				EnumerableTargetObjectSchema.OBJECT.getName(), true, true, true), null)
			.addAttributeAlias("_int", "some_int", null)
			.setAttributeResyncMode(ResyncMode.ONCE)
			.buildAndAdd();
	protected static final TargetObjectSchema SCHEMA_DOWN1 = CTX.builder(NAME_DOWN1)
			.setElementResyncMode(ResyncMode.ALWAYS)
			.setDefaultAttributeSchema(AttributeSchema.DEFAULT_VOID)
			.setAttributeResyncMode(ResyncMode.ALWAYS)
			.buildAndAdd();

	@Test
	public void testSerialize() {
		String serialized =
			XmlSchemaContext.serialize(CTX).replace("\t", "    ").replace("\r", "").trim();
		assertEquals(SCHEMA_XML, serialized);
	}

	@Test
	public void testDeserialize() throws JDOMException, IOException {
		SchemaContext result = XmlSchemaContext.deserialize(SCHEMA_XML);
		assertEquals(CTX, result);
	}
}
