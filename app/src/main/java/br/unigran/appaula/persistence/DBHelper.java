package br.unigran.appaula.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "dbAula", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuffer sql = new StringBuffer();
        sql.append("create table pessoa(" +
                "id integer primary key," +
                "nome varchar(100)," +
                "idade integer" +
                ");");

        sqLiteDatabase.execSQL(sql.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("alter table pessoa add column sexo integer;");
    }
}
