package igc2.cs262.calvin.edu.homework02;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class implements GET CRUD operation to retrieve sample data from the Monopoly Database Player
 * endpoint(s).
 */
class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Player URI for Homework 2
    private static final String PLAYER_LIST_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/players";
    private static final String PLAYER_ID_URL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/player/";

    /**
     * Method queries specified URI.
     *
     * @param queryString what we're searching for
     * @return the results
     */
    static String getPlayerListInfo(String queryString) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Stores results.
        String playerListJSONString = null;

        try {
            //Build up your query URI.
            Uri builtURI = Uri.parse(PLAYER_LIST_URL).buildUpon()

                    .build();

            // Convert URI to URL
            URL requestURL = new URL(builtURI.toString());

            // Open connection and make request.
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the response.
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
           /* Since it's JSON, adding a newline isn't necessary (it won't affect
              parsing) but it does make debugging a *lot* easier if you print out the
              completed buffer for debugging. */
                buffer.append(line).append("\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            playerListJSONString = buffer.toString();

        } catch (Exception ex) {

            ex.printStackTrace();
            return "Connection failed!";
        } finally {

            // Close all connections opened.
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
            if (playerListJSONString != null) {
                Log.e(LOG_TAG, playerListJSONString);
                return playerListJSONString;
            } else {
                return "";
            }
        }
    }

    /**
     * Method queries specified URI.
     *
     * @param queryString what we're searching for
     * @return the results
     */
    static String getPlayerIDInfo(String queryString) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Stores results.
        String playerIDJSONString = null;

        try {
            //Build up your query URI.
            Uri builtURI = Uri.parse(PLAYER_ID_URL).buildUpon()
                    .appendPath(queryString)
                    .build();

            // Convert URI to URL
            URL requestURL = new URL(builtURI.toString());

            // Open connection and make request.
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the response.
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
           /* Since it's JSON, adding a newline isn't necessary (it won't affect
              parsing) but it does make debugging a *lot* easier if you print out the
              completed buffer for debugging. */
                buffer.append(line).append("\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            playerIDJSONString = buffer.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Connection failed!";
        } finally {

            // Close all connections opened.
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