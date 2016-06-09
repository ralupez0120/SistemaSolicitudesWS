package co.com.inversiones_xyz.ss.dto;

import java.util.Date;

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
	private Integer radicado;
	private Usuario responsable; 
	private Date fechaCreacion;
	private Date fechaRespondida;
	private byte estado;
	private String tiempoTranscurrido;
	
	public Integer getRadicado() {
		return radicado;
	}

	public void setRadicado(Integer radicado) {
		this.radicado = radicado;
	}

	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRespondida() {
		return fechaRespondida;
	}

	public void setFechaRespondida(Date fechaRespondida) {
		this.fechaRespondida = fechaRespondida;
	}

	public byte getEstado() {
		return estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public String getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}

	public void setTiempoTranscurrido(String tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}
	
	
	
	
	
	
	
}
	