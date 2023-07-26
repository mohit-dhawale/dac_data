package com.sunbeaminfo.custom_exception;

@SuppressWarnings("serial")
public class ResourcesNotFoundException extends RuntimeException{
	public ResourcesNotFoundException(String mesg) {
		super(mesg);
	}
}
