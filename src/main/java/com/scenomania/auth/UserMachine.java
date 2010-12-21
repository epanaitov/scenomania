package com.scenomania.auth;

import com.scenomania.entities.EntityBase;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */
@Entity
@Table(name="user_machines")
public class UserMachine extends EntityBase {
	
	@Column(name="user_id")
	private Integer userId;

	@Column(name="machine_hash")
	private String machineHash;

	public String getMachineHash() {
		return machineHash;
	}

	public void setMachineHash(String machineHash) {
		this.machineHash = machineHash;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
