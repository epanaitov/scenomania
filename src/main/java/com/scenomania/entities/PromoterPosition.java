package com.scenomania.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="promoters_users")
public class PromoterPosition extends EntityBase {

	@Column
	private String position;

	@ManyToOne
	@JoinColumn(name="promoter_id")
	private Promoter promoter;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Promoter getPromoter() {
		return promoter;
	}

	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
