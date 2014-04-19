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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BorrarEmpleadoActivity extends Activity {

	private ServiciosEmpleados servBDEmpleados;
	private ListView listEmpleados;
	private Empleados empleadoRecuperado;
	
	public BorrarEmpleadoActivity() {
		super();
		servBDEmpleados = new ServiciosEmpleados(this);
		}

		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrar_empleado);
		setTitle("Borrar Empleado");
		iniciaBorrado();
		}
		
		public void iniciaBorrado()
		{
		listEmpleados = (ListView) findViewById(R.id.listViewEmpleadosb);

		listEmpleados.setOnItemClickListener(new OnItemClickListener() {

		@Override
		// Con el AdapterView se puede recuperar la lista de objetos.
		public void onItemClick(AdapterView<?> adapter, View arg1,
		int position, long arg3) {

		empleadoRecuperado = (Empleados) adapter.getItemAtPosition(position);

		System.out.println("Ingresando al metodo" + empleadoRecuperado.toString());
		irBorrar(empleadoRecuperado);
		}
		});
		
		//servBDEmpleados = new ServiciosCargos(this);

		servBDEmpleados.abrirBD();
		System.out.println ("Entrando a listar para borrar");
		List<Empleados> empleadoss = servBDEmpleados.recuperarTodos();
		ArrayAdapter<Empleados> adapter = new ArrayAdapter<Empleados>(this,
		android.R.layout.simple_list_item_1, empleadoss);
		listEmpleados.setAdapter(adapter);
		System.out.println(empleadoss.toString());
		servBDEmpleados.cerrarBD();
		}
		
		public void irBorrar(final Empleados empleado) {


			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setTitle("Borrar Registro");
			builder.setMessage("Seguro que desea borrarlo?");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			try {
				servBDEmpleados.abrirBD();
				servBDEmpleados.eliminar(empleado);
				System.out.println( "Cargo Borrado Con Exito");
				servBDEmpleados.cerrarBD();
				iniciaBorrado();

				} 
			catch (Exception exception) {
				System.out.println ("Error al borrar cargo");

				}}});

				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

				//@Override
				public void onClick(DialogInterface dialog, int which) {
					System.out.println("Cancelada la operaci�n de borrado");
				}
				});

				builder.show();
				}

}
