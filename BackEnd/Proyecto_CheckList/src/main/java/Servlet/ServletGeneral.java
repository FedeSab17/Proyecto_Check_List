
package Servlet;


import Controlador.ControladorGeneral;
import Modelo.General;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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


//Se especifica el nombre y ruta de la clase: 'http://localhost:8080/Proyecto_CheckList/GeneralServlet?
@WebServlet(name = "GeneralServlet", urlPatterns = {"/GeneralServlet"})
public class ServletGeneral extends HttpServlet {
    
     
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
                    
                    ControladorGeneral c1 = new ControladorGeneral();
                    List<General> listaGeneral = c1.buscarAllGeneral();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(listaGeneral);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("buscar")){
                    
                    ControladorGeneral c1 = new ControladorGeneral();  
                    General general = c1.buscarOneGeneral(Long.parseLong(request.getParameter("idGeneral"))); 
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(general);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("insertar")){
                    
                    //Se completa con los datos del ControladorAbertura
                    
                    String codigo = request.getParameter("codigo");
                    String nombreCliente = request.getParameter("nombreCliente");
                    String dni = request.getParameter("dni");
                    String domicilio = request.getParameter("domicilio");
                    String usoEdificio = request.getParameter("usoEdificio");
                    double alturaEdificio = Double.parseDouble(request.getParameter("alturaEdificio"));
                    double m2Cubierta = Double.parseDouble(request.getParameter("m2Cubierta"));
                    double m2Muro = Double.parseDouble(request.getParameter("m2Muro"));
                    String alcance = request.getParameter("alcance");
                    int duracionObra = Integer.parseInt(request.getParameter("duracionObra"));
                    String comentario = request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = request.getParameter("estado");
                    

                    ControladorGeneral c1 = new ControladorGeneral();  
                    General general = new General(codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado);
                    c1.insertarGeneral(general);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(general);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("actualizar")){
                    
                    
                    Long idGeneral = Long.parseLong(request.getParameter("idGeneral"));
                    String codigo = request.getParameter("codigo");
                    String nombreCliente = request.getParameter("nombreCliente");
                    String dni = request.getParameter("dni");
                    String domicilio = request.getParameter("domicilio");
                    String usoEdificio = request.getParameter("usoEdificio");
                    double alturaEdificio = Double.parseDouble(request.getParameter("alturaEdificio"));
                    double m2Cubierta = Double.parseDouble(request.getParameter("m2Cubierta"));
                    double m2Muro = Double.parseDouble(request.getParameter("m2Muro"));
                    String alcance = request.getParameter("alcance");
                    int duracionObra = Integer.parseInt(request.getParameter("duracionObra"));
                    String comentario = request.getParameter("comentario");
                    LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
                    LocalDate fechaBaja = LocalDate.parse(request.getParameter("fechaBaja"));
                    String estado = request.getParameter("estado");
                    

                    ControladorGeneral c1 = new ControladorGeneral();  
                    General general = new General(idGeneral, codigo, nombreCliente, dni, domicilio, usoEdificio, alturaEdificio, m2Cubierta, m2Muro, alcance, duracionObra, comentario, fechaAlta, fechaBaja, estado);
                    c1.actualizarGeneral(general);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(general);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("eliminar")){
                    
                    ControladorGeneral c1 = new ControladorGeneral();   
                    c1.eliminarGeneral(Long.parseLong(request.getParameter("idGeneral")));
                    List<General> listaGeneral = c1.buscarAllGeneral();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(listaGeneral);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("ultimoId")){
                    
                    ControladorGeneral c1 = new ControladorGeneral();  
                    Long ultimoId = c1.ultimoIdGeneral();
                    Gson gsonBuilder = new GsonBuilder().create();
                    String ultimoJson = gsonBuilder.toJson(ultimoId);
                    respuestaServer = ultimoJson;
                    
                }else if(request.getParameter("action").equals("idGeneralxCodigo")){
                    
                    String codigo = request.getParameter("codigo");
                    
                    
                    ControladorGeneral c1 = new ControladorGeneral();    
                    Long idGeneral = c1.numeroId(codigo);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String idGeneralJson = gsonBuilder.toJson(idGeneral);
                    respuestaServer = idGeneralJson;
                    
                }else if(request.getParameter("action").equals("buscarCodigo")){
                    
                    
                    String codigo = request.getParameter("codigo");
                    
                    
                    ControladorGeneral c1 = new ControladorGeneral();  
                    General general = c1.buscarOneGeneralNobra(codigo);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(general);
                    respuestaServer = generalJson;
                    
                }else if(request.getParameter("action").equals("listarXnombre")){
                    
                    String nombre = request.getParameter("nombreCliente");
                    
                    ControladorGeneral c1 = new ControladorGeneral();
                    List<General> listaGeneral = c1.buscarAllGeneralNombre(nombre);
                    Gson gsonBuilder = new GsonBuilder().create();
                    String generalJson = gsonBuilder.toJson(listaGeneral);
                    respuestaServer = generalJson;
                
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




