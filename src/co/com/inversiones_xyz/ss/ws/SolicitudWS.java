package co.com.inversiones_xyz.ss.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.inversiones_xyz.ss.dto.Seguimiento;
import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.dto.SolicitudWSDto;
import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;
import co.com.inversiones_xyz.ss.service.SeguimientoService;
import co.com.inversiones_xyz.ss.service.SolicitudService;

@Component
@Path("solicitud")
public class SolicitudWS {
	@Autowired
	SolicitudService solicitudService;
	
	@Autowired
	SeguimientoService seguimientoService;
	
	//-------- SERVICIOS -----

	/**
	 * Guardar nueva solicitud en el sistema con los datos ingresados desde el formulario
	 * de generación de solicitudes
	 * Web service asociado a requisito RF-01
	 * 
	 * @param nombres: nombres del cliente que hace la solicitud
	 * @param apellidos: apellidos del cliente
	 * @param correo: email registrado por el cliente
	 * @param telefono: telefono registrado por el cliente
	 * @param celular: celular registrado por el cliente
	 * @param descripcion: detalles de la solicitud
	 * @param codigoSucursal: codigo de la sucursal donde se adquirió el producto
	 * @param codigoTipo: codigo del tipo de la solicitud que hace el cliente
	 * @param codigoProducto: codigo del producto que adquirió el cliente
	 * 
	 * @throws RemoteException
	 */
	@Path("generar")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_HTML)
	public String generarSolicitud(@FormParam("nombres")String nombres, 
			@FormParam("apellidos")String apellidos,@FormParam("correo")String correo,
			@FormParam("telefono")String telefono,@FormParam("celular")String celular,
			@FormParam("descripcion")String descripcion, @FormParam("codigoSucursal")String codigoSucursal,
			@FormParam("tipoSolicitud")int codigoTipo, @FormParam("producto")int codigoProducto
			) throws RemoteException, ServiceException{
		
		Solicitud nuevaSolicitud;
		try {
			nuevaSolicitud=solicitudService.generarSolicitud(nombres, apellidos, correo, 
					telefono, celular, descripcion, codigoSucursal, codigoTipo, 
					codigoProducto);
			
		} catch (DaoException  ex) {
			throw new RemoteException(ex.toString());
			// TODO: handle exception
		}
		return "Se ha ingresado una nueva solicitud de manera exitosa!"
				+"Su número de radicado es: "+nuevaSolicitud.getRadicado().toString();		
	}
	
	/**
	 * Despliega las solicitudes asociadas a un usuario para mostrarlas en pantalla.
	 * Este web service está asociado al requisito RF-02 Mostrar solicitudes
	 * @param nombreUsuario: nombre del usuario responsable
	 * @return lista de solicitudes obtenidas del sistema
	 * @throws RemoteException
	 * 		Cuando no existe usuario con ese userName
	 * 		Cuando no hay solicitudes asociadas al Usuario ingresado como parámetro
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("porusuario/{nombreUsuario}")
	public List<Solicitud> obtenerPorUsuario(@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		List<Solicitud> lista = new ArrayList<Solicitud>();
		try{
			lista=solicitudService.obtenerSolicitudes(nombreUsuario);
		}catch (DaoException ex){
			throw new RemoteException(ex.toString());
		}catch(ServiceException ex){
			throw new RemoteException(ex.toString());
		}
		return lista;
	}
	
	/**
	 * Despliega los detalles de una solicitud dada su radicado
	 * Este web service está asociado al requisito RF-03 Y RF-05
	 * 
	 * @param radicado: identificador de la solicitud consultada
	 * @param nombreUsuario: nombre de usuario del usuario responsable o con rol gerente de cuentas
	 * @return la solicitud obtenida del sistema
	 * 
	 * @throws RemoteException
	 * 		algún error con el servicio o al consultar en la base de datos
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("verDetalleSolicitud/{radicado}/{nombreUsuario}")
	public Solicitud obtenerPorRadicado(@PathParam("radicado")int radicado, 
			@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		Solicitud solicitud=null;
		try{
			solicitud = solicitudService.consultarSolicitud(radicado, nombreUsuario);
		}catch (DaoException ex){
			throw new RemoteException(ex.toString());
		}catch(ServiceException ex){
			throw new RemoteException(ex.toString());
		}
		return solicitud;
	}
	
	/**
	 * Le permite al gerente de cuentas corporativas llevar un
	 *	seguimiento de todas las solicitudes, tanto de las que se encuentren
	 *	respondidas como las que aún se encuentren en estado pendiente.
	 *
	 * Web service asociado al requisito RF-08
	 *
	 * @return lista de solicitudes obtenidas del sistema con sólo la siguiente información:
	 * 		radicado
	 * 		fechaCreación
	 * 		responsable
	 * 		estado
	 * 		tiempoRespuesta (o tiempo transcurrido en caso de estar pendiente)
	 * 		
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("seguirsolicitudes/{nombreUsuario}")
	public List<SolicitudWSDto> seguirSolicitudes(@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		List<SolicitudWSDto> lista = new ArrayList<SolicitudWSDto>();
		List<Solicitud> solicitudes=new ArrayList<Solicitud>();
		try{
			solicitudes=solicitudService.seguirSolicitudes(nombreUsuario);
			for(Solicitud solicitud:solicitudes){
				SolicitudWSDto solicitudWSDto = new SolicitudWSDto();
				solicitudWSDto.setRadicado(solicitud.getRadicado());
				solicitudWSDto.setFechaCreacion(solicitud.getSeguimiento().getFechaCreacion());
				solicitudWSDto.setFechaRespondida(solicitud.getSeguimiento().getFechaRespondida());
				solicitudWSDto.setEstado(solicitud.getSeguimiento().getEstado());
				solicitudWSDto.setResponsable(solicitud.getSeguimiento().getResponsable());
				lista.add(solicitudWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex.getMessage());
		}catch(ServiceException ex){
			throw new RemoteException(ex.getMessage());
		}
		return lista;
	}
	
	
	/**
	 * Responder solicitud desde el navegador
	 * Web service asociado a requisito RF-06
	 * @param id: identificador del seguimiento asociado a la solicitud
	 * @param nombreUsuario: nombre del usuario que responde la solicitud
	 * @param codigoRol: codigo del rol asociado al usuario que responde la solicitud
	 * @throws RemoteException
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_HTML)
	@Path("respondersolicitud")
	public String responderSolicitud(@FormParam("radicado")int radicado, 
			@FormParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		Seguimiento seguimientoModificado;
		try{
			seguimientoModificado=seguimientoService.responderSolicitud(radicado, nombreUsuario);
		}catch(ServiceException ex){
			throw new RemoteException(ex.getMessage());
		} catch (DaoException ex) {
			throw new RemoteException(ex.getMessage());
		}
		if (seguimientoModificado==null) {
			return "Error al responder solicitud";
		} else {
			return "Se ha respondido satisfactoriamente solicitud #"+radicado+
					" el día:"+seguimientoModificado.getFechaRespondida();
		}
	}
	
	/**
	 * Reasignar solicitud desde el navegador
	 * Web service asociado a requisito RF-07
	 * @param id: radicado de la la solicitud a reasignar
	 * @param nombreUsuario: nombre de usuario del gerente de cuentas
	 * @param nuevoResponsable: nombre de usuario del nuevo responsable asignado
	 * @throws RemoteException
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_HTML)
	@Path("reasignarsolicitud")
	public String reasignarSolicitud(@FormParam("radicado")int radicado, @FormParam("nombreUsuario")String nombreUsuario, 
			@FormParam("nuevoResponsable")String nuevoResponsable)throws RemoteException{
		Usuario nuevoUsuario;
		try{
			nuevoUsuario=seguimientoService.reasignarSolicitud(radicado, nombreUsuario, nuevoResponsable);
		}catch(ServiceException ex){
			throw new RemoteException(ex.getMessage());
		} catch (DaoException ex) {
			throw new RemoteException(ex.getMessage());
		}
		if(nuevoUsuario==null){
			return "Error al asignar nuevo usuario";
		}else{
			return nuevoUsuario.getNombreUsuario()+
					" ha quedado como el nuevo usuario responsable de la solicitud #"+radicado;
		}
	}
	
	/**
	 * consultar resultado encuesta dado el id de su radicado
	 * Web service asociado a requisito RF-09
	 * 
	 * @param radicado
	 * @param nombreUsuario
	 * @param codigoRol
	 * @return
	 * @throws RemoteException
	 */
	@Produces(MediaType.TEXT_HTML)
	@GET
	@Path("resultadoencuesta/{radicado}/{nombreUsuario}")
	public String obtenerResultado(@PathParam("radicado")int radicado, 
			@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		String resultado = null;
		try{
			resultado = seguimientoService.consultarResultadoEncuenta(radicado, nombreUsuario);
		}catch (DaoException ex){
			throw new RemoteException(ex.getMessage());
		}catch(ServiceException ex){
			throw new RemoteException(ex.getMessage());
		}
		if (resultado==null){
			return "solicitud con radicado "+radicado+" no ha sido respondida aún";
		}else{
			return resultado;
		}
	}
	
	/**
	 * consultar los resultados de todas las encuestas
	 * Web service asociado a requisito RF-09
	 * @param nombreUsuario
	 * @param codigoRol
	 * @return
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("resultadosencuestas/{nombreUsuario}/")
	public JSONArray obtenerResultados(@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		List<String> lista = new ArrayList<String>();
		try{
			lista = seguimientoService.consultarResultadosEncuentas(nombreUsuario);
		}catch (DaoException ex){
			throw new RemoteException(ex.getMessage());
		}catch(ServiceException ex){
			throw new RemoteException(ex.getMessage());
		}
		
		return new JSONArray(lista);
	}
}
