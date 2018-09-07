package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;

/**
 * An implementation of the {@link LSystemBuilderProvider}.
 * @author 0036502252
 *
 */
public class LSystemBuilderProviderImpl implements LSystemBuilderProvider {
	
	/**
	 * Creates a new {@link LSystemBuilder}. 
	 * @return the newly created {@link LSystemBuilder}.
	 */
	@Override
	public LSystemBuilder createLSystemBuilder() {
		return new LSystemBuilderImpl();
	}

}
