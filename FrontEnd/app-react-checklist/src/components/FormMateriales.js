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
const FormMateriales = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    const [material, setMaterial] = useState({


        estadoAlmacen:'',
        movMateriales:'',
        almacenSeguro:'',
        envasesVacio:'',
        materialSobran:'',
        estadoLimpieza:'',
        desechosOrgani:'',
        comentario:'',
        fechaAlta:'',
        fechaBaja:'',
        estado:'',
        idVisita:'',

        
    })


    useEffect(() => {

        fondo()
        validarCargaForm()

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

        setMaterial({

            ...material,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = (material, event) => {

            
        insertar(material);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setMaterial({

            estadoAlmacen:'',
            movMateriales:'',
            almacenSeguro:'',
            envasesVacio:'',
            materialSobran:'',
            estadoLimpieza:'',
            desechosOrgani:'',
            comentario:'',
            fechaAlta:'',
            fechaBaja:'',
            estado:'',
            idVisita:'',

        });

        //Redirecciono =>
        navigate(`/formPrincipal`)
 
    }

    const insertar = async(material) => {   

        try{

            let id = localStorage.getItem("idVisita")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/MaterialServlet`,{

                method:"GET",
                params:{

                    action:"insertar",
                    estadoAlmacen:material.estadoAlmacen,
                    movMateriales:material.movMateriales,
                    almacenSeguro:material.almacenSeguro,
                    envasesVacio:material.envasesVacio,
                    materialSobran:material.materialSobran,
                    estadoLimpieza:material.estadoLimpieza,
                    desechosOrgani:material.desechosOrgani,
                    comentario:material.comentario,

                    //Se autocompletan =>
                    fechaAlta:moment().format('YYYY-MM-DD'),
                    fechaBaja:moment('1900-01-01').format('YYYY-MM-DD'),
                    estado:'activo',
                    idVisita:id,


                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            alert("DATOS GUARDADOS CON EXITO.")

        }catch(error){

            console.log(error)

            alert("ERROR, NO FUE POSIBLE GUARDAR LOS DATOS, VUELVA A INTENTARLO.")

        }


    }

    //Metodo para validar si el idVisita en la entidad Material existe =>
    const validarCargaForm = async() => {

        try{

            let id = localStorage.getItem("idVisita")

            let validar = true

            console.log("ID_VISITA => ", id)

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/MaterialServlet`,{

                method:"GET",
                params:{

                    action:"listar",

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)


            for(let i = 0; i < resJson.length; i++){

                if((resJson[i].idVisita).toString() === (id).toString()){

                    validar = false
                    break

                }

            }

            if(validar === false){

                console.log("VALIDAR => ", validar)

                document.querySelector("#mensaje").innerHTML = "YA FUE GESTIONADA LA CARGA DEL FORMULARIO MATERIALES PARA ESTA VISITA"

                return validar;

            }else{

                return validar

            }

            

        }catch(error){

            console.log(error)

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

            <Alert.Heading className="alertTitle">FORMULARIO DE REGISTRO DE MATERIALES</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>
            
            <br></br>

            <h5 className='red'>En caso se seleccionar opcion "Otro" se debe especificar en comentario.</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <br></br>

            <Row fluid="true">

                <Col sm={6} fluid="true">
                    
                    <label>Estado y orden del almacenamiento o estiba: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="estadoAlmacen" 
                        onChange={handleInputChange}
                        {...register("estadoAlmacen", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

                </Col>

                
                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.estadoAlmacen && errors.estadoAlmacen.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true" >

                <Col sm={6} fluid="true">
                    
                    <label>Movimiento y traslado de materiales ordenado y sistematizado: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="movMateriales" 
                        onChange={handleInputChange}
                        {...register("movMateriales", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.movMateriales && errors.movMateriales.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={6} fluid="true">
                    
                    <label>Almacenamiento seguro, bajo llave y encargado: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="almacenSeguro" 
                        onChange={handleInputChange}
                        {...register("almacenSeguro", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.almacenSeguro && errors.almacenSeguro.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={6} fluid="true">
                    
                    <label>Envases vacios de consumibles ubicados en lugar seguro y ordenado: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="envasesVacio" 
                        onChange={handleInputChange}
                        {...register("envasesVacio", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.envasesVacio && errors.envasesVacio.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={6} fluid="true">
                    
                    <label>Material Sobrante de recortes y/o escombros en lugar definido y seguro: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="materialSobran" 
                        onChange={handleInputChange}
                        {...register("materialSobran", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.materialSobran && errors.materialSobran.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={6} fluid="true">
                    
                    <label>Estado de limpieza en lugares de transito de personas y equipos: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="estadoLimpieza" 
                        onChange={handleInputChange}
                        {...register("estadoLimpieza", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.estadoLimpieza && errors.estadoLimpieza.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={6} fluid="true">
                    
                    <label>Desechos organicos (Restos de comida, etc) removidos con frecuencia: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="desechosOrgani" 
                        onChange={handleInputChange}
                        {...register("desechosOrgani", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Malo">Malo</option>
                        <option value="Regular">Regular</option>
                        <option value="Bueno">Bueno</option>
                        <option value="Muy Bueno">Muy Bueno</option>
                        <option value="Excelente">Excelente</option>
                        <option value="Otro">Otro (Aclarar en Comentario)</option>

                    </select>

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

                                validate1:validarCargaForm,

                            }

                        })}   

                    >
                    </textarea>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.comentario && errors.comentario.message}
                    </span>

                    <span className="text-danger text-small d-block mb-2">
                    {
                        errors.comentario && errors.comentario.type === "validate1" && (
                            <div className="error">*</div>
                        )
                    }
                    </span>


                </Col>

            </Row>

            <br></br>
            <br></br>

            <Row className='body' fluid="true">   

                <Col fluid="true">
                    
                    <Button fluid="true" type="submit" variant="primary" size="lg">CARGAR</Button>&nbsp;&nbsp;
                    <Button fluid="true" type="button" href={`/formPrincipal`} variant="danger" size="lg">VOLVER</Button>
                
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

export default FormMateriales