package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Scales the turtle's unit length by the given factor.
 * 
 * @author 0036502252
 *
 */
public class ScaleCommand implements Command {
	/**
	 * The factor used for scaling.
	 */
	private double factor;

	/**
	 * Creates a new {@link ScaleCommand}.
	 * 
	 * @param factor
	 *            the factor to be assigned to the command
	 */
	public ScaleCommand(double factor) {
		this.factor = factor;
	}

	/**
	 * Executes the {@link ScaleCommand}. Scales the turtle's unit length using
	 * the scale factor given by the command.
	 * 
	 * @param ctx
	 *            the context of the turtle
	 * @param factor
	 *            unused
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		double offset = ctx.getCurrentState().getUnitLength();
		ctx.getCurrentState().setUnitLength(offset * factor);
	}
}
