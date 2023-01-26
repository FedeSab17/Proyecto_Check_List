
package Controlador;

import Conexion.Conexion;
import Modelo.Seco;
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


public class ControladorSeco {
    
    //METODO PARA INSERTAR REGISTRO SECO:
    public void insertarSeco(Seco seco) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO seco (fechaInicio, fechaFinal, mLineales, mPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, materialVigas, materialMuros, materialCubiertas, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setDate(1, Date.valueOf(seco.getFechaInicial()));
            ps.setDate(2, Date.valueOf(seco.getFechaFinal()));
            ps.setDouble(3, seco.getmLineales());
            ps.setInt(4, seco.getmPerson());
            ps.setDouble(5, seco.getM2Muro());
            ps.setInt(6, seco.getMuroPerson());
            ps.setDouble(7, seco.getM2Cubierta());
            ps.setInt(8, seco.getCubiertaPerson());
            ps.setDouble(9, seco.getMetrosLineales());
            ps.setInt(10, seco.getLinealesPerson());
            ps.setInt(11, seco.getDiasCaidos());
            ps.setString(12, seco.getMotivo());
            ps.setString(13, seco.getMaterialVigas());
            ps.setString(14, seco.getMaterialMuros());
            ps.setString(15, seco.getMaterialCubiertas());
            ps.setString(16, seco.getComentario());
            ps.setDate(17, Date.valueOf(seco.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(18, Date.valueOf(seco.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(19, seco.getEstado());
            ps.setLong(20, seco.getIdVisita());

            
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO SECO:
    public void actualizarSeco(Seco seco) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE seco SET fechaInicio = ?, fechaFinal = ?, mLineales = ?, mPerson = ?, m2Muro = ?, muroPerson = ?, m2Cubierta = ?, cubiertaPerson = ?, metrosLineales = ?, linealesPerson = ?, diasCaidos = ?, motivo = ?, materialVigas = ?, materialMuros = ?, materialCubiertas = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ? WHERE idSeco = ? ");

            
             // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setDate(1, Date.valueOf(seco.getFechaInicial()));
            ps.setDate(2, Date.valueOf(seco.getFechaFinal()));
            ps.setDouble(3, seco.getmLineales());
            ps.setInt(4, seco.getmPerson());
            ps.setDouble(5, seco.getM2Muro());
            ps.setInt(6, seco.getMuroPerson());
            ps.setDouble(7, seco.getM2Cubierta());
            ps.setInt(8, seco.getCubiertaPerson());
            ps.setDouble(9, seco.getMetrosLineales());
            ps.setInt(10, seco.getLinealesPerson());
            ps.setInt(11, seco.getDiasCaidos());
            ps.setString(12, seco.getMotivo());
            ps.setString(13, seco.getMaterialVigas());
            ps.setString(14, seco.getMaterialMuros());
            ps.setString(15, seco.getMaterialCubiertas());
            ps.setString(16, seco.getComentario());
            ps.setDate(17, Date.valueOf(seco.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(18, Date.valueOf(seco.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(19, seco.getEstado());
            ps.setLong(20, seco.getIdVisita());
            ps.setLong(21, seco.getIdSeco());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO SECO:
    public Seco buscarOneSeco(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Seco seco = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM seco WHERE idSeco = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idSeco = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double mLineales = Double.parseDouble(rs.getString(4));
                int mPerson = Integer.parseInt(rs.getString(5));
                double m2Muro = Double.parseDouble(rs.getString(6));
                int muroPerson = Integer.parseInt(rs.getString(7));
                double m2Cubierta = Double.parseDouble(rs.getString(8));
                int cubiertaPerson = Integer.parseInt(rs.getString(9));
                double metrosLineales = Double.parseDouble(rs.getString(10));
                int linealesPerson = Integer.parseInt(rs.getString(11));
                int diasCaidos = Integer.parseInt(rs.getString(12));
                String motivo = rs.getString(13);
                String materialVigas = rs.getString(14);
                String materialMuros = rs.getString(15);
                String materialCubiertas = rs.getString(16);
                String comentario = rs.getString(17);
                LocalDate fechaAlta = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(19)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(20);
                Long idVisita = rs.getLong(21);
                

                seco = new Seco(idSeco, fechaInicio, fechaFinal, mLineales, mPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, materialVigas, materialMuros, materialCubiertas, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return seco; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS SECO:
    public List<Seco> buscarAllSeco() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Seco seco = null;
        List<Seco> listaSeco = new ArrayList<Seco>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM seco");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idSeco = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double mLineales = Double.parseDouble(rs.getString(4));
                int mPerson = Integer.parseInt(rs.getString(5));
                double m2Muro = Double.parseDouble(rs.getString(6));
                int muroPerson = Integer.parseInt(rs.getString(7));
                double m2Cubierta = Double.parseDouble(rs.getString(8));
                int cubiertaPerson = Integer.parseInt(rs.getString(9));
                double metrosLineales = Double.parseDouble(rs.getString(10));
                int linealesPerson = Integer.parseInt(rs.getString(11));
                int diasCaidos = Integer.parseInt(rs.getString(12));
                String motivo = rs.getString(13);
                String materialVigas = rs.getString(14);
                String materialMuros = rs.getString(15);
                String materialCubiertas = rs.getString(16);
                String comentario = rs.getString(17);
                LocalDate fechaAlta = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(19)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(20);
                Long idVisita = rs.getLong(21);
                

                seco = new Seco(idSeco, fechaInicio, fechaFinal, mLineales, mPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, materialVigas, materialMuros, materialCubiertas, comentario, fechaAlta, fechaBaja, estado, idVisita);

                
                listaSeco.add(seco);

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

        return listaSeco; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO SECO A TRAVES DE UPDATE:
    public void eliminarLogicoSeco(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE seco SET fechaBaja = ?, estado = ?  WHERE idSeco = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO SECO:
    public void eliminarSeco(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM seco WHERE idSeco = ?");

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
    
    //METODO PARA BUSCAR ONE REGISTRO SECO X ID_VISITA:
    public Seco buscarOneSecoIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Seco seco = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM seco WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idSeco = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicio = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                double mLineales = Double.parseDouble(rs.getString(4));
                int mPerson = Integer.parseInt(rs.getString(5));
                double m2Muro = Double.parseDouble(rs.getString(6));
                int muroPerson = Integer.parseInt(rs.getString(7));
                double m2Cubierta = Double.parseDouble(rs.getString(8));
                int cubiertaPerson = Integer.parseInt(rs.getString(9));
                double metrosLineales = Double.parseDouble(rs.getString(10));
                int linealesPerson = Integer.parseInt(rs.getString(11));
                int diasCaidos = Integer.parseInt(rs.getString(12));
                String motivo = rs.getString(13);
                String materialVigas = rs.getString(14);
                String materialMuros = rs.getString(15);
                String materialCubiertas = rs.getString(16);
                String comentario = rs.getString(17);
                LocalDate fechaAlta = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(19)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(20);
                Long idVisita = rs.getLong(21);
                

                seco = new Seco(idSeco, fechaInicio, fechaFinal, mLineales, mPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, materialVigas, materialMuros, materialCubiertas, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return seco; //devolvemos el objeto conclusion
        
    }
 
    
}
