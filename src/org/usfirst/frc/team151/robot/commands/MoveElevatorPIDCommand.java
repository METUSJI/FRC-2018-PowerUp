//package org.usfirst.frc.team151.robot.commands;
//
//import org.usfirst.frc.team151.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.PIDCommand;
// 
//public class MoveElevatorPIDCommand extends PIDCommand {
//	private int count;
//
//	public MoveElevatorPIDCommand(double setpoint, double p, double i, double d) {
//		super(p, i, d);
//		setSetpoint(setpoint);
//		getPIDController().setAbsoluteTolerance(2);
//		//Robot.autoOn = true;
//		//LiveWindow.addActuator(moduleType, channel, component);
//	}
//
//	@Override
//	protected double returnPIDInput() {
//		if (count % 3 == 0) {  
//			System.out.println("Height: " + Robot.ELEVATOR_SUBSYSTEM.getHeight());
//		}
//		count++;
//		//Robot.autoOn = true;
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
//		boolean finished = getPIDController().onTarget();
//		if(finished) {
//			System.out.println("Finished");
//			Robot.ELEVATOR_SUBSYSTEM.moveElevator(0);
//			getPIDController().disable();
//			//Robot.autoOn = false;
//		}
//		return finished;
//	}
//
//}
