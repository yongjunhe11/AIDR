// default package
// Generated Nov 24, 2014 4:55:08 PM by Hibernate Tools 4.0.0

/**
 * Implements the entity corresponding to the crisis_type table in the aidr_predict DB
 * 
 * @author Koushik
 */
package qa.qcri.aidr.dbmanager.entities.misc;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * CrisisType generated by hbm2java
 */
@Entity
@Table(name = "crisis_type", catalog = "aidr_predict")
public class CrisisType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8074463052776843105L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long crisisTypeId;
	
	@Column(name = "name", nullable = false, length = 140)
	private String name;
	
	public CrisisType() {
	}

	public CrisisType(String name) {
		this.name = name;
	}

	public Long getCrisisTypeId() {
		return this.crisisTypeId;
	}

	public void setCrisisTypeId(Long crisisTypeId) {
		this.crisisTypeId = crisisTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}