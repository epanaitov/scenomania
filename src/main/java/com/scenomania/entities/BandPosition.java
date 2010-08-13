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
@Table(name="bands_users")
public class BandPosition extends EntityBase {

	@Column
	private String position;

	@ManyToOne
	@JoinColumn(name="band_id")
	private Band band;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
