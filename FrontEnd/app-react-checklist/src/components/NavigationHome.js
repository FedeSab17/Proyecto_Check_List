import React, {Component, Fragment, useState, useEffect} from 'react';
import Button from 'react-bootstrap/Button';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import FormControl from 'react-bootstrap/FormControl';
import Form from 'react-bootstrap/Form';
import Container from "react-bootstrap/Container";
import Label from "react-bootstrap/FormLabel";
import {useNavigate} from 'react-router-dom';
import NavHome from "../components/NavHome";
import "../assets/css/navigation.css"

// SE IMPLEMENTA NAVBAR RESPONSIVE =>
const NavigationHome = (props) => {

    const navigate = useNavigate()

    const[dato,setDato] = useState({

        nombreCliente:null,

    })


    useEffect(() => {


    },[])

    //ESTE METODO SE PUEDE USAR PARA CAPTURAR LA INFORMACION INGRESADA EN EL FORM:
    const handleInputChange = (event) => {

        setDato({

            ...dato,
            [event.target.name] : event.target.value

        })

    }

    //Metodo para accionar el evento onClink =>
    const obtenerNombre = () => {

        if(dato.nombreCliente === '' || dato.nombreCliente === null){

            alert("DEBE INGRESAR DATOS A LA BUSQUEDA DE NOMBRE CLIENTE")

        }else{

            console.log("OBTENER NOMBRE => ", dato.nombreCliente)

            
            //Redirecciono y paso los datos a traves de un search =>
            navigate(`/grillaBusqueda?nombreCliente=${dato.nombreCliente}`)

            

        }    

    }




    return (

        <Fragment>

        <Navbar collapseOnSelect expand="sm" bg="dark" variant="dark" >
            <Navbar.Toggle aria-controls="navbarScroll" data-bs-taget="#navbarScroll" />
            <Navbar.Collapse id="navbarScroll">
            <NavHome/>
            <Nav className="me-auto">
            <Nav.Link  href="/prevCarga" >CARGAR DATOS</Nav.Link>
            <Nav.Link  href="/verificarObra" >VER DATOS</Nav.Link>
            <Nav.Link  href="/busquedaAdmin" >ACTUALIZAR DATOS</Nav.Link>
            </Nav>

            <Form className="d-flex">
                <Form.Label ></Form.Label>&nbsp;&nbsp;
                <FormControl
                    type="search"
                    placeholder="Nombre de Cliente"
                    className="me-2"
                    aria-label="Search"
                    name="nombreCliente"
                    onChange={handleInputChange}
                />&nbsp;&nbsp;
                <Button variant="danger" onClick={obtenerNombre}>SEARCH</Button>&nbsp;&nbsp;
            </Form>
           

            </Navbar.Collapse>
        </Navbar>

        </Fragment>  

    );  



}

export default NavigationHome;