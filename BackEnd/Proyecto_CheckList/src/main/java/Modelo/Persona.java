
package Modelo;


import java.time.LocalDate;
import java.util.List;


public class Persona {
    
    private Long idPersona;
    private int personasTotal;
    private int nGremios;
    private String gremioEnfoque;
    private String vestimentaOk;
    private String calzadoOk;
    private String utilizanEpp;
    private String herramientasOk;
    private String seguridadOk;
    private String trabajoAltura;
    private String banosOk;
    private String comerOk;
    private int edadJoven;
    private int edadViejo;
    private int rangoMin;
    private int rangoMax;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idVisita;
    
    //Variable de relacion bidireccional Visita:Persona (1:1) =>
    private Visita visita;
    
    //Variable de relacion bidireccional Persona:Gremio (1:N) =>
    private List<Gremio> listaGremio;

    public Persona() {
    }

    public Persona(Long idPersona, int personasTotal, int nGremios, String gremioEnfoque, String vestimentaOk, String calzadoOk, String utilizanEpp, String herramientasOk, String seguridadOk, String trabajoAltura, String banosOk, String comerOk, int edadJoven, int edadViejo, int rangoMin, int rangoMax, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.idPersona = idPersona;
        this.personasTotal = personasTotal;
        this.nGremios = nGremios;
        this.gremioEnfoque = gremioEnfoque;
        this.vestimentaOk = vestimentaOk;
        this.calzadoOk = calzadoOk;
        this.utilizanEpp = utilizanEpp;
        this.herramientasOk = herramientasOk;
        this.seguridadOk = seguridadOk;
        this.trabajoAltura = trabajoAltura;
        this.banosOk = banosOk;
        this.comerOk = comerOk;
        this.edadJoven = edadJoven;
        this.edadViejo = edadViejo;
        this.rangoMin = rangoMin;
        this.rangoMax = rangoMax;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Persona(int personasTotal, int nGremios, String gremioEnfoque, String vestimentaOk, String calzadoOk, String utilizanEpp, String herramientasOk, String seguridadOk, String trabajoAltura, String banosOk, String comerOk, int edadJoven, int edadViejo, int rangoMin, int rangoMax, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.personasTotal = personasTotal;
        this.nGremios = nGremios;
        this.gremioEnfoque = gremioEnfoque;
        this.vestimentaOk = vestimentaOk;
        this.calzadoOk = calzadoOk;
        this.utilizanEpp = utilizanEpp;
        this.herramientasOk = herramientasOk;
        this.seguridadOk = seguridadOk;
        this.trabajoAltura = trabajoAltura;
        this.banosOk = banosOk;
        this.comerOk = comerOk;
        this.edadJoven = edadJoven;
        this.edadViejo = edadViejo;
        this.rangoMin = rangoMin;
        this.rangoMax = rangoMax;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public int getPersonasTotal() {
        return personasTotal;
    }

    public void setPersonasTotal(int personasTotal) {
        this.personasTotal = personasTotal;
    }

    public int getnGremios() {
        return nGremios;
    }

    public void setnGremios(int nGremios) {
        this.nGremios = nGremios;
    }

    public String getGremioEnfoque() {
        return gremioEnfoque;
    }

    public void setGremioEnfoque(String gremioEnfoque) {
        this.gremioEnfoque = gremioEnfoque;
    }

    public String getVestimentaOk() {
        return vestimentaOk;
    }

    public void setVestimentaOk(String vestimentaOk) {
        this.vestimentaOk = vestimentaOk;
    }

    public String getCalzadoOk() {
        return calzadoOk;
    }

    public void setCalzadoOk(String calzadoOk) {
        this.calzadoOk = calzadoOk;
    }

    public String getUtilizanEpp() {
        return utilizanEpp;
    }

    public void setUtilizanEpp(String utilizanEpp) {
        this.utilizanEpp = utilizanEpp;
    }

    public String getHerramientasOk() {
        return herramientasOk;
    }

    public void setHerramientasOk(String herramientasOk) {
        this.herramientasOk = herramientasOk;
    }

    public String getSeguridadOk() {
        return seguridadOk;
    }

    public void setSeguridadOk(String seguridadOk) {
        this.seguridadOk = seguridadOk;
    }

    public String getTrabajoAltura() {
        return trabajoAltura;
    }

    public void setTrabajoAltura(String trabajoAltura) {
        this.trabajoAltura = trabajoAltura;
    }

    public String getBanosOk() {
        return banosOk;
    }

    public void setBanosOk(String banosOk) {
        this.banosOk = banosOk;
    }

    public String getComerOk() {
        return comerOk;
    }

    public void setComerOk(String comerOk) {
        this.comerOk = comerOk;
    }

    public int getEdadJoven() {
        return edadJoven;
    }

    public void setEdadJoven(int edadJoven) {
        this.edadJoven = edadJoven;
    }

    public int getEdadViejo() {
        return edadViejo;
    }

    public void setEdadViejo(int edadViejo) {
        this.edadViejo = edadViejo;
    }

    public int getRangoMin() {
        return rangoMin;
    }

    public void setRangoMin(int rangoMin) {
        this.rangoMin = rangoMin;
    }

    public int getRangoMax() {
        return rangoMax;
    }

    public void setRangoMax(int rangoMax) {
        this.rangoMax = rangoMax;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
    }
    
    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public List<Gremio> getListaGremio() {
        return listaGremio;
    }

    public void setListaGremio(List<Gremio> listaGremio) {
        this.listaGremio = listaGremio;
    }


    @Override
    public String toString() {
        return "idPersona: " + idPersona + "\npersonasTotal: " + personasTotal + "\nGremios: " + nGremios + "\ngremioEnfoque: " + gremioEnfoque + 
                "\nvestimentaOk: " + vestimentaOk + "\ncalzadoOk: " + calzadoOk + "\nutilizanEpp: " + utilizanEpp + "\nherramientasOk: " + herramientasOk + 
                "\nseguridadOk: " + seguridadOk + "\ntrabajoAltura: " + trabajoAltura + "\nbanosOk: " + banosOk + "\ncomerOk: " + comerOk + "\nedadJoven: " + edadJoven + 
                "\nedadViejo: " + edadViejo + "\nrangoMin: " + rangoMin + "\nrangoMax: " + rangoMax + "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + 
                "\nfechaBaja: " + fechaBaja + "\nestado: " + estado + "\nidVisita: " + idVisita;
    }

    
}
