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

import java.io.File;
import java.io.IOException;

import net.sf.sevenzipjbinding.SevenZipException;

import org.junit.Test;

import uk.ac.bolton.iec.crx2widget.util.SevenZipUtils;


public class SevenZipUtilsTest {
	
	@Test
	public void testUnzipFile() throws IOException, SevenZipException{
		// Get test file
		File in = new File("src-tests/test1.crx");
		// Create temp output directory
		File out = new File("src-tests/output");
		out.delete();
		out.mkdir();
		// Unzip
		SevenZipUtils.unzip(in,out);
		// Done
		
	}

}
