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


//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK =>
const PrincipalCargaForm = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    useEffect(() => {

        fondo()
        ultimoIdVisita()
        ultimoIdPersona()

    },[])

    //Metodo para modificar el color/imagen de la Pagina de Fondo =>
    const fondo = () => {

        document.body.style.backgroundImage = "url(https://imgur.com/mDsgdeZ.png)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundPosition = "center center";
        document.body.style.backgroundAttachment = "fixed";

    }

    //Metodo para solicitar el ultimo idVisita x n° de obra =>
    const ultimoIdVisita = async() => {

        let ultimoId = 0;

        //Obtener el n° de obra desde el localstorage =>
        let codigo = localStorage.getItem("codigo")

        try{

            const response = await fetch(`${process.env.REACT_APP_KEY}Proyecto_CheckList/VisitaServlet?action=ultimoId&codigo=${codigo}`, {

                method:"GET",
                
            })

            const resJson = await response.json()

            ultimoId = resJson

            console.log("ULTIMO ID_VISITA X N° DE OBRA => ", ultimoId)

            localStorage.setItem("idVisita", ultimoId)

        }catch(error){

            console.log("Error => ", error)

        }

    }

    //Metodo para solicitar el ultimo idPersona x n° de obra =>
    const ultimoIdPersona = async() => {

        let ultimoId = 0;

        //Obtener el n° de obra desde el localstorage =>
        let codigo = localStorage.getItem("codigo")

        try{

            const response = await fetch(`${process.env.REACT_APP_KEY}Proyecto_CheckList/PersonaServlet?action=ultimoId&codigo=${codigo}`, {

                method:"GET",
                
            })

            const resJson = await response.json()

            ultimoId = resJson

            console.log("ULTIMO ID_PERSONA X N° DE OBRA => ", ultimoId)

            localStorage.setItem("idPersona", ultimoId)

        }catch(error){

            console.log("Error => ", error)

        }

    }

    return(


        <Fragment>

            <NavigationHome></NavigationHome>

            <br></br>

            <Container className='body'>

            <Alert variant="dark" fluid="true">

            <br></br>  

            <Alert.Heading className="alertTitle">SELECCION DE FORMULARIOS PARA GARGA</Alert.Heading>

            <br></br>
            <br></br>

            <h5 className='orden'>* Seguir orden de Formularios numerados.</h5>

            <br></br>
            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formMateriales" className="botonForm" variant="primary">1- FORMULARIO MATERIALES</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formPersonas" className="botonForm" variant="primary">2- FORMULARIO PERSONAS</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true"> 

                    <Button fluid="true" href="/formGremios" className="botonForm" variant="primary">3- FORMULARIO GREMIOS</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formHumeda" className="botonForm" variant="primary">4- FORMULARIO OBRA HUMEDA</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formSeco" className="botonForm" variant="primary">5- FORMULARIO OBRA SECO</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formPaneles" className="botonForm" variant="primary">6- FORMULARIO PANELES</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formRedAgua" className="botonForm" variant="primary">7- FORMULARIO RED DE AGUA</Button>
                    
                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formRedGas" className="botonForm" variant="primary">8- FORMULARIO RED DE GAS</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formRedElectricidad" className="botonForm" variant="primary">9- FORMULARIO RED DE ELECTRICIDAD</Button>
                    
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col fluid="true">

                    <Button fluid="true" href="/formAberturas" className="botonForm" variant="primary">10- FORMULARIO ABERTURAS</Button>
                    
                </Col>

            </Row>

            <br></br>
            <br></br>

            <Row fluid="true">

                    <Col fluid="true">

                    <Button fluid="true" type="button" href={`/`}  size="lg" variant="danger">VOLVER</Button> 

                    </Col>

            </Row>


            <br></br>

            </Alert>

            </Container>

        </Fragment>


    )

}

export default PrincipalCargaForm