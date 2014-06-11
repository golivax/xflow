package br.usp.ime.lapessc.xflow2.entity;

import javax.persistence.Entity;

@Entity(name="task_dependency")
public class TaskDependencyGraph extends DependencyGraph<FileDependencyObject, FileDependencyObject>  {
	
	public TaskDependencyGraph(boolean isDirected){
		super(isDirected);
		this.setType(DependencyGraphType.TASK_DEPENDENCY.getValue());
	}
	
}
