package com.scenomania.services;

import com.scenomania.entities.Band;

/**
 *
 * @author eugene
 */
public interface BandService {
	public Band findById(Integer id);
	public Band save(Band band);
}
