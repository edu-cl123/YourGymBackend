package com.app.ApiRestFul.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "clase")
public class Clase implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String Name;
	
	@Column(name = "descripcion")
	private String description;
	
	@JsonIgnoreProperties(value = { "clase" }, allowSetters = true)
	@JoinTable(name = "cliente_clase", joinColumns = @JoinColumn(name = "id_clase"), inverseJoinColumns = @JoinColumn(name = "id_client"))
    @ManyToMany()
	private List<Client> client; 
	




	public Clase() {
		super();
	}

	public Clase(Long id, String name, String descripcion,List<Client> client) {
		super();
		this.id = id;
		Name = name;
		this.description = descripcion;

		this.client = new ArrayList<Client>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


	public String getDescripcion() {
		return description;
	}

	public void setDescripcion(String descripcion) {
		this.description = descripcion;
	}


	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Clase [id=" + id + ", Name=" + Name + ", description=" + description + ", client=" + client + "]";
	}



	

}

