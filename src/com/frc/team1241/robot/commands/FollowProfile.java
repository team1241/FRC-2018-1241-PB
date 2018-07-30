package com.frc.team1241.robot.commands;

import com.frc.team1241.auto.DriveProfile;
import com.frc.team1241.auto.ProfileController;
import com.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * FollowProfile
 * @author Neil
 * Follow a profile by switching setpoints based on time. 
 * For output see @ProfileController.	
 */
public class FollowProfile extends Command {

	private DriveProfile profile;
	private double segmentTime;
	private int index = 0;
	private Timer timer;
	
    public FollowProfile(DriveProfile auto) {
        profile = auto;
        segmentTime = profile.getTotalTime() / profile.getLeftProfile().length;
         
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPos = profile.getLeftProfile()[index].getPos() * 12;
    	double leftVel = profile.getLeftProfile()[index].getVel();
    	double leftAcc = profile.getLeftProfile()[index].getAcc();
    	
    	double rightPos = profile.getRightProfile()[index].getPos() * 12;
    	double rightVel = profile.getRightProfile()[index].getVel();
    	double rightAcc = profile.getRightProfile()[index].getAcc();
    	
    	double currentLeftPos = Robot.drive.getLeftEncoder();
    	double currentRightPos = Robot.drive.getRightEncoder();
    	
    	Robot.drive.runLeftDrive(ProfileController.output(leftPos, leftVel, leftAcc, currentLeftPos));
    	Robot.drive.runRightDrive(ProfileController.output(rightPos, rightVel, rightAcc, currentRightPos));
    	
    	if (timer.get() >= segmentTime) {
    		timer.reset();
    		index++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (index < profile.getLeftProfile().length)
        	return false;
        else
        	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stopDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
