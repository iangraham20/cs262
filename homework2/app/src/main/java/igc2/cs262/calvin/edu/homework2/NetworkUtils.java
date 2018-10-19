package igc2.cs262.calvin.edu.homework2;

import android.widget.Toast;
import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String PLAYER_LIST_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/players";
    private static final String PLAYER_ID_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/player/";

    static String getPlayerListInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String playerListJSONString = null;
        try {
            Uri builtURI = Uri.parse(PLAYER_LIST_URL).buildUpon().build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            playerListJSONString = buffer.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Connection Failed!";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(playerListJSONString != null) {
                Log.e(LOG_TAG, playerListJSONString);
                return playerListJSONString;
            } else {
                return "";
            }
        }
    }

    static String getPlayerIDInfo(String queryString) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String playerIDJSONString = null;

        try {
            Uri builtURI = Uri.parse(PLAYER_ID_URL).buildUpon().appendPath(queryString).build();
            URL requestURL = new URL(builtURI.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            playerIDJSONString = buffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Connection failed!";
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (playerIDJSONString != null) {
                Log.e(LOG_TAG, playerIDJSONString);
                return playerIDJSONString;
            } else {
                return "";
            }
        }
    }
}








