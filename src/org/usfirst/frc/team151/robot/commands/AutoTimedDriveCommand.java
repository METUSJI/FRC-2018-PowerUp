package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class AutoTimedDriveCommand extends TimedCommand {

    public AutoTimedDriveCommand(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.TANK_DRIVE_SUBSYSTEM.drive(0.6, 0.6);
    }

    // Called once after timeout
    protected void end() {
    	Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
    	Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
