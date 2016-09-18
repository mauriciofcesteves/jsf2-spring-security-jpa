package infraestrutura.dao;

import infraestrutura.bean.infra.Filtro;
import infraestrutura.bean.infra.Filtro.TipoFiltro;
import infraestrutura.bean.infra.Filtro.ValorFiltro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.EntityManagerImpl;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericDao<T> {

	public abstract Class<T> getPersistentClass();
	
	@PersistenceContext
	protected EntityManager entityManager;

	public String getNomeTabela(){
		return getPersistentClass().getSimpleName();
	}
	
	@Transactional
	public void salvar(T obj) {
		entityManager.merge(obj);
	}

	@Transactional
	public void excluir(T obj) {
		obj = entityManager.merge(obj);
		entityManager.remove(obj);
	}
	
	@SuppressWarnings("unchecked")
	public T consultarPorId(Integer id) {
		try{
			return (T) entityManager.createQuery("select t from "+ getNomeTabela() + " t where t.id =:id ").setParameter("id", id).getSingleResult();
		}catch(NoResultException ex){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return entityManager.createQuery("select t from " + getNomeTabela() + " t").getResultList();
	}

	public Session getSessao() {
	    Session session;
	    if (entityManager.getDelegate() instanceof EntityManagerImpl) {
	        EntityManagerImpl entityManagerImpl = (EntityManagerImpl) entityManager.getDelegate();
	        session = entityManagerImpl.getSession();
	    } else {
	        session = (Session) entityManager.getDelegate();
	    }
	    return session;

	}
	
	public List<T> consultarPorFiltro(Filtro filter) {
		return consultarPorClasseEFiltro(getPersistentClass(), filter);
	}
	
	@SuppressWarnings("unchecked")
	protected <F> List<F> consultarPorClasseEFiltro(Class<F> clazz,
			Filtro filter) {
		
		// now executing the count query
		Criteria countCriteria = criarCriteria(clazz, null);
		addFields(filter, countCriteria);
		countCriteria.setProjection(Projections.rowCount());
		
		int count = Integer.valueOf(countCriteria.uniqueResult().toString());

		List<F> list = new ArrayList<F>();
		if (count > 0) {
			// executing the normal query
			Criteria criteria = criarCriteria(clazz, null);
			Set<String> aliases = addFields(filter, criteria);
			addOrderBy(filter, criteria, aliases);
			criteria.setFirstResult(filter.getFirst());
			criteria.setMaxResults(filter.getPageSize());
			list = criteria.list();
		}
		return list;
	}
	
	protected Criteria criarCriteria(Class<?> clazz, String alias) {
		Criteria criteria;

		if (StringUtils.isNotBlank(alias)) {
			criteria = getSessao().createCriteria(clazz, alias);
		} else {
			criteria = getSessao().createCriteria(clazz);
		}
		return criteria;
	}
	
	protected Set<String> addFields(Filtro filtro, Criteria criteria) {
		Set<String> aliases = new HashSet<String>();

		for (Entry<String, Object> entry : filtro.entrySet()) {
			String attribute = entry.getKey();
			ValorFiltro valor = (ValorFiltro) entry.getValue();

			TipoFiltro tipoFiltro = valor.getTipo();

			Boolean isNegation =TipoFiltro.NEGACAO.equals(tipoFiltro);
			Boolean isExactValue =TipoFiltro.VALOR_EXATO.equals(tipoFiltro);
			Boolean isOrColumnCause = TipoFiltro.OU.equals(tipoFiltro);
			Boolean isIntervalo = TipoFiltro.INTERVARLO.equals(tipoFiltro);
			Boolean isMaior = TipoFiltro.MAIOR.equals(tipoFiltro);
			Boolean isMenor = TipoFiltro.MENOR.equals(tipoFiltro);
			
			Object value = valor.getValor();

			if (isValueNotEmpty(value)) {

				// preparing the alias
				String alias = getAlias(attribute);
				if (StringUtils.isNotBlank(alias) && !aliases.contains(alias)) {
					aliases.add(alias);
					criteria.createAlias(alias, alias);
				}
				if (!isOrColumnCause) {

					// adding the restriction
					Criterion criterion = null;
					if (value.equals(Filtro.NULL_VALUE)) {
						criterion = Restrictions.isNull(attribute);
					} else if (value instanceof String) {
						// verifies if the string must be exact
						if (isExactValue) {
							criterion = Restrictions.ilike(attribute,
									((String) value).toLowerCase(),
									MatchMode.EXACT);
						} else {
							// if not, searches anywhere
							criterion = Restrictions.ilike(attribute,
									((String) value).toLowerCase(),
									MatchMode.ANYWHERE);
						}
					} else if(isMaior || isMenor){
						if(isMaior){
							criterion = Restrictions.gt(attribute, value);
						}else{
							criterion = Restrictions.lt(attribute, value);
						}
					} else if (value instanceof Collection) {
						criterion = Restrictions.in(attribute,
								(Collection<?>) value);
					} else if(isIntervalo){
						Object[] valores = (Object[]) value; 
						criterion = Restrictions.between(attribute, valores[0],valores[1]);
					}else {
						criterion = Restrictions.eq(attribute, value);
					}
					// in case of a negation
					if (isNegation) {
						criterion = Restrictions.not(criterion);
					}
					criteria.add(criterion);
				} else {
					String[] result = attribute.split(";");
					attribute = result[0];
					String attribute2 = result[1];

					alias = getAlias(attribute2);
					if (StringUtils.isNotBlank(alias)
							&& !aliases.contains(alias)) {
						aliases.add(alias);
						criteria.createAlias(alias, alias);
					}

					criteria.add(Restrictions.or(
							Restrictions.eq(attribute, value),
							Restrictions.eq(attribute2, value)));
				}
			}
		}
		return aliases;
	}
	
	@SuppressWarnings("rawtypes")
	protected static boolean isValueNotEmpty(Object value) {
		boolean result;

		if (value instanceof String) {
			result = StringUtils.isNotBlank((String) value);
		}else if(value instanceof Collection){
			result = !((Collection) value).isEmpty();
		}else {
			result = value != null;
		}
		return result;
	}
	
	private String getAlias(String attribute) {
		String alias = null;
		int index = attribute.indexOf('.');

		if (index > 0) {
			alias = attribute.substring(0, index);
		}
		return alias;
	}
	
	private void addOrderBy(Filtro filter, Criteria criteria,
			Set<String> aliases) {
		addOrderBy(filter.getOrder(), criteria, aliases);
	}

	protected void addOrderBy(Map<String, Boolean> order, Criteria criteria,
			Set<String> aliases) {
		for (Entry<String, Boolean> entry : order.entrySet()) {
			String attribute = entry.getKey();
			Boolean ascending = entry.getValue();

			// preparing the alias
			String alias = getAlias(attribute);
			if (StringUtils.isNotBlank(alias) && !aliases.contains(alias)) {
				aliases.add(alias);
				criteria.createAlias(alias, alias, Criteria.LEFT_JOIN);
			}

			if (ascending) {
				criteria.addOrder(Order.asc(attribute));
			} else {
				criteria.addOrder(Order.desc(attribute));
			}
		}
	}
	
}
