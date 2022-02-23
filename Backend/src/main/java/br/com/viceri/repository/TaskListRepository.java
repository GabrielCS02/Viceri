package br.com.viceri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.viceri.model.TaskList;



@Repository
public interface TaskListRepository extends JpaRepository <TaskList, Long> {


	public Optional<TaskList>findByStatus(Boolean status);

	
	public List<TaskList>findAllByTarefasContainingIgnoreCase(String tarefas);
	
}
