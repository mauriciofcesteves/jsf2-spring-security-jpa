--
-- PostgreSQL database dump
--

-- Started on 2013-10-04 10:22:36

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1839 (class 0 OID 0)
-- Dependencies: 127
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 112, true);


--
-- TOC entry 1825 (class 0 OID 24752)
-- Dependencies: 128
-- Data for Name: caderneta; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1826 (class 0 OID 24765)
-- Dependencies: 129
-- Data for Name: configuracao; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1827 (class 0 OID 24772)
-- Dependencies: 130
-- Data for Name: coordenador; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (30, true, 'ERIKA.MEDEIROS@ESTACIO.BR', '14006769', 'ERIKA CARLOS MEDEIROS', '(81) 9469-9266', '14006769');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (85, true, 'rone.silva@estacio.br', '14011461', 'RONE CESARIO DA SILVA', '(81) 8743-2705', '14011461');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (86, true, 'THAISAS84@GMAIL.COM', '14012365', 'THAIS DE ALMEIDA DA SILVA', '(81) 8785-2590', '14012365');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (87, true, 'felipe.pereira@live.estacio.br', '14006675', 'FELIPE AUGUSTO PEREIRA', '(81) 9735-0797', '14006675');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (88, true, 'joseoliveira@estacio.br', '14010911', 'JOSE LUIZ ARRUDA DE OLIVEIRA', '(81) 9376-3081', '14010911');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (89, true, 'jeane.beltrao@live.estacio.br', '14012386', 'JEANE ELISABETH DE OLIVEIRA BELTRAO ', '(81) 8191-1881', '14012386');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (90, true, 'kleyvson.miranda@estacio.br', '14006928', 'KLEYVSON JOSE DE MIRANDA', '(81) 7811-2047', '14006928');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (91, true, 'luiz.oliveira@estacio.br', '14006848', 'LUIZ JOSE RODRIGUES DE OLIVEIRA', '(81) 8639-1992', '14006848');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (92, true, 'daniele.melo@estacio.br', '14011092', 'DANIELE DE CASTRO PESSOA DE MELO ', '(81) 8643-1643', '14011092');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (93, true, 'gliner0504@hotmail.com', '14011086', 'GLINER DIAS ALENCAR', '(81) 9232-1983', '14011086');
INSERT INTO coordenador (id, ativo, email, matricula, nome, telefone, usuario_login) VALUES (94, true, 'cgpdelgado@hotmail.com', '14006914', 'CARLOS GUSTAVO PINTO DELGADO', '(81) 9620-0707', '14006914');


--
-- TOC entry 1828 (class 0 OID 24777)
-- Dependencies: 131
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (35, true, '2133', 'MBA EM GOVERNANÇA DE TI', 30);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (95, true, '1469', 'MBA EM GESTÃO DE PESSOAS', 85);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (96, true, '1471', 'COMUNICAÇÃO E MARKETING EM MÍDIAS DIGITAIS', 87);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (97, true, '1475', 'LOGISTICA EMPRESARIAL', 88);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (98, true, '2002', 'ENFERMAGEM DO TRABALHO', 86);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (99, true, '2019', 'ENGENHARIA DE SEGURANÇA DO TRABALHO', 89);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (100, true, '2065', 'MBA EM GESTÃO EMPRESARIAL', 88);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (101, true, '2081', 'MBA EM ADMINISTRAÇÃO PÚBLICA', 90);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (102, true, '2151', 'MBA EM GESTÃO ESTRATÉGICA DE PROJETOS', 91);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (103, true, '2264', 'MBA EM PETRÓLEO E ENERGIAS', 92);
INSERT INTO curso (id, ativo, codigo, nome, coordenador_id) VALUES (109, true, '1496', 'COMÉRCIO EXTERIOR', 93);


--
-- TOC entry 1829 (class 0 OID 24784)
-- Dependencies: 132
-- Data for Name: curso_disciplina; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (35, 34);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (35, 32);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (35, 33);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (35, 31);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (95, 84);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (95, 81);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (95, 80);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (95, 83);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (95, 82);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (96, 76);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (96, 77);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (96, 78);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (96, 79);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (97, 71);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (97, 72);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (97, 73);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (97, 74);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (97, 75);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (98, 68);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (98, 69);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (98, 70);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (98, 66);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (98, 67);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 59);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 63);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 62);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 61);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 60);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 64);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (99, 65);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (100, 55);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (100, 54);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (100, 58);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (100, 57);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (100, 56);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (101, 51);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (101, 50);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (101, 49);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (101, 53);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (101, 52);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (102, 46);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (102, 47);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (102, 44);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (102, 45);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (102, 48);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 38);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 39);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 36);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 37);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 42);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 43);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 40);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (103, 41);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (109, 108);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (109, 106);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (109, 107);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (109, 104);
INSERT INTO curso_disciplina (curso_id, disciplina_id) VALUES (109, 105);


