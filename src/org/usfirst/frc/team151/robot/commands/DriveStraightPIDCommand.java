package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveStraightPIDCommand extends PIDCommand {
	
	double distance;
	private int count;
	
	public DriveStraightPIDCommand(double setpoint, double p, double i, double d) {
		super(p, i, d);
		setSetpoint(setpoint);
		getPIDController().setAbsoluteTolerance(3);
		distance = setpoint;
		//Robot.autoOn = true;
		
		//LiveWindow.addActuator(moduleType, channel, component);
	}

	@Override
	protected double returnPIDInput() {
		if (count % 3 == 0) { 
			System.out.println("Travelled: " + Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled());
			System.out.println("Left Encoder Output: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.get());
			System.out.println("Right Encoder Output: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.get());
		}
		count++;
		//Robot.autoOn = true;
		return Robot.TANK_DRIVE_SUBSYSTEM.getDistanceTraveled();
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.TANK_DRIVE_SUBSYSTEM.drive(-output, -output); //direction is inverted
	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(Robot.TANK_DRIVE_SUBSYSTEM.getEncoder() - distance) < 0.5) {
			System.out.println("Finished");
			Robot.TANK_DRIVE_SUBSYSTEM.drive(0, 0);
			getPIDController().disable();
			//Robot.autoOn = false;
			return true;
		}
		else {
			return false;
		}
	}

}
