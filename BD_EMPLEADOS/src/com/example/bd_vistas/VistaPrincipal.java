package com.example.bd_vistas;

import com.example.bd_empleados.R;
import com.example.bd_empleados.RegisrarActivity;
import com.example.bd_empleados.R.layout;
import com.example.bd_empleados.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class VistaPrincipal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vista_principal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_principal, menu);
		return true;
	}
	
	public void IrServiciosCargos(View view){
		Intent intent=new Intent(VistaPrincipal.this,MainActivity.class);
		System.out.println("Ir a Servicios Cargos");
		startActivity(intent);
	}
	public void IrServiciosEmpleados(View view){
		Intent intent=new Intent(VistaPrincipal.this,EmpleadoActivity.class);
		System.out.println("Ir a Servicios Empleados");
		startActivity(intent);
	}

}
