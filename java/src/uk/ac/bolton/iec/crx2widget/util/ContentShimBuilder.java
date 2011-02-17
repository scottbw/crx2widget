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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.wookie.w3c.W3CWidget;

public class ContentShimBuilder {

	String src;
	String height;
	String width;

	public ContentShimBuilder(W3CWidget widget){
		this.src = widget.getContentList().get(0).getSrc();
		this.height = String.valueOf(widget.getHeight());
		this.width = String.valueOf(widget.getWidth());	
		
		if (height.equals("0")) height="100%";
		if (width.equals("0")) width="100%";
	}

	public void save(File widgetFolder) throws IOException{
		String content = template;
		content = content.replace("$src", src);
		content = content.replace("$height",height);
		content = content.replace("$width", width);

		File indexFile = new File(widgetFolder, "index.html");
		Writer out = new OutputStreamWriter(new FileOutputStream(indexFile), "UTF-8");

		try {
			out.write(content);
		}
		finally {
			out.close();
		}
	}

	String template = "<html><body style=\"margin:0 0 0 0;padding: 0 0 0 0\"><iframe style=\"border:none;overflow:auto;margin:0 0 0 0;padding: 0 0 0 0\" src=\"$src\" height=\"$height\" width=\"$width\"></iframe></body></html>";

}
