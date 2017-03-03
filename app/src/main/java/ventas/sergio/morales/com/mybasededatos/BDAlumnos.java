package ventas.sergio.morales.com.mybasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by sergio morales on 01/03/2017.
 */
    public class BDAlumnos extends SQLiteOpenHelper{
        //CREANDO LA CADENA QUE CREARA LA BASE DE DATOS CON 3 CAMPOS
        String sqlCreate  ="CREATE TABLE Alumnos (codigo INTEGER,nombre TEXT)";
        String sqlCreate2 ="CREATE TABLE Alumnos (codigo INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT,direccion TEXT)";

    public BDAlumnos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SE EJECUTA AL MOMENTO DE LLAMAR LA BASE DE DATOS OSE CUANO ES LA PRIMER A VEZ
        //SE CONSTRUYE POR PRIMERA VEZ LA BASE DE DATOS
        db.execSQL(sqlCreate); //ejecuta la instruccion para crear base de datpos
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST Alumnos");
        db.execSQL(sqlCreate2);
    }
}
