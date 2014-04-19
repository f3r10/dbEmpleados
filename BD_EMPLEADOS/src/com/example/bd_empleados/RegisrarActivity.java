package com.example.bd_empleados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.servicios.ServiciosCargos;
import com.example.bd_vistas.MainActivity;

public class RegisrarActivity extends Activity {

	private ServiciosCargos servBDEmpleados;
	private EditText edtNombre;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar);
		setTitle("Nuevo Cargo");
		servBDEmpleados=new ServiciosCargos(this);
		edtNombre=(EditText)findViewById(R.id.Txtnombrecargo);
	}
	
	public void guardarNuevoCargo(View view){
		servBDEmpleados.abrirBD();
		Cargos carg=new Cargos();
		carg.setNombreCargo(edtNombre.getText().toString());
		
		try{
		servBDEmpleados.Insertar(carg);
		System.out.println("Valor ingresado correctamente\n");
		servBDEmpleados.cerrarBD();
		}
		catch(Exception e){
			System.out.println("No se ha podido guardar los datos");
			servBDEmpleados.cerrarBD();
		}
		
		try{
			edtNombre.setText(" ");
			System.out.println("Campos vaciados exitosamente");
		}
		catch(Exception e){
			System.out.println("Los campos no se han vaciado");
		}
	}
	
	public void registrarIrMenu(View view){
		Intent intent=new Intent(this, MainActivity.class);
		System.out.println("Ir a Registrar");
		startActivity(intent);
	}
	
	
	
	
	
	
}
