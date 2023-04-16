package com.masai.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.PoliceException;
import com.masai.Exception.UserException;
import com.masai.Model.Police;
import com.masai.Model.PoliceStation;
import com.masai.Model.User;
import com.masai.Repository.FIrDao;
import com.masai.Repository.PoliceDao;
import com.masai.Repository.PoliceStationDao;
import com.masai.Repository.SessionDao;
import com.masai.Repository.UserDao;


@Service
public class PoliceServiceImpl implements PoliceService{


	@Autowired
	private FIrDao fDao;
	
	@Autowired
	private PoliceDao pDao;
	
	@Autowired
	private PoliceStationDao psDao;
	
	@Autowired
	private UserDao uDao;
	
	@Autowired
	private SessionDao sDao;

	
	
	@Override
	public Police registerPolice(Police police) throws PoliceException {
		Police newPolice=pDao.findByMobileName(police.getMobileName());
			
		    if(newPolice==null) {
		    	
		    	Police existPolice=pDao.save(police);
		     return  existPolice;
		    }
		    else {
		    	
		    	throw new PoliceException("Police Officer Is Already Registered with Given Mobile Number");
		    }
		
	}

	@Override
	public PoliceStation registerPoliceStation(PoliceStation policeStation) throws PoliceException {
		PoliceStation PoliceStationisExist=psDao.findByStationCode(policeStation.getStationCode());
	    if(PoliceStationisExist==null) {
	    	
	    	PoliceStation newPoliceStation=psDao.save(PoliceStationisExist);
	     return newPoliceStation;
	    }
	    else {
	    	
	    	throw new PoliceException("Police Station Is Already Registered");
	    }
	}



}
