package com.example.bdempleadost;

import java.util.List;

import com.example.bd_empleados.R;
import com.example.bd_empleados.R.id;
import com.example.bd_empleados.R.layout;
import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.entidades.Empleados;
import com.example.bd_empleados.servicios.ServiciosCargos;
import com.example.bd_empleados.servicios.ServiciosEmpleados;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListarEmpleadoActivity extends Activity {

	private ServiciosEmpleados servEmpleados;
	private ListView listEmpleados;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_listar_empleado);
	setTitle("Lista de Empleados");
	listEmpleados = (ListView) findViewById(R.id.listViewCargos);
	listEmpleados.setOnItemClickListener(new OnItemClickListener() {

	@Override
	// Con el AdapterView se puede recuperar la lista de objetos.
	public void onItemClick(AdapterView<?> adapter, View arg1,
	int position, long arg3) {
	Empleados empleado = (Empleados) adapter.getItemAtPosition(position);
	System.out.println("Ingresando al metodo" + empleado.toString());
	//irActualizar(carg);
	}
	});
	
	//servcargos = new ServiciosCargos(this);
	servEmpleados = new ServiciosEmpleados(this);
	servEmpleados.abrirBD();
	List<Empleados> empleadosS = servEmpleados.recuperarTodos();
	ArrayAdapter<Empleados> adapter = new ArrayAdapter<Empleados>(this,
	android.R.layout.simple_list_item_1, empleadosS);
	listEmpleados.setAdapter(adapter);
	System.out.println(empleadosS.toString());
	servEmpleados.cerrarBD();
	}
}


