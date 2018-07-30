package com.frc.team1241.auto;

public class ProfilePoint {

	private double pos; // position in inches
	private double vel; // velocity in feet/s
	private double acc; // acc in ft/s^2
	private double time; // used for total time calculations

	// trajectory point with pos, vel, acc, time and intake boolean
	public ProfilePoint(double pos, double vel, double acc, double time) {
		this.pos = pos;
		this.vel = vel;
		this.acc = acc;
		this.time = time;
	}

	// get position
	public double getPos() {
		return pos;
	}

	// get velocity
	public double getVel() {
		return vel;
	}

	// get acc
	public double getAcc() {
		return acc;
	}

	// get time
	public double getTime() {
		return time;
	}
}
