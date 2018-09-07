package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * Represents a command which rotates the {@link TurtleState}'s directional
 * vector by the given angle.
 * 
 * @author 0036502252
 *
 */
public class RotateCommand implements Command {
	/**
	 * The angle used for rotation, in degrees.
	 */
	private double angle;

	/**
	 * Creates a new {@link RotateCommand}.
	 * 
	 * @param angle
	 *            the angle to be used by the command, in degrees.
	 */
	public RotateCommand(double angle) {
		this.angle = angle;
	}

	/**
	 * Rotates the {@link TurtleState}'s directional vector.
	 * @param ctx the turtle's current context
	 * @param painter unused
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		Vector2D vector = ctx.getCurrentState().getDirection().rotated(angle)
				.normalize();
		ctx.getCurrentState().setDirection(vector);
	}
}
