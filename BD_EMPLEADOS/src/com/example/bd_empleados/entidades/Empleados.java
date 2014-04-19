package com.example.bd_empleados.entidades;

import java.io.Serializable;

public class Empleados implements Serializable{
	
	int idEmpleado;
	String nombreEmpleado;
	String direccionEmpleado;
	Float sueldoEmpleado;
	int idCargo;
	String nombreCargo;
	
	
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getDireccionEmpleado() {
		return direccionEmpleado;
	}
	public void setDireccionEmpleado(String direccionEmpleado) {
		this.direccionEmpleado = direccionEmpleado;
	}
	public Float getSueldoEmpleado() {
		return sueldoEmpleado;
	}
	public void setSueldoEmpleado(Float sueldoEmpleado) {
		this.sueldoEmpleado = sueldoEmpleado;
	}
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
		return "IdCargo: "+idCargo+"\nNombre Empleado: "+nombreEmpleado+"\nDireccion Empleado: "
				+direccionEmpleado+"\nSueldo: "+sueldoEmpleado +"\nCargo: "+ nombreCargo;
	}
}
