package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraightPIDCommand extends PIDCommand {

	double MINIMUM_OUTPUT = -0.7;
	double MAXIMUM_OUTPUT = 0.7;
	int count = 0;

	public DriveStraightPIDCommand(double setpoint, double p, double i, double d) {
		super(p, i, d);
		requires(Robot.TANK_DRIVE_SUBSYSTEM);
		setSetpoint(setpoint);
		getPIDController().setAbsoluteTolerance(1.5);
		getPIDController().setOutputRange(MINIMUM_OUTPUT, MAXIMUM_OUTPUT);
	}
	
	@Override
	protected void initialize() {
		getPIDController().enable();
	}

	@Override
	protected double returnPIDInput() {
		//ENCODER IS GIVIG NEGATIVE VALUES WHEN WHEELS ARE MOVING FORWARD (WITH NEGATIVE INPUTS)
		System.out.println("Encoder: " + (Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled()));
		return (Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled());
	}

	@Override 
	protected void usePIDOutput(double output) {
		count++;
//		if (count < 50) {
//			Robot.TANK_DRIVE_SUBSYSTEM.drive(-output * count / 50, -output * count / 50);
//		}
//		else {
			Robot.TANK_DRIVE_SUBSYSTEM.drive(-output, -output); //direction is inverted
//		}
	}

	@Override
	protected boolean isFinished() {
		if (Robot.autoOn == false) {
			return true;
		} 
		boolean finished = getPIDController().onTarget();
		if(finished) {
			System.out.println("Finished");
			Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
			Robot.TANK_DRIVE_SUBSYSTEM.resetAll();
			getPIDController().disable();
		}
		return finished;
	}

}
