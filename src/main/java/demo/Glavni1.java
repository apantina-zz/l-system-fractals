package demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Demonstration program for the {@link LSystemBuilderImpl} class.
 * 
 * @author 0036502252
 *
 */
public class Glavni1 {
	/**
	 * Main method. Displays an {@link LSystem} in a window.
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(createKochCurve(LSystemBuilderImpl::new));
	}

	/**
	 * Loads a L-system configuration from a command and generates it.
	 * 
	 * @param provider
	 * @return the generated {@link LSystem}.
	 */
	private static LSystem createKochCurve(LSystemBuilderProvider provider) {
		return provider.createLSystemBuilder().registerCommand('F', "draw 1")
				.registerCommand('+', "rotate 60")
				.registerCommand('-', "rotate -60").setOrigin(0.05, 0.4)
				.setAngle(0).setUnitLength(0.9)
				.setUnitLengthDegreeScaler(1.0 / 3.0)
				.registerProduction('F', "F+F--F+F").setAxiom("F").build();
	}
}