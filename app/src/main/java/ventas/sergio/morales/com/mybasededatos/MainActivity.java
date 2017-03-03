package ventas.sergio.morales.com.mybasededatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button actualizar, borrar, insertar, consultar;
    //SQLiteDatabase db;
    EditText editText1;
    TextView TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos la base de datos en Modo Escritura
        BDAlumnos usdbh = new BDAlumnos(this, "BDAlumnos", null, 2); //EL DOS SIGNIFICA LA VERSION DE LA BD
        final SQLiteDatabase db = usdbh.getWritableDatabase();

        //insertar datos automaticamente;
        if (db != null) {
            for (int i = 1; i < 10; i++) {
                int codigo = 1;
                String NombreAlumno = "Alumno" + i;
                String DireccionAlumno = "DirAlumno" + i;
                //SINTAXIS PA RA LA PRIMERA VERSION DE LA BASE DE DATOS
                //db.execSQL("INSERT INTO Alumnos(codigo,nombre)  " + " VALUES (" + codigo + " ,'"+ NombreAlumno+"')");
                //SINTAXIS PARA LA 2DA VERSION DE LA BASE DE DATOS
                //db.execSQL("INSERT INTO Alumnos (nombre)  " + "VALUES ('" + NombreAlumno + "')");
                db.execSQL("INSERT INTO Alumnos (codigo,nombre,direccion)  " +
                        "VALUES (" + codigo +" , '" + NombreAlumno + "', '" + DireccionAlumno + "')");
            }
            //SE CIERRRA LA BD
            db.close();
        }

        actualizar = (Button) findViewById(R.id.button3);
        insertar = (Button) findViewById(R.id.button1);
        borrar = (Button) findViewById(R.id.button2);
        consultar = (Button) findViewById(R.id.button4);
        TextView = (TextView) findViewById(R.id.textView);


        actualizar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v){
              db.execSQL("UPDATE Alumnos set nombre='" + editText1.getText() + "' WHERE codigo=5)");
              Toast.makeText(getApplicationContext(), "Has Pulsado El boton Actualizar", Toast.LENGTH_SHORT).show();
          }
         });

       borrar.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
          db.execSQL("DELETE FROM Alumnos WHERE nombre='" + editText1.getText() + "'");
          Toast.makeText(getApplicationContext(), "Has Pulsado El boton Borrar", Toast.LENGTH_SHORT).show();
          }
        });


       insertar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              db.execSQL("INSERT INTO Alumnos (nombre,direccion)  VALUES ('" + editText1.getText() + "' , ' DIRECCION DE PRUEBA ') ");
              Toast.makeText(getApplicationContext(), "Has Pulsado El boton Insertar", Toast.LENGTH_SHORT).show();
          }
       });


      consultar.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
       String[] args = new String[]{editText1.getText().toString()};
       Cursor c = db.rawQuery("SELECT * FROM Alumnos WHERE nombre=?" ,args);
        if (c.moveToFirst()) {
                TextView.setText("");
                do {
                Integer codigo = c.getInt(0);
                String nombre = c.getString(1);
                String direccion = c.getString(2);
                TextView.append("Codigo" + codigo + "Nombre" + nombre + "direccion" + direccion + "\n");
                } while (c.moveToNext());
            }
             Toast.makeText(getApplicationContext(), "Has Pulsado El boton consultar", Toast.LENGTH_SHORT).show();
           }
        });
     }
}

