package com.in.fmc.userservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.fmc.userservice.constants.ErrorConstants;
import com.in.fmc.userservice.constants.SuccessConstants;
import com.in.fmc.userservice.entities.Login;
import com.in.fmc.userservice.entities.Register;
import com.in.fmc.userservice.exceptions.InvalidCredentialException;
import com.in.fmc.userservice.exceptions.UsernameOccupiedException;
import com.in.fmc.userservice.repositories.LoginRepository;
import com.in.fmc.userservice.repositories.RegisterRepository;
import com.in.fmc.userservice.utils.Utils;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {

	@Autowired
	LoginRepository loginrepository;

	@Autowired
	RegisterRepository registerrepository;

	@Override
	public ResponseEntity<String> login(Login login) {
		login.setPassword(Utils.secureHash(login.getPassword()));
		Optional<Login> optionallogin = loginrepository.findByUsernameAndPassword(login.getUsername(),
				login.getPassword());
		if (optionallogin.isPresent()) {
			return new ResponseEntity<String>(SuccessConstants.LOGIN_SUCCESS, HttpStatus.OK);
		}
		throw new InvalidCredentialException(ErrorConstants.INVALID_CREDENTIALS_EXCEPTION_MSG);
	}

	@Override
	public ResponseEntity<String> register(Register register) {
		Optional<Login> optionallogin = loginrepository.findByUsername(register.getLogin().getUsername());

		if (!optionallogin.isPresent()) {
			Login login = register.getLogin();
			login.setRegister(register);

			login.setPassword(Utils.secureHash(login.getPassword()));
			registerrepository.save(register);

			return new ResponseEntity<String>(SuccessConstants.REGISTER_SUCCESS, HttpStatus.CREATED);
		}
			throw new UsernameOccupiedException(ErrorConstants.USERNAME_OCCUPIED_EXCEPTION_MSG);
	}

}
