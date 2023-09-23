package com.blogs.backing;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class GenericBackingBean {
	
	public void addErrorMessage(String strHeader, String strMessage){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, strHeader + " - " +strMessage, strHeader + " - " +strMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void addErrorMessage(String strMessage){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, strMessage, strMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	 
	public void addSuccessMessage(String strMessage){
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, strMessage, "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public String prepareStringForLikeSt(String str){
		return "%" + ((str==null)?"":str) + "%";
	}
        
        
        public String paisInRequest(){
            FacesContext facesContext=FacesContext.getCurrentInstance();
            Map params = facesContext.getExternalContext().getRequestParameterMap();
            return ((String) params.get("pais"));
        }
        
	
}
