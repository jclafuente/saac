package ec.edu.ute.saac.pojo;

public class ItemRevision {
	
	private String id;
    private String aspecto;
    private String Criterio;
    private String aprobacion;
    private String observacion;

    public ItemRevision() {
    }

    public ItemRevision(String id, String aspecto, String Criterio, String aprobacion, String observacion) {
        this.id = id;
        this.aspecto = aspecto;
        this.Criterio = Criterio;
        this.aprobacion = aprobacion;
        this.observacion = observacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAprobacion() {
        return aprobacion;
    }

    public String getAspecto() {
        return aspecto;
    }

    public String getCriterio() {
        return Criterio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setAprobacion(String aprobacion) {
        this.aprobacion = aprobacion;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public void setCriterio(String Criterio) {
        this.Criterio = Criterio;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
