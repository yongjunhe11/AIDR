/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qa.qcri.aidr.predictui.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


//import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author Imran
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role")})
@JsonIgnoreProperties(ignoreUnknown=true)
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userID")
    @XmlElement private Integer userID;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    @XmlElement private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "role")
    @XmlElement private String role;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonManagedReference
    private Collection<Crisis> crisisCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonManagedReference
    private Collection<NominalAttribute> nominalAttributeCollection;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Users(Integer userID, String name, String role) {
        this.userID = userID;
        this.name = name;
        this.role = role;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Crisis> getCrisisCollection() {
        return crisisCollection;
    }

    public void setCrisisCollection(Collection<Crisis> crisisCollection) {
        this.crisisCollection = crisisCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<NominalAttribute> getNominalAttributeCollection() {
        return nominalAttributeCollection;
    }

    public void setNominalAttributeCollection(Collection<NominalAttribute> nominalAttributeCollection) {
        this.nominalAttributeCollection = nominalAttributeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qa.qcri.aidr.predictui.entities.Users[ userID=" + userID + " ]";
    }
    
	public static Users toLocalUsers(qa.qcri.aidr.task.dto.UsersDTO userDTO) {
		Users user = new Users(userDTO.getUserID().intValue(), userDTO.getName(), userDTO.getRole());
		return user;
	}
	
	public static qa.qcri.aidr.task.dto.UsersDTO toTaskManagerUsersDTO(Users user) {
		Long userID = user.getUserID().longValue();
		qa.qcri.aidr.task.dto.UsersDTO userDTO = new qa.qcri.aidr.task.dto.UsersDTO(userID, user.getName(), user.getRole());
		return userDTO;
	}
	
	public static qa.qcri.aidr.task.entities.Users toTaskManagerUsers(Users user) {
		Long userID = user.getUserID().longValue();
		qa.qcri.aidr.task.entities.Users u = new qa.qcri.aidr.task.entities.Users(userID, user.getName(), user.getRole());
		return u;
	}
	
	public static List<Users> toLocalUsersList(List<qa.qcri.aidr.task.dto.UsersDTO> list) {
		if (list != null) {
			List<Users> usersList = new ArrayList<Users>();
			for (qa.qcri.aidr.task.dto.UsersDTO u: list) {
				usersList.add(toLocalUsers(u));
			}
			return usersList;
		}
		return null;
	}
	
	public static List<qa.qcri.aidr.task.dto.UsersDTO> toTaskManagerUsersDTOList(List<Users> list) {
		if (list != null) {
			List<qa.qcri.aidr.task.dto.UsersDTO> usersDTOList = new ArrayList<qa.qcri.aidr.task.dto.UsersDTO>();
			for (Users u: list) {
				usersDTOList.add(toTaskManagerUsersDTO(u));
			}
			return usersDTOList;
		}
		return null;
	}
	
	public static List<qa.qcri.aidr.task.entities.Users> toTaskManagerUsersList(List<Users> list) {
		if (list != null) {
			List<qa.qcri.aidr.task.entities.Users> usersList = new ArrayList<qa.qcri.aidr.task.entities.Users>();
			for (Users u: list) {
				usersList.add(toTaskManagerUsers(u));
			}
			return usersList;
		}
		return null;
	}
}
