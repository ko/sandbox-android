package com.relurori.sandbox.gesture;

import android.util.Log;
import android.view.MotionEvent;

public class GestureUtil {

	private static final String TAG = GestureUtil.class.getSimpleName();

	public static final int DOWN_EDGE_NOWHERE = 0;
	public static final int DOWN_EDGE_NORTH = 1;
	public static final int DOWN_EDGE_SOUTH = 2;
	public static final int DOWN_EDGE_EAST = 4;
	public static final int DOWN_EDGE_WEST = 8;

	public static final int SWIPE_NOWHERE = 0;
	public static final int SWIPE_LEFT = 1;
	public static final int SWIPE_UP = 2;
	public static final int SWIPE_RIGHT = 3;
	public static final int SWIPE_DOWN = 4;

	private boolean DOWN_EDGE_STATE = false;
	private int DOWN_EDGE_LOCATION = DOWN_EDGE_NOWHERE;
	private float eventDownX = 0;
	private float eventDownY = 0;
	private float eventUpX = 0;
	private float eventUpY = 0;

	private float deltaX = 0;
	private float deltaY = 0;

	private int screenX = 0;
	private int screenY = 0;

	public GestureUtil(int x, int y) {
		screenX = x;
		screenY = y;
	}

	/**
	 * resets state of DOWN_EDGE_STATE. sets DOWN_EDGE_STATE and
	 * DOWN_EDGE_LOCATION if applicable.
	 * 
	 * @param event
	 * @return
	 */
	public boolean setActionDown(MotionEvent event) {
		setDownEdgeState(false, event, DOWN_EDGE_NOWHERE);
		if (event.getX() < 100) {
			setDownEdgeState(true, event, DOWN_EDGE_WEST);
		} else if (event.getX() > (screenX - 100)) {
			setDownEdgeState(true, event, DOWN_EDGE_EAST);
		} 
		if (event.getY() < 100) {
			setDownEdgeState(true, event, DOWN_EDGE_NORTH);
		} else if (event.getY() > (screenY - 100)) {
			setDownEdgeState(true, event, DOWN_EDGE_SOUTH);
		}
		return getGestureDownNearEdgeState();
	}

	private void setDownEdgeState(boolean b, MotionEvent me, int location) {
		setGestureDownNearEdgeState(b);
		setDownLocation(location);
		setDownEvent(me);
	}

	private void setDownLocation(int location) {
		if (getGestureDownNearEdgeState() == true) {
			DOWN_EDGE_LOCATION |= location;
		} else {
			DOWN_EDGE_LOCATION = DOWN_EDGE_NOWHERE;
			setGestureDownNearEdgeState(false);
		}
	}

	private void setDownEvent(MotionEvent me) {
		eventDownX = me.getX();
		eventDownY = me.getY();
	}

	public float getDownEventX() {
		return eventDownX;
	}

	public boolean getGestureDownNearEdgeState() {
		return DOWN_EDGE_STATE;
	}

	public void setGestureDownNearEdgeState(boolean b) {
		DOWN_EDGE_STATE = b;
	}

	public int getGestureDownLocation() {
		return DOWN_EDGE_LOCATION;
	}

	/**
	 * Location where ACTION_UP heard, from an ACTION_DOWN near an edge
	 * 
	 * @param event
	 */
	public void setActionUp(MotionEvent event) {
		setUpEvent(event);
	}

	private void setUpEvent(MotionEvent event) {
		eventUpX = event.getX();
		eventUpY = event.getY();
	}

	public float getUpEventX() {
		return eventUpX;
	}

	/**
	 * Get swipe direction.
	 * 
	 * Particularly important from starting in corners where it's ambiguous to
	 * be both NORTH and EAST, for instance.
	 * 
	 * @return
	 */
	public int getSwipe() {
		if (getGestureDownNearEdgeState() != true) {
			return SWIPE_NOWHERE;
		}

		deltaX = Math.abs(eventUpX - eventDownX);
		deltaY = Math.abs(eventUpY - eventUpY);

		Log.d(TAG, "up.x,down.x=" + eventUpX + "," + eventDownX);
		Log.d(TAG, "deltaX=" + deltaX + " deltaY=" + deltaY);
		Log.d(TAG, "location = " + getGestureDownLocation());

		if ((getGestureDownLocation() & DOWN_EDGE_NORTH) == DOWN_EDGE_NORTH) {
			if (deltaY > 100 && eventUpY > eventDownY)
				return SWIPE_DOWN;
		} else if ((getGestureDownLocation() & DOWN_EDGE_SOUTH) == DOWN_EDGE_SOUTH) {
			if (deltaY > 100 && eventUpY < eventDownY)
				return SWIPE_UP;
		} else if ((getGestureDownLocation() & DOWN_EDGE_EAST) == DOWN_EDGE_EAST) {
			if (deltaX > 100 && eventUpX < eventDownX)
				return SWIPE_LEFT;
		} else if ((getGestureDownLocation() & DOWN_EDGE_WEST) == DOWN_EDGE_WEST) {
			if (deltaX > 100 && eventUpX > eventDownX)
				return SWIPE_RIGHT;
		}

		return SWIPE_NOWHERE;
	}
}
