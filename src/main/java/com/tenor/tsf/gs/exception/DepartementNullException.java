package com.tenor.tsf.gs.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartementNullException extends gsException {

	private static final long serialVersionUID = 1L;
	
	public static final Logger log = LogManager.getLogger(SalleNotFoundException.class);
	
	public DepartementNullException() {
	
		log.debug("entry fields of departement can't be empty",this);
	}

}
