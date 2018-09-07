package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Represents a command used for L-system generation. Using the given context
 * and (optional) painter, the command can be executed.
 * 
 * @author 0036502252
 *
 */
public interface Command {

	/**
	 * Executes the command
	 * 
	 * @param ctx
	 *            the current context
	 * @param painter
	 *            the painter used for drawing
	 */
	void execute(Context ctx, Painter painter);
}
