
package Controlador;

import Conexion.Conexion;
import Modelo.Material;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorMaterial {
    
    //METODO PARA INSERTAR REGISTRO MATERIAL:
    public void insertarMaterial(Material material) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO material (estadoAlmacen, movMateriales, almacenSeguro, envasesVacio, materialSobran, estadoLimpieza, desechosOrgani, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
           
            ps.setString(1, material.getEstadoAlmacen());
            ps.setString(2, material.getMovMateriales());
            ps.setString(3, material.getAlmacenSeguro());
            ps.setString(4, material.getEnvasesVacio());
            ps.setString(5, material.getMaterialSobran());
            ps.setString(6, material.getEstadoLimpieza());
            ps.setString(7, material.getDesechosOrgani());
            ps.setString(8, material.getComentario());
            ps.setDate(9, Date.valueOf(material.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(10, Date.valueOf(material.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(11, material.getEstado());
            ps.setLong(12, material.getIdVisita());
            
            
           
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO MATERIAL:
    public void actualizarMaterial(Material material) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE material SET estadoAlmacen = ?, movMateriales = ?, almacenSeguro = ?, envasesVacio = ?, materialSobran = ?, estadoLimpieza = ?, desechosOrgani = ?, comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ? WHERE idMaterial = ? ");

            
            ps.setString(1, material.getEstadoAlmacen());
            ps.setString(2, material.getMovMateriales());
            ps.setString(3, material.getAlmacenSeguro());
            ps.setString(4, material.getEnvasesVacio());
            ps.setString(5, material.getMaterialSobran());
            ps.setString(6, material.getEstadoLimpieza());
            ps.setString(7, material.getDesechosOrgani());
            ps.setString(8, material.getComentario());
            ps.setDate(9, Date.valueOf(material.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(10, Date.valueOf(material.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(11, material.getEstado());
            ps.setLong(12, material.getIdVisita());
            ps.setLong(13, material.getIdMaterial());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO MATERIAL:
    public Material buscarOneMaterial(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Material material = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM material WHERE idMaterial = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idMaterial = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String estadoAlmacen = rs.getString(2);
                String movMateriales = rs.getString(3);
                String almacenSeguro = rs.getString(4);
                String envasesVacio = rs.getString(5);
                String materialSobran = rs.getString(6);
                String estadoLimpieza = rs.getString(7);
                String desechosOrgani = rs.getString(8);
                String comentario = rs.getString(9);
                LocalDate fechaAlta = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(11)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(12);
                Long idVisita = rs.getLong(13);
                
              
                material = new Material(idMaterial, estadoAlmacen, movMateriales, almacenSeguro, envasesVacio, materialSobran, estadoLimpieza, desechosOrgani, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return material; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS MATERIAL:
    public List<Material> buscarAllMaterial() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Material material = null;
        List<Material> listaMaterial = new ArrayList<Material>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM material");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idMaterial = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String estadoAlmacen = rs.getString(2);
                String movMateriales = rs.getString(3);
                String almacenSeguro = rs.getString(4);
                String envasesVacio = rs.getString(5);
                String materialSobran = rs.getString(6);
                String estadoLimpieza = rs.getString(7);
                String desechosOrgani = rs.getString(8);
                String comentario = rs.getString(9);
                LocalDate fechaAlta = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(11)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(12);
                Long idVisita = rs.getLong(13);
                
              
                material = new Material(idMaterial, estadoAlmacen, movMateriales, almacenSeguro, envasesVacio, materialSobran, estadoLimpieza, desechosOrgani, comentario, fechaAlta, fechaBaja, estado, idVisita);

                listaMaterial.add(material);
                
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

        return listaMaterial; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO MATERIAL A TRAVES DE UPDATE:
    public void eliminarLogicoMaterial(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE material SET fechaBaja = ?, estado = ?  WHERE idMaterial = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO MATERIAL:
    public void eliminarMaterial(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM material WHERE idMaterial = ?");

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO MATERIAL X ID_VISITA:
    public Material buscarOneMaterialIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Material material = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM material WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idMaterial = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                String estadoAlmacen = rs.getString(2);
                String movMateriales = rs.getString(3);
                String almacenSeguro = rs.getString(4);
                String envasesVacio = rs.getString(5);
                String materialSobran = rs.getString(6);
                String estadoLimpieza = rs.getString(7);
                String desechosOrgani = rs.getString(8);
                String comentario = rs.getString(9);
                LocalDate fechaAlta = (rs.getDate(10)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(11)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(12);
                Long idVisita = rs.getLong(13);
                
              
                material = new Material(idMaterial, estadoAlmacen, movMateriales, almacenSeguro, envasesVacio, materialSobran, estadoLimpieza, desechosOrgani, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return material; //devolvemos el objeto conclusion
        
    }
    
}
