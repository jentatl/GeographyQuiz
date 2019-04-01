package edu.uga.cs.geographyquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String LOG = "DatabaseHelper";

    // database name
    private static final String DATABASE_NAME = "geographyQuizDB.db";

    // table names
    public static final String TABLE_COUNTRIES = "Countries";
    public static final String TABLE_QUIZZES = "Quizzes";

    // country columns
    private static final String KEY_COUNTRIES_ID = "countries_id";
    private static final String KEY_COUNTRIES_NAME = "country_name";
    private static final String KEY_COUNTRY_CONTINENT = "continent";

    // quiz columns
    private static final String KEY_QUIZZES_ID = "quiz_id";
    private static final String KEY_QUIZ_SCORE = "score";
    private static final String KEY_QUIZ_DATE = "date";
    private static final String KEY_QUIZ_COUNTRIES = "quiz_countries";

    public DatabaseHandler(Context context, String name,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    } // constructor

    public void onCreate(SQLiteDatabase db){
        String CREATE_COUNTRIES = "CREATE TABLE " + TABLE_COUNTRIES +
                "(" +  KEY_COUNTRIES_ID + "INTEGER PRIMARY KEY," + KEY_COUNTRIES_NAME
                + "TEXT," + KEY_COUNTRY_CONTINENT + "TEXT )";

        String CREATE_QUIZZES = "CREATE TABLE " + TABLE_QUIZZES +
                "(" +  KEY_QUIZZES_ID + "INTEGER PRIMARY KEY," + KEY_QUIZ_DATE + "TEXT"
                + KEY_QUIZ_SCORE + "INTEGER," + KEY_QUIZ_COUNTRIES + "TEXT )";

        db.execSQL(CREATE_COUNTRIES);
        db.execSQL(CREATE_QUIZZES);
    } // on create

    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZZES);

        onCreate(db);
    } // on upgrade

    public String loadCountryHandler(){
        String result = "";
        String query = "Select*FROM " + TABLE_COUNTRIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    } // load country handler

    public String loadQuizHandler(){
        String result = "";
        String query = "Select*FROM " + TABLE_QUIZZES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        while(cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    } // load quiz handler

    public void addHandler(Country country){
        ContentValues values = new ContentValues();
        values.put(KEY_COUNTRIES_ID, country.getId());
        values.put(KEY_COUNTRIES_NAME, country.getCountryName());
        values.put(KEY_COUNTRY_CONTINENT, country.getContinent());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_COUNTRIES, null, values);
        db.close();
    }// country handler

    public void addHandler(Quiz quiz){
        ContentValues values = new ContentValues();
        values.put(KEY_QUIZZES_ID, quiz.getId());
        values.put(KEY_QUIZ_SCORE, quiz.getScore());
        values.put(KEY_QUIZ_DATE, quiz.getDate());
        values.put(KEY_QUIZ_COUNTRIES, quiz.getCountries());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_QUIZZES, null, values);
        db.close();
    } // quiz handler

    public Country findHandler(String countryName){
        String query = "Select * FROM " + TABLE_COUNTRIES + "WHERE" + KEY_COUNTRIES_NAME +
                " = " + "'" + countryName + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Country country = new Country();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            country.setId(Integer.parseInt(cursor.getString(0)));
            country.setCountryName(cursor.getString(1));
            country.setContinent(cursor.getString(2));
            cursor.close();
        }else{
            country = null;
        }
        db.close();
        return country;
    } // find country

    public Quiz findHandler(int quizNum){
        String query = "Select * FROM " + TABLE_QUIZZES+ "WHERE" + KEY_QUIZZES_ID +
                " = " + "'" + quizNum + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Quiz  quiz = new Quiz();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            quiz.setId(Integer.parseInt(cursor.getString(0)));
            quiz.setDate(cursor.getString(1));
            quiz.setScore(Integer.parseInt(cursor.getString(2)));
            quiz.setCountries(cursor.getString(3));
            cursor.close();
        }else{
            quiz = null;
        }
        db.close();
        return quiz;
    } // find quiz

    public boolean deleteCountryHandler(int id){
        boolean result = false;
        String query = "Select * FROM " + TABLE_COUNTRIES + "WHERE" + KEY_COUNTRIES_ID
                + "= '" + String.valueOf(id) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Country country = new Country();

        if(cursor.moveToFirst()){
            String newString[] = new String[1];
            newString[1] = String.valueOf(country.getId());

            country.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_COUNTRIES, KEY_COUNTRIES_ID + "=?",
                    new String[]{newString[1]});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    } // deleteCountryHandler

    public boolean deleteQuizHandler(int id){
        boolean result = false;
        String query = "Select * FROM " + TABLE_QUIZZES + "WHERE" + KEY_QUIZZES_ID
                + "= '" + String.valueOf(id) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Quiz quiz = new Quiz();

        if(cursor.moveToFirst()){
            String newString[] = new String[1];
            newString[1] = String.valueOf(quiz.getId());

            quiz.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_QUIZZES, KEY_QUIZZES_ID + "=?",
                    new String[]{newString[1]});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    } // delete quiz

    public boolean updateCountryHandler(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KEY_COUNTRIES_ID, id);
        args.put(KEY_COUNTRIES_NAME, name);
        return db.update(TABLE_COUNTRIES, args, KEY_COUNTRIES_ID + "="
                + id, null) > 0;
    } // country

    public boolean updateQuizyHandler(int id, int score, String date, String countries){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KEY_QUIZZES_ID, id);
        args.put(KEY_QUIZ_SCORE, score);
        args.put(KEY_QUIZ_DATE, date);
        args.put(KEY_QUIZ_COUNTRIES, countries);
        return db.update(TABLE_QUIZZES, args, KEY_QUIZZES_ID + "="
                + id, null) > 0;
    } // quiz
} // dataabase handler class
