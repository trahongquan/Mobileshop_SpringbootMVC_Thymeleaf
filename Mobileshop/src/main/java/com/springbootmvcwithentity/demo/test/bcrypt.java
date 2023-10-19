package com.springbootmvcwithentity.demo.test;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class bcrypt {
    static String endcodeBcrypt(String password, int number){
//        String password = "stackjava.com";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(number));
        System.out.println("BCrypt hash: " + hash);
        return hash;
    }
    static Boolean checkdecode(String password, String hashed){
//        String password = "stackjava.com";
//        boolean valuate = BCrypt.checkpw(password, "$2a$12$OFOICietLS3.qRtzIe6jE.vF.fmtL22DqIZ18WNMmQ.8nS7Frq5aO");
        boolean valuate = BCrypt.checkpw(password, hashed);
        System.out.println(valuate);
        return valuate;
    }
    public static void main(String[] args) {
        String password = "stackjava.com";
        String hashed =  endcodeBcrypt(password, 12);
        boolean check = checkdecode(password, hashed);
    }
}
