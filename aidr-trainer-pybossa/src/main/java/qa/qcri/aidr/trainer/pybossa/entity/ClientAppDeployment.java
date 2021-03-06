package qa.qcri.aidr.trainer.pybossa.entity;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jlucas
 * Date: 6/11/14
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "clientAppDeployment")
public class ClientAppDeployment implements Serializable {
    private static final long serialVersionUID = -5527566248002296042L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deploymentID")
    private Long deploymentID;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "status", nullable = false)
    private int status;


    @Column (name = "clientAppID", nullable = false)
    private Long clientAppID;

    public Long getDeploymentID() {
        return deploymentID;
    }

    public void setDeploymentID(Long deploymentID) {
        this.deploymentID = deploymentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getClientAppID() {
        return clientAppID;
    }

    public void setClientAppID(Long clientAppID) {
        this.clientAppID = clientAppID;
    }
}
