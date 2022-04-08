package com.example.poosdaniel_restapi;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class City {
    private int id;
    private String nev;
    private String orszag;
    private int lakossag;
}
class Response {
    int responseCode;
    String content;
    public Response(int responseCode, String content) {
        this.responseCode = responseCode;
        this.content = content;
    }
    public String getContent() { return content;}
    public void setContent(String content) { this.content = content; }
}
class RequestTask extends AsyncTask<Void, Void, Response> {
    private String requestType;
    private String requestParams;
    private String url;
    public Response response;
    private Runnable finalTask;
    public RequestTask(String url, String requestType) {
        this.requestType = requestType;
        this.url = url;
    }
    public RequestTask(String url, String requestType, String requestParams) {
        this.requestType = requestType;
        this.requestParams = requestParams;
        this.url = url;
    }
    public Runnable getFinalTask() {
        return finalTask;
    }
    public void setFinalTask(Runnable finalTask) {
        this.finalTask = finalTask;
    }
    public RequestTask(){
        this.finalTask = null;
    }
    @Override protected Response doInBackground(Void... voids) {
        Response response = null;
        try {
            switch(requestType){
                case "get":
                    response = RequestHandler.get(url);
                    break;
                case "post":
                    response = RequestHandler.post(url,requestParams);
                    break;
            }
        } catch (IOException e) {e.printStackTrace();   }
        this.response = response;
        return response;
    }
    @Override protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        finalTask.run();
    }
}
class RequestHandler {
    private RequestHandler(){}
    public static Response get(String url) throws IOException {
        HttpURLConnection conn = setupConnection(url);
        return getResponse(conn);
    }
    public static Response post(String url, String data) throws IOException {
        HttpURLConnection conn = setupConnection(url);
        conn.setRequestMethod("POST");
        addRequestBody(conn, data);
        return getResponse(conn);
    }
    private static void addRequestBody(HttpURLConnection conn, String data) throws IOException{
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
        writer.write(data);
        writer.flush();
        writer.close();
        os.close();
    }
    private static HttpURLConnection setupConnection(String url) throws IOException{
        URL urlObj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        return conn;
    }
    private static Response getResponse(HttpURLConnection conn) throws IOException{
        int responseCode = conn.getResponseCode();
        InputStream is;
        if (responseCode < 400) is = conn.getInputStream();
        else is = conn.getErrorStream();
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String sor = br.readLine();
        while (sor != null){
            builder.append(sor);
            sor = br.readLine();
        }
        br.close();
        is.close();
        return new Response(responseCode, builder.toString());
    }
}