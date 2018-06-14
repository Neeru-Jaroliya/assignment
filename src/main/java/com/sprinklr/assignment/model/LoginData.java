package com.sprinklr.assignment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_data")
public class LoginData implements Serializable{
	
	private static final long serialVersionUID = 10008799L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
	
	@Column(name="user_id")
	private long userId;
	
	public LoginData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name="action_performed")
	private String actionPerformed;
	
	@Column(name="created_at")
	private Date createdAt;

	@Override
	public String toString() {
		return "LoginData [id=" + id + ", userId=" + userId + ", actionPerformed=" + actionPerformed + ", createdAt="
				+ createdAt + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getActionPerformed() {
		return actionPerformed;
	}

	public void setActionPerformed(String actionPerformed) {
		this.actionPerformed = actionPerformed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
