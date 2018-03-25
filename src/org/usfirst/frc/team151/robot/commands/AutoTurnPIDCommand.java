package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;


import edu.wpi.first.wpilibj.command.PIDCommand;

public class AutoTurnPIDCommand extends PIDCommand {

	double angle;
	double currentOutput = 0;
 
	double MINIMUM_OUTPUT = -0.7;
	double MAXIMUM_OUTPUT = 0.7;

	public AutoTurnPIDCommand(double setpoint, double p, double i, double d) {
		super(p, i, d);
		requires(Robot.TANK_DRIVE_SUBSYSTEM);
		setSetpoint(setpoint);
		getPIDController().setAbsoluteTolerance(2.5);
		angle = setpoint;

		getPIDController().setOutputRange(MINIMUM_OUTPUT, MAXIMUM_OUTPUT);
	}

	@Override
	protected double returnPIDInput() {
		System.out.println("Gyro: " + Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle());
		return Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (Math.abs(output) > 0.3) {
			Robot.TANK_DRIVE_SUBSYSTEM.drive(-output, output); 
		}
		else {
			if (output < 0) {
				Robot.TANK_DRIVE_SUBSYSTEM.drive(0.3, -0.3);
			}
			else {
				Robot.TANK_DRIVE_SUBSYSTEM.drive(-0.3, 0.3);
			}
		}
		currentOutput = output;
	}

	@Override
	protected boolean isFinished() {
		if (Robot.autoOn == false ) {
			return true;
		}
		if  (getPIDController().onTarget() || !Robot.autoOn) {
			System.out.println("Angle finished");
			Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
			Robot.TANK_DRIVE_SUBSYSTEM.resetAll();
			getPIDController().disable();
			return true;
		}
		else {
			return false;
		}

	}

}
