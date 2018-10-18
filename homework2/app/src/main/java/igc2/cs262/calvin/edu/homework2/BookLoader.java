package igc2.cs262.calvin.edu.homework2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader {
    private String mQueryString;

    public BookLoader(@NonNull Context context, String query) {
        super(context);
        this.mQueryString = query;
     }

    @Override
    public String loadInBackground() {
        return NetworkUtils.getPlayerListInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }


}
