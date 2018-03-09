package br.quixada.ufc.web.ninjatypr.model.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.quixada.ufc.web.ninjatypr.model.User;

@Repository
@Transactional
public interface UserDAO extends CrudRepository<User, Long> {
	public User findById(Long id);
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	public ArrayList<User> findAll();
	public User findByEmail(String email);
}
