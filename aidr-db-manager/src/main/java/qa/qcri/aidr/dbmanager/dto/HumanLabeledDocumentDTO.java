/*
 * This DTO is used for transferring out humanlabeled documents 
 */
package qa.qcri.aidr.dbmanager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import qa.qcri.aidr.common.exception.PropertyNotSetException;
import qa.qcri.aidr.dbmanager.dto.DocumentDTO;
import qa.qcri.aidr.dbmanager.dto.DocumentNominalLabelDTO;

@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
public class HumanLabeledDocumentDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8284975892012345464L;
	private static final Logger logger = Logger.getLogger("db-manager-log");

	@XmlElement private DocumentDTO doc;
	
	@XmlElement private List<DocumentNominalLabelDTO> labelData;
	
	public HumanLabeledDocumentDTO() {
		labelData = new ArrayList<DocumentNominalLabelDTO>();
	}
	
	public HumanLabeledDocumentDTO(DocumentDTO doc, List<DocumentNominalLabelDTO> labelData) throws Exception {
		this();
		if (doc != null) {
			this.setDoc(doc);
		} else {
			throw new PropertyNotSetException("Document can't be null in constructor");
		}
		if (labelData != null) {
			this.setLabelData(labelData);
		} 
	}
	
	public HumanLabeledDocumentDTO(DocumentDTO doc, DocumentNominalLabelDTO labelData) throws Exception {
		this();
		if (doc != null) {
			this.setDoc(doc);
		} else {
			throw new PropertyNotSetException("Document can't be null in constructor");
		}
		if (labelData != null) {
			this.labelData.add(labelData);
		} 
	}
	
	public DocumentDTO getDoc() {
		return this.doc;
	}
	
	public void setDoc(DocumentDTO doc) {
		this.doc = doc;
	}
	
	public List<DocumentNominalLabelDTO> getLabelData() {
		return this.labelData;
	}
	
	public void setLabelData(List<DocumentNominalLabelDTO> labelData) {
		this.labelData = labelData;
	}
	
	public void addSingleLabelData(DocumentNominalLabelDTO labelData) {
		if (this.labelData != null) {
			this.labelData.add(labelData);
		} else {
			this.labelData = new ArrayList<DocumentNominalLabelDTO>();
			this.labelData.add(labelData);
		}
	}
	
	public String toJsonString() {
		Gson jsonObject = new GsonBuilder().serializeNulls().disableHtmlEscaping()
				.serializeSpecialFloatingPointValues().setPrettyPrinting()
				.create();
		try {
			String jsonString = jsonObject.toJson(this, HumanLabeledDocumentDTO.class);
			return jsonString;
		} catch (Exception e) {
			logger.warn("Error in parsing json to human labeled doc.");
			return null;
		}
	}
}
