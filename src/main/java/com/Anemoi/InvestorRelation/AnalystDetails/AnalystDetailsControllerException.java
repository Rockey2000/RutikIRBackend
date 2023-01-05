package com.Anemoi.InvestorRelation.AnalystDetails;

import com.Anemoi.InvestorRelation.GlobalExeption.GlobalException;

public class AnalystDetailsControllerException extends GlobalException {
	private static final long serialVirsionUID=1L;
	
	public AnalystDetailsControllerException(int errorCode, String developerMessage, String message) {
		super(errorCode, developerMessage, message);
		// TODO Auto-generated constructor stub
	}


	public AnalystDetailsControllerException(String message, Throwable cause,int errorCode, String developerMessage) {
		super(message, cause, errorCode,developerMessage);
		// TODO Auto-generated constructor stub
	}

	
}
