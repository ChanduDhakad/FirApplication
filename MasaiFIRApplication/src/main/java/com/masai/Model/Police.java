package com.masai.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Police {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer policeId;
	private String officerName;
	
	@Column(unique = true)
	private String mobileName;

	private String department;

	@OneToMany
	private List<Fir> nOfFirsFiled =new ArrayList<>();
	
	@OneToMany
	private List<Fir> nOfcasesClosed=new ArrayList<>();
	

}
