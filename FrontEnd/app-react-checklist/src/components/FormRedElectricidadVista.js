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
import { useLocation } from "react-router-dom";

const FormRedElectricidadVista = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

    //Validar formulario con Libreria useForm =>
    const {register, formState: { errors }, handleSubmit, setValue} = useForm({

    })

    const[dato,setDato] = useState(null)

    const[idVisita, setIdVisita] = useState(null)

    const[idGeneral, setIdGeneral] = useState(null)

    const [red, setRed] = useState({

        fechaInicio:'',
        fechaFinal:'',
        metrosLineales:'',
        nroPersonas:'',
        comentario:'',
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

            let id = localStorage.getItem("idVisitaVista")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/RedElectricidadServlet`,{

                method:"GET",
                params:{

                    action:"buscarIdVisita",
                    idVisita:id,

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Pasar datos al form =>
            setValue("fechaInicio",moment(`${resJson.fechaInicial.year}-${resJson.fechaInicial.month}-${resJson.fechaInicial.day}`).format('DD-MM-YYYY'))
            setValue("fechaFinal",moment(`${resJson.fechaFinal.year}-${resJson.fechaFinal.month}-${resJson.fechaFinal.day}`).format('DD-MM-YYYY'))
            setValue("metrosLineales", resJson.metrosLineales)
            setValue("nroPersonas", resJson.nroPersonas)
            setValue("comentario", resJson.comentario)
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

            <Alert.Heading className="alertTitle">FORMULARIO VISUALIZACION DE DATOS RED DE ELECTRICIDAD</Alert.Heading>

            <br></br>

            <h5 className='red'></h5>

            <br></br>

            </div>

            <br></br>
            <br></br>  

            <Form>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha de inicio Actividades: </label>

                
                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="fechaInicio"
                        disabled={true}
                        placeholder=""
                        className="form-control"
                        {...register("fechaInicio", { 

                            required:{
                                value: true,
                                message: '*' 
                            },

                        })}      
                    >
                    </textarea>
                
                
                </Col>

                <Col sm={1} fluid="true">

                      
                    <span className="text-danger text-small d-block mb-2">
                    {errors.fechaInicio && errors.fechaInicio.message}
                    </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha final de Actividades: </label>

                
                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="fechaFinal"
                        disabled={true}
                        placeholder=""
                        className="form-control"
                        {...register("fechaFinal", { 

                            required:{
                                value: true,
                                message: '*' 
                            },

                        })}      
                    >
                    </textarea>
                
                
                </Col>

                <Col sm={1} fluid="true">

                    
                    <span className="text-danger text-small d-block mb-2">
                    {errors.fechaFinal && errors.fechaFinal.message}
                    </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Metros Lineales: </label>

                
                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="metrosLineales"
                        disabled={true}
                        placeholder=""
                        className="form-control"
                        {...register("metrosLineales", { 

                            required:{
                                value: true,
                                message: '*' 
                            },

                        })}      
                    >
                    </textarea>
                
                
                </Col>

                <Col sm={1} fluid="true">

                      
                    <span className="text-danger text-small d-block mb-2">
                    {errors.metrosLineales && errors.metrosLineales.message}
                    </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Nro de Personas: </label>

                
                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="nroPersonas"
                        disabled={true}
                        placeholder=""
                        className="form-control"
                        {...register("nroPersonas", { 

                            required:{
                                value: true,
                                message: '*' 
                            },

                        })}      
                    >
                    </textarea>
                
                
                </Col>

                <Col sm={1} fluid="true">

                    
                    <span className="text-danger text-small d-block mb-2">
                    {errors.nroPersonas && errors.nroPersonas.message}
                    </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Comentario: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="comentario"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("comentario", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                                

                            }

                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.comentario && errors.comentario.message}
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
                
                    <Button fluid="true"type="button" href={`/formPrincipalVista?idGeneral=${idGeneral}&idVisita=${idVisita}`} variant="danger" size="lg">VOLVER</Button>
                
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

export default FormRedElectricidadVista
