package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Represents a color command used for l-system creation.
 * 
 * @author 0036502252
 *
 */
public class ColorCommand implements Command {
	/**
	 * The color that the command sets when it is executed.
	 */
	private Color color;

	/**
	 * Creates a new {@link ColorCommand}.
	 * 
	 * @param color
	 *            the color to be set to the command
	 */
	public ColorCommand(Color color) {
		this.color = color;
	}

	/**
	 * Sets the drawing color of the turtle's current state.
	 * 
	 * @param ctx
	 *            the turtle's current context
	 * @param painter
	 *            unused
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.getCurrentState().setDrawingColor(color);
	}

}
