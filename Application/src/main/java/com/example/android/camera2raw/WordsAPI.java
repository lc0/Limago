package com.example.android.camera2raw;

/**
 * Created by khomenkos on 27/09/15.
 */
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.*;
import java.io.*;

public class WordsAPI {

    private static String baseUrl = "https://wordsapiv1.p.mashape.com/words/";
    private String key;

    public WordsAPI(String key) {
        this.key = key;
    }

    private HttpsURLConnection createConnection(String word, String method) {
        String query = "https://wordsapiv1.p.mashape.com/words/" + word + "/" + method;
        URL url = null;
        try {
            url = new URL(query);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection conn = null;
        try {
            conn = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("X-Mashape-Key", key);
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }

    public String getDefinition(String word) throws IOException {
        HttpsURLConnection conn = createConnection(word, "also");

        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
        BufferedReader buff = new BufferedReader(in);
        StringBuilder builder = new StringBuilder();
        String line;
        do {
            line = buff.readLine();
            if (line != null) {
                builder.append(line + "\n");
            }
        } while (line != null);

        return builder.toString();
    }
}

