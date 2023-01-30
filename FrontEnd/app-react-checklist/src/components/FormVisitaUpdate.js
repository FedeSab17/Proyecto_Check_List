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
import {useNavigate} from 'react-router-dom';
import { useLocation } from "react-router-dom";
import "../assets/css/formMateriales.css";

//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK =>
const FormVisitaUpdate = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //Obtengo los datos pasados por search URL =>
    let {search} = useLocation();
    let query = new URLSearchParams(search)

    //Validar formulario con Libreria useForm =>
    const {register, formState: { errors }, handleSubmit, setValue} = useForm({

    })

    const[dato,setDato] = useState(null)

    const[idVisita, setIdVisita] = useState(null)

    const[idGeneral, setIdGeneral] = useState(null)

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

        //Obtenemos los datos del localStorage =>
        setIdGeneral(localStorage.getItem("idGeneralUpdate"))
        setIdVisita(localStorage.getItem("idVisitaUpdate"))

        cargarDatos()
        
    },[])

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

            
        actualizar(visita);

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

        
        //Redireccionamos a la pagina principal de formUpdate =>
        navigate(`/formPrincipalUpdate?idGeneral=${idGeneral}&idVisita=${idVisita}`)

    
      
    }

    
    const cargarDatos = async() => {   

        try{

            //Obtengo el n° de obra del localStorage =>
            let id = localStorage.getItem("idVisitaUpdate")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/VisitaServlet`,{

                method:"GET",
                params:{

                    action:"buscar",
                    idVisita:id,

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Pasar datos al form =>
            setValue("fecha",moment(`${resJson.fecha.year}-${resJson.fecha.month}-${resJson.fecha.day}`).format('YYYY-MM-DD'))
            setValue("nombreTecnico", resJson.nombreTecnico)
            setValue("apellidoTecnico", resJson.apellidoTecnico)
            
            //Guardo el nVisita en localStorage =>
            localStorage.setItem("nVisitaUpdate", resJson.nVisita)



        }catch(error){

            console.log(error)

            alert("ERROR, NO FUE POSIBLE OBTENER LOS DATOS, VUELVA A INTENTARLO.")

        }


    }


    const actualizar = async(visita) => {

        try{

            //Obtenermos el idGeneral del localStorage =>
            let idGen = localStorage.getItem("idGeneralUpdate")
            let idVis = localStorage.getItem("idVisitaUpdate")
            let nVis = localStorage.getItem("nVisitaUpdate")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/VisitaServlet`, {

                method:"GET",
                params:{

                   action:'actualizar',
                   fecha: visita.fecha,
                   nombreTecnico:visita.nombreTecnico,
                   apellidoTecnico:visita.apellidoTecnico,

                   //Datos que no pueden ser modificados => 
                   nVisita:nVis,
                   idGeneral:idGen,
                   idVisita:idVis,
                   
                   //Se autocompletan =>
                   fechaAlta:moment().format('YYYY-MM-DD'), 
                   fechaBaja:moment("1900-01-01").format('YYYY-MM-DD'), 
                   estado:"actualizado",


                }


            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)


            alert("DATOS ACTUALIZADOS CON EXITO.")


        }catch(error){

            console.log("Error => ", error)

            alert("ERROR, NO FUE POSIBLE ACTUALIZAR LOS DATOS, VUELVA A INTENTARLO.")


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

            <Alert.Heading className="alertTitle">FORMULARIO DE ACTUALIZACION DE VISITA</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha Visita: </label>

                
                </Col>

                <Col sm={2} fluid="true">
                    
                    <input 
                        type="date"
                        name="fecha"
                        onChange={handleInputChange}
                        placeholder=""
                        className="form-control"
                        {...register("fecha", { 

                            required:{
                                value: true,
                                message: '*' 
                            },

                        })}      
                    >
                    </input>
                
                
                </Col>

                <Col sm={1} fluid="true">

                      
                    <span className="text-danger text-small d-block mb-2">
                    {errors.fecha && errors.fecha.message}
                    </span>

                </Col>


            </Row>


            <br></br>   

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
                    
                    <Button fluid="true" type="submit" variant="primary" size="lg">ACTUALIZAR</Button>&nbsp;&nbsp;
                    <Button fluid="true" type="button" href={`/formPrincipalUpdate?idGeneral=${idGeneral}&idVisita=${idVisita}`} variant="danger" size="lg">VOLVER</Button>
                
                </Col>


            </Row>

            </Form>

            <br></br>

            </Alert>

            </Container>


        </Fragment>


    )



}

export default FormVisitaUpdate