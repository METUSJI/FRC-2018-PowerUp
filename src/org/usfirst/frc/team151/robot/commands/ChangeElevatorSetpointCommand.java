package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeElevatorSetpointCommand extends Command {
	
	private boolean isFinished = false;
	private double setpoint = 0;
	
	public ChangeElevatorSetpointCommand(double setpoint) {
		this.setpoint = setpoint;
		System.out.println("ChangeElevatorSetpointCommand initialized");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("change el command initalize method");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("ChangeElevatorSetpointCommand, setpoint: " + setpoint);
		Robot.ELEVATOR_PID_SUBSYSTEM.changeSetpoint(setpoint);
		Robot.elevatorPIDControl = true;
		Robot.ELEVATOR_PID_SUBSYSTEM.enable();
		//TODO take this out after testing
		Robot.startTime = System.nanoTime();
		isFinished  = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
