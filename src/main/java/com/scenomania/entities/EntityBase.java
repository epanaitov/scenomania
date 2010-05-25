package com.scenomania.entities;

import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBase {

	private Integer id;
	private Date created_at;

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}

	@Column
	public Date getCreated_at() {
		return created_at;
	}

}
