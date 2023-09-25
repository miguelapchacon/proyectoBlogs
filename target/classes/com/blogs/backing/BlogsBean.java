package com.blogs.backing;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.blogs.backing.GenericBackingBean;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.blogs.repository.BlogsRepository;
import com.blogs.entity.Blogs;
import com.blogs.factory.SpringAppContextFactory;

@ManagedBean(name = "blogsBean")
@ViewScoped
public class BlogsBean extends GenericBackingBean {

	Logger log = Logger.getLogger(BlogsBean.class);

	private BlogsRepository blogsRepository;
	String message="";	
	private boolean validaciones=false;
	private List<Blogs> blogsList;
	private Blogs blogsEle;
	private Blogs blogsEdit;
	private Blogs blogsSelect;
	private boolean editing=false;
    
	@PostConstruct
	public void init() {
		try{				
		//	userPincipal = SecurityContextUtils.getUserPrincipal();		
			blogsEle= new Blogs();
			blogsEdit= new Blogs();
			blogsSelect= new Blogs();	    
			validaciones=false;	
			editing=false;	
	        message="";
	        blogsRepository=SpringAppContextFactory.getInstance().getBean(BlogsRepository.class);
	        searchBlogs();
	    	System.out.println("Cargando el init del blog");
		}catch(Exception e){
			System.out.println("Error en el init del blog: "+e);
		}
	}
	
    public void doDelete() {
		try {
			 RequestContext context = RequestContext.getCurrentInstance();
			  FacesContext context2 = FacesContext.getCurrentInstance(); 
			 System.out.println("Entrando al delete");
			 blogsRepository.delete(blogsEle);
			message="Blog ha sido eliminado satisfactoriamente";		
			super.addErrorMessage(message);
			clean();
			searchBlogs();
		//	 context2.addMessage(null, new FacesMessage("Successful", message) );
			context.execute("PF('wblogsList').clearFilters();");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			addErrorMessage(
					"Ocurrio un error al tratar de eliminar Blog");
		}
	}

	
    public void searchBlogs(){
		try{
			RequestContext context = RequestContext.getCurrentInstance();
			blogsList = new ArrayList<Blogs>();
			try {
				blogsList=blogsRepository.getAllBlogs();
			} catch (Exception e) {
				System.out.println("Exception: "+e);	
			}			
		}catch (Exception ex) {

			super.addErrorMessage("Ha ocurrido un error inesperado", ex.getMessage());
		}
	}
	
	public void saveBlog(){
		try{		
			System.out.println("Entro al save del blog");
			if(!editing){
				if(findExistingName(blogsEle.getTitle().toUpperCase())){
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

			System.out.println("Error en el save de param");
		}
	}
	
	
	public void save(){
		System.out.println("Entro al save del blog");
		message="";
		  FacesContext context2 = FacesContext.getCurrentInstance(); 
        RequestContext context = RequestContext.getCurrentInstance();
      	validaciones=true;
		if(editing){
			message="El Blog ha sido modificado satisfactoriamente";
		}else{
			message="El Blog ha sido creado satisfactoriamente";
		}
		System.out.println("blogsRepository.save(blogsEle)");
		try {
			blogsRepository.save(blogsEle);
		} catch (Exception e) {
			System.out.println("Error al guardar: "+e);
		}
		super.addErrorMessage("El lector ha sido creado satisfactoriamente"); 
	//	 context2.addMessage(null, new FacesMessage("Successful", message) );
		clean();
		searchBlogs();
		context.execute("PF('wblogsList').clearFilters();");
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
			int existe=blogsRepository.findExistingName(nombre);
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
		  blogsEle = blogsSelect;
	      editing = true;
	      System.out.println("Entro en editSelect: "+blogsSelect.getTitle());

	    }
	
	public void edit(){
		blogsEle=blogsSelect;
		editing=true;
	}
	
	public void clean(){
		  System.out.println("entro al clean del blog ");
		blogsEle=new Blogs();		
		blogsSelect=new Blogs();
		editing=false;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Blogs> getBlogsList() {
		return blogsList;
	}

	public void setBlogsList(List<Blogs> blogsList) {
		this.blogsList = blogsList;
	}

	public Blogs getBlogsEle() {
		return blogsEle;
	}

	public void setBlogsEle(Blogs blogsEle) {
		this.blogsEle = blogsEle;
	}

	public Blogs getBlogsEdit() {
		return blogsEdit;
	}

	public void setBlogsEdit(Blogs blogsEdit) {
		this.blogsEdit = blogsEdit;
	}

	public Blogs getBlogsSelect() {
		return blogsSelect;
	}

	public void setBlogsSelect(Blogs blogsSelect) {
		this.blogsSelect = blogsSelect;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
	
	
	
}
