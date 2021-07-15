package com.attt.ducnb.atttapi.Utils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.encryptor4j.util.FileEncryptor;
import org.encryptor4j.util.TextEncryptor;

public class AES {
    public static void Encrypt(File srcFile,String key) throws GeneralSecurityException, IOException{
        String filename = srcFile.getName();
        File destFile = new File(filename.concat(".dbrr"));
        FileEncryptor fe = new FileEncryptor(key);
        fe.encrypt(srcFile, destFile);
    }
    public static void Decrypt(File srcFile,String key) throws GeneralSecurityException, IOException{
        String filename = srcFile.getName();
        File destFile = new File(filename.substring(0, filename.length()-".dbrr".length()-1));
        FileEncryptor fe = new FileEncryptor(key);
        fe.decrypt(srcFile, destFile);
    }
    public static String EncryptText(String text,String key) throws GeneralSecurityException{
        TextEncryptor te = new TextEncryptor(key);
        return te.encrypt(text);
    }
    public static String DeccryptText(String text,String key) throws GeneralSecurityException{
        TextEncryptor te = new TextEncryptor(key);
        return te.decrypt(text);
    }
}
