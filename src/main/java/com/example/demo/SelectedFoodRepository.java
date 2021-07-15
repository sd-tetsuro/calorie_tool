package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedFoodRepository  extends JpaRepository<SelectedFood,
Integer> {
	List <SelectedFood> findAllByDishCode(Integer dishCode);

	//void deleteByDishCode(Integer dishCode);

}
