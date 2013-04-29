package de.htwg.seapal.aview.tui.activity;

import java.util.UUID;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import de.htwg.seapal.R;
import de.htwg.seapal.aview.listener.MyLocationListener;
import de.htwg.seapal.aview.tui.states.recordTrip.StartState;
import de.htwg.seapal.controller.impl.WaypointController;
import de.htwg.seapal.database.impl.HashMapWaypointDatabase;

public class TripRecordActivity extends AActivity {

	private LocationManager locationMgr;
	private WaypointController controller;
	private UUID trip;

	@Override
	protected void setup() {
		TextView header = (TextView) this.findViewById(R.id.header);
		header.setText("Trip Record");
		
		Bundle bundle = getIntent().getExtras();
		trip = UUID.fromString(bundle.getString("trip").toString());
		
		controller = new WaypointController(HashMapWaypointDatabase.getInstance());
		currenState = new StartState();
		controller.addObserver(this);
		
		LocationListener myLocationListener = new MyLocationListener(trip, controller);
		locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, myLocationListener);
	}

	public WaypointController getController() {
		return controller;
	}

	public UUID getTrip() {
		return trip;
	}
}
