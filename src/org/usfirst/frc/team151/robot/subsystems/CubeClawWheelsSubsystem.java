package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.MoveCubeWheelsCommand;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem; 

/**
 *
 */
public class CubeClawWheelsSubsystem extends Subsystem {

	SpeedController left = null;
	SpeedController right = null;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CubeClawWheelsSubsystem( ) {
		left = new Victor(RobotMap.CUBE_LEFT_WHEEL);
		right = new Victor(RobotMap.CUBE_RIGHT_WHEEL);
		right.setInverted(true);
	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new MoveCubeWheelsCommand());
    }
    
    public void spinWheelsTeleop() {
    	double forwardSpeed = Robot.coDriverOI.getJoystick().getRawAxis(2);
    	double reverseSpeed = Robot.coDriverOI.getJoystick().getRawAxis(3);
    	left.set((forwardSpeed - reverseSpeed) / 2);
    	right.set((forwardSpeed - reverseSpeed) / 2);
    }
    
    public void spinWheelsAuto(double speed) {
    	left.set(speed);
    	right.set(speed);
    }
}

