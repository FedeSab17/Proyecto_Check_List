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

const FormConclusion = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    const [conclusion, setConclusion] = useState({

        obraTerminada:'',
        avanceActual:'',
        avanceEsperado:'',
        fechaFinalizacion:'',
        gradoSatisfaccion:'',
        comentario:'',
        fechaAlta:'',
        fechaBaja:'',
        estado:'',
        idGeneral:'',
        
    })

    useEffect(() => {

        fondo()
        
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

        setConclusion({

            ...conclusion,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = async (conclusion, event) => {

        let id = await idGeneral()    

        await insertar(conclusion, id);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setConclusion({

            obraTerminada:'',
            avanceActual:'',
            avanceEsperado:'',
            fechaFinalizacion:'',
            gradoSatisfaccion:'',
            comentario:'',
            fechaAlta:'',
            fechaBaja:'',
            estado:'',
            idGeneral:'',

        });

        //Redirecciono y paso los datos a traves de un search =>
        navigate(`/`)

    }

    //Metodo para solicitar el idGeneral x N° de obra =>
    const idGeneral = async() => {

        let idGeneral;

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

        return idGeneral


    }

    

    //Metodo para insertar los datos a la BD =>
    const insertar = async(conclusion, id) => {

    
        console.log("ID_GENERAL DESDE METODO INSERTAR  => ", id)

        try{


            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/ConclusionServlet`, {

                method:"GET",
                params:{

                    action:"insertar",
                    obraTerminada:conclusion.obraTerminada,
                    avanceActual:conclusion.avanceActual,
                    avanceEsperado:conclusion.avanceEsperado,
                    fechaFinalizacion:conclusion.fechaFinalizacion,
                    gradoSatisfaccion:conclusion.gradoSatisfaccion,
                    comentario:conclusion.comentario,
                    //Se autocompletan =>
                    fechaAlta:moment().format('YYYY-MM-DD'), 
                    fechaBaja:moment("1900-01-01").format('YYYY-MM-DD'), 
                    estado:"activo",
                    idGeneral:id,

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            alert("DATOS GUARDADOS CON EXITO.")


        }catch(error){

            console.log("Error =>", error)

            alert("ERROR, NO FUE POSIBLE GUARDAR LOS DATOS, VUELVA A INTENTARLO.")

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

            <Alert.Heading className="alertTitle">FORMULARIO DE REGISTRO CONCLUSION FINAL DE OBRA</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Obra Terminada: </label>

                </Col>

                <Col sm={2} fluid="true">
                    
                    <select 

                        name="obraTerminada" 
                        onChange={handleInputChange}
                        {...register("obraTerminada", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Si">Si</option>
                        <option value="No">No</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.obraTerminada && errors.obraTerminada.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Avance Actual (%): </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="avanceActual"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato con 10,00 %"
                        className="form-control my-2"
                        min="0"
                        max="100"
                        step="0.01"
                        {...register("avanceActual", { 

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
                    {errors.avanceActual && errors.avanceActual.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Avance Esperado (%): </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="avanceEsperado"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato con 10,00 %"
                        className="form-control my-2"
                        min="0"
                        max="100"
                        step="0.01"
                        {...register("avanceEsperado", { 

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
                    {errors.avanceEsperado && errors.avanceEsperado.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    <br></br>
                    <label>Fecha Finalizacion: </label>

                
                </Col>

                <Col sm={2} fluid="true">
                    <br></br>
                     <input 
                        type="date"
                        name="fechaFinalizacion"
                        onChange={handleInputChange}
                        placeholder="Ingrese la Fecha Nacimiento Formato 2020-11-05"
                        className="form-control"
                        {...register("fechaFinalizacion", { 

                            required:{
                                value: true,
                                message: '*' 
                            },

                        })}      
                    >
                    </input>
                
                
                </Col>

                <Col sm={1} fluid="true">

                        <br></br>
                        <span className="text-danger text-small d-block mb-2">
                        {errors.fechaFinalizacion && errors.fechaFinalizacion.message}
                        </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Grado Satisfaccion del Cliente (1 a 5): </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="gradoSatisfaccion"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        min={1}
                        max={5}
                        className="form-control my-2"
                        {...register("gradoSatisfaccion", { 

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
                    {errors.gradoSatisfaccion && errors.gradoSatisfaccion.message}
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

export default FormConclusion