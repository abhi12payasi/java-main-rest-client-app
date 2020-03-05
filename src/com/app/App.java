package com.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {
        post();
        get();
    }
    private static void get() throws IOException {

        String getUrl = "http://localhost:8080/student-app/studentData";
        URL url = new URL(getUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("accept","application/json");
        int responseCode = httpURLConnection.getResponseCode();
        if(responseCode == httpURLConnection.HTTP_OK){

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            //response
            System.out.println(response.toString());
        }

    }
    private static void post() throws IOException {
        String getUrl = "http://localhost:8080/student-app/registerStudent";
        URL url = new URL(getUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");

        //for post  else we ll get error cannot write to a URLConnection if doOutput=false - call setDoOutput(true)
        httpURLConnection.setDoOutput(true);

        int roll = 101;
        String name = "abhishek";
        String university = "MANIT";
       // String student =("rollNo="+roll+"&name="+name+"&university="+university).trim();
        String student =("name="+name+"&university="+university).trim();

        httpURLConnection.getOutputStream().write(student.getBytes());

        int responseCode = httpURLConnection.getResponseCode();
        //System.out.println(responseCode);
        if(responseCode == httpURLConnection.HTTP_OK){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            // print result
            System.out.println(response.toString());

        }
    }
}
