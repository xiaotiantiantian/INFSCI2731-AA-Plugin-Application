/*
 * a model of timestamp
 */
package model;

import java.util.Date;

/**
 *
 * @author Siwei Jiao
 */
public class TimeStamp {
    private long timeStampsID;
    private Date createTime;
    private Date updateTime;

    /**
     * @return the timeStampsID
     */
    public long getTimeStampsID() {
        return timeStampsID;
    }

    /**
     * @param timeStampsID the timeStampsID to set
     */
    public void setTimeStampsID(long timeStampsID) {
        this.timeStampsID = timeStampsID;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
