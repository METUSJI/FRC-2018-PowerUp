package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ElevatorPistonSubsystem extends Subsystem {
	
	Solenoid sol = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public ElevatorPistonSubsystem() {
		sol = new Solenoid(1);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setOn() {
    	sol.set(true);
    }
    
    public void setOff() {
    	sol.set(false);
    }
}

