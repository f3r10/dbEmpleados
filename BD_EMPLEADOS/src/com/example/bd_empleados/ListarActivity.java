package com.example.bd_empleados;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.servicios.ServiciosCargos;

public class ListarActivity extends Activity {
	private ServiciosCargos servcargos;
	private ListView listCargos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_listar);
	setTitle("Lista de Cargos");
	listCargos = (ListView) findViewById(R.id.listViewCargos);
	listCargos.setOnItemClickListener(new OnItemClickListener() {

	@Override
	// Con el AdapterView se puede recuperar la lista de objetos.
	public void onItemClick(AdapterView<?> adapter, View arg1,
	int position, long arg3) {
	Cargos carg = (Cargos) adapter.getItemAtPosition(position);
	System.out.println("Ingresando al metodo" + carg.toString());
	irActualizar(carg);
	}
	});
	
	//servcargos = new ServiciosCargos(this);
	servcargos = new ServiciosCargos(this);
	servcargos.abrirBD();
	List<Cargos> cargoss = servcargos.recuperarTodos();
	ArrayAdapter<Cargos> adapter = new ArrayAdapter<Cargos>(this,
	android.R.layout.simple_list_item_1, cargoss);
	listCargos.setAdapter(adapter);
	System.out.println(cargoss.toString());
	servcargos.cerrarBD();
	}

	public void irActualizar(Cargos carg) {
	Intent intent = new Intent(this, ActualizarActivity.class);
	intent.putExtra("idcargo", carg.getIdCargo());
	System.out.println("Ir a Actualizar.");
	startActivity(intent);
	}

}
