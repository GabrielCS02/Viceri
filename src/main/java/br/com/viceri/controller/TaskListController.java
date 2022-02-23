package br.com.viceri.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.viceri.model.TaskList;
import br.com.viceri.repository.TaskListRepository;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskListController {

	@Autowired
	private TaskListRepository taskListRepository;
	
	@GetMapping
	public ResponseEntity <List<TaskList>> getAll(){
		return ResponseEntity.ok(taskListRepository.findAll());

	}
	
	@GetMapping("/tarefas{tarefas}")
	public ResponseEntity<List<TaskList>> GetByTarefas(@PathVariable String tarefas){
		return ResponseEntity.ok(taskListRepository.findAllByTarefasContainingIgnoreCase(tarefas));
	}
	
	@GetMapping
	public ResponseEntity<List<TaskList>> GetByStatus(@PathVariable Boolean status){
		return ResponseEntity.ok(taskListRepository.findAllByStatusContainingIgnoreCase(status));
	}
	
	@PostMapping
	public ResponseEntity<TaskList>post(@Valid @RequestBody TaskList taskList){
		return ResponseEntity.status(HttpStatus.CREATED).body(taskListRepository.save(taskList));
	}
	
	@PutMapping
	public ResponseEntity<TaskList>put(@Valid @RequestBody TaskList taskList){
		return ResponseEntity.status(HttpStatus.CREATED).body(taskListRepository.save(taskList));
	}
	
	@DeleteMapping("/{id}")
	  public void delete(@PathVariable long id) {
		taskListRepository.deleteById(id);
	  }
}
