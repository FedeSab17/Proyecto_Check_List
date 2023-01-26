import React from 'react';
import { Fragment } from 'react';
import './App.css';
import { Routes, Route} from "react-router-dom";
import Home from './components/Home';
import PrevCarga from "../src/components/PrevCarga"
import FormGeneral from "../src/components/FormGeneral"
import FormVisita from "../src/components/FormVisita"
import PrincipalCargaForm from "../src/components/PrincipalCargaForm"
import PedidoObra from "../src/components/PedidoObra"
import PrevCargaConclusion from "../src/components/PrevCargaConclusion"
import FormConclusion from "../src/components/FormConclusion"
import FormMateriales from "../src/components/FormMateriales"
import FormHumeda from "../src/components/FormHumeda"
import FormSeco from "../src/components/FormSeco"
import FormPaneles from "../src/components/FormPaneles"
import FormRedAgua from "../src/components/FormRedAgua"
import FormRedGas from "../src/components/FormRedGas"
import FormRedElectricidad from "../src/components/FormRedElectricidad"
import FormAberturas from "../src/components/FormAberturas"
import FormPersonas from "../src/components/FormPersonas"
import FormGremios from "../src/components/FormGremios"
import VerificarObra from "../src/components/VerificarObra"
import PrincipalVista from "../src/components/PrincipalVista"
import FormPrincipalVista from "../src/components/FormPrincipalVista"
import FormGeneralVista from "../src/components/FormGeneralVista"
import FormVisitaVista from "../src/components/FormVisitaVista"
import FormMaterialesVista from "../src/components/FormMaterialesVista"
import FormPersonasVista from "../src/components/FormPersonasVista"
import FormHumedaVista from "../src/components/FormHumedaVista"
import FormSecoVista from "../src/components/FormSecoVista"
import FormPanelesVista from "../src/components/FormPanelesVista"
import FormRedAguaVista from "../src/components/FormRedAguaVista"
import FormRedGasVista from "../src/components/FormRedGasVista"
import FormRedElectricidadVista from "../src/components/FormRedElectricidadVista"
import FormAberturasVista from "../src/components/FormAberturasVista"
import FormConclusionVista from "../src/components/FormConclusionVista"
import PrincipalGremioVista from "../src/components/PrincipalGremioVista"
import FormGremioVista from "../src/components/FormGremioVista"
import PrincipalAberturasVista from "../src/components/PrincipalAberturasVista"
import GrillaBusqueda from "../src/components/GrillaBusqueda"
import BusquedaObraAdmin from "../src/components/BusquedaObraAdmin"
import VisitasUpdate from "../src/components/VisitasUpdate"
import FormPrincipalUpdate from "../src/components/FormPrincipalUpdate"
import FormGeneralUpdate from "../src/components/FormGeneralUpdate"
import FormVisitaUpdate from "../src/components/FormVisitaUpdate"
import FormMaterialesUpdate from "../src/components/FormMaterialesUpdate"
import FormPersonaUpdate from "../src/components/FormPersonaUpdate"
import PrincipalGremioUpdate from "../src/components/PrincipalGremioUpdate"
import FormGremioUpdate from "../src/components/FormGremioUpdate"
import FormHumedaUpdate from "../src/components/FormHumedaUpdate"
import FormSecoUpdate from "../src/components/FormSecoUpdate"
import FormPanelesUpdate from "../src/components/FormPanelesUpdate"
import FormRedAguaUpdate from "../src/components/FormRedAguaUpdate"
import FormRedGasUpdate from "../src/components/FormRedGasUpdate"
import FormRedElectricidadUpdate from "../src/components/FormRedElectricidadUpdate"
import PrincipalAberturasUpdate from "../src/components/PrincipalAberturasUpdate"
import FormAberturasUpdate from "../src/components/FormAberturasUpdate"
import FormConclusionUpdate from "../src/components/FormConclusionUpdate"



