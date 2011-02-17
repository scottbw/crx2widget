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
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.wookie.w3c.W3CWidget;

import uk.ac.bolton.iec.crx2widget.exceptions.BadCrxException;
import uk.ac.bolton.iec.crx2widget.impl.CrxImpl;
import uk.ac.bolton.iec.crx2widget.util.ConfigBuilder;
import uk.ac.bolton.iec.crx2widget.util.ContentShimBuilder;
import uk.ac.bolton.iec.crx2widget.util.CrxPackageUtils;

import net.sf.sevenzipjbinding.SevenZipException;

/**
 * Factory for parsing a CRX and converting it to a Widget
 */
public class CrxFactory {

	private File unzippedCrxDirectory;
	private File outputDirectory;
	private String localPath;
	
	public CrxFactory(){
		this.localPath = "/widgets";
		this.outputDirectory = null;
	}
	
	/**
	 * Set the directory to use to save widgets.
	 * @param outputDirectory
	 * @throws IOException if the directory does not exist
	 */
	public void setOutputDirectory(final String outputDirectory) throws IOException {
		if (outputDirectory == null) throw new NullPointerException();
		File file = new File(outputDirectory);
		if (!file.exists()) throw new FileNotFoundException("the output directory does not exist");
		if (!file.canWrite()) throw new IOException("the output directory cannot be written to");
		if (!file.isDirectory()) throw new IOException("the output directory is not a folder");
		this.outputDirectory = file;
	}
	
	public W3CWidget convert(final File crxFile, boolean save) throws Exception{
		W3CWidget widget;
		if (save){
			widget =  Crx2Widget.convert(parse(crxFile,false));
			ConfigBuilder builder = new ConfigBuilder(widget);
			File configFile = new File(unzippedCrxDirectory,"config.xml");
			new ContentShimBuilder(widget).save(unzippedCrxDirectory);
			builder.save(configFile);
		} else {
			widget =  Crx2Widget.convert(parse(crxFile, true));			
		}
		return widget;
	}
	
	public W3CWidget convert(final File crxFile) throws Exception{
		return Crx2Widget.convert(parse(crxFile,true));
	}

	public Crx parse(final File crxFile) throws Exception{
		if (outputDirectory == null) throw new Exception("No output directory has been set; use setOutputDirectory(File) to set the location to output widget files");
		return processCrxPackage(crxFile,true);
	}
	
	public Crx parse(final File crxFile, boolean fixIconPaths) throws Exception{
		if (outputDirectory == null) throw new Exception("No output directory has been set; use setOutputDirectory(File) to set the location to output widget files");
		return processCrxPackage(crxFile,fixIconPaths);
	}
	
	private Crx processCrxPackage(File crxFile, boolean fixIconPaths) throws SevenZipException, BadCrxException, IOException{
		if (CrxPackageUtils.hasManifest(crxFile)){
			// build the model
			CrxImpl crx = new CrxImpl(CrxPackageUtils.extractManifest(crxFile));
			// get the widget identifier
			String crxIdentifier = crx.getIdentifier();
			// actually we'll use the file name for now
			crxIdentifier = crxFile.getName().replace(".crx","");
			// create the folder structure to unzip the zip into
			unzippedCrxDirectory = CrxPackageUtils.createUnpackedWidgetFolder(outputDirectory,crxIdentifier);
			// unzip
			CrxPackageUtils.unpackZip(crxFile, unzippedCrxDirectory);
			
			if(fixIconPaths){
				// get the path to the root of the unzipped folder
				String thelocalPath = CrxPackageUtils.getURLForWidget(localPath, crxIdentifier, "");
				// now pass this to the model which will prepend the path to local resources (not web icons)
				crx.updateIconPaths(thelocalPath);		
			}
			
			return crx;
		} else {
			throw new BadCrxException();
		}
	}
}
