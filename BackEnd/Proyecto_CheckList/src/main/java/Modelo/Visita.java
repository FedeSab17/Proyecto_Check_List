
package Modelo;

import java.time.LocalDate;


public class Visita {
    
    
    private Long idVisita;
    private LocalDate fecha;
    private String nombreTecnico;
    private String apellidoTecnico;
    private int nVisita;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idGeneral;
    
    //Variable de relacion bidireccional Visita:General (1:N) =>
    private General general;
    
    //Variable de relacion bidireccional Visita:Material (1:1) =>
    private Material material;

    //Variable de relacion bidireccional Visita:Material (1:1) =>
    private Persona persona;
    
    //Variable de relacion bidireccional Visita:Humeda (1:1) =>
    private Humeda humeda;
    
    //Variable de relacion bidireccional Visita:Seco (1:1) =>
    private Seco seco;
    
    //Variable de relacion bidireccional Visita:Panel (1:1) =>
    private Panel panel;
    
    //Variable de relacion bidireccional Visita:RedAgua (1:1) =>
    private RedAgua redAgua;
    
    //Variable de relacion bidireccional Visita:RedGas (1:1) =>
    private RedGas redGas;
    
    //Variable de relacion bidireccional Visita:RedElectricidad (1:1) =>
    private RedElectricidad redElectricidad;
    
    //Variable de relacion bidireccional Visita:Abertura (1:1) =>
    private Abertura abertura;
    

    public Visita() {
    }
    
    public Visita(Long idVisita, LocalDate fecha, String nombreTecnico, String apellidoTecnico, int nVisita, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idGeneral) {
        this.idVisita = idVisita;
        this.fecha = fecha;
        this.nombreTecnico = nombreTecnico;
        this.apellidoTecnico = apellidoTecnico;
        this.nVisita = nVisita;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idGeneral = idGeneral;
    }

    public Visita(LocalDate fecha, String nombreTecnico, String apellidoTecnico, int nVisita, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idGeneral) {
        this.fecha = fecha;
        this.nombreTecnico = nombreTecnico;
        this.apellidoTecnico = apellidoTecnico;
        this.nVisita = nVisita;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idGeneral = idGeneral;
    }

    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getApellidoTecnico() {
        return apellidoTecnico;
    }

    public void setApellidoTecnico(String apellidoTecnico) {
        this.apellidoTecnico = apellidoTecnico;
    }

    public int getnVisita() {
        return nVisita;
    }

    public void setnVisita(int nVisita) {
        this.nVisita = nVisita;
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

    public Long getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(Long idGeneral) {
        this.idGeneral = idGeneral;
    }
    
    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Humeda getHumeda() {
        return humeda;
    }

    public void setHumeda(Humeda humeda) {
        this.humeda = humeda;
    }

    public Seco getSeco() {
        return seco;
    }

    public void setSeco(Seco seco) {
        this.seco = seco;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public RedAgua getRedAgua() {
        return redAgua;
    }

    public void setRedAgua(RedAgua redAgua) {
        this.redAgua = redAgua;
    }

    public RedGas getRedGas() {
        return redGas;
    }

    public void setRedGas(RedGas redGas) {
        this.redGas = redGas;
    }

    public RedElectricidad getRedElectricidad() {
        return redElectricidad;
    }

    public void setRedElectricidad(RedElectricidad redElectricidad) {
        this.redElectricidad = redElectricidad;
    }

    public Abertura getAbertura() {
        return abertura;
    }

    public void setAbertura(Abertura abertura) {
        this.abertura = abertura;
    }

   

    @Override
    public String toString() {
        return "idVisita: " + idVisita + "\nfecha: " + fecha + "\nnombreTecnico: " + nombreTecnico + "\napellidoTecnico: " + apellidoTecnico + "\nnVisita: " + nVisita + 
                "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + "\nestado: " + estado + "\nidGeneral: " + idGeneral;
    }
 

}
