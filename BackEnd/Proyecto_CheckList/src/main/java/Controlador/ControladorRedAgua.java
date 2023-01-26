
package Controlador;

import Conexion.Conexion;
import Modelo.RedAgua;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorRedAgua {
    
    //METODO PARA INSERTAR REGISTRO RED_AGUA:
    public void insertarRedAgua(RedAgua redAgua) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO redagua (fechaInicio, fechaFinal, metrosLineales, nroPersonas, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            
            ps.setDate(1, Date.valueOf(redAgua.getFechaInicio())); //Se trabaja en java con LocalDate
            ps.setDate(2, Date.valueOf(redAgua.getFechaFinal()));
            ps.setDouble(3, redAgua.getMetrosLineales());
            ps.setInt(4, redAgua.getNroPersonas());
            ps.setString(5, redAgua.getComentario());
            ps.setDate(6, Date.valueOf(redAgua.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(7, Date.valueOf(redAgua.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(8, redAgua.getEstado());
            ps.setLong(9, redAgua.getIdVisita());
            
            
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO RED_AGUA:
    public void actualizarRedAgua(RedAgua redAgua) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE redagua SET fechaInicio = ?, fechaFinal = ?, metrosLineales = ?, nroPersonas = ?,comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ? WHERE idAgua = ? ");

            
            // Se establecen los parámetros y se ejecuta la sentencia.
            
            ps.setDate(1, Date.valueOf(redAgua.getFechaInicio())); //Se trabaja en java con LocalDate
            ps.setDate(2, Date.valueOf(redAgua.getFechaFinal()));
            ps.setDouble(3, redAgua.getMetrosLineales());
            ps.setInt(4, redAgua.getNroPersonas());
            ps.setString(5, redAgua.getComentario());
            ps.setDate(6, Date.valueOf(redAgua.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(7, Date.valueOf(redAgua.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(8, redAgua.getEstado());
            ps.setLong(9, redAgua.getIdVisita());
            ps.setLong(10, redAgua.getIdAgua());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO RED_AGUA:
    public RedAgua buscarOneRedAgua(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        RedAgua redAgua = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM redagua WHERE idAgua = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idAgua = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double metrosLineales = rs.getDouble(4);
                int nroPersonas = rs.getInt(5);
                String comentario = rs.getString(6);
                LocalDate fechaAlta = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(8)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(9);
                Long idVisita = rs.getLong(10);
                
               
                redAgua = new RedAgua(idAgua, fechaInicio, fechaFinal, metrosLineales, nroPersonas, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return redAgua; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS RED_AGUA:
    public List<RedAgua> buscarAllRedAgua() {

        Connection conexion = null;
        Conexion con = new Conexion();
        RedAgua redAgua = null;
        List<RedAgua> listaRedAgua = new ArrayList<RedAgua>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM redagua");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idAgua = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double metrosLineales = rs.getDouble(4);
                int nroPersonas = rs.getInt(5);
                String comentario = rs.getString(6);
                LocalDate fechaAlta = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(8)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(9);
                Long idVisita = rs.getLong(10);
                
               
                redAgua = new RedAgua(idAgua, fechaInicio, fechaFinal, metrosLineales, nroPersonas, comentario, fechaAlta, fechaBaja, estado, idVisita);
                
                listaRedAgua.add(redAgua);
                
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

        return listaRedAgua; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO RED_AGUA A TRAVES DE UPDATE:
    public void eliminarLogicoRedAgua(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE redagua SET fechaBaja = ?, estado = ?  WHERE idAgua = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO RED_AGUA:
    public void eliminarRedAgua(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM redagua WHERE idAgua = ?");

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
    
    //METODO PARA BUSCAR ONE REGISTRO RED_AGUA:
    public RedAgua buscarOneRedAguaIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        RedAgua redAgua = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM redagua WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idAgua = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double metrosLineales = rs.getDouble(4);
                int nroPersonas = rs.getInt(5);
                String comentario = rs.getString(6);
                LocalDate fechaAlta = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(8)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(9);
                Long idVisita = rs.getLong(10);
                
               
                redAgua = new RedAgua(idAgua, fechaInicio, fechaFinal, metrosLineales, nroPersonas, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return redAgua; //devolvemos el objeto conclusion
        
    }
    
}
