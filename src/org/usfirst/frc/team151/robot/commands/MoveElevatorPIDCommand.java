//package org.usfirst.frc.team151.robot.commands;
//
//import org.usfirst.frc.team151.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.PIDCommand;
// 
//public class MoveElevatorPIDCommand extends PIDCommand {
//	
//	public MoveElevatorPIDCommand(double setpoint, double p, double i, double d) {
//		super(p, i, d);
//		setSetpoint(setpoint);
//		
//	}
//
//	@Override
//	protected double returnPIDInput() {
//		return Robot.ELEVATOR_SUBSYSTEM.getHeight();
//	}
//
//	@Override
//	protected void usePIDOutput(double output) {
//		Robot.ELEVATOR_SUBSYSTEM.moveElevator(output);
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return false;
//	}
//
//}
