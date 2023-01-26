
package Controlador;

import Conexion.Conexion;
import Modelo.Conclusion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorConclusion {
    
   
    //METODO PARA INSERTAR REGISTRO CONCLUSION:
    public void insertarConclusion(Conclusion conclusion) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO conclusion (obraTerminada, avanceActual, avanceEsperado, fechaFinalizacion, gradoSatisfaccion, comentario, fechaAlta, fechaBaja, estado, idGeneral) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, conclusion.getObraTerminada());
            ps.setDouble(2, conclusion.getAvanceActual());
            ps.setDouble(3, conclusion.getAvanceEsperado());
            ps.setDate(4, Date.valueOf(conclusion.getFechaFinalizacion()));
            ps.setInt(5, conclusion.getGradoSatisfaccion());
            ps.setString(6, conclusion.getComentario());
            ps.setDate(7, Date.valueOf(conclusion.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(8, Date.valueOf(conclusion.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(9, conclusion.getEstado());
            ps.setLong(10, conclusion.getIdGeneral());

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
    
    
    //METODO PARA ACTUALIZAR REGISTRO CONCLUSION:
    public void actualizarConclusion(Conclusion conclusion) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE conclusion SET obraTerminada = ?, avanceActual = ?, avanceEsperado = ?, fechaFinalizacion = ?, gradoSatisfaccion = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idGeneral = ? WHERE idConclusion = ? ");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, conclusion.getObraTerminada());
            ps.setDouble(2, conclusion.getAvanceActual());
            ps.setDouble(3, conclusion.getAvanceEsperado());
            ps.setDate(4, Date.valueOf(conclusion.getFechaFinalizacion()));
            ps.setInt(5, conclusion.getGradoSatisfaccion());
            ps.setString(6, conclusion.getComentario());
            ps.setDate(7, Date.valueOf(conclusion.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(8, Date.valueOf(conclusion.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(9, conclusion.getEstado());
            ps.setLong(10, conclusion.getIdGeneral());
            ps.setLong(11, conclusion.getIdConclusion());

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO CONCLUSION:
    public Conclusion buscarOneConclusion(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Conclusion conclusion = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM conclusion WHERE idConclusion = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idConclusion = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String obraTerminada = rs.getString(2);
                double avanceActual = rs.getDouble(3);
                double avanceEsperado = rs.getDouble(4);
                LocalDate fechaFinalizacion = (rs.getDate(5)).toLocalDate();
                int gradoSatisfaccion = rs.getInt(6);
                String comentario = rs.getString(7);
                LocalDate fechaAlta = (rs.getDate(8)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(10);
                Long idGeneral = rs.getLong(11);

                conclusion = new Conclusion(idConclusion, obraTerminada, avanceActual, avanceEsperado, fechaFinalizacion, gradoSatisfaccion, comentario, fechaAlta, fechaBaja, estado, idGeneral);

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

        return conclusion; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS CONLUSION:
    public List<Conclusion> buscarAllConclusion() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Conclusion conclusion = null;
        List<Conclusion> listaConclusion = new ArrayList<Conclusion>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM conclusion");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idConclusion = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String obraTerminada = rs.getString(2);
                double avanceActual = rs.getDouble(3);
                double avanceEsperado = rs.getDouble(4);
                LocalDate fechaFinalizacion = (rs.getDate(5)).toLocalDate();
                int gradoSatisfaccion = rs.getInt(6);
                String comentario = rs.getString(7);
                LocalDate fechaAlta = (rs.getDate(8)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(10);
                Long idGeneral = rs.getLong(11);

                conclusion = new Conclusion(idConclusion, obraTerminada, avanceActual, avanceEsperado, fechaFinalizacion, gradoSatisfaccion, comentario, fechaAlta, fechaBaja, estado, idGeneral);

                listaConclusion.add(conclusion);

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

        return listaConclusion; //devolvemos la lista de conclusion

    }
   
    //METODO PARA DELETE LOGICO REGISTRO CONCLUSION A TRAVES DE UPDATE:
    public void eliminarLogicoConclusion(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE conclusion SET fechaBaja = ?, estado = ?  WHERE idConclusion = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO CONCLUSION:
    public void eliminarConclusion(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM conclusion WHERE idConclusion = ?");

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
    
    //METODO PARA OBTENER REGISTRO CONCLUSION PASANDO ID_GENERAL =>
     public int registroConclusionXid(Long idGeneral) {

        Connection conexion = null;
        Conexion con = new Conexion();
        int registroGeneral = 0;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT count(idGeneral) FROM conclusion where idGeneral = ?");

            ps.setLong(1, idGeneral); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            while (rs.next()) {

                registroGeneral = rs.getInt(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                
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

        return registroGeneral; //devolvemos el contador de registros idGeneral

    }
     
    
    //METODO PARA BUSCAR ONE REGISTRO CONCLUSION X ID_GENERAL:
    public Conclusion buscarOneConclusionIdGeneral(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Conclusion conclusion = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM conclusion WHERE idGeneral = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idConclusion = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String obraTerminada = rs.getString(2);
                double avanceActual = rs.getDouble(3);
                double avanceEsperado = rs.getDouble(4);
                LocalDate fechaFinalizacion = (rs.getDate(5)).toLocalDate();
                int gradoSatisfaccion = rs.getInt(6);
                String comentario = rs.getString(7);
                LocalDate fechaAlta = (rs.getDate(8)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(9)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(10);
                Long idGeneral = rs.getLong(11);

                conclusion = new Conclusion(idConclusion, obraTerminada, avanceActual, avanceEsperado, fechaFinalizacion, gradoSatisfaccion, comentario, fechaAlta, fechaBaja, estado, idGeneral);

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

        return conclusion; //devolvemos el objeto conclusion
        
    } 
    
}
