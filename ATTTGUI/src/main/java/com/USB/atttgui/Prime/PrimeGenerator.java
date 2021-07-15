package com.USB.atttgui.Prime;

import java.math.BigInteger;
import java.security.SecureRandom;

public class PrimeGenerator {
    public static BigInteger[] generateQPAndK() {
        SecureRandom random = new SecureRandom();

        final int pSizeInBits = 1024;
        final int qSizeInBits = 160;
        BigInteger q = BigInteger.probablePrime(qSizeInBits, random);
        BigInteger k = BigInteger.ONE.shiftLeft(pSizeInBits - qSizeInBits); // k = 2^(pSizeInBits - qSizeInBits);

        BigInteger probablyPrime = q.multiply(k).add(BigInteger.ONE); // probablyPrime = q * k + 1
        while (!probablyPrime.isProbablePrime(50)) {
            q = BigInteger.probablePrime(qSizeInBits, random);
            probablyPrime = q.multiply(k).add(BigInteger.ONE);
        }

        BigInteger[] qAndPandK = new BigInteger[3];
        qAndPandK[0] = q;
        qAndPandK[1] = probablyPrime;
        qAndPandK[2] = k;
        
        return qAndPandK;
    }
    public static BigInteger RandomPrimeSchnorrGroup(int bitlength){
        BigInteger randomvalue = new BigInteger(bitlength,new SecureRandom());
        while(randomvalue.compareTo(GeneratedPrime.p)!=-1){
            randomvalue = new BigInteger(bitlength,new SecureRandom());
        }
        return randomvalue.modPow(GeneratedPrime.r, GeneratedPrime.p);
    }
}
