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
const FormPersonas = (props) => {

    
    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

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

        setPersona({

            ...persona,
            [event.target.name] : event.target.value

        })

    }

    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = (persona, event) => {

            
        insertar(persona);

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

        //Redirecciono =>
        navigate(`/formPrincipal`)
 
    }


    const insertar = async(persona) => {   

        try{

            let id = localStorage.getItem("idVisita")

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/PersonaServlet`,{

                method:"GET",
                params:{

                    action:"insertar",
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

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/PersonaServlet`,{

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

                document.querySelector("#mensaje").innerHTML = "YA FUE GESTIONADA LA CARGA DEL FORMULARIO PERSONAS PARA ESTA VISITA"

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

            <Alert.Heading className="alertTitle">FORMULARIO DE REGISTRO DE PERSONAS</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

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

                <Col sm={3}>
                    
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

                <Col sm={3}>
                    
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

export default FormPersonas