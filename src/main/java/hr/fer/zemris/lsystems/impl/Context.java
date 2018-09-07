package hr.fer.zemris.lsystems.impl;

import hr.zemris.java.custom.collections.ObjectStack;

/**
 * Represents the context of the turtle. 
 * @author 0036502252
 *
 */
public class Context {
	/**
	 * Stack data structure implementation used for modelling the context.
	 */
	private ObjectStack stack;
	
	/**
	 * Creates a new context.
	 */
	public Context() {
		this.stack = new ObjectStack();
	}
	
	/**
	 * Gets the current state of the turtle's context.
	 * @return the currentState
	 */
	public TurtleState getCurrentState() {
		return (TurtleState) stack.peek();
	}
	
	/**
	 * Pushes a new state on the context stack.
	 * @param state the state to be set
	 */
	public void pushState(TurtleState state) {
		stack.push(state);
	} 
	
	/**
	 * Pops a state from the context stack.
	 */
	public void popState() {
		stack.pop();
	} 
}
