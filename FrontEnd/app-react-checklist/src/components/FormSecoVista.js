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

const FormSecoVista = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

    //Validar formulario con Libreria useForm =>
    const {register, formState: { errors }, handleSubmit, setValue} = useForm({

    })

    const[dato,setDato] = useState(null)

    const[idVisita, setIdVisita] = useState(null)

    const[idGeneral, setIdGeneral] = useState(null)

    const [seco, setSeco] = useState({

        fechaInicio:'',
        fechaFinal:'',
        mLineales:'',
        mPerson:'',
        m2Muro:'',
        muroPerson:'',
        m2Cubierta:'',
        cubiertaPerson:'',
        metrosLineales:'',
        linealesPerson:'',
        diasCaidos:'',
        motivo:'',
        materialVigas:'',
        materialMuros:'',
        materialCubiertas:'',
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

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/SecoServlet`,{

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
            setValue("mLineales", resJson.mLineales)
            setValue("mPerson", resJson.mPerson)
            setValue("m2Muro", resJson.m2Muro)
            setValue("muroPerson", resJson.muroPerson)
            setValue("m2Cubierta", resJson.m2Cubierta)
            setValue("cubiertaPerson", resJson.cubiertaPerson)
            setValue("metrosLineales", resJson.metrosLineales)
            setValue("linealesPerson", resJson.linealesPerson)
            setValue("diasCaidos", resJson.diasCaidos)
            setValue("motivo", resJson.motivo)
            setValue("materialVigas", resJson.materialVigas)
            setValue("materialMuros", resJson.materialMuros)
            setValue("materialCubiertas", resJson.materialCubiertas)
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

            <Alert.Heading className="alertTitle">FORMULARIO VISUALIZACION DE DATOS OBRA SECO</Alert.Heading>

            <br></br>

            <h5 className='red'>La cantidad de M2 o metros lineales serán las totales (no la diferencia entre una visita y la anterior).</h5>

            <br></br>

            <h5 className='red'>Si algun item no esta dentro del alcance, colocar valor -1 (No Aplicable).</h5>

            <br></br>

            <h5 className='red'>Si al momento de la primer visita hay algun item terminado, colocar -2 (Ya Construido).</h5>

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
                    
                    <label className="my-2">Metros lineales Vigas y Columnas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="mLineales"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("mLineales", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.mLineales && errors.mLineales.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Vigas N° de Personas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="mPerson"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("mPerson", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.mPerson && errors.mPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 Muro: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea
                        type="text"
                        name="m2Muro"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("m2Muro", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.m2Muro && errors.m2Muro.message}
                    </span>

                   
                </Col>

            </Row>
            
            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Muro N° de Personas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="muroPerson"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("muroPerson", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.muroPerson && errors.muroPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 Cubierta: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="m2Cubierta"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("m2Cubierta", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.m2Cubierta && errors.m2Cubierta.message}
                    </span>

                   
                </Col>

            </Row>
            
            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Cubierta N° de Personas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="cubiertaPerson"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("cubiertaPerson", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.cubiertaPerson && errors.cubiertaPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Metros lineales de terminaciones: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="metrosLineales"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("metrosLineales", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.metrosLineales && errors.linealesPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Lineales N° de Personas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="linealesPerson"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("linealesPerson", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.linealesPerson && errors.linealesPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Nro de dias Caidos: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="diasCaidos"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("diasCaidos", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.diasCaidos && errors.diasCaidos.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Motivo: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="motivo"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("motivo", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.motivo && errors.motivo.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Material utilizado en Vigas y Columnas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="materialVigas"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("materialVigas", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.materialVigas && errors.materialVigas.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Material utilizado en Muros: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="materialMuros"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("materialMuros", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.materialMuros && errors.materialMuros.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Material en Cubiertas: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="materialCubiertas"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("materialCubiertas", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.materialCubiertas && errors.materialCubiertas.message}
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

export default FormSecoVista
