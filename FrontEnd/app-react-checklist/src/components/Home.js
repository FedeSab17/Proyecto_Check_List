import React, { useState, useEffect, Fragment } from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";
import Table from 'react-bootstrap/Table'
import NavigationHome from "./NavigationHome";
import Image from 'react-bootstrap/Image'
import ImgLogo from "../assets/img/logoLtn.png"
import "../assets/css/home.css"



//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK / SE IMPLEMENTA RESPONSIVE=>
const Home = (props) => {

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

            <Alert.Heading className="alertTitle">CHECK LIST OBRA GRUPO LTN</Alert.Heading>

            <br></br>
            <br></br>  

            <Row fluid="true">

                <Col fluid="true">

                    <Image fluid="true" rounded="true" className='imgLogo' src={ImgLogo}></Image>
                
                </Col>

            </Row>

            <br></br> 

            <Row fluid="true">

                <Col fluid="true">
                
                    <h4>A traves de la aplicacion web check list de Grupo LTN los usuarios podran</h4><br></br>
                    <h4>acceder a cargar y visualizar datos necesarios para control de obras.</h4>
                </Col>

            </Row>

            <br></br> 

            <Row fluid="true">

                <Col fluid="true">
                
                    <Button fluid="true" variant='primary' href="/prevCarga" className='botonGrande'>CARGAR DATOS</Button>&nbsp;&nbsp;
                    <Button fluid="true" variant='primary' href="/verificarObra" className='botonGrande'>VER DATOS</Button>
                
                </Col>


            </Row>

            <br></br> 

            </Alert>

            </Container>

        </Fragment>

    )



}

export default Home;