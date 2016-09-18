package infraestrutura.bean.infra;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Filtro extends HashMap<String, Object> {
	
	public static enum TipoFiltro{
		VALOR_EXATO, INTERVARLO, NEGACAO, LISTA, OU, MAIOR, MENOR;
	} 

	public static final String NULL_VALUE = "__NULL_VALUE__";

	private static final long serialVersionUID = 1L;

	private Map<String, Boolean> order;
	private int first;
	private int pageSize;

	public Filtro() {
		first = 0;
		pageSize = Integer.MAX_VALUE;
		order = new HashMap<String, Boolean>();
	}

	public Filtro(Filtro filter) {
		this();
		putAll(filter);
		order.putAll(filter.getOrder());
		first = filter.getFirst();
		pageSize = filter.getPageSize();
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Filtro addOrder(String attribute) {
		return addOrder(attribute, true);
	}

	public Filtro addOrder(String attribute, Boolean ascending) {
		if (StringUtils.isNotBlank(attribute)
				&& !"null".equalsIgnoreCase(attribute)) {
			order.put(attribute, ascending);
		}
		return this;
	}

	public Map<String, Boolean> getOrder() {
		return order;
	}

	public void setOrder(Map<String, Boolean> order) {
		this.order = order;
	}

	public Object put(String key, Object value) {
		return put(key,ValorFiltro.get(value));
	}
	
	public Object get(Object key){
		ValorFiltro valorFiltro = getValorFiltro((String)key);
		if(valorFiltro != null){
			return valorFiltro.getValor();
		}
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Object put(String key, ValorFiltro valor){
		if (valor == null || valor.getValor() == null || valor.getValor() instanceof String
				&& StringUtils.isBlank((String) valor.getValor())
				|| valor.getValor() instanceof Collection
				&& ((Collection) valor.getValor()).isEmpty() || valor.getValor() instanceof Map
				&& ((Map) valor.getValor()).isEmpty()) {
			remove(key);
			return null;
		}
		return super.put(key, valor);
	}

	// forces a null value
	public final Object putNullValue(String key) {
		return put(key, NULL_VALUE);
	}
	
	public ValorFiltro getValorFiltro(String key){
		return (ValorFiltro) super.get(key);
	}
	
	public ValorFiltro removeValorFiltro(String key){
		return (ValorFiltro) remove(key);
	}
	
	public static Filtro criarFiltro(String key, ValorFiltro value){
		Filtro filtro = new Filtro();
		filtro.put(key, value);
		return filtro;
	}
	
	public static Filtro criarFiltro(String key, Object value){
		return criarFiltro(key, ValorFiltro.get(value));
	}
	
	public static Filtro criarFiltro(String key, TipoFiltro tipo, Object valor){
		return criarFiltro(key, ValorFiltro.get(tipo, valor));
	}
	
	public static class ValorFiltro{
		TipoFiltro tipo;
		Object valor;
		
		public ValorFiltro(TipoFiltro tipo, Object valor){
			this.tipo = tipo;
			this.valor = valor;
		}
		
		public ValorFiltro(Object valor){
			this.tipo = TipoFiltro.VALOR_EXATO;
			this.valor = valor;
		}
		
		public TipoFiltro getTipo() {
			return tipo;
		}
		
		public void setTipo(TipoFiltro tipo) {
			this.tipo = tipo;
		}
		
		@SuppressWarnings("unchecked")
		public <V> V getValor() {
			return (V) valor;
		}
		
		public void setValor(Object valor) {
			this.valor = valor;
		}
		
		public static ValorFiltro get(Object valor){
			return new ValorFiltro(valor);
		}
		
		public static ValorFiltro get(TipoFiltro tipo, Object valor){
			return new ValorFiltro(tipo, valor);
		}
		
		@Override
		public String toString() {
			return tipo + " " + valor;
		}
		
	}

	
}
