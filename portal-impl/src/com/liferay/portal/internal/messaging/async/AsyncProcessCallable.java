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

package com.liferay.portal.internal.messaging.async;

import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiServiceInvokerUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.spring.aop.ServiceBeanMethodInvocation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class AsyncProcessCallable
	implements Externalizable, ProcessCallable<Serializable> {

	public AsyncProcessCallable() {
		this(null);
	}

	public AsyncProcessCallable(
		ServiceBeanMethodInvocation serviceBeanMethodInvocation) {

		_serviceBeanMethodInvocation = serviceBeanMethodInvocation;
	}

	@Override
	public Serializable call() {
		try {
			if (_serviceBeanMethodInvocation != null) {
				_serviceBeanMethodInvocation.proceed();
			}
			else {
				AsyncInvokeThreadLocal.setEnabled(true);

				try {
					_methodHandler.invoke(null);
				}
				finally {
					AsyncInvokeThreadLocal.setEnabled(false);
				}
			}
		}
		catch (Throwable t) {
			throw new RuntimeException(t);
		}

		return null;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		_methodHandler = (MethodHandler)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		MethodHandler methodHandler = _methodHandler;

		if (methodHandler == null) {
			methodHandler =
				IdentifiableOSGiServiceInvokerUtil.createMethodHandler(
					_serviceBeanMethodInvocation.getThis(),
					_serviceBeanMethodInvocation.getMethod(),
					_serviceBeanMethodInvocation.getArguments());
		}

		objectOutput.writeObject(methodHandler);
	}

	private MethodHandler _methodHandler;
	private final ServiceBeanMethodInvocation _serviceBeanMethodInvocation;

}