package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightCommand extends Command {

	private double distance = 0;
	
	public DriveStraightCommand(double distance) {
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
	}
	
	@Override
	protected void execute() {
		if(Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTravelled() < distance)
			Robot.TANK_DRIVE_SUBSYSTEM.drive(0.5, 0.5);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTravelled() >= distance;
	}
	
	@Override
	protected void end() {
		Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
		Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
	}
	
	@Override
	protected void interrupted() {
		end();
	}

}
