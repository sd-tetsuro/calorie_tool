package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface foodRepository  extends JpaRepository<food, Integer> {

	List<food> findByCategorycode(Integer categorycode);

	List<food> findByNameLike(String name);

	List<food> findByUname(String uname);
}
