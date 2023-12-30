package ro.ubb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIRequest {

    public static String getRequest(String urlAsString){
        try {
            URL url = new URL(urlAsString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                return output;
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> getMapFromStringJSON(String stringJSON) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object>  a = mapper.readValue(stringJSON, Map.class);
        return mapper.readValue(stringJSON, Map.class);
    }
}
