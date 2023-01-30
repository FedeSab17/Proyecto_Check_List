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

const FormMaterialesVista = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

    //Validar formulario con Libreria useForm =>
    const {register, formState: { errors }, handleSubmit, setValue} = useForm({

    })

    const[dato,setDato] = useState(null)

    const[idVisita, setIdVisita] = useState(null)

    const[idGeneral, setIdGeneral] = useState(null)

    const [material, setMaterial] = useState({


        estadoAlmacen:'',
        movMateriales:'',
        almacenSeguro:'',
        envasesVacio:'',
        materialSobran:'',
        estadoLimpieza:'',
        desechosOrgani:'',
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


            //Guardamos el dato alojado en el localstorage en una variable =>
            let id = localStorage.getItem("idVisitaVista")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/MaterialServlet`,{

                method:"GET",
                params:{

                    action:"buscarIdVisita",
                    idVisita:id,

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Pasar datos al form =>
            setValue("estadoAlmacen",resJson.estadoAlmacen)
            setValue("movMateriales", resJson.movMateriales)
            setValue("almacenSeguro", resJson.almacenSeguro)
            setValue("envasesVacio", resJson.envasesVacio)
            setValue("materialSobran", resJson.materialSobran)
            setValue("estadoLimpieza", resJson.estadoLimpieza)
            setValue("desechosOrgani", resJson.desechosOrgani)
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

            <Alert.Heading className="alertTitle">FORMULARIO VISUALIZACION DE DATOS MATERIALES</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            </div>

            <br></br>
            <br></br>  

            <Form>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Estado y orden del almacenamiento o estiba: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="estadoAlmacen"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("estadoAlmacen", { 

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
                    {errors.estadoAlmacen && errors.estadoAlmacen.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Movimiento y traslado de materiales ordenado y sistematizado: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="movMateriales"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("movMateriales", { 

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
                    {errors.movMateriales && errors.movMateriales.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Almacenamiento seguro, bajo llave y encargado: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="almacenSeguro"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("almacenSeguro", { 

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
                    {errors.almacenSeguro && errors.almacenSeguro.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Envases vacios de consumibles ubicados en lugar seguro y ordenado: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="envasesVacio"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("envasesVacio", { 

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
                    {errors.envasesVacio && errors.envasesVacio.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Material Sobrante de recortes y/o escombros en lugar definido y seguro: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="materialSobran"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("materialSobran", { 

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
                    {errors.materialSobran && errors.materialSobran.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Estado de limpieza en lugares de transito de personas y equipos: </label>

                </Col>

                    
                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="estadoLimpieza"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("estadoLimpieza", { 

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
                    {errors.estadoLimpieza && errors.estadoLimpieza.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Desechos organicos (Restos de comida, etc) removidos con frecuencia: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="desechosOrgani"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("desechosOrgani", { 

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
                    {errors.desechosOrgani && errors.desechosOrgani.message}
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

export default FormMaterialesVista