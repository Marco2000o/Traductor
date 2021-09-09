/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traductor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import static javax.management.Query.lt;
import org.json.JSONArray;
import org.json.JSONObject;

public class Traductor {

    public static void main(String[] args) throws IOException {

        try {
            String IdiomaOrigen = "", IdiomaDestino = "", Palabra = "";
            IdiomaOrigen = "es";
            IdiomaDestino = "en";
            System.out.println("Palabra en Español");

            Scanner entradaEscaner = new Scanner(System.in);
            Palabra = entradaEscaner.nextLine();
            String urlstr = "https://translation.googleapis.com/language/translate/v2?key=";
            urlstr += "&source=" + IdiomaOrigen;
            urlstr += "&target=" + IdiomaDestino;
            urlstr += "&q=" + URLEncoder.encode(Palabra, "UTF-16");

            URL ul1 = new URL(urlstr);

            HttpURLConnection urlC = (HttpURLConnection) ul1.openConnection();
            urlC.setRequestMethod("GET");
            urlC.setRequestProperty("accept", "application/json");

            InputStream inputStream = urlC.getInputStream();
            BufferedInputStream reader = new BufferedInputStream(inputStream);

            int readerBuffer = reader.read();

            String myJson = "";

            while (readerBuffer != -1) {
                char u = (char) readerBuffer;
                myJson += u;
                readerBuffer = reader.read();
            }
            System.out.println(myJson);

            JSONObject json = new JSONObject(myJson);
  
            Object data = json.getJSONObject("data").getJSONArray("translations").getJSONObject(0).getString("translatedText");

            
            System.out.println(data);
            
           
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

    }

}