--
-- TOC entry 1830 (class 0 OID 24787)
-- Dependencies: 133
-- Data for Name: disciplina; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (31, true, 'PGN1781', 'FUNDAMENTOS DE GOVERNANÇA DE TI');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (32, true, 'PGN1783', 'PLANEJAMENTO ESTRATEGICO DE TI');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (33, true, 'PGN1784', 'GERENCIAMENTO DE PROJETOS DE TI');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (34, true, 'PGN1785', 'GERENCIAMENTO DE SERVIÇOS DE TI COM ITIL V3');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (36, true, 'PGN2687', 'MATRIZ ENERGETICA');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (37, true, 'PGN2688', 'GEO. PET. E DO GÁS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (38, true, 'PGN2689', 'GER. DE P. - PMI');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (39, true, 'PGN2690', 'ANÁLISE E PROJETO');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (40, true, 'PGN2691', 'GESTÃO P. COMPETÊNCIAS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (41, true, 'PGN2692', 'GESTÃO IMP AMBIENTAIS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (42, true, 'PGN2693', 'PLANEJAMENTO ESTRETÉGICO');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (43, true, 'PGN2694', 'PETR: ESTUDOS DE CASOS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (44, true, 'PGN1987', 'AVAL.PROJ.DEINVEST.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (45, true, 'PGN1988', 'GERENC.DECUSTOS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (46, true, 'PGN1989', 'GERENC.DERISCOS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (47, true, 'PGN1990', 'GER.AQUIS.ECONTRAT.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (48, true, 'PGN1991', 'OF.PROJ.VIS.AN.FIN.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (49, true, 'PGN1547', 'A LEI R.FISCAL');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (50, true, 'PGN1548', 'CONTABIL.PUBLICA');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (51, true, 'PGN1549', 'FIN.ORÇ.PUBLICO');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (52, true, 'PGN1550', 'D.C.C.F.PÚBLICAS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (53, true, 'PGN1551', 'G.EST.DEPROJETOS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (54, true, 'PGN1316', 'GER.DEREC.HUMANOS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (55, true, 'PGN1317', 'GER.OPR.CAD.SUP.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (56, true, 'PGN1318', 'GER.TECN.INF.EERP');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (57, true, 'PGN1319', 'GER.MARK.COM.CORP.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (58, true, 'PGN1320', 'OFIC.PROJ.GER(TCP)');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (59, true, 'PGN0592', 'ENG.SEG.T.CONTEXT');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (60, true, 'PGN0593', 'ADM.ENG.DESEGURAN');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (61, true, 'PGN0594', 'PSI.ENG.S.C.TREI');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (62, true, 'PGN0595', 'PROT.AO.M.AMBIENTE');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (63, true, 'PGN0596', 'SIST.DEGST.AMBIEN');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (64, true, 'PGN0597', 'LEG.ENORM.TECNICA');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (65, true, 'PGN0598', 'TOP.AV.ENG.DESEG');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (66, true, 'PGN0524', 'SAÚDEDOTRABALHADOR');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (67, true, 'PGN0526', 'LEG.APL.S.DOTRAB');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (68, true, 'PGN0527', 'BIO.EASD.DOTRAB');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (69, true, 'PGN0532', 'EPID.EBIO.A.SAÚD');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (70, true, 'PGN0533', 'FISIOL.EERG.TRAB');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (71, true, 'PGN0203', 'TECN.SIST.DE.TRANSP');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (72, true, 'PGN0204', 'SIST.DIST.REDES.LOG.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (73, true, 'PGN0205', 'COMÉRCIOINTERNACION');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (74, true, 'PGN0206', 'LOG.EOPR.INTERN.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (75, true, 'PGN0207', 'SIST.INT.GEST.NEG.EL');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (76, true, 'PGN0646', 'PRD.CNT.P.MD.DG');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (77, true, 'PGN0647', 'ADVERGAMES');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (78, true, 'PGN0648', 'DISPOSITIVOS MÓVEIS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (79, true, 'PGN0649', 'MÉTR.EINDICADORES');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (80, true, 'PGN0140', 'LID.EGESTÃOEQUIPE');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (81, true, 'PGN0142', 'CLIMAQUAL.VIDATRAB');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (82, true, 'PGN0144', 'GESTÃO COMPETÊNCIAS');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (83, true, 'PGN0508', 'CID.RESP.SOCIAL');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (84, true, 'PGN0509', 'E.C-C.H.TRAB.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (104, true, 'PGN0196', 'MARKETING');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (105, true, 'PGN0331', 'PLAN ESTRATÉGICO');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (107, true, 'PGN0333', 'G. ADM. C. EXTERIOR');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (108, true, 'PGN0335', 'NEG. INTERNACIONAL');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (110, true, '1472', 'DIREITO CIVIL E PROCESSO CIVIL');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (106, true, 'PGN0332', 'GST DE P. E COMPET.');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (111, true, 'PGN0695', 'P. G. COD. CIVIL');
INSERT INTO disciplina (id, ativo, codigo, nome) VALUES (112, true, 'PGN0696', 'T. G. P. R. A. A. REFORMA');


--
-- TOC entry 1831 (class 0 OID 24792)
-- Dependencies: 134
-- Data for Name: parametrosistema; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1832 (class 0 OID 24800)
-- Dependencies: 135
-- Data for Name: perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO perfil (nome, descricao) VALUES ('ROLE_ADMIN', 'ADMINISTRADOR');
INSERT INTO perfil (nome, descricao) VALUES ('ROLE_SECRETARIA', 'SECRETÁRIA');
INSERT INTO perfil (nome, descricao) VALUES ('ROLE_GOP', 'GESTOR DE OPERAÇÕES');
INSERT INTO perfil (nome, descricao) VALUES ('ROLE_COORDENADOR', 'COORDENADOR');


--
-- TOC entry 1833 (class 0 OID 24805)
-- Dependencies: 136
-- Data for Name: professor; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1834 (class 0 OID 24810)
-- Dependencies: 137
-- Data for Name: templateemail; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1835 (class 0 OID 24818)
-- Dependencies: 138
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (login, ativado, nome, senha) VALUES ('admin', true, 'Administrador', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('sec', true, 'Secretária', 'add93534eeb463800fe0ed0946048d33636dd2a014fab92e8a37f77ce98c740b');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('gop', true, 'Gestor de Operações', 'fa3086798c6622b42928da40e2fe3464c482b8df4ce97a04edea3d81e83bc07f');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('1111', true, 'COORDENADOR 1', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('2222', true, 'COORDENADOR 2', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('ASDASDASDASDASD', true, 'ASDASDASDAD', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14006769', true, 'ERIKA CARLOS MEDEIROS', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14011461', true, 'RONE CESARIO DA SILVA', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14012365', true, 'THAIS DE ALMEIDA DA SILVA', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14006675', true, 'FELIPE AUGUSTO PEREIRA', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14010911', true, 'JOSE LUIZ ARRUDA DE OLIVEIRA', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14012386', true, 'JEANE ELISABETH DE OLIVEIRA BELTRAO ', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14006928', true, 'KLEYVSON JOSE DE MIRANDA', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14006848', true, 'LUIZ JOSE RODRIGUES DE OLIVEIRA', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14011092', true, 'DANIELE DE CASTRO PESSOA DE MELO ', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14011086', true, 'GLINER DIAS ALENCAR', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');
INSERT INTO usuario (login, ativado, nome, senha) VALUES ('14006914', true, 'CARLOS GUSTAVO PINTO DELGADO', '4fc8b2e4a5524a7d1d215e7cb0c64a08d5041856dc192ecb2fe1c8022b61d771');


--
-- TOC entry 1836 (class 0 OID 24823)
-- Dependencies: 139
-- Data for Name: usuario_perfil; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('sec', 'ROLE_SECRETARIA');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('gop', 'ROLE_GOP');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('1111', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('2222', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('ASDASDASDASDASD', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14006769', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14011461', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14012365', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14006675', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14010911', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14012386', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14006928', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14006848', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14011092', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14011086', 'ROLE_COORDENADOR');
INSERT INTO usuario_perfil (login_usuario, nome_perfil) VALUES ('14006914', 'ROLE_COORDENADOR');


-- Completed on 2013-10-04 10:22:37

--
-- PostgreSQL database dump complete
--

