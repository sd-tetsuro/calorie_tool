package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface menuRepository  extends JpaRepository<menu,
Integer> {
	List<menu> findAllByOrderByDishcodeDesc();

	List<menu> findByDishnameAndKcalall(String dishname, Integer kcalall);

	List<menu> findByUserid(Integer code);

}
