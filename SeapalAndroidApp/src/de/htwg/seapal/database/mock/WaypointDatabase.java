package de.htwg.seapal.database.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

import de.htwg.seapal.database.IWaypointDatabase;
import de.htwg.seapal.model.IWaypoint;
import de.htwg.seapal.model.impl.Waypoint;

public class WaypointDatabase implements IWaypointDatabase {

	Map<UUID, IWaypoint> db = new HashMap<UUID, IWaypoint>();
	private IWaypoint newWaypoint;

	public WaypointDatabase() {
		open();
	}
	
	@Override
	public boolean open() {
		// create test data
		UUID id = createNewBoatInDatabase();
		newWaypoint = get(id);
		newWaypoint.setName("Waypoint-NEW");
		save(newWaypoint);
		for (int i = 1; i < 10; i++) {
			id = createNewBoatInDatabase();
			IWaypoint waypoint = get(id);
			waypoint.setName("Waypoint-" + i);
			save(waypoint);
		}
		return true;
	}
	
	@Override
	public boolean close() {
		return true;
	}

	@Override
	public UUID create() {
		return newWaypoint.getUUID();
	}

	private UUID createNewBoatInDatabase() {
		IWaypoint waypoint = new Waypoint();
		UUID id = waypoint.getUUID();
		db.put(id, waypoint);
		return id;
	}

	@Override
	public boolean save(IWaypoint waypoint) {
		return true;
	}

	@Override
	public void delete(UUID id) {
	}

	@Override
	public IWaypoint get(UUID id) {
		return new Waypoint(db.get(id));
	}

	@Override
	public List<IWaypoint> loadAll() {
		return ImmutableList.copyOf(db.values());
	}

	@Override
	public List<IWaypoint> loadAllByTripId(UUID tripId) {
		// TODO Auto-generated method stub
		return null;
	}
}
