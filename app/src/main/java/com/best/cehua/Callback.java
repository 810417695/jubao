package com.best.cehua;

public interface Callback {
	void onBefore();

	boolean onRun();

	void onAfter(boolean b);
}
