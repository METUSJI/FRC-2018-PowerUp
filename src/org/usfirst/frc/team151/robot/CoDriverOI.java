package org.usfirst.frc.team151.robot;


import org.usfirst.frc.team151.robot.commands.ChangeElevatorSetpointCommand;
//import org.usfirst.frc.team151.robot.commands.CloseClawCommand;
import org.usfirst.frc.team151.robot.commands.DeltaSetpointElevatorCommand;
//import org.usfirst.frc.team151.robot.commands.OpenClawCommand; 
import org.usfirst.frc.team151.robot.commands.DisableElevatorPIDCommand;
import org.usfirst.frc.team151.robot.commands.ElevatorPIDToManualCommandGroup;
import org.usfirst.frc.team151.robot.commands.EnableElevatorPIDCommand;

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
		start = new JoystickButton(joystick, RobotMap.START);
		back = new JoystickButton(joystick, RobotMap.BACK);
		leftJoystick = new JoystickButton(joystick, RobotMap.LEFT_JOYSTICK);
		rightJoystick = new JoystickButton(joystick, RobotMap.RIGHT_JOYSTICK);
		
		leftBumper = new JoystickButton(joystick, RobotMap.LEFT_BUMPER);
		rightBumper = new JoystickButton(joystick, RobotMap.RIGHT_BUMPER);
		
		//ground level: 0.5
		a.whenPressed(new ChangeElevatorSetpointCommand(0.5));
		//switch level: 16
		x.whenPressed(new ChangeElevatorSetpointCommand(5));
		//low/mid scale level: 65
		b.whenPressed(new ChangeElevatorSetpointCommand(10));
		//high scale level: 90
		y.whenPressed(new ChangeElevatorSetpointCommand(16));
		
		back.whenPressed(new DeltaSetpointElevatorCommand(-2));
		start.whenPressed(new DeltaSetpointElevatorCommand(2));
		
		leftJoystick.whenPressed(new ElevatorPIDToManualCommandGroup());
		rightJoystick.whenPressed(new EnableElevatorPIDCommand());
						
//		leftBumper.whenPressed(new OpenClawCommand());
//		rightBumper.whenPressed(new CloseClawCommand());
	}
}
