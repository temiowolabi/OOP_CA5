package com.dkit.oop.sd2.Core;

public class CAOService {
    public static final int PORT_NUM = 50025;
    public static final String HOSTNAME = "localhost";

    public static final String BREAKING_CHARACTER = "%%";

    public static final String END_SESSION = "QUIT";
    public static final String REGISTER_COMMAND = "REGISTER";
    public static final String SUCCESSFUL_REGISTER = "REGISTERED";
    public static final String FAILED_REGISTER = "REG FAILED";

    //Response strings
    public static final String UNRECOGNISED = "UNKNOWN_COMMAND";
    public static final String SESSION_TERMINATED = "GOODBYE";
}
