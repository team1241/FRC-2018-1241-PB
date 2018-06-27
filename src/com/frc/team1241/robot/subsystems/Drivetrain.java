package com.frc.team1241.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.frc.team1241.robot.NumberConstants;
import com.frc.team1241.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {	
	
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftSlave;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightSlave;
	
	public Drivetrain() {
		//left master
		leftMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftMaster.setSensorPhase(true);
		leftMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		
		//left slave
		leftSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE);
		leftSlave.set(ControlMode.Follower, RobotMap.LEFT_MASTER);
		
		//right master
		rightMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightMaster.setSensorPhase(true);
		rightMaster.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Coast);
		
		//right slave
		rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE);
		rightSlave.set(ControlMode.Follower, RobotMap.RIGHT_MASTER);
		
		//config pid values (set to zero)
		leftMaster.config_kP(0, NumberConstants.pTalonDrive, 0);
		leftMaster.config_kI(0, NumberConstants.iTalonDrive, 0);
		leftMaster.config_kD(0, NumberConstants.dTalonDrive, 0);
		leftMaster.config_kF(0, NumberConstants.fTalonDrive, 0);

		rightMaster.config_kP(0, NumberConstants.pTalonDrive, 0);
		rightMaster.config_kI(0, NumberConstants.iTalonDrive, 0);
		rightMaster.config_kD(0, NumberConstants.dTalonDrive, 0);
		rightMaster.config_kF(0, NumberConstants.fTalonDrive, 0);
		
		//set to use slot 0
		leftMaster.selectProfileSlot(0, 0);
		rightMaster.selectProfileSlot(0, 0);
	}
	
    public void initDefaultCommand() {
        
    }
    
    //run left side of drive
    public void runLeftDrive(double output) {
    	leftMaster.set(output);
    }
    
    //run right side of drive
    public void runRightDrive(double output) {
    	rightMaster.set(output);
    }
    
    //ENCODER
    //left encoder value
    public double getLeftEncoder() {
		return (leftMaster.getSelectedSensorPosition(0) / NumberConstants.nativeToInches);
	}

    //right encoder value
	public double getRightEncoder() {
		return (rightMaster.getSelectedSensorPosition(0) / NumberConstants.nativeToInches);
	}
	
	//reset encoders
	public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);
	}
}

