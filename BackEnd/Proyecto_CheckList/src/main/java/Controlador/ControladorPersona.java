
package Controlador;

import Conexion.Conexion;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorPersona {
    
    //METODO PARA INSERTAR REGISTRO PERSONA:
    public void insertarPersona(Persona persona) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  //Este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("INSERT INTO persona (personasTotal, nGremios, gremioEnfoque, vestimentaOk, calzadoOk, utilizanEpp, herramientasOk, seguridadOk, trabajoAltura, bañosOk, comerOk, edadJoven, edadViejo, rangoMin, rangoMax, comentario, fechaAlta, fechaBaja, estado, idVisita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Se establecen los parámetros y se ejecuta la sentencia.
            
            ps.setInt(1, persona.getPersonasTotal());
            ps.setInt(2, persona.getnGremios());
            ps.setString(3, persona.getGremioEnfoque());
            ps.setString(4, persona.getVestimentaOk());
            ps.setString(5, persona.getCalzadoOk());
            ps.setString(6, persona.getUtilizanEpp());
            ps.setString(7, persona.getHerramientasOk());
            ps.setString(8, persona.getSeguridadOk());
            ps.setString(9, persona.getTrabajoAltura());
            ps.setString(10, persona.getBanosOk());
            ps.setString(11, persona.getComerOk());
            ps.setInt(12, persona.getEdadJoven());
            ps.setInt(13, persona.getEdadViejo());
            ps.setInt(14, persona.getRangoMin());
            ps.setInt(15, persona.getRangoMax());
            ps.setString(16, persona.getComentario());
            ps.setDate(17, Date.valueOf(persona.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(18, Date.valueOf(persona.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(19, persona.getEstado());
            ps.setLong(20, persona.getIdVisita());
           

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
    
    
    //METODO PARA ACTUALIZAR REGISTRO PERSONA:
    public void actualizarPersona(Persona persona) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE persona SET personasTotal = ?, nGremios = ?, gremioEnfoque = ?, vestimentaOk = ?, calzadoOk = ?, utilizanEpp = ?, herramientasOk = ?, seguridadOk = ?, trabajoAltura = ?, bañosOk = ?, comerOk = ?, edadJoven = ?, edadViejo = ?, rangoMin = ?, rangoMax = ?,comentario = ?, fechaAlta = ?, fechaBaja = ?, estado = ?, idVisita = ? WHERE idPersona = ? ");

            
            // Se establecen los parámetros y se ejecuta la sentencia.
            
            ps.setInt(1, persona.getPersonasTotal());
            ps.setInt(2, persona.getnGremios());
            ps.setString(3, persona.getGremioEnfoque());
            ps.setString(4, persona.getVestimentaOk());
            ps.setString(5, persona.getCalzadoOk());
            ps.setString(6, persona.getUtilizanEpp());
            ps.setString(7, persona.getHerramientasOk());
            ps.setString(8, persona.getSeguridadOk());
            ps.setString(9, persona.getTrabajoAltura());
            ps.setString(10, persona.getBanosOk());
            ps.setString(11, persona.getComerOk());
            ps.setInt(12, persona.getEdadJoven());
            ps.setInt(13, persona.getEdadViejo());
            ps.setInt(14, persona.getRangoMin());
            ps.setInt(15, persona.getRangoMax());
            ps.setString(16, persona.getComentario());
            ps.setDate(17, Date.valueOf(persona.getFechaAlta())); //Se trabaja en java con LocalDate
            ps.setDate(18, Date.valueOf(persona.getFechaBaja())); //Se trabaja en java con LocalDate
            ps.setString(19, persona.getEstado());
            ps.setLong(20, persona.getIdVisita());
            ps.setLong(21, persona.getIdPersona());
            

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
    
    
    //METODO PARA BUSCAR ONE REGISTRO PERSONA:
    public Persona buscarOnePersona(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Persona persona = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM persona WHERE idPersona = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idPersona = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                int personasTotal = rs.getInt(2);
                int nGremios = rs.getInt(3);
                String gremioEnfoque = rs.getString(4);
                String vestimentaOk = rs.getString(5);
                String calzadoOk = rs.getString(6);
                String utilizanEpp = rs.getString(7);
                String herramientasOk = rs.getString(8);
                String seguridadOk = rs.getString(9);
                String trabajoAltura = rs.getString(10);
                String banosOk = rs.getString(11);
                String comerOk = rs.getString(12);
                int edadJoven = rs.getInt(13);
                int edadViejo = rs.getInt(14);
                int rangoMin = rs.getInt(15);
                int rangoMax = rs.getInt(16);
                String comentario = rs.getString(17);
                LocalDate fechaAlta = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(19)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(20);
                Long idVisita = rs.getLong(21);
                
              
                persona = new Persona(idPersona, personasTotal, nGremios, gremioEnfoque, vestimentaOk, calzadoOk, utilizanEpp, herramientasOk, seguridadOk, trabajoAltura, banosOk, comerOk, edadJoven, edadViejo, rangoMin, rangoMax, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return persona; //devolvemos el objeto conclusion
        
    }
    
    //METODO PARA BUSCAR ALL REGISTROS PERSONA:
    public List<Persona> buscarAllPersona() {

        Connection conexion = null;
        Conexion con = new Conexion();
        Persona persona = null;
        List<Persona> listaPersona = new ArrayList<Persona>();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM persona");

            rs = ps.executeQuery();

            while (rs.next()) {

                Long idPersona = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                int personasTotal = rs.getInt(2);
                int nGremios = rs.getInt(3);
                String gremioEnfoque = rs.getString(4);
                String vestimentaOk = rs.getString(5);
                String calzadoOk = rs.getString(6);
                String utilizanEpp = rs.getString(7);
                String herramientasOk = rs.getString(8);
                String seguridadOk = rs.getString(9);
                String trabajoAltura = rs.getString(10);
                String banosOk = rs.getString(11);
                String comerOk = rs.getString(12);
                int edadJoven = rs.getInt(13);
                int edadViejo = rs.getInt(14);
                int rangoMin = rs.getInt(15);
                int rangoMax = rs.getInt(16);
                String comentario = rs.getString(17);
                LocalDate fechaAlta = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(19)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(20);
                Long idVisita = rs.getLong(21);
                
              
                persona = new Persona(idPersona, personasTotal, nGremios, gremioEnfoque, vestimentaOk, calzadoOk, utilizanEpp, herramientasOk, seguridadOk, trabajoAltura, banosOk, comerOk, edadJoven, edadViejo, rangoMin, rangoMax, comentario, fechaAlta, fechaBaja, estado, idVisita);

                listaPersona.add(persona);
                
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

        return listaPersona; //devolvemos la lista de humeda

    }
   
    //METODO PARA DELETE LOGICO REGISTRO PERSONA A TRAVES DE UPDATE:
    public void eliminarLogicoPersona(Long id, LocalDate fecha) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("UPDATE persona SET fechaBaja = ?, estado = ?  WHERE idPersona = ? ");

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
    
    
    //METODO PARA ELIMINAR REGISTRO PERSONA:
    public void eliminarPersona(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("DELETE FROM persona WHERE idPersona = ?");

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
    
    
    //METODO PARA OBTENER EL ULTIMO ID_PERSONA X N° DE OBRA =>
     public Long ultimoIdPersona(String codigo) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Long idPersona = 0L;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT MAX(p.idPersona) FROM persona as p inner join visita as v on p.idVisita = v.idVisita inner join general as g on v.idGeneral = g.idGeneral where g.codigo = ?");

            ps.setString(1,codigo); //Se puede usar indicando el primer signo de pregunta del qwery y alojar la variable
            
            rs = ps.executeQuery();

            while (rs.next()) {

                idPersona = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                
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

        return idPersona; //devolvemos el ultimo id

    }
     
    //METODO PARA BUSCAR ONE REGISTRO PERSONA X ID_VISITA:
    public Persona buscarOnePersonaIdVisita(Long id) {

        Connection conexion = null;
        Conexion con = new Conexion();
        Persona persona = null;
        PreparedStatement ps = null;  //Este objeto permite guardar las consultas que hacemos a la BD.
        ResultSet rs = null;  // este objeto lo usamos cuando obtenemos algo de la base de datos.

        try {

            conexion = con.getConnection(); //metodo getConnection, logueamos el usuario.

            ps = conexion.prepareStatement("SELECT * FROM persona WHERE idVisita = ?");

            ps.setLong(1, id); //pasamos el id parametro y se ingresa en el ? del query

            rs = ps.executeQuery();  //Ejecutamos el Resulset y executeQuery cuando obtenemos algo de la base de datos.

            if (rs.next()) {  //si nos devuelve un dato true

                Long idPersona = rs.getLong(1); //cada numero del parametro hace referencia al dato del campo que se desea obtener = idPersona
                int personasTotal = rs.getInt(2);
                int nGremios = rs.getInt(3);
                String gremioEnfoque = rs.getString(4);
                String vestimentaOk = rs.getString(5);
                String calzadoOk = rs.getString(6);
                String utilizanEpp = rs.getString(7);
                String herramientasOk = rs.getString(8);
                String seguridadOk = rs.getString(9);
                String trabajoAltura = rs.getString(10);
                String banosOk = rs.getString(11);
                String comerOk = rs.getString(12);
                int edadJoven = rs.getInt(13);
                int edadViejo = rs.getInt(14);
                int rangoMin = rs.getInt(15);
                int rangoMax = rs.getInt(16);
                String comentario = rs.getString(17);
                LocalDate fechaAlta = (rs.getDate(18)).toLocalDate(); //En java trabajamos con LocalDate
                LocalDate fechaBaja = (rs.getDate(19)).toLocalDate(); //En java trabajamos con LocalDate
                String estado = rs.getString(20);
                Long idVisita = rs.getLong(21);
                
              
                persona = new Persona(idPersona, personasTotal, nGremios, gremioEnfoque, vestimentaOk, calzadoOk, utilizanEpp, herramientasOk, seguridadOk, trabajoAltura, banosOk, comerOk, edadJoven, edadViejo, rangoMin, rangoMax, comentario, fechaAlta, fechaBaja, estado, idVisita);

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

        return persona; //devolvemos el objeto conclusion
        
    }
    
}
