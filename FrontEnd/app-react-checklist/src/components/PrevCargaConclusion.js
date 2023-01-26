import React, { useState, useEffect, Fragment } from 'react';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";
import Table from 'react-bootstrap/Table'
import NavigationHome from "./NavigationHome";
import {useNavigate} from 'react-router-dom';
import "../assets/css/prevCargaConclusion.css"

const PrevCargaConclusion = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    const[dato,setDato] = useState(null)

    const[nVisitaIncremt, setNVisitaIncrement] = useState()

    const[validar,setValidar] = useState({

        validarConclusion:false,

    })

    useEffect(() => {

        fondo()

        let n =  localStorage.getItem("nVisitaIncremental")

        //Actualizo el numero visita para pasar por incremento =>
        setNVisitaIncrement(n)

        evento()

    },[])

    //Metodo para modificar el color/imagen de la Pagina de Fondo =>
    const fondo = () => {

        document.body.style.backgroundImage = "url(https://imgur.com/mDsgdeZ.png)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundPosition = "center center";
        document.body.style.backgroundAttachment = "fixed";

    }


    //Metodo para solicitar el idGeneral x N° de obra =>
    const idGeneral = async() => {

        let idGeneral;

        let codigo = localStorage.getItem("codigo")

        console.log("OBTENER CODIGO => ", codigo)

        try{

            const response = await axios("http://localhost:8080/Proyecto_CheckList/GeneralServlet", {

                method:"GET",
                params:{

                    action:"idGeneralxCodigo",
                    codigo: codigo,

                }
                
            })

            const resJson = await response.data

            idGeneral = resJson


        }catch(error){

            console.log("Error => ", error)

        }

        console.log("VALOR ID_GENERAL => ", idGeneral)

        return idGeneral


    }

    //Metodo para obtener contador de registros conclusion  =>
    const contadorConclusion = async(idGeneral) => {

        let contador = 0;

        console.log("idGeneral => ", idGeneral)

        try{

            const response = await axios("http://localhost:8080/Proyecto_CheckList/ConclusionServlet", {

                method:"GET",
                params:{

                    action:"contadorConclusion",
                    idGeneral:idGeneral,

                }
                
            })

            const resJson = await response.data

            contador = resJson


        }catch(error){

            console.log("Error => ", error)

        }

        console.log("CONTADOR DE REGISTRO CONCLUSION => ", contador)


        return contador


    }

    //Metodo que ejecuta el evento click del boton SI =>
    const evento = async() => {

        let id = await idGeneral()

        //Verificamos si la entidad Conclusion ya tiene gargado un Form =>
        let contador = await contadorConclusion(id)

        let codigo = localStorage.getItem("codigo")

        if(contador === 1){

            document.querySelector("#mensaje").innerHTML = "El n° de obra ingresado " + codigo + " ya tiene cargada la conclusion final de obra."

            //Se cambia el valor de hooks validar, para bloquear el boton de formularios =>
            setValidar({

                validarConclusion:true,

            })

        }else{

             //Se cambia el valor de hooks validar, para bloquear el boton de formularios =>
             setValidar({

                validarConclusion:false,

            })

        }


    }

    return(

        <Fragment>

            <NavigationHome></NavigationHome>

            <br></br>

            <Container className="body">

            <Alert variant="dark" fluid="true">

            <br></br>    

            <Alert.Heading className="alertTitle">SELECCIONAR LA OPCION CORRECTA</Alert.Heading>

            <br></br>
            <br></br>  

            <Row fluid="true">

                <Col fluid="true">
                
                    <Button  fluid="true" type="button" variant='primary' size="lg" href={`/formVisita?nroVisita=${nVisitaIncremt}`} disabled={validar.validarConclusion === true} >NUEVA VISITA</Button>&nbsp;&nbsp;
                    <Button  fluid="true" type="button" variant='primary' size="lg" href={`/formConclusion`}  disabled={validar.validarConclusion === true} >CONCLUSION</Button>
                
                </Col>


            </Row>

            <br></br>

            <Row fluid="true">

                    <Col fluid="true"> 

                        <Button fluid="true" type="button" variant='danger' size="lg" href="/" >VOLVER</Button>
                        
                    
                    </Col>

            </Row>

            <br></br>
            <br></br>

            <Row fluid="true">

                    <Col fluid="true">

                        <h3 id="mensaje" className='mensaje'></h3>

                    </Col>

            </Row>

            <br></br> 

            </Alert>

            </Container>

        </Fragment>

    )


}

export default PrevCargaConclusion