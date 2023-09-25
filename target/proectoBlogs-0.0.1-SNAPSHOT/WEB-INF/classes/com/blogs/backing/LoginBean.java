package com.blogs.backing;



import org.apache.log4j.Logger;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import com.blogs.backing.GenericBackingBean;


@ManagedBean(name="LoginBean")
@ViewScoped
public class LoginBean extends GenericBackingBean{
	Logger log = Logger.getLogger(LoginBean.class);
	
}
	