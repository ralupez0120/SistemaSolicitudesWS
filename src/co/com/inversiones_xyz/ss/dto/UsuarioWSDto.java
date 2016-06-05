package co.com.inversiones_xyz.ss.dto;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Servlet implementation class Servlet
 * Esta clase permite desplegar objetos logica del negocio en el navegador
 * @author: Rafael Luna Pérez
			ralp2089@gmail.com
 * @version: 1.0
 * 			 05/05/2016
 */
@XmlRootElement
public class UsuarioWSDto {
	private String nombreUsuario;
	private String nombres;
	private String apellidos;
	private String correo;
	private String password;
	private String rol;
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	

	
}
