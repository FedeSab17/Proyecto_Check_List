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


const PedidoObra = (props) => {


    //Redireccionamiento =>
    let navigate = useNavigate()

    //react-hook-form (validacion) =>
    const {register, formState: { errors }, handleSubmit} = useForm()

    const[dato,setDato] = useState(null)

    const [obra, setObra] = useState({

        codigo:'',

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

        setObra({

            ...obra,
            [event.target.name] : event.target.value

        })

    }


    //Metodo para gestionar el envio de datos al Servlet y BD =>
    const enviarDatos = async (obra, event) => {

        await incrementarVisita(obra.codigo)

        //Guardamos el codigo o n° de obra en el localStorage =>
        localStorage.setItem("codigo", obra.codigo);

        event.preventDefault();

        //Limpiar los campos del Form =>
        event.target.reset();

        //Vaciar todas las variables =>
        setObra({

            codigo:'',

        });

        //Redirecciono y paso los datos a traves de un search =>
        navigate(`/prevCargaConclusion`)

    
      
    }

     //Validar Codigo ingresado no exista en la BD entidad General =>
     const validarCodigo = async(codigo) => {

        try{

            const response = await fetch(`${process.env.REACT_APP_KEY}Proyecto_CheckList/GeneralServlet?action=listar`, {

                method:"GET",

            })
            
            const resJson = await response.json()

            console.log("DATOS API => ", resJson)

            let validar = false

            for(let i = 0; i < resJson.length; i++){

                if(resJson[i].codigo === codigo){

                    validar = true;
                    break;

                }

            }

            //Si el codigo o N° de obra ingresado existe en la BD retorna true, caso contrario retorna false y no valida el form =>
            return validar

        }catch(error){

            console.log("Error => ", error)

        }    

    }

    //Metodo para incrementar el n° de visita =>
    const incrementarVisita = async(codigo) => {

        try{

            const response = await axios(`${process.env.REACT_APP_KEY}Proyecto_CheckList/VisitaServlet`, {

                method:"GET",
                params:{

                    action:"visitaIncremental",
                    codigo:codigo,

                }

            })

            const resJson = await response.data

            console.log("DATO NVISITA INCREMENTO => ", resJson);

            //Guardamos el codigo o n° de obra en el localStorage =>
            await localStorage.setItem("nVisitaIncremental", JSON.stringify(resJson));


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

            <div className='body'>

            <Alert.Heading className="alertTitle">VALIDACION DEL N° DE OBRA</Alert.Heading>

            <br></br>

            <h5 className='red'>* Campos Obligatorios</h5>

            <br></br>

            <h5 className='red'>Usar el buscador en caso de no recordar el n° de obra asociado.</h5>

            </div>

            <br></br>
            <br></br>  

            <Form onSubmit={handleSubmit(enviarDatos)}>

            <Row fluid="true">

                <Col sm={3} fluid="true">
                    
                    <label className="my-2">Numero de Obra: </label>

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
                            <div className="error">El n° de obra ingresado no existe.</div>
                        )
                    }
                    </span>



                </Col>

                </Row>

                <br></br>

                <Row className='body' fluid="true">   

                <Col fluid="true">
                    
                    <Button fluid="true" type="submit" variant="primary" size="lg">VERIFICAR</Button>&nbsp;&nbsp;
                    <Button fluid="true" type="button" href={`/`} variant="danger" size="lg">VOLVER</Button>
                
                </Col>


            </Row>


            </Form>

            <br></br>

            </Alert>

            </Container>

        </Fragment>


    )


}

export default PedidoObra