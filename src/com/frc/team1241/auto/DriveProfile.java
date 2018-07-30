package com.frc.team1241.auto;

public class DriveProfile {

	//left and right profile
	private ProfilePoint[] leftProfile, rightProfile;
	
	//total length of profile for both sides
	private double totalTime;
	
	//drive profile containing left and right profiles
	public DriveProfile(ProfilePoint[] leftProfile, ProfilePoint[] rightProfile) {
		this.leftProfile = leftProfile;
		this.rightProfile = rightProfile;
		calcTotalTime();
	}
	
	//sum time from all points
	private void calcTotalTime() {
		totalTime = 0;
		for (int i = 1; i < leftProfile.length; i++) {
			totalTime += leftProfile[i].getTime();
		}
	}
	
	//get total time
	public double getTotalTime() {
		return totalTime;
	}
	
	//get left profile
	public ProfilePoint[] getLeftProfile() {
		return leftProfile;
	}
	
	//get right profile
	public ProfilePoint[] getRightProfile() {
		return rightProfile;
	}
	
	//set left profile
	public void setLeftProfile(ProfilePoint[] newLeftProfile) {
		leftProfile = newLeftProfile;
	}
	
	//set right profile
	public void setRightProfile(ProfilePoint[] newRightProfile) {
		rightProfile = newRightProfile;
	}
}
