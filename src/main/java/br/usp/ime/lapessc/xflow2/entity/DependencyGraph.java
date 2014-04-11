package br.usp.ime.lapessc.xflow2.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="dependency")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="OBJECT_TYPE", discriminatorType=DiscriminatorType.INTEGER)
public abstract class DependencyGraph<Client extends DependencyObject, Suppliers extends DependencyObject> {

	public final static int TASK_DEPENDENCY = 0;
	public final static int TASK_ASSIGNMENT = 1;
	public final static int COORD_REQUIREMENTS = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEPENDENCY_ID")
	private long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "associatedDependency", targetEntity = DependencySet.class)
	private Set<DependencySet<Client, Suppliers>> dependencies;
	
	@OneToOne
	@JoinColumn(name = "ENTRY_ID", nullable = false)
	private Commit associatedEntry;
	
	@ManyToOne
	@JoinColumn(name = "ANALYSIS_ID", nullable = false)
	private Analysis associatedAnalysis;
	
	@Column(name = "DIRECTED_DEPENDENCY")
	private boolean directedDependency;
	
	@Column(name = "DEPENDENCY_TYPE")
	private int type;
	
	public DependencyGraph() {
		// Empty constructor.
	}

	public DependencyGraph(boolean isDirected){
		this.directedDependency = isDirected;
	}
	
	public long getId() {
		return id;
	}

	public void setDependencies(Set<DependencySet<Client, Suppliers>> dependencies) {
		this.dependencies = dependencies;
		for (DependencySet<Client, Suppliers> dependencySet : dependencies) {
			dependencySet.setAssociatedDependency(this);
		}
	}

	public Set<DependencySet<Client, Suppliers>> getDependencies() {
		return dependencies;
	}

	public Commit getAssociatedEntry() {
		return associatedEntry;
	}

	public void setAssociatedEntry(final Commit associatedEntry) {
		this.associatedEntry = associatedEntry;
	}

	public Analysis getAssociatedAnalysis() {
		return associatedAnalysis;
	}

	public void setAssociatedAnalysis(final Analysis associatedAnalysis) {
		this.associatedAnalysis = associatedAnalysis;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isDirectedDependency() {
		return directedDependency;
	}

	public void setDirectedDependency(final boolean directedDependency) {
		this.directedDependency = directedDependency;
	}
}