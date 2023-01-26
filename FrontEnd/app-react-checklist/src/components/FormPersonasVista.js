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

const FormPersonasVista = (props) => {

    //Redireccionamiento de Pagina =>
    let navigate = useNavigate()

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

            const response = await axios("http://localhost:8080/Proyecto_CheckList/PersonaServlet",{

                method:"GET",
                params:{

                    action:"buscarIdVisita",
                    idVisita:id,

                }

            })

            const resJson = await response.data

            //Guardo el idPersona en localStorage =>
            localStorage.setItem("idPersonaVista", resJson.idPersona)

            console.log("DATOS API => ", resJson)

            //Pasar datos al form =>
            setValue("personasTotal",resJson.personasTotal)
            setValue("nGremios", resJson.nGremios)
            setValue("gremioEnfoque", resJson.gremioEnfoque)
            setValue("vestimentaOk", resJson.vestimentaOk)
            setValue("calzadoOk", resJson.calzadoOk)
            setValue("utilizanEpp", resJson.utilizanEpp)
            setValue("herramientasOk", resJson.herramientasOk)
            setValue("seguridadOk", resJson.seguridadOk)
            setValue("trabajoAltura", resJson.trabajoAltura)
            setValue("banosOk", resJson.banosOk)
            setValue("comerOk", resJson.comerOk)
            setValue("edadJoven", resJson.edadJoven)
            setValue("edadViejo", resJson.edadViejo)
            setValue("rangoMin", resJson.rangoMin)
            setValue("rangoMax", resJson.rangoMax)
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

            <Alert.Heading className="alertTitle">FORMULARIO VISUALIZACION DE DATOS PERSONAS</Alert.Heading>

            <br></br>

            <h5 className='red'></h5>

            </div>

            <br></br>
            <br></br>  

            <Form>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Numero de Personas en total Trabajando: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="personasTotal"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("personasTotal", { 

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
                    {errors.personasTotal && errors.personasTotal.message}
                    </span>

                
                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Numero de Gremios trabajando en simultaneo: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="nGremios"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("nGremios", { 

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
                    {errors.nGremios && errors.nGremios.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Gremio de Enfoque: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="gremioEnfoque"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("gremioEnfoque", { 

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
                    {errors.gremioEnfoque && errors.gremioEnfoque.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Vestimenta de trabajo adecuada: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="vestimentaOk"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("vestimentaOk", { 

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
                    {errors.vestimentaOk && errors.vestimentaOk.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Calzado Adecuado:  </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                        <textarea 
                            type="text"
                            name="calzadoOk"
                            disabled={true}
                            placeholder=""
                            className="form-control my-2"
                            {...register("calzadoOk", { 

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
                    {errors.calzadoOk && errors.calzadoOk.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Utilizan EPP:  </label>

                </Col>

                    
                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="utilizanEpp"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("utilizanEpp", { 

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
                    {errors.utilizanEpp && errors.utilizanEpp.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Usan las herramientas adecuadas:  </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="herramientasOk"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("herramientasOk", { 

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
                    {errors.herramientasOk && errors.herramientasOk.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Son cuidadosos de la seguirdad e higiene: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="seguridadOk"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("seguridadOk", { 

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
                    {errors.seguridadOk && errors.seguridadOk.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>En los trabajos de altura utilizan amarres: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="trabajoAltura"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("trabajoAltura", { 

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
                    {errors.trabajoAltura && errors.trabajoAltura.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Tienen baños adecuados: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="banosOk"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("banosOk", { 

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
                    {errors.banosOk && errors.banosOk.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Tienen lugar para comer adecuado: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="comerOk"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("comerOk", { 

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
                    {errors.comerOk && errors.comerOk.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Edad aparente del mas joven: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="edadJoven"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("edadJoven", { 

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
                    {errors.edadJoven && errors.edadJoven.message}
                    </span>


                </Col>

            </Row>

             <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Edad aparente del mas viejo: </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="edadViejo"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("edadViejo", { 

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
                    {errors.edadViejo && errors.edadViejo.message}
                    </span>


                </Col>

            </Row>

            <br></br>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Rango de edad (Edad Menor): </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="rangoMin"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("rangoMin", { 

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
                    {errors.rangoMin && errors.rangoMin.message}
                    </span>


                </Col>

            </Row>

            <br></br>


            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label>Rango de edad (Edad Mayor):  </label>

                </Col>

                <Col sm={7} fluid="true">
                    
                    <textarea 
                        type="text"
                        name="rangoMax"
                        disabled={true}
                        placeholder=""
                        className="form-control my-2"
                        {...register("rangoMax", { 

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
                    {errors.rangoMax && errors.rangoMax.message}
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

export default FormPersonasVista