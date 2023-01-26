
package Controlador;

import Conexion.Conexion;
import Modelo.Panel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorPanel {
    
    //METODO PARA INSERTAR REGISTRO PANEL:
    public void insertarPanel(Panel panel) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO panel (selladores, izaje, tornillos, perfileria, panelesFrio, perfileriaFrio, espesor, resultado, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
           
            ps.setString(1, panel.getSelladores());
            ps.setString(2, panel.getIzaje());
            ps.setString(3, panel.getTornillos());
            ps.setString(4, panel.getPerfileria());
            ps.setString(5, panel.getPanelesFrio());
            ps.setString(6, panel.getPerfileriaFrio());
            ps.setDouble(7, panel.getEspesor());
            ps.setString(8, panel.getResultado());
            ps.setString(9, panel.getComentario());
            ps.setDate(10, Date.valueOf(panel.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(11, Date.valueOf(panel.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(12, panel.getEstado());
            ps.setLong(13, panel.getIdVisita());
            
            
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO PANEL:
    public void actualizarPanel(Panel panel) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE panel SET selladores = ?, izaje = ?, tornillos = ?, perfileria = ?, panelesFrio = ?, perfileriaFrio = ?, espesor = ?, resultado = ?,comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ? WHERE idPanel = ? ");

            
            // Se establecen los parámetros y se ejecuta la sentencia.
           
            ps.setString(1, panel.getSelladores());
            ps.setString(2, panel.getIzaje());
            ps.setString(3, panel.getTornillos());
            ps.setString(4, panel.getPerfileria());
            ps.setString(5, panel.getPanelesFrio());
            ps.setString(6, panel.getPerfileriaFrio());
            ps.setDouble(7, panel.getEspesor());
            ps.setString(8, panel.getResultado());
            ps.setString(9, panel.getComentario());
            ps.setDate(10, Date.valueOf(panel.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(11, Date.valueOf(panel.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(12, panel.getEstado());
            ps.setLong(13, panel.getIdVisita());
            ps.setLong(14, panel.getIdPanel());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO PANEL:
    public Panel buscarOnePanel(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Panel panel = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM panel WHERE idPanel = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idPanel = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String selladores = rs.getString(2);
                String izaje = rs.getString(3);
                String tornillos = rs.getString(4);
                String perfileria = rs.getString(5);
                String panelesFrio = rs.getString(6);
                String perfileriaFrio = rs.getString(7);
                double espesor = rs.getDouble(8);
                String resultado = rs.getString(9);
                String comentario = rs.getString(10);
                LocalDate fechaAlta = (rs.getDate(11)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(12)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(13);
                Long idVisita = rs.getLong(14);
                
              
                panel = new Panel(idPanel, selladores, izaje, tornillos, perfileria, panelesFrio, perfileriaFrio, espesor, resultado, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return panel; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS PANEL:
    public List<Panel> buscarAllPanel() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Panel panel = null;
        List<Panel> listaPanel = new ArrayList<Panel>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM panel");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idPanel = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String selladores = rs.getString(2);
                String izaje = rs.getString(3);
                String tornillos = rs.getString(4);
                String perfileria = rs.getString(5);
                String panelesFrio = rs.getString(6);
                String perfileriaFrio = rs.getString(7);
                double espesor = rs.getDouble(8);
                String resultado = rs.getString(9);
                String comentario = rs.getString(10);
                LocalDate fechaAlta = (rs.getDate(11)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(12)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(13);
                Long idVisita = rs.getLong(14);
                
              
                panel = new Panel(idPanel, selladores, izaje, tornillos, perfileria, panelesFrio, perfileriaFrio, espesor, resultado, comentario, fechaAlta, fechaBaja, estado, idVisita);

                listaPanel.add(panel);
                
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

        return listaPanel; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO PANEL A TRAVES DE UPDATE:
    public void eliminarLogicoPanel(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE panel SET fechaBaja = ?, estado = ?  WHERE idPanel = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO PANEL:
    public void eliminarPanel(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM panel WHERE idPanel = ?");

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
    
    //METODO PARA BUSCAR ONE REGISTRO PANEL:
    public Panel buscarOnePanelIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Panel panel = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM panel WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idPanel = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String selladores = rs.getString(2);
                String izaje = rs.getString(3);
                String tornillos = rs.getString(4);
                String perfileria = rs.getString(5);
                String panelesFrio = rs.getString(6);
                String perfileriaFrio = rs.getString(7);
                double espesor = rs.getDouble(8);
                String resultado = rs.getString(9);
                String comentario = rs.getString(10);
                LocalDate fechaAlta = (rs.getDate(11)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(12)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(13);
                Long idVisita = rs.getLong(14);
                
              
                panel = new Panel(idPanel, selladores, izaje, tornillos, perfileria, panelesFrio, perfileriaFrio, espesor, resultado, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return panel; //devolvemos el objeto conclusion
        
    }
    
    
    
}
