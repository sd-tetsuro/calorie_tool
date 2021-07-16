package com.example.demo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KcalRepository  extends JpaRepository<Kcal,
Integer> {
	List<Kcal> findByKcalall(Integer kcalall);


	List<Kcal> findByDateAndUsercode(Date date, Integer usercord);
}
