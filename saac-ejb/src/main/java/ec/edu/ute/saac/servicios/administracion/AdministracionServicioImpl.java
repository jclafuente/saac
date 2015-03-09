package ec.edu.ute.saac.servicios.administracion;

import java.util.Collection;

import javax.ejb.EJB;

import ec.edu.ute.saac.entidades.AreaInvestigacion;
import ec.edu.ute.saac.entidades.Carrera;
import ec.edu.ute.saac.entidades.Catalogo;
import ec.edu.ute.saac.entidades.CursoTitulacion;
import ec.edu.ute.saac.entidades.CursoTitulacionEstudiante;
import ec.edu.ute.saac.entidades.Facultad;
import ec.edu.ute.saac.entidades.LineaInvestigacion;
import ec.edu.ute.saac.entidades.Periodos;
import ec.edu.ute.saac.entidades.Persona;
import ec.edu.ute.saac.entidades.Proceso;
import ec.edu.ute.saac.entidades.Rol;
import ec.edu.ute.saac.entidades.SeleccionTema;
import ec.edu.ute.saac.entidades.TemasTitulacion;
import ec.edu.ute.saac.entidades.Usuario;
import ec.edu.ute.saac.entidades.UsuarioRol;
import ec.edu.ute.saac.servicios.AreaInvestigacionFacade;
import ec.edu.ute.saac.servicios.CarreraFacade;
import ec.edu.ute.saac.servicios.CatalogoFacade;
import ec.edu.ute.saac.servicios.CursoTitulacionEstudianteFacade;
import ec.edu.ute.saac.servicios.CursoTitulacionFacade;
import ec.edu.ute.saac.servicios.FacultadFacade;
import ec.edu.ute.saac.servicios.LineaInvestigacionFacade;
import ec.edu.ute.saac.servicios.PeriodosFacade;
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
	public Collection<CursoTitulacion> obtenerCursoTitulacion()
			throws Exception {
		// TODO Auto-generated method stub
		return cursoTitulacionFacade.obtenerCursoTitulacion();
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
	public Collection<Proceso> obtenerProcesoEstudiante() throws Exception {
		// TODO Auto-generated method stub
		return procesoFacade.obtenerProcesoEstudiante();
	}

	@Override
	public void crearProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		procesoFacade.create(proceso);
	}

	


	
	


	

}
