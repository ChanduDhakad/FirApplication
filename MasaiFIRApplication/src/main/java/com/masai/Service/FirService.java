package com.masai.Service;

import com.masai.Exception.FirException;
import com.masai.Exception.UserException;
import com.masai.Model.Fir;

public interface FirService {

	public Fir createFir(Integer userId,Fir fir)throws FirException, UserException;

}
