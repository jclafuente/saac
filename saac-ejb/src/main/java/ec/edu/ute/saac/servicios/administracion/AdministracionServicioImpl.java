package ec.edu.ute.saac.servicios.administracion;

import java.util.Collection;

import javax.ejb.EJB;

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.Contenido;
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
import ec.edu.ute.saac.servicios.AreaInvestigacionFacade;
import ec.edu.ute.saac.servicios.CarreraFacade;
import ec.edu.ute.saac.servicios.CatalogoFacade;
import ec.edu.ute.saac.servicios.ContenidoFacade;
import ec.edu.ute.saac.servicios.CursoTitulacionEstudianteFacade;
import ec.edu.ute.saac.servicios.CursoTitulacionFacade;
import ec.edu.ute.saac.servicios.FacultadFacade;
import ec.edu.ute.saac.servicios.LineaInvestigacionFacade;
import ec.edu.ute.saac.servicios.PeriodosFacade;
import ec.edu.ute.saac.servicios.PersonaCarreraFacade;
import ec.edu.ute.saac.servicios.PersonaFacade;
import ec.edu.ute.saac.servicios.ProcesoFacade;
import ec.edu.ute.saac.servicios.RolFacade;
import ec.edu.ute.saac.servicios.SeleccionTemaFacade;
import ec.edu.ute.saac.servicios.TemasTitulacionFacade;
import ec.edu.ute.saac.servicios.UsuarioFacade;
import ec.edu.ute.saac.servicios.UsuarioRolFacade;

public class AdministracionServicioImpl implements IAdministracionServicio {

	@EJB
	private FacultadFacade facultadFacade;
	@EJB
	private CarreraFacade carreraFacade;
	@EJB
	private RolFacade rolFacade;
	@EJB
	private PersonaFacade personaFacade;
	@EJB
	private LineaInvestigacionFacade lineaInvestigacionFacade;
	@EJB
	private AreaInvestigacionFacade areaInvestigacionFacade;
	@EJB
	private TemasTitulacionFacade temaTitulacionFacade;
	@EJB
	private CursoTitulacionFacade cursoTitulacionFacade;
	@EJB
	private UsuarioRolFacade usuarioRolFacade;
	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private PeriodosFacade periodoFacade;
	@EJB
	private CatalogoFacade catalogoFacade;
	@EJB
	private SeleccionTemaFacade seleccionTemaFacade;
	@EJB
	private ProcesoFacade procesoFacade;
	@EJB
	private CursoTitulacionEstudianteFacade cursoTitulacionEstudianteFacade;
	@EJB
	private PersonaCarreraFacade personaCarreraFacade;
	@EJB
	private ContenidoFacade contenidoFacade;
	
	@Override
	public Collection<Facultad> findAll() {
		return facultadFacade.findAll();
	}

	@Override
	public Collection<Carrera> findCarreraFacultad() {
		return carreraFacade.obtenerCarreraFacultad();
	}

	@Override
	public Collection<Rol> findAllRol() {
		return rolFacade.findAll();
	}

	@Override
	public void actualizarFacultad(Facultad facultad) throws Exception {
		// TODO Auto-generated method stub
		facultadFacade.edit(facultad);

	}

	@Override
	public void crearRol(Rol rol) throws Exception {
		// TODO Auto-generated method stub
		rolFacade.create(rol);

	}

	@Override
	public void crearCarrera(Carrera carrera) throws Exception {
		// TODO Auto-generated method stub
		carreraFacade.create(carrera);

	}

	@Override
	public void actualizarCarrera(Carrera carrera) throws Exception {
		// TODO Auto-generated method stub
		carreraFacade.edit(carrera);

	}

	@Override
	public void crearFacultad(Facultad facultad) throws Exception {
		// TODO Auto-generated method stub

		facultadFacade.create(facultad);

	}

	@Override
	public void actualizarRol(Rol rol) throws Exception {
		// TODO Auto-generated method stub
		rolFacade.edit(rol);
	}

	@Override
	public Collection<Persona> obtenerPersona() throws Exception {
		// TODO Auto-generated method stub
		return personaFacade.findAll();
	}

	@Override
	public Collection<Carrera> obtenerCarrera() {
		// TODO Auto-generated method stub
		return carreraFacade.findAll();
	}

