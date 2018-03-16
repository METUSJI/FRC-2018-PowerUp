package org.usfirst.frc.team151.robot.subsystems;


import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.NeutralClawCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeClawMovementSubsystem extends Subsystem {
	
	Solenoid solenoid = new Solenoid(0); //FIX THIS LATER IN ROBOT MAP
		
	public CubeClawMovementSubsystem( ) {

	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new NeutralClawCommand());
    }
    
    public void openClaw() {
    	solenoid.set(true);
//    	System.out.println("Claw opened");
    }
    
    public void closeClaw() {
    	solenoid.set(false);
    }    
    
    public void neutralClaw() {
    	
    }
}

