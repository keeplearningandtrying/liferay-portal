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

package com.liferay.asset.auto.tagger.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry;
import com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntryModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the AssetAutoTaggerEntry service. Represents a row in the &quot;AssetAutoTaggerEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AssetAutoTaggerEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetAutoTaggerEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAutoTaggerEntryImpl
 * @see AssetAutoTaggerEntry
 * @see AssetAutoTaggerEntryModel
 * @generated
 */
@ProviderType
public class AssetAutoTaggerEntryModelImpl extends BaseModelImpl<AssetAutoTaggerEntry>
	implements AssetAutoTaggerEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset auto tagger entry model instance should use the {@link AssetAutoTaggerEntry} interface instead.
	 */
	public static final String TABLE_NAME = "AssetAutoTaggerEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "assetAutoTaggerEntryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "assetEntryId", Types.BIGINT },
			{ "assetTagId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("assetAutoTaggerEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("assetEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("assetTagId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table AssetAutoTaggerEntry (assetAutoTaggerEntryId LONG not null primary key,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,assetEntryId LONG,assetTagId LONG)";
	public static final String TABLE_SQL_DROP = "drop table AssetAutoTaggerEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY assetAutoTaggerEntry.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY AssetAutoTaggerEntry.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.asset.auto.tagger.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.asset.auto.tagger.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.asset.auto.tagger.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry"),
			true);
	public static final long ASSETENTRYID_COLUMN_BITMASK = 1L;
	public static final long ASSETTAGID_COLUMN_BITMASK = 2L;
	public static final long CREATEDATE_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.asset.auto.tagger.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry"));

	public AssetAutoTaggerEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetAutoTaggerEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetAutoTaggerEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetAutoTaggerEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetAutoTaggerEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AssetAutoTaggerEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetAutoTaggerEntryId", getAssetAutoTaggerEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("assetTagId", getAssetTagId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetAutoTaggerEntryId = (Long)attributes.get(
				"assetAutoTaggerEntryId");

		if (assetAutoTaggerEntryId != null) {
			setAssetAutoTaggerEntryId(assetAutoTaggerEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long assetTagId = (Long)attributes.get("assetTagId");

		if (assetTagId != null) {
			setAssetTagId(assetTagId);
		}
	}

	@Override
	public long getAssetAutoTaggerEntryId() {
		return _assetAutoTaggerEntryId;
	}

	@Override
	public void setAssetAutoTaggerEntryId(long assetAutoTaggerEntryId) {
		_assetAutoTaggerEntryId = assetAutoTaggerEntryId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

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

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getAssetEntryId() {
		return _assetEntryId;
	}

	@Override
	public void setAssetEntryId(long assetEntryId) {
		_columnBitmask |= ASSETENTRYID_COLUMN_BITMASK;

		if (!_setOriginalAssetEntryId) {
			_setOriginalAssetEntryId = true;

			_originalAssetEntryId = _assetEntryId;
		}

		_assetEntryId = assetEntryId;
	}

	public long getOriginalAssetEntryId() {
		return _originalAssetEntryId;
	}

	@Override
	public long getAssetTagId() {
		return _assetTagId;
	}

	@Override
	public void setAssetTagId(long assetTagId) {
		_columnBitmask |= ASSETTAGID_COLUMN_BITMASK;

		if (!_setOriginalAssetTagId) {
			_setOriginalAssetTagId = true;

			_originalAssetTagId = _assetTagId;
		}

		_assetTagId = assetTagId;
	}

	public long getOriginalAssetTagId() {
		return _originalAssetTagId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			AssetAutoTaggerEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AssetAutoTaggerEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AssetAutoTaggerEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AssetAutoTaggerEntryImpl assetAutoTaggerEntryImpl = new AssetAutoTaggerEntryImpl();

		assetAutoTaggerEntryImpl.setAssetAutoTaggerEntryId(getAssetAutoTaggerEntryId());
		assetAutoTaggerEntryImpl.setGroupId(getGroupId());
		assetAutoTaggerEntryImpl.setCompanyId(getCompanyId());
		assetAutoTaggerEntryImpl.setCreateDate(getCreateDate());
		assetAutoTaggerEntryImpl.setModifiedDate(getModifiedDate());
		assetAutoTaggerEntryImpl.setAssetEntryId(getAssetEntryId());
		assetAutoTaggerEntryImpl.setAssetTagId(getAssetTagId());

		assetAutoTaggerEntryImpl.resetOriginalValues();

		return assetAutoTaggerEntryImpl;
	}

	@Override
	public int compareTo(AssetAutoTaggerEntry assetAutoTaggerEntry) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				assetAutoTaggerEntry.getCreateDate());

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

		if (!(obj instanceof AssetAutoTaggerEntry)) {
			return false;
		}

		AssetAutoTaggerEntry assetAutoTaggerEntry = (AssetAutoTaggerEntry)obj;

		long primaryKey = assetAutoTaggerEntry.getPrimaryKey();

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
		AssetAutoTaggerEntryModelImpl assetAutoTaggerEntryModelImpl = this;

		assetAutoTaggerEntryModelImpl._setModifiedDate = false;

		assetAutoTaggerEntryModelImpl._originalAssetEntryId = assetAutoTaggerEntryModelImpl._assetEntryId;

		assetAutoTaggerEntryModelImpl._setOriginalAssetEntryId = false;

		assetAutoTaggerEntryModelImpl._originalAssetTagId = assetAutoTaggerEntryModelImpl._assetTagId;

		assetAutoTaggerEntryModelImpl._setOriginalAssetTagId = false;

		assetAutoTaggerEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AssetAutoTaggerEntry> toCacheModel() {
		AssetAutoTaggerEntryCacheModel assetAutoTaggerEntryCacheModel = new AssetAutoTaggerEntryCacheModel();

		assetAutoTaggerEntryCacheModel.assetAutoTaggerEntryId = getAssetAutoTaggerEntryId();

		assetAutoTaggerEntryCacheModel.groupId = getGroupId();

		assetAutoTaggerEntryCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetAutoTaggerEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			assetAutoTaggerEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetAutoTaggerEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			assetAutoTaggerEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetAutoTaggerEntryCacheModel.assetEntryId = getAssetEntryId();

		assetAutoTaggerEntryCacheModel.assetTagId = getAssetTagId();

		return assetAutoTaggerEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{assetAutoTaggerEntryId=");
		sb.append(getAssetAutoTaggerEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", assetEntryId=");
		sb.append(getAssetEntryId());
		sb.append(", assetTagId=");
		sb.append(getAssetTagId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>assetAutoTaggerEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetAutoTaggerEntryId());
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
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetEntryId</column-name><column-value><![CDATA[");
		sb.append(getAssetEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assetTagId</column-name><column-value><![CDATA[");
		sb.append(getAssetTagId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = AssetAutoTaggerEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			AssetAutoTaggerEntry.class, ModelWrapper.class
		};
	private long _assetAutoTaggerEntryId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _assetEntryId;
	private long _originalAssetEntryId;
	private boolean _setOriginalAssetEntryId;
	private long _assetTagId;
	private long _originalAssetTagId;
	private boolean _setOriginalAssetTagId;
	private long _columnBitmask;
	private AssetAutoTaggerEntry _escapedModel;
}