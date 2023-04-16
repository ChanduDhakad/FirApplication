package com.masai.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fir {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer firId;
	private LocalDate date;
	private LocalTime time;
 	private boolean isOpen;
    
 	@ManyToOne() 
    private User applicant;
 	
 	@OneToMany
 	 private  List<User> noOfCriminals=new ArrayList<>(); 
	
 	 @ManyToOne()
 	 private Police officer;
 	 
 	 @ManyToOne()
 	 private PoliceStation policeStation;
 	
	
}
