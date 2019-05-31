package com.mygdx.game.entity.state;

public interface StateListener<S> {
	// This method is called before a state is added.
	// All listeners must return true for the state to be added
	boolean stateAddValid(S state);

	// This method is called when a state is added to States
	void stateAdd(S state);

	// This method is called when a state is removed from States
	void stateRemove(S state);
}