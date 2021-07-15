package com.USB.utils;

public class Utility {
    /**
    * Appends a byte array to another
    * 
    * @param a Byte array to append to
    * @param b Byte array appended
    * @return Returns appended byte array
    */
    public static byte[] append ( byte[] a, byte[] b ){    
        byte[] c = new byte[a.length + b.length];

        System.arraycopy ( a, 0, c, 0, a.length );
        System.arraycopy ( b, 0, c, a.length, b.length );

        return c;
    }
    public static String convertByteToHex(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
          sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    /* s must be an even-length string. */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
