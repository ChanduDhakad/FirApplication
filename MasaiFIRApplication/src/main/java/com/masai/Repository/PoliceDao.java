package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Police;

@Repository
public interface PoliceDao extends JpaRepository<Police, Integer>{

 
	public Police findByMobileName(String mobileName);
	
}
