/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.slee.sipevent.server.rlscache;

import javax.slee.resource.ActivityHandle;

import org.openxdm.xcap.common.uri.DocumentSelector;

public class ResourceListActivityHandle implements ActivityHandle {

	public final static Class<?> TYPE = ResourceListActivityHandle.class;
	
	private final DocumentSelector documentSelector;
		
	public ResourceListActivityHandle(DocumentSelector documentSelector) {
		if (documentSelector == null) {
			throw new NullPointerException("null document selector");
		}
		this.documentSelector = documentSelector;
	}
	
	public DocumentSelector getDocumentSelector() {
		return documentSelector;
	}

	@Override
	public int hashCode() {
		return documentSelector.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceListActivityHandle other = (ResourceListActivityHandle) obj;
		return this.documentSelector.equals(other.documentSelector);
	}
		
	@Override
	public String toString() {
		return new StringBuilder("ResourceListActivityHandle[ds=").append(documentSelector).append("]").toString();
	}
}
