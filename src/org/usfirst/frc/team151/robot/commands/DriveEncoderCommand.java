package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class DriveEncoderCommand extends Command {
	
	double distance  = 0;
	boolean leftIsFinished = false;
	boolean rightIsFinished = false;
	int count = 0;
	double firstSpeed = 0;
	double secondSpeed = 0;
	
    public DriveEncoderCommand(double initDistance, double initSpeed, double secondarySpeed) {
    	System.out.println("Constructor");
        requires(Robot.TANK_DRIVE_SUBSYSTEM);
        distance = initDistance;
        firstSpeed = initSpeed;
        secondSpeed = secondarySpeed;
        System.out.println("First speed is " + firstSpeed + ", second speed is " + secondSpeed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double left = 0;
    	double right = 0;
    	count++;
    	if (distance - Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.getDistance() > 12) {
    		if (count < 100) {
    			left = -0.7 * count / 100;
    		}
    		else {
    			left = -0.7;
    		}
    	}
    	else if (distance - Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.getDistance() > 0.5) {
    		left = -0.4;
    	}
    	else {
    		left = 0;
    		leftIsFinished = true;
    	}
    	
    	if (distance - Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.getDistance() > 6) {
    		if (count < 100) {
    			right = -0.7 * count / 100;
    		}
    		else {
    			right = -0.7;
    		}
    	}
    	else if (distance - Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.getDistance() > 0.5) {
    		right = -0.4;
    	}
    	else {
    		right = 0;
    		rightIsFinished = true;
    	}
    	Robot.TANK_DRIVE_SUBSYSTEM.drive(left, right); //NOT NEGATIVE, CHANGE THIS FOR REAL ROBOT, SECOND VALUE IS RIGHT
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.autoOn == false) {
    		return true;
    	}
        return (leftIsFinished && rightIsFinished);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
