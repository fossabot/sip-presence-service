package org.mobicents.slee.xdm.server.datasource.jpa;

import java.io.Serializable;
import java.io.StringReader;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.openxdm.xcap.common.error.InternalServerErrorException;
import org.openxdm.xcap.common.error.NotWellFormedConflictException;
import org.openxdm.xcap.common.xml.XMLValidator;


/**
 *     
 * @author eduardomartins
 *
 */
@Entity
@Table(name = "XDM_DATASOURCE_DOCUMENTS")
@NamedQueries({
	@NamedQuery(name=Document.JPA_QUERY_SELECT_DOCUMENTS_FROM_DOCUMENT_PARENT,query="SELECT x FROM Document x WHERE x.key.appUsage = :auid AND x.key.documentParent = :documentParent"),
	@NamedQuery(name=Document.JPA_QUERY_SELECT_DOCUMENTS_FROM_APPUSAGE,query="SELECT x FROM Document x WHERE x.key.appUsage = :auid"),
	@NamedQuery(name=Document.JPA_QUERY_UPDATE_DOCUMENTS_FROM_KEY,query="UPDATE Document x SET x.xml=:xml,x.eTag=:eTag WHERE x.key.appUsage = :auid AND x.key.documentParent = :documentParent AND x.key.documentName = :documentName"),
	@NamedQuery(name=Document.JPA_QUERY_DELETE_DOCUMENTS_FROM_KEY,query="DELETE FROM Document x WHERE x.key.appUsage = :auid AND x.key.documentParent = :documentParent AND x.key.documentName = :documentName")
	})
public class Document implements org.openxdm.xcap.common.datasource.Document, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3697052553779974529L;

	public static final String JPA_QUERY_SELECT_DOCUMENTS_FROM_DOCUMENT_PARENT = "selectDocumentsFromDocumentParent";
	public static final String JPA_QUERY_SELECT_DOCUMENTS_FROM_APPUSAGE = "selectDocumentsFromAppUsage";
	public static final String JPA_QUERY_UPDATE_DOCUMENTS_FROM_KEY = "updateDocumentFromKey";
	public static final String JPA_QUERY_DELETE_DOCUMENTS_FROM_KEY = "deleteDocumentFromKey";
	
	private transient org.w3c.dom.Document domDocument = null;
	
	@EmbeddedId
	protected DocumentPrimaryKey key;
	
	/**
	 * the document XML
	 */
	@Column(name = "XML", nullable = false)
	private String xml;
	
	/**
	 * the document entity tag, a.k.a. the version
	 */
	@Column(name = "ETAG", nullable =false)
	private String eTag;
	
	public Document() {
		// TODO Auto-generated constructor stub
	}
	
	public Document(String auid, String documentParent, String documentName) {
		setKey(new DocumentPrimaryKey(auid,documentParent,documentName));
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj.getClass() == this.getClass()) {
			Document other = (Document) obj;
			return other.key.equals(this.key);
		}
		else {
			return false;
		}
	}

	// -- GETTERS AND SETTERS
	
	public DocumentPrimaryKey getKey() {
		return key;
	}

	public void setKey(DocumentPrimaryKey key) {
		this.key = key;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getETag() {
		return eTag;
	}

	public void setETag(String tag) {
		eTag = tag;
	}
	
	//
	
	public String getAUID() {
		return key.getAppUsage();
	}
	
	public String getDocumentParent() {
		return key.getDocumentParent();
	}
	
	public String getDocumentName() {
		return key.getDocumentName();
	}
	
    public String getAsString() throws InternalServerErrorException {
        return getXml();
    }

    public org.w3c.dom.Document getAsDOMDocument() throws InternalServerErrorException {
        if (domDocument == null) {
        	try {
        		domDocument = XMLValidator.getWellFormedDocument(new StringReader(xml));
            } catch (NotWellFormedConflictException e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        }
    	return domDocument;
    }
}