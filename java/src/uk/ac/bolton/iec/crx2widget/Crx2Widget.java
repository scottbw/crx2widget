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

import org.apache.wookie.w3c.IContentEntity;
import org.apache.wookie.w3c.IIconEntity;
import org.apache.wookie.w3c.INameEntity;
import org.apache.wookie.w3c.IW3CXMLConfiguration;
import org.apache.wookie.w3c.W3CWidget;
import org.apache.wookie.w3c.impl.ContentEntity;
import org.apache.wookie.w3c.impl.IconEntity;
import org.apache.wookie.w3c.impl.NameEntity;

import uk.ac.bolton.iec.crx2widget.impl.W3CWidgetImpl;

public class Crx2Widget {
	
	public static W3CWidget convert(Crx crx){
		
		W3CWidgetImpl widget = new W3CWidgetImpl();
		
		INameEntity name = new NameEntity(crx.getName(), null, "en");
		widget.getNames().add(name);
		
		IContentEntity content = new ContentEntity(crx.getWeb_url(), IW3CXMLConfiguration.DEFAULT_CHARSET, IW3CXMLConfiguration.DEFAULT_MEDIA_TYPE);
		widget.getContentList().add(content);
		
		widget.setHeight(crx.getHeight());
		
		widget.setWidth(crx.getWidth());
		
		IIconEntity icon = new IconEntity(crx.getIcon(),128,128);
		widget.getIconsList().add(icon);
		
		if(crx.getContainer().equals("tab")){
			widget.setViewmodes("fullscreen");
		} else {
			widget.setViewmodes("windowed floating");
		}
		
		widget.setVersion(crx.getVersion());
		
		return widget;
	}

}
