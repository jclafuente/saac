/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ute.saac.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author JNK
 */
@Entity
@Table(name = "comite", catalog = "saac", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"com_codigo"})})
@NamedQueries({
    @NamedQuery(name = "Comite.findAll", query = "SELECT c FROM Comite c"),
    @NamedQuery(name = "Comite.findByComCodigo", query = "SELECT c FROM Comite c WHERE c.comCodigo = :comCodigo")})
public class Comite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "com_codigo", nullable = false)
    private Integer comCodigo;
    @OneToMany(mappedBy = "comite", fetch = FetchType.LAZY)
    private List<ParticipantesProceso> participantesProcesoList;
    @JoinColumn(name = "tipo_comite", referencedColumnName = "tip_com_codigo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoComite tipoComite;

    public Comite() {
    }

    public Comite(Integer comCodigo) {
        this.comCodigo = comCodigo;
    }

    public Integer getComCodigo() {
        return comCodigo;
    }

    public void setComCodigo(Integer comCodigo) {
        this.comCodigo = comCodigo;
    }

    public List<ParticipantesProceso> getParticipantesProcesoList() {
        return participantesProcesoList;
    }

    public void setParticipantesProcesoList(List<ParticipantesProceso> participantesProcesoList) {
        this.participantesProcesoList = participantesProcesoList;
    }

    public TipoComite getTipoComite() {
        return tipoComite;
    }

    public void setTipoComite(TipoComite tipoComite) {
        this.tipoComite = tipoComite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comCodigo != null ? comCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comite)) {
            return false;
        }
        Comite other = (Comite) object;
        if ((this.comCodigo == null && other.comCodigo != null) || (this.comCodigo != null && !this.comCodigo.equals(other.comCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.ute.saac.entidades.Comite[ comCodigo=" + comCodigo + " ]";
    }
    
}
