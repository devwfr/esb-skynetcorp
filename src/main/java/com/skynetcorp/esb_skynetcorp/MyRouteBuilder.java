package com.skynetcorp.esb_skynetcorp;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java8 DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {
	private static final Object[] OBJECTS = new Object[] { "A string", new Integer(1), new Double(1.0) };

	private int index;

	/**
	 * Let's configure the Camel routing rules using Java code...
	 */
	public void configure() {

		/**
		 * ***********************************************************************
		 * Configuracion de Servicio REST expuesto
		 */

		restConfiguration()
			.apiContextListing(true)
			.component("jetty")
			.enableCORS(true)
			.port(10000);
		
		/*
		 * ***********************************************************************
		 * Routing Servicio get All Empleados
		 */

		rest("esb-skynet")
			.get("/empleados")
			.produces("application/json")
			.to("direct:processGetEmpleados");

		from("direct:processGetEmpleados")
			.setHeader("headerRq", constant("1"))
			.setHeader("serviceID", constant("1"))
			.setHeader("Accept", constant("application/json"))
			.to("http://localhost:8180/OMS/Empleados/v1/empleados?bridgeEndpoint=true");
		
		/*
		 * ***********************************************************************
		 * Routing Servicio get Empleado por id
		 */
		
		rest("esb-skynet")
			.get("/empleado/{idEmpleado}")
			.produces("application/json")
			.to("direct:processGetEmpleadoPorId");

		from("direct:processGetEmpleadoPorId")
			.setHeader("headerRq", constant("1"))
			.setHeader("serviceID", constant("1"))
			.setHeader("Accept", constant("application/json"))
			.setHeader(Exchange.HTTP_PATH, simple("${header.idEmpleado}"))
			.to("http://localhost:8180/OMS/Empleados/v1/empleado?bridgeEndpoint=true");	
		
		/*
		 * ***********************************************************************
		 * Routing Servicio autenticacion
		 */
		
		rest("esb-skynet")
			.get("/empleado/autenticacion/{usuario}/{pass}")
			.produces("application/json")
			.to("direct:processAutenticacionEmpl");

		from("direct:processAutenticacionEmpl")
			.setHeader("headerRq", constant("1"))
			.setHeader("serviceID", constant("1"))
			.setHeader("Accept", constant("application/json"))			
			.setHeader(Exchange.HTTP_PATH, simple("${header.usuario}/${header.pass}"))
			.to("http://localhost:8180/OMS/Empleados/v1/empleado/authenticationService?bridgeEndpoint=true");	
		

		/*
		 * ***********************************************************************
		 * Routing Servicio get Rol por idEmpleado
		 */
		
		rest("esb-skynet")
			.get("/rol/{idEmpleado}")
			.produces("application/json")
			.to("direct:processGetRolPorId");

		from("direct:processGetRolPorId")
			.setHeader("headerRq", constant("1"))
			.setHeader("serviceID", constant("1"))
			.setHeader("Accept", constant("application/json"))
			.setHeader(Exchange.HTTP_PATH, simple("${header.idEmpleado}"))
			.to("http://localhost:8180/OMS/Empleados/v1/rol?bridgeEndpoint=true");	
			
		
		
		/*
		 * ***********************************************************************
		 * Routing Servicio Post Crear Empleado
		 */

		rest("esb-skynet")
			.post("/empleado")
			.produces("application/json")
			.to("direct:processCrearEmpleados");

		from("direct:processCrearEmpleados")
			.setHeader("headerRq", constant("1"))
			.setHeader("serviceID", constant("1"))
			.setHeader("Accept", constant("application/json"))			
			.setHeader(Exchange.HTTP_METHOD, simple("POST"))
			.to("http://localhost:8180/OMS/Empleados/v1/empleado?bridgeEndpoint=true");
		
		
		/*
		 * ***********************************************************************
		 * Routing Servicio PUT Actualizar Empleado
		 */

		rest("esb-skynet")
			.put("/empleado/{idEmpleado}")
			.produces("application/json")
			.to("direct:processActualizarEmpleados");

		from("direct:processActualizarEmpleados")
			.setHeader("headerRq", constant("1"))
			.setHeader("serviceID", constant("1"))
			.setHeader("Accept", constant("application/json"))
			.setHeader(Exchange.HTTP_PATH, simple("${header.idEmpleado}"))
			.setHeader(Exchange.HTTP_METHOD, simple("PUT"))
			.to("http://localhost:8180/OMS/Empleados/v1/empleado?bridgeEndpoint=true");
		

	}

	private Object randomBody(Message m) {
		return OBJECTS[m.getHeader("index", Integer.class)];
	}
}
