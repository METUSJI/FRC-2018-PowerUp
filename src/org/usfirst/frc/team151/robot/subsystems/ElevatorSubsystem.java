package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.MoveElevatorWithJoysticksCommand;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDSource; 
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class ElevatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private double TOTAL_HEIGHT = 19.0; //consult design for height of elevator
	private double INIT_HEIGHT = 0.0;
	
	private SpeedController elevator = null;
	Potentiometer height = new AnalogPotentiometer(RobotMap.ELEVATOR_ANALOG_INPUT, TOTAL_HEIGHT);

	public ElevatorSubsystem() {
		elevator = new Victor(RobotMap.ELEVATOR_MOTOR);
	}
	
	
    public void initDefaultCommand() {
        setDefaultCommand(new MoveElevatorWithJoysticksCommand());
    }
    
    
    public void moveElevator(double speed) {
    	elevator.set(speed);
    }
    
    public void manualElevator(OI oi) {
    	double speed = oi.getJoystick().getRawAxis(RobotMap.RIGHT_JOYSTICK_VERTICAL_AXIS);
    	elevator.set(speed);
    }
    
    /**
     * Uses a string potentiometer to measure the height of the elevator
     * @return The current height of the elevator in inches
     */
    public double getHeight() {
    	return height.get();
    }
}