	@Override
	public Collection<LineaInvestigacion> obtenerLineaInvestigacionCarrera(
			Integer carCodigo) throws Exception {
		// TODO Auto-generated method stub
		return lineaInvestigacionFacade
				.obtenerLineaInvestigacionCarrera(carCodigo);
	}

	@Override
	public Collection<AreaInvestigacion> obtenerAreaInvestigacionLineaInvestigacion(
			Integer linInvCodigo) throws Exception {
		// TODO Auto-generated method stub
		return areaInvestigacionFacade
				.obtenerAreaInvestigacionLineaInvestigacion(linInvCodigo);
	}

	@Override
	public Collection<Persona> obtenerPersona(Integer carCodigo,
			Integer linInvCodigo, Integer areaInvCodigo) throws Exception {
		// TODO Auto-generated method stub
		return personaFacade.obtenerPersona(carCodigo, linInvCodigo,
				areaInvCodigo);
	}

	@Override
	public Collection<TemasTitulacion> obtenerTemaTitulacion(Integer carCodigo,
			Integer linInvCodigo, Integer areaInvCodigo) throws Exception {
		// TODO Auto-generated method stub
		return temaTitulacionFacade.obtenerTemaTitulacion(carCodigo,
				linInvCodigo, areaInvCodigo);
	}

	@Override
	public void actualizarTemaTitulacion(TemasTitulacion temasTitulacion,
			String estado) throws Exception {
		// TODO Auto-generated method stub
		temaTitulacionFacade.actualizarEstadoTemaTitulacion(temasTitulacion,
				estado);
	}

	@Override
	public void crearCursoTitulacion(CursoTitulacion cursoTitulacion)
			throws Exception {
		// TODO Auto-generated method stub
		cursoTitulacionFacade.create(cursoTitulacion);
	}

