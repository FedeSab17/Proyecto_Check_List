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
import "../assets/css/formMateriales.css";

//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK =>

const FormVisitaVista = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

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
        estado:'',
       
    })


    useEffect(() => {


        fondo()
        
        cargarDatos()

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

    const cargarDatos = async() => {   

        try{

            //Traer el dato  dentro del metodo y no guardarlo en un hooks =>
            let id = await localStorage.getItem("idVisitaVista")

            console.log("IDVISITA => ", id)


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
            setValue("fecha",moment(`${resJson.fecha.year}-${resJson.fecha.month}-${resJson.fecha.day}`).format('DD-MM-YYYY'))
            setValue("nombreTecnico", resJson.nombreTecnico)
            setValue("apellidoTecnico", resJson.apellidoTecnico)
            setValue("nVisita", resJson.nVisita)
            setValue("estado", resJson.estado)
           

            alert("DATOS ENCONTRADOS CON EXITO.")

        }catch(error){

            console.log(error)

            alert("ERROR, NO FUE POSIBLE OBTENER LOS DATOS, VUELVA A INTENTARLO.")

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

            <Alert.Heading className="alertTitle">FORMULARIO VISUALIZACION DE DATOS VISITA</Alert.Heading>

            <br></br>

            <h5 className='red'></h5>

            </div>

            <br></br>
            <br></br>  

            <Form>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Nombre del Tecnico:  </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="nombreTecnico"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("nombreTecnico", { 

                                required:{
                                    value: true,
                                    message: 'Campo Obligatorio' 
                                },

                                validate:{


                                }

                            })}   

                        >
                        </textarea>

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
                    
                    <label>Apellido del Tecnico: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="apellidoTecnico"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("apellidoTecnico", { 

                                required:{
                                    value: true,
                                    message: 'Campo Obligatorio' 
                                },

                                validate:{


                                }

                            })}   

                        >
                        </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.apellidoTecnico && errors.apellidoTecnico.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Numero de Visita: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="nVisita"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("nVisita", { 

                                required:{
                                    value: true,
                                    message: 'Campo Obligatorio' 
                                },

                                validate:{


                                }

                            })}   

                        >
                        </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.nVisita && errors.nVisita.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Estado (Activo-Inactivo-Actualizado): </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="estado"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("estado", { 

                                required:{
                                    value: true,
                                    message: 'Campo Obligatorio' 
                                },

                                validate:{


                                }

                            })}   

                        >
                        </textarea>

                </Col>

                
                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.estado && errors.estado.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>
            <br></br>

            <Row className='body' fluid="true">   

                <Col fluid="true">
                
                    <Button fluid="true" type="button" href={`/formPrincipalVista?idGeneral=${idGeneral}&idVisita=${idVisita}`} variant="danger" size="lg">VOLVER</Button>
                
                </Col>


            </Row>

            <br></br>
            <br></br>

            <Row className='body' fluid="true">   

                <Col fluid="true">
                    
                   <h5 id="mensaje" className='mensaje'></h5>

                </Col>


            </Row>

            </Form>

            <br></br>

            </Alert>

            </Container>


        </Fragment>




    )



}

export default FormVisitaVista