package co.com.inversiones_xyz.ss.dto;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Esta clase permite desplegar solicitudes en el navegador
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			3/06/2016
 *
 */
@XmlRootElement
public class SolicitudWSDto {
	private int radicado; //entero que identifica una solicitud 
	private String nombres; //nombres del cliente que registra la solicitud
	private String apellidos; //apellidos del cliente
	private String correo; //email registrado por el cliente
	private String telefono; //telefono del cliente
	private String celular; //celular del cliente
	private String descripcion; //descripcion de la solicitud
	
	public int getRadicado() {
		return radicado;
	}
	public void setRadicado(int radicado) {
		this.radicado = radicado;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}
