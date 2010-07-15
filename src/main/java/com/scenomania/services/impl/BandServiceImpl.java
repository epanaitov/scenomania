package com.scenomania.services.impl;

import com.scenomania.dao.BandDao;
import com.scenomania.entities.Band;
import com.scenomania.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eugene
 */

@Service
public class BandServiceImpl implements BandService {

	@Autowired
	private BandDao bandDao;

	@Transactional
	public Band findById(Integer id) {
		return this.bandDao.findById(id);
	}

	@Transactional
	public Band save(Band band) {
		return this.bandDao.save(band);
	}
}
