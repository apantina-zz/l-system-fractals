package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Represents a command which pops a {@link TurtleState} from the context's stack.
 * @author 0036502252
 *
 */
public class PopCommand implements Command {

	/**
	 * Pops a {@link TurtleState} from the context's stack.
	 * @param ctx the context from which the state is popped
	 * @param painter unused
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.popState();
	}
}
