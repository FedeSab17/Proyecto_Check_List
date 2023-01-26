
package Servlet;


import Controlador.ControladorConclusion;
import Modelo.Conclusion;
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


//Se especifica el nombre y ruta de la clase: 'http://localhost:8080/Proyecto_CheckList/ConclusionServlet?
@WebServlet(name = "ConclusionServlet", urlPatterns = {"/ConclusionServlet"})
public class ServletConclusion extends HttpServlet {
    
     
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
                    
                    ControladorConclusion c1 = new ControladorConclusion();
                    List<Conclusion> listaConclusion = c1.buscarAllConclusion();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(listaConclusion);
                    respuestaServer = conclusionJson;
                    
                }else if(request.getParameter("action").equals("buscar")){
                    
                    ControladorConclusion c1 = new ControladorConclusion();  
                    Conclusion conclusion = c1.buscarOneConclusion(Long.parseLong(request.getParameter("idConclusion"))); 
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(conclusion);
                    respuestaServer = conclusionJson;
                    
                }else if(request.getParameter("action").equals("insertar")){
                    
                    //Se completa con los datos del ControladorAbertura
                    
                    String obraTerminada = request.getParameter("obraTerminada");
                    double avanceActual = Double.parseDouble(request.getParameter("avanceActual"));
                    double avanceEsperado = Double.parseDouble(request.getParameter("avanceEsperado"));
                    LocalDate fechaFinalizacion = LocalDate.parse(request.getParameter("fechaFinalizacion"));
                    int gradoSatisfaccion = Integer.parseInt(request.getParameter("gradoSatisfaccion"));
                    String comentario =  request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = (request.getParameter("estado"));
                    Long idGeneral = Long.parseLong(request.getParameter("idGeneral"));
            

                    ControladorConclusion c1 = new ControladorConclusion();  
                    Conclusion conclusion = new Conclusion(obraTerminada, avanceActual, avanceEsperado, fechaFinalizacion, gradoSatisfaccion, comentario, fechaAlta, fechaBaja, estado, idGeneral);
                    c1.insertarConclusion(conclusion);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(conclusion);
                    respuestaServer = conclusionJson;
                    
                }else if(request.getParameter("action").equals("actualizar")){
                    
                     //Se completa con los datos del ControladorAbertura
                    
                    Long idConclusion = Long.parseLong(request.getParameter("idConclusion"));
                    String obraTerminada = request.getParameter("obraTerminada");
                    double avanceActual = Double.parseDouble(request.getParameter("avanceActual"));
                    double avanceEsperado = Double.parseDouble(request.getParameter("avanceEsperado"));
                    LocalDate fechaFinalizacion = LocalDate.parse(request.getParameter("fechaFinalizacion"));
                    int gradoSatisfaccion = Integer.parseInt(request.getParameter("gradoSatisfaccion"));
                    String comentario =  request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = (request.getParameter("estado"));
                    Long idGeneral = Long.parseLong(request.getParameter("idGeneral"));
            

                    ControladorConclusion c1 = new ControladorConclusion();  
                    Conclusion conclusion = new Conclusion(idConclusion, obraTerminada, avanceActual, avanceEsperado, fechaFinalizacion, gradoSatisfaccion, comentario, fechaAlta, fechaBaja, estado, idGeneral);
                    c1.actualizarConclusion(conclusion);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(conclusion);
                    respuestaServer = conclusionJson;
                    
                }else if(request.getParameter("action").equals("eliminar")){
                    
                    ControladorConclusion c1 = new ControladorConclusion(); 
                    c1.eliminarConclusion(Long.parseLong(request.getParameter("idConclusion")));
                    List<Conclusion> listaConclusion = c1.buscarAllConclusion();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(listaConclusion);
                    respuestaServer = conclusionJson;
                    
                }else if(request.getParameter("action").equals("eliminarLogico")){
                    
                    Long idConclusion = Long.parseLong(request.getParameter("idConclusion"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    
                    ControladorConclusion c1 = new ControladorConclusion();  
                    c1.eliminarLogicoConclusion(idConclusion, fechaBaja);
                    List<Conclusion> listaConclusion = c1.buscarAllConclusion();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(listaConclusion);
                    respuestaServer = conclusionJson;
                    
                }else if(request.getParameter("action").equals("contadorConclusion")){
                    
                    Long idGeneral = Long.parseLong(request.getParameter("idGeneral"));
                    
                    
                    ControladorConclusion c1 = new ControladorConclusion();     
                    int contador = c1.registroConclusionXid(idGeneral);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String contadorJson = gsonBuilder.toJson(contador);
                    respuestaServer = contadorJson;
                    
                }else if(request.getParameter("action").equals("buscarIdGeneral")){
                    
                    ControladorConclusion c1 = new ControladorConclusion();     
                    Conclusion conclusion = c1.buscarOneConclusionIdGeneral(Long.parseLong(request.getParameter("idGeneral"))); 
                    Gson gsonBuilder = new GsonBuilder().create();
                    String conclusionJson = gsonBuilder.toJson(conclusion);
                    respuestaServer = conclusionJson;
                    
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

