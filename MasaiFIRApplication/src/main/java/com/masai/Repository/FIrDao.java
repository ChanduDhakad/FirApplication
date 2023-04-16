package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.Model.Fir;
@Repository
public interface FIrDao extends JpaRepository<Fir, Integer> {

}
