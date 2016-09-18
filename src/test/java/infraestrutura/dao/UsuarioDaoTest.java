package infraestrutura.dao;

import junit.framework.Assert;
import infraestrutura.model.Perfil;
import infraestrutura.model.Usuario;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UsuarioDaoTest extends GenericDaoTest {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PerfilDao perfilDao;

	/**
	 * 1. Dado que possuo um login "teste".<br>
	 * 2. Quando for consultar o Usuario com o login "teste". <br>
	 * 3. O sistema deverá retornar o Usuario de login 'teste'. 
	 */
	@Test
	public void testarConsultarPorLoginExistente() {
		Usuario usuario = new Usuario();
		usuario.setLogin("teste");
		usuario.setSenha("teste");
		
		Perfil perfil = new Perfil();
		perfil.setNome("ADMINISTRADOR");
		perfilDao.salvar(perfil);
		
		usuario.getPerfis().add(perfil);
		
		usuarioDao.salvar(usuario);

		String login = "teste";

		Usuario retorno = usuarioDao.consultarPorLogin(login);
		Assert.assertNotNull(retorno);
		Assert.assertEquals(login, retorno.getLogin());
		Assert.assertFalse(retorno.getPerfis().isEmpty());
	}
}
