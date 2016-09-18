package infraestrutura.bean.infra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelecaoPojo<K> extends HashMap<K, Object> implements Map<K, Object>{

	private static final long serialVersionUID = 1L;
	
	public List<K> keySetSelected() {
		List<K> objetosSelecionados = new ArrayList<K>();
		for(Entry<K, Object > value : entrySet()){
			Boolean selected = false;
			if(value.getValue() instanceof String){
				selected = Boolean.valueOf((String)value.getValue());
			}else if(value.getValue() instanceof Boolean){
				selected = (Boolean)value.getValue();
			}
			if(selected){
				objetosSelecionados.add(value.getKey());
			}
		}
		return objetosSelecionados;
	}
	
	public List<K> desmarcarTodos() {
		List<K> objetosSelecionados = new ArrayList<K>();
		for(Entry<K, Object > value : entrySet()){
			value.setValue(false);
		}
		return objetosSelecionados;
	} 

}
