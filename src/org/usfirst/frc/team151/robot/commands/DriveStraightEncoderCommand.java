package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightEncoderCommand extends Command {
	
	private boolean isFinished = false;
	private double distance;

    public DriveStraightEncoderCommand(double initDistance) {
    	distance = initDistance;
        requires(Robot.TANK_DRIVE_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.TANK_DRIVE_SUBSYSTEM.getEncoder() < distance) {
    		Robot.TANK_DRIVE_SUBSYSTEM.drive(0.25, 0.25);
    		System.out.println("Left Encoder Distance: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.getDistance());
    		System.out.println("Left Encoder Count: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.get());
    		System.out.println("Right Encoder Distance: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.getDistance());
    		System.out.println("Right Encoder Count: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.get());
    	}
    	else {
    		Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
