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

public interface Crx {

	public String getName();

	public void setName(String name);

	public String getIcon();

	public void setIcon(String icon);

	public String getDescription();

	public void setDescription(String description);

	public String getWeb_url();

	public void setWeb_url(String web_url);

	public String getContainer();

	public void setContainer(String container);

	public String getVersion();

	public void setVersion(String version);

	public int getHeight();

	public void setHeight(int height);

	public int getWidth();

	public void setWidth(int width);

	public String getUpdate_url();

	public void setUpdate_url(String update_url);

	public String getIdentifier();

}
