package br.com.digitro.documentocrud.server;

import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;

import br.com.digitro.documentocrud.config.AppResourceConfig;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletInfo;

public class UndertowServer {

	public static void main(String[] args) throws ServletException {
		
		ServletInfo servletContainer = Servlets.servlet("jerseyServlet", ServletContainer.class);
		servletContainer.setLoadOnStartup(1)
			.addInitParam("javax.ws.rs.Application", AppResourceConfig.class.getName())
			.addMapping("/*");
		
		DeploymentInfo servletBuilder = Servlets.deployment();
		servletBuilder.setClassLoader(UndertowServer.class.getClassLoader())
			.setContextPath("/doc")
			.setDeploymentName("doc.war")
			.addServlets(servletContainer);
		
		DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
		manager.deploy();
		
		Undertow server = Undertow.builder()
				.addHttpListener(8080, "localhost")
				.setHandler(manager.start())
				.build();
		
		server.start();
		
	}
	
}
