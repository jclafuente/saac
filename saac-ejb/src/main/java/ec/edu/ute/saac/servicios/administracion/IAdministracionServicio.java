package ec.edu.ute.saac.servicios.administracion;

import java.util.Collection;

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.CursoTitulacionEstudiante;
import ec.edu.ute.saac.entidades.Facultad;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.PersonaCarrera;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.Rol;
import ec.edu.ute.saac.entidades.SeleccionTema;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;

@QAdministracion
public interface IAdministracionServicio {
	
	
	public Collection<Facultad> findAll();
	public Collection<Carrera> findCarreraFacultad();
	public Collection<Rol> findAllRol();
	public Collection<Facultad> obtenerFacultad();
	public Collection<CursoTitulacion> obtenerCursoTitulacion() throws Exception;
	public void crearFacultad(Facultad facultad) throws Exception;
	public void actualizarFacultad(Facultad facultad) throws Exception;
	public void actualizarCarrera (Carrera carrera) throws Exception;
	public void actualizarRol(Rol rol) throws Exception;
	public void crearCarrera(Carrera carrera) throws Exception;
	public void crearRol(Rol rol) throws Exception;
	public void crearPersona(Persona persona) throws Exception;
	public Collection<LineaInvestigacion> obtenerLineaInvestigacion() throws Exception;
	public Collection<AreaInvestigacion> obteneraAreaInvestigacion() throws Exception;
	
	public Collection<Persona> obtenerPersona() throws Exception;
	public Collection<Persona> obtenerPersonaUsuario() throws Exception;
	public Collection<Persona> obtenerPersonaUsuarioEstudiante() throws Exception;
	public Collection<Persona> obtenerPersona(Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo) throws Exception;
	public Collection<Persona> obtenerPersonaCarreraFacultad(Integer facCodigo,Integer carCodigo) throws Exception;
	
	public void actualizarPersona(Persona persona) throws Exception;
	
	public Collection<PersonaCarrera> obtenerPersonaCarrera(Integer facCodigo, Integer carCodigo) throws Exception;
	
	public Collection<Carrera> obtenerCarrera() throws Exception;
	public Collection<Carrera> obtenerCarreraFacultad(Integer facCodigo) throws  Exception;
	
	public Collection<LineaInvestigacion> obtenerLineaInvestigacionCarrera(Integer carCodigo) throws Exception;
	public Collection<AreaInvestigacion> obtenerAreaInvestigacionLineaInvestigacion(Integer linInvCodigo) throws Exception;
	
	public Collection <TemasTitulacion> obtenerTemaTitulacion(Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo) throws Exception;
	public Collection <TemasTitulacion> obtenerTemaTitulacionAprobado(Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo) throws Exception;
	
	public void actualizarTemaTitulacion(TemasTitulacion temaTitulacion, String estado) throws Exception;
	public void actualizarTemaTitulacion(TemasTitulacion temaTitulacion ) throws Exception;
	
	public Collection<CursoTitulacion>obtenerCursoTitulacion(Integer periodo) throws Exception;
	public void crearCursoTitulacion(CursoTitulacion cursoTitulacion) throws Exception;
	
	public Collection<UsuarioRol> obtenerPersonaEstudiante() throws Exception;
	public void crearTemaTitulacion(TemasTitulacion temaTitulacion) throws Exception;
	public void asignarEstadoTemaTitulacion(TemasTitulacion temaTitulacion, String estado) throws Exception;
	
	public void eliminarCursoTitulacion(CursoTitulacion cursoTitulacion) throws Exception;
	public void eliminarCursoTitulacionEstudiante(CursoTitulacionEstudiante cursoTitulacionEstudiante) throws Exception;
	
	public Collection<Periodos> obtenerPeriodo() throws Exception;
	public Collection<Usuario> obtenerUsuario() throws Exception;
	public Collection<Usuario> obtenerUsuarioEstudiante() throws Exception;
	
	public Collection<Catalogo> obtenerCatalogoNac() throws Exception;
	public Collection<Catalogo> obtenerCatalogoEstadoCiv() throws Exception;
	public Collection<Catalogo> obtenerCatalogoGenero() throws Exception;
	
	public Collection<UsuarioRol> obtenerUsuarioRolEst() throws Exception;
	public void actualizarCursoTitulacion(CursoTitulacion cursoTitulacion) throws Exception;
	public void actualizarCursoTitulacionEstudiante(CursoTitulacionEstudiante cursoTitulacionEstudiante) throws Exception;
	public void crearUsuario(Usuario usuario) throws Exception;
	public void crearUsuarioRol(UsuarioRol usuarioRol) throws Exception;
	public Collection<SeleccionTema> obtenerSeleccionTemaAprobado(Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo, Integer temaCodigo) throws Exception;
	public Collection <SeleccionTema>obtenerEstudianteSeleccionTema(Integer perCodigo)throws Exception;
	public Collection <SeleccionTema>obtenerEstudiantesSeleccionTema() throws Exception;
	public Collection<TemasTitulacion> obtenerSeleccionTemaAreaInvestigacion(Integer areInvCodigo) throws Exception;
	public void crearProcesoSeleccionEstudiante(Proceso proceso) throws Exception;
	
	public void crearCursoTitulacionEstudiante(CursoTitulacionEstudiante cursoTitulacionEstudiante) throws Exception;
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudiante() throws Exception;
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudianteTema(Integer curCodigo) throws Exception;
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudianteEstudiante(Integer curCodigo, Integer temCodigo) throws Exception;
	
	public Collection<Proceso> obtenerProcesoEstudiante()throws Exception;
	public void crearProceso(Proceso proceso) throws Exception;
	public void actualizarPersonaCarreraEstudiante(PersonaCarrera personaCarrera) throws Exception;
	
	public Collection<Persona> obtenerPeronaUsuarioRol()throws Exception;
	
}
