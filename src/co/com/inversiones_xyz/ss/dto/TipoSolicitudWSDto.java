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
public class TipoSolicitudWSDto {
	private int codigo;
	private String nombre;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
