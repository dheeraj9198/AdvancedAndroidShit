package db;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.android.AndroidDatabaseResults;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: dheeraj
 * Date: 18/10/13
 * Time: 3:48 PM
 */

//make it a singleton
public class DbHandler {

    private static final String TAG = DbHandler.class.getSimpleName();
    private static final int maximumImageCount = 100;
    private volatile static int currentImageCount = 0;

    private static final Long LIMIT_ONE = 1L;
    private static final Long ACCESS_LOG_LIMIT = 20L;

    private static DbHandler dbHandler;

    private DatabaseHelper databaseHelper = null;
    private Context context;

    private DbHandler(DatabaseHelper databaseHelper, Context context) {
        this.databaseHelper = databaseHelper;
        this.context = context;
    }

    public static void start(Context context) {
        if (dbHandler == null) {
            synchronized (DbHandler.class) {
                if (dbHandler == null) {
                    synchronized (DbHandler.class) {
                        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
                        dbHandler = new DbHandler(databaseHelper, context);
                    }
                }
            }
        }
    }

    public static boolean isStarted() {
        return dbHandler != null;
    }

    public static DbHandler getDbHandler() {
        if (dbHandler != null) {
            return dbHandler;
        }
        throw new RuntimeException("null db handler, start method not called yet");
    }


    public boolean saveTable(Table table) {
        try {
            boolean result = databaseHelper.getTableLongDao().createOrUpdate(table).getNumLinesChanged() == 1;
            return result;
        } catch (Exception e) {
            Log.e(TAG, "caught exception while creating-updating subject subscription validity", e);
            return false;
        }
    }

    public Cursor getTableCursor() {
        try {
            QueryBuilder<Table, Long> tableLongQueryBuilder = databaseHelper.getTableLongDao().queryBuilder();
            tableLongQueryBuilder.where();
            CloseableIterator<Table> iterator = databaseHelper.getTableLongDao().iterator(tableLongQueryBuilder.prepare());
            AndroidDatabaseResults results = (AndroidDatabaseResults) iterator.getRawResults();
            Cursor cursor = results.getRawCursor();
            return cursor;
        } catch (Exception e) {
            Log.e(TAG, "caught exception", e);
            return null;
        }
    }

}
