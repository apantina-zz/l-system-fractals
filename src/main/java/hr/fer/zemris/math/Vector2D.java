package hr.fer.zemris.math;

import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

/**
 * Represents a two-dimensional vector, with its x and y-components.
 * 
 * @author 0036502252
 *
 */
public class Vector2D {
	/**
	 * The x-component of the vector.
	 */
	private double x;
	/**
	 * The y-component of the vector.
	 */
	private double y;

	/**
	 * Constructs a new 2D vector with the given coordinates.
	 * 
	 * @param x
	 *            the x-component
	 * @param y
	 *            the y-component
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x-component of the 2D vector.
	 * 
	 * @return the x-component
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y-component of the 2D vector.
	 * 
	 * @return the y-component
	 */
	public double getY() {
		return y;
	}

	/**
	 * Translates a vector using another <i>offset</i> vector. Modifies the
	 * vector which calls this method.
	 * 
	 * @param offset
	 *            the offset vector
	 */
	public void translate(Vector2D offset) {
		if (offset == null)
			throw new NullPointerException(
					"Can't accept a null value as an argument!");
		double x = this.x + offset.getX();
		double y = this.y + offset.getY();
		this.x = x;
		this.y = y;
	}

	/**
	 * Translates a vector using another <i>offset</i> vector. Instead of
	 * modifying the calling vector, it returns a new one.
	 * 
	 * @param offset
	 *            the offset vector
	 * @return the new, translated vector.
	 */
	public Vector2D translated(Vector2D offset) {
		if (offset == null)
			throw new NullPointerException(
					"Can't accept a null value as an argument!");
		double x = this.x + offset.getX();
		double y = this.y + offset.getY();
		return new Vector2D(x, y);
	}

	/**
	 * Rotates the given vector for a given angle. Modifies the vector which
	 * calls this method.
	 * 
	 * @param angle
	 *            the angle used for rotation, in degrees
	 */
	public void rotate(double angle) {
		Double angleInRadians = Math.toRadians(angle);

		double x = this.x * cos(angleInRadians) - this.y * sin(angleInRadians);
		double y = this.x * sin(angleInRadians) + this.y * sin(angleInRadians);
		this.x = x;
		this.y = y;
	}

	/**
	 * Rotates the given vector for a given angle. Instead of modifying the
	 * calling vector, it returns a new one.
	 * 
	 * @param angle
	 *            the angle used for rotation, in degrees
	 * @return the new, rotated vector.
	 */
	public Vector2D rotated(double angle) {
		Double angleInRadians = Math.toRadians(angle);

		double x = this.x * cos(angleInRadians) - this.y * sin(angleInRadians);
		double y = this.x * sin(angleInRadians) + this.y * cos(angleInRadians);
		return new Vector2D(x, y);
	}

	/**
	 * Scales the vector's components using a scaling factor. Modifies the
	 * vector which calls this method.
	 * 
	 * @param scaler
	 *            the scaling factor.
	 */
	public void scale(double scaler) {
		double x = this.x * scaler;
		double y = this.y * scaler;
		this.x = x;
		this.y = y;
	}

	/**
	 * Scales the vector's components using a scaling factor. Instead of
	 * modifying the calling vector, it returns a new one.
	 * 
	 * @param scaler
	 *            the scaling factor.
	 * @return the new, scaled vector.
	 */
	public Vector2D scaled(double scaler) {
		double x = this.x * scaler;
		double y = this.y * scaler;
		return new Vector2D(x, y);
	}

	/**
	 * Creates a copy of this vector by creating a new Vector2D instance with
	 * the identical components.
	 * 
	 * @return the copy of the vector.
	 */
	public Vector2D copy() {
		return new Vector2D(this.x, this.y);
	}

	/**
	 * Returns a normalized instance of this vector, which by definition has a
	 * length of 1.
	 * 
	 * @return
	 */
	public Vector2D normalize() {
		double length = sqrt(this.x * this.x + this.y * this.y);
		double normalizedX = this.x / length;
		double normalizedY = this.y / length;
		return new Vector2D(normalizedX, normalizedY);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		final double DELTA = 1E-6;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector2D))
			return false;
		Vector2D other = (Vector2D) obj;
		if ((Math.abs(x - other.x) > DELTA))
			return false;
		if (Math.abs(y - other.y) > DELTA)
			return false;
		return true;
	}
}