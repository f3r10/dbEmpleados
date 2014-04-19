package com.example.bd_empleados;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.servicios.ServiciosCargos;

public class BorrarActivity extends Activity {
	
	private ServiciosCargos servBDEmpleados;
	private ListView listCargos;
	private Cargos cargoRecuperado;
	
	public BorrarActivity() {
		super();
		servBDEmpleados = new ServiciosCargos(this);
		}

		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrar);
		setTitle("Borrar Cargo");
		iniciaBorrado();
		}
		
		public void iniciaBorrado()
		{
		listCargos = (ListView) findViewById(R.id.listViewCargosb);

		listCargos.setOnItemClickListener(new OnItemClickListener() {

		@Override
		// Con el AdapterView se puede recuperar la lista de objetos.
		public void onItemClick(AdapterView<?> adapter, View arg1,
		int position, long arg3) {

		cargoRecuperado = (Cargos) adapter.getItemAtPosition(position);

		System.out.println("Ingresando al metodo" + cargoRecuperado.toString());
		irBorrar(cargoRecuperado);
		}
		});
		
		//servBDEmpleados = new ServiciosCargos(this);

		servBDEmpleados.abrirBD();
		System.out.println ("Entrando a listar para borrar");
		List<Cargos> cargoss = servBDEmpleados.recuperarTodos();
		ArrayAdapter<Cargos> adapter = new ArrayAdapter<Cargos>(this,
		android.R.layout.simple_list_item_1, cargoss);
		listCargos.setAdapter(adapter);
		System.out.println(cargoss.toString());
		servBDEmpleados.cerrarBD();
		}
		
		public void irBorrar(final Cargos cargo) {


			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setTitle("Borrar Registro");
			builder.setMessage("Seguro que desea borrarlo?");
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			try {
				servBDEmpleados.abrirBD();
				servBDEmpleados.eliminar(cargo);
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
					System.out.println("Cancelada la operación de borrado");
				}
				});

				builder.show();
				}

}
