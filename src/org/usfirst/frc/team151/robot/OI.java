package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joystick = null;

	protected JoystickButton x;
	protected JoystickButton a;
	protected JoystickButton b;
	protected JoystickButton y;
	protected JoystickButton leftBumper;
	protected JoystickButton rightBumper;
	protected JoystickButton leftTrigger;
	protected JoystickButton rightTrigger;
	protected JoystickButton back;
	protected JoystickButton start;
	protected JoystickButton leftJoyDown;
	protected JoystickButton rightJoyDown;

	public OI(int joystickChannel) {		
		joystick = new Joystick(joystickChannel);	
		x = null; 
		a = null;
		b = null;
		y = null;
		leftBumper = null;
		rightBumper = null;
		leftTrigger = null;
		rightTrigger = null;
		back = null;
		start = null;
		leftJoyDown = null;
		rightJoyDown = null;
	}

	public Joystick getJoystick() {
		return joystick;
	}	
}
