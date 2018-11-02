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

package com.liferay.portal.tools.service.builder.test.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.tools.service.builder.test.model.LVEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LVEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LVEntry
 * @generated
 */
@ProviderType
public class LVEntryCacheModel implements CacheModel<LVEntry>, Externalizable,
	MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LVEntryCacheModel)) {
			return false;
		}

		LVEntryCacheModel lvEntryCacheModel = (LVEntryCacheModel)obj;

		if ((lvEntryId == lvEntryCacheModel.lvEntryId) &&
				(mvccVersion == lvEntryCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, lvEntryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", headId=");
		sb.append(headId);
		sb.append(", defaultLanguageId=");
		sb.append(defaultLanguageId);
		sb.append(", lvEntryId=");
		sb.append(lvEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LVEntry toEntityModel() {
		LVEntryImpl lvEntryImpl = new LVEntryImpl();

		lvEntryImpl.setMvccVersion(mvccVersion);
		lvEntryImpl.setHeadId(headId);

		if (defaultLanguageId == null) {
			lvEntryImpl.setDefaultLanguageId("");
		}
		else {
			lvEntryImpl.setDefaultLanguageId(defaultLanguageId);
		}

		lvEntryImpl.setLvEntryId(lvEntryId);
		lvEntryImpl.setGroupId(groupId);

		lvEntryImpl.resetOriginalValues();

		return lvEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		headId = objectInput.readLong();
		defaultLanguageId = objectInput.readUTF();

		lvEntryId = objectInput.readLong();

		groupId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(headId);

		if (defaultLanguageId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(defaultLanguageId);
		}

		objectOutput.writeLong(lvEntryId);

		objectOutput.writeLong(groupId);
	}

	public long mvccVersion;
	public long headId;
	public String defaultLanguageId;
	public long lvEntryId;
	public long groupId;
}