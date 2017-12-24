package br.quixada.ufc.web.ninjatypr.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.web.ninjatypr.model.SpeedTest;

@Repository
@Transactional
public interface SpeedTestDAO extends CrudRepository<SpeedTest, Long> {

	List<SpeedTest> findByUserId(Long id);

}
