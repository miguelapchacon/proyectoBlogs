package com.blogs.backing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
//import java.util.Objects;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.dao.InvalidDataAccessApiUsageException;
//import org.primefaces.json.JSONObject;
import org.codehaus.jettison.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.blogs.backing.GenericBackingBean;
import com.blogs.factory.SpringAppContextFactory;

import com.blogs.security.SecurityContextUtils;
import com.blogs.security.UserPrincipal;

import com.blogs.utils.RestFullClient;

@ManagedBean(name="LoginBean")
@ViewScoped
public class LoginBean extends GenericBackingBean{
	Logger log = Logger.getLogger(LoginBean.class);
	
}
	