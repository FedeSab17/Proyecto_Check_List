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
const FormSecoUpdate = (props) => {

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
        fechaAlta:'',
        fechaBaja:'',
        estado:'',
        idVisita:'',
  
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

        setSeco({

            ...seco,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = async (seco, event) => {

            
        actualizar(seco);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setSeco({

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
            fechaAlta:'',
            fechaBaja:'',
            estado:'',
            idVisita:'',

        });

        
        //Redireccionamos a la pagina principal de formUpdate =>
        navigate(`/formPrincipalUpdate?idGeneral=${idGeneral}&idVisita=${idVisita}`)

    
      
    }

    
    const cargarDatos = async() => {   

        try{

            //Obtengo el n° de obra del localStorage =>
            let id = localStorage.getItem("idVisitaUpdate")

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
            setValue("fechaInicio",moment(`${resJson.fechaInicial.year}-${resJson.fechaInicial.month}-${resJson.fechaInicial.day}`).format('YYYY-MM-DD'))
            setValue("fechaFinal",moment(`${resJson.fechaFinal.year}-${resJson.fechaFinal.month}-${resJson.fechaFinal.day}`).format('YYYY-MM-DD'))
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


            //Guardamos en LocalStorage el idMaterial =>
            localStorage.setItem("idSecoUpdate", resJson.idSeco)
            
            
        }catch(error){

            console.log(error)

            alert("ERROR, NO FUE POSIBLE OBTENER LOS DATOS, VUELVA A INTENTARLO.")

        }


    }


    const actualizar = async(seco) => {

        try{

            //Obtenermos el idGeneral del localStorage =>
            let idGen = localStorage.getItem("idGeneralUpdate")
            let idVis = localStorage.getItem("idVisitaUpdate")
            let idSec = localStorage.getItem("idSecoUpdate")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/SecoServlet`, {

                method:"GET",
                params:{

                    action:'actualizar',
                    fechaInicio:seco.fechaInicio,
                    fechaFinal:seco.fechaFinal,
                    mLineales:seco.mLineales,
                    mPerson:seco.mPerson,
                    m2Muro:seco.m2Muro,
                    muroPerson:seco.muroPerson,
                    m2Cubierta:seco.m2Cubierta,
                    cubiertaPerson:seco.cubiertaPerson,
                    metrosLineales:seco.metrosLineales,
                    linealesPerson:seco.linealesPerson,
                    diasCaidos:seco.diasCaidos,
                    motivo:seco.motivo,
                    materialVigas:seco.materialVigas,
                    materialMuros:seco.materialMuros,
                    materialCubiertas:seco.materialCubiertas,
                    comentario:seco.comentario,
                   
                   //Datos que no pueden ser modificados => 
                   idVisita:idVis,
                   idSeco:idSec,

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

            <Alert.Heading className="alertTitle">FORMULARIO DE ACTUALIZACION DE OBRA EN SECO</Alert.Heading>

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
                    
                    <label className="my-2">Metros lineales Vigas y Columnas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="mLineales"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 2,00 (Decimal)"
                        className="form-control my-2"
                        min="-2"
                        step="0.01"
                        {...register("mLineales", { 

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
                    {errors.mLineales && errors.mLineales.message}
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
                        name="mPerson"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio / Formato 1 (entero)"
                        className="form-control my-2"
                        min="-2"
                        {...register("mPerson", { 

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
                    {errors.mPerson && errors.mPerson.message}
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
                        placeholder="* Campo Obligatorio / Formato 2,00 (Decimal)"
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
                    
                    <label className="my-2">Metros lineales de Perfileria: </label>

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

                <Col sm={3}>
                    
                    <label className="my-2">Material utilizado en Vigas y Columnas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="materialVigas"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("materialVigas", { 

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
                    {errors.materialVigas && errors.materialVigas.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Material utilizado en Muros: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="materialMuros"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("materialMuros", { 

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
                    {errors.materialMuros && errors.materialMuros.message}
                    </span>

                   
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Material en Cubiertas: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="materialCubiertas"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("materialCubiertas", { 

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
                    {errors.materialCubiertas && errors.materialCubiertas.message}
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

                <Col>
                    
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

export default FormSecoUpdate