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
const FormGremioUpdate = (props) => {

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

    //Obtener el parametro que pasamos por el URL =>
    const[urlGremio,setUrlGremio] = useState(query.get("idGremio"))

    const [gremio, setGremio] = useState({

        nombreGremio:'',
        nroPersonas:'',
        horarioDesde:'',
        horarioHasta:'',
        fechaDesde:'',
        fechaHasta:'',
        nroArgentinos:'',
        nombreContratista:'',
        apellidoContratista:'',
        fechaAlta:'',
        fechaBaja:'',
        estado:'',
        idPersona:'',
                
    })

    useEffect(() => {

        fondo()

        //Obtenemos los datos del localStorage =>
        setIdGeneral(localStorage.getItem("idGeneralUpdate"))
        setIdVisita(localStorage.getItem("idVisitaUpdate"))

        setUrlGremio(query.get("idGremio"))

        cargarDatos()
        
    },[query.get("idGremio")])


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

        setGremio({

            ...gremio,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = async (gremio, event) => {

            
        actualizar(gremio);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setGremio({

            nombreGremio:'',
            nroPersonas:'',
            horarioDesde:'',
            horarioHasta:'',
            fechaDesde:'',
            fechaHasta:'',
            nroArgentinos:'',
            nombreContratista:'',
            apellidoContratista:'',
            fechaAlta:'',
            fechaBaja:'',
            estado:'',
            idPersona:'',
           
        });

        
        //Redireccionamos a la pagina principal de formUpdate =>
        navigate(`/formPrincipalUpdate?idGeneral=${idGeneral}&idVisita=${idVisita}`)

    
      
    }

    
    const cargarDatos = async() => {   

        try{

            //Obtengo el n° de obra del localStorage =>
            let id = query.get("idGremio")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GremioServlet`,{

                method:"GET",
                params:{

                    action:"buscar",
                    idGremio:id,

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Pasar datos al form =>
            setValue("nombreGremio", resJson.nombreGremio)
            setValue("nroPersonas", resJson.nroPersonas)
            setValue("horarioDesde", moment(resJson.horarioDesde).add(24, 'hours').format('HH:mm'))
            setValue("horarioHasta", moment(resJson.horarioHasta).add(24, 'hours').format('HH:mm'))
            setValue("fechaDesde", moment(`${resJson.fechaDesde.year}-${resJson.fechaDesde.month}-${resJson.fechaDesde.day}`).format('YYYY-MM-DD'))
            setValue("fechaHasta", moment(`${resJson.fechaHasta.year}-${resJson.fechaHasta.month}-${resJson.fechaHasta.day}`).format('YYYY-MM-DD'))
            setValue("nroArgentinos", resJson.nroArgentinos)
            setValue("nombreContratista", resJson.nombreContratista)
            setValue("apellidoContratista", resJson.apellidoContratista)
            setValue("comentario", resJson.comentario)
            


            //Guardamos en LocalStorage el idMaterial =>
            localStorage.setItem("idGremioUpdate", resJson.idGremio)
            
            
        }catch(error){

            console.log(error)

            alert("ERROR, NO FUE POSIBLE OBTENER LOS DATOS, VUELVA A INTENTARLO.")

        }


    }


    const actualizar = async(gremio) => {

        try{

            //Obtenermos el idGeneral del localStorage =>
            let idGen = localStorage.getItem("idGeneralUpdate")
            let idVis = localStorage.getItem("idVisitaUpdate")
            let idPer = localStorage.getItem("idPersonaUpdate")
            let idGrem = localStorage.getItem("idGremioUpdate")


            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GremioServlet`, {

                method:"GET",
                params:{

                   action:'actualizar',
                   nombreGremio:gremio.nombreGremio,
                   nroPersonas:gremio.nroPersonas,
                   horarioDesde:gremio.horarioDesde,
                   horarioHasta:gremio.horarioHasta,
                   fechaDesde:gremio.fechaDesde,
                   fechaHasta:gremio.fechaHasta,
                   nroArgentinos:gremio.nroArgentinos,
                   nombreContratista:gremio.nombreContratista,
                   apellidoContratista:gremio.apellidoContratista,
                   comentario:gremio.comentario,
                   
        
                   //Datos que no pueden ser modificados => 
                   idGremio:idGrem,
                   idPersona:idPer,

                   //Se autocompletan =>
                   fechaAlta:moment().format('YYYY-MM-DD'), 
                   fechaBaja:moment("1900-01-01").format('YYYY-MM-DD'), 
                   estado:"actualizado",


                }


            })

            const resJson = await response.data

            console.log("DATOS API ACTUALIZAR => ", resJson)

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

            <Alert.Heading className="alertTitle">FORMULARIO DE ACTUALIZACION DE GREMIO</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            <br></br>

            <h5 className='red'>Se debe aclarar en campo comentario: Dia de la semana de trabajo y cantidad de Horas Ej: Lunes 4hs, Martes 5hs, etc</h5>

            <br></br>

            <h5 className='red'>Se pueden agregar la cantidad de Gremios necesarios asociados a la visita (Completar nuevamente el Formulario)</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Nombre del Gremio: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="nombreGremio"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("nombreGremio", { 

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
                    {errors.nombreGremio && errors.nombreGremio.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Numero de Personas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="nroPersonas"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("nroPersonas", { 

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
                    {errors.nroPersonas && errors.nroPersonas.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Horario Desde: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="time"
                        name="horarioDesde"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("horarioDesde", { 

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
                    {errors.horarioDesde && errors.horarioDesde.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Horario Hasta: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="time"
                        name="horarioHasta"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("horarioHasta", { 

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
                    {errors.horarioHasta && errors.horarioHasta.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha Desde: </label>

                
                </Col>

                <Col sm={2} fluid="true">
                    
                    <input 
                        type="date"
                        name="fechaDesde"
                        onChange={handleInputChange}
                        placeholder=""
                        className="form-control"
                        {...register("fechaDesde", { 

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
                    {errors.fechaDesde && errors.fechaDesde.message}
                    </span>

                </Col>


            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha Hasta: </label>

                
                </Col>

                <Col sm={2} fluid="true">
                    
                    <input 
                        type="date"
                        name="fechaHasta"
                        onChange={handleInputChange}
                        placeholder=""
                        className="form-control"
                        {...register("fechaHasta", { 

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
                    {errors.fechaHasta && errors.fechaHasta.message}
                    </span>

                </Col>


            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Numero de Argentinos: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="nroArgentinos"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("nroArgentinos", { 

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
                    {errors.nroArgentinos && errors.nroArgentinos.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Nombre del Contratista: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="nombreContratista"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("nombreContratista", { 

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
                    {errors.nombreContratista && errors.nombreContratista.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Apellido del Contratista: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="apellidoContratista"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("apellidoContratista", { 

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
                    {errors.apellidoContratista && errors.apellidoContratista.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Comentario: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="comentario"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Hasta 3000 caracteres"
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

export default FormGremioUpdate