package com.example.bdempleadost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bd_empleados.R;
import com.example.bd_empleados.R.id;
import com.example.bd_empleados.R.layout;
import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.entidades.Empleados;
import com.example.bd_empleados.servicios.ServiciosCargos;
import com.example.bd_empleados.servicios.ServiciosEmpleados;
import com.example.bd_vistas.EmpleadoActivity;
import com.example.bd_vistas.MainActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ActualizarEmpleadoActivity extends Activity implements OnItemSelectedListener {

	private Empleados empleadoRecuperado;
	private ServiciosCargos servcargos;
	private EditText edtNombreEmpleado;
	private EditText edtDireccionEmpleado;
	private EditText edtSueldoEmpleado;
	private TextView edtCargo;
	private TextView edtIdEmpleado;
	Spinner spinnerCargoNuevo;
	int idCargo;
	Map<String,Integer> claveValorCargos = new HashMap<String, Integer>();


	private ServiciosEmpleados servbdEmpleados;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_empleado);
		//Bundle para pasar par�metros entre actividades
		Bundle extras=getIntent().getExtras();		
		int idEmpleado=extras.getInt("idEmpleado");
		setTitle("Actualizar Empleado");
		empleadoRecuperado=new Empleados();
		servbdEmpleados = new ServiciosEmpleados(this);
		servcargos=new ServiciosCargos(this);
		System.out.println("Procedo a abrir la bd para actualizacion");
		servbdEmpleados.abrirBD();
		System.out.println("Base de datos abierta");

		Cursor c=servbdEmpleados.recuperarEmpleado(idEmpleado);
		// int id=c.getInt(0);
		// String nombrec=c.getString(1);
		//if (c.moveToFirst())
		//if (c.moveToFirst())
		
		if(c!=null)
		{

			System.out.println("Ingresando en el if luego de recuperar cargo, significa c!=null ");
			empleadoRecuperado.setIdEmpleado(c.getInt(0));
			empleadoRecuperado.setNombreEmpleado(c.getString(1));
			empleadoRecuperado.setDireccionEmpleado(c.getString(2));
			empleadoRecuperado.setSueldoEmpleado(c.getFloat(3));
			empleadoRecuperado.setNombreCargo(c.getString(4));
			empleadoRecuperado.setIdCargo(c.getInt(5));

			Toast.makeText(this,
			"id: " + empleadoRecuperado.getIdEmpleado() + "\n" +
			"Nombre Cargo: " + empleadoRecuperado.getNombreCargo() + "\n",
			Toast.LENGTH_LONG).show();
			System.out.println(
			"id: " + empleadoRecuperado.getIdEmpleado() + "\n" +
			"Nombre Cargo: " + empleadoRecuperado.getNombreCargo()+ "\n");		
			edtNombreEmpleado=(EditText)findViewById(R.id.editTextNombre);
			edtNombreEmpleado.setText(empleadoRecuperado.getNombreEmpleado());
			
			edtDireccionEmpleado= (EditText)findViewById(R.id.editTextDireccion);
			edtDireccionEmpleado.setText(empleadoRecuperado.getDireccionEmpleado());
			
			edtSueldoEmpleado = (EditText)findViewById(R.id.editTextSuledo);
			edtSueldoEmpleado.setText(empleadoRecuperado.getSueldoEmpleado().toString());
			
			edtCargo = (TextView)findViewById(R.id.textViewNombreCargo);
			edtCargo.setText(empleadoRecuperado.getNombreCargo());
		
			
			/*
			Intent intent = new Intent(this, ListarActivity.class);
			System.out.println("Ir a la vista.");
			startActivity(intent);*/
			}
		else
		{
		Toast.makeText(this, "El contacto no existe", Toast.LENGTH_LONG).show();

		}
		System.out.println("A continuacion se cerrara la BD en actualizaion");
		servbdEmpleados.cerrarBD();
		
		spinnerCargoNuevo=(Spinner)findViewById(R.id.spinnerNuevoCargo);
		loadSpinnerData();
		spinnerCargoNuevo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView,
			View selectedItemView, int position, long id) {
			idCargo = claveValorCargos.get(parentView.getItemAtPosition(position).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			}
			});
		
	}
	
	public void actualizarEmpleado(View view){
		Log.w("Pruebaa", "Entrar a actualizar");
		servbdEmpleados.abrirBD();
		empleadoRecuperado.setNombreEmpleado(edtNombreEmpleado.getText().toString());
		empleadoRecuperado.setDireccionEmpleado(edtDireccionEmpleado.getText().toString());
		empleadoRecuperado.setSueldoEmpleado(Float.parseFloat(edtSueldoEmpleado.getText().toString()));
		empleadoRecuperado.setIdCargo(idCargo);
		servbdEmpleados.actualizar(empleadoRecuperado);
		servbdEmpleados.cerrarBD();
		actualizarIrLista(view);
	}

	public void actualizarIrLista(View view) {
		Intent intent = new Intent(this, EmpleadoActivity.class);
		System.out.println("Ir al Menu.");
		startActivity(intent);
	}
	
	private void loadSpinnerData() {   
		Log.w("loadSpinner", "entra");
		servcargos.abrirBD(); 
        // Spinner Drop down elements
        List<Cargos> lables = servcargos.recuperarTodos();
        servcargos.cerrarBD();
        
        List<String>listanombres=new ArrayList<String>();
        
        for (int i = 0; i < lables.size(); i++) {
			listanombres.add(lables.get(i).getNombreCargo());
			claveValorCargos.put(lables.get(i).getNombreCargo(), lables.get(i).getIdCargo());
			
		}
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listanombres);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerCargoNuevo.setAdapter(dataAdapter);
    }

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		 String label = parent.getItemAtPosition(position).toString();
		 
	        // Showing selected spinner item
	        System.out.println(label);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
