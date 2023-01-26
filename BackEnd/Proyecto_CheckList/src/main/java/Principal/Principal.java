
package Principal;

import Controlador.ControladorAbertura;
import Controlador.ControladorAuxVisitaGeneral;
import Controlador.ControladorConclusion;
import Controlador.ControladorGeneral;
import Controlador.ControladorGremio;
import Controlador.ControladorHumeda;
import Controlador.ControladorMaterial;
import Controlador.ControladorPanel;
import Controlador.ControladorPersona;
import Controlador.ControladorRedAgua;
import Controlador.ControladorRedElectricidad;
import Controlador.ControladorRedGas;
import Controlador.ControladorSeco;
import Controlador.ControladorVisita;
import Modelo.Abertura;
import Modelo.AuxVisitaGeneral;
import Modelo.Conclusion;
import Modelo.General;
import Modelo.Gremio;
import Modelo.Humeda;
import Modelo.Material;
import Modelo.Panel;
import Modelo.Persona;
import Modelo.RedAgua;
import Modelo.RedElectricidad;
import Modelo.RedGas;
import Modelo.Seco;
import Modelo.Visita;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Principal {
    
    
    //Clase principal para ejecutar los test pruebas de controladores, conexion con BD =>
    public static void main(String[] args) {
        
        
        //TESTEO DE CONTROLADOR Y MODELO GENERAL (1) =>
        
        /*
        
        //INSERT OBJETO GENERAL (OK) =>
        
        //General g1 = new General("P0123", "Maria", "Gonzalez", "31029020", "Adolfo Calle 21 Dorrego Guaymallen Mendoza", "R", 30, 22, 25, "IDC", 22, "Se verifica la obra", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo");
        
        General g1 = new General("P0234", "Oscar", "Rodriguez", "34232123", "Colon 232 Ciudad Mendoza", "NI", 23.22, 23.21, 25.34, "IDC", 150, "obra en proceso", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo");
        
        ControladorGeneral c1 = new ControladorGeneral();
        
        c1.insertarGeneral(g1);

        */  
        
        /*
        
        //UPDATE OBJETO GENERAL (OK) =>
        
        General g1 = new General(1L, "P0564", "Daniela", "Juarez", "4533222", "Bandera de los Andes Guaymallen Mendoza", "C", 23.22, 24.33, 89.33, "EE", 44, "ver obra", LocalDate.parse("2022-09-07"), LocalDate.parse("1900-01-01"), "inactivo");
        
        ControladorGeneral c1 = new ControladorGeneral();
        
        c1.actualizarGeneral(g1);

        */
        
        /*
        
        //READ_ONE OBJETO GENERAL (OK) =>
        
        ControladorGeneral c1 = new ControladorGeneral();
        
        General g1 = c1.buscarOneGeneral(1L);
        
        System.out.println(g1.toString());

        */
        
        /*
        
        //READ_ALL OBJETO GENERAL (OK) =>
        
        ControladorGeneral c1 = new ControladorGeneral();
        
        List<General> listaGeneral = c1.buscarAllGeneral();
        
        for (General item:listaGeneral) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }

        */
        
        /*
        
        //DELETE LOGIC OBJETO GENERAL (OK) =>
        
        ControladorGeneral c1 = new ControladorGeneral();
        
        c1.eliminarLogicoGeneral(2L, LocalDate.parse("1900-01-01"));

        */
        
        /*
        
        //ULTIMO IDGENERAL (OK) =>
        
        ControladorGeneral c1 = new ControladorGeneral();
        
        System.out.println("ULTIMO IDGENERAL => " + c1.ultimoIdGeneral());
        
        */
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO VISTA (2) =>
        
        /*
        
        //INSERT OBJETO VISTA (OK) =>
        
        Visita v1 = new Visita(LocalDate.parse("2022-09-14"), "Daniel", "Lombardo", 1, LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        Visita v2 = new Visita(LocalDate.parse("2022-09-22"), "Enrique", "Gutierrez", 1, LocalDate.parse("1900-01-01"), LocalDate.parse("1900-01-01"), "activo", 2L);
        
        ControladorVisita c1 = new ControladorVisita();
        
        c1.insertarVisita(v2);

        */
        
        /*
        
        //UPDATE OBJETO VISTA (OK) =>
        
        Visita v1 = new Visita(1L, LocalDate.parse("2022-09-13"), "Jorge", "Diaz", 2, LocalDate.parse("2022-09-22"), LocalDate.parse("1900-01-01"), "activo", 2L);
        
        ControladorVisita c1 = new ControladorVisita();
        
        c1.actualizarVisita(v1);

        */
        
        /*
        
        //READ_ONE OBJETO VISITA (OK) =>
        
        ControladorVisita c1 = new ControladorVisita();
        
        Visita v1 = c1.buscarOneVisita(1L);
       
        System.out.println(v1.toString());

        */
        
        
        /*
        
        //READ_ALL OBJETO VISITA (OK) =>
        
        ControladorVisita c1 = new ControladorVisita();
        
        List<Visita> listaVisita = c1.buscarAllVisita();
        
        for (Visita item:listaVisita) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }

        */
        
        
        /*
        
        //DELETE LOGIC OBJETO VISITA (OK) =>
        
        ControladorVisita c1 = new ControladorVisita();
        
        c1.eliminarLogicoVisita(1L, LocalDate.parse("1900-01-01"));
        

        */

        /*
        
        //INCREMENTAR EL VALOR DE N_VISITA A TRAVES N° DE OBRA (OK) =>
        
        ControladorVisita c1 = new ControladorVisita();
        int nVisita = c1.incrementarVisita("P0234");
        
        System.out.println("N° de Visita Incrementado => " + nVisita);
        
        */
        
        /*
        
        //OBTENER ULTIMO ID_VISITA X N° DE OBRA (OK) =>
        
        ControladorVisita c1 = new ControladorVisita();
        
        Long idVisita = c1.ultimoIdVisita("PO621");
        
        System.out.println("El ultimo idVisita ingresado es : " + idVisita);
        
        
        */
      
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO MATERIAL (3) =>
        
        /*
        
        //INSERT OBJETO MATERIAL (OK) =>
        
        Material m1 = new Material("M", "R", "B", "MB", "E", "E", "MB", "Se verifican los elementos", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        Material m2 = new Material("E", "E", "R", "MB", "R", "R", "MB", "Se detalla el material", LocalDate.parse("1900-01-01"), LocalDate.parse("1900-01-01"), "activo", 2L);
        
        ControladorMaterial c1 = new ControladorMaterial();
        
        c1.insertarMaterial(m2);

        */

        
        /*
       
        //UPDATE OBJETO MATERIAL (OK) =>
        
        Material m1 = new Material(1L, "E", "E", "E", "E", "B", "B", "E", "elementos y materiales", LocalDate.parse("2022-08-10"), LocalDate.parse("1900-01-01"), "inactivo", 1L);
        
        ControladorMaterial c1 = new ControladorMaterial();
        
        c1.actualizarMaterial(m1);

        */
        
        /*
        
        //READ_ONE OBJETO MATERIAL (OK) =>
        
        ControladorMaterial c1 = new ControladorMaterial();
        
        Material m1 = c1.buscarOneMaterial(1L);
       
        System.out.println(m1.toString());

        */
        
        /*
        
        //READ_ALL OBJETO MATERIAL (OK) =>
        
        ControladorMaterial c1 = new ControladorMaterial();
        
        List<Material> listaMaterial = c1.buscarAllMaterial();
        
        for (Material item:listaMaterial) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }

        */
        

        /*
        
        //DELETE LOGIC OBJETO MATERIAL (OK) =>
        
        ControladorMaterial c1 = new ControladorMaterial();
        
        c1.eliminarLogicoMaterial(2L, LocalDate.parse("1900-01-01"));
        
        
        */
        
        /*
        
        
        //READ_ONE OBJETO MATERIAL X N° DE ID_VISITA (OK) =>
        
        ControladorMaterial c1 = new ControladorMaterial();
        
        Material m1 = c1.buscarOneMaterialIdVisita(1L);
       
        System.out.println(m1.toString());
        
        */
        
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO PERSONA (4) =>
        
        /*
        
        //INSERT OBJETO PERSONA (OK) =>
        
        Persona p1 = new Persona(2, 2, "Obreros SA", "SI", "SI", "NO", "SI", "NO", "SI", "NO", "SI", 22, 27, 21, 35, "Se presentan 2 gremios", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        Persona p2 = new Persona(6, 1, "Puerto SA", "NO", "NO", "NO", "SI", "SI", "SI", "SI", "SI", 24, 34, 36, 39, "Se presenta 1 gremio", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 2L);
        
        ControladorPersona c1 = new ControladorPersona();
        
        c1.insertarPersona(p2);

        */
        
        
        /*
       
        //UPDATE OBJETO PERSONA (OK) =>
        
        Persona p1 = new Persona(1L, 4, 1, "Maritimo SA", "NO", "NO", "SI", "NO", "SI", "NO", "SI", "NO", 19, 23, 23, 12, "Se presentan", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorPersona c1 = new ControladorPersona();
        
        c1.actualizarPersona(p1);
        
        */

        
        /*
        
        //READ_ONE OBJETO PERSONA (OK) =>
        
        ControladorPersona c1 = new ControladorPersona();
        
        Persona p1 = c1.buscarOnePersona(1L);
       
        System.out.println(p1.toString());

        */
        
        /*
        
        //READ_ALL OBJETO PERSONA (OK) =>
        
        ControladorPersona c1 = new ControladorPersona();
        
        List<Persona> listaPersona = c1.buscarAllPersona();
        
        for (Persona item:listaPersona) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }

       */
        

        /*
        
        //DELETE LOGIC OBJETO PERSONA (OK) =>
        
        ControladorPersona c1 = new ControladorPersona();
        
        c1.eliminarLogicoPersona(1L, LocalDate.parse("2022-09-08"));
        
        
        */
        
        /*
        
        //OBTENER ULTIMO ID_PERSONA X N° DE OBRA (OK) =>
        
        ControladorPersona c1 = new ControladorPersona();
        
        Long idPersona = c1.ultimoIdPersona("PO621");
        
        System.out.println("El ultimo idPersona ingresado es : " + idPersona);
        
        */
        
        /*
        
        //READ_ONE OBJETO MATERIAL X N° DE ID_VISITA (OK) =>
        
        ControladorPersona c1 = new ControladorPersona();
        
        Persona p1 = c1.buscarOnePersonaIdVisita(1L);
       
        System.out.println(p1.toString());
        
        */
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO GREMIO (5) =>
        
        /*
        
        //INSERT OBJETO GREMIO (OK) =>
        
        Gremio g1 = new Gremio("Gremio Persona Salud",3, LocalTime.of(11, 00, 00), LocalTime.of(12, 00, 00), LocalDate.parse("2022-03-03"), LocalDate.parse("2022-05-07"), 3, "Marcelo", "Lopez",   LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorGremio c1 = new ControladorGremio();
        
        c1.insertarGremio(g1);
        
        */

 
        /*
       
        //UPDATE OBJETO GREMIO (OK) =>
        
        Gremio g1 = new Gremio(1L, "Gremio Persona", 2, LocalTime.of(12, 34, 23), LocalTime.of(15, 30, 00), LocalDate.parse("2022-01-02"), LocalDate.parse("2022-03-08"), 3, "Daniel", "Juarez",   LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorGremio c1 = new ControladorGremio();
        
        c1.actualizarGremio(g1);
 
        */
        
        /*
        
        //READ_ONE OBJETO GREMIO (OK) =>
        
        ControladorGremio c1 = new ControladorGremio();
        
        Gremio g1 = c1.buscarOneGremio(1L);
       
        System.out.println(g1.toString());

        */
        
        /*
        
        //READ_ALL OBJETO GREMIO (OK) =>
        
        ControladorGremio c1 = new ControladorGremio();
        
        List<Gremio> listaGremio = c1.buscarAllGremio();
        
        for (Gremio item:listaGremio) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        */

       
        /*
        
        //DELETE LOGIC OBJETO GREMIO (OK) =>
        
        ControladorGremio c1 = new ControladorGremio();
        
        c1.eliminarLogicoGremio(1L, LocalDate.parse("2022-09-08"));
        
        
        */
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO HUMEDA (6) =>
        
        /*
        
        //INSERT OBJETO HUMEDA (OK) =>
        
        Humeda h1 = new Humeda(LocalDate.parse("2022-01-12"), LocalDate.parse("2022-03-10"), "22", "3", "5", "2", "34", "3", "52", "3", "13", "2", "35", "Lluvia", "Esperando que pase la lluvia", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorHumeda c1 = new ControladorHumeda();
        
        c1.insertarHumeda(h1);
        
        */

 
        /*
       
        //UPDATE OBJETO HUMEDA (OK) =>
        
        
        Humeda h1 = new Humeda(1L, LocalDate.parse("2022-02-11"), LocalDate.parse("2022-04-11"), "21", "34", "43", "23", "12", "34", "34", "5", "11", "7", "2", "meteorito", "Esperando que pase", LocalDate.parse("2022-04-02"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorHumeda c1 = new ControladorHumeda();
        
        c1.actualizarHumeda(h1);
        
        */
        
        
        /*
        
        //READ_ONE OBJETO HUMEDA (OK) =>
        
        ControladorHumeda c1 = new ControladorHumeda();
        
        Humeda h1 = c1.buscarOneHumeda(1L);
       
        System.out.println(h1.toString());

        */
        
        
        /*
        
        //READ_ALL OBJETO HUMEDA (OK) =>
        
        ControladorHumeda c1 = new ControladorHumeda();
        
        List<Humeda> listaHumeda = c1.buscarAllHumeda();
        
        for (Humeda item:listaHumeda) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
       
        /*
        
        //DELETE LOGIC OBJETO HUMEDA (OK) =>
        
        ControladorHumeda c1 = new ControladorHumeda();
        
        c1.eliminarLogicoHumeda(1L, LocalDate.parse("2022-09-08"));
        
        */
        
        /*
        
        ControladorHumeda c1 = new ControladorHumeda();

        Humeda h1 = c1.buscarOneHumedaIdVisita(1L);
       
        System.out.println(h1.toString());
        
        */
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO SECO (7) =>
        
        /*
        
        //INSERT OBJETO SECO (OK) =>
        
        Seco s1 = new Seco(LocalDate.parse("2022-01-12"), LocalDate.parse("2022-03-10"), "22", "3", "5", "2", "34", "3", "52", "3", "53", "Lluvia", "Materiales Vigas", "Materiales Muros", "Materiales Cubiertas", "Esperando que pase la lluvia", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorSeco c1 = new ControladorSeco();
        
        c1.insertarSeco(s1);
        
        */ 
        
        
        /*
        
       
        //UPDATE OBJETO SECO (OK) =>
        
        
        Seco s1 = new Seco(1L, LocalDate.parse("2022-02-11"), LocalDate.parse("2022-04-23"), "12", "4", "5", "1", "14", "21", "12", "56", "22", "Fuego", "Materiales Vigas", "Materiales Muros", "Materiales Cubiertas", "Esperando que pase la lluvia", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorSeco c1 = new ControladorSeco();
        
        c1.actualizarSeco(s1);
        
        
        */
        
        
        /*
        
        //READ_ONE OBJETO SECO (OK) =>
        
        ControladorSeco c1 = new ControladorSeco();
        
        Seco s1 = c1.buscarOneSeco(1L);
       
        System.out.println(s1.toString());

        */
        
        
        /*
        
        //READ_ALL OBJETO SECO (OK) =>
        
        ControladorSeco c1 = new ControladorSeco();
        
        List<Seco> listaSeco = c1.buscarAllSeco();
        
        for (Seco item:listaSeco) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO SECO (OK) =>
        
        ControladorSeco c1 = new ControladorSeco();
        
        c1.eliminarLogicoSeco(1L, LocalDate.parse("2022-09-08"));
        
        */
        
        //BSUCAR ONE_SECO X ID_VISITA (OK) =>
        
        /*
        
        ControladorSeco c1 = new ControladorSeco();

        Seco s1 = c1.buscarOneSecoIdVisita(1L);
       
        System.out.println(s1.toString());
        
        */
        
        
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO PANEL (8) =>
        
        /*
        
        //INSERT OBJETO PANEL (OK) =>
        
        Panel p1 = new Panel( "SI", "Superior", "Doble masa", "tornillos amurados", "SI", "NO", 22.3, "Muy Bueno", "Muy bueno", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorPanel c1 = new ControladorPanel();
        
        c1.insertarPanel(p1);
        
        
        */
        
        
        /*

        //UPDATE OBJETO PANEL (OK) =>
        
        
        Panel p1 = new Panel(1L, "NO", "Baja", "Doble", "tornillos", "No", "Si", 25.6, "Excelente", "Maravilla", LocalDate.parse("2022-09-12"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorPanel c1 = new ControladorPanel();
        
        c1.actualizarPanel(p1);
        
        
        */
        
        /*
        
        //READ_ONE OBJETO PANEL (OK) =>
        
        ControladorPanel c1 = new ControladorPanel();
        
        Panel p1 = c1.buscarOnePanel(1L);
       
        System.out.println(p1.toString());

        */
        
        
        /*
        
        //READ_ALL OBJETO PANEL (OK) =>
        
        ControladorPanel c1 = new ControladorPanel();
        
        List<Panel> listaPanel = c1.buscarAllPanel();
        
        for (Panel item:listaPanel) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO PANEL (OK) =>
        
        ControladorPanel c1 = new ControladorPanel();
        
        c1.eliminarLogicoPanel(1L, LocalDate.parse("2022-07-07"));
        
        
        */
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO RED_AGUA (9) =>
        
        /*
        
        //INSERT OBJETO RED_AGUA (OK) =>
        
        RedAgua a1 = new RedAgua(LocalDate.parse("2022-09-08"), LocalDate.parse("2022-01-01"), 22.22, 3, "Hola mundo", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorRedAgua c1 = new ControladorRedAgua();
        
        c1.insertarRedAgua(a1);
        
        */
        

        /*

        //UPDATE OBJETO RED_AGUA (OK) =>
        
        
        RedAgua a1 = new RedAgua(1L, LocalDate.parse("2022-01-02"), LocalDate.parse("2022-02-02"), 33.22, 4, "Hola", LocalDate.parse("2022-03-03"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorRedAgua c1 = new ControladorRedAgua();
        
        c1.actualizarRedAgua(a1);
        
        */
        
        
        /*
        
        //READ_ONE OBJETO RED_AGUA (OK) =>
        
        ControladorRedAgua c1 = new ControladorRedAgua();
        
        RedAgua p1 = c1.buscarOneRedAgua(1L);
       
        System.out.println(p1.toString());

        */
        
        
        /*
        
        //READ_ALL OBJETO RED_AGUA (OK) =>
        
        ControladorRedAgua c1 = new ControladorRedAgua();
        
        List<RedAgua> listaRedAgua = c1.buscarAllRedAgua();
        
        for (RedAgua item:listaRedAgua) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO RED_AGUA (OK) =>
        
        ControladorRedAgua c1 = new ControladorRedAgua();
        
        c1.eliminarLogicoRedAgua(1L, LocalDate.parse("2022-07-07"));
        
        */
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO RED_GAS (10) =>
        
        
        /*
        
        //INSERT OBJETO RED_GAS (OK) =>
        
        RedGas g1 = new RedGas(LocalDate.parse("2022-09-08"), LocalDate.parse("2022-01-01"), 22.22, 3, "Hola mundo", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorRedGas c1 = new ControladorRedGas();
        
        c1.insertarRedGas(g1);
        
        */
        

        /*

        //UPDATE OBJETO RED_GAS (OK) =>
        
        
        RedGas g1 = new RedGas(1L, LocalDate.parse("2022-10-02"), LocalDate.parse("2022-02-04"), 54.22, 5, "Hola", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorRedGas c1 = new ControladorRedGas();
        
        c1.actualizarRedGas(g1);
        
        */
        
        
        /*
        
        //READ_ONE OBJETO RED_GAS (OK) =>
        
        ControladorRedGas c1 = new ControladorRedGas();
        
        RedGas p1 = c1.buscarOneRedGas(1L);
       
        System.out.println(p1.toString());

        
        */
        
        
        /*
        
        //READ_ALL OBJETO RED_GAS (OK) =>
        
        ControladorRedGas c1 = new ControladorRedGas();
        
        List<RedGas> listaRedGas = c1.buscarAllRedGas();
        
        for (RedGas item:listaRedGas) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO RED_AGUA (OK) =>
        
        ControladorRedGas c1 = new ControladorRedGas();
        
        c1.eliminarLogicoRedGas(1L, LocalDate.parse("2022-07-07"));
        
        */
        
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO RED_ELECTRICIDAD (11) =>
        
        
        /*
        
        //INSERT OBJETO RED_ELECTRICIDAD (OK) =>
        
        RedElectricidad e1 = new RedElectricidad(LocalDate.parse("2022-09-08"), LocalDate.parse("2022-01-01"), 22.22, 3, "Hola mundo", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorRedElectricidad c1 = new ControladorRedElectricidad();
        
        c1.insertarRedElectricidad(e1);
        
        
        */

        
        /*

        //UPDATE OBJETO RED_ELECTRICIDAD (OK) =>
        
        
        RedElectricidad e1 = new RedElectricidad(1L, LocalDate.parse("2022-05-03"), LocalDate.parse("2022-03-03"), 45.22, 4, "Hola", LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorRedElectricidad c1 = new ControladorRedElectricidad();
        
        c1.actualizarRedElectricidad(e1);
        
        
        */
        
        
        /*
        
        //READ_ONE OBJETO RED_ELECTRECIDAD (OK) =>
        
        ControladorRedElectricidad c1 = new ControladorRedElectricidad();
        
        RedElectricidad p1 = c1.buscarOneRedElectricidad(1L);
       
        System.out.println(p1.toString());

        
        */
        
        
        /*
        
        //READ_ALL OBJETO RED_ELECTRICIDAD (OK) =>
        
        ControladorRedElectricidad c1 = new ControladorRedElectricidad();
        
        List<RedElectricidad> listaRedElectricidad = c1.buscarAllRedElectricidad();
        
        for (RedElectricidad item:listaRedElectricidad) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO RED_ELECTRICIDAD (OK) =>
        
        ControladorRedElectricidad c1 = new ControladorRedElectricidad();
        
        c1.eliminarLogicoRedElectricidad(1L, LocalDate.parse("2022-07-07"));
        
        
        */
        
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO ABERTURA (12) =>
        
        
        /*
        
        //INSERT OBJETO ABERTURA (OK) =>
        
        Abertura e1 = new Abertura(LocalDate.parse("2022-07-05"), LocalDate.parse("2022-05-22"), 5, 45.67, 5, "Hola",LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorAbertura c1 = new ControladorAbertura();
        
        c1.insertarAbertura(e1);
        
        
        */

        
        /*

        //UPDATE OBJETO ABERTURA (OK) =>
        
        
        Abertura e1 = new Abertura(1L, LocalDate.parse("2022-08-05"), LocalDate.parse("2022-04-03"), 7, 23.67, 9, "Hola Mundo",LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorAbertura c1 = new ControladorAbertura();
        
        c1.actualizarAbertura(e1);
        
        */
        
        
        
        /*
        
        //READ_ONE OBJETO ABERTURA (OK) =>
        
        ControladorAbertura c1 = new ControladorAbertura();
        
        Abertura p1 = c1.buscarOneAbertura(1L);
       
        System.out.println(p1.toString());

        */
        
        
        
        /*
        
        //READ_ALL OBJETO ABERTURA (OK) =>
        
        ControladorAbertura c1 = new ControladorAbertura();
        
        List<Abertura> listaAbertura = c1.buscarAllAbertura();
        
        for (Abertura item:listaAbertura) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO ABERTURA (OK) =>
        
        ControladorAbertura c1 = new ControladorAbertura();
        
        c1.eliminarLogicoAbertura(1L, LocalDate.parse("2022-07-07"));
        
        
        */
        
        /*
        
        //READ_ALL OBJETO ABERTURA X ID_VISITA (OK) =>
        
        ControladorAbertura c1 = new ControladorAbertura();
        
        List<Abertura> listaAbertura = c1.buscarAllAberturaIdVisita(1L);
        
        for (Abertura item:listaAbertura) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        */
        
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR Y MODELO CONCLUSION (13) =>
        
        
        /*
        
        //INSERT OBJETO CONCLUSION (OK) =>
        
        Conclusion e1 = new Conclusion("SI", 50, 70, LocalDate.parse("2022-07-06"), 8, "Muy satisfecho con la obra" ,LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorConclusion c1 = new ControladorConclusion();
        
        c1.insertarConclusion(e1);
        
        */
        

        /*
       
        //UPDATE OBJETO CONCLUSION (OK) =>
        
        
        Conclusion e1 = new Conclusion(1L, "NO", 40, 80, LocalDate.parse("2022-03-04"), 7, "Muy satisfecho" ,LocalDate.parse("2022-09-08"), LocalDate.parse("1900-01-01"), "activo", 1L);
        
        ControladorConclusion c1 = new ControladorConclusion();
        
        c1.actualizarConclusion(e1);
        
        
        */
        
        
        /*
        
        //READ_ONE OBJETO CONCLUSION  (OK) =>
        
        ControladorConclusion c1 = new ControladorConclusion();
        
        Conclusion p1 = c1.buscarOneConclusion(1L);
       
        System.out.println(p1.toString());

        
        */
        
        
        /*
        
        //READ_ALL OBJETO CONCLUSION (OK) =>
        
        ControladorConclusion c1 = new ControladorConclusion();
        
        List<Conclusion> listaConclusion = c1.buscarAllConclusion();
        
        for (Conclusion item:listaConclusion) {
            
            System.out.println(item.toString());
            System.out.println("");
            
        }
        
        
        */
        
       
        /*
        
        //DELETE LOGIC OBJETO CONCLUSION (OK) =>
        
        ControladorConclusion c1 = new ControladorConclusion();
        
        c1.eliminarLogicoConclusion(1L, LocalDate.parse("2022-07-07"));
        
        
        */
        
        
        /*
        
        ControladorConclusion c1 = new ControladorConclusion();

        Conclusion d1 = c1.buscarOneConclusionIdGeneral(1L);
       
        System.out.println(d1.toString());
        
        
        */
        
       
        
        /*
        
        //----------------------------------------------------------
        
        //TESTEO DE CONTROLADOR AUX_VISITA_GENERAL BUSCAR ALL X N° DE OBRA =>
        
        ControladorAuxVisitaGeneral c1 = new ControladorAuxVisitaGeneral();
        List<AuxVisitaGeneral> listaAux = c1.buscarAllAuxVisitaGeneral("PO7893");
        
        for(AuxVisitaGeneral item:listaAux){
            
            
            System.out.println(item.toString());
            System.out.println("");
            
            
        }

        */
        
    }    
        
}
