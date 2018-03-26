--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.8
-- Dumped by pg_dump version 9.6.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: accesos; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA accesos;


ALTER SCHEMA accesos OWNER TO postgres;

--
-- Name: visits; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA visits;


ALTER SCHEMA visits OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: asigna_permiso(boolean, integer, integer); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.asigna_permiso(opcion boolean, pid_perfil integer, pid_menu integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare rpta int;
BEGIN
rpta=0;
	if (opcion) 
	then
		--rpta=1;
                insert into accesos.permisos (id_perfil,id_menu,fecha)values(pid_perfil,pid_menu,CURRENT_DATE);
        else
		--rpta=2;
                DELETE FROM accesos.permisos where id_perfil=pid_perfil and id_menu=pid_menu;
        end if;

  return rpta;

  exception when others then  	
  raise notice '% : %', SQLERRM, SQLSTATE;
  return -1;  

END;
$$;


ALTER FUNCTION accesos.asigna_permiso(opcion boolean, pid_perfil integer, pid_menu integer) OWNER TO postgres;

--
-- Name: cambio_password(integer, character varying, character varying); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.cambio_password(pid_usuario integer, passact character varying, newpass character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
	declare rpta int;
	BEGIN
	rpta=0;
		if not exists(select * from accesos.usuario where id_usuario=pid_usuario and clave=passact )
		then  
			rpta=1;--la contraeña actual no es valida	
		else
			UPDATE accesos.usuario SET clave=newpass WHERE id_usuario=pid_usuario; 
			rpta=0;--la contraeña actual es valida
		END IF;  
	return rpta;
	END;
$$;


ALTER FUNCTION accesos.cambio_password(pid_usuario integer, passact character varying, newpass character varying) OWNER TO postgres;

--
-- Name: delete_usuario(integer); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.delete_usuario(pid_usuario integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare rpta int;
BEGIN
rpta=0;
	DELETE FROM accesos.usuario
	WHERE id_usuario=pid_usuario;

  return rpta;

  exception when others then  	
  raise notice '% : %', SQLERRM, SQLSTATE;
  return -1;  

END;
$$;


ALTER FUNCTION accesos.delete_usuario(pid_usuario integer) OWNER TO postgres;

--
-- Name: info_user(character varying); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.info_user(p_login character varying, OUT id_usuario integer, OUT login character varying, OUT clave character varying, OUT estado integer, OUT fecha_reg timestamp without time zone, OUT nro_sesion integer, OUT sesion_activa integer, OUT nombres character varying, OUT apellidos character varying, OUT foto character varying, OUT genero character varying, OUT dni character varying, OUT correo character varying, OUT id_perfil integer, OUT perfil character varying, OUT pestado integer, OUT tiempo_sesion integer) RETURNS SETOF record
    LANGUAGE sql
    AS $$
	select u.id_usuario,u.login,u.clave,u.estado,u.fecha_reg,u.nro_sesion,u.sesion_activa,u.nombres,u.apellidos,u.foto,u.genero,u.dni,u.correo,u.id_perfil,p.nombre as perfil, p.estado,p.tiempo_sesion  from accesos.usuario u 
	inner join accesos.perfiles p on p.id_perfil=u.id_perfil where login=P_login;
 $$;


ALTER FUNCTION accesos.info_user(p_login character varying, OUT id_usuario integer, OUT login character varying, OUT clave character varying, OUT estado integer, OUT fecha_reg timestamp without time zone, OUT nro_sesion integer, OUT sesion_activa integer, OUT nombres character varying, OUT apellidos character varying, OUT foto character varying, OUT genero character varying, OUT dni character varying, OUT correo character varying, OUT id_perfil integer, OUT perfil character varying, OUT pestado integer, OUT tiempo_sesion integer) OWNER TO postgres;

--
-- Name: inserta_perfil(character varying, integer, timestamp without time zone, integer); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.inserta_perfil(pnombre character varying, pestado integer, pfecha timestamp without time zone, ptiempo_sesion integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare rpta int;
BEGIN
rpta=0;
  if not exists(select * from accesos.perfiles where nombre=pnombre)
   then  
	INSERT INTO accesos.perfiles(nombre, estado, fecha, tiempo_sesion)
	VALUES (pnombre, pestado, pfecha, ptiempo_sesion);
	rpta=0;--el perfl no exite	
  else
	rpta=1;--el perfl ya existe

   END IF;
 return rpta;
END;
$$;


ALTER FUNCTION accesos.inserta_perfil(pnombre character varying, pestado integer, pfecha timestamp without time zone, ptiempo_sesion integer) OWNER TO postgres;

--
-- Name: inserta_usuario(character varying, character varying, integer, timestamp without time zone, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.inserta_usuario(plogin character varying, pclave character varying, pestado integer, pfecha_reg timestamp without time zone, pid_perfil integer, pnro_sesion integer, psesion_activa integer, pnombres character varying, papellidos character varying, pfoto character varying, pgenero character varying, pdni character varying, pcorreo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare rpta int;
BEGIN
rpta=0;
  if not exists(select * from accesos.usuario where login=plogin)
   then  
	INSERT INTO accesos.usuario(
            login, clave, estado, fecha_reg, id_perfil, nro_sesion, 
            sesion_activa, nombres, apellidos, foto, genero, dni, correo)
	VALUES (plogin, pclave, pestado, pfecha_reg, pid_perfil, pnro_sesion, 
            psesion_activa, pnombres, papellidos, pfoto, pgenero, pdni, pcorreo);
	rpta=0;--el usuario no exite	
  else
	rpta=1;--el usuario ya existe

   END IF;
 return rpta;
END;
$$;


ALTER FUNCTION accesos.inserta_usuario(plogin character varying, pclave character varying, pestado integer, pfecha_reg timestamp without time zone, pid_perfil integer, pnro_sesion integer, psesion_activa integer, pnombres character varying, papellidos character varying, pfoto character varying, pgenero character varying, pdni character varying, pcorreo character varying) OWNER TO postgres;

--
-- Name: modifica_perfil(integer, character varying, integer, integer); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.modifica_perfil(pid_perfil integer, pnombre character varying, pestado integer, ptiempo_sesion integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare rpta int;
BEGIN
rpta=0;
	UPDATE accesos.perfiles
	SET  nombre=pnombre, estado=pestado, tiempo_sesion=ptiempo_sesion
	WHERE id_perfil=pid_perfil;


  return rpta;

  exception when others then  	
  raise notice '% : %', SQLERRM, SQLSTATE;
  return -1; 
END;
$$;


ALTER FUNCTION accesos.modifica_perfil(pid_perfil integer, pnombre character varying, pestado integer, ptiempo_sesion integer) OWNER TO postgres;

--
-- Name: modifica_usuario(integer, character varying, integer, integer, integer, integer, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.modifica_usuario(pid_usuario integer, plogin character varying, pestado integer, pid_perfil integer, pnro_sesion integer, psesion_activa integer, pnombres character varying, papellidos character varying, pfoto character varying, pgenero character varying, pdni character varying, pcorreo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
declare rpta int;
BEGIN
rpta=0;
	UPDATE accesos.usuario
	SET login=plogin, estado=pestado, id_perfil=pid_perfil, 
        nro_sesion=pnro_sesion, sesion_activa=psesion_activa, nombres=pnombres, apellidos=papellidos, foto=pfoto, 
        genero=pgenero, dni=pdni, correo=pcorreo
	WHERE id_usuario=pid_usuario;

  return rpta;

  exception when others then  	
  raise notice '% : %', SQLERRM, SQLSTATE;
  return -1; 
END;
$$;


ALTER FUNCTION accesos.modifica_usuario(pid_usuario integer, plogin character varying, pestado integer, pid_perfil integer, pnro_sesion integer, psesion_activa integer, pnombres character varying, papellidos character varying, pfoto character varying, pgenero character varying, pdni character varying, pcorreo character varying) OWNER TO postgres;

--
-- Name: valida_usuario(character varying, character varying); Type: FUNCTION; Schema: accesos; Owner: postgres
--

CREATE FUNCTION accesos.valida_usuario(v1 character varying, v2 character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
	declare rpta int;
	BEGIN
	rpta=0;
		if not exists(select * from accesos.usuario where login=v1)
		then  
			rpta=1;--el usuario no exite	
		else
			if not exists(select * from accesos.usuario where login=v1 and clave=v2)
			then  
				rpta=2;--La ontraseña  es incorrecta
			else
				if exists(select * from accesos.usuario where login=v1 and clave=v2 and estado=0)
				then
				 rpta=3; --Usuario inactivo 
				else
				  rpta=(select case when sesion_activa<nro_sesion then 0 else 0 end from accesos.usuario where login=v1 and clave=v2);
				END IF;
			END IF;
		END IF;  
	return rpta;
	END;
$$;


ALTER FUNCTION accesos.valida_usuario(v1 character varying, v2 character varying) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: menu; Type: TABLE; Schema: accesos; Owner: postgres
--

CREATE TABLE accesos.menu (
    id_menu integer NOT NULL,
    padre integer NOT NULL,
    nivel integer NOT NULL,
    nombre_menu character varying(250) NOT NULL,
    url character varying(250),
    estado integer,
    icono character varying,
    orden smallint
);


ALTER TABLE accesos.menu OWNER TO postgres;

--
-- Name: menu_id_menu_seq; Type: SEQUENCE; Schema: accesos; Owner: postgres
--

CREATE SEQUENCE accesos.menu_id_menu_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accesos.menu_id_menu_seq OWNER TO postgres;

--
-- Name: menu_id_menu_seq; Type: SEQUENCE OWNED BY; Schema: accesos; Owner: postgres
--

ALTER SEQUENCE accesos.menu_id_menu_seq OWNED BY accesos.menu.id_menu;


--
-- Name: perfiles; Type: TABLE; Schema: accesos; Owner: postgres
--

CREATE TABLE accesos.perfiles (
    id_perfil integer NOT NULL,
    nombre character varying(100) NOT NULL,
    estado integer NOT NULL,
    fecha timestamp(6) without time zone,
    tiempo_sesion integer
);


ALTER TABLE accesos.perfiles OWNER TO postgres;

--
-- Name: perfiles_id_perfil_seq; Type: SEQUENCE; Schema: accesos; Owner: postgres
--

CREATE SEQUENCE accesos.perfiles_id_perfil_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accesos.perfiles_id_perfil_seq OWNER TO postgres;

--
-- Name: perfiles_id_perfil_seq; Type: SEQUENCE OWNED BY; Schema: accesos; Owner: postgres
--

ALTER SEQUENCE accesos.perfiles_id_perfil_seq OWNED BY accesos.perfiles.id_perfil;


--
-- Name: permisos; Type: TABLE; Schema: accesos; Owner: postgres
--

CREATE TABLE accesos.permisos (
    id_perfil integer NOT NULL,
    id_menu integer NOT NULL,
    fecha timestamp(6) without time zone NOT NULL
);


ALTER TABLE accesos.permisos OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: accesos; Owner: postgres
--

CREATE TABLE accesos.usuario (
    id_usuario integer NOT NULL,
    login character varying(20) NOT NULL,
    clave character varying(255) NOT NULL,
    estado integer NOT NULL,
    fecha_reg timestamp(6) without time zone NOT NULL,
    id_perfil integer,
    nro_sesion integer,
    sesion_activa integer,
    nombres character varying(50),
    apellidos character varying(50),
    foto character varying(50),
    genero character varying(1),
    dni character varying(20),
    correo character varying(50)
);


ALTER TABLE accesos.usuario OWNER TO postgres;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: accesos; Owner: postgres
--

CREATE SEQUENCE accesos.usuario_id_usuario_seq
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE accesos.usuario_id_usuario_seq OWNER TO postgres;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: accesos; Owner: postgres
--

ALTER SEQUENCE accesos.usuario_id_usuario_seq OWNED BY accesos.usuario.id_usuario;


--
-- Name: view_perfiles; Type: VIEW; Schema: accesos; Owner: postgres
--

CREATE VIEW accesos.view_perfiles AS
 SELECT perfiles.id_perfil,
    perfiles.nombre,
    perfiles.estado,
    perfiles.fecha,
    perfiles.tiempo_sesion
   FROM accesos.perfiles;


ALTER TABLE accesos.view_perfiles OWNER TO postgres;

--
-- Name: vista_usuarios; Type: VIEW; Schema: accesos; Owner: postgres
--

CREATE VIEW accesos.vista_usuarios AS
 SELECT a.id_usuario,
    a.login,
    a.clave,
    a.estado,
    a.fecha_reg,
    a.id_perfil,
    a.nro_sesion,
    a.sesion_activa,
    b.nombre AS perfil,
    b.tiempo_sesion,
    a.nombres,
    a.apellidos,
    a.foto,
    a.genero,
    a.dni,
    a.correo
   FROM (accesos.usuario a
     JOIN accesos.perfiles b ON ((b.id_perfil = a.id_perfil)));


ALTER TABLE accesos.vista_usuarios OWNER TO postgres;

--
-- Name: company; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.company (
    id_company integer NOT NULL,
    company_name character varying(100),
    status smallint,
    registration_date timestamp without time zone
);


ALTER TABLE visits.company OWNER TO postgres;

--
-- Name: company_id_company_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.company_id_company_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.company_id_company_seq OWNER TO postgres;

--
-- Name: company_id_company_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.company_id_company_seq OWNED BY visits.company.id_company;


--
-- Name: department; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.department (
    id_department integer NOT NULL,
    department character varying(50),
    registration_date timestamp without time zone DEFAULT now(),
    status smallint DEFAULT 1
);


ALTER TABLE visits.department OWNER TO postgres;

--
-- Name: department_id_department_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.department_id_department_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.department_id_department_seq OWNER TO postgres;

--
-- Name: department_id_department_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.department_id_department_seq OWNED BY visits.department.id_department;


--
-- Name: employee; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.employee (
    id_employee integer NOT NULL,
    full_name character varying(100),
    idcard character varying(25),
    registration_date timestamp without time zone DEFAULT now(),
    status smallint,
    id_occupation integer
);


ALTER TABLE visits.employee OWNER TO postgres;

--
-- Name: employee_id_employee_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.employee_id_employee_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.employee_id_employee_seq OWNER TO postgres;

--
-- Name: employee_id_employee_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.employee_id_employee_seq OWNED BY visits.employee.id_employee;


--
-- Name: occupation; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.occupation (
    id_occupation integer NOT NULL,
    occupation character varying(50),
    registration_date timestamp without time zone DEFAULT now(),
    status smallint DEFAULT 1
);


ALTER TABLE visits.occupation OWNER TO postgres;

--
-- Name: occupation_id_occupation_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.occupation_id_occupation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.occupation_id_occupation_seq OWNER TO postgres;

--
-- Name: occupation_id_occupation_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.occupation_id_occupation_seq OWNED BY visits.occupation.id_occupation;


--
-- Name: reasons_visit; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.reasons_visit (
    id_reason integer NOT NULL,
    reasons_name character varying(50),
    status boolean,
    registration_date timestamp without time zone DEFAULT now()
);


ALTER TABLE visits.reasons_visit OWNER TO postgres;

--
-- Name: reasons_visit_id_reason_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.reasons_visit_id_reason_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.reasons_visit_id_reason_seq OWNER TO postgres;

--
-- Name: reasons_visit_id_reason_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.reasons_visit_id_reason_seq OWNED BY visits.reasons_visit.id_reason;


--
-- Name: sequence_call_code; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.sequence_call_code
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.sequence_call_code OWNER TO postgres;

--
-- Name: visit_schedule; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.visit_schedule (
    id_visit_schedule bigint NOT NULL,
    date_ini timestamp without time zone NOT NULL,
    badge_number character varying(50),
    id_company integer NOT NULL,
    id_employee integer NOT NULL,
    id_visitor integer NOT NULL,
    registration_date timestamp without time zone,
    id_usuario integer NOT NULL,
    id_reason integer NOT NULL,
    status smallint NOT NULL,
    call_cod character varying DEFAULT (('CT'::text || nextval('visits.sequence_call_code'::regclass)) || 'AC'::text) NOT NULL,
    id_department integer NOT NULL,
    date_end timestamp without time zone,
    hour character varying(10)
);


ALTER TABLE visits.visit_schedule OWNER TO postgres;

--
-- Name: visit_schedule_id_visit_schedule_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.visit_schedule_id_visit_schedule_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.visit_schedule_id_visit_schedule_seq OWNER TO postgres;

--
-- Name: visit_schedule_id_visit_schedule_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.visit_schedule_id_visit_schedule_seq OWNED BY visits.visit_schedule.id_visit_schedule;


--
-- Name: visitor_log; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.visitor_log (
    id_visit_schedule bigint NOT NULL,
    badge_number character varying(50),
    type smallint NOT NULL,
    registration_date timestamp without time zone NOT NULL
);


ALTER TABLE visits.visitor_log OWNER TO postgres;

--
-- Name: visitors; Type: TABLE; Schema: visits; Owner: postgres
--

CREATE TABLE visits.visitors (
    id_visitor integer NOT NULL,
    full_name character varying(100),
    number_license character varying(50),
    citizen_ship character varying,
    email character varying(50),
    phone_number character varying(50),
    status smallint,
    registration_date timestamp without time zone
);


ALTER TABLE visits.visitors OWNER TO postgres;

--
-- Name: visitors_id_visitor_seq; Type: SEQUENCE; Schema: visits; Owner: postgres
--

CREATE SEQUENCE visits.visitors_id_visitor_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE visits.visitors_id_visitor_seq OWNER TO postgres;

--
-- Name: visitors_id_visitor_seq; Type: SEQUENCE OWNED BY; Schema: visits; Owner: postgres
--

ALTER SEQUENCE visits.visitors_id_visitor_seq OWNED BY visits.visitors.id_visitor;


--
-- Name: menu id_menu; Type: DEFAULT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.menu ALTER COLUMN id_menu SET DEFAULT nextval('accesos.menu_id_menu_seq'::regclass);


--
-- Name: perfiles id_perfil; Type: DEFAULT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.perfiles ALTER COLUMN id_perfil SET DEFAULT nextval('accesos.perfiles_id_perfil_seq'::regclass);


--
-- Name: usuario id_usuario; Type: DEFAULT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('accesos.usuario_id_usuario_seq'::regclass);


--
-- Name: company id_company; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.company ALTER COLUMN id_company SET DEFAULT nextval('visits.company_id_company_seq'::regclass);


--
-- Name: department id_department; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.department ALTER COLUMN id_department SET DEFAULT nextval('visits.department_id_department_seq'::regclass);


--
-- Name: employee id_employee; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.employee ALTER COLUMN id_employee SET DEFAULT nextval('visits.employee_id_employee_seq'::regclass);


--
-- Name: occupation id_occupation; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.occupation ALTER COLUMN id_occupation SET DEFAULT nextval('visits.occupation_id_occupation_seq'::regclass);


--
-- Name: reasons_visit id_reason; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.reasons_visit ALTER COLUMN id_reason SET DEFAULT nextval('visits.reasons_visit_id_reason_seq'::regclass);


--
-- Name: visit_schedule id_visit_schedule; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule ALTER COLUMN id_visit_schedule SET DEFAULT nextval('visits.visit_schedule_id_visit_schedule_seq'::regclass);


--
-- Name: visitors id_visitor; Type: DEFAULT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visitors ALTER COLUMN id_visitor SET DEFAULT nextval('visits.visitors_id_visitor_seq'::regclass);


--
-- Data for Name: menu; Type: TABLE DATA; Schema: accesos; Owner: postgres
--

COPY accesos.menu (id_menu, padre, nivel, nombre_menu, url, estado, icono, orden) FROM stdin;
2	1	1	Usuarios	usuarios/inicio	1	fa fa-user	2
3	1	1	Perfiles	perfiles/inicio	1	fa fa-group	1
1	0	0	Accesos	\N	1	fa fa-lock	1
6	4	1	Report	visit/report	1	fa fa-area-chart 	5
4	0	0	Visit	\N	1	fa fa-list-alt	2
5	4	1	Schedule	visit/schedule	1	fa fa-clock-o	1
8	4	1	Employee	visit/employee	1	fa fa-user-plus	3
9	4	1	Company	visit/company	1	fa  fa-building	4
7	4	1	Visitors	visit/visitor	1	fa fa-child	2
\.


--
-- Name: menu_id_menu_seq; Type: SEQUENCE SET; Schema: accesos; Owner: postgres
--

SELECT pg_catalog.setval('accesos.menu_id_menu_seq', 14, true);


--
-- Data for Name: perfiles; Type: TABLE DATA; Schema: accesos; Owner: postgres
--

COPY accesos.perfiles (id_perfil, nombre, estado, fecha, tiempo_sesion) FROM stdin;
2	consulta	0	2015-09-02 00:00:00	10
1	administrador	1	2013-05-21 16:02:21	30
6	desarrollo	1	2015-09-02 00:00:00	30
4	soporte	1	2015-09-02 00:00:00	1200
8	tester	1	2017-11-09 17:04:12.972	1200
9	muñoz	1	2017-11-17 17:02:03.238	1200
\.


--
-- Name: perfiles_id_perfil_seq; Type: SEQUENCE SET; Schema: accesos; Owner: postgres
--

SELECT pg_catalog.setval('accesos.perfiles_id_perfil_seq', 9, true);


--
-- Data for Name: permisos; Type: TABLE DATA; Schema: accesos; Owner: postgres
--

COPY accesos.permisos (id_perfil, id_menu, fecha) FROM stdin;
1	1	2017-09-11 16:42:07.513732
1	2	2017-09-11 16:42:07.513732
1	3	2017-09-11 16:42:07.513732
1	4	2018-02-25 00:00:00
1	5	2018-02-25 00:00:00
1	6	2018-02-25 00:00:00
1	7	2018-02-27 00:00:00
1	8	2018-02-27 00:00:00
1	9	2018-02-27 00:00:00
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: accesos; Owner: postgres
--

COPY accesos.usuario (id_usuario, login, clave, estado, fecha_reg, id_perfil, nro_sesion, sesion_activa, nombres, apellidos, foto, genero, dni, correo) FROM stdin;
2	dsalas	123456	1	2017-09-11 16:41:48.978469	6	1	1	David	Salas Montalvan	user.png	M	44372224	david.salas@atiperu.com
5	eespiritu	123456	1	2017-11-09 17:25:29.355	6	1	1	Erik	Espiritu Ariza	user.png	M	42662642	erik.espiritu@atiperu.com
1	admin	admin	1	2017-09-11 16:41:11.864134	1	1	1	admin	ctac	anonymous.png	M	\N	admin@atiperu.com
\.


--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: accesos; Owner: postgres
--

SELECT pg_catalog.setval('accesos.usuario_id_usuario_seq', 5, true);


--
-- Data for Name: company; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.company (id_company, company_name, status, registration_date) FROM stdin;
3	telefonica	1	2018-03-01 22:52:17.682
1	ati peru lima	1	2018-03-01 22:11:24.332
2	gms	0	2018-03-01 22:52:06.247
4	company1	1	2018-03-01 23:57:35.287
8	company5	0	2018-03-01 23:57:51.539
7	company4	0	2018-03-01 23:57:47.888
6	company3	0	2018-03-01 23:57:44.074
5	company2	0	2018-03-01 23:57:38.967
9	dell	1	2018-03-16 21:34:28.335
10	hp company	1	2018-03-17 17:07:03.122
\.


--
-- Name: company_id_company_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.company_id_company_seq', 10, true);


--
-- Data for Name: department; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.department (id_department, department, registration_date, status) FROM stdin;
1	contabilidad	2018-03-13 21:11:47.331842	1
2	logistica	2018-03-13 21:12:54.076645	1
3	finanzas	2018-03-13 21:13:01.751509	1
\.


--
-- Name: department_id_department_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.department_id_department_seq', 3, true);


--
-- Data for Name: employee; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.employee (id_employee, full_name, idcard, registration_date, status, id_occupation) FROM stdin;
1	david salas montalvan	44372224	2018-03-03 08:17:00.353	1	1
2	ricardo santos	34343434	2018-03-03 08:43:08.676	0	2
3	Maria perez	444444444	2018-03-03 08:43:31.785	1	3
4	sofia fernandez	3434343435656	2018-03-15 22:04:05.951	1	2
\.


--
-- Name: employee_id_employee_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.employee_id_employee_seq', 4, true);


--
-- Data for Name: occupation; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.occupation (id_occupation, occupation, registration_date, status) FROM stdin;
1	gerente general	2018-03-15 21:18:42.367023	1
3	gerente de operaciones	2018-03-15 21:19:17.169429	1
2	gerente de marketing	2018-03-15 21:19:01.76732	1
\.


--
-- Name: occupation_id_occupation_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.occupation_id_occupation_seq', 3, true);


--
-- Data for Name: reasons_visit; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.reasons_visit (id_reason, reasons_name, status, registration_date) FROM stdin;
3	Work meeting	t	2018-03-02 00:22:55.584297
2	visit	t	2018-03-02 00:21:32.470912
1	business meeting\n	t	2018-03-02 00:20:27.695152
4	maintenance works\n	t	2018-03-02 00:25:47.625257
\.


--
-- Name: reasons_visit_id_reason_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.reasons_visit_id_reason_seq', 4, true);


--
-- Name: sequence_call_code; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.sequence_call_code', 118, true);


--
-- Data for Name: visit_schedule; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.visit_schedule (id_visit_schedule, date_ini, badge_number, id_company, id_employee, id_visitor, registration_date, id_usuario, id_reason, status, call_cod, id_department, date_end, hour) FROM stdin;
23	2018-03-24 00:00:00	\N	10	3	4	2018-03-24 15:42:28.304	1	3	1	CT117AC	1	2018-03-31 00:00:00	10:00
24	2018-03-24 00:00:00	\N	10	4	5	2018-03-24 19:41:39.429	1	2	1	CT118AC	2	2018-03-24 00:00:00	19:41
21	2018-03-25 08:50:17.960278	\N	9	1	1	2018-03-24 15:08:24.581	1	3	1	CT115AC	3	2018-03-25 08:50:17.960278	15:08
22	2018-03-25 08:50:17.960278	\N	9	4	1	2018-03-24 15:13:23.147	1	3	1	CT116AC	2	2018-03-25 08:50:17.960278	18:12
\.


--
-- Name: visit_schedule_id_visit_schedule_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.visit_schedule_id_visit_schedule_seq', 24, true);


--
-- Data for Name: visitor_log; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.visitor_log (id_visit_schedule, badge_number, type, registration_date) FROM stdin;
\.


--
-- Data for Name: visitors; Type: TABLE DATA; Schema: visits; Owner: postgres
--

COPY visits.visitors (id_visitor, full_name, number_license, citizen_ship, email, phone_number, status, registration_date) FROM stdin;
1	ana flores	33333333	peruana	ana.flores@dell.com	99999999999	1	2018-03-03 12:45:57.612
2	juan flores	434343434	peruana	juan.flores@dell.com	333333333333	1	2018-03-03 12:49:40.516
3	ana flores 1	33333333	peruana	ana.flores1@dell.com	66666666	0	2018-03-03 12:50:31.065
4	patricia pereyra	34567888	peruana	patricia@hp.com	6677777777	1	2018-03-17 17:06:47.944
5	ana flores	454545454545	peruana	ana.flores@hp.com	99999999999	1	2018-03-24 19:12:54.124
\.


--
-- Name: visitors_id_visitor_seq; Type: SEQUENCE SET; Schema: visits; Owner: postgres
--

SELECT pg_catalog.setval('visits.visitors_id_visitor_seq', 5, true);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id_menu);


--
-- Name: perfiles perfiles_pkey; Type: CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.perfiles
    ADD CONSTRAINT perfiles_pkey PRIMARY KEY (id_perfil);


--
-- Name: permisos permisos_pkey; Type: CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.permisos
    ADD CONSTRAINT permisos_pkey PRIMARY KEY (id_perfil, id_menu);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- Name: company company_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id_company);


--
-- Name: department department_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (id_department);


--
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id_employee);


--
-- Name: occupation occupation_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.occupation
    ADD CONSTRAINT occupation_pkey PRIMARY KEY (id_occupation);


--
-- Name: reasons_visit reasons_visit_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.reasons_visit
    ADD CONSTRAINT reasons_visit_pkey PRIMARY KEY (id_reason);


--
-- Name: visit_schedule visit_schedule_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_pkey PRIMARY KEY (id_visit_schedule);


--
-- Name: visitors visitors_pkey; Type: CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visitors
    ADD CONSTRAINT visitors_pkey PRIMARY KEY (id_visitor);


--
-- Name: permisos permisos_id_menu_fkey; Type: FK CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.permisos
    ADD CONSTRAINT permisos_id_menu_fkey FOREIGN KEY (id_menu) REFERENCES accesos.menu(id_menu);


--
-- Name: permisos permisos_id_perfil_fkey; Type: FK CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.permisos
    ADD CONSTRAINT permisos_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES accesos.perfiles(id_perfil);


--
-- Name: usuario usuario_id_perfil_fkey; Type: FK CONSTRAINT; Schema: accesos; Owner: postgres
--

ALTER TABLE ONLY accesos.usuario
    ADD CONSTRAINT usuario_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES accesos.perfiles(id_perfil);


--
-- Name: employee employee_id_occupation_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.employee
    ADD CONSTRAINT employee_id_occupation_fkey FOREIGN KEY (id_occupation) REFERENCES visits.occupation(id_occupation);


--
-- Name: visit_schedule visit_schedule_id_company_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_id_company_fkey FOREIGN KEY (id_company) REFERENCES visits.company(id_company);


--
-- Name: visit_schedule visit_schedule_id_department_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_id_department_fkey FOREIGN KEY (id_department) REFERENCES visits.department(id_department);


--
-- Name: visit_schedule visit_schedule_id_employee_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_id_employee_fkey FOREIGN KEY (id_employee) REFERENCES visits.employee(id_employee);


--
-- Name: visit_schedule visit_schedule_id_reason_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_id_reason_fkey FOREIGN KEY (id_reason) REFERENCES visits.reasons_visit(id_reason);


--
-- Name: visit_schedule visit_schedule_id_usuario_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES accesos.usuario(id_usuario);


--
-- Name: visit_schedule visit_schedule_id_visitor_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visit_schedule
    ADD CONSTRAINT visit_schedule_id_visitor_fkey FOREIGN KEY (id_visitor) REFERENCES visits.visitors(id_visitor);


--
-- Name: visitor_log visitor_log_id_visit_schedule_fkey; Type: FK CONSTRAINT; Schema: visits; Owner: postgres
--

ALTER TABLE ONLY visits.visitor_log
    ADD CONSTRAINT visitor_log_id_visit_schedule_fkey FOREIGN KEY (id_visit_schedule) REFERENCES visits.visit_schedule(id_visit_schedule);


--
-- PostgreSQL database dump complete
--