const App = () => {

    return(

     
      <Fragment>

        <Routes>

          <Route path="/" element={<Home />} />
          <Route path="/grillaBusqueda" element={<GrillaBusqueda />} />
          <Route path="/prevCarga" element={<PrevCarga />} />
          <Route path="/formGeneral" element={<FormGeneral />} />
          <Route path="/formVisita" element={<FormVisita />} />
          <Route path="/formPrincipal" element={<PrincipalCargaForm />} />
          <Route path="/pedidoObra" element={<PedidoObra />} />
          <Route path="/prevCargaConclusion" element={<PrevCargaConclusion />} />
          <Route path="/formConclusion" element={<FormConclusion />} />
          <Route path="/formMateriales" element={<FormMateriales />} />
          <Route path="/formPersonas" element={<FormPersonas />} />
          <Route path="/formHumeda" element={<FormHumeda />} />
          <Route path="/formSeco" element={<FormSeco />} />
          <Route path="/formPaneles" element={<FormPaneles />} />
          <Route path="/formRedAgua" element={<FormRedAgua />} />
          <Route path="/formRedGas" element={<FormRedGas />} />
          <Route path="/formRedElectricidad" element={<FormRedElectricidad />} />
          <Route path="/formAberturas" element={<FormAberturas />} />
          <Route path="/formGremios" element={<FormGremios />} />
          <Route path="/verificarObra" element={<VerificarObra />} />
          <Route path="/principalVista" element={<PrincipalVista />} />
          <Route path="/formPrincipalVista" element={<FormPrincipalVista />} />
          <Route path="/formGeneralVista" element={<FormGeneralVista />} />
          <Route path="/formVisitaVista" element={<FormVisitaVista />} />
          <Route path="/formMaterialesVista" element={<FormMaterialesVista />} />
          <Route path="/formPersonasVista" element={<FormPersonasVista />} />
          <Route path="/formHumedaVista" element={<FormHumedaVista />} />
          <Route path="/formSecoVista" element={<FormSecoVista />} />
          <Route path="/formPanelesVista" element={<FormPanelesVista />} />
          <Route path="/formRedAguaVista" element={<FormRedAguaVista />} />
          <Route path="/formRedGasVista" element={<FormRedGasVista />} />
          <Route path="/formRedElectricidadVista" element={<FormRedElectricidadVista />} />
          <Route path="/principalAberturasVista" element={<PrincipalAberturasVista />} />
          <Route path="/formAberturasVista" element={<FormAberturasVista />} />
          <Route path="/formConclusionVista" element={<FormConclusionVista />} />
          <Route path="/principalGremioVista" element={<PrincipalGremioVista />} />
          <Route path="/formGremioVista" element={<FormGremioVista />} />
          <Route path="/busquedaAdmin" element={<BusquedaObraAdmin />} />
          <Route path="/visitasUpdate" element={<VisitasUpdate />} />
          <Route path="/formPrincipalUpdate" element={<FormPrincipalUpdate />} />
          <Route path="/formGeneralUpdate" element={<FormGeneralUpdate />} />
          <Route path="/formVisitaUpdate" element={<FormVisitaUpdate />} />
          <Route path="/formMaterialesUpdate" element={<FormMaterialesUpdate />} />
          <Route path="/formPersonaUpdate" element={<FormPersonaUpdate />} />
          <Route path="/principalGremioUpdate" element={<PrincipalGremioUpdate />} />
          <Route path="/formGremioUpdate" element={<FormGremioUpdate />} />
          <Route path="/formHumedaUpdate" element={<FormHumedaUpdate />} />
          <Route path="/formSecoUpdate" element={<FormSecoUpdate />} />
          <Route path="/formPanelesUpdate" element={<FormPanelesUpdate />} />
          <Route path="/formRedAguaUpdate" element={<FormRedAguaUpdate />} />
          <Route path="/formRedGasUpdate" element={<FormRedGasUpdate />} />
          <Route path="/formRedElectricidadUpdate" element={<FormRedElectricidadUpdate />} />
          <Route path="/PrincipalAberturasUpdate" element={<PrincipalAberturasUpdate />} />
          <Route path="/FormAberturasUpdate" element={<FormAberturasUpdate />} />
          <Route path="/formConclusionUpdate" element={<FormConclusionUpdate />} />
          

        </Routes>

      </Fragment>

    );
}

export default App;