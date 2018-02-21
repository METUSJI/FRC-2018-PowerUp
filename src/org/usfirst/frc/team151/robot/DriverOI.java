package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DriverOI extends OI {
	
	/**
	 * The OI for the codriver functions (anything besides driving)
	 * @param channel The USB port of the OI
	 */
	public DriverOI(int channel) {
		super(channel);				
		x = new JoystickButton(joystick, RobotMap.X);
		y = new JoystickButton(joystick, RobotMap.Y);
		a = new JoystickButton(joystick, RobotMap.A);
	}
}
