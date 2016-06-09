package co.com.inversiones_xyz.ss.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.inversiones_xyz.ss.dto.Usuario;
import co.com.inversiones_xyz.ss.dto.UsuarioWSDto;
import co.com.inversiones_xyz.ss.excepcion.DaoException;
import co.com.inversiones_xyz.ss.service.UsuarioService;
import javassist.tools.rmi.RemoteException;

/**
 * Esta clase permite desplegar usuario logica del negocio en el navegador
 * @author 
 * 		Juan Carlos Estrada
 * 		Rafael Luna Pérez
 * 		Joan Manuel Rodríguez
 * @version 1.0.0
 * 			3/06/2016
 *
 */
@Component
@Path("usuario")
public class UsuarioWS {
	@Autowired 
	UsuarioService usuarioService;
	
	/**
	 * Despliega los usuarios obtenidos en el navegador
	 * @return lista de usuarios obtenidos
	 * @throws RemoteException
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<UsuarioWSDto> obtener()throws RemoteException{
		List<UsuarioWSDto> lista = new ArrayList<UsuarioWSDto>();
		List<Usuario> usuarios= new ArrayList<Usuario>();
		try{
			usuarios=usuarioService.UsuariosActivos();
			for(Usuario usuario:usuarios){
				UsuarioWSDto usuarioWSDto = new UsuarioWSDto();
				usuarioWSDto.setNombreUsuario(usuario.getNombreUsuario());
				usuarioWSDto.setNombres(usuario.getNombres());
				usuarioWSDto.setApellidos(usuario.getApellidos());
				lista.add(usuarioWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}
	
	
}
