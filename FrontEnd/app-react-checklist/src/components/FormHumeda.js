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

//ACTUALIZADO AL 22-9-22 (V2) FUNCIONA OK =>
const FormHumeda = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    const [humeda, setHumeda] = useState({

        fechaInicio:'',
        fechaFinal:'',
        m2Piso:'',
        pisoPerson:'',
        metros:'',
        metrosPerson:'',
        m2Muro:'',
        muroPerson:'',
        m2Cubierta:'',
        cubiertaPerson:'',
        metrosLineales:'',
        linealesPerson:'',
        diasCaidos:'',
        motivo:'',
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

        setHumeda({

            ...humeda,
            [event.target.name] : event.target.value

        })

    }

    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = (humeda, event) => {

            
        insertar(humeda);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setHumeda({

            fechaInicio:'',
            fechaFinal:'',
            m2Piso:'',
            pisoPerson:'',
            metros:'',
            metrosPerson:'',
            m2Muro:'',
            muroPerson:'',
            m2Cubierta:'',
            cubiertaPerson:'',
            metrosLineales:'',
            linealesPerson:'',
            diasCaidos:'',
            motivo:'',
            comentario:'',
            fechaAlta:'',
            fechaBaja:'',
            estado:'',
            idVisita:'',

        });

        //Redirecciono =>
        navigate(`/formPrincipal`)
 
    }

    const insertar = async(humeda) => {   

        try{

            let id = localStorage.getItem("idVisita")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/HumedaServlet`,{

                method:"GET",
                params:{

                    action:"insertar",
                    fechaInicio:humeda.fechaInicio,
                    fechaFinal:humeda.fechaFinal,
                    m2Piso:humeda.m2Piso,
                    pisoPerson:humeda.pisoPerson,
                    metros:humeda.metros,
                    metrosPerson:humeda.metrosPerson,
                    m2Muro:humeda.m2Muro,
                    muroPerson:humeda.muroPerson,
                    m2Cubierta:humeda.m2Cubierta,
                    cubiertaPerson:humeda.cubiertaPerson,
                    metrosLineales:humeda.metrosLineales,
                    linealesPerson:humeda.linealesPerson,
                    diasCaidos:humeda.diasCaidos,
                    motivo:humeda.motivo,
                    comentario:humeda.comentario,

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

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/HumedaServlet`,{

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

                document.querySelector("#mensaje").innerHTML = "YA FUE GESTIONADA LA CARGA DEL FORMULARIO OBRA HUMEDA PARA ESTA VISITA"

                return validar

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

            <Alert.Heading className="alertTitle">FORMULARIO DE REGISTRO DE OBRA HUMEDA</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            <br></br>

            <h5 className='red'>La cantidad de M2 o metros lineales serán las totales (no la diferencia entre una visita y la anterior).</h5>

            <br></br>

            <h5 className='red'>Si algun item no esta dentro del alcance, colocar valor -1 (No Aplicable).</h5>

            <br></br>

            <h5 className='red'>Si al momento de la primer visita hay algun item terminado, colocar -2 (Ya Construido).</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha de inicio Actividades: </label>

                
                </Col>

                <Col sm={2} fluid="true">
                    
                    <input 
                        type="date"
                        name="fechaInicio"
                        onChange={handleInputChange}
                        placeholder=""
                        className="form-control"
                        {...register("fechaInicio", { 

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
                    {errors.fechaInicio && errors.fechaInicio.message}
                    </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">


                <Col sm={3} fluid="true">
                    
                    <label>Fecha final de Actividades: </label>

                
                </Col>

                <Col sm={2} fluid="true">
                    
                    <input 
                        type="date"
                        name="fechaFinal"
                        onChange={handleInputChange}
                        placeholder=""
                        className="form-control"
                        {...register("fechaFinal", { 

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
                    {errors.fechaFinal && errors.fechaFinal.message}
                    </span>

                </Col>



            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 de Piso: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="m2Piso"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 2,00 (Decimal)"
                        className="form-control my-2"
                        min="-2"
                        step="0.01"
                        {...register("m2Piso", { 

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
                    {errors.m2Piso && errors.m2Piso.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Piso N° de Personas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="pisoPerson"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        min="-2"
                        className="form-control my-2"
                        {...register("pisoPerson", { 

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
                    {errors.pisoPerson && errors.pisoPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Metros lineales Vigas y Columnas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="metros"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 2,00 (Decimal)"
                        className="form-control my-2"
                        min="-2"
                        step="0.01"
                        {...register("metros", { 

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
                    {errors.metros && errors.metros.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

             <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Vigas N° de Personas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="metrosPerson"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        className="form-control my-2"
                        min="-2"
                        {...register("metrosPerson", { 

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
                    {errors.metrosPerson && errors.metrosPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 Muro: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="m2Muro"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 2.00 (Decimal)"
                        className="form-control my-2"
                        min="-2"
                        step="0.01"
                        {...register("m2Muro", { 

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
                    {errors.m2Muro && errors.m2Muro.message}
                    </span>

                   
                </Col>

            </Row>
            
            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Muro N° de Personas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="muroPerson"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        className="form-control my-2"
                        min="-2"
                        {...register("muroPerson", { 

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
                    {errors.muroPerson && errors.muroPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">M2 Cubierta: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="m2Cubierta"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 2,00 (Decimal)"
                        className="form-control my-2"
                        min="-2"
                        step="0.01"
                        {...register("m2Cubierta", { 

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
                    {errors.m2Cubierta && errors.m2Cubierta.message}
                    </span>

                   
                </Col>

            </Row>
            
            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Cubierta N° de Personas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="cubiertaPerson"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        className="form-control my-2"
                        min="-2"
                        {...register("cubiertaPerson", { 

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
                    {errors.cubiertaPerson && errors.cubiertaPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Metros lineales de terminaciones: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="metrosLineales"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 2,00 (Decimal)"
                        className="form-control my-2"
                        min="-2"
                        step="0.01"
                        {...register("metrosLineales", { 

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
                    {errors.metrosLineales && errors.linealesPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Lineales N° de Personas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="linealesPerson"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        className="form-control my-2"
                        min="-2"
                        {...register("linealesPerson", { 

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
                    {errors.linealesPerson && errors.linealesPerson.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Nro de dias Caidos (entre la visita actual y anterior): </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="diasCaidos"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        className="form-control my-2"
                        min="-2"
                        {...register("diasCaidos", { 

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
                    {errors.diasCaidos && errors.diasCaidos.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Motivo: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="motivo" 
                        onChange={handleInputChange}
                        {...register("motivo", { 

                            required:{
                                value: true,
                                message: '*', 
                            },

                            validate:{

                            }

                        })}   
                        
                    >

                        <option value="">Seleccione una Opcion</option>
                        <option value="Mal Tiempo">Mal Tiempo</option>
                        <option value="Otro">Otro (aclarar en comentario)</option>
                        

                    </select>

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

export default FormHumeda