	@Override
	public void crearPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub
		personaFacade.create(persona);
	}

	@Override
	public void actualizarTemaTitulacion(TemasTitulacion temaTitulacion)
			throws Exception {
		// TODO Auto-generated method stub
		temaTitulacionFacade.edit(temaTitulacion);
	}

	@Override
	public void crearTemaTitulacion(TemasTitulacion temaTitulacion)
			throws Exception {
		// TODO Auto-generated method stub
		temaTitulacionFacade.create(temaTitulacion);

	}

	@Override
	public void asignarEstadoTemaTitulacion(TemasTitulacion temaTitulacion,
			String estado) throws Exception {
		// TODO Auto-generated method stub
		temaTitulacionFacade
				.asignarEstadoTemaTitulacion(temaTitulacion, estado);

	}

	@Override
	public Collection<Persona> obtenerPersonaUsuario() throws Exception {
		// TODO Auto-generated method stub
		return personaFacade.obtenerPersonaDocente();
	}

	@Override
	public void eliminarCursoTitulacion(CursoTitulacion cursoTitulacion)
			throws Exception {
		// TODO Auto-generated method stub
		cursoTitulacionFacade.remove(cursoTitulacion);
	}

	@Override
	public Collection<Periodos> obtenerPeriodo() throws Exception {
		// TODO Auto-generated method stub
		return periodoFacade.findAll();
	}

	@Override
	public Collection<Usuario> obtenerUsuario() throws Exception {
		// TODO Auto-generated method stub
		return usuarioFacade.obtenerUsuarioDocente();
	}

	@Override
	public Collection<Catalogo> obtenerCatalogoEstadoCiv() throws Exception {
		// TODO Auto-generated method stub
		return catalogoFacade.obtenerCatalogoEstCiv();
	}

	@Override
	public Collection<Catalogo> obtenerCatalogoNac() throws Exception {
		// TODO Auto-generated method stub
		return catalogoFacade.obtenerCatalogoNac();
	}

	@Override
	public Collection<Catalogo> obtenerCatalogoGenero() throws Exception {
		// TODO Auto-generated method stub
		return catalogoFacade.obtenerCatalogoGenero();
	}

	@Override
	public Collection<Usuario> obtenerUsuarioEstudiante() throws Exception {
		// TODO Auto-generated method stub
		return usuarioFacade.obtenerUsuarioEstudiante();
	}

	@Override
	public Collection<UsuarioRol> obtenerUsuarioRolEst() throws Exception {
		// TODO Auto-generated method stub
		return usuarioRolFacade.obtenerUsuarioDocente();
	}

	@Override
	public Collection<TemasTitulacion> obtenerTemaTitulacionAprobado(
			Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo)
			throws Exception {
		// TODO Auto-generated method stub
		return temaTitulacionFacade.obtenerTemaTitulacionAprobado(carCodigo,
				linInvCodigo, areaInvCodigo);
	}

	

	@Override
	public void actualizarCursoTitulacion(CursoTitulacion cursoTitulacion)
			throws Exception {
		// TODO Auto-generated method stub
		cursoTitulacionFacade.edit(cursoTitulacion);
	}

	@Override
	public void crearUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		usuarioFacade.create(usuario);
	}

	@Override
	public void crearUsuarioRol(UsuarioRol usuarioRol) throws Exception {
		// TODO Auto-generated method stub
		usuarioRolFacade.create(usuarioRol);
	}

	@Override
	public Collection<UsuarioRol> obtenerPersonaEstudiante() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<SeleccionTema> obtenerSeleccionTemaAprobado(
			Integer carCodigo, Integer linInvCodigo, Integer areaInvCodigo, Integer temaCodigo)
			throws Exception {
		// TODO Auto-generated method stub
		return seleccionTemaFacade.obtenerSeleccionTemaAprobado(carCodigo,
				linInvCodigo, areaInvCodigo, temaCodigo);
	}

	@Override
	public Collection<SeleccionTema> obtenerEstudianteSeleccionTema(
			Integer perCodigo) throws Exception {
		// TODO Auto-generated method stub
		return seleccionTemaFacade.obtenerEstudianteSeleccionTema(perCodigo);
	}

	@Override
	public Collection<TemasTitulacion> obtenerSeleccionTemaAreaInvestigacion(
			Integer areInvCodigo) throws Exception {
		// TODO Auto-generated method stub
		return temaTitulacionFacade.obtenerSeleccionTemaAreaInvestigacion(areInvCodigo);
	}

	@Override
	public void crearProcesoSeleccionEstudiante(Proceso proceso)
			throws Exception {
		// TODO Auto-generated method stub
		procesoFacade.create(proceso);
	}

	@Override
	public Collection<Persona> obtenerPersonaUsuarioEstudiante()
			throws Exception {
		// TODO Auto-generated method stub
		return personaFacade.obtenerPersonaEstudiante();
	}

	@Override
	public Collection<SeleccionTema> obtenerEstudiantesSeleccionTema()
			throws Exception {
		// TODO Auto-generated method stub
		return seleccionTemaFacade.obtenerEstudiantesSeleccionTema();
	}

	@Override
	public void crearCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante)
			throws Exception {
		// TODO Auto-generated method stub
		
		cursoTitulacionEstudianteFacade.create(cursoTitulacionEstudiante);
	}

	@Override
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudiante()
			throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionEstudianteFacade.obtenerCursoTitulacionEstudiantes();
	}

	@Override
	public void eliminarCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante)
			throws Exception {
		// TODO Auto-generated method stub
		
		cursoTitulacionEstudianteFacade.remove(cursoTitulacionEstudiante);
	}

	@Override
	public void actualizarCursoTitulacionEstudiante(
			CursoTitulacionEstudiante cursoTitulacionEstudiante)
			throws Exception {
		// TODO Auto-generated method stub
		cursoTitulacionEstudianteFacade.edit(cursoTitulacionEstudiante);
		
	}


	@Override
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudianteTema(
			Integer curCodigo) throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionEstudianteFacade.obtenerCursoTitulacionEstudianteTema(curCodigo);
	}

	@Override
	public Collection<CursoTitulacionEstudiante> obtenerCursoTitulacionEstudianteEstudiante(
			Integer curCodigo, Integer temCodigo) throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionEstudianteFacade.obtenerCursoTitulacionEstudianteEstudiante(curCodigo, temCodigo);
	}

	@Override
	public void crearProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		procesoFacade.create(proceso);
	}

	@Override
	public Collection<LineaInvestigacion> obtenerLineaInvestigacion()
			throws Exception {
		// TODO Auto-generated method stub
		return lineaInvestigacionFacade.findAll();
	}

	@Override
	public Collection<AreaInvestigacion> obteneraAreaInvestigacion()
			throws Exception {
		// TODO Auto-generated method stub
		return areaInvestigacionFacade.findAll();
	}

	@Override
	public Collection<CursoTitulacion> obtenerCursoTitulacion(Integer periodo)
			throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionFacade.obtenerCursoTitulacionPeriodo(periodo);
	}

	@Override
	public Collection<CursoTitulacion> obtenerCursoTitulacion() throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionFacade.obtenerCursoTitulacion();
	}

	@Override
	public Collection<Facultad> obtenerFacultad() {
		// TODO Auto-generated method stub
		return facultadFacade.findAll();
	}

	@Override
	public Collection<Carrera> obtenerCarreraFacultad(Integer facCodigo)
			throws Exception {
		// TODO Auto-generated method stub
		return carreraFacade.obtenerCarreraFacultad();
	}

	@Override
	public Collection<PersonaCarrera> obtenerPersonaCarrera(Integer facCodigo,
			Integer carCodigo) throws Exception {
		// TODO Auto-generated method stub
		return personaCarreraFacade.obtenerPersonaCarrera(facCodigo,carCodigo);
	}

	@Override
	public void actualizarPersonaCarreraEstudiante(PersonaCarrera personaCarrera)
			throws Exception {
		// TODO Auto-generated method stub
		
		personaCarreraFacade.edit(personaCarrera);
	}

	@Override
	public Collection<Persona> obtenerPersonaCarreraFacultad(Integer facCodigo,
			Integer carCodigo) throws Exception {
		// TODO Auto-generated method stub
		return personaFacade.obtenerPersonaCarrera(facCodigo, carCodigo); 
	}

	@Override
	public void actualizarPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub
		
		personaFacade.edit(persona);
	}

	@Override
	public Collection<Persona> obtenerPeronaUsuarioRol() throws Exception {
		// TODO Auto-generated method stub
		return personaFacade.obtenerPersonaUsuarioRol();
	}

	@Override
	public Collection<Usuario> obtenerUsuarios() throws Exception {
		// TODO Auto-generated method stub
		return usuarioFacade.findAll();
	}

	@Override
	public Object obtenerPassUsuario(Integer usuCodigo) throws Exception {
		// TODO Auto-generated method stub
		return usuarioFacade.obtenerPassUsuario(usuCodigo);
	}

	@Override
	public Collection<CursoTitulacion> obtenerCursoTitulacionCodigo(
			Integer codCursoTit) throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionFacade.obtenerCursoTitulacionCodigo(codCursoTit);
	}

	@Override
	public Collection<Proceso> obtenerProcesoCursoTitulacion(Integer curCodigo) throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoCursoTitulacion(curCodigo);
	}

	@Override
	public Collection<Proceso> obtenerProcesoEstudiante(Integer curCodigo)
			throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoEstudiante(curCodigo);
	}

	@Override
	public void eliminarProcesoEstudiante(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		procesoFacade.remove(proceso);
	}  

	@Override
	public void actualizarProcesoEstudiante(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		procesoFacade.edit(proceso);
	}

	@Override
	public Collection<TemasTitulacion> obtenerTemaTitulacionDocente(
			Integer perCodigo) throws Exception {
		// TODO Auto-generated method stub
		return temaTitulacionFacade.obtenerTemaTitulacionDocente(perCodigo);
	}

	@Override
	public Collection<Proceso> obtenerProcesoPeriodo() throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoPeriodo();
	}

	@Override
	public Collection<Proceso> obtenerProcesoPeriodoEstudiante(Integer prdCodigo)
			throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoPeriodoEstudiante(prdCodigo);
	}

	@Override
	public Collection<Proceso> obtenerProcesoEstudianteLoggin()
			throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoEstudianteLoggin();
	}

	@Override
	public Collection<Proceso> obtenerProcesoTemaTitulacionEstudiante(
			Integer perCodigo) throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoTemaTitulacionEstudiante(perCodigo);
	}

	
	@Override
	public String obtenerJustificacion(Integer prcCodigo) throws Exception {
		// TODO Auto-generated method stub
		return contenidoFacade.obtenerJustificacion(prcCodigo);
	}

	@Override
	public Collection<Proceso> obtenerProblema(Integer prcCodigo)
			throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProblema(prcCodigo);
	}

	


	

	

	

	


}
