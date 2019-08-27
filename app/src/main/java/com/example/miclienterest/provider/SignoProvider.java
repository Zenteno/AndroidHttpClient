package com.example.miclienterest.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.example.miclienterest.DaoMaster;
import com.example.miclienterest.DaoSession;
import com.example.miclienterest.SignoDao;

import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

public class SignoProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.miclienterest";
    public static final String BASE_PATH = "Signo";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/" + BASE_PATH;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/" + BASE_PATH;

    private static final String TABLENAME = SignoDao.TABLENAME;
    private static final String PK = SignoDao.Properties.Id.columnName;

    private static final int SIMPLEENTITY_DIR = 0;
    private static final int SIMPLEENTITY_ID = 1;

    private static final UriMatcher sURIMatcher;

    static {
        sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, SIMPLEENTITY_DIR);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", SIMPLEENTITY_ID);
    }

    public static DaoSession daoSession;

    @Override
    public boolean onCreate() {
        //if(daoSession == null) {
         //    throw new IllegalStateException("DaoSession must be set before content provider is created");
        //}
        DaoLog.d("Content Provider started: " + CONTENT_URI);
        return true;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("This content provider is readonly");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("This content provider is readonly");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("This content provider is readonly");
    }

    @Override
    public final String getType(Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case SIMPLEENTITY_DIR:
                return CONTENT_TYPE;
            case SIMPLEENTITY_ID:
                return CONTENT_ITEM_TYPE;
            default :
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType = sURIMatcher.match(uri);
        /*switch (uriType) {
            case SIMPLEENTITY_DIR:
                queryBuilder.setTables(TABLENAME);
                break;
            case SIMPLEENTITY_ID:
                queryBuilder.setTables(TABLENAME);
                queryBuilder.appendWhere(PK + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }*/
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("SIGNO");

        Cursor d = daoSession.getSignoDao().queryBuilder().buildCursor().query();
        return d;
    }
}
