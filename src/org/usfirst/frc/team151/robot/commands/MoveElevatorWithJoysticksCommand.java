//package org.usfirst.frc.team151.robot.commands;
//
//import org.usfirst.frc.team151.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class MoveElevatorWithJoysticksCommand extends Command {
//
//
//	public MoveElevatorWithJoysticksCommand() {
//		requires(Robot.ELEVATOR_PID_SUBSYSTEM);
//	}
//
//	protected void initialize() {
//
//	}
//
//	// Called repeatedly when this Command is scheduled to run
//	protected void execute() {
//		Robot.ELEVATOR_PID_SUBSYSTEM.manualElevator(Robot.coDriverOI);
//	}
//
//	protected boolean isFinished() {
//		return Robot.elevatorPIDControl;
//	}
//
//	protected void end() {
//
//	}
//
//	protected void interrupted() {
//		end();
//	}
//}
