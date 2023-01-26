
package Servlet;


import Controlador.ControladorGremio;
import Modelo.Gremio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;
import java.time.LocalDate;
import java.time.LocalTime;


//Se especifica el nombre y ruta de la clase: 'http://localhost:8080/CheckList/GremioServlet?
@WebServlet(name = "GremioServlet", urlPatterns = {"/GremioServlet"})
public class ServletGremio extends HttpServlet {
    
     
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Modificando el response.setContentType y agregando charset=UTF-8 soluciona problema de caracteres como ñ en react:
        //https://blog.continuum.cl/generar-una-respuesta-json-desde-java-en-utf-8-e68392ae4587
        
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        
        PrintWriter out = response.getWriter();
        String respuestaServer = "";
        try {
            
            mostrarElementos(request, response);
            if(request.getParameter("action") != null){
                System.out.println("ACTION " + request.getParameter("action"));
                if(request.getParameter("action").equals("listar")){
                    
                    ControladorGremio c1 = new ControladorGremio();
                    List<Gremio> listaGremio = c1.buscarAllGremio();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String gremioJson = gsonBuilder.toJson(listaGremio);
                    respuestaServer = gremioJson;
                    
                }else if(request.getParameter("action").equals("buscar")){
                    
                    ControladorGremio c1 = new ControladorGremio();  
                    Gremio gremio = c1.buscarOneGremio(Long.parseLong(request.getParameter("idGremio"))); 
                    Gson gsonBuilder = new GsonBuilder().create();
                    String gremioJson = gsonBuilder.toJson(gremio);
                    respuestaServer = gremioJson;
                    
                }else if(request.getParameter("action").equals("insertar")){
                    
                    //Se completa con los datos del ControladorAbertura
                    
                    String nombreGremio = request.getParameter("nombreGremio");
                    int nroPersonas = Integer.parseInt(request.getParameter("nroPersonas"));
                    LocalTime horarioDesde = LocalTime.parse(request.getParameter("horarioDesde"));
                    LocalTime horarioHasta = LocalTime.parse(request.getParameter("horarioHasta"));
                    LocalDate fechaDesde = LocalDate.parse(request.getParameter("fechaDesde"));
                    LocalDate fechaHasta = LocalDate.parse(request.getParameter("fechaHasta"));
                    int nroArgentinos = Integer.parseInt(request.getParameter("nroArgentinos"));
                    String nombreContratista = request.getParameter("nombreContratista");
                    String apellidoContratista = request.getParameter("apellidoContratista");
                    String comentario = request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = request.getParameter("estado");
                    Long idPersona = Long.parseLong(request.getParameter("idPersona"));
                    

                    ControladorGremio c1 = new ControladorGremio();   
                    Gremio gremio = new Gremio(nombreGremio, nroPersonas, horarioDesde, horarioHasta, fechaDesde, fechaHasta, nroArgentinos, nombreContratista, apellidoContratista, comentario, fechaAlta, fechaBaja, estado, idPersona);
                    c1.insertarGremio(gremio);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(gremio);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("actualizar")){
                    
                    
                    Long idGremio = Long.parseLong(request.getParameter("idGremio"));
                    String nombreGremio = request.getParameter("nombreGremio");
                    int nroPersonas = Integer.parseInt(request.getParameter("nroPersonas"));
                    LocalTime horarioDesde = LocalTime.parse(request.getParameter("horarioDesde"));
                    LocalTime horarioHasta = LocalTime.parse(request.getParameter("horarioHasta"));
                    LocalDate fechaDesde = LocalDate.parse(request.getParameter("fechaDesde"));
                    LocalDate fechaHasta = LocalDate.parse(request.getParameter("fechaHasta"));
                    int nroArgentinos = Integer.parseInt(request.getParameter("nroArgentinos"));
                    String nombreContratista = request.getParameter("nombreContratista");
                    String apellidoContratista = request.getParameter("apellidoContratista");
                    String comentario = request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = request.getParameter("estado");
                    Long idPersona = Long.parseLong(request.getParameter("idPersona"));
                    

                    ControladorGremio c1 = new ControladorGremio();  
                    Gremio gremio = new Gremio(idGremio, nombreGremio, nroPersonas, horarioDesde, horarioHasta, fechaDesde, fechaHasta, nroArgentinos, nombreContratista, apellidoContratista, comentario, fechaAlta, fechaBaja, estado, idPersona);
                    c1.actualizarGremio(gremio);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String gremioJson = gsonBuilder.toJson(gremio);
                    respuestaServer = gremioJson;
                    
                }else if(request.getParameter("action").equals("eliminar")){
                    
                    ControladorGremio c1 = new ControladorGremio();    
                    c1.eliminarGremio(Long.parseLong(request.getParameter("idGremio")));
                    List<Gremio> listaGremio = c1.buscarAllGremio();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String gremioJson = gsonBuilder.toJson(listaGremio);
                    respuestaServer = gremioJson;
                    
                }else if(request.getParameter("action").equals("eliminarLogico")){
                    
                    Long idGremio = Long.parseLong(request.getParameter("idGremio"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    
                    ControladorGremio c1 = new ControladorGremio();  
                    c1.eliminarLogicoGremio(idGremio, fechaBaja);
                    List<Gremio> listaGremio = c1.buscarAllGremio();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String gremioJson = gsonBuilder.toJson(listaGremio);
                    respuestaServer = gremioJson;
                    
                }else if(request.getParameter("action").equals("listarXidPersona")){
                    
                    Long idPersona = Long.parseLong(request.getParameter("idPersona"));
                    
                    ControladorGremio c1 = new ControladorGremio();
                    List<Gremio> listaGremio = c1.buscarAllGremioIdPersona(idPersona);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String listaJson = gsonBuilder.toJson(listaGremio);
                    respuestaServer = listaJson;
                    
                }
                
                
            }
            out.write(respuestaServer);
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            out.close();
        }
    }
    
    private void mostrarElementos(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException{
        try { 
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            response.setContentType("text/html");
            
            if(!isMultipart ) {
                System.out.println("NO ES MULTIPART");
                return;
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

        
           // Parse the request to get file items.
           List fileItems = upload.parseRequest(request);

           // Process the uploaded file items
           Iterator i = fileItems.iterator();

           while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                // Get the uploaded file parameters
                String fieldName = fi.getFieldName();
                String value = fi.getString();
                System.out.println("FieldName: " + fieldName);
                System.out.println("VALOR: " + value);
           }
           
           } catch(Exception ex) {
              System.out.println(ex);
           }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}







