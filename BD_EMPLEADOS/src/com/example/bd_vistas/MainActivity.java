package com.example.bd_vistas;

import com.example.bd_empleados.BorrarActivity;
import com.example.bd_empleados.ListarActivid;
import com.example.bd_empleados.ListarActivity;
import com.example.bd_empleados.R;
import com.example.bd_empleados.RegisrarActivity;
import com.example.bd_empleados.R.layout;
import com.example.bd_empleados.R.menu;
import com.example.bd_empleados.servicios.ServiciosCargos;
import com.example.bdempleadost.ListarEmpleadoActivity;
import com.example.bdempleadost.ListarParaActualizarActivity;
import com.example.bdempleadost.RegistrarEmpleado;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private ServiciosCargos servcargo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("CARGOS");
		servcargo=new ServiciosCargos(this);
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void menuirRegistrar(View view){
		Intent intent=new Intent(MainActivity.this,RegisrarActivity.class);
		System.out.println("Ir a Registrar");
		startActivity(intent);
	}
	
	public void menuIrListar(View view) {
		Intent intent = new Intent(MainActivity.this, ListarActivity.class);
		System.out.println("Ir a Listar.");
		startActivity(intent);
	}
	
	public void menuIrActualizar(View view) {
		Intent intent = new Intent(this, ListarActivid.class);
		System.out.println("Ir a Actualizar.");
		startActivity(intent);
		}
	
	public void menuIrBorrar(View view) {
		Intent intent = new Intent(this, BorrarActivity.class);
		System.out.println("Ir a Borrar.");
		startActivity(intent);

		}
	
	public void irMenuPrincipal(View view) {
		Intent intent = new Intent(this, VistaPrincipal.class);
		System.out.println("Ir a Borrar.");
		startActivity(intent);

		}
}
