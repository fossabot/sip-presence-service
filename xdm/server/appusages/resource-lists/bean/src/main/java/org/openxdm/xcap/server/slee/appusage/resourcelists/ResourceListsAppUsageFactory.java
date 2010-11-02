package org.openxdm.xcap.server.slee.appusage.resourcelists;

import javax.xml.validation.Schema;

import org.mobicents.xdm.server.appusage.AppUsage;
import org.mobicents.xdm.server.appusage.AppUsageDataSourceInterceptor;
import org.mobicents.xdm.server.appusage.AppUsageFactory;

public class ResourceListsAppUsageFactory implements AppUsageFactory {

	private Schema schema = null;
	
	public ResourceListsAppUsageFactory(Schema schema) {
		this.schema = schema;
	}
	
	public AppUsage getAppUsageInstance() {
		return new ResourceListsAppUsage(schema.newValidator());
	}
	
	public String getAppUsageId() {
		return ResourceListsAppUsage.ID;
	}
	
	@Override
	public AppUsageDataSourceInterceptor getDataSourceInterceptor() {
		return null;
	}
}