package demo;

import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Demonstration program for the {@link LSystemBuilderImpl} class.
 * 
 * @author 0036502252
 *
 */
public class Glavni3 {
	/**
	 * Loads a .txt file of an L-system (some examples can be found in the
	 * root/src/main/resources/examples directory) and displays it in a window.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(LSystemBuilderImpl::new);
	}
}