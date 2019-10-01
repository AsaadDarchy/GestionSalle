package com.tenor.tsf.gs.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends gsException {

	private static final long serialVersionUID = 1L;
	public static final Logger log = LogManager.getLogger(SalleNotFoundException.class);
	public DepartmentNotFoundException() {
		log.debug("this departement doesn't exist",this);
	}

}