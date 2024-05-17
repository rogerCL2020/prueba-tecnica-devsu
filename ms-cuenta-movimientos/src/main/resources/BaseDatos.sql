--
-- PostgreSQL database dump
--

-- Dumped from database version 14.12
-- Dumped by pg_dump version 14.12

-- Started on 2024-05-16 11:36:54

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "demo-devsu";
--
-- TOC entry 3343 (class 1262 OID 24804)
-- Name: demo-devsu; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "demo-devsu" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Peru.1252';


ALTER DATABASE "demo-devsu" OWNER TO postgres;

\connect -reuse-previous=on "dbname='demo-devsu'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3344 (class 0 OID 0)
-- Name: demo-devsu; Type: DATABASE PROPERTIES; Schema: -; Owner: postgres
--

ALTER DATABASE "demo-devsu" CONNECTION LIMIT = 100;


\connect -reuse-previous=on "dbname='demo-devsu'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 25173)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    edad integer,
    estado boolean DEFAULT true,
    cliente_id bigint NOT NULL,
    contrasena character varying(255),
    direccion character varying(255),
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255),
    CONSTRAINT cliente_genero_check CHECK (((genero)::text = ANY ((ARRAY['MASCULINO'::character varying, 'FEMENINO'::character varying, 'OTRO'::character varying])::text[])))
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 25172)
-- Name: cliente_cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_cliente_id_seq OWNER TO postgres;

--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 209
-- Name: cliente_cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_cliente_id_seq OWNED BY public.cliente.cliente_id;


--
-- TOC entry 212 (class 1259 OID 25186)
-- Name: cuenta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta (
    estado boolean DEFAULT true,
    saldo numeric(38,2),
    cliente_id bigint NOT NULL,
    cuenta_id bigint NOT NULL,
    numero_cuenta character varying(10) NOT NULL,
    tipo_cuenta character varying(255) NOT NULL,
    CONSTRAINT cuenta_tipo_cuenta_check CHECK (((tipo_cuenta)::text = ANY ((ARRAY['CORRIENTE'::character varying, 'AHORRO'::character varying])::text[])))
);


ALTER TABLE public.cuenta OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 25185)
-- Name: cuenta_cuenta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cuenta_cuenta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cuenta_cuenta_id_seq OWNER TO postgres;

--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 211
-- Name: cuenta_cuenta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cuenta_cuenta_id_seq OWNED BY public.cuenta.cuenta_id;


--
-- TOC entry 214 (class 1259 OID 25197)
-- Name: movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimiento (
    saldo numeric(38,2),
    saldo_inicial numeric(38,2),
    valor numeric(38,2),
    cuenta_id bigint NOT NULL,
    fecha timestamp(6) without time zone NOT NULL,
    movimiento_id bigint NOT NULL
);


ALTER TABLE public.movimiento OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 25196)
-- Name: movimiento_movimiento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimiento_movimiento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimiento_movimiento_id_seq OWNER TO postgres;

--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 213
-- Name: movimiento_movimiento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_movimiento_id_seq OWNED BY public.movimiento.movimiento_id;


--
-- TOC entry 3175 (class 2604 OID 25177)
-- Name: cliente cliente_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN cliente_id SET DEFAULT nextval('public.cliente_cliente_id_seq'::regclass);


--
-- TOC entry 3178 (class 2604 OID 25190)
-- Name: cuenta cuenta_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta ALTER COLUMN cuenta_id SET DEFAULT nextval('public.cuenta_cuenta_id_seq'::regclass);


--
-- TOC entry 3180 (class 2604 OID 25200)
-- Name: movimiento movimiento_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento ALTER COLUMN movimiento_id SET DEFAULT nextval('public.movimiento_movimiento_id_seq'::regclass);


--
-- TOC entry 3333 (class 0 OID 25173)
-- Dependencies: 210
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cliente VALUES (32, true, 2, NULL, 'Amazonas y NNUU', 'FEMENINO', '5678', 'Marianela Montalvo', '097548965');
INSERT INTO public.cliente VALUES (58, true, 3, NULL, '13 de junio y Equinoccial', 'MASCULINO', '1597', 'Juan Osorio', '098874587');
INSERT INTO public.cliente VALUES (27, true, 1, '12345', 'Otavalo sn y principal', 'MASCULINO', '1245', 'Jose Lema', '098254785');


--
-- TOC entry 3335 (class 0 OID 25186)
-- Dependencies: 212
-- Data for Name: cuenta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cuenta VALUES (true, 1000.00, 1, 5, '585545', 'CORRIENTE');
INSERT INTO public.cuenta VALUES (true, 1425.00, 1, 1, '478758', 'AHORRO');
INSERT INTO public.cuenta VALUES (true, 700.00, 2, 2, '225487', 'CORRIENTE');
INSERT INTO public.cuenta VALUES (true, 150.00, 3, 3, '495878', 'AHORRO');
INSERT INTO public.cuenta VALUES (true, 0.00, 2, 4, '496825', 'AHORRO');


--
-- TOC entry 3337 (class 0 OID 25197)
-- Dependencies: 214
-- Data for Name: movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento VALUES (1425.00, 2000.00, -575.00, 1, '2024-05-16 09:38:18.117084', 1);
INSERT INTO public.movimiento VALUES (700.00, 100.00, 600.00, 2, '2024-05-16 09:38:27.352741', 2);
INSERT INTO public.movimiento VALUES (150.00, 0.00, 150.00, 3, '2024-05-16 09:38:36.649536', 3);
INSERT INTO public.movimiento VALUES (0.00, 540.00, -540.00, 4, '2024-05-16 09:38:44.022554', 4);


--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 209
-- Name: cliente_cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_cliente_id_seq', 3, true);


--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 211
-- Name: cuenta_cuenta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuenta_cuenta_id_seq', 5, true);


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 213
-- Name: movimiento_movimiento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_movimiento_id_seq', 4, true);


--
-- TOC entry 3182 (class 2606 OID 25184)
-- Name: cliente cliente_identificacion_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_identificacion_key UNIQUE (identificacion);


--
-- TOC entry 3184 (class 2606 OID 25182)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id);


--
-- TOC entry 3186 (class 2606 OID 25195)
-- Name: cuenta cuenta_numero_cuenta_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_numero_cuenta_key UNIQUE (numero_cuenta);


--
-- TOC entry 3188 (class 2606 OID 25193)
-- Name: cuenta cuenta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (cuenta_id);


--
-- TOC entry 3190 (class 2606 OID 25202)
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (movimiento_id);


--
-- TOC entry 3191 (class 2606 OID 25203)
-- Name: cuenta fk_cuenta_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente(cliente_id);


--
-- TOC entry 3192 (class 2606 OID 25208)
-- Name: movimiento fk_movimiento_cuenta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT fk_movimiento_cuenta FOREIGN KEY (cuenta_id) REFERENCES public.cuenta(cuenta_id);


-- Completed on 2024-05-16 11:36:55

--
-- PostgreSQL database dump complete
--

