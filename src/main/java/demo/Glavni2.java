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
public class Glavni2 {

	/**
	 * Main method. Displays an {@link LSystem} in a window. 
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(createKochCurve2(LSystemBuilderImpl::new));
	}

	/**
	 * Loads a L-system configuration from a string and generates it.
	 * 
	 * @param provider
	 * @return the generated {@link LSystem}.
	 */
	private static LSystem createKochCurve2(LSystemBuilderProvider provider) {
		String[] data = new String[] { "origin 0.05 0.4", "angle 0",
				"unitLength 0.9", "unitLengthDegreeScaler 1.0 / 3.0", "",
				"command F draw 1", "command + rotate 60",
				"command - rotate -60", "", "axiom F", "",
				"production F F+F--F+F" };
		return provider.createLSystemBuilder().configureFromText(data).build();
	}
}
