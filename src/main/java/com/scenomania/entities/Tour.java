package com.scenomania.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="tours")
public class Tour extends EntityBase {

	@Column
	private String name;

	@Column(columnDefinition="mediumtext")
	private String description;

	public static final int STATUS_PLANNING = -1;
	public static final int STATUS_ONTOUR = 0;
	public static final int STATUS_COMPLETED = 1;


	@Column(columnDefinition="tinyint default "+STATUS_PLANNING)
	private Integer status;

	@ManyToOne
	@JoinColumn(name="band_id")
	private Band band;

	@OneToMany(mappedBy="performance", fetch=FetchType.LAZY)
	private Set<Performance> performances;

	public Set<Performance> getPerformances() {
		return performances;
	}

	public void setPerformances(Set<Performance> performances) {
		this.performances = performances;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
