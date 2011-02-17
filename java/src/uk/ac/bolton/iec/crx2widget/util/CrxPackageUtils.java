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
package uk.ac.bolton.iec.crx2widget.util;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.wookie.w3c.util.WidgetPackageUtils;
import org.json.JSONException;
import org.json.JSONObject;

import net.sf.sevenzipjbinding.SevenZipException;

public class CrxPackageUtils extends WidgetPackageUtils{

	public static boolean hasManifest(File file){
		return SevenZipUtils.zipContainsFile(file, "manifest.json");
	}
	
	public static JSONObject extractManifest(File file){
		try {
			String manifest = SevenZipUtils.extract(file, "manifest.json");
			JSONObject obj = new JSONObject(manifest);
			return obj;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SevenZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void unpackZip(File file, File unzippedCrxDirectory) throws FileNotFoundException, SevenZipException{
		SevenZipUtils.unzip(file, unzippedCrxDirectory);
	}
	
}
