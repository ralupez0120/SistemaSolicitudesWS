package co.com.inversiones_xyz.ss.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.inversiones_xyz.ss.dto.Producto;
import co.com.inversiones_xyz.ss.dto.ProductoWSDto;
import co.com.inversiones_xyz.ss.exception.DaoException;
import co.com.inversiones_xyz.ss.exception.ServiceException;
import co.com.inversiones_xyz.ss.service.ProductoService;
import javassist.tools.rmi.RemoteException;

/**
 * Esta clase permite desplegar producto logica del negocio en el navegador
 * @author:
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			3/06/2016
 *
 */
@Component
@Path("producto")
public class ProductoWS {
	@Autowired 
	ProductoService productoService;

	/**
	 * Desplegar productos obtenidos en el navegador
	 * @return lista de productos obtenidos del sistema
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<ProductoWSDto> obtener()throws RemoteException{
		List<ProductoWSDto> lista = new ArrayList<ProductoWSDto>();
		try{
			for(Producto producto:productoService.consultarProductos()){
				ProductoWSDto productoWSDto = new ProductoWSDto();
				productoWSDto.setCodigo(producto.getCodigo());
				productoWSDto.setNombre(producto.getNombre());
				productoWSDto.setDescripcion(producto.getDescripcion());
				lista.add(productoWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}catch(ServiceException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}

}
