package com.relurori.osmandroid;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pre();
	}

	MapView mMapView;
	ResourceProxyImpl mResourceProxy;
	
	
	private void pre() {
		
		mMapView = (MapView) findViewById(R.id.map);
		mMapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
		

		mMapView.setBuiltInZoomControls(true);
		mMapView.setMultiTouchControls(true);
		mMapView.getController().setZoom(16);
		mMapView.getController().setCenter(new GeoPoint(30266000, -97739000));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
