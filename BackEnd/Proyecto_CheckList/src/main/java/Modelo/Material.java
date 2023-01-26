
package Modelo;

import java.time.LocalDate;


public class Material {
    
    private Long idMaterial;
    private String estadoAlmacen;
    private String movMateriales;
    private String almacenSeguro;
    private String envasesVacio;
    private String materialSobran;
    private String estadoLimpieza;
    private String desechosOrgani;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idVisita;
    
    //Variable de relacion bidireccional Visita:Material (1:1) =>
    private Visita visita;

    public Material() {
    }

    public Material(Long idMaterial, String estadoAlmacen, String movMateriales, String almacenSeguro, String envasesVacio, String materialSobran, String estadoLimpieza, String desechosOrgani, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.idMaterial = idMaterial;
        this.estadoAlmacen = estadoAlmacen;
        this.movMateriales = movMateriales;
        this.almacenSeguro = almacenSeguro;
        this.envasesVacio = envasesVacio;
        this.materialSobran = materialSobran;
        this.estadoLimpieza = estadoLimpieza;
        this.desechosOrgani = desechosOrgani;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Material(String estadoAlmacen, String movMateriales, String almacenSeguro, String envasesVacio, String materialSobran, String estadoLimpieza, String desechosOrgani, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.estadoAlmacen = estadoAlmacen;
        this.movMateriales = movMateriales;
        this.almacenSeguro = almacenSeguro;
        this.envasesVacio = envasesVacio;
        this.materialSobran = materialSobran;
        this.estadoLimpieza = estadoLimpieza;
        this.desechosOrgani = desechosOrgani;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getEstadoAlmacen() {
        return estadoAlmacen;
    }

    public void setEstadoAlmacen(String estadoAlmacen) {
        this.estadoAlmacen = estadoAlmacen;
    }

    public String getMovMateriales() {
        return movMateriales;
    }

    public void setMovMateriales(String movMateriales) {
        this.movMateriales = movMateriales;
    }

    public String getAlmacenSeguro() {
        return almacenSeguro;
    }

    public void setAlmacenSeguro(String almacenSeguro) {
        this.almacenSeguro = almacenSeguro;
    }

    public String getEnvasesVacio() {
        return envasesVacio;
    }

    public void setEnvasesVacio(String envasesVacio) {
        this.envasesVacio = envasesVacio;
    }

    public String getMaterialSobran() {
        return materialSobran;
    }

    public void setMaterialSobran(String materialSobran) {
        this.materialSobran = materialSobran;
    }

    public String getEstadoLimpieza() {
        return estadoLimpieza;
    }

    public void setEstadoLimpieza(String estadoLimpieza) {
        this.estadoLimpieza = estadoLimpieza;
    }

    public String getDesechosOrgani() {
        return desechosOrgani;
    }

    public void setDesechosOrgani(String desechosOrgani) {
        this.desechosOrgani = desechosOrgani;
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
        return "idMaterial: " + idMaterial + "\nestadoAlmacen: " + estadoAlmacen + "\nmovMateriales: " + movMateriales + "\nalmacenSeguro: " + almacenSeguro + 
                "\nenvasesVacio: " + envasesVacio + "\nmaterialSobran: " + materialSobran + "\nestadoLimpieza: " + estadoLimpieza + "\ndesechosOrgani: " + desechosOrgani + 
                "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + "\nestado: " + estado + "\nidVisita: " + idVisita;
    }
    
   
 
}
