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


//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK / SE IMPLEMENTA RESPONSIVE =>
const FormGeneral = (props) => {

    //Redireccionamiento =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    const [general, setGeneral] = useState({

        codigo:'',
        nombreCliente:'',
        dni:'',
        domicilio:'',
        usoEdificio:'',
        alturaEdificio:'',
        m2Cubierta:'',
        m2Muro:'',
        alcance:'',
        duracionObra:'',
        comentario:'',
        fechaAlta:'',
        fechaBaja:'',
        estado:'',
        
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

        setGeneral({

            ...general,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = (general, event) => {

            
        insertar(general);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setGeneral({

            
            codigo:'',
            nombreCliente:'',
            dni:'',
            domicilio:'',
            usoEdificio:'',
            alturaEdificio:'',
            m2Cubierta:'',
            m2Muro:'',
            alcance:'',
            duracionObra:'',
            comentario:'',
            fechaAlta:'',
            fechaBaja:'',
            estado:'',

        });

        //Redirecciono =>
        navigate(`/formVisita`)

    
      
    }

    

    //Metodo para insertar los datos a la BD =>
    const insertar = async(general) => {

        try{

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GeneralServlet`, {

                method:"GET",
                params:{

                    action:"insertar",
                    codigo:general.codigo,
                    nombreCliente:general.nombreCliente,
                    dni:general.dni,
                    domicilio:general.domicilio,
                    usoEdificio:general.usoEdificio,
                    alturaEdificio:general.alturaEdificio,
                    m2Cubierta:general.m2Cubierta,
                    m2Muro:general.m2Muro,
                    alcance:general.alcance,
                    duracionObra:general.duracionObra,
                    comentario:general.comentario,

                    //Se autocompletan =>
                    fechaAlta:moment().format('YYYY-MM-DD'), 
                    fechaBaja:moment("1900-01-01").format('YYYY-MM-DD'), 
                    estado:"activo",

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Guardamos el codigo o n° de obra en el localStorage =>
            localStorage.setItem("codigo", general.codigo);

            alert("DATOS GUARDADOS CON EXITO.")


        }catch(error){

            console.log("Error =>", error)

            alert("ERROR, NO FUE POSIBLE GUARDAR LOS DATOS, VUELVA A INTENTARLO.")

        }

    }

    //Validar Codigo ingresado no exista en la BD entidad General =>
    const validarCodigo = async(codigo) => {

        try{

            const response = await fetch(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GeneralServlet?action=listar`, {

                method:"GET",

            })
            
            const resJson = await response.json()

            console.log("DATOS API => ", resJson)

            let validar = true

            for(let i = 0; i < resJson.length; i++){

                if(resJson[i].codigo === codigo){

                    validar = false;
                    break;

                }

            }

            //Si el dato nombre ingresado en form es igual a algun nombre de la BD retorna falsa y no permite la validacion =>
            return validar

        }catch(error){

            console.log("Error => ", error)

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

            <Alert.Heading className="alertTitle">FORMULARIO DE REGISTRO DE DATOS GENERALES</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            <br></br>

            <h5 className='red'>DNI-CUIT-CUIL sin . , - , /  (solo numeracion)</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">N° de Obra:</label>

                </Col>


                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="codigo"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("codigo", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                               validate1:validarCodigo,

                            }

                        })}   

                    >
                    </input>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.codigo && errors.codigo.message}
                    </span>

                    <span className="text-danger text-small d-block mb-2">
                    {
                        errors.codigo && errors.codigo.type === "validate1" && (
                            <div className="error">El n° de obra ingresado ya existe.</div>
                        )
                    }
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Nombre de Cliente/Empresa: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="nombreCliente"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("nombreCliente", { 

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
                    {errors.nombreCliente && errors.nombreCliente.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Dni/Cuil/Cuit: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="dni"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato sin . o -"
                        className="form-control my-2"
                        {...register("dni", { 

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
                    {errors.dni && errors.dni.message}
                    </span>

                  
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Domicilio: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="domicilio"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("domicilio", { 

                            required:{
                                value: true,
                                message: '*', 
                            },


                        })}   

                    >
                    </input>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.domicilio && errors.domicilio.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Uso destino del Edificio: </label>

                </Col>

                <Col sm={2} fluid="true">
                    
                    <select 

                        name="usoEdificio" 
                        onChange={handleInputChange}
                        {...register("usoEdificio", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Residencial">Residencial</option>
                        <option value="Nave Industrial">Nave Industrial</option>
                        <option value="Comercial">Comercial</option>
                        <option value="Institucional">Institucional</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.usoEdificio && errors.usoEdificio.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Altura del Edificio: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="alturaEdificio"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="0"
                        step="0.01"
                        {...register("alturaEdificio", { 

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
                    {errors.alturaEdificio && errors.alturaEdificio.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 de Cubierta: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="m2Cubierta"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato con 10,00"
                        className="form-control my-2"
                        min="0"
                        step="0.01"
                        {...register("m2Cubierta", { 

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
                    {errors.m2Cubierta && errors.m2Cubierta.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 de Muro: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="m2Muro"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato con 10,00"
                        className="form-control my-2"
                        min="0"
                        step="0.01"
                        {...register("m2Muro", { 

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
                    {errors.m2Muro && errors.m2Muro.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Alcance: </label>

                </Col>

                <Col sm={2} fluid="true">
                    
                    <select 

                        name="alcance" 
                        onChange={handleInputChange}
                        {...register("alcance", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Inicia desde Cero">Inicia desde Cero</option>
                        <option value="Estructura Existente">Estructura Existente</option>
                        <option value="Ampliacion">Ampliacion</option>
                        <option value="Refaccion">Refaccion</option>
                        <option value="Otro">Otro (Puede aclarar en comentarios)</option>

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.usoEdificio && errors.usoEdificio.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Expectativa Cliente duracion Obra: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="duracionObra"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato  1"
                        className="form-control my-2"
                        min="1"
                        {...register("duracionObra", { 

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
                    {errors.duracionObra && errors.duracionObra.message}
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
                    
                    <Button type="submit" fluid="true" variant="primary" size="lg">CARGAR</Button>&nbsp;&nbsp;
                    <Button type="button" fluid="true" href={`/prevCarga`} variant="danger" size="lg">VOLVER</Button>
                
                </Col>


            </Row>

            </Form>

            <br></br>

            </Alert>

            </Container>


        </Fragment>


    )

}

export default FormGeneral