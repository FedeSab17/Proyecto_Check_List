import React, { useState, useEffect, Fragment } from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";
import Table from 'react-bootstrap/Table'
import NavigationHome from "../components/NavigationHome";
import Form from "react-bootstrap/Form";
import {useForm} from 'react-hook-form';
import moment from 'moment';
import "../assets/css/formPrincipal.css"
import {useNavigate} from 'react-router-dom';
import { useLocation } from "react-router-dom";

//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK =>
const FormPrincipalUpdate = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //Obtengo los datos pasados por search URL =>
    let {search} = useLocation();
    let query = new URLSearchParams(search)

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    //Obtener el parametro que pasamos por el URL =>
    const[urlGeneral,setUrlGeneral] = useState(query.get("idGeneral"))

    //Obtener el parametro que pasamos por el URL =>
    const[urlVisita,setUrlVisita] = useState(query.get("idVisita"))

    //Hooks  =>
    const[obra,setObra] = useState(null)

    useEffect(() => {

        fondo()

        //De esta forma esta a la escucha de las modificaciones en los search URL =>
        setUrlGeneral(query.get("idGeneral"))
        setUrlVisita(query.get("idVisita"))

        //Guardamos en el localStorage =>
        localStorage.setItem("idGeneralUpdate", urlGeneral)
        localStorage.setItem("idVisitaUpdate", urlVisita)

        //Obtenemos el n° de Obra =>
        setObra(localStorage.getItem("nObraUpdate"))
        

    },[query.get("idGeneral"), query.get("idVisita")])

    //Metodo para modificar el color/imagen de la Pagina de Fondo =>
    const fondo = () => {

        document.body.style.backgroundImage = "url(https://imgur.com/mDsgdeZ.png)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundPosition = "center center";
        document.body.style.backgroundAttachment = "fixed";

    }

    
    return(


        <Fragment>

            <NavigationHome></NavigationHome>

            <br></br>

            <Container className='body'>

            <Alert variant="dark" fluid="true">

            <br></br>  

            <Alert.Heading className="alertTitle">SELECCION DE FORMULARIOS PARA ACTUALIZACION DE DATOS</Alert.Heading>

            <br></br>
            <br></br>

            <h5 className='orden'>* Seguir orden de Formularios numerados.</h5>

            <br></br>
            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formGeneralUpdate" className="botonForm" variant="primary">1- FORMULARIO GENERAL</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formVisitaUpdate" className="botonForm" variant="primary">2- FORMULARIO VISITA</Button>
                    
                </Col>

            </Row>


            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formMaterialesUpdate" className="botonForm" variant="primary">3- FORMULARIO MATERIALES</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formPersonaUpdate" className="botonForm" variant="primary">4- FORMULARIO PERSONAS</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/principalGremioUpdate" className="botonForm" variant="primary">5- FORMULARIO GREMIOS</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formHumedaUpdate" className="botonForm" variant="primary">6- FORMULARIO OBRA HUMEDA</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formSecoUpdate" className="botonForm" variant="primary">7- FORMULARIO OBRA SECO</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formPanelesUpdate" className="botonForm" variant="primary">8- FORMULARIO PANELES</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formRedAguaUpdate" className="botonForm" variant="primary">9- FORMULARIO RED DE AGUA</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formRedGasUpdate" className="botonForm" variant="primary">10- FORMULARIO RED DE GAS</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formRedElectricidadUpdate" className="botonForm" variant="primary">11- FORMULARIO RED DE ELECTRICIDAD</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/principalAberturasUpdate" className="botonForm" variant="primary">12- FORMULARIO ABERTURAS</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formConclusionUpdate" className="botonForm" variant="primary">13- FORMULARIO CONCLUSION FINAL</Button>
                    
                </Col>

            </Row>

            <br></br>
            <br></br>

            <Row fluid="true">

                    <Col fluid="true">

                    <Button fluid="true" type="button" href={`/visitasUpdate`}  size="lg" variant="danger">VOLVER</Button> 

                    </Col>

            </Row>


            <br></br>

            </Alert>

            </Container>

        </Fragment>


    )

}

export default FormPrincipalUpdate