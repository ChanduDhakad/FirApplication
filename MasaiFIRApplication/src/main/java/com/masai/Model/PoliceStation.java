package com.masai.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceStation {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer policeStationId;

	@Column(unique = true)
	private String stationCode;
	private String address;
	
	@OneToOne
	private Police officerInCharge;

    @OneToMany
    private List<Police>  noOfConstables =new ArrayList<>();
	 
	
	@OneToMany
	private List<Fir> noOfFirs=new ArrayList<>();
	
}


