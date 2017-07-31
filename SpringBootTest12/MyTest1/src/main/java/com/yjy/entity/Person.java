package com.yjy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_person")
public class Person {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	private String id; // 唯一标识

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime; // 创建时间 格式：yyyy-MM-dd HH:mm:ss

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_TIME")
	private Date lastUpdateTime; // 最后更新时间

	@Column(name = "VERSION_NUMBER", length = 32)
	private String versionNumber; // 乐观锁版本号

	@Column(name = "PERSON_NAME", length = 32)
	private String personName; // 姓名

	@Column(name = "GENDER", length = 4)
	private String gender; // 性别编码

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "BIRTH_TIME", length = 32)
	private Date birthTime; // 出生时间

	@Column(name = "IDENTITY_NUMBER", length = 32)
	private String identityNumber; // 身份证号码

	@Column(name = "MARITAL_STATUS", length = 4)
	private String maritalStatus; // 婚姻状况编码

	@Column(name = "SPOUSE_NAME", length = 10)
	private String spouseName; // 配偶姓名

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", versionNumber=" + versionNumber + ", personName=" + personName + ", gender=" + gender
				+ ", birthTime=" + birthTime + ", identityNumber=" + identityNumber + ", maritalStatus=" + maritalStatus
				+ ", spouseName=" + spouseName + "]";
	}
}
