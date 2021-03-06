package br.usp.ime.lapessc.xflow2.presentation.visualizations.graph;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import br.usp.ime.lapessc.xflow2.entity.Metrics;
import br.usp.ime.lapessc.xflow2.exception.persistence.DatabaseException;
import br.usp.ime.lapessc.xflow2.presentation.visualizations.Visualization;
import br.usp.ime.lapessc.xflow2.presentation.visualizations.VisualizationControl;
import br.usp.ime.lapessc.xflow2.presentation.visualizations.VisualizationRenderer;
import br.usp.ime.lapessc.xflow2.presentation.visualizations.graph.controls.DependencyChooserControl;
import br.usp.ime.lapessc.xflow2.presentation.visualizations.graph.controls.LayoutChooserControl;

@SuppressWarnings("unchecked")
public class GraphVisualization extends Visualization {

	private final VisualizationRenderer<GraphVisualization>[] renderers = new VisualizationRenderer[]{new GraphRenderer()};
	private final VisualizationControl<GraphVisualization>[] northControls = new VisualizationControl[]{new LayoutChooserControl(), new DependencyChooserControl()};
	
	public GraphVisualization(Metrics metricsSession) {
		super(metricsSession);
	}
	
	@Override
	public JComponent buildVisualizationGUI() throws DatabaseException {
		for (VisualizationRenderer<GraphVisualization> renderer : renderers) {
			renderer.composeVisualization(this.visualizationGUIComponent);
		}
		
		JPanel northPanel = new JPanel();
		this.visualizationGUIComponent.add(northPanel, BorderLayout.NORTH);
		for (VisualizationControl<GraphVisualization> control : northControls) {
			control.buildControlGUI(northPanel);
		}
		
		return this.visualizationGUIComponent;
	}

	@Override
	public String getName() {
		return "Graph Visualization";
	}

	@Override
	public void toggleQualitySettings(int qualityParameter) {
		switch (qualityParameter) {
		case VisualizationRenderer.HIGH_QUALITY:
			for (VisualizationRenderer<GraphVisualization> renderer : this.renderers) {
				renderer.setHighQuality();
			}			
			break;

		case VisualizationRenderer.LOW_QUALITY:
			for (VisualizationRenderer<GraphVisualization> renderer : this.renderers) {
				renderer.setLowerQuality();
			}			
			break;
		}
	}

	@Override
	public void updateDisplayedData(int inferiorLimit, int superiorLimit) throws DatabaseException {
		for (VisualizationRenderer<GraphVisualization> renderer : this.renderers) {
			renderer.updateVisualizationLimits(inferiorLimit, superiorLimit);
		}
	}

	public VisualizationRenderer<GraphVisualization>[] getRenderers() {
		return renderers;
	}

	@Override
	public void updateAuthorsVisibility(String selectedAuthorsQuery) throws DatabaseException {
		// Not applicable.
	}

}
