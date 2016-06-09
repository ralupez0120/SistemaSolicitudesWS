package co.com.inversiones_xyz.ss.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.inversiones_xyz.ss.dto.Sucursal;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.excepcion.ServiceException;
import co.com.inversiones_xyz.ss.service.SucursalService;
import javassist.tools.rmi.RemoteException;

/**
 * Esta clase permite desplegar sucursal logica del negocio en el navegador
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			3/06/2016
 *
 */

@Component
@Path("sucursal")
public class SucursalWS {
	@Autowired 
	SucursalService sucursalService;
	
	/**
	 * Despliega lista de sucursales obtenidas en el navegador
	 * @return lista de sucursales obtenidas del sistema
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Sucursal> obtener()throws RemoteException{
		List<Sucursal> lista=new ArrayList<Sucursal>();
		try{
			lista=sucursalService.consultarSucursales();
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}

}
