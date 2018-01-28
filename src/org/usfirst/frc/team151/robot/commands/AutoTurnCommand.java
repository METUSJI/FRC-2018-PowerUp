//package org.usfirst.frc.team151.robot.commands;
//
//import org.usfirst.frc.team151.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.PIDCommand;
//
//public class AutoTurnCommand extends PIDCommand {
//
//	public AutoTurnCommand(double setpoint, double p, double i, double d) {
//		super(p, i, d);
//		setSetpoint(setpoint);
//	}
//
//	@Override
//	protected double returnPIDInput() {
//		return Robot.TANK_DRIVE_SUBSYSTEM.getAngle();
//	}
//
//	@Override
//	protected void usePIDOutput(double output) {
//		Robot.TANK_DRIVE_SUBSYSTEM.drive(output,  -output);
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return isFinished(); //check whether this works - print statements
//	}
//
//}
