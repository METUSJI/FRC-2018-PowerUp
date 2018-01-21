package org.usfirst.frc.team151.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//TALONS
	public static final int DRIVE_LEFT_REAR = 2;
	public static final int DRIVE_LEFT_FRONT = 0;
	public static final int DRIVE_RIGHT_REAR = 3;
	public static final int DRIVE_RIGHT_FRONT = 1;
	
	public static final int ELEVATOR_MOTOR = 9;
	
	public static final int CUBE_LEFT_WHEEL = 4;
	public static final int CUBE_RIGHT_WHEEL = 5;
	
	//DIO PORTS
	public static final int LEFT_DRIVE_ENCODER_A = 0;
	public static final int LEFT_DRIVE_ENCODER_B = 1;
	
	//SOLENOID CHANNEL
	public static final int RIGHT_CLAW_FORWARD_CHANNEL = 5;
	public static final int RIGHT_CLAW_REVERSE_CHANNEL = 4;
	public static final int LEFT_CLAW_FORWARD_CHANNEL = 1;
	public static final int LEFT_CLAW_REVERSE_CHANNEL = 2;
	
	// ANALOG INPUT/OUTPUT
	public static final int ELEVATOR_ANALOG_INPUT = 0;
	
	//RELAYS
	public static final int SOLENOID_RELAY = 0;
		
	//JOYSTICK BUTTONS
	public static final int X = 3;
	public static final int A = 1;
	public static final int B = 2;
	public static final int Y = 4;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	public static final int BACK = 7;
	public static final int START = 8;
	public static final int LEFT_JOYSTICK = 9;
	public static final int RIGHT_JOYSTICK = 10;
	
	//JOYSTICK AXES
	public static final int LEFT_JOYSTICK_LATERAL_AXIS = 0;
	public static final int LEFT_JOYSTICK_VERTICAL_AXIS = 1;
	public static final int RIGHT_JOYSTICK_LATERAL_AXIS = 4;
	public static final int RIGHT_JOYSTICK_VERTICAL_AXIS = 5;
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;

}
