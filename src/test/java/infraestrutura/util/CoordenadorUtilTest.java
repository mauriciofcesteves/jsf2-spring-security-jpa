package infraestrutura.util;

import infraestrutura.model.Coordenador;

public class CoordenadorUtilTest {

	public static Coordenador criar(Integer id) {
		Coordenador coordenador = new Coordenador();
		coordenador.setId(id);
		coordenador.setAtivo(true);
		coordenador.setEmail("mauricio.fc.esteves@gmail.com");
		coordenador.setMatricula(id+""+id+""+""+id+""+id);
		coordenador.setNome("Coordenador"+id);
		coordenador.setTelefone("1111");
		return coordenador;
	}
}
