
package Controlador;

import Conexion.Conexion;
import Modelo.Gremio;
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


public class ControladorGremio {
    
    //METODO PARA INSERTAR REGISTRO GREMIO:
    public void insertarGremio(Gremio gremio) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO gremio (nombreGremio, nroPersonas, horarioDesde, horarioHasta, fechaDesde, fechaHasta, nroArgentinos, nombreContratista, apellidoContratista, comentario, fechaAlta, fechaBaja, estado, idPersona) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, gremio.getNombreGremio());
            ps.setInt(2, gremio.getNroPersonas());
            ps.setTime(3, Time.valueOf(gremio.getHorarioDesde()));  //Modifico por LocalTime que representa solo la hora y en BD con TIME
            ps.setTime(4, Time.valueOf(gremio.getHorarioHasta()));  //Modifico por LocalTime que representa solo la hora y en BD con TIME
            ps.setDate(5, Date.valueOf(gremio.getFechaDesde())); //Se trabaja en java con LocalDate
            ps.setDate(6, Date.valueOf(gremio.getFechaHasta())); //Se trabaja en java con LocalDate
            ps.setInt(7, gremio.getNroArgentinos());
            ps.setString(8, gremio.getNombreContratista());
            ps.setString(9, gremio.getApellidoContratista());
            ps.setString(10, gremio.getComentario());
            ps.setDate(11, Date.valueOf(gremio.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(12, Date.valueOf(gremio.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(13, gremio.getEstado());
            ps.setLong(14, gremio.getIdPersona());
            
            
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO GREMIO:
    public void actualizarGremio(Gremio gremio) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE gremio SET nombreGremio = ?, nroPersonas = ?, horarioDesde = ?, horarioHasta = ?, fechaDesde = ?, fechaHasta = ?, nroArgentinos = ?, nombreContratista = ?, apellidoContratista = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idPersona = ? WHERE idGremio = ? ");

            
            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, gremio.getNombreGremio());
            ps.setInt(2, gremio.getNroPersonas());
            ps.setTime(3, Time.valueOf(gremio.getHorarioDesde()));  //Modifico por LocalTime que representa solo la hora y en BD con TIME
            ps.setTime(4, Time.valueOf(gremio.getHorarioHasta()));  //Modifico por LocalTime que representa solo la hora y en BD con TIME
            ps.setDate(5, Date.valueOf(gremio.getFechaDesde())); //Se trabaja en java con LocalDate
            ps.setDate(6, Date.valueOf(gremio.getFechaHasta())); //Se trabaja en java con LocalDate
            ps.setInt(7, gremio.getNroArgentinos());
            ps.setString(8, gremio.getNombreContratista());
            ps.setString(9, gremio.getApellidoContratista());
            ps.setString(10, gremio.getComentario());
            ps.setDate(11, Date.valueOf(gremio.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(12, Date.valueOf(gremio.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(13, gremio.getEstado());
            ps.setLong(14, gremio.getIdPersona());
            ps.setLong(15, gremio.getIdGremio());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO GREMIO:
    public Gremio buscarOneGremio(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Gremio gremio = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM gremio WHERE idGremio = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idGremio = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String nombreGremio = rs.getString(2);
                int nroPersonas = rs.getInt(3);
                LocalTime horarioDesde = (rs.getTime(4)).toLocalTime(); //modifico a local time para obtener solo la hora
                LocalTime horarioHasta = (rs.getTime(5)).toLocalTime();
                LocalDate fechaDesde = (rs.getDate(6)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaHasta = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                int nroArgentinos = rs.getInt(8);
                String nombreContratista = rs.getString(9);
                String apellidoContratista = rs.getString(10);
                String comentario = rs.getString(11);
                LocalDate fechaAlta = (rs.getDate(12)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(14);
                Long idPersona = rs.getLong(15);
                
                gremio = new Gremio(idGremio, nombreGremio, nroPersonas, horarioDesde, horarioHasta, fechaDesde, fechaHasta, nroArgentinos, nombreContratista, apellidoContratista, comentario, fechaAlta, fechaBaja, estado, idPersona);

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

        return gremio; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS GREMIO:
    public List<Gremio> buscarAllGremio() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Gremio gremio = null;
        List<Gremio> listaGremio = new ArrayList<Gremio>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM gremio");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idGremio = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String nombreGremio = rs.getString(2);
                int nroPersonas = rs.getInt(3);
                LocalTime horarioDesde = (rs.getTime(4)).toLocalTime(); //modifico a local time para obtener solo la hora
                LocalTime horarioHasta = (rs.getTime(5)).toLocalTime();
                LocalDate fechaDesde = (rs.getDate(6)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaHasta = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                int nroArgentinos = rs.getInt(8);
                String nombreContratista = rs.getString(9);
                String apellidoContratista = rs.getString(10);
                String comentario = rs.getString(11);
                LocalDate fechaAlta = (rs.getDate(12)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(14);
                Long idPersona = rs.getLong(15);
                
                gremio = new Gremio(idGremio, nombreGremio, nroPersonas, horarioDesde, horarioHasta, fechaDesde, fechaHasta, nroArgentinos, nombreContratista, apellidoContratista, comentario, fechaAlta, fechaBaja, estado, idPersona);

                
                listaGremio.add(gremio);

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

        return listaGremio; //devolvemos la lista de conclusion

    }
   
    //METODO PARA DELETE LOGICO REGISTRO GREMIO A TRAVES DE UPDATE:
    public void eliminarLogicoGremio(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE gremio SET fechaBaja = ?, estado = ?  WHERE idGremio = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO GREMIO:
    public void eliminarGremio(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM gremio WHERE idGremio = ?");

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
    
    
    //METODO PARA BUSCAR ALL REGISTROS GREMIO X ID_PERSONA:
    public List<Gremio> buscarAllGremioIdPersona(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Gremio gremio = null;
        List<Gremio> listaGremio = new ArrayList<Gremio>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM gremio WHERE idPersona = ?");
            
            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idGremio = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String nombreGremio = rs.getString(2);
                int nroPersonas = rs.getInt(3);
                LocalTime horarioDesde = (rs.getTime(4)).toLocalTime(); //modifico a local time para obtener solo la hora
                LocalTime horarioHasta = (rs.getTime(5)).toLocalTime();
                LocalDate fechaDesde = (rs.getDate(6)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaHasta = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                int nroArgentinos = rs.getInt(8);
                String nombreContratista = rs.getString(9);
                String apellidoContratista = rs.getString(10);
                String comentario = rs.getString(11);
                LocalDate fechaAlta = (rs.getDate(12)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(14);
                Long idPersona = rs.getLong(15);
                
                gremio = new Gremio(idGremio, nombreGremio, nroPersonas, horarioDesde, horarioHasta, fechaDesde, fechaHasta, nroArgentinos, nombreContratista, apellidoContratista, comentario, fechaAlta, fechaBaja, estado, idPersona);

                
                listaGremio.add(gremio);

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

        return listaGremio; //devolvemos la lista de conclusion

    }
    
}
