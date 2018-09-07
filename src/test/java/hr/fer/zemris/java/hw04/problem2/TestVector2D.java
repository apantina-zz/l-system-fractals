package hr.fer.zemris.java.hw04.problem2;

import org.junit.Assert;
import org.junit.Test;

import hr.fer.zemris.math.Vector2D;
import static java.lang.Math.PI;

/**
 * Testing class for the {@link Vector2D} class.
 * 
 * @author 0036502252
 *
 */
public class TestVector2D {
	@Test
	public void translateTest() {
		Vector2D vector = new Vector2D(3, 4);
		Vector2D offset = new Vector2D(0.1, -0.5);
		vector.translate(offset);
		Vector2D expected = new Vector2D(3.1, 3.5);
		Assert.assertTrue(vector.equals(expected));
	}

	@Test
	public void translatedTest() {
		Vector2D vector = new Vector2D(3, 4);
		Vector2D offset = new Vector2D(0.00001, -0.00005);
		Vector2D translated = vector.translated(offset);
		Vector2D expected = new Vector2D(3.00001, 3.99995);
		Assert.assertTrue(translated.equals(expected));
	}

	@Test
	public void rotateTest() {
		Vector2D vector = new Vector2D(1, 0);
		vector.rotate(90);
		Vector2D expected = new Vector2D(0, 1);
		Assert.assertTrue(vector.equals(expected));
	}

	@Test
	public void rotatedTest() {
		Vector2D vector = new Vector2D(2.1234, 3.3433);
		Vector2D rot = vector.rotated(180);
		Vector2D expected = new Vector2D(-2.1234, -3.3433);
		Assert.assertTrue(rot.equals(expected));
	}

	@Test
	public void scaleTest() {
		Vector2D vector = new Vector2D(3.1212, 4.2121);
		vector.scale(3);
		Vector2D expected = new Vector2D(9.3636, 12.6363);
		Assert.assertEquals(expected, vector);
	}

	@Test
	public void scaledTest() {
		Vector2D vector = new Vector2D(0.00002, -1.11111);
		Vector2D scaled = vector.scaled(2);
		Vector2D expected = new Vector2D(0.00004, -2.22222);
		Assert.assertEquals(expected, scaled);
	}

	@Test
	public void copyTest() {
		Vector2D vector = new Vector2D(3.22223, -132.44443);
		Vector2D copy = vector.copy();
		Assert.assertEquals(vector, copy);
	}

	@Test
	public void normalizeTest() {
		Vector2D vector = new Vector2D(121234124.12321, 3.23423);
		vector = vector.normalize();
		double length = Math.sqrt(
				vector.getX() * vector.getX() + vector.getY() * vector.getY());
		Assert.assertEquals(1, length, 1E-6);
	}
}
