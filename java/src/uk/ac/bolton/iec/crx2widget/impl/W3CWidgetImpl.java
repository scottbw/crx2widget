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

import java.util.ArrayList;
import java.util.List;

import org.apache.wookie.w3c.IAccessEntity;
import org.apache.wookie.w3c.IAuthorEntity;
import org.apache.wookie.w3c.IContentEntity;
import org.apache.wookie.w3c.IDescriptionEntity;
import org.apache.wookie.w3c.IFeatureEntity;
import org.apache.wookie.w3c.IIconEntity;
import org.apache.wookie.w3c.ILicenseEntity;
import org.apache.wookie.w3c.INameEntity;
import org.apache.wookie.w3c.IPreferenceEntity;
import org.apache.wookie.w3c.IW3CXMLConfiguration;
import org.apache.wookie.w3c.W3CWidget;
import org.apache.wookie.w3c.exceptions.BadManifestException;
import org.apache.wookie.w3c.util.LocalizationUtils;
import org.jdom.Element;

public class W3CWidgetImpl implements W3CWidget {
	
	private String fIdentifier;
	private String fVersion;
	private Integer fHeight;
	private Integer fWidth;
	private String fViewModes;
	private String fLang;
	private String[] features;
	private List<INameEntity> fNamesList;
	private List<IDescriptionEntity> fDescriptionsList;
	private IAuthorEntity fAuthor;
	private List<ILicenseEntity> fLicensesList;
	private List<IIconEntity> fIconsList;
	private List<IAccessEntity> fAccessList;
	private List<IContentEntity> fContentList;
	private List<IFeatureEntity> fFeaturesList;
	private List<IPreferenceEntity> fPreferencesList;
	private String fUpdate;
	
	public W3CWidgetImpl(){
		fNamesList = new ArrayList<INameEntity>();
		fDescriptionsList = new ArrayList<IDescriptionEntity>();
		fLicensesList = new ArrayList<ILicenseEntity>();
		fIconsList = new ArrayList<IIconEntity>();
		fContentList = new ArrayList<IContentEntity>();
		fAccessList = new ArrayList<IAccessEntity>();
		fFeaturesList = new ArrayList<IFeatureEntity>();
		fPreferencesList = new ArrayList<IPreferenceEntity>();
	}

	@Override
	public void fromXML(Element element) throws BadManifestException {
	}

	@Override
	public List<IAccessEntity> getAccessList() {
		return fAccessList;
	}

	@Override
	public List<IContentEntity> getContentList() {
		return fContentList;
	}

	@Override
	public List<IIconEntity> getIconsList() {
		return fIconsList;
	}

	@Override
	public String getIdentifier() {
		return fIdentifier;
	}

	@Override
	public List<ILicenseEntity> getLicensesList() {
		return fLicensesList;
	}

	@Override
	public List<INameEntity> getNames() {
		return fNamesList;
	}

	@Override
	public List<IDescriptionEntity> getDescriptions() {
		return fDescriptionsList;
	}

	@Override
	public Integer getHeight() {
		return fHeight;
	}

	@Override
	public Integer getWidth() {
		return fWidth;
	}

	@Override
	public IAuthorEntity getAuthor() {
		return fAuthor;
	}

	@Override
	public List<IPreferenceEntity> getPrefences() {
		return fPreferencesList;
	}

	@Override
	public List<IFeatureEntity> getFeatures() {
		return fFeaturesList;
	}

	@Override
	public String getVersion() {
		return fVersion;
	}

	@Override
	public String getViewModes() {
		return fViewModes;
	}

	@Override
	public String getLocalName(String locale) {
		INameEntity name = (INameEntity)LocalizationUtils.getLocalizedElement(fNamesList.toArray(new INameEntity[fNamesList.size()]), new String[]{locale});
		if (name != null) return name.getName();
		return IW3CXMLConfiguration.UNKNOWN;
	}

	@Override
	public String getUpdate() {
		return fUpdate;
	}

	public void setHeight(int height){
		fHeight = height;
	}
	
	public void setWidth(int width){
		fWidth = width;
	}
	
	public void setViewmodes(String viewmodes){
		fViewModes = viewmodes;
	}
	
	public void setVersion(String version){
		fVersion = version;
	}
}
