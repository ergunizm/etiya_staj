package com.etiya.staj.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.etiya.staj.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

public class JsonUtility {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static List<User> readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonString = readAll(rd);

            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonString, User[].class);

            return Arrays.asList(users);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            is.close();
        }
        return null;
    }
}
