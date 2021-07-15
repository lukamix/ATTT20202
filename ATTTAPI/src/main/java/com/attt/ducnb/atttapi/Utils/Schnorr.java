package com.attt.ducnb.atttapi.Utils;

import com.attt.ducnb.atttapi.prime.GeneratedPrime;
import com.attt.ducnb.atttapi.prime.PrimeGenerator;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Schnorr {
    /*
        Registration Phase
    */
    //Server
    public static String[] GenerateERS(String id,String hpw){
        BigInteger k = BigInteger.probablePrime(160,new Random());
        while(k.compareTo(GeneratedPrime.q)!=-1){
            k = BigInteger.probablePrime(160,new Random());
        }
        BigInteger hpwInt = new BigInteger(hpw);
        BigInteger r = hpwInt.modPow(k, GeneratedPrime.p);
        BigInteger r1 = GeneratedPrime.g.modPow(k, GeneratedPrime.p);
        
        String hashString = id.concat(r.toString().concat(r1.toString()));
        byte[] ebyte = SHA1Hashing.getSHAHash(hashString.getBytes());
        
        BigInteger eInt = new BigInteger(ebyte);
        BigInteger s = (k.subtract(eInt.multiply(GeneratedPrime.x))).mod(GeneratedPrime.q);
        String[] ERS = new String[3];
        ERS[0] = eInt.toString();
        ERS[1] = r.toString();
        ERS[2] = s.toString();
        return ERS;
    }
    //Client
    public static int CheckSign(String[] ERS,String id){
        BigInteger sign = GeneratedPrime.g.modPow(new BigInteger(ERS[2]), GeneratedPrime.p).multiply(GeneratedPrime.y.modPow(new BigInteger(ERS[0]),GeneratedPrime.p)).mod(GeneratedPrime.p);
        
        String hashString = id.concat(ERS[1].concat(sign.toString()));
        byte[] hashdata = SHA1Hashing.getSHAHash(hashString.getBytes());
        BigInteger hashDataInt = new BigInteger(hashdata);
        return hashDataInt.compareTo(new BigInteger(ERS[0]));
    }
    /*
        Transfer Phase
    */
    //Client
    public static void ClientVerify(String id,String password,String Fn){
        String hpw = SHA1Hashing.hashToGroupElement(password.getBytes());
        BigInteger rC = PrimeGenerator.RandomPrimeSchnorrGroup(1024);
        
        BigInteger u = new BigInteger(hpw).multiply(GeneratedPrime.y.modPow(rC, GeneratedPrime.p)).mod(GeneratedPrime.p);
        BigInteger w = GeneratedPrime.g.modPow(rC, GeneratedPrime.p);
        
    }
    //Server
    public static String[] Verify(String Fn, String id, String e, String s, String u, String w) throws GeneralSecurityException{
        String[] res = new String[3];
        BigInteger eInt = new BigInteger(e);
        BigInteger sInt = new BigInteger(s);
        BigInteger uInt = new BigInteger(u);
        BigInteger wInt = new BigInteger(w);
        
        BigInteger hpw = uInt.multiply(wInt.modPow(GeneratedPrime.x.negate(), GeneratedPrime.p)).mod(GeneratedPrime.p);
        
        BigInteger k = sInt.add(eInt.multiply(GeneratedPrime.x).mod(GeneratedPrime.q)).mod(GeneratedPrime.q);
        
        BigInteger r = hpw.modPow(k, GeneratedPrime.p);
        
        BigInteger sign = GeneratedPrime.g.modPow(sInt, GeneratedPrime.p).multiply(GeneratedPrime.y.modPow(eInt,GeneratedPrime.p)).mod(GeneratedPrime.p);
        String hashString = id.concat(r.toString()).concat(sign.toString());
        byte[] hashdata = SHA1Hashing.getSHAHash(hashString.getBytes());
        BigInteger hashDataInt = new BigInteger(hashdata);
        if(eInt.compareTo(hashDataInt)==0){
            System.out.println("ok");
            BigInteger rS = PrimeGenerator.RandomPrimeSchnorrGroup(1024);
            BigInteger z = GeneratedPrime.g.modPow(rS, GeneratedPrime.p);
            BigInteger sk = wInt.modPow(rS, GeneratedPrime.p);
            byte[] nbyte = SHA1Hashing.getSHAHash(GeneratedPrime.x.toString().concat(Fn).getBytes());
            String n = new BigInteger(nbyte).toString();
            String Eskn = AES.EncryptText(n, sk.toString());
            String MAC = new BigInteger(SHA1Hashing.getSHAHash(hpw.toString().concat(id).concat(n).concat(sk.toString()).concat(w).getBytes())).toString();
            res[0] = Eskn;
            res[1] = z.toString();
            res[2] = MAC;
            return res;
        }
        else {
            System.out.println("not ok");
            return res;
        }
    }
    //Client
    public static BigInteger checkVerify(String[] verify,BigInteger rC,String hpw,String id,BigInteger w){
        if(verify[0]!=null){
            BigInteger sk = new BigInteger(verify[1]).modPow(rC, GeneratedPrime.p);
            String Dskn ="";
            try {
                Dskn = AES.DeccryptText(verify[0], sk.toString());
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(Schnorr.class.getName()).log(Level.SEVERE, null, ex);
            }
            String hashString = hpw.concat(id).concat(Dskn).concat(sk.toString()).concat(w.toString());
            byte[] data = SHA1Hashing.getSHAHash(hashString.getBytes());
            if(new BigInteger(verify[2]).compareTo(new BigInteger(data))==0){
                byte[] abyte = SHA1Hashing.getSHAHash(id.concat(Dskn).getBytes());
                BigInteger a = new BigInteger(abyte);
                BigInteger K = GeneratedPrime.y.modPow(a, GeneratedPrime.p);
                return K;
            }
            else return null;
        }
        else return null;
    }
}
