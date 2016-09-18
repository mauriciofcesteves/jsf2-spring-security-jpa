package infraestrutura.util;

import infraestrutura.model.Coordenador;
import infraestrutura.model.Curso;
import infraestrutura.model.Disciplina;

public class CursoUtilTest {

	public static Curso criar(Integer id, Coordenador coordenador, Disciplina disciplina) {
		Curso curso = new Curso();
		curso.setAtivo(true);
		curso.setCodigo(id+""+id+""+id+""+id);
		curso.setCoordenador(coordenador);
		curso.getDisciplinas().add(disciplina);
		curso.setId(id);
		curso.setNome("Curso"+id);
		return curso;
	}
}
