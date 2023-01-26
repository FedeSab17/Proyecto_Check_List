
package Modelo;

import java.time.LocalDate;
import java.util.List;


public class General {
    
   
    private Long idGeneral;
    private String codigo;
    private String nombreCliente;
    private String dni;
    private String domicilio;
    private String usoEdificio;
    private double alturaEdificio;
    private double m2Cubierta;
    private double m2Muro;
    private String alcance;
    private int duracionObra;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    
    
    //Variable de relacion bidireccional General:Vista (1:N) =>
    private List<Visita> listaVisita;
    
    //Variable de relacion bidireccional General:Conclusion (1:1) =>
    private Conclusion conclusion;


    public General() {
    }

    public General(String codigo, String nombreCliente, String dni, String domicilio, String usoEdificio, double alturaEdificio, double m2Cubierta, double m2Muro, String alcance, int duracionObra, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado) {
        this.codigo = codigo;
        this.nombreCliente = nombreCliente;
        this.dni = dni;
        this.domicilio = domicilio;
        this.usoEdificio = usoEdificio;
        this.alturaEdificio = alturaEdificio;
        this.m2Cubierta = m2Cubierta;
        this.m2Muro = m2Muro;
        this.alcance = alcance;
        this.duracionObra = duracionObra;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
    }

    public General(Long idGeneral, String codigo, String nombreCliente, String dni, String domicilio, String usoEdificio, double alturaEdificio, double m2Cubierta, double m2Muro, String alcance, int duracionObra, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado) {
        this.idGeneral = idGeneral;
        this.codigo = codigo;
        this.nombreCliente = nombreCliente;
        this.dni = dni;
        this.domicilio = domicilio;
        this.usoEdificio = usoEdificio;
        this.alturaEdificio = alturaEdificio;
        this.m2Cubierta = m2Cubierta;
        this.m2Muro = m2Muro;
        this.alcance = alcance;
        this.duracionObra = duracionObra;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
    }
    


    public Long getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(Long idGeneral) {
        this.idGeneral = idGeneral;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getUsoEdificio() {
        return usoEdificio;
    }

    public void setUsoEdificio(String usoEdificio) {
        this.usoEdificio = usoEdificio;
    }

    public double getAlturaEdificio() {
        return alturaEdificio;
    }

    public void setAlturaEdificio(double alturaEdificio) {
        this.alturaEdificio = alturaEdificio;
    }

    public double getM2Cubierta() {
        return m2Cubierta;
    }

    public void setM2Cubierta(double m2Cubierta) {
        this.m2Cubierta = m2Cubierta;
    }

    public double getM2Muro() {
        return m2Muro;
    }

    public void setM2Muro(double m2Muro) {
        this.m2Muro = m2Muro;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public int getDuracionObra() {
        return duracionObra;
    }

    public void setDuracionObra(int duracionObra) {
        this.duracionObra = duracionObra;
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
    
    
    public List<Visita> getListaVisita() {
        return listaVisita;
    }

    public void setListaVisita(List<Visita> listaVisita) {
        this.listaVisita = listaVisita;
    }

    public Conclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }
    

    @Override
    public String toString() {
        return "idGeneral: " + idGeneral + "\ncodigo: " + codigo + "\nnombreCliente: " + nombreCliente + "\ndni: " + dni + "\ndomicilio: " + 
                domicilio + "\nusoEdificio: " + usoEdificio + "\nalturaEdificio: " + alturaEdificio + "\nm2Cubierta: " + m2Cubierta + "\nm2Muro: " + m2Muro + "\nalcance: " + 
                alcance + "\nduracionObra: " + duracionObra + "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + "\nestado: " + estado;
    }

    
    
}
