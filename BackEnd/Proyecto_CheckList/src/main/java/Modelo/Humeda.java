
package Modelo;

import java.time.LocalDate;


public class Humeda {
    
    
    private Long idHumeda;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private double m2Piso;
    private int pisoPerson;
    private double metros;
    private int metrosPerson;
    private double m2Muro;
    private int muroPerson;
    private double m2Cubierta;
    private int cubiertaPerson;
    private double metrosLineales;
    private int linealesPerson;
    private int diasCaidos;
    private String motivo;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idVisita;
    
    //Variable de relacion bidireccional Visita:Humeda (1:1) =>
    private Visita visita;

    public Humeda() {
    }

    public Humeda(Long idHumeda, LocalDate fechaInicial, LocalDate fechaFinal, double m2Piso, int pisoPerson, double metros, int metrosPerson, double m2Muro, int muroPerson, double m2Cubierta, int cubiertaPerson, double metrosLineales, int linealesPerson, int diasCaidos, String motivo, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        
        this.idHumeda = idHumeda;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.m2Piso = m2Piso;
        this.pisoPerson = pisoPerson;
        this.metros = metros;
        this.metrosPerson = metrosPerson;
        this.m2Muro = m2Muro;
        this.muroPerson = muroPerson;
        this.m2Cubierta = m2Cubierta;
        this.cubiertaPerson = cubiertaPerson;
        this.metrosLineales = metrosLineales;
        this.linealesPerson = linealesPerson;
        this.diasCaidos = diasCaidos;
        this.motivo = motivo;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
        
    }

    public Humeda(LocalDate fechaInicial, LocalDate fechaFinal, double m2Piso, int pisoPerson, double metros, int metrosPerson, double m2Muro, int muroPerson, double m2Cubierta, int cubiertaPerson, double metrosLineales, int linealesPerson, int diasCaidos, String motivo, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.m2Piso = m2Piso;
        this.pisoPerson = pisoPerson;
        this.metros = metros;
        this.metrosPerson = metrosPerson;
        this.m2Muro = m2Muro;
        this.muroPerson = muroPerson;
        this.m2Cubierta = m2Cubierta;
        this.cubiertaPerson = cubiertaPerson;
        this.metrosLineales = metrosLineales;
        this.linealesPerson = linealesPerson;
        this.diasCaidos = diasCaidos;
        this.motivo = motivo;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;

    }

    public Long getIdHumeda() {
        return idHumeda;
    }

    public void setIdHumeda(Long idHumeda) {
        this.idHumeda = idHumeda;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getM2Piso() {
        return m2Piso;
    }

    public void setM2Piso(double m2Piso) {
        this.m2Piso = m2Piso;
    }

    public int getPisoPerson() {
        return pisoPerson;
    }

    public void setPisoPerson(int pisoPerson) {
        this.pisoPerson = pisoPerson;
    }

    public double getMetros() {
        return metros;
    }

    public void setMetros(double metros) {
        this.metros = metros;
    }

    public int getMetrosPerson() {
        return metrosPerson;
    }

    public void setMetrosPerson(int metrosPerson) {
        this.metrosPerson = metrosPerson;
    }

    public double getM2Muro() {
        return m2Muro;
    }

    public void setM2Muro(double m2Muro) {
        this.m2Muro = m2Muro;
    }

    public int getMuroPerson() {
        return muroPerson;
    }

    public void setMuroPerson(int muroPerson) {
        this.muroPerson = muroPerson;
    }

    public double getM2Cubierta() {
        return m2Cubierta;
    }

    public void setM2Cubierta(double m2Cubierta) {
        this.m2Cubierta = m2Cubierta;
    }

    public int getCubiertaPerson() {
        return cubiertaPerson;
    }

    public void setCubiertaPerson(int cubiertaPerson) {
        this.cubiertaPerson = cubiertaPerson;
    }

    public double getMetrosLineales() {
        return metrosLineales;
    }

    public void setMetrosLineales(double metrosLineales) {
        this.metrosLineales = metrosLineales;
    }

    public int getLinealesPerson() {
        return linealesPerson;
    }

    public void setLinealesPerson(int linealesPerson) {
        this.linealesPerson = linealesPerson;
    }

    public int getDiasCaidos() {
        return diasCaidos;
    }

    public void setDiasCaidos(int diasCaidos) {
        this.diasCaidos = diasCaidos;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    

    @Override
    public String toString() {
        return "idHumeda: " + idHumeda + "\nfechaInicial: " + fechaInicial + "\nfechaFinal: " + fechaFinal + "\nm2Piso: " + m2Piso + "\npisoPerson: " + pisoPerson + 
                "\nmetros: " + metros + "\nmetrosPerson: " + metrosPerson + "\nm2Muro: " + m2Muro + "\nmuroPerson: " + muroPerson + "\nm2Cubierta: " + m2Cubierta + 
                "\ncubiertaPerson: " + cubiertaPerson + "\nmetrosLineales: " + metrosLineales + "\nlinealesPerson: " + linealesPerson + "\ndiasCaidos: " + diasCaidos + 
                "\nmotivo: " + motivo + "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + 
                "\nestado: " + estado + "\nidVisita: " + idVisita;
    }

  
}
