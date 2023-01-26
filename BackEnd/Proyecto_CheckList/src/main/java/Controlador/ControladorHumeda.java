
package Controlador;

import Conexion.Conexion;
import Modelo.Humeda;
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


public class ControladorHumeda {
    
    //METODO PARA INSERTAR REGISTRO HUMEDA:
    public void insertarHumeda(Humeda humeda) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO humeda (fechaInicio, fechaFinal, m2Piso, pisoPerson, metros, metrosPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setDate(1, Date.valueOf(humeda.getFechaInicial()));
            ps.setDate(2, Date.valueOf(humeda.getFechaFinal()));
            ps.setDouble(3, humeda.getM2Piso());
            ps.setInt(4, humeda.getPisoPerson());
            ps.setDouble(5, humeda.getMetros());
            ps.setInt(6, humeda.getMetrosPerson());
            ps.setDouble(7, humeda.getM2Muro());
            ps.setInt(8, humeda.getMuroPerson());
            ps.setDouble(9, humeda.getM2Cubierta());
            ps.setInt(10, humeda.getCubiertaPerson());
            ps.setDouble(11, humeda.getMetrosLineales());
            ps.setInt(12, humeda.getLinealesPerson());
            ps.setInt(13, humeda.getDiasCaidos());
            ps.setString(14, humeda.getMotivo());
            ps.setString(15, humeda.getComentario());
            ps.setDate(16, Date.valueOf(humeda.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(17, Date.valueOf(humeda.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(18, humeda.getEstado());
            ps.setLong(19, humeda.getIdVisita());

            
            //Ejecutamos el comando y mandamos los datos al sistema:
            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                System.out.println("El Registro fue insertado con exito a la Base de Datos.");
                //JOptionPane.showMessageDialog(null, "El Registro fue insertado con exito a la Base de Datos.");
                //out.print("<script>alert('El Registro fue insertado con exito a la Base de Datos.');<script>");

            } else {

                System.out.println("Error al intentar insertar el registro.");
                //JOptionPane.showMessageDialog(null, "Error al intentar insertar el registro.");
                //out.print("<script>alert('Error al intentar insertar el registro.');<script>");
            }

            conexion.close(); //cerramos la conexion.

        } catch (Exception ex) {

            System.err.println("Error. " + ex);
            //out.print("<script>alert('Error de Conexion.');<script>");

        } finally {

            try {

                ps.close();

            } catch (SQLException ex) {

                System.err.println("Error. " + ex);
                //out.print("<script>alert('Error de Conexion.');<script>");
            }

        }
    }
    
    
    //METODO PARA ACTUALIZAR REGISTRO HUMEDA:
    public void actualizarHumeda(Humeda humeda) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE humeda SET fechaInicio = ?, fechaFinal = ?, m2Piso = ?, pisoPerson = ?, metros = ?, metrosPerson = ?, m2Muro = ?, muroPerson = ?, m2Cubierta = ?, cubiertaPerson = ?, metrosLineales = ?, linealesPerson = ?, diasCaidos = ?, motivo = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ?   WHERE idHumeda = ? ");

            
            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setDate(1, Date.valueOf(humeda.getFechaInicial()));
            ps.setDate(2, Date.valueOf(humeda.getFechaFinal()));
            ps.setDouble(3, humeda.getM2Piso());
            ps.setInt(4, humeda.getPisoPerson());
            ps.setDouble(5, humeda.getMetros());
            ps.setInt(6, humeda.getMetrosPerson());
            ps.setDouble(7, humeda.getM2Muro());
            ps.setInt(8, humeda.getMuroPerson());
            ps.setDouble(9, humeda.getM2Cubierta());
            ps.setInt(10, humeda.getCubiertaPerson());
            ps.setDouble(11, humeda.getMetrosLineales());
            ps.setInt(12, humeda.getLinealesPerson());
            ps.setInt(13, humeda.getDiasCaidos());
            ps.setString(14, humeda.getMotivo());
            ps.setString(15, humeda.getComentario());
            ps.setDate(16, Date.valueOf(humeda.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(17, Date.valueOf(humeda.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(18, humeda.getEstado());
            ps.setLong(19, humeda.getIdVisita());
            ps.setLong(20, humeda.getIdHumeda());
            

            //Ejecutamos el comando y mandamos los datos al sistema:
            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                System.out.println("El Registro fue actualizado con exito a la Base de Datos.");
                //JOptionPane.showMessageDialog(null, "El Registro fue modificado con exito a la Base de Datos.");

            } else {

                System.out.println("Error al intentar actualizar el registro.");
                //JOptionPane.showMessageDialog(null, "Error al intentar modificar el registro.");
            }

            conexion.close(); //cerramos la conexion.

        } catch (Exception ex) {

            System.err.println("Error. " + ex);

        } finally {

            try {
                ps.close();

            } catch (SQLException ex) {

                System.err.println("Error. " + ex);
            }

        }

    }
    
    
    //METODO PARA BUSCAR ONE REGISTRO HUMEDA:
    public Humeda buscarOneHumeda(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Humeda humeda = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM humeda WHERE idHumeda = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idHumeda = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double m2Piso = rs.getDouble(4);
                int pisoPerson = rs.getInt(5);
                double metros = rs.getDouble(6);
                int metrosPerson = rs.getInt(7);
                double m2Muro = rs.getDouble(8);
                int muroPerson = rs.getInt(9);
                double m2Cubierta = rs.getDouble(10);
                int cubiertaPerson = rs.getInt(11);
                double metrosLineales = rs.getDouble(12);
                int linealesPerson = rs.getInt(13);
                int diasCaidos = rs.getInt(14);
                String motivo = rs.getString(15);
                String comentario = rs.getString(16);
                LocalDate fechaAlta = (rs.getDate(17)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(19);
                Long idVisita = rs.getLong(20);
                

                humeda = new Humeda(idHumeda, fechaInicio, fechaFinal, m2Piso, pisoPerson, metros, metrosPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, comentario, fechaAlta, fechaBaja, estado, idVisita);

                System.out.println("El Registro fue encontrado con exito.");
                //JOptionPane.showMessageDialog(null, "El Registro fue encontrado con exito.");

            } else {

                System.out.println("El Registro no fue encontrado en la Base de Datos.");
                //JOptionPane.showMessageDialog(null, "El Registro no fue encontrado en la Base de Datos.");
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

        return humeda; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS HUMEDA:
    public List<Humeda> buscarAllHumeda() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Humeda humeda = null;
        List<Humeda> listaHumeda = new ArrayList<Humeda>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM humeda");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idHumeda = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double m2Piso = rs.getDouble(4);
                int pisoPerson = rs.getInt(5);
                double metros = rs.getDouble(6);
                int metrosPerson = rs.getInt(7);
                double m2Muro = rs.getDouble(8);
                int muroPerson = rs.getInt(9);
                double m2Cubierta = rs.getDouble(10);
                int cubiertaPerson = rs.getInt(11);
                double metrosLineales = rs.getDouble(12);
                int linealesPerson = rs.getInt(13);
                int diasCaidos = rs.getInt(14);
                String motivo = rs.getString(15);
                String comentario = rs.getString(16);
                LocalDate fechaAlta = (rs.getDate(17)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(19);
                Long idVisita = rs.getLong(20);
                

                humeda = new Humeda(idHumeda, fechaInicio, fechaFinal, m2Piso, pisoPerson, metros, metrosPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, comentario, fechaAlta, fechaBaja, estado, idVisita);

                
                listaHumeda.add(humeda);

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

        return listaHumeda; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO HUMEDA A TRAVES DE UPDATE:
    public void eliminarLogicoHumeda(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE humeda SET fechaBaja = ?, estado = ?  WHERE idHumeda = ? ");

            ps.setDate(1, Date.valueOf(fecha)); //Trabajamos en java con LocalDate
            ps.setString(2, "inactivo");
            ps.setLong(3, id);

            //Ejecutamos el comando y mandamos los datos al sistema:
            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                System.out.println("El Registro fue eliminado (Logico) de la Base de Datos.");
                //JOptionPane.showMessageDialog(null, "El Registro fue modificado con exito a la Base de Datos.");

            } else {

                System.out.println("Error al intentar actualizar el registro.");
                //JOptionPane.showMessageDialog(null, "Error al intentar modificar el registro.");
            }

            conexion.close(); //cerramos la conexion.

        } catch (Exception ex) {

            System.err.println("Error. " + ex);

        } finally {

            try {
                ps.close();

            } catch (SQLException ex) {

                System.err.println("Error. " + ex);
            }

        }

    }
    
    
    //METODO PARA ELIMINAR REGISTRO HUMEDA:
    public void eliminarHumeda(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM humeda WHERE idHumeda = ?");

            ps.setLong(1, id); //Se puede usar indicando el primer signo de pregunta del qwery y alojar la variable

            //Ejecutamos el comando y mandamos los datos al sistema:
            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                System.out.println("El Registro fue eliminado con exito a la Base de Datos.");
                //JOptionPane.showMessageDialog(null, "El Registro fue eliminado con exito a la Base de Datos.");
                
            } else {

                System.out.println("Error al intentar eliminar el registro.");
                //JOptionPane.showMessageDialog(null, "Error al intentar eliminar el registro.");
            }

            conexion.close(); //cerramos la conexion.

        } catch (Exception ex) {

            System.err.println("Error. " + ex);

        } finally {

            try {
                ps.close();

            } catch (SQLException ex) {
                System.err.println("Error. " + ex);
            }

        }

    }
    
    
    //METODO PARA BUSCAR ONE REGISTRO HUMEDA X ID_VISITA:
    public Humeda buscarOneHumedaIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Humeda humeda = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM humeda WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idHumeda = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double m2Piso = rs.getDouble(4);
                int pisoPerson = rs.getInt(5);
                double metros = rs.getDouble(6);
                int metrosPerson = rs.getInt(7);
                double m2Muro = rs.getDouble(8);
                int muroPerson = rs.getInt(9);
                double m2Cubierta = rs.getDouble(10);
                int cubiertaPerson = rs.getInt(11);
                double metrosLineales = rs.getDouble(12);
                int linealesPerson = rs.getInt(13);
                int diasCaidos = rs.getInt(14);
                String motivo = rs.getString(15);
                String comentario = rs.getString(16);
                LocalDate fechaAlta = (rs.getDate(17)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(19);
                Long idVisita = rs.getLong(20);
                

                humeda = new Humeda(idHumeda, fechaInicio, fechaFinal, m2Piso, pisoPerson, metros, metrosPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, comentario, fechaAlta, fechaBaja, estado, idVisita);

                System.out.println("El Registro fue encontrado con exito.");
                //JOptionPane.showMessageDialog(null, "El Registro fue encontrado con exito.");

            } else {

                System.out.println("El Registro no fue encontrado en la Base de Datos.");
                //JOptionPane.showMessageDialog(null, "El Registro no fue encontrado en la Base de Datos.");
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

        return humeda; //devolvemos el objeto conclusion
        
    }
    
      
}
