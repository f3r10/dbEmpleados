package com.example.bd_empleados.servicios;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bd_empleados.bdd.CargosHelper;
import com.example.bd_empleados.entidades.Cargos;
import com.example.bd_empleados.entidades.Empleados;

public class ServiciosEmpleados <SQLiteDataBase> {
	
	private CargosHelper cargoHelper;
	private SQLiteDatabase dataBase;
	
		
	private String columnas[]={"idEmpleado","nombreEmpleado","direccionEmpleado","sueldoEmpleado","idCargo"};
		
	
	public ServiciosEmpleados(Context context){
		cargoHelper=new CargosHelper(context);
	}
	
	public void abrirBD() {
		//dataBase.execSQL("PRAGMA foreign_keys=ON;");
		dataBase=cargoHelper.getWritableDatabase();
		
	}
	
	public void cerrarBD(){
		cargoHelper.close();
	}
	
	public void Insertar(Empleados empleado){
		ContentValues valores=new ContentValues();
		valores.put("nombreEmpleado", empleado.getNombreEmpleado());
		valores.put("direccionEmpleado", empleado.getDireccionEmpleado());
		valores.put("sueldoEmpleado", empleado.getSueldoEmpleado());
		valores.put("idCargo", empleado.getIdCargo());
		dataBase.insert("EMPLEADO", null, valores);
	}
	
	public void actualizar(Empleados empleado){
		ContentValues valores = new ContentValues();
		valores.put("nombreEmpleado", empleado.getNombreEmpleado());
		valores.put("direccionEmpleado", empleado.getDireccionEmpleado());
		valores.put("sueldoEmpleado", empleado.getSueldoEmpleado());
		valores.put("idCargo", empleado.getIdCargo());
		dataBase.update("EMPLEADO", valores, "idEmpleado="+empleado.getIdEmpleado(), null);
		Log.w("pruebaa", "entra a actualizar!!!!!!");
		}
	
	public List<Empleados> recuperarTodos() {

		List<Empleados> empleadosLista = new ArrayList<Empleados>();
		//Cursor cursor = dataBase.query("EMPLEADO", columnas, null, null,
		//null, null, null);
		String MY_QUERY = "SELECT E.IDEMPLEADO, E.NOMBREEMPLEADO, E.DIRECCIONEMPLEADO, E.SUELDOEMPLEADO, C.NOMBRECARGO, C.IDCARGO  FROM EMPLEADO E, CARGO C WHERE"
				+ " E.idcargo =C.idcargo";
		Cursor cursor = dataBase.rawQuery(MY_QUERY, null);
		cursor.moveToNext();
		while (!cursor.isAfterLast()) {
		Empleados empleado = new Empleados();
		empleado.setIdEmpleado(cursor.getInt(0));
		empleado.setNombreEmpleado(cursor.getString(1));
		empleado.setDireccionEmpleado(cursor.getString(2));
		empleado.setSueldoEmpleado(cursor.getFloat(3));
		empleado.setIdCargo(cursor.getInt(5));
		empleado.setNombreCargo(cursor.getString(4));
		
		

		empleadosLista.add(empleado);
		cursor.moveToNext();
		}

		return empleadosLista;

		}
	
	public Cursor recuperarEmpleado(int rowid)throws SQLException
	{
	String MY_QUERY = "SELECT E.IDEMPLEADO, E.NOMBREEMPLEADO, E.DIRECCIONEMPLEADO, E.SUELDOEMPLEADO, C.NOMBRECARGO, C.IDCARGO  FROM EMPLEADO E, CARGO C WHERE"
			+ " E.idEmpleado= ? and E.idcargo =C.idcargo";
	Cursor mCursor = dataBase.rawQuery(MY_QUERY, new String[]{String.valueOf(rowid)});
	/*dataBase.query(true, "EMPLEADO", new String[] {"IDEMPLEADO",
	"NOMBREEMPLEADO", "DIRECCIONEMPLEADO","SUELDOEMPLEADO",}, "IDCARGO" + "=" + rowid, null,
	null, null, null, null);*/
		
	if (mCursor != null) {
	mCursor.moveToFirst();

	System.out.println("Saliendo de recuperarCargo");

	}
	return mCursor;
	}
	
	
	public void eliminar(Empleados empleado){
		ContentValues valores = new ContentValues();
		valores.put( "idEmpleado",empleado.getIdCargo());
		dataBase.delete("EMPLEADO", "idEmpleado="+empleado.getIdEmpleado(), null);
	}
}
