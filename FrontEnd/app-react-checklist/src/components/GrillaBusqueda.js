import React, { useState, useEffect, Fragment } from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";
import Table from 'react-bootstrap/Table'
import NavigationHome from "../components/NavigationHome";
import '../assets/css/principalVista.css'
import {useNavigate} from 'react-router-dom';
import { useLocation } from "react-router-dom";
import moment from 'moment';

//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK =>
const GrillaBusqueda = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //Obtengo los datos pasados por search URL =>
    let {search} = useLocation();
    let query = new URLSearchParams(search)

    const[dato,setDato] = useState(null)

    //Obtener el parametro que pasamos por el URL =>
    const[urlNombre,setUrlNombre] = useState(query.get("nombreCliente"))


    useEffect(() => {

        fondo()
       
        setUrlNombre(query.get("nombreCliente"))

        obtenerDatos()
        


    },[query.get("nombreCliente")])

    //Metodo para modificar el color/imagen de la Pagina de Fondo =>
    const fondo = () => {

        document.body.style.backgroundImage = "url(https://imgur.com/mDsgdeZ.png)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundPosition = "center center";
        document.body.style.backgroundAttachment = "fixed";

    }


    const obtenerDatos = async() => {

        //Se debe obtener todos los registros de General y despues gestionar una Sub-busqueda con metodo filter =>

        try{

            
            //Obtenemos el dato pasado x url =>
            let urlDato = query.get("nombreCliente")

        
            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GeneralServlet`,{

                method:"GET",
                params:{

                    action:"listar",
                    

                }


            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            if(resJson.length !== 0){

                //Filtramos con el metodo Filter buscando el dato descripcion buscador =>
                const encontrado = resJson.filter((item) => {

                    var matcher = new RegExp(urlDato, 'i')
                    return matcher.test([item.nombreCliente].join())

                })

                //Guardamos en el Hooks Dato =>
                setDato(encontrado)

            }else{

                setDato(null)

            }

    
        }catch(error){

            console.log(error)

        }


    }


    if(dato === null){

        return(

            <Fragment>

                <NavigationHome></NavigationHome>

                <br></br>

                <Container className='body'>

                <Alert variant="dark" fluid="true">

                <br></br>  

                <Alert.Heading className="alertTitle">RESULTADO DE BUSQUEDA POR NOMBRE DE CLIENTE</Alert.Heading>

                <br></br>

                <h5 className='red'>No se encontraron datos.</h5>

                <br></br>

                <Row fluid="true">

                    <Col fluid="true">

                    <Button fluid="true" type="button" href={`/`} variant="danger" size="lg">VOLVER</Button>

                    </Col>

                </Row>

                <br></br>

                </Alert>

                </Container>

            </Fragment>



        )

    }else{


        return(

            <Fragment>

                <NavigationHome></NavigationHome>

                <br></br>

                <Container className='body'>

                <Alert variant="dark" fluid="true">

                <br></br>  

                <Alert.Heading className="alertTitle">RESULTADO DE BUSQUEDA POR NOMBRE DE CLIENTE</Alert.Heading>

                <br></br>

                <h5 className='red'></h5>

                <br></br>
                <br></br>

                <Table fluid="true" className="tabla" striped bordered hover variant="dark" responsive="sm">

                    <thead>

                        <tr>

                            <th className='celda'>Indice</th>
                            <th className='celda'>N° de Obra</th>
                            <th className='celda'>Nombre del Cliente</th>
                            <th className='celda'>Dni/Cuil/Cuit</th>
                            <th className='celda'>Domicilio</th>
                            
                        </tr>


                    </thead>


                    <tbody>

                        {dato.map((item,i) => (


                            <tr key={i}>

                                <td className='celda'>{i+1}</td>
                                <td className='celda'>{item.codigo}</td>
                                <td className='celda'>{item.nombreCliente}</td>
                                <td className='celda'>{item.dni}</td>
                                <td className='celda'>{item.domicilio}</td>


                            </tr>


                        ))}


                    </tbody>



                </Table>

                <br></br>

                <Row fluid="true">

                    <Col fluid="true">

                    <Button fluid="true" type="button" href={`/`} variant="danger" size="lg">VOLVER</Button>

                    </Col>

                </Row>

                <br></br>

                </Alert>

                </Container>

            </Fragment>



        )


    }    


}

export default GrillaBusqueda