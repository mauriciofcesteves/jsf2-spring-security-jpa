package infraestrutura.util;

import infraestrutura.model.Professor;

public class ProfessorUtilTest {

	public static Professor criar(Integer id) {
		Professor professor = new Professor();
		professor.setId(id);
		professor.setAtivo(true);
		professor.setEmail("mauricio.fc.esteves@gmail.com");
		professor.setMatricula(id+""+id+""+""+id+""+id);
		professor.setNome("Professor"+id);
		professor.setTelefone("1111");
		return professor;
	}
}
