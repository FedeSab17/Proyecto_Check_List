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
const PrincipalAberturasVista = (props) => {

    let navigate = useNavigate()

    const[dato,setDato] = useState(null)

    const[idVisita, setIdVisita] = useState(null)

    const[idGeneral, setIdGeneral] = useState(null)

   
    useEffect(() => {

       
        fondo()

        obtenerAberturas()
        
        setIdVisita(localStorage.getItem("idVisitaVista"))

        setIdGeneral(localStorage.getItem("idGeneralVista"))


    },[])


    //Metodo para modificar el color/imagen de la Pagina de Fondo =>
    const fondo = () => {

        document.body.style.backgroundImage = "url(https://imgur.com/mDsgdeZ.png)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundPosition = "center center";
        document.body.style.backgroundAttachment = "fixed";

    }

    const obtenerAberturas = async() => {


        try{

            let id = localStorage.getItem("idVisitaVista")

            console.log("ID_VISITA => ", id)

            const response = await axios("http://localhost:8080/Proyecto_CheckList/AberturaServlet",{

                method:"GET",
                params:{

                    action:"buscarId",
                    idVisita:id,


                }


            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Guardamos en el Hooks Dato =>
            setDato(resJson)

    
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

                <Alert.Heading className="alertTitle">LISTA DE ABERTURAS</Alert.Heading>

                <br></br>

                <h5 className='red'>No se encontraron datos de gremios.</h5>

                <br></br>

                <Row fluid="true">

                    <Col fluid="true">

                    <Button  fluid="true" type="button" href={`/formPrincipalVista?idGeneral=${idGeneral}&idVisita=${idVisita}`} variant="danger" size="lg">VOLVER</Button>

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

                <Alert.Heading className="alertTitle">PLANILLA DE ABERTURAS</Alert.Heading>

                <br></br>

                <h5 className='red'></h5>

                <br></br>
                <br></br>

                <Table className="tabla" striped bordered hover variant="dark" fluid="true" responsive="sm">

                    <thead>

                        <tr>

                            <th className='celda'>Indice</th>
                            <th className='celda'>Tipo de Abertura</th>
                            <th className='celda'>Cantidad</th>
                            <th className='celda'>M2</th>
                            <th className='celda'>Acciones</th>
                            
                        </tr>


                    </thead>


                    <tbody>

                        {dato.map((item,i) => (


                            <tr key={i}>

                                <td className='celda'>{i+1}</td>
                                <td className='celda'>{item.tipoAbertura}</td>
                                <td className='celda'>{item.cantidad}</td>
                                <td className='celda'>{item.m2}</td>
                                <td className='celda'>

                                    <Button fluid="true" variant="warning" size="sm" href={`/formAberturasVista?idAbertura=${item.idAbertura}`}>OBTENER DATOS</Button>

                                </td>

                            </tr>


                        ))}


                    </tbody>



                </Table>

                <br></br>

                <Row fluid="true">

                    <Col fluid="true">

                    <Button fluid="true" type="button" href={`/formPrincipalVista?idGeneral=${idGeneral}&idVisita=${idVisita}`} variant="danger" size="lg">VOLVER</Button>

                    </Col>

                </Row>

                <br></br>

                </Alert>

                </Container>

            </Fragment>



        )


    }    


}

export default PrincipalAberturasVista