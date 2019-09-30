
package com.skynetcorp.esb_skynetcorp.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "apellido", "clave", "estado", "idEmpleado", "nombre", "usuario" })
public class Empleado {

	@JsonProperty("apellido")
	private String apellido;
	@JsonProperty("clave")
	private String clave;
	@JsonProperty("estado")
	private String estado;
	@JsonProperty("idEmpleado")
	private Integer idEmpleado;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("usuario")
	private String usuario;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("apellido")
	public String getApellido() {
		return apellido;
	}

	@JsonProperty("apellido")
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@JsonProperty("clave")
	public String getClave() {
		return clave;
	}

	@JsonProperty("clave")
	public void setClave(String clave) {
		this.clave = clave;
	}

	@JsonProperty("estado")
	public String getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@JsonProperty("idEmpleado")
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	@JsonProperty("idEmpleado")
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	@JsonProperty("nombre")
	public String getNombre() {
		return nombre;
	}

	@JsonProperty("nombre")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@JsonProperty("usuario")
	public String getUsuario() {
		return usuario;
	}

	@JsonProperty("usuario")
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}