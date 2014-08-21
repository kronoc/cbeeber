package net.conor.android.cbeeber;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import net.conor.android.cbeeber.model.Programme;

import java.util.ArrayList;

//TODO table definition
public class Datasource
{
    private static final String DATABASE_NAME = "cbeeber";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;

    public Datasource(Context context)
    {
        ApplicationSQLiteOpenHelper applicationSQLiteOpenHelper = new ApplicationSQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        try
        {
            this.sqLiteDatabase = applicationSQLiteOpenHelper.getWritableDatabase();
        }
        catch(Exception exception)
        {
            Log.e("cbeeber", "Exception", exception);
        }
    }

    public long insert(Programme programme)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_UID, "foo");
//        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_TITLE, scheduleItem.getTitle());
//        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_DESCRIPTION, scheduleItem.getDescription());
//        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_GUID, scheduleItem.getGuid());
//        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_PUBDATE, scheduleItem.getPubDate());
//        contentValues.put(ApplicationSQLiteOpenHelper.TABLE_COLUMN_THUMBNAIL, scheduleItem.getThumbnail());
        return this.sqLiteDatabase.insert(ApplicationSQLiteOpenHelper.TABLE_NAME, null, contentValues);
    }

    public ArrayList<Programme> selectAll()
    {
        Cursor cursor = this.sqLiteDatabase.query(
                ApplicationSQLiteOpenHelper.TABLE_NAME,
                new String[] {
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_UID,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_TITLE,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_DESCRIPTION,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_GUID,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_PUBDATE,
                        ApplicationSQLiteOpenHelper.TABLE_COLUMN_THUMBNAIL
                },
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        ArrayList<Programme> arrayList = new ArrayList<Programme>();
        while(!cursor.isAfterLast())
        {
            Programme ScheduleItem = new Programme();
//            ScheduleItem.setUid(cursor.getString(0));
//            ScheduleItem.setTitle(cursor.getString(1));
//            ScheduleItem.setDescription(cursor.getString(2));
//            ScheduleItem.setGuid(cursor.getString(3));
//            ScheduleItem.setPubDate(cursor.getString(4));
//            ScheduleItem.setThumbnail(cursor.getString(5));
            arrayList.add(ScheduleItem);
            cursor.moveToNext();
        }
        return arrayList;
    }

    private class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper
    {
        private static final String TABLE_NAME = "ScheduleItem";
        private static final String TABLE_COLUMN_ID = "ID";
        private static final String TABLE_COLUMN_UID = "UID";
        private static final String TABLE_COLUMN_TITLE = "TITLE";
        private static final String TABLE_COLUMN_DESCRIPTION = "DESCRIPTION";
        private static final String TABLE_COLUMN_GUID = "GUID";
        private static final String TABLE_COLUMN_PUBDATE = "PUBDATE";
        private static final String TABLE_COLUMN_THUMBNAIL = "THUMBNAIL";

        public ApplicationSQLiteOpenHelper(Context context, String name, CursorFactory cursorFactory, int version)
        {
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                            TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            TABLE_COLUMN_UID + " TEXT, " +
                            TABLE_COLUMN_TITLE + " TEXT, " +
                            TABLE_COLUMN_DESCRIPTION + " TEXT, " +
                            TABLE_COLUMN_GUID + " TEXT, " +
                            TABLE_COLUMN_PUBDATE + " TEXT, " +
                            TABLE_COLUMN_THUMBNAIL + " TEXT " +
                            ")"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            this.onCreate(sqLiteDatabase);
        }
    }
}
