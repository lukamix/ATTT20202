package com.USB.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestServer {
    public static String GET(String id,String hashpassword) throws IOException {
        String url = "http://localhost:8080/ATTTAPI/login"
                +"?id="+id+"&hpw="+hashpassword;
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        
        int responseCode = conection.getResponseCode();
        System.out.println(responseCode);
        String ers = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            ers = response.toString();
        } else {
            System.out.println("GET NOT WORKED");
        }
        return ers;
    }
    public static String GET(String Fn,String id,String e,String s,String u,String w) throws MalformedURLException, IOException{
        String url = "http://localhost:8080/ATTTAPI/cryptography"
                +"?Fn="+Fn+"&id="+id+"&e="+e+"&s="+s+"&u="+u+"&w="+w;
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        
        int responseCode = conection.getResponseCode();
        System.out.println(responseCode);
        String esknzmac = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            esknzmac = response.toString();
        } else {
            System.out.println("GET NOT WORKED");
        }
        return esknzmac;
    }
    
}
