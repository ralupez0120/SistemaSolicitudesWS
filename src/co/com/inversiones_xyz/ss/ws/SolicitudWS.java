package co.com.inversiones_xyz.ss.ws;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.inversiones_xyz.ss.dto.Solicitud;
import co.com.inversiones_xyz.ss.dto.SolicitudWSDto;
import co.com.inversiones_xyz.ss.exception.DaoException;
import co.com.inversiones_xyz.ss.exception.ServiceException;
import co.com.inversiones_xyz.ss.service.SeguimientoService;
import co.com.inversiones_xyz.ss.service.SolicitudService;
import javassist.tools.rmi.RemoteException;

/**
 * Esta clase permite desplegar solicitudes logica del negocio en el navegador
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			3/06/2016
 *
 */

@Component
@Path("solicitud")
public class SolicitudWS {
	@Autowired 
	SolicitudService solicitudService;
	@Autowired
	SeguimientoService seguimientoService;
	
	/**
	 * Despliega las solicitudes obtenidas en el navegador
	 * @param nombreUsuario: nombre del usuario que consulta
	 * @return la lista de solicitudes obtenidas del sistema
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("mostrarsolicitudes")
	public List<SolicitudWSDto> obtener(@QueryParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		List<SolicitudWSDto> lista = new ArrayList<SolicitudWSDto>();
		try{
			for(Solicitud solicitud:solicitudService.consultarSolicitudes(nombreUsuario)){
				SolicitudWSDto solicitudWSDto = new SolicitudWSDto();
				solicitudWSDto.setRadicado(solicitud.getRadicado());
				solicitudWSDto.setNombres(solicitud.getNombres());
				solicitudWSDto.setApellidos(solicitud.getApellidos());
				solicitudWSDto.setCorreo(solicitud.getCorreo());
				solicitudWSDto.setTelefono(solicitud.getTelefono());
				solicitudWSDto.setCelular(solicitud.getCelular());
				solicitudWSDto.setDescripcion(solicitud.getDescripcion());
				lista.add(solicitudWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}
	
	/**
	 * Despliega la solicitud dada su radicado
	 * @param radicado: identificador de la solicitud consultada
	 * @param nombreUsuario: nombre de usuario del usuario responsable o con rol gerente de cuentas
	 * @param codigoRol: codigo del rol que tiene asociado el usuario que consulta
	 * @return la solicitud obtenida del sistema
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("porradicado/{radicado}/{nombreUsuario}/{codigoRol}")
	public SolicitudWSDto obtenerPorRadicado(@PathParam("radicado")int radicado, @PathParam("nombreUsuario")String nombreUsuario, @PathParam("codigoRol")String codigoRol)throws RemoteException{
		SolicitudWSDto solicitudWSDto = null;
		try{
			Solicitud solicitud = solicitudService.consultarSolicitud(radicado, nombreUsuario, codigoRol);
			solicitudWSDto = new SolicitudWSDto();
			solicitudWSDto.setRadicado(radicado);
			solicitudWSDto.setNombres(solicitud.getNombres());
			solicitudWSDto.setApellidos(solicitud.getApellidos());
			solicitudWSDto.setCorreo(solicitud.getCorreo());
			solicitudWSDto.setTelefono(solicitud.getTelefono());
			solicitudWSDto.setCelular(solicitud.getCelular());
			solicitudWSDto.setDescripcion(solicitud.getDescripcion());
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return solicitudWSDto;
	}	
	
	/**
	 * Despliega las solicitudes asociadas a un usuario
	 * @param nombreUsuario: nombre del usuario responsable
	 * @return lista de solicitudes obtenidas del sistema
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("porusuario/{nombreUsuario}")
	public List<SolicitudWSDto> obtenerPorUsuario(@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		List<SolicitudWSDto> lista = new ArrayList<SolicitudWSDto>();
		try{
			for(Solicitud solicitud:solicitudService.consultarSolicitudes(nombreUsuario)){
				SolicitudWSDto solicitudWSDto = new SolicitudWSDto();
				solicitudWSDto.setRadicado(solicitud.getRadicado());
				solicitudWSDto.setNombres(solicitud.getNombres());
				solicitudWSDto.setApellidos(solicitud.getApellidos());
				solicitudWSDto.setCorreo(solicitud.getCorreo());
				solicitudWSDto.setTelefono(solicitud.getTelefono());
				solicitudWSDto.setCelular(solicitud.getCelular());
				solicitudWSDto.setDescripcion(solicitud.getDescripcion());
				lista.add(solicitudWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}
	
	/**
	 * Despliega las solicitudes que tiene asociadas un usuario
	 * @param nombreUsuario: nombre de usuario del usuario al que se quieren seguir las solicitudes que tiene asociadas
	 * @return lista de solicitudes obtenidas del sistema
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("seguirsolicitudes/{nombreUsuario}")
	public List<SolicitudWSDto> seguirSolicitudes(@PathParam("nombreUsuario")String nombreUsuario)throws RemoteException{
		List<SolicitudWSDto> lista = new ArrayList<SolicitudWSDto>();
		try{
			for(Solicitud solicitud:solicitudService.consultarSolicitudes(nombreUsuario)){
				SolicitudWSDto solicitudWSDto = new SolicitudWSDto();
				solicitudWSDto.setRadicado(solicitud.getRadicado());
				solicitudWSDto.setNombres(solicitud.getNombres());
				solicitudWSDto.setApellidos(solicitud.getApellidos());
				solicitudWSDto.setCorreo(solicitud.getCorreo());
				solicitudWSDto.setTelefono(solicitud.getTelefono());
				solicitudWSDto.setCelular(solicitud.getCelular());
				solicitudWSDto.setDescripcion(solicitud.getDescripcion());
				lista.add(solicitudWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}
	
	/**
	 * Guardar nueva solicitud en el sistema con los datos ingresados desde el navegador
	 * @param radicado: identificador de la solicitud
	 * @param nombres: nombres del cliente que hace la solicitud
	 * @param apellidos: apellidos del cliente
	 * @param correo: email registrado por el cliente
	 * @param telefono: telefono registrado por el cliente
	 * @param celular: celular registrado por el cliente
	 * @param descripcion: detalles de la solicitud
	 * @param codigoSucursal: codigo de la sucursal donde se adquirió el producto
	 * @param codigoTipo: codigo del tipo de la solicitud que hace el cliente
	 * @param codigoProducto: codigo del producto que adquirió el cliente
	 * @param idSeguimiento: identificador del seguimiento que se crea asociado a la solicitud
	 * @throws RemoteException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("guardarsolicitud")
	public void guardarSolicitud(@FormParam("radicado")int radicado, @FormParam("nombres")String nombres, @FormParam("apellidos")String apellidos, @FormParam("correo")String correo,
			@FormParam("telefono")String telefono, @FormParam("celular")String celular, @FormParam("descripcion") String descripcion, 
			@FormParam("codigoSucursal")String codigoSucursal, @FormParam("codigoTipo")int codigoTipo,
			@FormParam("codigoProducto")int codigoProducto, @FormParam("idSeguimiento")int idSeguimiento)throws RemoteException{
		try{
			solicitudService.generarSolicitud(radicado, nombres, apellidos, correo, telefono, celular, descripcion, codigoSucursal, codigoTipo, codigoProducto, idSeguimiento);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		} catch (DaoException ex) {
			throw new RemoteException(ex);
		}
	}

	/**
	 * Responder solicitud desde el navegador
	 * @param id: identificador del seguimiento asociado a la solicitud
	 * @param nombreUsuario: nombre del usuario que responde la solicitud
	 * @param codigoRol: codigo del rol asociado al usuario que responde la solicitud
	 * @throws RemoteException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("respondersolicitud")
	public void responderSolicitud(@FormParam("id")int id, @FormParam("nombreUsuario")String nombreUsuario, @FormParam("codigoRol")String codigoRol)throws RemoteException{
		try{
			seguimientoService.responderSolicitud(id, nombreUsuario, codigoRol);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		} catch (DaoException ex) {
			throw new RemoteException(ex);
		}
	}
	
	/**
	 * Reasignar solicitud desde el navegador
	 * @param id: identificador del seguimiento asociado a la solicitud
	 * @param nombreUsuario: nombre de usuario del gerente de cuentas
	 * @param nuevoResponsable: nombre de usuario del nuevo responsable asignado
	 * @param codigoRol: codigo del rol del que reasigna la solicitud
	 * @throws RemoteException
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("reasignarsolicitud")
	public void reasignarSolicitud(@FormParam("id")int id, @FormParam("nombreUsuario")String nombreUsuario, @FormParam("nuevoResponsable")String nuevoResponsable, @FormParam("codigoRol")String codigoRol)throws RemoteException{
		try{
			seguimientoService.reasignarSolicitud(id, nombreUsuario, nuevoResponsable, codigoRol);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		} catch (DaoException ex) {
			throw new RemoteException(ex);
		}
	}
	
	/**
	 * consultar resultado encuesta
	 * @param radicado
	 * @param nombreUsuario
	 * @param codigoRol
	 * @return
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_XML)
	@GET
	@Path("resultadoencuesta/{radicado}/{nombreUsuario}/{codigoRol}")
	public String obtenerResultado(@PathParam("radicado")int radicado, @PathParam("nombreUsuario")String nombreUsuario, @PathParam("codigoRol")String codigoRol)throws RemoteException{
		String resultado = null;
		try{
			resultado = seguimientoService.consultarResultadoEncuentas(radicado, nombreUsuario, codigoRol);
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return resultado;
	}
	
	/**
	 * consultar los resultados de todas las encuestas
	 * @param nombreUsuario
	 * @param codigoRol
	 * @return
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("resultadosencuesta/{nombreUsuario}/{codigoRol}")
	public List<String> obtenerResultados(@PathParam("nombreUsuario")String nombreUsuario, @PathParam("codigoRol")String codigoRol)throws RemoteException{
		List<String> lista = new ArrayList<String>();
		try{
			lista = seguimientoService.consultarResultadosEncuentas(nombreUsuario, codigoRol);
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}
}
