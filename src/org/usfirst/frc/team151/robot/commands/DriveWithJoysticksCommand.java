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
		//if (Robot.autoOn) {
		//	return;
		//}
		System.out.println("Starting DriveWithJoysticks excecute");
		Robot.TANK_DRIVE_SUBSYSTEM.drive(Robot.driverOI);
//		else {
//			System.out.println("Inside else statement of execute");
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; 
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
