package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DriveStraightPIDCommand extends PIDCommand {

	private int count;
	
	double MINIMUM_OUTPUT = -0.55;
	double MAXIMUM_OUTPUT = 0.55;

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
		if (count % 3 == 0) {  
//			System.out.println("Travelled: " + Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled());
//			System.out.println("Left Encoder Output: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.get());
//			System.out.println("Right Encoder Output: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.get());
//			System.out.println("Setpoint: " + getSetpoint());
		}
		count++;
		return Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled();
	}

	@Override 
	protected void usePIDOutput(double output) {
		System.out.println("PID Output: " + output);
		Robot.TANK_DRIVE_SUBSYSTEM.drive(-output, -output); //direction is inverted
	}

	@Override
	protected boolean isFinished() {
		if (Robot.autoOn == false) {
			return true;
		}
		boolean finished = getPIDController().onTarget();
		if(finished) {
//			System.out.println("Drive straight finished");
			Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
			Robot.TANK_DRIVE_SUBSYSTEM.resetAll();
			getPIDController().disable();
		}
		return finished;
	}

}
