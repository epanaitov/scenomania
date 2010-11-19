package com.scenomania.entities;

import javax.persistence.CascadeType;
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
@Table(name="city_locale")
public class CityLocale extends EntityBase implements EntityLocale {
	
	@Column
	private String name;

	@Column(columnDefinition = "mediumtext")
	private String description;

	@Column(columnDefinition = "char")
	private String locale;

	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn(name = "city_id")
	private City city;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	
}
