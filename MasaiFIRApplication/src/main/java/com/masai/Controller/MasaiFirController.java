package com.masai.Controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.FirException;
import com.masai.Exception.PoliceException;
import com.masai.Exception.PoliceStationException;
import com.masai.Exception.UserException;
import com.masai.Model.Fir;
import com.masai.Model.LoginDto;
import com.masai.Model.Police;
import com.masai.Model.PoliceStation;
import com.masai.Model.User;
import com.masai.Service.FirService;
import com.masai.Service.PoliceService;
import com.masai.Service.UserService;

@RestController
@RequestMapping("/masaifir")
public class MasaiFirController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private FirService firService;
	
	@Autowired
	private PoliceService policeService;
	
	
	@PostMapping("/user/register") 
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user)throws UserException{
		User res = userService.registerUser(user);
		return new ResponseEntity<User>(res, HttpStatus.CREATED);
			
	}
	
	@PostMapping("/userlogin")
	public ResponseEntity<User> loginUser(LoginDto credential)throws UserException{
		User res = userService.loginUser(credential);
		return new ResponseEntity<User>(res, HttpStatus.OK);
			
	}
 
	@GetMapping("/user/fir/{stationId}")
	public  ResponseEntity<Fir> firsOfPoliceStation(@PathVariable("stationId") Integer stationId)throws PoliceStationException{
		Fir res = userService.firsOfPoliceStation(stationId);
		return new ResponseEntity<Fir>(res, HttpStatus.OK);
	}
	
	
	@GetMapping("/officer/{stationId}")
	public ResponseEntity<List<Police>> policesOfClosedMaxCases(@PathVariable("stationId") Integer stationId)throws PoliceStationException{
		List<Police> res = userService.policesOfClosedMaxCases(stationId);
		return new ResponseEntity<List<Police>>(res, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/user/fir/{userId}/{firId}/")
	public ResponseEntity<Fir>  closeFir(@PathVariable("userId") Integer userId,@PathVariable("firId") Integer firId)throws UserException,FirException{
		Fir res = userService.closeFir(userId, firId);
		return new ResponseEntity<Fir>(res, HttpStatus.OK);
	}
	
	@PutMapping("/user/fir/{uuid}/{firId}/{policeId}")
	public ResponseEntity<String> policeOfficerTryToCloseFir(@PathVariable("uuid") Integer uuid, @PathVariable("firId") Integer firId,@PathVariable("policeId")  Integer policeId)throws FirException,PoliceException{
	
		String res = userService.policeOfficerTryToCloseFir(uuid, firId, policeId);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	
	}
	@DeleteMapping("officer/{policeId1}/{policeId2}")
	public  ResponseEntity<String>  deletePolice(@PathVariable("policeId1") Integer policeID1,@PathVariable("policeId2") Integer policeID2)throws PoliceException,PoliceStationException{
		String res = userService.deletePolice(policeID1, policeID2);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@PostMapping("masaifir/user/fir/{userId}")
	public ResponseEntity<Fir> createFir(@PathVariable("userId") Integer userId,@RequestBody Fir fir)throws FirException, UserException{
		Fir res = firService.createFir(userId, fir);
		return new ResponseEntity<Fir>(res, HttpStatus.OK);
	}
	
	@PostMapping("masaifir/user/police")
	public ResponseEntity<Police> registerPolice(@RequestBody Police police)throws PoliceException{
		Police res = policeService.registerPolice(police);
		return new ResponseEntity<Police>(res, HttpStatus.OK);
	}
	@PostMapping("masaifir/user/policeStation")
	public ResponseEntity<PoliceStation> registerPoliceStation(@RequestBody PoliceStation policeStation)throws PoliceException{
		PoliceStation res = policeService.registerPoliceStation(policeStation);
		return new ResponseEntity<PoliceStation>(res, HttpStatus.OK);
	}
	
	
	


}