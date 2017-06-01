package com.popcode.madnilo.starwiki.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.popcode.madnilo.starwiki.model.People;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danilo Lima on 30/05/2017.
 *
 * Classe ainda não utilizada.
 */

public class PeopleDAO extends SQLiteOpenHelper {
    public PeopleDAO(Context context) {
        super(context, "starwiki_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE People (id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "height TEXT, " +
                "mass TEXT, " +
                "hair_color TEXT, " +
                "skin_color TEXT, " +
                "eye_color TEXT, " +
                "birth_year TEXT, " +
                "gender TEXT, " +
                "homeworld TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS People";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(People person) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("name", person.getName());
        data.put("height", person.getHeight());
        data.put("mass", person.getMass());
        data.put("hair_color", person.getHair_color());
        data.put("skin_color", person.getSkin_color());
        data.put("eye_color", person.getEye_color());
        data.put("birth_year", person.getBirth_year());
        data.put("gender", person.getGender());
        data.put("homeworld", person.getHomeworld());

        db.insert("People", null, data);
    }

    public List<People> getAll() {
        String sql = "SELECT * from People;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<People> people = new ArrayList<>();
        while(c.moveToNext()){
            People person = new People();
            //person.setId(c.getLong(c.getColumnIndex("id")));
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setHeight(c.getString(c.getColumnIndex("height")));
            person.setMass(c.getString(c.getColumnIndex("mass")));
            person.setHair_color(c.getString(c.getColumnIndex("hair_color")));
            person.setSkin_color(c.getString(c.getColumnIndex("skin_color")));
            person.setEye_color(c.getString(c.getColumnIndex("eye_color")));
            person.setBirth_year(c.getString(c.getColumnIndex("birth_year")));
            person.setGender(c.getString(c.getColumnIndex("gender")));
            person.setHomeworld(c.getString(c.getColumnIndex("homeworld")));

            people.add(person);
        }
        c.close();
        return people;
    }
}
