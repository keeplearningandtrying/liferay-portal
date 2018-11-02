/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.segments.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsEntryModel;
import com.liferay.segments.model.SegmentsEntrySoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the SegmentsEntry service. Represents a row in the &quot;SegmentsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SegmentsEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SegmentsEntryImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsEntryImpl
 * @see SegmentsEntry
 * @see SegmentsEntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SegmentsEntryModelImpl extends BaseModelImpl<SegmentsEntry>
	implements SegmentsEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a segments entry model instance should use the {@link SegmentsEntry} interface instead.
	 */
	public static final String TABLE_NAME = "SegmentsEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "segmentsEntryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "active_", Types.BOOLEAN },
			{ "criteria", Types.CLOB },
			{ "key_", Types.VARCHAR },
			{ "type_", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("segmentsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("criteria", Types.CLOB);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table SegmentsEntry (segmentsEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name STRING null,description STRING null,active_ BOOLEAN,criteria TEXT null,key_ VARCHAR(75) null,type_ VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table SegmentsEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY segmentsEntry.modifiedDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY SegmentsEntry.modifiedDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.segments.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.segments.model.SegmentsEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.segments.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.segments.model.SegmentsEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.segments.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.segments.model.SegmentsEntry"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long KEY_COLUMN_BITMASK = 4L;
	public static final long TYPE_COLUMN_BITMASK = 8L;
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SegmentsEntry toModel(SegmentsEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SegmentsEntry model = new SegmentsEntryImpl();

		model.setSegmentsEntryId(soapModel.getSegmentsEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setActive(soapModel.isActive());
		model.setCriteria(soapModel.getCriteria());
		model.setKey(soapModel.getKey());
		model.setType(soapModel.getType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SegmentsEntry> toModels(SegmentsEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SegmentsEntry> models = new ArrayList<SegmentsEntry>(soapModels.length);

		for (SegmentsEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.segments.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.segments.model.SegmentsEntry"));

	public SegmentsEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _segmentsEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSegmentsEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _segmentsEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SegmentsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return SegmentsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("segmentsEntryId", getSegmentsEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("active", isActive());
		attributes.put("criteria", getCriteria());
		attributes.put("key", getKey());
		attributes.put("type", getType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long segmentsEntryId = (Long)attributes.get("segmentsEntryId");

		if (segmentsEntryId != null) {
			setSegmentsEntryId(segmentsEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		String criteria = (String)attributes.get("criteria");

		if (criteria != null) {
			setCriteria(criteria);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@JSON
	@Override
	public long getSegmentsEntryId() {
		return _segmentsEntryId;
	}

	@Override
	public void setSegmentsEntryId(long segmentsEntryId) {
		_segmentsEntryId = segmentsEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(LocalizationUtil.updateLocalization(nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getDescription(), languageId,
			useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(String description, Locale locale,
		Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale) {
		if (descriptionMap == null) {
			return;
		}

		setDescription(LocalizationUtil.updateLocalization(descriptionMap,
				getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@JSON
	@Override
	public String getCriteria() {
		if (_criteria == null) {
			return "";
		}
		else {
			return _criteria;
		}
	}

	@Override
	public void setCriteria(String criteria) {
		_criteria = criteria;
	}

	@JSON
	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		_columnBitmask |= KEY_COLUMN_BITMASK;

		if (_originalKey == null) {
			_originalKey = _key;
		}

		_key = key;
	}

	public String getOriginalKey() {
		return GetterUtil.getString(_originalKey);
	}

	@JSON
	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SegmentsEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(SegmentsEntry.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(getDescription(defaultLocale), defaultLocale,
				defaultLocale);
		}
	}

	@Override
	public SegmentsEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SegmentsEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SegmentsEntryImpl segmentsEntryImpl = new SegmentsEntryImpl();

		segmentsEntryImpl.setSegmentsEntryId(getSegmentsEntryId());
		segmentsEntryImpl.setGroupId(getGroupId());
		segmentsEntryImpl.setCompanyId(getCompanyId());
		segmentsEntryImpl.setUserId(getUserId());
		segmentsEntryImpl.setUserName(getUserName());
		segmentsEntryImpl.setCreateDate(getCreateDate());
		segmentsEntryImpl.setModifiedDate(getModifiedDate());
		segmentsEntryImpl.setName(getName());
		segmentsEntryImpl.setDescription(getDescription());
		segmentsEntryImpl.setActive(isActive());
		segmentsEntryImpl.setCriteria(getCriteria());
		segmentsEntryImpl.setKey(getKey());
		segmentsEntryImpl.setType(getType());

		segmentsEntryImpl.resetOriginalValues();

		return segmentsEntryImpl;
	}

	@Override
	public int compareTo(SegmentsEntry segmentsEntry) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				segmentsEntry.getModifiedDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SegmentsEntry)) {
			return false;
		}

		SegmentsEntry segmentsEntry = (SegmentsEntry)obj;

		long primaryKey = segmentsEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SegmentsEntryModelImpl segmentsEntryModelImpl = this;

		segmentsEntryModelImpl._originalGroupId = segmentsEntryModelImpl._groupId;

		segmentsEntryModelImpl._setOriginalGroupId = false;

		segmentsEntryModelImpl._setModifiedDate = false;

		segmentsEntryModelImpl._originalActive = segmentsEntryModelImpl._active;

		segmentsEntryModelImpl._setOriginalActive = false;

		segmentsEntryModelImpl._originalKey = segmentsEntryModelImpl._key;

		segmentsEntryModelImpl._originalType = segmentsEntryModelImpl._type;

		segmentsEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SegmentsEntry> toCacheModel() {
		SegmentsEntryCacheModel segmentsEntryCacheModel = new SegmentsEntryCacheModel();

		segmentsEntryCacheModel.segmentsEntryId = getSegmentsEntryId();

		segmentsEntryCacheModel.groupId = getGroupId();

		segmentsEntryCacheModel.companyId = getCompanyId();

		segmentsEntryCacheModel.userId = getUserId();

		segmentsEntryCacheModel.userName = getUserName();

		String userName = segmentsEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			segmentsEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			segmentsEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			segmentsEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			segmentsEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			segmentsEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		segmentsEntryCacheModel.name = getName();

		String name = segmentsEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			segmentsEntryCacheModel.name = null;
		}

		segmentsEntryCacheModel.description = getDescription();

		String description = segmentsEntryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			segmentsEntryCacheModel.description = null;
		}

		segmentsEntryCacheModel.active = isActive();

		segmentsEntryCacheModel.criteria = getCriteria();

		String criteria = segmentsEntryCacheModel.criteria;

		if ((criteria != null) && (criteria.length() == 0)) {
			segmentsEntryCacheModel.criteria = null;
		}

		segmentsEntryCacheModel.key = getKey();

		String key = segmentsEntryCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			segmentsEntryCacheModel.key = null;
		}

		segmentsEntryCacheModel.type = getType();

		String type = segmentsEntryCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			segmentsEntryCacheModel.type = null;
		}

		return segmentsEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{segmentsEntryId=");
		sb.append(getSegmentsEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", active=");
		sb.append(isActive());
		sb.append(", criteria=");
		sb.append(getCriteria());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.segments.model.SegmentsEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>segmentsEntryId</column-name><column-value><![CDATA[");
		sb.append(getSegmentsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(isActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>criteria</column-name><column-value><![CDATA[");
		sb.append(getCriteria());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SegmentsEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SegmentsEntry.class, ModelWrapper.class
		};
	private long _segmentsEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private String _criteria;
	private String _key;
	private String _originalKey;
	private String _type;
	private String _originalType;
	private long _columnBitmask;
	private SegmentsEntry _escapedModel;
}