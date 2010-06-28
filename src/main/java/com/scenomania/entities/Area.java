package com.scenomania.entities;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="areas")
public class Area extends EntityBase {

	@Column
	private String name;

	@Column(name="country_id")
	private Integer countryId;

	@Column(name="country_code", columnDefinition = "char")
	private String countryCode;

	@Column(columnDefinition = "char")
	private String code;

	@OneToMany(mappedBy="area", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
	@Cascade(org.hibernate.annotations.CascadeType.REPLICATE)
	private Set<AreaLocale> locales;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AreaLocale> getLocales() {
		return locales;
	}

	public void setLocales(Set<AreaLocale> locales) {
		this.locales = locales;
	}
	
	
}
