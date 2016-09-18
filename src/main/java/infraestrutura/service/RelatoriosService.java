//package infraestrutura.service;
//
//import infraestrutura.bean.dto.CadernetaDTO;
//import infraestrutura.bean.dto.DashboardDTO;
//import infraestrutura.model.Caderneta;
//import infraestrutura.model.Documento;
//import infraestrutura.util.CadernetaUtil;
//import infraestrutura.util.ManagedBeanUtil;
//import infraestrutura.util.MessageResourcesConstants;
//
//import java.io.ByteArrayOutputStream;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExporterParameter;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//
//import org.joda.time.LocalDateTime;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RelatoriosService{
//
//	public Documento gerarRelatorioCadernetas(List<Caderneta> cadernetas, boolean relatorioProfessor) throws JRException{
//		Map<String, Object> parametros = new HashMap<String, Object>();
//		String titulo;
//
//		if(relatorioProfessor){
//			titulo = ManagedBeanUtil.getMessage(MessageResourcesConstants.MENU_RELATORIO_PROFESSOR);
//		}else{
//			titulo = ManagedBeanUtil.getMessage(MessageResourcesConstants.MENU_RELATORIO_COORDENADOR);
//		}
//
//		parametros.put("TITULO",titulo);
//
//		return gerarRelatorio(CadernetaUtil.getRelatorio("cadernetas"), titulo, parametros, getCadernetasDTO(cadernetas, relatorioProfessor));
//	}
//	
//	public Documento gerarRelatorioJobs(List<DashboardDTO> jobs) throws JRException{
//		return gerarRelatorio(CadernetaUtil.getRelatorio("jobs"), "jobs", new HashMap<String, Object>(), jobs);
//	}
//	
//	public Documento gerarRelatorio(String relatorioPath,String titulo, Map<String, Object> parametros, Collection<?> dados) throws JRException{
//		Documento documento = new Documento();
//		
//		JRDataSource dataSource = new JRBeanCollectionDataSource(dados);  
//		JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioPath, parametros, dataSource);
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		JRPdfExporter exporter = new JRPdfExporter();
//
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
//		exporter.exportReport();
//
//		byte[] bytes = baos.toByteArray();
//		documento.setConteudo(bytes);
//		documento.setNome(titulo+" "+new LocalDateTime().toString(CadernetaUtil.FORMATO_ARQUIVO)+".pdf");
//		
//		return documento;
//	}
//
//	private List<CadernetaDTO> getCadernetasDTO(List<Caderneta> cadernetas, boolean relatorioProfessor){
//		List<CadernetaDTO> dtos = new ArrayList<CadernetaDTO>();
//		for(Caderneta c : cadernetas){
//			CadernetaDTO dto = new CadernetaDTO();
//			dto.setCurso(c.getCurso().getCodigo() + " - " + c.getCurso().getNome());
//			dto.setDisciplina(c.getDisciplina().getCodigo() + " - " +c.getDisciplina().getNome());
//			dto.setTurma(c.getTurma());
//			dto.setCoordenador(c.getCoordenador().getNome());
//			dto.setProfessor(c.getProfessor().getNome());
//			if(relatorioProfessor){
//				dto.setDataPrevistaDevolucao(CadernetaUtil.getDataFormatada(c.getDataPrevistaDevolucaoProfessor()));
//				dto.setDentroPrazo(c.isProfessorNoPrazo());
//			}else{
//				dto.setDataPrevistaDevolucao(CadernetaUtil.getDataFormatada(c.getDataPrevistaDevolucaoCoordenador()));
//				dto.setDentroPrazo(c.isCoordenadorNoPrazo());
//			}
//
//			dtos.add(dto);
//		}
//		return dtos;
//	}
//
//}
