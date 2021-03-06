/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * (c) Scott Wilson, 2011 <scott.bradley.wilson@gmail.com>
 */
package uk.ac.bolton.iec.crx2widget;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.wookie.w3c.W3CWidget;
import org.junit.Test;


public class CrxFactoryTest {
	
	@Test
	public void testParseCrx(){
		CrxFactory fac = new CrxFactory();
		
		// Create temp output directory
		File out = new File("src-tests/output");
		out.delete();
		out.mkdir();
		
		try {
			fac.setOutputDirectory("src-tests/output");
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		// Get test file
		File in = new File("src-tests/test1.crx");
		try {
			Crx crx = fac.parse(in);
			assertEquals("HuffingtonPost NewsGlide", crx.getName());
			assertEquals("http://www.huffingtonpost.com/NewsGlide/", crx.getWeb_url());
			assertEquals("The official Huffington Post Chrome app! Get all the news you need with up-to-the-minute reports, blogs and analysis.", crx.getDescription());
			assertEquals("/widgets/test1/resources/icon-H-chrome-128.png", crx.getIcon());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testConvertCrx(){
		CrxFactory fac = new CrxFactory();
		
		// Create temp output directory
		File out = new File("src-tests/output");
		out.delete();
		out.mkdir();
		
		try {
			fac.setOutputDirectory("src-tests/output");
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		// Get test file
		File in = new File("src-tests/test1.crx");
		try {
			W3CWidget widget = fac.convert(in,true);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
