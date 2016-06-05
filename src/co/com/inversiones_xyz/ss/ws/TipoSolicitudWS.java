package co.com.inversiones_xyz.ss.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.inversiones_xyz.ss.dto.TipoSolicitud;
import co.com.inversiones_xyz.ss.dto.TipoSolicitudWSDto;
import co.com.inversiones_xyz.ss.exception.DaoException;
import co.com.inversiones_xyz.ss.exception.ServiceException;
import co.com.inversiones_xyz.ss.service.TipoSolicitudService;
import javassist.tools.rmi.RemoteException;

/**
 * Servlet implementation class Servlet
 * Esta clase permite desplegar objetos logica del negocio en el navegador
 * @author: Rafael Luna Pérez
			ralp2089@gmail.com
 * @version: 1.0
 * 			 05/05/2016
 */
@Component
@Path("tiposolicitud")
public class TipoSolicitudWS {
	@Autowired 
	TipoSolicitudService tipoService;
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<TipoSolicitudWSDto> obtener()throws RemoteException{
		List<TipoSolicitudWSDto> lista = new ArrayList<TipoSolicitudWSDto>();
		try{
			for(TipoSolicitud tipo:tipoService.consultarTipos()){
				TipoSolicitudWSDto tipoWSDto = new TipoSolicitudWSDto();
				tipoWSDto.setCodigo(tipo.getCodigo());
				tipoWSDto.setNombre(tipo.getNombre());
				lista.add(tipoWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}

}
