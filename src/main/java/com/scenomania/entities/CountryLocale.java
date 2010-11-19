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
@Table(name="country_locale")
public class CountryLocale extends EntityBase implements EntityLocale {

	@Column
	private String name;

	@Column(columnDefinition = "char")
	private String locale;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id")
	private Country country;

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	
}
