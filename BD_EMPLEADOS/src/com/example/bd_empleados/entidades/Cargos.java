package com.example.bd_empleados.entidades;

import java.io.Serializable;

public class Cargos implements Serializable{
	
	int idCargo;
	String nombreCargo;
	
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
		
	public String getNombreCargo() {
		return nombreCargo;
	}
	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}
	
	public String toString(){
		return "Id Cargo: "+idCargo+"\nNombre Cargo: "+nombreCargo;
	}

}
