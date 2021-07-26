package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository  extends JpaRepository<user, Integer> {

	List<user> findByEmailAndPassword(String email,String password);

}
