package br.com.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.jettison.JettisonFeature;
/*
 * Classe singleton que configura o Jersey
 */
public class MyApplication extends Application {
	
	@Override
	public Set<Object> getSingletons(){
		Set<Object> singletons = new  HashSet<>();
		singletons.add(new JettisonFeature());
		return singletons;
		
	}
	
	@Override
	public Map<String, Object> getProperties(){
		Map<String, Object> properties = new  HashMap<>();
		properties.put("jersey.config.server.provider.packages", "br.com.inicio");
		return properties;
		
	}

}
