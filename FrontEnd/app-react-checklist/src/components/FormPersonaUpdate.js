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
const FormPersonaUpdate = (props) => {

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

    const [persona, setPersona] = useState({

        personasTotal:'',
        nGremios:'',
        gremioEnfoque:'',
        vestimentaOk:'',
        calzadoOk:'',
        utilizanEpp:'',
        herramientasOk:'',
        seguridadOk:'',
        trabajoAltura:'',
        banosOk:'',
        comerOk:'',
        edadJoven:'',
        edadViejo:'',
        rangoMin:'',
        rangoMax:'',
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

        setPersona({

            ...persona,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = async (persona, event) => {

            
        actualizar(persona);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setPersona({

            personasTotal:'',
            nGremios:'',
            gremioEnfoque:'',
            vestimentaOk:'',
            calzadoOk:'',
            utilizanEpp:'',
            herramientasOk:'',
            seguridadOk:'',
            trabajoAltura:'',
            banosOk:'',
            comerOk:'',
            edadJoven:'',
            edadViejo:'',
            rangoMin:'',
            rangoMax:'',
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

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/PersonaServlet`,{

                method:"GET",
                params:{

                    action:"buscarIdVisita",
                    idVisita:id,

                }

            })

            const resJson = await response.data

            console.log("DATOS API => ", resJson)

            //Pasar datos al form =>
            setValue("personasTotal", resJson.personasTotal)
            setValue("nGremios", resJson.nGremios)
            setValue("gremioEnfoque", resJson.gremioEnfoque)
            setValue("vestimentaOk", resJson.vestimentaOk)
            setValue("calzadoOk", resJson.calzadoOk)
            setValue("utilizanEpp", resJson.utilizanEpp)
            setValue("herramientasOk",resJson.herramientasOk)
            setValue("seguridadOk", resJson.seguridadOk)
            setValue("trabajoAltura", resJson.trabajoAltura)
            setValue("banosOk", resJson.banosOk)
            setValue("comerOk",resJson.comerOk)
            setValue("edadJoven", resJson.edadJoven)
            setValue("edadViejo", resJson.edadViejo)
            setValue("rangoMin",resJson.rangoMin)
            setValue("rangoMax", resJson.rangoMax)
            setValue("comentario", resJson.comentario)


            //Guardamos en LocalStorage el idMaterial =>
            localStorage.setItem("idPersonaUpdate", resJson.idPersona)
            
            
        }catch(error){

            console.log(error)

            alert("ERROR, NO FUE POSIBLE OBTENER LOS DATOS, VUELVA A INTENTARLO.")

        }


    }


    const actualizar = async(persona) => {

        try{

            //Obtenermos el idGeneral del localStorage =>
            let idGen = localStorage.getItem("idGeneralUpdate")
            let idVis = localStorage.getItem("idVisitaUpdate")
            let idPer = localStorage.getItem("idPersonaUpdate")


            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/PersonaServlet`, {

                method:"GET",
                params:{

                   action:'actualizar',
                   personasTotal:persona.personasTotal,
                   nGremios:persona.nGremios,
                   gremioEnfoque:persona.gremioEnfoque,
                   vestimentaOk:persona.vestimentaOk,
                   calzadoOk:persona.calzadoOk,
                   utilizanEpp:persona.utilizanEpp,
                   herramientasOk:persona.herramientasOk,
                   seguridadOk:persona.seguridadOk,
                   trabajoAltura:persona.trabajoAltura,
                   banosOk:persona.banosOk,
                   comerOk:persona.comerOk,
                   edadJoven:persona.edadJoven,
                   edadViejo:persona.edadViejo,
                   rangoMin:persona.rangoMin,
                   rangoMax:persona.rangoMax, 
                   comentario:persona.comentario,
            

                   //Datos que no pueden ser modificados => 
                   idVisita:idVis,
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

            <Alert.Heading className="alertTitle">FORMULARIO DE ACTUALIZACION DE PERSONA</Alert.Heading>

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

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Numero de Personas en total Trabajando: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="personasTotal"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("personasTotal", { 

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
                    {errors.personasTotal && errors.personasTotal.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Numero de Gremios trabajando en simultaneo: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="nGremios"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("nGremios", { 

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
                    {errors.nGremios && errors.nGremios.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Gremio de Enfoque: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="text"
                        name="gremioEnfoque"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        {...register("gremioEnfoque", { 

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
                    {errors.gremioEnfoque && errors.gremioEnfoque.message}
                    </span>


                </Col>

            </Row>            

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Vestimenta de trabajo adecuada: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="vestimentaOk" 
                        onChange={handleInputChange}
                        {...register("vestimentaOk", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.vestimentaOk && errors.vestimentaOk.message}
                    </span>

                
                </Col>

            </Row>      

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Calzado Adecuado: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="calzadoOk" 
                        onChange={handleInputChange}
                        {...register("calzadoOk", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.calzadoOk && errors.calzadoOk.message}
                    </span>

                
                </Col>

            </Row>     

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Utilizan EPP: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="utilizanEpp" 
                        onChange={handleInputChange}
                        {...register("utilizanEpp", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.utilizanEpp && errors.utilizanEpp.message}
                    </span>

                
                </Col>

            </Row>        

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Usan las herramientas adecuadas: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="herramientasOk" 
                        onChange={handleInputChange}
                        {...register("herramientasOk", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.herramientasOk && errors.herramientasOk.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Son cuidadosos de la seguirdad e higiene: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="seguridadOk" 
                        onChange={handleInputChange}
                        {...register("seguridadOk", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.seguridadOk && errors.seguridadOk.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">En los trabajos de altura utilizan amarres: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="trabajoAltura" 
                        onChange={handleInputChange}
                        {...register("trabajoAltura", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.trabajoAltura && errors.trabajoAltura.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Tienen baños adecuados: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="banosOk" 
                        onChange={handleInputChange}
                        {...register("banosOk", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.banosOk && errors.banosOk.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Tienen lugar para comer adecuado: </label>

                </Col>

                <Col sm={3} fluid="true">
                    
                    <select 

                        name="comerOk" 
                        onChange={handleInputChange}
                        {...register("comerOk", { 

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
                        <option value="Otro">Otro (Aclarar en Comentario)</option>
                        

                    </select>

                </Col>

                <Col sm={1} fluid="true">

                        
                    <span className="text-danger text-small d-block mb-2">
                    {errors.comerOk && errors.comerOk.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Edad aparente del mas joven: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="edadJoven"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("edadJoven", { 

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
                    {errors.edadJoven && errors.edadJoven.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Edad aparente del mas viejo: </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="edadViejo"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("edadViejo", { 

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
                    {errors.edadViejo && errors.edadViejo.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true"> 

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Rango de edad (Edad Menor): </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="rangoMin"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("rangoMin", { 

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
                    {errors.rangoMin && errors.rangoMin.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Rango de edad (Edad Mayor): </label>

                </Col>

                <Col sm={6} fluid="true">
                    
                    <input 
                        type="number"
                        name="rangoMax"
                        onChange={handleInputChange}
                        placeholder="* Campo Obligatorio"
                        className="form-control my-2"
                        min="1"
                        {...register("rangoMax", { 

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
                    {errors.rangoMax && errors.rangoMax.message}
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

export default FormPersonaUpdate