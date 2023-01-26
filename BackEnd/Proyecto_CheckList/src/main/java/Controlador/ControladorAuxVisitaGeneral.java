
package Controlador;


import Conexion.Conexion;
import Modelo.AuxVisitaGeneral;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class ControladorAuxVisitaGeneral {
    
    //METODO PARA BUSCAR ALL REGISTROS AUX_VISITA_GENERAL POR N° DE OBRA:
    public List<AuxVisitaGeneral> buscarAllAuxVisitaGeneral(String codigo) {

        Connection conexion = null;
        Conexion con = new Conexion();
        AuxVisitaGeneral aux = null;
        List<AuxVisitaGeneral> listaAux = new ArrayList<AuxVisitaGeneral>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("Select v.fecha, v.nombreTecnico, v.apellidoTecnico, v.nVisita, g.nombreCliente, g.dni, g.domicilio, v.idVisita, v.idGeneral, g.codigo from visita as v inner join general as g on v.idGeneral = g.idGeneral where g.codigo = ?");
            
            ps.setString(1,codigo); //pasamos el id parametro y se ingresa en el ? del query
            
            rs = ps.executeQuery();

            while (rs.next()) {

     
                LocalDate fechaVisita = (rs.getDate(1)).toLocalDate(); //En java trabajamos con LocalDate
                String nombreTecnico = rs.getString(2);
                String apellidoTecnico = rs.getString(3);
                int nVisita = rs.getInt(4);
                String nombreCliente = rs.getString(5);
                String dni = rs.getString(6);
                String domicilio = rs.getString(7);
                Long idVisita = rs.getLong(8); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                Long idGeneral = rs.getLong(9); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String nObra = rs.getString(10);
                
                
                aux = new AuxVisitaGeneral(fechaVisita, nombreTecnico, apellidoTecnico, nVisita, nombreCliente, dni, domicilio, idVisita, idGeneral, nObra);
                
                listaAux.add(aux);
                
            }

            conexion.close();

        } catch (Exception ex) {

            System.err.println("Error. " + ex);

        } finally {

            try {

                ps.close();
                rs.close();

            } catch (SQLException ex) {
                System.err.println("Error. " + ex);
            }

        }

        return listaAux; //devolvemos la lista de humeda

    }
    
    
    
}
