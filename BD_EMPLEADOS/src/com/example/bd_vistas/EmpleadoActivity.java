package com.example.bd_vistas;

import com.example.bd_empleados.R;
import com.example.bd_empleados.R.layout;
import com.example.bd_empleados.R.menu;
import com.example.bdempleadost.BorrarEmpleadoActivity;
import com.example.bdempleadost.ListarEmpleadoActivity;
import com.example.bdempleadost.ListarParaActualizarActivity;
import com.example.bdempleadost.RegistrarEmpleado;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class EmpleadoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_empleado);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.empleado, menu);
		return true;
	}
	
	public void menuIrRegistrarEmpleado(View view) {
		Intent intent = new Intent(this, RegistrarEmpleado.class);
		System.out.println("Ir a Registrar Empleado");
		startActivity(intent);

		}
	public void menuIrListarEmpleado(View view) {
		Intent intent = new Intent(this, ListarEmpleadoActivity.class);
		System.out.println("Ir a Registrar Empleado");
		startActivity(intent);

		}
	
	public void menuIrActualizarEmpleado(View view) {
		Intent intent = new Intent(this, ListarParaActualizarActivity.class);
		System.out.println("Ir a Registrar Empleado");
		startActivity(intent);

		}
	public void menuIrBorrarEmpleado (View view)
	{
		Intent intent = new Intent(this, BorrarEmpleadoActivity.class);
		System.out.println("Ir a Registrar Empleado");
		startActivity(intent);
	}
	
	public void irMenuPrincipal (View view)
	{
		Intent intent = new Intent(this, VistaPrincipal.class);
		System.out.println("Ir a Registrar Empleado");
		startActivity(intent);
	}

}
