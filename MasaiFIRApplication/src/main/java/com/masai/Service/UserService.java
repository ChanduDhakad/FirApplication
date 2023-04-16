package com.masai.Service;

import java.util.List;

import com.masai.Exception.FirException;
import com.masai.Exception.PoliceException;
import com.masai.Exception.PoliceStationException;
import com.masai.Exception.UserException;
import com.masai.Model.Fir;
import com.masai.Model.LoginDto;
import com.masai.Model.Police;
import com.masai.Model.User;

public interface UserService {

	
	public User registerUser(User user)throws UserException;
	
	
	public User loginUser(LoginDto credential)throws UserException;

	public  Fir firsOfPoliceStation(Integer stationId)throws PoliceStationException;
	
	public List<Police> policesOfClosedMaxCases(Integer stationId)throws PoliceStationException;
	
	public Fir closeFir(Integer userId,Integer firId)throws UserException,FirException;
	
	public String policeOfficerTryToCloseFir(Integer uuid, Integer firId,Integer policeId)throws FirException,PoliceException;
	
	public String deletePolice( Integer policeID1,Integer policeID2)throws PoliceException,PoliceStationException;
	
	
}
