package com.minyazi.dao.domain;

import java.io.Serializable;

/**
 * 处理信息
 * 
 * @author minyazi
 */
public class ProcessInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    public String getProcessCode() {
        return this.processCode;
    }
    
    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }
    
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
        return other.getProcessCode().equals(this.getProcessCode());
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + (this.getProcessCode() == null ? 0 : this.getProcessCode().hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return this.getProcessMesg() == null ? "" : this.getProcessMesg();
    }
}
