package net.vrgsoft.rollinglayoutmanager.logic.datalayer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GetDataDB {
    static final String DATABASE_NOME = "DB";


    public static final String TABELLA_REPO = "repo";
    public static final String KEY_REPO_ID = "id";
    public static final String KEY_REPO_IDGITHUB = "id_github";
    public static final String KEY_REPO_NAME = "name";
    public static final String KEY_REPO_LANGUAGE = "language";
    public static final String KEY_REPO_IDOWNER = "id_owner";
    public static final String KEY_REPO_NICK_OWNER = "nickname_owner";

    static final int DATABASE_VERSIONE = 2;

     static final String TABELLA_REPO_CREAZIONE = "CREATE TABLE repo (id integer primary key autoincrement, " +
            "name text not null, language text not null, id_github text not null, id_owner text not null, nickname_owner text not null);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public GetDataDB(Context c) {
        this.context = c;
        DBHelper = new DatabaseHelper(context);
    }

    public GetDataDB open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        DBHelper.close();
    }


    public long inserisciRepo(String name, String language, String id_github, String id_owner, String nickname)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_REPO_NAME, name);
        initialValues.put(KEY_REPO_LANGUAGE, language);
        initialValues.put(KEY_REPO_IDGITHUB, id_github);
        initialValues.put(KEY_REPO_IDOWNER, id_owner);
        initialValues.put(KEY_REPO_NICK_OWNER, nickname);
        return db.insert(TABELLA_REPO, null, initialValues);
    }

    public Cursor ottieniRepo(String nickname) throws SQLException
    {
        Cursor mCursore = db.query(true, TABELLA_REPO, new String[] {KEY_REPO_NAME, KEY_REPO_LANGUAGE, KEY_REPO_NICK_OWNER, KEY_REPO_IDGITHUB}, KEY_REPO_NICK_OWNER+ " = ?" , new String[] {nickname}, null, null, null, null);
        if (mCursore != null) {
            mCursore.moveToFirst();
        }
        return mCursore;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NOME, null, DATABASE_VERSIONE);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(TABELLA_REPO_CREAZIONE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABELLA_REPO);
            onCreate(db);
        }

    }


}
