package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.math.Vector2D;

/**
 * Represents a drawing command, in which the turtle moves from one point to
 * another. Similar to the DrawCommand class, except it doesn't draw a line
 * along its path.
 * 
 * @see DrawCommand
 * @author 0036502252
 *
 */
public class SkipCommand implements Command {
	/**
	 * The unit step by which the turtle moves when the skip command is
	 * executed.
	 */
	private double step;

	/**
	 * Creates a new {@link SkipCommand}.
	 * 
	 * @param step
	 *            the step to be used by the command.
	 */
	public SkipCommand(double step) {
		this.step = step;
	}

	/**
	 * Moves the turtle from one point to another. The second point is
	 * determined by the turtle's orientation and by the command's desired step.
	 * 
	 * @param ctx
	 *            the turtle's current context
	 * @param painter
	 *            unused
	 */
	@Override
	public void execute(Context ctx, Painter painter) {

		double x = ctx.getCurrentState().getCurrentPosition().getX();
		double y = ctx.getCurrentState().getCurrentPosition().getY();
		double scale = ctx.getCurrentState().getUnitLength();

		double xDir = ctx.getCurrentState().getDirection().getX();
		double yDir = ctx.getCurrentState().getDirection().getY();

		double newX = x + xDir * step * scale;
		double newY = y + yDir * step * scale;

		ctx.getCurrentState().setPosition(new Vector2D(newX, newY));
	}
}
