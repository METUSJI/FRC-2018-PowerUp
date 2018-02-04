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
	}
}
