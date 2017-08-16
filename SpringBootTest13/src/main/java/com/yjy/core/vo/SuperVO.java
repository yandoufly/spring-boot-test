package com.yjy.core.vo;
import java.io.Serializable;
import java.sql.Timestamp;

public class SuperVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected Timestamp creationTimestamp;
	protected Timestamp modificationTimestamp = new Timestamp(System.currentTimeMillis());
	protected int dr; //是否删除
	
	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Timestamp creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Timestamp getModificationTimestamp() {
		return modificationTimestamp;
	}

	public void setModificationTimestamp(Timestamp modificationTimestamp) {
		this.modificationTimestamp = modificationTimestamp;
	}
	
}
