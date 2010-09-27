package com.scenomania.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author eugene
 */

@Entity
@Table(name="performances")
public class Performance extends EntityBase {

	@ManyToOne
	@JoinColumn(name="tour_id")
	private Tour tour;

	@ManyToOne
	@JoinColumn(name="event_id")
	private Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

}
