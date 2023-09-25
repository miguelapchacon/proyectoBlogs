package com.blogs.backing;



import org.apache.log4j.Logger;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="LoginBean")
@ViewScoped
public class LoginBean {
	Logger log = Logger.getLogger(LoginBean.class);
	
}
	