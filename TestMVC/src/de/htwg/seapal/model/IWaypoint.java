package de.htwg.seapal.model;

import java.util.Calendar;
import java.util.UUID;

public interface IWaypoint {

	public enum Maneuver {
		NONE, TACK, JIBE, LAYTO, SET_SAILS, CHANGE_SAILS, SAILS_DOWN, REFF, ANKER_UP, ANKER_DOWN
	}

	public enum ForeSail {
		NONE, GENUA1, GENUA2, GENUA3, FOCK, STORM_FOCK, BISTER, SPINACKER
	}

	public enum MainSail {
		NONE, FULL, REEF1, REEF2
	}

	String getName();

	String getNote();

	int getBTM();

	int getDTM();

	int getCOG();

	int getSOG();

	UUID getHeadedFor();

	Maneuver getManeuver();

	ForeSail getForesail();

	MainSail getMainsail();

	void setForesail(ForeSail foreSail);

	void setName(String name);

	void setNote(String note);

	void setBTM(int btm);

	void setDTM(int dtm);

	void setCOG(int cog);

	void setSOG(int sog);

	void setHeadedFor(UUID mark);

	void setManeuver(Maneuver maneuver);

	void setMainsail(MainSail mainSail);

	UUID getId();

	UUID getTrip();

	void setTrip(UUID trip);

	Calendar getDate();

	void setDate(Calendar date);

	double getLatitude();

	void setLatitude(double latitude);

	double getLongitude();

	void setLongitude(double longitude);
}