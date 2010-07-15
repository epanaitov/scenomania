package com.scenomania.dao;

import com.scenomania.entities.Band;

/**
 *
 * @author eugene
 */
public interface BandDao {
	public Band findById(Integer id);
	public Band save(Band band);
}
