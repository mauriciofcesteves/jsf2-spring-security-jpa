package infraestrutura.util;

import infraestrutura.model.Disciplina;

public class DisciplinaUtilTest {

	public static Disciplina criar(Integer id) {
		Disciplina disciplina = new Disciplina();
		disciplina.setAtivo(true);
		disciplina.setCodigo(id+""+id+""+id+""+id);
		disciplina.setId(id);
		disciplina.setNome("Disciplina"+id);
		return disciplina;
	}
}
