package infraestrutura.util;

import infraestrutura.model.Caderneta;
import infraestrutura.model.Curso;
import infraestrutura.model.Professor;
import infraestrutura.model.SituacaoCaderneta;

import org.joda.time.LocalDate;

public class CadernetaUtilTest {

	public static Caderneta criarCadernetaComProfessor(Integer id, Curso curso, LocalDate dataFinalDisciplina, Professor professor) {
		Caderneta caderneta = new Caderneta();
		caderneta.setAtivo(true);
		caderneta.setCurso(curso);
		caderneta.setDataFinalDisciplina(dataFinalDisciplina);
		caderneta.setDataRetiradaProfessor(dataFinalDisciplina);
		caderneta.setDataPrevistaDevolucaoProfessor(dataFinalDisciplina.plusDays(15));
		caderneta.setDisciplina(curso.getDisciplinas().get(0));
		caderneta.setProfessor(professor);
		caderneta.setProtocolo(new Long(id));
		caderneta.setCoordenador(curso.getCoordenador());
		caderneta.setSituacao(SituacaoCaderneta.CADERNETA_COM_PROFESSOR);
		return caderneta;
	}
	
	public static Caderneta criarAguardandoCoordenador(Integer id, Curso curso, Professor professor, LocalDate dataFinalDisciplina, LocalDate dataDevolucaoProfessor) {
		Caderneta caderneta = new Caderneta();
		caderneta.setAtivo(true);
		caderneta.setCurso(curso);
		caderneta.setDataFinalDisciplina(dataFinalDisciplina);
		caderneta.setDataPrevistaDevolucaoProfessor(dataFinalDisciplina.plusDays(15));
		caderneta.setDataDevolucaoProfessor(dataDevolucaoProfessor);
		caderneta.setDataPrevistaDevolucaoCoordenador(dataFinalDisciplina.plusDays(30));
		caderneta.setDisciplina(curso.getDisciplinas().get(0));
		caderneta.setProfessor(professor);
		caderneta.setProtocolo(new Long(id));
		caderneta.setCoordenador(curso.getCoordenador());
		caderneta.setSituacao(SituacaoCaderneta.AGUARDANDO_COORDENADOR);
		return caderneta;
	}
	
	public static Caderneta criarCadernetaComCoordenador(Integer id, Curso curso, Professor professor, LocalDate dataFinalDisciplina, LocalDate dataDevolucaoProfessor) {
		Caderneta caderneta = new Caderneta();
		caderneta.setAtivo(true);
		caderneta.setCurso(curso);
		caderneta.setDataFinalDisciplina(dataFinalDisciplina);
		caderneta.setDataPrevistaDevolucaoProfessor(dataFinalDisciplina.plusDays(15));
		caderneta.setDataDevolucaoProfessor(dataDevolucaoProfessor);
		caderneta.setDataPrevistaDevolucaoCoordenador(dataFinalDisciplina.plusDays(30));
		caderneta.setDataRetiradaCoordenador(dataDevolucaoProfessor.plusDays(5));
		caderneta.setDisciplina(curso.getDisciplinas().get(0));
		caderneta.setProfessor(professor);
		caderneta.setProtocolo(new Long(id));
		caderneta.setCoordenador(curso.getCoordenador());
		caderneta.setSituacao(SituacaoCaderneta.CADERNETA_COM_COORDENADOR);
		return caderneta;
	}
	
	public static Caderneta criarCadernetaConcluida(Integer id, Curso curso, Professor professor) {
		Caderneta caderneta = new Caderneta();
		caderneta.setAtivo(true);
		caderneta.setCurso(curso);
		caderneta.setDisciplina(curso.getDisciplinas().get(0));
		caderneta.setProfessor(professor);
		caderneta.setProtocolo(new Long(id));
		caderneta.setCoordenador(curso.getCoordenador());
		caderneta.setSituacao(SituacaoCaderneta.CONCLUIDO);
		caderneta.setDataFinalDisciplina(new LocalDate());
		return caderneta;
	}
}
