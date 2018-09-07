package hr.fer.zemris.java.hw04.problem1;
import org.junit.Assert;
import org.junit.Test;

import hr.zemris.java.custom.collections.Dictionary;

/**
 * Testing class for the {@link Dictionary} class.
 * @author 0036502252
 *
 */
public class TestDictionary {
	@Test
	public void basicPutAndGetTest() {
		Dictionary d = new Dictionary();
			d.put("Key", 1);
			d.put("Key2", 2);
			d.put("Key", 56);

		Assert.assertEquals(56, d.get("Key"));
	}
	
	@Test
	public void putOverwriteTest() {
		Dictionary d = new Dictionary();
		for(int i = 0; i < 50; i++) {
			if(i % 2 == 0) {
				d.put("Key1", i);
			} else {
				d.put("Key2", i);
			}
		}
		Assert.assertEquals(2, d.size());
	}
	
	@Test
	public void sizeTest() {
		Dictionary d = new Dictionary();
		d.put("Key", 1);
		d.put("Key2", 2);
		d.put("Key", 56);

		Assert.assertEquals(2, d.size());
	}
	
	@Test
	public void clearTest() {
		Dictionary d = new Dictionary();
		d.put("Key", 1);
		d.put("Key2", 2);
		d.put("Key", 56);
		d.clear();
		Assert.assertEquals(0, d.size());
	}
	
	@Test
	public void getTest() {
		Dictionary d = new Dictionary();
		d.put("Key", 1);
		d.put("Key2", 2);
		d.put("Key", 56);
		Assert.assertEquals(null, d.get("key"));
	}
	@Test(expected = NullPointerException.class)
	public void invalidGetTest() {
		Dictionary d = new Dictionary();
		d.put("Key", 1);
		d.put("Key2", 2);
		d.put("Key", 56);
		d.get(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void invalidPutTest() {
		Dictionary d = new Dictionary();
		d.put("Key", 1);
		d.put("Key2", 2);
		d.put("Key", 56);
		d.put(null, 42);
	}
}
