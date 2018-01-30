package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoysticksCommand extends Command {

	public DriveWithJoysticksCommand() {
		// Use requires() here to declare subsystem dependencies 
		requires(Robot.TANK_DRIVE_SUBSYSTEM);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (!Robot.autoOn) {
			Robot.TANK_DRIVE_SUBSYSTEM.driveArcade(Robot.driverOI);
		}
		else {
			
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
//		Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
