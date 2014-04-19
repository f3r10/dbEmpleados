package com.example.bd_empleados;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.servicios.ServiciosCargos;
import com.example.bd_vistas.MainActivity;

public class ActualizarActivity extends Activity {
	private Cargos cargoRecuperado;
	private EditText edtNombreCargo;

	private ServiciosCargos servbdEmpleados;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar);
		//Bundle para pasar par�metros entre actividades
		Bundle extras=getIntent().getExtras();		
		int id=extras.getInt("idcargo");
				
		setTitle("Actualizar Cargos");
		cargoRecuperado=new Cargos();
		servbdEmpleados = new ServiciosCargos(this);
		System.out.println("Procedo a abrir la bd para actualizacion");

		servbdEmpleados.abrirBD();
		System.out.println("Base de datos abierta");

		Cursor c=servbdEmpleados.recuperarCargo(id);
		// int id=c.getInt(0);
		// String nombrec=c.getString(1);
		//if (c.moveToFirst())
		//if (c.moveToFirst())
		
		if(c!=null)
		{

			System.out.println("Ingresando en el if luego de recuperar cargo, significa c!=null ");

			cargoRecuperado.setIdCargo(c.getInt(0));
			cargoRecuperado.setNombreCargo(c.getString(1));

			Toast.makeText(this,
			"id: " + cargoRecuperado.getIdCargo() + "\n" +
			"Nombre Cargo: " + cargoRecuperado.getNombreCargo() + "\n",
			Toast.LENGTH_LONG).show();

			System.out.println(
			"id: " + cargoRecuperado.getIdCargo() + "\n" +
			"Nombre Cargo: " + cargoRecuperado.getNombreCargo()+ "\n");

			edtNombreCargo=(EditText)findViewById(R.id.editTextNombre);
			edtNombreCargo.setText(cargoRecuperado.getNombreCargo());
			/*
			Intent intent = new Intent(this, ListarActivity.class);
			System.out.println("Ir a la vista.");
			startActivity(intent);*/
			}
		else
		{
		Toast.makeText(this, "El contacto no existe", Toast.LENGTH_LONG).show();

		}
		System.out.println("A continuaci�n se cerrar� la BD en actualizai�n");
		servbdEmpleados.cerrarBD();
	}
	
	public void actualizarCargo(View view){
		servbdEmpleados.abrirBD();
		cargoRecuperado.setNombreCargo(edtNombreCargo.getText().toString());

		servbdEmpleados.actualizar(cargoRecuperado);
		servbdEmpleados.cerrarBD();
	}

	public void actualizarIrLista(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		System.out.println("Ir al Menu.");
		startActivity(intent);
	}


}
