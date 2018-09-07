package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.math.Vector2D;

/**
 * Represents a drawing command, in which the turtle moves from one point to
 * another, drawing a line along its way.
 * 
 * @author 0036502252
 *
 */
public class DrawCommand implements Command {
	/**
	 * The unit step by which the turtle moves when the draw command is
	 * executed.
	 */
	private double step;

	/**
	 * Creates a new {@link DrawCommand}.
	 * 
	 * @param step
	 *            the step to be used by the command.
	 */
	public DrawCommand(double step) {
		this.step = step;
	}
	
	/**
	 * Moves the turtle from one point to another. The second point is
	 * determined by the turtle's orientation and by the command's desired step.
	 * Draws a line along the path.
	 * 
	 * @param ctx
	 *            the turtle's current context
	 * @param painter
	 *            the painter used to draw a line
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

		painter.drawLine(x, y, newX, newY,
				ctx.getCurrentState().getDrawingColor(), 1F);

		ctx.getCurrentState().setPosition(new Vector2D(newX, newY));
	}
}
