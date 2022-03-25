package br.univates.luan.projeto_dicionario.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtils {
    public static JSONObject readExternalJsonObject(String path) {
        JSONObject object = null;
        JSONParser parser = new JSONParser();
        try {
            FileReader file = new FileReader(path);
            object = (JSONObject) parser.parse(file);
        } catch (FileNotFoundException e) {

        } catch (IOException | ParseException e) {

        }
        return object;
    }

    public static JSONArray readExternalJsonArray(String path) {
        JSONArray array = null;
        JSONParser parser = new JSONParser();
        try {
            FileReader file = new FileReader(path);
            array = (JSONArray) parser.parse(file);
        } catch (FileNotFoundException e) {
            
        } catch (IOException | ParseException e) {
            
        }
        return array;
    }
}
