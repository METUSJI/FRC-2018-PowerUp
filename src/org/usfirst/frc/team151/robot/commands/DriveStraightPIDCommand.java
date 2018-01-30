package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraightPIDCommand extends PIDCommand {
	
	double distance;
	private int count;
	
	public DriveStraightPIDCommand(double setpoint, double p, double i, double d) {
		super(p, i, d);
		setSetpoint(setpoint);
		getPIDController().setAbsoluteTolerance(0.5);
		distance = setpoint;
		Robot.autoOn = true;
	}

	@Override
	protected double returnPIDInput() {
		if (count % 7 == 0) {
			System.out.println("Travelled: " + Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled());
		}
		count++;
		Robot.autoOn = true;
		return Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.TANK_DRIVE_SUBSYSTEM.drive(-output,  -output); //direction is inverted
	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(Robot.TANK_DRIVE_SUBSYSTEM.getEncoder() - distance) < 0.5) {
			System.out.println("Finished");
			Robot.autoOn = false;
			return true;
		}
		else {
			return false;
		}
	}

}
