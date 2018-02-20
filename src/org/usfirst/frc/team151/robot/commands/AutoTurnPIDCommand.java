package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurnPIDCommand extends PIDCommand {
	
	double angle;
	private int count = 0;
	double currentOutput = 0;
	 
	double MINIMUM_OUTPUT = -0.43;
	double MAXIMUM_OUTPUT = 0.43;
	
	public AutoTurnPIDCommand(double setpoint, double p, double i, double d) {
		super(p, i, d);
		setSetpoint(setpoint);
		getPIDController().setAbsoluteTolerance(2.5);
		angle = setpoint;
//		Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
		//Robot.autoOn = true;
		getPIDController().setOutputRange(MINIMUM_OUTPUT, MAXIMUM_OUTPUT);
		
		//LiveWindow.addActuator(moduleType, channel, component);
	}

	@Override
	protected double returnPIDInput() {
		if (count % 1 == 0) {  
			System.out.println("Current Angle: " + Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle());
//			SmartDashboard.putNumber("Angle", Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle()); 
		}
		count++;
		//Robot.autoOn = true;
		return Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (Math.abs(output) > 0.31) {
			Robot.TANK_DRIVE_SUBSYSTEM.drive(-output, output); 
		}
		else {
			if (output < 0) {
				Robot.TANK_DRIVE_SUBSYSTEM.drive(0.275, -0.275);
			}
			else {
				Robot.TANK_DRIVE_SUBSYSTEM.drive(-0.275, 0.275);
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
			//Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
			getPIDController().disable();
			//Robot.autoOn = false;
			return true;
		}
		else {
//			System.out.println("Current motor output: " + currentOutput);
//			System.out.println("is not finished");
			return false;
		}
		
	}

}
