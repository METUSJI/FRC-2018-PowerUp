package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.CloseClawCommand;
import org.usfirst.frc.team151.robot.commands.NeutralClawCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid; 
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeClawMovementSubsystem extends Subsystem {
	
	DoubleSolenoid solenoid = new DoubleSolenoid(RobotMap.RIGHT_CLAW_FORWARD_CHANNEL, RobotMap.RIGHT_CLAW_REVERSE_CHANNEL);
		
	public CubeClawMovementSubsystem( ) {

	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new NeutralClawCommand());
    }
    
    public void openClaw() {
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void closeClaw() {
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void neutralClaw() {
    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
}

