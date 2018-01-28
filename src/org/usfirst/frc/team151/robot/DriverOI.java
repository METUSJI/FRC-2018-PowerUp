package org.usfirst.frc.team151.robot;


//import org.usfirst.frc.team151.robot.commands.CloseClawCommand;
//import org.usfirst.frc.team151.robot.commands.NeutralClawCommand;
//import org.usfirst.frc.team151.robot.commands.OpenClawCommand; 

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DriverOI extends OI {
	
	/**
	 * The OI for the codriver functions (anything besides driving)
	 * @param channel The USB port of the OI
	 */
	public DriverOI(int channel) {
		super(channel);
		
//		x = new JoystickButton(joystick, 3);
//		y = new JoystickButton(joystick, 4);
//		Button leftTrigger = new JoystickButton(joystick, 7);
		
//		x.whenPressed(new ElevatorLiftCommand());
//		x.whenReleased(new ElevatorNeutralCommand());
//		y.whenPressed(new ElevatorDropCommand());
//		y.whenReleased(new ElevatorNeutralCommand());
		
		x = new JoystickButton(joystick, RobotMap.X);
		y = new JoystickButton(joystick, RobotMap.Y);
		a = new JoystickButton(joystick, RobotMap.A);
		
//		x.whenPressed(new CloseClawCommand());
//		y.whenPressed(new OpenClawCommand());
//		a.whenPressed(new NeutralClawCommand());
				
	}
}
