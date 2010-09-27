package com.scenomania.entities;

import java.util.Date;
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
@Table(name="events")
public class Event extends EntityBase {

	@ManyToOne
	@JoinColumn(name="promoter_id")
	private Promoter promoter;

	@ManyToOne
	@JoinColumn(name="stage_id")
	private Stage stage;

	@Column
	private String name;

	@Column(columnDefinition="mediumtext")
	private String description;

	@Column
	private Date start;

	@Column
	private Date end;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Promoter getPromoter() {
		return promoter;
	}

	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

}
