package com.minyazi.j2ee.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 处理信息
 * 
 * @author minyazi
 */
@Entity
@Table(name="ProcessInfo")
public class ProcessInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 标识属性
     */
    private Integer id;
    /**
     * 处理码
     */
    private String processCode;
    /**
     * 处理信息
     */
    private String processMesg;
    
    public ProcessInfoDO() {}
    
    public ProcessInfoDO(String processCode) {
        this.processCode = processCode;
    }
    
    @Id
    @GeneratedValue
    @Column(name="id")
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="processCode")
    public String getProcessCode() {
        return this.processCode;
    }
    
    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }
    
    @Column(name="processMesg")
    public String getProcessMesg() {
        return this.processMesg;
    }
    
    public void setProcessMesg(String processMesg) {
        this.processMesg = processMesg;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof ProcessInfoDO)) {
            return false;
        }
        
        ProcessInfoDO other = (ProcessInfoDO) obj;
        return other.getProcessCode() == null ? false : other.getProcessCode().equals(processCode);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = hashCode * 31 + (processCode == null ? 0 : processCode.hashCode());
        return hashCode;
    }
    
    @Override
    public String toString() {
        return processMesg == null ? "" : processMesg;
    }
}
