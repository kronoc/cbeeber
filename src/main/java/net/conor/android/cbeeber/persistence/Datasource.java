package net.conor.android.cbeeber.persistence;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import net.conor.android.cbeeber.model.Programme;

import java.util.ArrayList;

public class Datasource {
    private static final String DATABASE_NAME = "cbeeber";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;

    public Datasource(Context context) {
        ApplicationSQLiteOpenHelper applicationSQLiteOpenHelper = new ApplicationSQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        try {
            this.sqLiteDatabase = applicationSQLiteOpenHelper.getWritableDatabase();
        } catch (Exception exception) {
            Log.e("cbeeber", "Exception", exception);
        }
    }

    public long insert(Programme programme) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_PID, programme.getPid());
        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_TITLE, programme.getDisplayTitle());
        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_SUBTITLE, programme.getDisplaySubtitle());
        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_IMAGE_PID, programme.getImagePid());
        return this.sqLiteDatabase.insert(ApplicationSQLiteOpenHelper.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Programme> selectAll() {
        Cursor cursor = this.sqLiteDatabase.query(
                ApplicationSQLiteOpenHelper.TABLE_NAME,
                new String[]{
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_PID,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_TITLE,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_SUBTITLE,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_IMAGE_PID
                },
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        ArrayList<Programme> arrayList = new ArrayList<Programme>();
        while (!cursor.isAfterLast()) {
            Programme programme = new Programme();
            programme.setPid(cursor.getString(0));
            programme.setDisplayTitle(cursor.getString(1));
            programme.setDisplaySubtitle(cursor.getString(2));
            programme.setImagePid(cursor.getString(3));
            arrayList.add(programme);
            cursor.moveToNext();
        }
        return arrayList;
    }

    private class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {
        private static final String TABLE_NAME = "favourites";
        private static final String TABLE_COLUMN_ID = "ID";
        private static final String TABLE_COLUMN_PID = "PID";
        private static final String TABLE_COLUMN_TITLE = "TITLE";
        private static final String TABLE_COLUMN_SUBTITLE = "DESCRIPTION";
        private static final String TABLE_COLUMN_IMAGE_PID = "IMAGE_PID";

        public ApplicationSQLiteOpenHelper(Context context, String name, CursorFactory cursorFactory, int version) {
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                            TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            TABLE_COLUMN_PID + " TEXT, " +
                            TABLE_COLUMN_TITLE + " TEXT, " +
                            TABLE_COLUMN_SUBTITLE + " TEXT, " +
                            TABLE_COLUMN_IMAGE_PID + " TEXT " +
                            ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            this.onCreate(sqLiteDatabase);
        }
    }
}
