// default package
// Generated Nov 24, 2014 4:55:08 PM by Hibernate Tools 4.0.0
package qa.qcri.aidr.dbmanager.entities.task;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TaskAssignment generated by hbm2java
 */
@Entity
@Table(name = "task_assignment", catalog = "aidr_predict")
public class TaskAssignment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3761125422251764759L;
	private TaskAssignmentId id;
	private Document document;
	private Date assignedAt;
    private Long userId;
    private Long documentId;

	public TaskAssignment() {
	}

    public TaskAssignment( Document document, Long userId, Date assignedAt) {
        this.document = document;
        this.userId = userId;
        this.assignedAt = assignedAt;
    }

    public TaskAssignment( Long documentId, Long userId) {
        this.documentId = documentId;
        this.userId = userId;
        this.assignedAt = new Date();
    }

    public TaskAssignment(TaskAssignmentId id, Document document,
			Date assignedAt) {
		this.id = id;
		this.document = document;
		this.assignedAt = assignedAt;
	}

    @Column(name = "userID", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "documentID", nullable = false)
    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "documentId", column = @Column(name = "documentID", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userID", nullable = false)) })
	public TaskAssignmentId getId() {
		return this.id;
	}

	public void setId(TaskAssignmentId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "documentID", nullable = false, insertable = false, updatable = false)
	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "assignedAt", nullable = false, length = 19)
	public Date getAssignedAt() {
		return this.assignedAt;
	}

	public void setAssignedAt(Date assignedAt) {
		this.assignedAt = assignedAt;
	}

}
