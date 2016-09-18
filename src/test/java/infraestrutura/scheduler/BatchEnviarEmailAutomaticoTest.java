package infraestrutura.scheduler;

import infraestrutura.dao.GenericDaoTest;
import infraestrutura.util.PreCargaUtilTest;
import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BatchEnviarEmailAutomaticoTest extends GenericDaoTest {

	/**
	 * Apenas para testar se as pre cargas estao funcionando ok.
	 */
	@Test
	public void testarPreCargaPraBatch() {
		PreCargaUtilTest carga = new PreCargaUtilTest();
		carga.setProfessoresDao(professoresDao);
		carga.setCadernetaDao(cadernetaDao);
		carga.setCoordenadoresDao(coordenadoresDao);
		carga.setCursoDao(cursoDao);
		carga.setDisciplinasDao(disciplinasDao);
		carga.setParametroSistemaDao(parametroSistemaDao);

		LocalDate hoje = new LocalDate();
		carga.criarCargaNoSistema();
		carga.criarCargaCadernetaComProfessor(1, hoje);
		carga.criarCargaCadernetaComProfessor(2, hoje);
		carga.criarCargaCadernetaAguardandoCoordenador(3, hoje, hoje.plusDays(5));
		carga.criarCargaCadernetaComCoordenador(4, hoje, hoje.plusDays(5));
		carga.criarCargaCadernetaConcluida(5);

		BatchEnviarEmailAutomatico enviar = new BatchEnviarEmailAutomatico();
		enviar.setParametroSistemaService(parametroSistemaService);
		enviar.setCadernetasService(cadernetasService);
		enviar.run();
	}

	/**
	 * 1. Dado a Caderneta situaÁ„o 'CADERNETA_COM_PROFESSOR'. Dado que temos 5 professores. Dado que
	 * o Professor 1 retirou a caderneta hoje. Dado que o professor 2 retirou a caderneta a 2 dias atras. Dado que o professor 3 retirou a caderneta
	 * a 3 dias atras, dado que o professor 4 retirou a caderneta a 4 dias atras e dado que o professor 5 retirou a caderneta 16 dias atras.
	 * 2. Quando rodar o batch.
	 * 3. O sistema dever· enviar um e-mail para os professores 2, 4 e 5. Sendo que o professor 5 extrapolou, entao um e-mail tambem devera
	 * ser enviado para o coordenador do mesmo. (este teste deve ser verificado atraves de depuracao).
	 */
	@Test
	public void testarProfessorRetiraCaderneta() {
		PreCargaUtilTest carga = new PreCargaUtilTest();
		carga.setProfessoresDao(professoresDao);
		carga.setCadernetaDao(cadernetaDao);
		carga.setCoordenadoresDao(coordenadoresDao);
		carga.setCursoDao(cursoDao);
		carga.setDisciplinasDao(disciplinasDao);
		carga.setParametroSistemaDao(parametroSistemaDao);

		LocalDate hoje = new LocalDate();
		carga.criarCargaNoSistema();
		carga.criarCargaCadernetaComProfessor(1, hoje);
		carga.criarCargaCadernetaComProfessor(2, hoje.minusDays(2));
		carga.criarCargaCadernetaComProfessor(3, hoje.minusDays(3));
		carga.criarCargaCadernetaComProfessor(4, hoje.minusDays(4));
		carga.criarCargaCadernetaComProfessor(5, hoje.minusDays(16));

		//passo 2
		BatchEnviarEmailAutomatico enviar = new BatchEnviarEmailAutomatico();
		enviar.setParametroSistemaService(parametroSistemaService);
		enviar.setCadernetasService(cadernetasService);
		enviar.run();
	}
	
	/**
	 * 1. Dado que tenho varias Cadernetas com situaÁ„o 'CADERNETA_AGUARDANDO_COORDENADOR'. Dado que temos 3 professores. 
	 * Dado que tenho: 
	 * Caderneta 1, data final da disciplina 15 dias atr·s e entrega do Professor 1 foi 5 dias apÛs a data final. 
	 * Caderneta 2, data final da disciplina 25 dias atr·s e entrega do Professor 2 foi 10 dias apÛs a data final. 
	 * Caderneta 3, data final da disciplina 35 dias atr·s e entrega do Professor 3 foi 5 dias apÛs a data final. 
	 * 2. Quando rodar o batch.
	 * 3. O sistema dever· calcular quais coordenadores receberao emails. Dependendo da data de hoje,
	 * a resposta vai ser diferente. Entao, se o intervalo de envio de e-mails for a cada 2 dias,
	 * o sistema vai somar 2 dias desde a data final ate a data de hoje e se o somatorio coincidir
	 * com a data de hoje, ele enviara email ao coordenador. Porem, o professor 3 extrapolou a data dele
	 * e a data do coordenador, entao o sistema enviara email garantido para o coordenador 5.
	 */
	@Test
	public void testarCadernetaAguardandoCoordenador() {
		PreCargaUtilTest carga = new PreCargaUtilTest();
		carga.setProfessoresDao(professoresDao);
		carga.setCadernetaDao(cadernetaDao);
		carga.setCoordenadoresDao(coordenadoresDao);
		carga.setCursoDao(cursoDao);
		carga.setDisciplinasDao(disciplinasDao);
		carga.setParametroSistemaDao(parametroSistemaDao);
		
		LocalDate hoje = new LocalDate();
		carga.criarCargaNoSistema();
		carga.criarCargaCadernetaAguardandoCoordenador(1, hoje.minusDays(15), hoje.minusDays(10));
		carga.criarCargaCadernetaAguardandoCoordenador(2, hoje.minusDays(25), hoje.minusDays(15));
		carga.criarCargaCadernetaAguardandoCoordenador(3, hoje.minusDays(35), hoje.minusDays(30));
		
		//passo 2
		BatchEnviarEmailAutomatico enviar = new BatchEnviarEmailAutomatico();
		enviar.setParametroSistemaService(parametroSistemaService);
		enviar.setCadernetasService(cadernetasService);
		enviar.run();
	}
	
	@Test
	public void testarCadernetaComCoordenador() {
		PreCargaUtilTest carga = new PreCargaUtilTest();
		carga.setProfessoresDao(professoresDao);
		carga.setCadernetaDao(cadernetaDao);
		carga.setCoordenadoresDao(coordenadoresDao);
		carga.setCursoDao(cursoDao);
		carga.setDisciplinasDao(disciplinasDao);
		carga.setParametroSistemaDao(parametroSistemaDao);
		
		LocalDate hoje = new LocalDate();
		carga.criarCargaNoSistema();
		carga.criarCargaCadernetaComCoordenador(1, hoje.minusDays(15), hoje.minusDays(10));
		carga.criarCargaCadernetaComCoordenador(2, hoje.minusDays(25), hoje.minusDays(15));
		carga.criarCargaCadernetaComCoordenador(3, hoje.minusDays(35), hoje.minusDays(30));
		
		//passo 2
		BatchEnviarEmailAutomatico enviar = new BatchEnviarEmailAutomatico();
		enviar.setParametroSistemaService(parametroSistemaService);
		enviar.setCadernetasService(cadernetasService);
		enviar.run();
	}

	/**
	 * 1. Dado que tenho dois intervalos de datas iniciando no dia 01.01 at√© 16.01. Dado
	 * que a data de hoje √© 03.01. Dado que o intervalo informado √© de 2 dias.
	 * 2. Quando eu solicitar se o dia de hoje o sistema dever√° enviar um e-mail.
	 * 3. O sistema retornar√° true, pois hoje √© dia de enviar e-mail.
	 */
	@Test
	public void testarSeEnviarEmailRetornoTrue1() {
		//passo 1
		LocalDate hoje = new LocalDate(2013, 1, 3);
		LocalDate dataInicial = new LocalDate(2013, 1, 1);
		LocalDate dataFinal = new LocalDate(2013, 1, 16);
		Integer intervalo = 2;

		//passo 2 e 3
		BatchEnviarEmailAutomatico enviar = new BatchEnviarEmailAutomatico();
		Assert.assertTrue(enviar.isEnviarEmailHoje(hoje, dataInicial, dataFinal, intervalo));
	}
	
	/**
	 * 1. Dado que tenho dois intervalos de datas iniciando no dia 31.01 at√© 15.01. Dado
	 * que a data de hoje √© 04.01. Dado que o intervalo informado √© de 4 dias.
	 * 2. Quando eu solicitar se o dia de hoje o sistema dever√° enviar um e-mail.
	 * 3. O sistema retornar√° true, pois hoje √© dia de enviar e-mail.
	 */
	@Test
	public void testarSeEnviarEmailRetornoTrue2() {
		//passo 1
		LocalDate hoje = new LocalDate(2013, 2, 4);
		LocalDate dataInicial = new LocalDate(2013, 1, 31);
		LocalDate dataFinal = new LocalDate(2013, 2, 15);
		Integer intervalo = 4;
		
		//passo 2 e 3
		BatchEnviarEmailAutomatico enviar = new BatchEnviarEmailAutomatico();
		Assert.assertTrue(enviar.isEnviarEmailHoje(hoje, dataInicial, dataFinal, intervalo));
	}
}
