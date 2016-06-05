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
import co.com.inversiones_xyz.ss.exception.DaoException;
import co.com.inversiones_xyz.ss.service.UsuarioService;
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
@Path("usuario")
public class UsuarioWS {
	@Autowired 
	UsuarioService usuarioService;
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<UsuarioWSDto> obtener()throws RemoteException{
		List<UsuarioWSDto> lista = new ArrayList<UsuarioWSDto>();
		try{
			for(Usuario usuario:usuarioService.UsuariosActivos()){
				UsuarioWSDto usuarioWSDto = new UsuarioWSDto();
				usuarioWSDto.setNombreUsuario(usuario.getNombreUsuario());
				usuarioWSDto.setNombres(usuario.getNombres());
				usuarioWSDto.setApellidos(usuario.getApellidos());
				usuarioWSDto.setCorreo(usuario.getCorreo());
				lista.add(usuarioWSDto);
			}
		}catch (DaoException ex){
			throw new RemoteException(ex);
		}
		return lista;
	}

}
