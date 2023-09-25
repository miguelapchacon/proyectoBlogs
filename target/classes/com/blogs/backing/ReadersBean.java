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

import com.blogs.repository.ReadersRepository;
import com.blogs.entity.Readers;
import com.blogs.factory.SpringAppContextFactory;

@ManagedBean(name = "readersBean")
@ViewScoped
public class ReadersBean extends GenericBackingBean {

	private static final Logger LOG=Logger.getLogger(ReadersBean.class);

//	private UserPrincipal userPincipal;
	private ReadersRepository readersRepository;
	String message="";	
	private boolean validaciones=false;
	private List<Readers> readersList;
	private Readers readersEle;
	private Readers readersEdit;
	private Readers readersSelect;
	private boolean editing=false;
    private boolean deletePlan=false;
    private String usuario;
    
	@PostConstruct
	public void init() {
		try{				
		//	userPincipal = SecurityContextUtils.getUserPrincipal();		
			readersEle= new Readers();
			readersEdit= new Readers();
			 readersSelect= new Readers();
	        deletePlan=false;
			validaciones=false;	
			editing=false;	
	        message="";
	        readersRepository=SpringAppContextFactory.getInstance().getBean(ReadersRepository.class);
	        System.out.println("Cargando init del reader");
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
			 readersRepository.delete(readersEle);
			message="Reader ha sido eliminado satisfactoriamente";		
			super.addErrorMessage(message);
			clean();
			searchReaders();
		//	 context2.addMessage(null, new FacesMessage("Successful", message) );
			context.execute("PF('wreadersList').clearFilters();");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(
					"Ocurrio un error al tratar de eliminar el plan de financiamiento por perfil de crédito");
		}
	}

	
    public void searchReaders(){
		try{
			RequestContext context = RequestContext.getCurrentInstance();
			readersList = new ArrayList<Readers>();
			try {
				readersList=readersRepository.getAllReaders();
			    //    context.execute("PF('filterPl').clearFilters();");
			} catch (Exception e) {
				System.out.println("Exception: "+e);	
			}			
		}catch (Exception ex) {
			LOG.error("Error al cargar las invitaciones de tarjeta",ex);
			super.addErrorMessage("Ha ocurrido un error inesperado", ex.getMessage());
		}
	}
	
	public void saveReader(){
		try{		
			System.out.println("Entro al save Reader");
			if(!editing){
				System.out.println("Entro 1");
				System.out.println("VALOR DEL NAME: "+readersEle.getNameReader().toUpperCase());
				if(findExistingName(readersEle.getNameReader().toUpperCase())){
					System.out.println("Entro 2");
					save();
				}else{
					addErrorMessage("Este nombre de lector ya existe, ingresa uno diferente");
					System.out.println("Este nombre de lector ya existe, ingresa uno diferente");
				}
			}else{
				System.out.println("Entro 3");
				save();
			}
		}catch(Exception e){
			LOG.log(Level.ERROR, "Error en el save de param", e);
			System.out.println("Error en el save de param");
		}
	}
	
	
	public void save(){
		System.out.println("Entro al save");
		message="";
		  FacesContext context2 = FacesContext.getCurrentInstance(); 
        RequestContext context = RequestContext.getCurrentInstance();
      	validaciones=true;
		if(editing){
			message="El lector ha sido modificado satisfactoriamente";
		}else{
			message="El lector ha sido creado satisfactoriamente";
		}
		System.out.println("readersRepository.save(readersEle)");
		try {
			readersRepository.save(readersEle);
		} catch (Exception e) {
			System.out.println("Error al guardar: "+e);
		}
		super.addErrorMessage("El lector ha sido creado satisfactoriamente"); 
	//	 context2.addMessage(null, new FacesMessage("Successful", message) );
		clean();
		searchReaders();
		context.execute("PF('wreadersList').clearFilters();");
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
			int existe=readersRepository.findExistingName(nombre);
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
	
	
	public String validar() {
		String valor="";
		try {
			String nombre=readersRepository.getuser(usuario);
			if (usuario.equals(nombre)) {				 
				valor="exito";				 
				} else {				 
					valor= "fallo";				 
				}			
		} catch (Exception e) {
			System.out.println("Error un error al validar "+e);
			 valor="error";
			return valor;
		}
	
		return valor;
	
	
	}
//	public void onRowSelect(SelectEvent evt){
//		
//		if(customerWaveDetailSelected == null){
//			super.addErrorMessage("No se ha podido seleccionar la fila", "No se ha podido seleccionar la fila");
//			return;
//		}	
//	}
	public void onRowSelect(SelectEvent evt){
		  readersEle = readersSelect;
	      editing = true;
	      System.out.println("Entro en editSelect: "+readersSelect.getNameReader());

	    }
	
	public void edit(){
		readersEle=readersSelect;
		editing=true;
	}
	
	public void clean(){
		readersEle=new Readers();		
		readersSelect=new Readers();
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



	public List<Readers> getReadersList() {
		return readersList;
	}



	public void setReadersList(List<Readers> readersList) {
		this.readersList = readersList;
	}



	public Readers getReadersEle() {
		return readersEle;
	}



	public void setReadersEle(Readers readersEle) {
		this.readersEle = readersEle;
	}



	public Readers getReadersEdit() {
		return readersEdit;
	}



	public void setReadersEdit(Readers readersEdit) {
		this.readersEdit = readersEdit;
	}



	public boolean isEditing() {
		return editing;
	}



	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public Readers getReadersSelect() {
		return readersSelect;
	}

	public void setReadersSelect(Readers readersSelect) {
		this.readersSelect = readersSelect;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	
	
}
