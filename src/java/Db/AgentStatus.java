package Db;
// Generated Mar 15, 2017 2:10:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * AgentStatus generated by hbm2java
 */
public class AgentStatus  implements java.io.Serializable {


     private Integer agentStatusId;
     private String agentStatus;
     private Set insuranceAgents = new HashSet(0);

    public AgentStatus() {
    }

    public AgentStatus(String agentStatus, Set insuranceAgents) {
       this.agentStatus = agentStatus;
       this.insuranceAgents = insuranceAgents;
    }
   
    public Integer getAgentStatusId() {
        return this.agentStatusId;
    }
    
    public void setAgentStatusId(Integer agentStatusId) {
        this.agentStatusId = agentStatusId;
    }
    public String getAgentStatus() {
        return this.agentStatus;
    }
    
    public void setAgentStatus(String agentStatus) {
        this.agentStatus = agentStatus;
    }
    public Set getInsuranceAgents() {
        return this.insuranceAgents;
    }
    
    public void setInsuranceAgents(Set insuranceAgents) {
        this.insuranceAgents = insuranceAgents;
    }




}

