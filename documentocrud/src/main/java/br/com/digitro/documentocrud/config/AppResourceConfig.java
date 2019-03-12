package br.com.digitro.documentocrud.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.com.digitro.documentocrud.exception.InvalidInputExceptionMapper;
@ApplicationPath("/*")
public class AppResourceConfig extends ResourceConfig {

	public AppResourceConfig() {
		packages("br.com.digitro.documentocrud.resource");
		//register(InvalidInputExceptionMapper.class);
	}
	
}
