package com.blogs.backing;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.blogs.backing.GenericBackingBean;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.blogs.repository.ReadersBlogsRepository;
import com.blogs.repository.ReadersRepository;
import com.blogs.entity.Blogs_Readers;
import com.blogs.entity.Readers;
import com.blogs.factory.SpringAppContextFactory;

@ManagedBean(name = "readersBlogsBean")
@ViewScoped
public class ReadersBlogsBean extends GenericBackingBean {

	private static final Logger LOG=Logger.getLogger(ReadersBlogsBean.class);

//	private UserPrincipal userPincipal;
	private ReadersBlogsRepository readersBlogsRepository;
	String message="";	
	private boolean validaciones=false;
	private List<Blogs_Readers> readersBlogsList;
	private Blogs_Readers readersBlogsEle;
	private Blogs_Readers readersblogsEdit;
	private Blogs_Readers readersBlogsSelect;
	private boolean editing=false;
    
	@PostConstruct
	public void init() {
		try{				
		//	userPincipal = SecurityContextUtils.getUserPrincipal();		
			readersBlogsEle= new Blogs_Readers();
			readersblogsEdit= new Blogs_Readers();
			 readersBlogsSelect= new Blogs_Readers();	        
			validaciones=false;	
			editing=false;	
	        message="";
	        readersBlogsRepository=SpringAppContextFactory.getInstance().getBean(ReadersBlogsRepository.class);
	        searchReaders();
		}catch(Exception e){
			System.out.println("Error en el init: "+e);
		}
	}
	
    public void doDelete() {
		try {
			 RequestContext context = RequestContext.getCurrentInstance();
			  FacesContext context2 = FacesContext.getCurrentInstance(); 
			 System.out.println("Entrando al delete");
			 readersBlogsRepository.delete(readersBlogsEle);
			message="Reader ha sido eliminado satisfactoriamente";		
			super.addErrorMessage(message);
			clean();
			searchReaders();
		//	 context2.addMessage(null, new FacesMessage("Successful", message) );
			context.execute("PF('wreadersBlogsList').clearFilters();");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(
					"Ocurrio un error al tratar de eliminar el plan de financiamiento por perfil de crédito");
		}
	}

	
    public void searchReaders(){
		try{
			RequestContext context = RequestContext.getCurrentInstance();
			readersBlogsList = new ArrayList<Blogs_Readers>();
			try {
				readersBlogsList=readersBlogsRepository.getAllReadersBlogs();
			    //    context.execute("PF('filterPl').clearFilters();");
			} catch (Exception e) {
				System.out.println("Exception: "+e);	
			}			
		}catch (Exception ex) {
			LOG.error("Error al cargar las invitaciones de tarjeta",ex);
			super.addErrorMessage("Ha ocurrido un error inesperado", ex.getMessage());
		}
	}
	
	public void saveReaderBlogs(){
		try{		
			System.out.println("Entro al save Reader Blog");
			
				save();
			
		}catch(Exception e){
			LOG.log(Level.ERROR, "Error en el save de param", e);
			System.out.println("Error en el save de param");
		}
	}
	
	
	public void save(){
		System.out.println("Entro al save del blog Reader");
		message="";
		FacesContext context2 = FacesContext.getCurrentInstance(); 
        RequestContext context = RequestContext.getCurrentInstance();
      	validaciones=true;
		if(editing){
			message="El lector por blog ha sido modificado satisfactoriamente";
		}else{
			message="El lector por blog ha sido creado satisfactoriamente";
		}

		try {
			readersBlogsRepository.save(readersBlogsEle);
		} catch (Exception e) {
			System.out.println("Error al guardar: "+e);
		}
		super.addErrorMessage("El lector ha sido creado satisfactoriamente"); 
	//	 context2.addMessage(null, new FacesMessage("Successful", message) );
		clean();
		searchReaders();
		context.execute("PF('wreadersBlogsList').clearFilters();");
	  //  context.execute("PF('filterPl').clearFilters();");
	}
	
    public void refrescarMensaje(){
    	System.out.println("ejecutando refrescarTabla");
    	 if(validaciones){
    		 addSuccessMessage(message);
    	 }
    	 validaciones=false;
    }
	
	
	public boolean findExistingName(String nombre){
		try{
			System.out.println("estamos en: findExistingName");
			int existe=0;
			System.out.println("Existen: "+existe);
			if(existe==1){				
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			
			System.out.println("Error en el findExisting param "+e);
			return false;
		}
	}
//	public void onRowSelect(SelectEvent evt){
//		
//		if(customerWaveDetailSelected == null){
//			super.addErrorMessage("No se ha podido seleccionar la fila", "No se ha podido seleccionar la fila");
//			return;
//		}	
//	}
	public void onRowSelect(SelectEvent evt){
		  readersBlogsEle = readersBlogsSelect;
	      editing = true;
	      System.out.println("Entro en editSelect: "+readersBlogsSelect.getIdBlogsReader());

	    }
	
	public void edit(){
		readersBlogsEle=readersBlogsSelect;
		editing=true;
	}
	
	public void clean(){
		readersBlogsEle=new Blogs_Readers();		
		readersBlogsSelect=new Blogs_Readers();
		editing=false;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isValidaciones() {
		return validaciones;
	}

	public void setValidaciones(boolean validaciones) {
		this.validaciones = validaciones;
	}

	public List<Blogs_Readers> getReadersBlogsList() {
		return readersBlogsList;
	}

	public void setReadersBlogsList(List<Blogs_Readers> readersBlogsList) {
		this.readersBlogsList = readersBlogsList;
	}

	public Blogs_Readers getReadersBlogsEle() {
		return readersBlogsEle;
	}

	public void setReadersBlogsEle(Blogs_Readers readersBlogsEle) {
		this.readersBlogsEle = readersBlogsEle;
	}

	public Blogs_Readers getReadersblogsEdit() {
		return readersblogsEdit;
	}

	public void setReadersblogsEdit(Blogs_Readers readersblogsEdit) {
		this.readersblogsEdit = readersblogsEdit;
	}

	public Blogs_Readers getReadersBlogsSelect() {
		return readersBlogsSelect;
	}

	public void setReadersBlogsSelect(Blogs_Readers readersBlogsSelect) {
		this.readersBlogsSelect = readersBlogsSelect;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	
	
	
}
