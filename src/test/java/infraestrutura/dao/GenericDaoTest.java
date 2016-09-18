package infraestrutura.dao;

import infraestrutura.service.CadernetasService;
import infraestrutura.service.ParametroSistemaService;

import org.springframework.beans.factory.annotation.Autowired;

public class GenericDaoTest {

	public static final String EXPECTED_ERROR = "Era esperado um erro de neg�cio.";
	public static final String UNEXPECTED_ERROR = "N�o era esperado um erro de neg�cio.";
	
	@Autowired
	protected ProfessoresDao professoresDao;
	
	@Autowired
	protected CoordenadoresDao coordenadoresDao;
	
	@Autowired
	protected DisciplinasDao disciplinasDao;
	
	@Autowired
	protected CursosDao cursoDao;
	
	@Autowired
	protected CadernetasDao cadernetaDao;
	
	@Autowired
	protected ParametroSistemaDao parametroSistemaDao;
	
	@Autowired
	protected PerfilDao perfilDao;
	
	@Autowired
	protected UsuarioDao usuarioDao;
	
	@Autowired
	protected ParametroSistemaService parametroSistemaService;
	
	@Autowired
	protected CadernetasService cadernetasService;
}