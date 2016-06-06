package co.com.inversiones_xyz.ss.dto;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Esta clase permite desplegar sucursal en el navegador
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			3/06/2016
 *
 */
@XmlRootElement
public class SucursalWSDto {
	private String codigo; //codigo unico que identifica una sucursal
	private String nombre; //nombre de la sucursal
	private String direccion; //ubicacion geografica de la sucursal
	private String ciudad; // ciudad donde esta ubicada la sucursal
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

}
