
package Modelo;

import java.time.LocalDate;


//Clase auxiliar para los datos Visita-General de la VistaPrincipal =>
public class AuxVisitaGeneral {
    
    private LocalDate fechaVisita;
    private String nombreTecnico;
    private String apellidoTecnico;
    private int nVisita;
    private String nombreCliente;
    private String dni;
    private String domicilio;
    private Long idVisita;
    private Long idGeneral;
    private String codigo;

    public AuxVisitaGeneral() {
    }

    public AuxVisitaGeneral(LocalDate fechaVisita, String nombreTecnico, String apellidoTecnico, int nVisita, String nombreCliente, String dni, String domicilio, Long idVisita, Long idGeneral, String codigo) {
        this.fechaVisita = fechaVisita;
        this.nombreTecnico = nombreTecnico;
        this.apellidoTecnico = apellidoTecnico;
        this.nVisita = nVisita;
        this.nombreCliente = nombreCliente;
        this.dni = dni;
        this.domicilio = domicilio;
        this.idVisita = idVisita;
        this.idGeneral = idGeneral;
        this.codigo = codigo;
    }

    public LocalDate getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
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

    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
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

    @Override
    public String toString() {
        return "fechaVisita:" + fechaVisita + "\nnombreTecnico: " + nombreTecnico + "\napellidoTecnico: " + apellidoTecnico + "\nnVisita: " + nVisita + 
                "\nnombreCliente: " + nombreCliente + "\ndni: " + dni + 
                "\ndomicilio: " + domicilio + "\nidVisita: " + idVisita + "\nidGeneral: " + idGeneral + "\ncodigo: " + codigo;
    }
    
  
}
