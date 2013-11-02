package com.relurori.sandbox.gesture;

import android.view.MotionEvent;

public class GestureUtil {
	
	public static final int DOWN_EDGE_NOWHERE = 0;
	public static final int DOWN_EDGE_NORTH = 1;
	public static final int DOWN_EDGE_SOUTH = 2;
	public static final int DOWN_EDGE_EAST = 4;
	public static final int DOWN_EDGE_WEST = 8;
	
	public static final int SWIPE_EAST_TO_WEST = 1;
	public static final int SWIPE_SOUTH_TO_NORTH = 2;
	public static final int SWIPE_WEST_TO_EAST = 3;
	public static final int SWIPE_NORTH_TO_SOUTH = 4;

	private boolean DOWN_EDGE_STATE = false;
	private int DOWN_EDGE_LOCATION = DOWN_EDGE_NOWHERE;
	private float DOWN_X = 0;
	private float DOWN_Y = 0;
	
	private float UP_X = 0;
	private float UP_Y = 0;
	
	private int screenX = 0;
	private int screenY = 0;
	
	public GestureUtil (int x, int y) {
		screenX = x;
		screenY = y;
	}
	/**
	 * resets state of DOWN_EDGE_STATE.
	 * sets DOWN_EDGE_STATE and DOWN_EDGE_LOCATION if applicable.
	 * 
	 * @param event
	 * @return
	 */
	public boolean downNearEdge(MotionEvent event) {
		setDownEdgeState(false, event, DOWN_EDGE_NOWHERE);
		if (event.getX() < 100) {
			setDownEdgeState(true, event, DOWN_EDGE_WEST);
		} else if (event.getX() > (screenX - 100)) {
			setDownEdgeState(true, event, DOWN_EDGE_EAST);
		} else if (event.getY() < 100) {
			setDownEdgeState(true, event, DOWN_EDGE_NORTH);
		} else if (event.getY() > (screenY - 100)) {
			setDownEdgeState(true, event, DOWN_EDGE_SOUTH);
		}
		return DOWN_EDGE_STATE;
	}

	private void setDownEdgeState(boolean b, MotionEvent me, int location) {
		DOWN_EDGE_STATE = b;
		setDownLocation(location);
		setDownX(me.getX());
		setDownY(me.getY());
	}
	
	private void setDownLocation(int location) {
		if (DOWN_EDGE_STATE == true) {
			DOWN_EDGE_LOCATION |= location;
		}
		if (location == DOWN_EDGE_NOWHERE) {
			DOWN_EDGE_LOCATION = 0;
		}
	}
	private void setDownY(float y) {
		if (DOWN_EDGE_STATE == true) {
			DOWN_Y = y;
		}
	}
	private void setDownX(float x) {
		if (DOWN_EDGE_STATE == true) {
			DOWN_X = x;
		}
		
	}
	public boolean getGestureDownNearEdgeState() {
		return DOWN_EDGE_STATE;
	}
	
	public int getGestureDownLocation() {
		return DOWN_EDGE_LOCATION;
	}
	
	/**
	 * Location where ACTION_UP heard, from an ACTION_DOWN
	 * near an edge
	 * 
	 * @param event
	 */
	public void setActionUp(MotionEvent event) {
		UP_X = event.getX();
		UP_Y = event.getY();
		setDownLocation(DOWN_EDGE_NOWHERE);
	}
	
	/**
	 * Get swipe direction. 
	 * 
	 * Particularly important from starting in corners where
	 * it's ambiguous to be both NORTH and EAST, for instance.
	 * 
	 * @return
	 */
	public int getSwipe() {
		return SWIPE_WEST_TO_EAST;
	}
}
