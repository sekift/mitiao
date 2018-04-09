package com.mitiao.www.vo;

import java.util.Date;

/**
 * 
 * @author:sekift
 * @time:2016-7-26 下午04:06:52
 * @version:
 */
public class ContentVO {
	private int id; // `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
	private String hashNoteId; // `hashNoteId` varchar(64) NOT NULL COMMENT 'hashnoteid',
	private String content; // `content` varchar(20000) NOT NULL COMMENT '内容',
	private Date createTime; // `createTime` datetime NOT NULL COMMENT '创建时间',
	private Date updateTime; // `updateTime` datetime NOT NULL COMMENT '使用时间',
	private Date availableTime; //`availableTime` datetime NOT NULL COMMENT '有效时间，默认为1个月',
	private int status;   // `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0 有效；-1 已看并销毁；-2 自己销毁',
	private String memo; // `memo` varchar(256) DEFAULT NULL COMMENT '说明',

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHashNoteId() {
		return hashNoteId;
	}

	public void setHashNoteId(String hashNoteId) {
		this.hashNoteId = hashNoteId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(Date availableTime) {
		this.availableTime = availableTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "ContentVO [id=" + id + ",hashNoteId=" + hashNoteId + ",content=" + content + ",createTime="
				+ createTime + ",updateTime=" + updateTime 
				+ ",availableTime" + availableTime + ",status=" + status
				+ ",memo=" + memo + "]";
	}
}
