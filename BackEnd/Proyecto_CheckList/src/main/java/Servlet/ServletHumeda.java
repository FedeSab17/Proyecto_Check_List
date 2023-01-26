
package Servlet;



import Controlador.ControladorHumeda;
import Modelo.Humeda;
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


//Se especifica el nombre y ruta de la clase: 'http://localhost:8080/Proyecto_CheckList/HumedaServlet?
@WebServlet(name = "HumedaServlet", urlPatterns = {"/HumedaServlet"})
public class ServletHumeda extends HttpServlet {
    
     
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
                    
                    ControladorHumeda c1 = new ControladorHumeda();
                    List<Humeda> listaHumeda = c1.buscarAllHumeda();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(listaHumeda);
                    respuestaServer = humedaJson;
                    
                }else if(request.getParameter("action").equals("buscar")){
                    
                    ControladorHumeda c1 = new ControladorHumeda();
                    Humeda humeda = c1.buscarOneHumeda(Long.parseLong(request.getParameter("idHumeda"))); 
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(humeda);
                    respuestaServer = humedaJson;
                    
                }else if(request.getParameter("action").equals("insertar")){
                    
                    //Se completa con los datos del ControladorAbertura
                    
                    LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
                    LocalDate fechaFinal = LocalDate.parse(request.getParameter("fechaFinal"));
                    double m2Piso = Double.parseDouble(request.getParameter("m2Piso"));
                    int pisoPerson = Integer.parseInt(request.getParameter("pisoPerson"));
                    double metros = Double.parseDouble(request.getParameter("metros"));
                    int metrosPerson = Integer.parseInt(request.getParameter("metrosPerson"));
                    double m2Muro = Double.parseDouble(request.getParameter("m2Muro"));
                    int muroPerson = Integer.parseInt(request.getParameter("muroPerson"));
                    double m2Cubierta = Double.parseDouble(request.getParameter("m2Cubierta"));
                    int cubiertaPerson = Integer.parseInt(request.getParameter("cubiertaPerson"));
                    double metrosLineales = Double.parseDouble(request.getParameter("metrosLineales"));
                    int linealesPerson = Integer.parseInt(request.getParameter("linealesPerson"));
                    int diasCaidos = Integer.parseInt(request.getParameter("diasCaidos"));
                    String motivo = request.getParameter("motivo");
                    String comentario = request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = request.getParameter("estado");
                    Long idVisita = Long.parseLong(request.getParameter("idVisita"));
                    
                    

                    ControladorHumeda c1 = new ControladorHumeda();  
                    Humeda humeda = new Humeda(fechaInicio, fechaFinal, m2Piso, pisoPerson, metros, metrosPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, comentario, fechaAlta, fechaBaja, estado, idVisita);
                    c1.insertarHumeda(humeda);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(humeda);
                    respuestaServer = humedaJson;
                    
                }else if(request.getParameter("action").equals("actualizar")){
                    
                    
                     //Se completa con los datos del ControladorAbertura
                    
                    Long idHumeda = Long.parseLong(request.getParameter("idHumeda")); 
                    LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
                    LocalDate fechaFinal = LocalDate.parse(request.getParameter("fechaFinal"));
                    double m2Piso = Double.parseDouble(request.getParameter("m2Piso"));
                    int pisoPerson = Integer.parseInt(request.getParameter("pisoPerson"));
                    double metros = Double.parseDouble(request.getParameter("metros"));
                    int metrosPerson = Integer.parseInt(request.getParameter("metrosPerson"));
                    double m2Muro = Double.parseDouble(request.getParameter("m2Muro"));
                    int muroPerson = Integer.parseInt(request.getParameter("muroPerson"));
                    double m2Cubierta = Double.parseDouble(request.getParameter("m2Cubierta"));
                    int cubiertaPerson = Integer.parseInt(request.getParameter("cubiertaPerson"));
                    double metrosLineales = Double.parseDouble(request.getParameter("metrosLineales"));
                    int linealesPerson = Integer.parseInt(request.getParameter("linealesPerson"));
                    int diasCaidos = Integer.parseInt(request.getParameter("diasCaidos"));
                    String motivo = request.getParameter("motivo");
                    String comentario = request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = request.getParameter("estado");
                    Long idVisita = Long.parseLong(request.getParameter("idVisita"));
                    

                    
                    ControladorHumeda c1 = new ControladorHumeda();  
                    Humeda humeda = new Humeda(idHumeda, fechaInicio, fechaFinal, m2Piso, pisoPerson, metros, metrosPerson, m2Muro, muroPerson, m2Cubierta, cubiertaPerson, metrosLineales, linealesPerson, diasCaidos, motivo, comentario, fechaAlta, fechaBaja, estado, idVisita);
                    c1.actualizarHumeda(humeda);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(humeda);
                    respuestaServer = humedaJson;
                    
                }else if(request.getParameter("action").equals("eliminar")){
                    
                    ControladorHumeda c1 = new ControladorHumeda();      
                    c1.eliminarHumeda(Long.parseLong(request.getParameter("idHumeda")));
                    List<Humeda> listaHumeda = c1.buscarAllHumeda();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(listaHumeda);
                    respuestaServer = humedaJson;
                    
                }else if(request.getParameter("action").equals("eliminarLogico")){
                    
                    Long idHumeda = Long.parseLong(request.getParameter("idHumeda"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    
                    ControladorHumeda c1 = new ControladorHumeda();   
                    c1.eliminarLogicoHumeda(idHumeda, fechaBaja);
                    List<Humeda> listaHumeda = c1.buscarAllHumeda();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(listaHumeda);
                    respuestaServer = humedaJson;
                    
                }else if(request.getParameter("action").equals("buscarIdVisita")){
                    
                    ControladorHumeda c1 = new ControladorHumeda();
                    Humeda humeda = c1.buscarOneHumedaIdVisita(Long.parseLong(request.getParameter("idVisita"))); 
                    Gson gsonBuilder = new GsonBuilder().create();
                    String humedaJson = gsonBuilder.toJson(humeda);
                    respuestaServer = humedaJson;
                    
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








