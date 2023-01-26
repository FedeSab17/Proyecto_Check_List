
package Controlador;

import Conexion.Conexion;
import Modelo.General;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorGeneral {
    
    //METODO PARA INSERTAR REGISTRO GENERAL:
    public void insertarGeneral(General general) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO general (codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, general.getCodigo());
            ps.setString(2, general.getNombreCliente());
            ps.setString(3, general.getDni());
            ps.setString(4, general.getDomicilio());
            ps.setString(5, general.getUsoEdificio());
            ps.setDouble(6, general.getAlturaEdificio());
            ps.setDouble(7, general.getM2Cubierta());
            ps.setDouble(8, general.getM2Muro());
            ps.setString(9, general.getAlcance());
            ps.setInt(10, general.getDuracionObra());
            ps.setString(11, general.getComentario());
            ps.setDate(12, Date.valueOf(general.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(13, Date.valueOf(general.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(14, general.getEstado());
            
            
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO GENERAL:
    public void actualizarGeneral(General general) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE general SET codigo = ?, nombreCliente = ?, dni = ?, domicilio = ?, usoEdificio = ?, alturaEdificio = ?, m2Cubierta = ?, m2Muro = ?, alcance = ?, duracionObra = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ? WHERE idGeneral = ? ");

            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, general.getCodigo());
            ps.setString(2, general.getNombreCliente());
            ps.setString(3, general.getDni());
            ps.setString(4, general.getDomicilio());
            ps.setString(5, general.getUsoEdificio());
            ps.setDouble(6, general.getAlturaEdificio());
            ps.setDouble(7, general.getM2Cubierta());
            ps.setDouble(8, general.getM2Muro());
            ps.setString(9, general.getAlcance());
            ps.setInt(10, general.getDuracionObra());
            ps.setString(11, general.getComentario());
            ps.setDate(12, Date.valueOf(general.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(13, Date.valueOf(general.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(14, general.getEstado());
            ps.setLong(15, general.getIdGeneral());

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO GENERAL:
    public General buscarOneGeneral(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        General general = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM general WHERE idGeneral = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idGeneral = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String codigo = rs.getString(2);
                String nombreCliente = rs.getString(3);
                String dni = rs.getString(4);
                String domicilio = rs.getString(5);
                String usoEdificio = rs.getString(6);
                double alturaEdificio = rs.getDouble(7);
                double m2Cubierta = rs.getDouble(8);
                double m2Muro = rs.getDouble(9);
                String alcance = rs.getString(10);
                int duracionObra = rs.getInt(11);
                String comentario = rs.getString(12);
                LocalDate fechaAlta = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(14)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(15);
                
                
                general = new General(idGeneral, codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado);

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

        return general; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS GENERAL:
    public List<General> buscarAllGeneral() {

        Connection conexion = null;
        Conexion con = new Conexion();
        General general = null;
        List<General> listaGeneral = new ArrayList<General>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM general");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idGeneral = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String codigo = rs.getString(2);
                String nombreCliente = rs.getString(3);
                String dni = rs.getString(4);
                String domicilio = rs.getString(5);
                String usoEdificio = rs.getString(6);
                double alturaEdificio = rs.getDouble(7);
                double m2Cubierta = rs.getDouble(8);
                double m2Muro = rs.getDouble(9);
                String alcance = rs.getString(10);
                int duracionObra = rs.getInt(11);
                String comentario = rs.getString(12);
                LocalDate fechaAlta = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(14)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(15);
                
                
                
                general = new General(idGeneral, codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado);

                
                listaGeneral.add(general);

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

        return listaGeneral; //devolvemos la lista de conclusion

    }
   
    //METODO PARA DELETE LOGICO RTEGISTRO GENERAL A TRAVES DE UPDATE:
    public void eliminarLogicoGeneral(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE general SET fechaBaja = ?, estado = ?  WHERE idGeneral = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO GENERAL:
    public void eliminarGeneral(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM general WHERE idGeneral = ?");

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
    
    //METODO PARA OBTENER EL ULTIMO ID_GENERAL =>
     public Long ultimoIdGeneral() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Long idGeneral = 0L;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT MAX(idGeneral) FROM general");

            rs = ps.executeQuery();

            while (rs.next()) {

                idGeneral = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                
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

        return idGeneral; //devolvemos el ultimo id

    }
     
     
     //METODO PARA OBTENER EL IDGENERAL POR EL N° DE OBRA =>
     public Long numeroId(String codigo) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Long idGeneral = 0L;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("Select idGeneral from general where codigo = ?");

            ps.setString(1, codigo); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            while (rs.next()) {

                idGeneral = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                
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

        return idGeneral; //devolvemos el n° de idGeneral

    }
    
    //METODO PARA BUSCAR ONE REGISTRO GENERAL X N° DE OBRA:
    public General buscarOneGeneralNobra(String nObra) {

        Connection conexion = null;
        Conexion con = new Conexion();
        General general = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM general WHERE codigo = ?");

            ps.setString(1, nObra); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idGeneral = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String codigo = rs.getString(2);
                String nombreCliente = rs.getString(3);
                String dni = rs.getString(4);
                String domicilio = rs.getString(5);
                String usoEdificio = rs.getString(6);
                double alturaEdificio = rs.getDouble(7);
                double m2Cubierta = rs.getDouble(8);
                double m2Muro = rs.getDouble(9);
                String alcance = rs.getString(10);
                int duracionObra = rs.getInt(11);
                String comentario = rs.getString(12);
                LocalDate fechaAlta = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(14)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(15);
                
                
                general = new General(idGeneral, codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado);

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

        return general; //devolvemos el objeto conclusion
        
    }
    
    
    //METODO PARA BUSCAR ALL REGISTROS GENERAL X NOMBRE_CLIENTE:
    public List<General> buscarAllGeneralNombre(String nombre) {

        Connection conexion = null;
        Conexion con = new Conexion();
        General general = null;
        List<General> listaGeneral = new ArrayList<General>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM general where nombreCliente = ?");
            
            ps.setString(1, nombre);

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idGeneral = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String codigo = rs.getString(2);
                String nombreCliente = rs.getString(3);
                String dni = rs.getString(4);
                String domicilio = rs.getString(5);
                String usoEdificio = rs.getString(6);
                double alturaEdificio = rs.getDouble(7);
                double m2Cubierta = rs.getDouble(8);
                double m2Muro = rs.getDouble(9);
                String alcance = rs.getString(10);
                int duracionObra = rs.getInt(11);
                String comentario = rs.getString(12);
                LocalDate fechaAlta = (rs.getDate(13)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(14)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(15);
                
                
                
                general = new General(idGeneral, codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado);

                
                listaGeneral.add(general);

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

        return listaGeneral; //devolvemos la lista de conclusion

    }
    
}
