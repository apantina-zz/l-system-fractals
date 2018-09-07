package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.math.Vector2D;

/**
 * Represents the state of a turtle used for fractal drawing. The turtle has its
 * position, orientation, drawing color and step. 
 * @author 0036502252
 *
 */
public class TurtleState {
	/**
	 * The current position of the turtle.
	 */
	private Vector2D currentPosition;
	/**
	 * The direction in which the turtle is pointing. 
	 */
	private Vector2D direction;
	/**
	 * The color the turtle uses to draw lines. 
	 */
	private Color drawingColor;
	/**
	 * The unit length of steps the turtle makes. 
	 */
	private double unitLength;
	
	/**
	 * Constructs a new {@link TurtleState} with the given parameters.
	 * @param currentPosition the current position of the turtle
	 * @param direction the direction in which the turtle is pointing
	 * @param drawingColor the color used to draw lines
	 * @param unitLength the unit length of the turtle's steps
	 */
	public TurtleState(Vector2D currentPosition, Vector2D direction,
			Color drawingColor, double unitLength) {
		super();
		this.currentPosition = currentPosition.copy();
		this.direction = direction.normalize();
		this.drawingColor = drawingColor;
		this.unitLength = unitLength;
	}
	
	/**
	 * Creates a copy of this {@link TurtleState}.
	 * @return the copy of this state
	 */
	public TurtleState copy() {
		return new TurtleState(currentPosition, direction, drawingColor, unitLength);
	}
	
	/**
	 * Gets the turtle's current position vector.
	 * @return the turtle's currentPosition 
	 */
	public Vector2D getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Gets the turtle's current directional vector.
	 * @return the turtle's currentDirection
	 */
	public Vector2D getDirection() {
		return direction;
	}
	
	/**
	 * Gets the turtle's drawing color.
	 * @return the turtle's drawingColor
	 */
	public Color getDrawingColor() {
		return drawingColor;
	}
	
	/**
	 * Gets the turtle's unit length for the step.
	 * @return the turtle's unitLength
	 */
	public double getUnitLength() {
		return unitLength;
	}

	/**
	 * Sets the turtle's drawing color. 
	 * @param color the drawingColor to set
	 */
	public void setDrawingColor(Color color) {
		this.drawingColor = color;
	}
	
	/**
	 * Sets the turtle's drawing color. 
	 * @param unitLength the unitLength to set
	 */
	public void setUnitLength(double unitLength) {
		this.unitLength = unitLength;
	}
	
	/**
	 * Sets the turtle's positional vector. 
	 * @param vector the currentPosition to set
	 */
	public void setPosition(Vector2D vector) {
		this.currentPosition = vector;
	}
	
	/**
	 * Sets the turtle's directional vector.
	 * @param vector the direction to set
	 */
	public void setDirection(Vector2D vector) {
		this.direction = vector;
	}
	
}
