package igc2.cs262.calvin.edu.homework02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * Class acts as intermediary between NetworkUtils.java and MainActivity.java
 * <p>
 * Specifies necessary operations involving AsyncTaskLoader for the Player Table.
 */
class PlayerLoader extends AsyncTaskLoader<String> {

    private static final String LOG_TAG = PlayerLoader.class.getSimpleName();

    private final String mQueryString;

    /**
     * Constructor
     *
     * @param context application context.
     */
    public PlayerLoader(@NonNull Context context, String query) {
        super(context);

        this.mQueryString = query;
    }

    /**
     * Method called when starting the loader.
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        // Starts the loadInBackground() method.
        forceLoad();
    }

    /**
     * Method performs task in background.
     *
     * @return query results.
     */
    @Nullable
    @Override
    public String loadInBackground() {

        // Call method to query specified URI.
        // Based on whether string is empty or contains a positive integer value.
        if (mQueryString.length() == 0) {
            Log.e(LOG_TAG, "getPlayerListInfo called!");
            return NetworkUtils.getPlayerListInfo(mQueryString);
        } else {
            Log.e(LOG_TAG, "getPlayerIDInfo called!");
            return NetworkUtils.getPlayerIDInfo(mQueryString);
        }
    }
}