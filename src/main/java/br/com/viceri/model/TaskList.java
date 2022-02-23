package br.com.viceri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_task")
public class TaskList {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message= "Atributo tarefas não pode estar vazio!")
	private String tarefas;
	
	
	private String subTarefas;
	
	@NotBlank(message = "A tarefa deve ter uma prioridade entre baixo, médio ou alto")
	private String prioridade;
	
	@NotNull(message = "O usuário deve informar se a tarefa foi concluída ou não")
	private Boolean status;
	
	
	@ManyToOne
	@JsonIgnoreProperties("taskList")
	private Usuario usuario;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTarefas() {
		return tarefas;
	}

	public void setTarefas(String tarefas) {
		this.tarefas = tarefas;
	}

	public String getSubTarefas() {
		return subTarefas;
	}

	public void setSubTarefas(String subTarefas) {
		this.subTarefas = subTarefas;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
