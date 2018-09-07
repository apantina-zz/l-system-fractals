package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Represents a command which pushes a {@link TurtleState} on the context's
 * stack.
 * 
 * @author 0036502252
 *
 */
public class PushCommand implements Command {

	/**
	 * Pushes a {@link TurtleState} on the context's stack.
	 * 
	 * @param ctx
	 *            the turtle's current context
	 * @param painter
	 *            unused
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState state = ctx.getCurrentState().copy();
		ctx.pushState(state);
	}
}