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
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import net.sf.sevenzipjbinding.ISequentialOutStream;

public class SevenZipUtils {

	public static boolean zipContainsFile(File crx, String filename){
		RandomAccessFile file;
		try {
			file = new RandomAccessFile(crx, "r");
			ISimpleInArchive inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(file)).getSimpleInterface();
			for (ISimpleInArchiveItem item : inArchive.getArchiveItems()) {
				if (item.getPath().equals(filename)) return true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SevenZipException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public static String extract(File crx, String filename) throws FileNotFoundException, SevenZipException{
		RandomAccessFile file;
		file = new RandomAccessFile(crx, "r");
		String out = null;
		ISimpleInArchive inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(file)).getSimpleInterface();
		for (ISimpleInArchiveItem item : inArchive.getArchiveItems()) {
			if (item.getPath().equals(filename)){
				Extractor extractor = new Extractor(item, out);
				item.extractSlow(extractor);
				out = extractor.getOutput();
			}
		}
		return out;
	}

	public static void unzip(File crx, final File target) throws FileNotFoundException, SevenZipException{

		RandomAccessFile file = new RandomAccessFile(crx, "r");
		ISimpleInArchive inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(file)).getSimpleInterface();

		for (ISimpleInArchiveItem item : inArchive.getArchiveItems()) {
			if (!item.isFolder()) {
				ExtractOperationResult result;
				Extractor extractor = new Extractor(item,target);
				result = item.extractSlow(extractor);
				if (result != ExtractOperationResult.OK) throw new SevenZipException("Error extracting item: " + result);
			}
		}
		inArchive.close();

	}

}

class Extractor implements ISequentialOutStream{
	ISimpleInArchiveItem item;
	File out;
	String output;

	public Extractor(ISimpleInArchiveItem item, String output){
		this.output = output;
	}

	public Extractor(ISimpleInArchiveItem item, File target) throws SevenZipException{
		this.item = item;
		out = new File(target,item.getPath());
		out.mkdirs();
		out.delete();
	}

	public String getOutput(){
		return output;
	}

	@Override
	public int write(byte[] data) throws SevenZipException {
		if (out != null){
			try {
				FileOutputStream fs = new FileOutputStream(out);
				fs.write(data);
			} catch (Exception e) {
				throw new SevenZipException("Problem writing output from extractor:"+e.getMessage());
			} 
		} else {
			output = new String(data);
		}
		return data.length;
	}
}

