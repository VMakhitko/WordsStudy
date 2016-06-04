package com.study.wordsstudy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.wordsstudy.Word;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vmakhitko on 04.06.16.
 */

public class DbHandler extends SQLiteOpenHelper implements IDbHandler {
    private static String TAG = DbHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wordsStudy";
    private static final String TABLE_WORDS = "words";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "word";
    private static final String KEY_TRANSLATE = "translate";
    private static final String KEY_REPEATE_DATE = "repeate_date";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_WORD + " TEXT,"
                + KEY_TRANSLATE + " TEXT,"
                + KEY_REPEATE_DATE + " INTEGER" + ")";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_WORDS);

        onCreate(db);
    }

    @Override
    public void addWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word.getWord());
        values.put(KEY_TRANSLATE, word.getTranslate());
        values.put(KEY_REPEATE_DATE, word.getRepeateDate().getTime());

        db.insert(TABLE_WORDS, null, values);
        db.close();
    }

    @Override
    public Word getWord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_WORDS, new String[] {KEY_ID,
                KEY_WORD, KEY_TRANSLATE, KEY_REPEATE_DATE}, KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
                cursor.moveToFirst();
        }
        Word word;
        try {
            word = new Word(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    new Date(Long.parseLong(cursor.getString(3))));
        } catch (CursorIndexOutOfBoundsException e) {
            e.getStackTrace();
            return null;
        }

        return word;
    }

    @Override
    public List<Word> getAllWords() {
        List<Word> wordsList = new ArrayList<Word>();
        String query = "SELECT * FROM " + TABLE_WORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Word word = new Word(Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            new Date(Long.parseLong(cursor.getString(3))));
                    wordsList.add(word);
                } while (cursor.moveToNext());
            }
        } catch (CursorIndexOutOfBoundsException e) {
            e.getStackTrace();
            return null;
        }

        return wordsList;
    }

    @Override
    public List<Word> getWordsForToday() {
        //TODO:
        return null;
    }

    @Override
    public int getWordsCount() {
        String query = "SELECT * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        db.close();

        return cursor.getCount();
    }

    @Override
    public int updateWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word.getWord());
        values.put(KEY_TRANSLATE, word.getTranslate());
        values.put(KEY_REPEATE_DATE, word.getRepeateDate().getTime());

        return db.update(TABLE_WORDS, values, KEY_ID + "=?",
                new String[] {String.valueOf(word.getId())});
    }

    @Override
    public void deleteWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, KEY_ID + "=?",
                new String[] {String.valueOf(word.getId())});
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, null, null);
        db.close();
    }
}
