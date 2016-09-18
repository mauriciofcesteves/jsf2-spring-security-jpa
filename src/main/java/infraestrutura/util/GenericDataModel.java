package infraestrutura.util;

import infraestrutura.model.Curso;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class GenericDataModel extends ListDataModel<Curso> implements SelectableDataModel<Curso>{

	public GenericDataModel() {
		super();
	}

	public GenericDataModel(List<Curso> list) {
		super(list);
	}

	@SuppressWarnings("unchecked")
	public Curso getRowData(String rowKey) {
		 List<Curso> cars = (List<Curso>) getWrappedData();  
         
	        for(Curso t : cars) {  
	            if(t.getClass().equals(Integer.valueOf(rowKey)))  
	                return t;  
	        }  
		
		return null;
	}

	public Object getRowKey(Curso t) {
		return t.getId();
	}

}
