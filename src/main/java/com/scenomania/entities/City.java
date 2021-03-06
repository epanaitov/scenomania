package com.scenomania.entities;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="cities")
public class City extends EntityBase implements Localized {

	@Column
	private String name;

	@Column(name="area_id")
	private Integer areaId;

	@Column
	private Integer population;

	@Column(columnDefinition = "mediumtext")
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
	
	@Column
	private String slug;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="city", fetch=FetchType.LAZY)
	private Set<CityLocale> locales;

	@OneToMany(mappedBy="homecity")
	private Set<User> users;

	@OneToMany(mappedBy="homecity", fetch=FetchType.LAZY)
	private Set<Band> bands;

	@OneToMany(mappedBy="homecity", fetch=FetchType.LAZY)
	private Set<Promoter> promoters;

	@OneToMany(mappedBy="city", fetch=FetchType.LAZY)
	private Set<Stage> stages;

	@ManyToOne
	@JoinColumn(name="area_id", insertable=false, updatable=false)
	private Area area;

	public Set<Band> getBands() {
		return bands;
	}

	public Set<Promoter> getPromoters() {
		return promoters;
	}

	public void setPromoters(Set<Promoter> promoters) {
		this.promoters = promoters;
	}

	public Set<Stage> getStages() {
		return stages;
	}

	public void setStages(Set<Stage> stages) {
		this.stages = stages;
	}

	public void setBands(Set<Band> bands) {
		this.bands = bands;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

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

	public Set<CityLocale> getLocales() {
		return this.locales;
	}

	public void setLocales(Set<CityLocale> locales) {
		this.locales = locales;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	
	@Override
	public String getUrl() throws Exception {
		if (this.getArea() == null) throw new Exception("city's area is null");
		if (this.getArea().getCountry() == null) throw new Exception("city's country is null");
		
		return String.format("http://scenomania.ru/%s/%s/%s-%d/", this.getArea().getCountry().getSlug(), this.getArea().getCode(), this.getSlug(), this.getId());
	}
	
}
