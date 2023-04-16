package com.masai.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.FirException;
import com.masai.Exception.UserException;
import com.masai.Model.Fir;
import com.masai.Model.User;
import com.masai.Repository.FIrDao;
import com.masai.Repository.PoliceDao;
import com.masai.Repository.PoliceStationDao;
import com.masai.Repository.SessionDao;
import com.masai.Repository.UserDao;

@Service
public class FirServiceImpl implements FirService {

	
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
	public Fir createFir(Integer userId, Fir fir) throws FirException, UserException {
		Optional<User> exitUser=uDao.findById(userId);
		Fir fir2=null;
		if(exitUser.isPresent()) {
			User user=exitUser.get(); 
		 	fir2= fDao.save(fir);	
			
		}
		else {
			throw new UserException("User Not Exist with Given User ID");
		}
		
		
		return fir2;
	}

}
