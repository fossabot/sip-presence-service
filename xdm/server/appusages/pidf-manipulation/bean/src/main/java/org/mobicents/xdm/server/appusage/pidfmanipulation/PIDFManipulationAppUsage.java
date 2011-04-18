package org.mobicents.xdm.server.appusage.pidfmanipulation;

import javax.xml.validation.Validator;

import org.mobicents.xdm.server.appusage.AppUsage;
import org.mobicents.xdm.server.appusage.AppUsageDataSource;
import org.openxdm.xcap.common.error.ConstraintFailureConflictException;
import org.openxdm.xcap.common.error.InternalServerErrorException;
import org.openxdm.xcap.common.error.NotAuthorizedRequestException;
import org.openxdm.xcap.common.error.UniquenessFailureConflictException;
import org.openxdm.xcap.common.uri.DocumentSelector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * IETF PIDF Manipulation XCAP App Usage.
 * 
 * @author martins
 * 
 */
public class PIDFManipulationAppUsage extends AppUsage {

	public static final String ID = "pidf-manipulation";
	public static final String DEFAULT_DOC_NAMESPACE = "urn:ietf:params:xml:ns:pidf";
	public static final String MIMETYPE = "application/pidf+xml";

	private static final String ENTITY_ATTR_NAME = "entity";
	
	/**
	 * 
	 * @param schemaValidator
	 */
	public PIDFManipulationAppUsage(Validator schemaValidator,
			String allowedDocumentName) {
		super(ID, DEFAULT_DOC_NAMESPACE, MIMETYPE, schemaValidator,
				allowedDocumentName);
	}

	@Override
	public void checkConstraintsOnPut(Document document, String xcapRoot,
			DocumentSelector documentSelector, AppUsageDataSource dataSource)
			throws UniquenessFailureConflictException,
			InternalServerErrorException, ConstraintFailureConflictException,
			NotAuthorizedRequestException {

		// get xui from document selector by cutting first 6 chars -> users/
		String xui = documentSelector.getDocumentParent().substring(6);

		/*
		 * When handling (i.e. create, modify, delete, etc.) a Permanent
		 * Presence State document, the Presence XDMS SHALL also perform
		 * authorization of the request by verifying that the XUI matches the
		 * value of the “entity” attribute of the <presence> element in the
		 * Presence Information document as described in [RFC3863]. In case of
		 * no match, the Presence XDMS SHALL reject the request by responding to
		 * the request with an HTTP 403 (Forbidden) error response.
		 */
		Element presenceElement = document.getDocumentElement();
		String entityAttribute = presenceElement.getAttributeNode(
				ENTITY_ATTR_NAME).getNodeValue();
		if (!xui.equals(entityAttribute)) {
			throw new NotAuthorizedRequestException();
		}
	}
	
}