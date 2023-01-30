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
import "../assets/css/formGeneral.css"
import {useNavigate} from 'react-router-dom';
import { useLocation } from "react-router-dom";


//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK / SE IMPLEMENTA RESPONSIVE =>
const FormVisita = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //Obtengo los datos pasados por search URL =>
    let {search} = useLocation();
    let query = new URLSearchParams(search)

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    const[urlVisita,setUrlVisita] = useState(query.get("nroVisita"))

    const [visita, setVisita] = useState({

        fecha:'',
        nombreTecnico:'',
        apellidoTecnico:'',
        nVisita:'',
        fechaAlta:'',
        fechaBaja:'',
        estado:'',
        idGeneral:'',
        
    })


    useEffect(() => {

        fondo()

        setUrlVisita(query.get("nroVisita"))
        
    },[query.get("nroVisita")])


    //Metodo para modificar el color/imagen de la Pagina de Fondo =>
    const fondo = () => {

        document.body.style.backgroundImage = "url(https://imgur.com/mDsgdeZ.png)";
        document.body.style.backgroundRepeat = "no-repeat";
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundPosition = "center center";
        document.body.style.backgroundAttachment = "fixed";

    }


    //Metodo para obtener los datos ingresados en el form =>
    const handleInputChange = (event) => {

        setVisita({

            ...visita,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = async (visita, event) => {

            
        await insertar(visita);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setVisita({

            fecha:'',
            nombreTecnico:'',
            apellidoTecnico:'',
            nVisita:'',
            fechaAlta:'',
            fechaBaja:'',
            estado:'',
            idGeneral:'',

        });

        //Redirecciono y paso los datos a traves de un search =>
        navigate(`/formPrincipal`)

    
      
    }

    //Metodo para solicitar el idGeneral x N° de obra =>
    const idGeneral = async() => {

        let idGeneral;

        //Se obtiene el n° de obra =>
        let codigo = localStorage.getItem("codigo")

        console.log("OBTENER CODIGO => ", codigo)

        try{

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GeneralServlet`, {

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

        //Guardamos el idGeneral en el storage =>
        await localStorage.setItem("idGeneral", idGeneral)

        return idGeneral


    }

    //Metodo para solicitar el ultimo idGeneral =>
    const ultimoIdGeneral = async() => {

        let ultimoId = 0;

        try{

            const response = await fetch(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GeneralServlet?action=ultimoId`, {

                method:"GET",
                
            })

            const resJson = await response.json()

            ultimoId = resJson

            console.log("ULTIMO IDGENERAL => ", ultimoId)

        }catch(error){

            console.log("Error => ", error)

        }

        //Guardamos el idGeneral en el storage =>
        await localStorage.setItem("idGeneral", ultimoId)

        return ultimoId


    }

    

    //Metodo para insertar los datos a la BD =>
    const insertar = async(visita) => {

        const nroVisita = query.get("nroVisita")

        if(nroVisita === null || nroVisita === undefined){

            try{

                //Solicitamos el ultimo idGeneral ya que ingresa x primera visita =>
                let ultimoId = await ultimoIdGeneral()

                console.log("ULTIMO ID DESDE VISTA => ", ultimoId)

                const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/VisitaServlet`, {

                    method:"GET",
                    params:{

                        action:"insertar",
                        fecha:moment().format('YYYY-MM-DD'), 
                        nombreTecnico:visita.nombreTecnico,
                        apellidoTecnico:visita.apellidoTecnico,
                        nVisita:1,
                        //Se autocompletan =>
                        fechaAlta:moment().format('YYYY-MM-DD'), 
                        fechaBaja:moment("1900-01-01").format('YYYY-MM-DD'), 
                        estado:"activo",
                        idGeneral:ultimoId,

                    }

                })

                const resJson = await response.data

                console.log("DATOS API => ", resJson)

                //Guardamos el n° de visita en el localStorage =>
                localStorage.setItem("nVisita", 1);

                alert("DATOS GUARDADOS CON EXITO.")


            }catch(error){

                console.log("Error =>", error)

                alert("ERROR, NO FUE POSIBLE GUARDAR LOS DATOS, VUELVA A INTENTARLO.")

            }

        }else{

            try{

                //Solicitamos el idGeneral x codigo ya que es x n° de obra ingresado =>
                let id = await idGeneral()

                console.log("ID_GENERAL X N° OBRA => ", id)

                const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/VisitaServlet`, {

                    method:"GET",
                    params:{

                        action:"insertar",
                        fecha:moment().format('YYYY-MM-DD'), 
                        nombreTecnico:visita.nombreTecnico,
                        apellidoTecnico:visita.apellidoTecnico,
                        nVisita:nroVisita,
                        //Se autocompletan =>
                        fechaAlta:moment().format('YYYY-MM-DD'), 
                        fechaBaja:moment("1900-01-01").format('YYYY-MM-DD'), 
                        estado:"activo",
                        idGeneral:id,

                    }

                })

                const resJson = await response.data

                console.log("DATOS API => ", resJson)

                //Guardamos el n° de visita en el localStorage =>
                localStorage.setItem("nVisita", nroVisita);

                alert("DATOS GUARDADOS CON EXITO.")


            }catch(error){

                console.log("Error =>", error)

                alert("ERROR, NO FUE POSIBLE GUARDAR LOS DATOS, VUELVA A INTENTARLO.")

            }


        }    

    }

    


    return(


        <Fragment>

            <NavigationHome></NavigationHome>

            <br></br>

            <Container>

            <Alert variant="dark" fluid="true">

            <br></br>    

            <div className="body">

            <Alert.Heading className="alertTitle">FORMULARIO DE REGISTRO DE VISITA</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Nombre del Tecnico: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="nombreTecnico"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("nombreTecnico", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                               

                            }

                        })}   

                    >
                    </input>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.nombreTecnico && errors.nombreTecnico.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Apellido del Tecnico: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="apellidoTecnico"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("apellidoTecnico", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                               

                            }

                        })}   

                    >
                    </input>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.apellidoTecnico && errors.apellidoTecnico.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row className='body' fluid="true">   

                <Col fluid="true">
                    
                    <Button fluid="true" type="submit" variant="primary" size="lg">CARGAR</Button>&nbsp;&nbsp;
                    <Button fluid="true" type="button" href={`/prevCarga`} variant="danger" size="lg">VOLVER</Button>
                
                </Col>


            </Row>

            </Form>

            <br></br>

            </Alert>

            </Container>


        </Fragment>


    )

}

export default FormVisita