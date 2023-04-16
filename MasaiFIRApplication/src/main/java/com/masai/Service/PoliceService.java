package com.masai.Service;

import com.masai.Exception.PoliceException;
import com.masai.Model.Police;
import com.masai.Model.PoliceStation;

public interface PoliceService {

	public Police registerPolice(Police police)throws PoliceException;

	public PoliceStation registerPoliceStation(PoliceStation policeStation)throws PoliceException;
	
	
}
