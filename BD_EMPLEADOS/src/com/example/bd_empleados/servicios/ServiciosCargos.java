package com.example.bd_empleados.servicios;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bd_empleados.bdd.CargosHelper;
import com.example.bd_empleados.entidades.Cargos;
import com.example.bdempleadost.SpinnerObject;

public class ServiciosCargos <SQLiteDataBase>{
	
	private CargosHelper cargoHelper;
	private SQLiteDatabase dataBase;
	
		
	private String columnas[]={"idCargo","nombreCargo"};
	
	public ServiciosCargos(Context context){
		cargoHelper=new CargosHelper(context);
	}
	
	public void abrirBD() {
		dataBase=cargoHelper.getWritableDatabase();
	}
	
	public void cerrarBD(){
		cargoHelper.close();
	}
	
	public void Insertar(Cargos cargo){
		ContentValues valores=new ContentValues();
		valores.put("nombreCargo", cargo.getNombreCargo());
		dataBase.insert("CARGO", null, valores);
	}
	
	public void actualizar(Cargos cargo){
		ContentValues valores = new ContentValues();
		valores.put("nombreCargo", cargo.getNombreCargo());
		dataBase.update("CARGO", valores, "idCargo="+cargo.getIdCargo(), null);
		}
	
	public List<Cargos> recuperarTodos() {

		List<Cargos> cargosl = new ArrayList<Cargos>();
		Cursor cursor = dataBase.query("CARGO", columnas, null, null,
		null, null, null);
		cursor.moveToNext();
		while (!cursor.isAfterLast()) {
		Cargos carg = new Cargos();
		carg.setIdCargo(cursor.getInt(0));
		carg.setNombreCargo(cursor.getString(1));

		cargosl.add(carg);
		cursor.moveToNext();
		}

		return cargosl;

		}
		
	public Cursor recuperarCargo(int rowid)throws SQLException
	{
	Cursor mCursor =
	dataBase.query(true, "CARGO", new String[] {"IDCARGO",
	"NOMBRECARGO"}, "IDCARGO" + "=" + rowid, null,
	null, null, null, null);
	if (mCursor != null) {
	mCursor.moveToFirst();

	System.out.println("Saliendo de recuperarCargo");

	}
	return mCursor;
	}
	
	public void eliminar(Cargos cargo){
		ContentValues valores = new ContentValues();
		valores.put( "idCargo",cargo.getIdCargo());
		dataBase.delete("CARGO", "idCargo="+cargo.getIdCargo(), null);
		// tambi�n puedes aplicar �sta instrucci�n.
		//dataBase.execSQL("DELETE FROM CARGO WHERE idCargo = " + cargo.getIdCargo());
	}



	
	

}
