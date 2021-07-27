package bear.event.impl;

import bear.event.EventCancelable;

public class KeyEvent extends RenderEvent{

	private final int key;
	
	public KeyEvent(int key) {
		this.key = key;
		
	}
	
	public int getKey() {
		return key;
	}
}
