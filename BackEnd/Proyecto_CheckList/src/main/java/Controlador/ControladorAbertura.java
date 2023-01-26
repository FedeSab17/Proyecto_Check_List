
package Controlador;

import Conexion.Conexion;
import Modelo.Abertura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorAbertura {
    
    //METODO PARA INSERTAR REGISTRO ABERTURA:
    public void insertarAbertura(Abertura abertura) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO abertura (fechaInicial, fechaFinal, tipoAbertura, cantidad, m2, nroPersona, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setDate(1, Date.valueOf(abertura.getFechaInicial()));
            ps.setDate(2, Date.valueOf(abertura.getFechaFinal()));
            ps.setString(3, abertura.getTipoAbertura());
            ps.setInt(4, abertura.getCantidad());
            ps.setDouble(5, abertura.getM2());
            ps.setInt(6, abertura.getNroPersona());
            ps.setString(7, abertura.getComentario());
            ps.setDate(8, Date.valueOf(abertura.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(9, Date.valueOf(abertura.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(10, abertura.getEstado());
            ps.setLong(11, abertura.getIdVisita());

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
    
    
    //METODO PARA ACTUALIZAR REGISTRO ABERTURA:
    public void actualizarAbertura(Abertura abertura) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE abertura SET fechaInicial = ?, fechaFinal = ?, tipoAbertura = ?, cantidad = ?, m2 = ?, nroPersona = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ? WHERE idAbertura = ? ");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setDate(1, Date.valueOf(abertura.getFechaInicial()));
            ps.setDate(2, Date.valueOf(abertura.getFechaFinal()));
            ps.setString(3, abertura.getTipoAbertura());
            ps.setInt(4, abertura.getCantidad());
            ps.setDouble(5, abertura.getM2());
            ps.setInt(6, abertura.getNroPersona());
            ps.setString(7, abertura.getComentario());
            ps.setDate(8, Date.valueOf(abertura.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(9, Date.valueOf(abertura.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(10, abertura.getEstado());
            ps.setLong(11, abertura.getIdVisita());
            ps.setLong(12, abertura.getIdAbertura());

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO ABERTURA:
    public Abertura buscarOneAbertura(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Abertura abertura = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM abertura WHERE idAbertura = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idAbertura = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicial = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                String tipoAbertura = rs.getString(4);
                int cantidad = rs.getInt(5);
                double m2 = rs.getDouble(6);
                int nroPersona = rs.getInt(7);
                String comentario = rs.getString(8);
                LocalDate fechaAlta = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(11);
                Long idVisita = rs.getLong(12);

                abertura = new Abertura(idAbertura, fechaInicial, fechaFinal, tipoAbertura, cantidad, m2, nroPersona, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return abertura; //devolvemos el objeto abertura
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS ABERTURA:
    public List<Abertura> buscarAllAbertura() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Abertura abertura = null;
        List<Abertura> listaAbertura = new ArrayList<Abertura>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM abertura");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idAbertura = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicial = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                String tipoAbertura = rs.getString(4);
                int cantidad = rs.getInt(5);
                double m2 = rs.getDouble(6);
                int nroPersona = rs.getInt(7);
                String comentario = rs.getString(8);
                LocalDate fechaAlta = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(11);
                Long idVisita = rs.getLong(12);

                abertura = new Abertura(idAbertura, fechaInicial, fechaFinal, tipoAbertura, cantidad, m2, nroPersona, comentario, fechaAlta, fechaBaja, estado, idVisita);

                listaAbertura.add(abertura);

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

        return listaAbertura; //devolvemos la lista de abertura

    }
   
    //METODO PARA DELETE LOGICO ABERTURA A TRAVES DE UPDATE:
    public void eliminarLogicoAbertura(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE abertura SET fechaBaja = ?, estado = ?  WHERE idAbertura = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO ABERTURA:
    public void eliminarAbertura(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM abertura WHERE idAbertura = ?");

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
    
    //METODO PARA BUSCAR ONE REGISTRO ABERTURA:
    public Abertura buscarOneAberturaIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Abertura abertura = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM abertura WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idAbertura = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicial = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                String tipoAbertura = rs.getString(4);
                int cantidad = rs.getInt(5);
                double m2 = rs.getDouble(6);
                int nroPersona = rs.getInt(7);
                String comentario = rs.getString(8);
                LocalDate fechaAlta = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(11);
                Long idVisita = rs.getLong(12);

                abertura = new Abertura(idAbertura, fechaInicial, fechaFinal, tipoAbertura, cantidad, m2, nroPersona, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return abertura; //devolvemos el objeto abertura
        
    }
    
    
    //METODO PARA BUSCAR ALL REGISTROS ABERTURA X ID_VISITA:
    public List<Abertura> buscarAllAberturaIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Abertura abertura = null;
        List<Abertura> listaAbertura = new ArrayList<Abertura>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM abertura where idVisita = ?");
            
            
            ps.setLong(1, id);
            

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idAbertura = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fechaInicial = (rs.getDate(2)).toLocalDate();
                LocalDate fechaFinal = (rs.getDate(3)).toLocalDate();
                String tipoAbertura = rs.getString(4);
                int cantidad = rs.getInt(5);
                double m2 = rs.getDouble(6);
                int nroPersona = rs.getInt(7);
                String comentario = rs.getString(8);
                LocalDate fechaAlta = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(11);
                Long idVisita = rs.getLong(12);

                abertura = new Abertura(idAbertura, fechaInicial, fechaFinal, tipoAbertura, cantidad, m2, nroPersona, comentario, fechaAlta, fechaBaja, estado, idVisita);

                listaAbertura.add(abertura);

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

        return listaAbertura; //devolvemos la lista de abertura

    }

    
}
