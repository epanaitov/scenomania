package com.scenomania.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="cities")
public class City extends EntityBase {

	@Column
	private String name;

	@Column(name="area_id")
	private Integer areaId;

	@Column
	private Integer population;

	@Column(columnDefinition = "text")
	private String description;

	@Column
	private Integer reputation;

	@Column
	private Double latitude;

	@Column
	private Double longitude;

	@Column(name="country_code", columnDefinition = "char")
	private String countryCode;

	@Column(name="area_code", columnDefinition = "char")
	private String areaCode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="city", fetch=FetchType.EAGER)
	private List<CityLocale> locales;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public List<CityLocale> getLocales() {
		return this.locales;
	}

	public void setLocales(List<CityLocale> locales) {
		this.locales = locales;
	}
}
