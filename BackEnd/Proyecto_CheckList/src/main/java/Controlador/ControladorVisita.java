
package Controlador;

import Conexion.Conexion;
import Modelo.Visita;
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


public class ControladorVisita {
    
    //METODO PARA INSERTAR REGISTRO VISITA:
    public void insertarVisita(Visita visita) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO visita (fecha, nombreTecnico, apellidoTecnico, nVisita, fechaAlta, fechaBaja, estado, idGeneral) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            
            ps.setDate(1, Date.valueOf(visita.getFecha())); //Se trabaja en java con LocalDate
            ps.setString(2, visita.getNombreTecnico());
            ps.setString(3, visita.getApellidoTecnico());
            ps.setInt(4, visita.getnVisita());
            ps.setDate(5, Date.valueOf(visita.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(6, Date.valueOf(visita.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(7, visita.getEstado());
            ps.setLong(8, visita.getIdGeneral());
            
            
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
    
    
    //METODO PARA ACTUALIZAR REGISTRO VISITA:
    public void actualizarVisita(Visita visita) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE visita SET fecha = ?, nombreTecnico = ?, apellidoTecnico = ?, nVisita = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idGeneral = ? WHERE idVisita = ? ");

            
            // Se establecen los parámetros y se ejecuta la sentencia.
            
            ps.setDate(1, Date.valueOf(visita.getFecha())); //Se trabaja en java con LocalDate
            ps.setString(2, visita.getNombreTecnico());
            ps.setString(3, visita.getApellidoTecnico());
            ps.setInt(4, visita.getnVisita());
            ps.setDate(5, Date.valueOf(visita.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(6, Date.valueOf(visita.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(7, visita.getEstado());
            ps.setLong(8, visita.getIdGeneral());
            ps.setLong(9, visita.getIdVisita());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO VISITA:
    public Visita buscarOneVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Visita visita = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM visita WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idVisita = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fecha = (rs.getDate(2)).toLocalDate(); //En java trabajamos con LocalDate
                String nombreTecnico = rs.getString(3);
                String apellidoTecnico = rs.getString(4);
                int nVisita = rs.getInt(5);
                LocalDate fechaAlta = (rs.getDate(6)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(8);
                Long idGeneral = rs.getLong(9);
                
            
                visita = new Visita(idVisita, fecha, nombreTecnico, apellidoTecnico, nVisita, fechaAlta, fechaBaja, estado, idGeneral);

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

        return visita; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS VISITA:
    public List<Visita> buscarAllVisita() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Visita visita = null;
        List<Visita> listaVisita = new ArrayList<Visita>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM visita");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idVisita = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                LocalDate fecha = (rs.getDate(2)).toLocalDate(); //En java trabajamos con LocalDate
                String nombreTecnico = rs.getString(3);
                String apellidoTecnico = rs.getString(4);
                int nVisita = rs.getInt(5);
                LocalDate fechaAlta = (rs.getDate(6)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(7)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(8);
                Long idGeneral = rs.getLong(9);
                
            
                visita = new Visita(idVisita, fecha, nombreTecnico, apellidoTecnico, nVisita, fechaAlta, fechaBaja, estado, idGeneral);
                
                listaVisita.add(visita);
                
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

        return listaVisita; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO VISITA A TRAVES DE UPDATE:
    public void eliminarLogicoVisita(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE visita SET fechaBaja = ?, estado = ?  WHERE idVisita = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO VISITA:
    public void eliminarVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM visita WHERE idVisita = ?");

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
    
    
    //METODO PARA OBTENER EL VALOR MAXIMO DEL N° DE VISITA A TRAVES DEL N° DE OBRA E INCREMENTAR EN UNO EL VALOR =>
     public int incrementarVisita(String codigo) {

        Connection conexion = null;
        Conexion con = new Conexion();
        int incrementoVisita = 0;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("Select max(v.nVisita) as UltimaVisita from general as g inner join visita as v on g.idGeneral = v.idGeneral where g.codigo = ?");

            ps.setString(1, codigo); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            while (rs.next()) {

                incrementoVisita = rs.getInt(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                
            }
            
            incrementoVisita++; //se incrementa el valor en 1

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

        return incrementoVisita; //devolvemos el n° de visita incrementado

    }
     
     //METODO PARA OBTENER EL ULTIMO ID_VISITA X N° DE OBRA =>
     public Long ultimoIdVisita(String codigo) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Long idVisita = 0L;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT MAX(v.idVisita) FROM visita as v inner join general as g on v.idGeneral = g.idGeneral where g.codigo = ?");

            ps.setString(1,codigo); //Se puede usar indicando el primer signo de pregunta del qwery y alojar la variable
            
            rs = ps.executeQuery();

            while (rs.next()) {

                idVisita = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                
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

        return idVisita; //devolvemos el ultimo id

    }
    
    
    
}
