package com.example.bd_empleados.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CargosHelper extends SQLiteOpenHelper {

	public CargosHelper(Context context) {
		super(context, "bdEmpleados", null, 1);
		// TODO Auto-generated constructor stub
	}

	String sentencia = "CREATE TABLE CARGO(idCargo INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "nombreCargo TEXT NOT NULL)";
	String sentenciaEmpleado = "CREATE TABLE EMPLEADO(idEmpleado INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "nombreEmpleado TEXT NOT NULL,"
			+ "direccionEmpleado TEXT NOT NULL,"
			+ "sueldoEmpleado FLOAT,"
			+ "idCargo INTEGER, FOREIGN KEY(idCargo) REFERENCES CARGO(idCargo) ON DELETE CASCADE) ";

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sentencia);
		db.execSQL(sentenciaEmpleado);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("DBAdapter", "Upgrading database from verion" + oldVersion + "to"
				+ newVersion + ", which will destory all old data");
		db.execSQL("DROP TABLE IF EXISTS CARGO");
		db.execSQL("DROP TABLE IF EXISTS EMPLEADO");
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("PRAGMA foreign_keys=ON");

	}

}
