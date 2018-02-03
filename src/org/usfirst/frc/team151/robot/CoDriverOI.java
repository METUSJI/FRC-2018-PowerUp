//package org.usfirst.frc.team151.robot;
//
//
//import org.usfirst.frc.team151.robot.commands.MoveElevatorPIDCommand;
//
//import org.usfirst.frc.team151.robot.commands.CloseClawCommand;
//import org.usfirst.frc.team151.robot.commands.OpenClawCommand; 
//
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
//
//public class CoDriverOI extends OI {
//	
//	/**
//	 * The OI for the codriver functions (anything besides driving)
//	 * @param channel The USB port of the OI
//	 */
//	public CoDriverOI(int channel) {
//		super(channel);
//		
//		x = new JoystickButton(joystick, RobotMap.X);
//		y = new JoystickButton(joystick, RobotMap.Y);
//		a = new JoystickButton(joystick, RobotMap.A);
//		b = new JoystickButton(joystick, RobotMap.B);
//		
//		leftBumper = new JoystickButton(joystick, RobotMap.LEFT_BUMPER);
//		rightBumper = new JoystickButton(joystick, RobotMap.RIGHT_BUMPER);
//		
//		//ground level
//		a.whenPressed(new MoveElevatorPIDCommand(0, 90, 0.01, 0));
//		//switch
//		x.whenPressed(new MoveElevatorPIDCommand(24, 90, 0.01, 0));
//		//scale when in low/middle position
//		b.whenPressed(new MoveElevatorPIDCommand(60, 90, 0.01, 0));
//		//scale when in high position
//		y.whenPressed(new MoveElevatorPIDCommand(84, 90, 0.01, 0));
//				
//		leftBumper.whenPressed(new OpenClawCommand());
//		rightBumper.whenPressed(new CloseClawCommand());
//	}
//}
