package org.usfirst.frc.team151.robot;


//import org.usfirst.frc.team151.robot.commands.CloseClawCommand;
////import org.usfirst.frc.team151.robot.commands.MoveElevatorPIDCommand;
//import org.usfirst.frc.team151.robot.commands.NeutralClawCommand;
//import org.usfirst.frc.team151.robot.commands.OpenClawCommand; 

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class CoDriverOI extends OI {
	
	/**
	 * The OI for the codriver functions (anything besides driving)
	 * @param channel The USB port of the OI
	 */
	public CoDriverOI(int channel) {
		super(channel);
		
		x = new JoystickButton(joystick, RobotMap.X);
		y = new JoystickButton(joystick, RobotMap.Y);
		a = new JoystickButton(joystick, RobotMap.A);
		b = new JoystickButton(joystick, RobotMap.B);
		
//		x.whenPressed(new CloseClawCommand());
//		y.whenPressed(new OpenClawCommand());
//		a.whenPressed(new NeutralClawCommand());
				
	}
}
