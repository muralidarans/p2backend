package com.niit.model;

public class Error {
int errorCode;
String errorMessage;
public Error(){
	errorCode=0;
	errorMessage="";
}
public Error(int errorCode , String errorMessage){
	this.errorCode = errorCode;
	this.errorMessage = errorMessage;
}
public int getErrorCode() {
	return errorCode;
}
public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorStatus) {
	this.errorMessage = errorStatus;
}
}
