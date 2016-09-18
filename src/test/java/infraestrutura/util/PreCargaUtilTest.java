package infraestrutura.util;

import infraestrutura.dao.CadernetasDao;
import infraestrutura.dao.CoordenadoresDao;
import infraestrutura.dao.CursosDao;
import infraestrutura.dao.DisciplinasDao;
import infraestrutura.dao.ParametroSistemaDao;
import infraestrutura.dao.PerfilDao;
import infraestrutura.dao.ProfessoresDao;
import infraestrutura.dao.UsuarioDao;
import infraestrutura.model.Caderneta;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Curso;
import infraestrutura.model.Disciplina;
import infraestrutura.model.ParametroSistema;
import infraestrutura.model.Professor;

import java.util.HashMap;

import org.joda.time.LocalDate;

/**
 * Esta classe realiza uma pre-carga em todo o sistema, com todas as classes
 * com o intuito de auxiliar nos testes automatizados do sistema.
 */
public class PreCargaUtilTest {

	
	private ProfessoresDao professoresDao;
	private CoordenadoresDao coordenadoresDao;
	private DisciplinasDao disciplinasDao;
	private CursosDao cursoDao;
	private CadernetasDao cadernetaDao;
	private ParametroSistemaDao parametroSistemaDao;
	
	private HashMap<Integer, Curso> mapaCurso;
	private HashMap<Integer, Professor> mapaProfessor;

	public void criarCargaNoSistema() {
		mapaCurso = new HashMap<Integer, Curso>();
		mapaProfessor = new HashMap<Integer, Professor>();

		criarCargaParametroSistema();
		criarCargaProfessor();
		criarCargaCursoDisciplinaCoordenador();
	}

	public void criarCargaProfessor() {
		Professor p1 = ProfessorUtilTest.criar(1);
		Professor p2 = ProfessorUtilTest.criar(2);
		Professor p3 = ProfessorUtilTest.criar(3);
		Professor p4 = ProfessorUtilTest.criar(4);
		Professor p5 = ProfessorUtilTest.criar(5);
		professoresDao.salvar(p1);
		professoresDao.salvar(p2);
		professoresDao.salvar(p3);
		professoresDao.salvar(p4);
		professoresDao.salvar(p5);
		
		mapaProfessor.put(1, p1);
		mapaProfessor.put(2, p2);
		mapaProfessor.put(3, p3);
		mapaProfessor.put(4, p4);
		mapaProfessor.put(5, p5);
	}
	
	public Coordenador criarCargaCoordenador(Integer id) {
		Coordenador c1 = CoordenadorUtilTest.criar(id);
		coordenadoresDao.salvar(c1);
		return c1;
	}
	
	public Disciplina criarCargaDisciplina(Integer id) {
		Disciplina d1 = DisciplinaUtilTest.criar(id);
		disciplinasDao.salvar(d1);
		return d1;
	}
	
	public void criarCargaCursoDisciplinaCoordenador() {
		Curso c1 = CursoUtilTest.criar(1, criarCargaCoordenador(1), criarCargaDisciplina(1));
		Curso c2 = CursoUtilTest.criar(2, criarCargaCoordenador(2), criarCargaDisciplina(2));
		Curso c3 = CursoUtilTest.criar(3, criarCargaCoordenador(3), criarCargaDisciplina(3));
		Curso c4 = CursoUtilTest.criar(4, criarCargaCoordenador(4), criarCargaDisciplina(4));
		Curso c5 = CursoUtilTest.criar(5, criarCargaCoordenador(5), criarCargaDisciplina(5));
		
		cursoDao.salvar(c1);
		cursoDao.salvar(c2);
		cursoDao.salvar(c3);
		cursoDao.salvar(c4);
		cursoDao.salvar(c5);
		
		mapaCurso.put(1, c1);
		mapaCurso.put(2, c2);
		mapaCurso.put(3, c3);
		mapaCurso.put(4, c4);
		mapaCurso.put(5, c5);
	}
	
	public void criarCargaCadernetaComProfessor(Integer id, LocalDate dataFinalDisciplina) {
		Caderneta c1 = CadernetaUtilTest.criarCadernetaComProfessor(id, mapaCurso.get(id), dataFinalDisciplina, mapaProfessor.get(id));
		cadernetaDao.salvar(c1);
	}
	
	public void criarCargaCadernetaAguardandoCoordenador(Integer id, LocalDate dataFinalDisciplina, LocalDate dataDevolucaoProfessor) {
		Caderneta c3 = CadernetaUtilTest.criarAguardandoCoordenador(id, mapaCurso.get(id), mapaProfessor.get(id), dataFinalDisciplina, dataDevolucaoProfessor);
		cadernetaDao.salvar(c3);
	}
	
	public void criarCargaCadernetaComCoordenador(Integer id, LocalDate dataFinalDisciplina, LocalDate dataDevolucaoProfessor) {
		Caderneta c4 = CadernetaUtilTest.criarCadernetaComCoordenador(id, mapaCurso.get(id), mapaProfessor.get(id), dataFinalDisciplina, dataDevolucaoProfessor);
		cadernetaDao.salvar(c4);
	}
	
	public void criarCargaCadernetaConcluida(Integer id) {
		Caderneta c5 = CadernetaUtilTest.criarCadernetaConcluida(id, mapaCurso.get(5), mapaProfessor.get(5));
		cadernetaDao.salvar(c5);
	}
	
	public void criarCargaParametroSistema() {
		ParametroSistema parametro = ParametroSistemaUtilTest.criar(1);
		parametroSistemaDao.salvar(parametro);
	}

	public void setProfessoresDao(ProfessoresDao professoresDao) {
		this.professoresDao = professoresDao;
	}

	public void setCoordenadoresDao(CoordenadoresDao coordenadoresDao) {
		this.coordenadoresDao = coordenadoresDao;
	}

	public void setDisciplinasDao(DisciplinasDao disciplinasDao) {
		this.disciplinasDao = disciplinasDao;
	}

	public void setCursoDao(CursosDao cursoDao) {
		this.cursoDao = cursoDao;
	}

	public void setCadernetaDao(CadernetasDao cadernetaDao) {
		this.cadernetaDao = cadernetaDao;
	}

	public void setParametroSistemaDao(ParametroSistemaDao parametroSistemaDao) {
		this.parametroSistemaDao = parametroSistemaDao;
	}

}
