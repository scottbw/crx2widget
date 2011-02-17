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

import org.apache.wookie.w3c.IW3CXMLConfiguration;
import org.apache.wookie.w3c.W3CWidget;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class ConfigBuilder {
	
	W3CWidget widget;
	
	public ConfigBuilder(W3CWidget widget){
		this.widget = widget;
	}
	
	public void save(File file) throws IOException{
		Element widgetElem = new Element(IW3CXMLConfiguration.WIDGET_ELEMENT,IW3CXMLConfiguration.MANIFEST_NAMESPACE);
		if (widget.getHeight() > 0) widgetElem.setAttribute(IW3CXMLConfiguration.HEIGHT_ATTRIBUTE,String.valueOf(widget.getHeight()));
		if (widget.getHeight() > 0) widgetElem.setAttribute(IW3CXMLConfiguration.WIDTH_ATTRIBUTE,String.valueOf(widget.getWidth()));
		
		// Name
		Element nameElem = new Element(IW3CXMLConfiguration.NAME_ELEMENT, IW3CXMLConfiguration.MANIFEST_NAMESPACE);
		nameElem.setText(widget.getLocalName("en"));
		widgetElem.addContent(nameElem);
		
		// Icons
		if(widget.getIconsList().size()!=0){
			Element iconElem = new Element(IW3CXMLConfiguration.ICON_ELEMENT,IW3CXMLConfiguration.MANIFEST_NAMESPACE);
			iconElem.setAttribute(IW3CXMLConfiguration.SOURCE_ATTRIBUTE,widget.getIconsList().get(0).getSrc());
			widgetElem.addContent(iconElem);
		}
		
		// Content
		Element contentElem = new Element(IW3CXMLConfiguration.CONTENT_ELEMENT,IW3CXMLConfiguration.MANIFEST_NAMESPACE);
		contentElem.setAttribute(IW3CXMLConfiguration.SOURCE_ATTRIBUTE, widget.getContentList().get(0).getSrc());
		widgetElem.addContent(contentElem);
		
		Document doc = new Document(widgetElem);
		
		
		XMLOutputter out = new XMLOutputter();
		FileOutputStream fos = new FileOutputStream(file);
		out.output(doc, fos);
	}

}
