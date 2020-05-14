package org.sid.service.Imp;

import org.sid.security.JwtUtil;
import org.sid.service.AuthrizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthorizationServiceImp  implements AuthrizationService{
	@Autowired
	JwtUtil jwtTokenUtil;
	@Override
	public String getConnectedUserName(String authorzationHeader) {
		String jwt =  authorzationHeader.substring(7);
        String email = jwtTokenUtil.extractUsername(jwt);
		return email;
	}

}
