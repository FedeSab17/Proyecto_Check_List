create database proyecto_check_list;

use proyecto_check_list;

SET SQL_SAFE_UPDATES = 0;

alter table general auto_increment = 1;

#---------------------------------------

#ENTIDAD GENERAL 13° BORRAR =>

SELECT * FROM general;

SELECT * FROM general WHERE idGeneral = 1;

DELETE FROM general WHERE idGeneral = 1;

UPDATE general set codigo = "0001" where idGeneral = 1;

DELETE FROM general;

alter table general auto_increment = 1;

#Query para obtener el ultimo n° de idGeneral  =>
SELECT MAX(idGeneral) FROM general;

#Query para obtener el idGeneral por N° de Obra  =>
Select idGeneral from general where codigo = "PO577";

#Query para obtener idGeneral + idVisita por el n° de Obra =>
select g.idGeneral, v.idVisita from general as g inner join visita as v on g.idGeneral = v.idGeneral where g.codigo = "0001";

#---------------------------------------

#ENTIDAD VISITA 12° BORRAR =>

SELECT * FROM visita;

SELECT * FROM visita WHERE idVisita = 1;

DELETE FROM visita WHERE idVisita = 1;

DELETE FROM visita;

alter table visita auto_increment = 1;

#Query para obtener por N° de Obra/codigo el mayor numero de Visita =>
Select max(v.nVisita) as UltimaVisita from general as g inner join visita as v on g.idGeneral = v.idGeneral where g.codigo = "PO7892";

#Query para obtener el ultimo idVisita asociado al n° de obra =>
SELECT MAX(v.idVisita) FROM visita as v inner join general as g on v.idGeneral = g.idGeneral where g.codigo = "PO621"; 

#Query para obtener all visitas por n° idGeneral =>
Select * from visita where idGeneral = 2;

#Query para obtener all visitas por n° de obra =>
Select v.fecha, v.nombreTecnico, v.apellidoTecnico, v.nVisita, g.nombreCliente, g.apellidoCliente, g.dni, g.domicilio, v.idVisita, v.idGeneral, g.codigo from visita as v inner join general as g on v.idGeneral = g.idGeneral where g.codigo = "PO7893";

#---------------------------------------

#ENTIDAD MATERIAL 2° BORRAR =>

SELECT * FROM material;

SELECT * FROM material WHERE idMaterial = 1;

DELETE FROM material WHERE idMaterial = 1;

DELETE FROM material;

alter table material auto_increment = 1;

#---------------------------------------

#ENTIDAD PERSONA 4° BORRAR =>

SELECT * FROM persona;

SELECT * FROM persona WHERE idPersona = 1;

DELETE FROM persona WHERE idPersona = 1;

DELETE FROM persona;

alter table persona auto_increment = 1;

#Query para obtener el ultimo idPersona asociado al n° de obra =>
SELECT MAX(p.idPersona) FROM persona as p inner join visita as v on p.idVisita = v.idVisita inner join general as g on v.idGeneral = g.idGeneral where g.codigo = "PO621"; 

#---------------------------------------

#ENTIDAD GREMIO 3° BORRAR =>

SELECT * FROM gremio;

SELECT * FROM gremio WHERE idGremio = 1;

DELETE FROM gremio WHERE idGremio = 1;

DELETE FROM gremio;

alter table gremio auto_increment = 1;

#---------------------------------------

#ENTIDAD HUMEDA 5° BORRAR =>

SELECT * FROM humeda;

SELECT * FROM humeda WHERE idHumeda = 1;

DELETE FROM humeda WHERE idHumeda = 1;

DELETE FROM humeda;

alter table humeda auto_increment = 1;

#---------------------------------------

#ENTIDAD SECO 6° BORRAR =>

SELECT * FROM seco;

SELECT * FROM seco WHERE idSeco = 1;

DELETE FROM seco WHERE idSeco = 1;

DELETE FROM seco;

alter table seco auto_increment = 1;

#---------------------------------------

#ENTIDAD PANEL 7° BORRAR =>

SELECT * FROM panel;

SELECT * FROM panel WHERE idPanel = 1;

DELETE FROM panel WHERE idPanel = 1;

DELETE FROM panel;

alter table panel auto_increment = 1;

#---------------------------------------

#ENTIDAD RED_AGUA  8° BORRAR =>

SELECT * FROM redagua;

SELECT * FROM redagua WHERE idAgua = 1;

DELETE FROM redagua WHERE idAgua = 1;

DELETE FROM redagua;

alter table redagua auto_increment = 1;

#---------------------------------------

#ENTIDAD RED_GAS  9° BORRAR =>

SELECT * FROM redgas;

SELECT * FROM redgas WHERE idGas = 1;

DELETE FROM redgas WHERE idGas = 1;

DELETE FROM redgas;

alter table redgas auto_increment = 1;

#---------------------------------------

#ENTIDAD RED_ELECTRICIDAD  10° BORRAR =>

SELECT * FROM redelectricidad;

SELECT * FROM redelectricidad WHERE idElectricidad = 1;

DELETE FROM redelectricidad WHERE idElectricidad = 1;

DELETE FROM redelectricidad;

alter table redelectricidad auto_increment = 1;

#---------------------------------------

#ENTIDAD ABERTURAS  11° BORRAR =>

SELECT * FROM abertura;

SELECT * FROM abertura WHERE idAbertura = 1;

DELETE FROM abertura WHERE idAbertura = 1;

DELETE FROM abertura;

alter table abertura auto_increment = 1;

#---------------------------------------

#ENTIDAD CONLUSION 1° Borrar =>

SELECT * FROM conclusion;

SELECT * FROM conclusion WHERE idConclusion = 1;

DELETE FROM conclusion WHERE idConclusion = 1;

DELETE FROM conclusion;

alter table conclusion auto_increment = 1;

#Verificar si la entidad Conclusion tiene un registro relacionado al idGeneral =>
SELECT count(idGeneral) FROM conclusion where idGeneral = 6;
                                                                                                                                                     
                                                                                                                                                     