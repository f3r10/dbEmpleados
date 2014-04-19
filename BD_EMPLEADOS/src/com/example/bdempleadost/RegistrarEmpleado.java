package com.example.bdempleadost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bd_empleados.R;
import com.example.bd_empleados.RegisrarActivity;
import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.entidades.Empleados;
import com.example.bd_empleados.servicios.ServiciosCargos;
import com.example.bd_empleados.servicios.ServiciosEmpleados;
import com.example.bd_vistas.EmpleadoActivity;
import com.example.bd_vistas.MainActivity;

public class RegistrarEmpleado extends Activity implements OnItemSelectedListener{

	private ServiciosEmpleados servBDEmpleados;
	private ServiciosCargos servcargos;
	private EditText edtNombreEmpleado;
	private EditText edtDireccionEmpleado;
	private EditText edtSueldoEmpleado;
	private String nombreCargo;
	Spinner spinner;
	int idCargo;
	int prueba;
	Map<String,Integer> claveValorCargos = new HashMap<String, Integer>();
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar_empleado);
		setTitle("Nuevo Empleado");
		servBDEmpleados=new ServiciosEmpleados(this);
		edtNombreEmpleado=(EditText)findViewById(R.id.Txtnombreempleado);
		edtDireccionEmpleado=(EditText)findViewById(R.id.Txtdireccion);
		edtSueldoEmpleado=(EditText)findViewById(R.id.Txtsueldo);
		servcargos=new ServiciosCargos(this);
		spinner=(Spinner)findViewById(R.id.Spncargos);
		loadSpinnerData();
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView,
			View selectedItemView, int position, long id) {
			idCargo = claveValorCargos.get(parentView.getItemAtPosition(position).toString());
			nombreCargo = parentView.getItemAtPosition(position).toString();
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			}
			});
	}
	
	public void guardarNuevoEmpleado(View view){
		servBDEmpleados.abrirBD();
		Empleados emp=new Empleados();
		emp.setNombreEmpleado(edtNombreEmpleado.getText().toString());
		emp.setDireccionEmpleado(edtDireccionEmpleado.getText().toString());
		emp.setSueldoEmpleado(Float.parseFloat(edtSueldoEmpleado.getText().toString()));
		emp.setIdCargo(idCargo);
		emp.setNombreCargo(nombreCargo);
		Log.w("registro empleado", emp.getNombreCargo());
		try{
		servBDEmpleados.Insertar(emp);
		System.out.println("Valor ingresado correctamente\n");
		Toast.makeText(this, prueba, Toast.LENGTH_LONG).show();
		servBDEmpleados.cerrarBD();
		}
		catch(Exception e){
			System.out.println("No se ha podido guardar los datos");
			servBDEmpleados.cerrarBD();
		}		
		try{			
			edtNombreEmpleado.setText("");
			edtDireccionEmpleado.setText("");
			edtSueldoEmpleado.setText("");
			System.out.println("Campos vaciados exitosamente");
			System.out.println(prueba);
		}
		catch(Exception e){
			System.out.println("Los campos no se han vaciado");
		}
	}
		
	private void loadSpinnerData() {             
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
        spinner.setAdapter(dataAdapter);
    }

	@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
 
        // Showing selected spinner item
        System.out.println(label);
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
    
    public void registrarIrMenu(View view){
		Intent intent=new Intent(this,EmpleadoActivity.class);
		System.out.println("Ir a Registrar");
		startActivity(intent);
	}
}
