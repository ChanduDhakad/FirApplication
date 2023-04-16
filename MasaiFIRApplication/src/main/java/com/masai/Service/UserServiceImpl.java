package com.masai.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.FirException;
import com.masai.Exception.PoliceException;
import com.masai.Exception.PoliceStationException;
import com.masai.Exception.UserException;
import com.masai.Model.CurrentSession;
import com.masai.Model.Fir;
import com.masai.Model.LoginDto;
import com.masai.Model.Police;
import com.masai.Model.PoliceStation;
import com.masai.Model.User;
import com.masai.Repository.FIrDao;
import com.masai.Repository.PoliceDao;
import com.masai.Repository.PoliceStationDao;
import com.masai.Repository.SessionDao;
import com.masai.Repository.UserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{


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
	public User registerUser(User user) throws UserException {
	 
	   User isUserPresent=uDao.findByEmail(user.getEmail());
	
	    if(isUserPresent==null) {
	    	
	     User newUser=uDao.save(user);
	     return newUser;
	    }
	    else {
	    	
	    	throw new UserException("User Is Already Registered with given email id "+user.getEmail());
	    }
		
	}

	@Override
	public User loginUser(LoginDto credential) throws UserException {
	
		
		User isUserPresent = uDao.findByEmail(credential.getEmail());
		
		if(isUserPresent!=null) {
		
			if(credential.getPassword().equals(isUserPresent.getPassword())) {
			 String key=RandomString.make(6);
			 CurrentSession active=new CurrentSession(isUserPresent.getUserId(), key, LocalDateTime.now());
			 
			 sDao.save(active);
			return isUserPresent;
			}
			else {
				throw new UserException(" login Password is Correct");
			}
			
		}
		else {
			throw new UserException(" login User name is not Correct");
		}
		
		
		
	} 

	  public int compare(Fir fire1,  Fir fire2) {
	        return fire1.getDate().compareTo(fire2.getDate());
	    }
	
	@Override
	public  Fir firsOfPoliceStation(Integer stationId) throws PoliceStationException {
		
		Fir Oldfirs=null;
		Optional<PoliceStation>  isPoliceStationExist=psDao.findById(stationId);
		if(isPoliceStationExist.isPresent()) {
			PoliceStation station=isPoliceStationExist.get();
		    List<Fir> listOfFirs=station.getNoOfFirs();
	     Collections.sort(listOfFirs,(o1, o2) ->   compare(o1, o2));
	      Oldfirs=listOfFirs.get((listOfFirs.size()-1));	  
		 return Oldfirs;
		 }
		else {
			throw new PoliceStationException("Police Station Not Registor wth  Given Id");
		}
		
	}


	
	
	@Override
	public List<Police> policesOfClosedMaxCases(Integer stationId) throws PoliceStationException {
		List<Police> Newlist=null;
		
		Optional<PoliceStation>  isPoliceStationExist=psDao.findById(stationId);
		
		if( isPoliceStationExist.isPresent()) {
			
			PoliceStation newStation= isPoliceStationExist.get();
			
			List<Police> constables=newStation.getNoOfConstables();
			Police officers=newStation.getOfficerInCharge();
			
			
			int maxCase=0;
			
			for(int i=0;i<constables.size();i++) {
				if(maxCase<=constables.get(i).getNOfcasesClosed().size()) {
					maxCase=constables.get(i).getNOfcasesClosed().size();
				}
			}
			
			
			Newlist= new ArrayList<>();
			for(int j=0;j<constables.size();j++) {
				if(maxCase==constables.get(j).getNOfcasesClosed().size()) {
					Newlist.add(constables.get(j));
				}
			}
			
		     
			
		 if(officers.getNOfcasesClosed().size()>=maxCase) {
			 Newlist.add(officers);
		 }
	     
			
		}
		else {
			
			System.out.println("Wrong Station Id is not match ");
			
		}
		
		
		 return Newlist;		
	}

	@Override
	public Fir closeFir(Integer userId, Integer firId) throws UserException, FirException {
		Fir fir=null;
		 	
		Optional<User> isUserExist=uDao.findById(userId);
		Optional<Fir> isFireExist=fDao.findById(firId);
		
		if(isUserExist.isPresent()) {
			User user=isUserExist.get();
			
			if(isFireExist.isPresent()) {
			 Fir fire=isFireExist.get();
			 fir=fire;
              if(fire.getApplicant().getUserId()==userId) {
            	  
            	   
            	  fire.setOpen(true);
            	  fDao.delete(fire);
            	  
            	  
            	  
              }else {
            	  
            	  throw new UserException("You Can Only Delete Your Fir");
              }
			 
			 
			
				
			}
			
		}
		
		
		
		
		
		return fir;
	}

	@Override
	public String policeOfficerTryToCloseFir(Integer uuid,Integer firId, Integer policeId) throws FirException, PoliceException {
	String result="";	
	Optional<Fir> isFirPresent=fDao.findById(firId);
	Optional<Police> isPolicePresent=pDao.findById(firId);
	
	if(isFirPresent.isPresent()) {
		if(isPolicePresent.isPresent()) {
			Fir fir=isFirPresent.get();
			Police police=isPolicePresent.get();
			
		Optional<CurrentSession> Opt= sDao.findById(uuid);
		
		 if(Opt.isPresent()) {
			 
			 fDao.delete(fir);
			 
		 }
		 else {
			 
			 throw new PoliceException("You Are Not Close Fir Because User Not Login" );
			 
		 }
		
			
		}
		else {
			throw new PoliceException("Police  Is Not Present By Given Id");
			
		}
	}
	else {
	
		throw new FirException("Fir Is Not Present By Given Id");
		
	}
	
		
	
	
	 return result;
	}

	@Override
	public String deletePolice(Integer policeID1, Integer policeID2) throws PoliceException, PoliceStationException {
		String result="Ploice Officer Not Deleted";
		
	 Optional<Police> opt1=pDao.findById(policeID1);
	 Optional<Police> opt2=pDao.findById(policeID2);
		
	 
	 if(opt1.isPresent()  && opt2.isPresent()) {
		 Police police1=opt1.get();
		 Police police2=opt2.get();
		 
		 List<Fir> firs=police1.getNOfFirsFiled();
		 
		 police2.setNOfFirsFiled(firs);
         
		 pDao.delete(police1);
		 
		 result="Police Officer Deleted and Successfully Assign The Open Cases to Other Police Officer";
		 
	 }
	 else {
		 System.out.println("Police Is Not Find Out By Given Id");
	 }
		
		
		return result;
	}
	
	

}
