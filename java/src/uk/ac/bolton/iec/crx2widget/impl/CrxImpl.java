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
package uk.ac.bolton.iec.crx2widget.impl;

import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.bolton.iec.crx2widget.Crx;

public class CrxImpl implements Crx{
	
	private String name;
	private String icon;
	private String description;
	private String web_url;
	private String container;
	private String version;
	private int height;
	private int width;
	private String update_url;

	public CrxImpl(JSONObject manifest) {
		try {
			this.name = manifest.getString("name");
		} catch (JSONException e) {
			this.name ="unknown";
		}
		try {
			this.description = manifest.getString("description");
		} catch (JSONException e) {
			this.description ="";
		}	
		try {
			this.web_url = manifest.getJSONObject("app").getJSONObject("launch").getString("web_url");
		} catch (JSONException e) {
			this.web_url = "";
		}
		try {
			this.icon = manifest.getJSONObject("icons").getString("128");
		} catch (JSONException e) {
			this.icon = null;
		}
		try {
			this.container = manifest.getJSONObject("app").getJSONObject("launch").getString("container");
		} catch (JSONException e) {
			this.container = "tab";
		}
		try {
			this.version = manifest.getString("version");
		} catch (JSONException e) {
			this.version ="";
		}	
		
	}
	
	public void updateIconPaths(String path){
		if (icon != null) if(!icon.startsWith("http:")) icon = path + icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getUpdate_url() {
		return update_url;
	}

	public void setUpdate_url(String update_url) {
		this.update_url = update_url;
	}

	public String getIdentifier() {
		// TODO Auto-generated method stub
		return "test";
	}


}
