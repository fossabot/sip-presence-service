/**
 * 
 */
package org.mobicents.slee.xdm.server;

/**
 * @author martins
 *
 */
public interface ServerConfigurationMBean {

	public String getServerHost();

	public void setServerHost(String serverHost);

	public int getServerPort();

	public void setServerPort(int serverPort);

	public String getSchemeAndAuthority();

	public void setSchemeAndAuthority(String schemeAndAuthority);

	public String getXcapRoot();

	public void setXcapRoot(String xcapRoot);
	
	public String getAuthenticationRealm();

	public void setAuthenticationRealm(String realm);
	
}
