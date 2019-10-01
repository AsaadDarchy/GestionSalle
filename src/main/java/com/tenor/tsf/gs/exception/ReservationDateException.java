package com.tenor.tsf.gs.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservationDateException extends gsException {
	private static final long serialVersionUID = 1L;
	public static final Logger log = LogManager.getLogger(ReservationDateException.class);
	public ReservationDateException() {
		log.debug("Dates are incorrect",this);
	}
}

