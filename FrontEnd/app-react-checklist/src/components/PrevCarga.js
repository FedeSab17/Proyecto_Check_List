import React, { useState, useEffect, Fragment } from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";
import Table from 'react-bootstrap/Table'
import NavigationHome from "./NavigationHome";

//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK / SE IMPLEMENTA RESPONSIVE=>
const PrevCarga = (props) => {

    const[dato,setDato] = useState(null)

    useEffect(() => {

        fondo()


    },[])

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

            <Container className="body">

            <Alert variant="dark" fluid="true">

            <br></br>    

            <Alert.Heading className="alertTitle">¿ES LA PRIMERA VISITA A LA OBRA?</Alert.Heading>

            <br></br>
            <br></br>  

            <Row fluid="true">

                <Col fluid="true">
                
                    <Button fluid="true" variant='primary'  href="/formGeneral" className='botonGrande'>SI</Button>&nbsp;&nbsp;
                    <Button fluid="true" variant='primary'  href="/pedidoObra" className='botonGrande'>NO</Button>
                
                </Col>


            </Row>

            <br></br> 

            </Alert>

            </Container>

        </Fragment>

    )


}

export default PrevCarga