package org.mobicents.slee.sipevent.server.publication.jmx;

/**
 * JMX Configuration of the SIP Event Subscription Control.
 * 
 * @author martins
 *
 */
public interface PublicationControlManagementMBean {

	
	public static final String MBEAN_NAME="org.mobicents.slee:sippresence=SipEventPublicationControl";
	
	/**
	 * Retrieves default subscription time in seconds.
	 * @return
	 */
	public int getDefaultExpires();

	/**
	 * Defines default subscription time in seconds.
	 * @param defaultExpires
	 */
	public void setDefaultExpires(int defaultExpires);
	
	/**
	 * Retrieves maximum subscription time in seconds.
	 * @return
	 */
	public int getMaxExpires();
	
	/**
	 * Defines maximum subscription time in seconds.
	 * @param maxExpires
	 */
	public void setMaxExpires(int maxExpires);
	
	/**
	 * Retrieves minimum subscription time in seconds.
	 * @return
	 */
	public int getMinExpires();
	
	/**
	 * Defines minimum subscription time in seconds.
	 * @param maxExpires
	 */
	public void setMinExpires(int minExpires);
	
	/**
	 * Retrieves the display name used in contact header's addresses.
	 * @return
	 */
	public String getContactAddressDisplayName();
	
	/**
	 * Defines the display name used in contact header's addresses.
	 * @param contactAddressDisplayName
	 */
	public void setContactAddressDisplayName(String contactAddressDisplayName);
	
	/**
	 * Retrieves the TerminationIOI parameter of PChargingVector header, to be used on PUBLISH responses in a IMS environment.
	 *   
	 * @return
	 */
	public String getPChargingVectorHeaderTerminatingIOI();
	
	/**
	 * Defines the TerminationIOI parameter of PChargingVector header, to be used on PUBLISH responses in a IMS environment.
	 * @param chargingVectorHeaderTerminatingIOI
	 */
	public void setPChargingVectorHeaderTerminatingIOI(
			String chargingVectorHeaderTerminatingIOI);
	
	/**
	 * Indicates if an alternative value will be used for expired/removed publications.
	 * @return
	 */
	public boolean isUseAlternativeValueForExpiredPublication();
	
	/**
	 * Defines if an alternative value will be used for expired/removed publications.
	 * @param value
	 */
	public void setUseAlternativeValueForExpiredPublication(
			boolean value);
	
}
