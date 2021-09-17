package com.assignment.booking.security;

public class SecurityConstants {

    // From browser , I could not get "Authorization" header when I send login  request
    // "pragma" header was showing . that's why i put JWT into pragma header
    public static final String HEADER_STRING="Pragma";
    public static final long EXPERATION_TIME=1000000000;
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String SECRET_TOKEN="sldkjiowfsdkfjlskdjf";
    public static final String CROSS_ORIGIN="Access-Control-Allow-Origin";
}
