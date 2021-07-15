package com.attt.ducnb.atttapi.Utils;

import com.attt.ducnb.atttapi.prime.GeneratedPrime;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHA1Hashing {
    
    public static byte[] getSHAHash(byte[] input) {
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input);
            return messageDigest;
        } catch (NoSuchAlgorithmException ex) {
             throw new RuntimeException(ex);
        }
    }
    public static String hashToGroupElement(byte[] input){
        BigInteger inputInt = new BigInteger(Utility.convertByteToHex(getSHAHash(input)),16);
        BigInteger output = inputInt.modPow(GeneratedPrime.r, GeneratedPrime.p);
        return output.toString();
    }
    
}
