package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Job;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class JobsDao extends GenericPojoDao<Job> {

	private static final String DATA_PREVISTA = "dataPrevista";
	private static final String DATA_CADASTRO = "dataCadastro";
	private static final String DATA_CONCLUSAO = "dataConclusao";

	@Override
	public Class<Job> getPersistentClass() {
		return Job.class;
	}
	
	@Override
	public List<Job> listar() {
		Filtro filtro = new Filtro();
		filtro.addOrder("coordenador.nome", true);
		return consultarPorFiltro(filtro);
	}
	
	@SuppressWarnings("unchecked")
	public List<Job> consultarParaAgrupamento(HashMap<String, Object> filtro){
		List<Coordenador> coordenadores = (List<Coordenador>) filtro.get("coordenadores");

		LocalDate dataInicioPrevista = (LocalDate) filtro.get("dataInicioPrevista");
		LocalDate dataFimPrevista = (LocalDate) filtro.get("dataFimPrevista");

		LocalDate dataInicioConclusao = (LocalDate) filtro.get("dataInicioConclusao");
		LocalDate dataFimConclusao = (LocalDate) filtro.get("dataFimConclusao");
		
		LocalDate dataInicioCadastro = (LocalDate) filtro.get("dataInicioCadastro");
		LocalDate dataFimCadastro = (LocalDate) filtro.get("dataFimCadastro");
		
		Criteria criteria = criarCriteria(Job.class, null);
		
		if (dataInicioCadastro != null && dataFimCadastro != null) {
			criteria.add(Restrictions.between(DATA_CADASTRO, dataInicioCadastro, dataFimCadastro));
		}
		
		if (dataInicioPrevista != null && dataFimPrevista != null) {
			criteria.add(Restrictions.ge(DATA_PREVISTA, dataInicioPrevista));
			criteria.add(Restrictions.le(DATA_PREVISTA, dataFimPrevista));
		}
		
		if (dataInicioConclusao != null && dataFimConclusao != null) {
			criteria.add(Restrictions.ge(DATA_CONCLUSAO, dataInicioConclusao));
			criteria.add(Restrictions.le(DATA_CONCLUSAO, dataFimConclusao));
		}

		if (CollectionUtils.isNotEmpty(coordenadores)) {
			criteria.add(Restrictions.in("coordenador", coordenadores));
		}

		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Job> consultarParaRelatorio(Coordenador coordenador, LocalDate dataInicioCadastro, LocalDate dataFimCadastro){
		Criteria criteria = criarCriteria(Job.class, null);
		
		if (dataInicioCadastro != null && dataFimCadastro != null) {
			criteria.add(Restrictions.between(DATA_CADASTRO, dataInicioCadastro, dataFimCadastro));
		}else if(dataInicioCadastro != null){
			criteria.add(Restrictions.ge(DATA_CADASTRO, dataInicioCadastro));
		}else if(dataFimCadastro != null){
			criteria.add(Restrictions.le(DATA_CADASTRO, dataFimCadastro));
		}

		if (coordenador != null) {
			criteria.add(Restrictions.eq("coordenador", coordenador));
		}

		return criteria.list();
	}
	
}
