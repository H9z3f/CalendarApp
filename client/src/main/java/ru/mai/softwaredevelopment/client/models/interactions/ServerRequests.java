package ru.mai.softwaredevelopment.client.models.interactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerRequests {
    public static String registrationRequest(String username, String password) throws IOException {
        String url = "http://localhost:3000/authorization/registration";
        String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        return request(url, requestBody);
    }

    public static String loginRequest(String username, String password) throws IOException {
        String url = "http://localhost:3000/authorization/login";
        String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        return request(url, requestBody);
    }

    public static String addNoteRequest(String sessionKey, String username, String date, String note) throws IOException {
        String url = "http://localhost:3000/notes/add";
        String requestBody = "{\"sessionKey\": \"" + sessionKey + "\", \"username\": \"" + username + "\", \"date\": \""
                + date + "\", \"note\": \"" + note + "\"}";

        return request(url, requestBody);
    }

    public static String removeNoteRequest(String sessionKey, String username, String date) throws IOException {
        String url = "http://localhost:3000/notes/remove";
        String requestBody = "{\"sessionKey\": \"" + sessionKey + "\", \"username\": \"" + username + "\", \"date\": \""
                + date + "\"}";

        return request(url, requestBody);
    }

    public static String request(String _url, String requestBody) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(requestBody.getBytes());
        outputStream.flush();
        outputStream.close();

        StringBuilder response = new StringBuilder();
        String inputLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

        return response.toString();
    }
}