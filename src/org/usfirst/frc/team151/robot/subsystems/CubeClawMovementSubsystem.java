package org.usfirst.frc.team151.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeClawMovementSubsystem extends Subsystem {
	
//	DoubleSolenoid solenoid = new DoubleSolenoid(RobotMap.RIGHT_CLAW_FORWARD_CHANNEL, RobotMap.RIGHT_CLAW_REVERSE_CHANNEL);
		
	public CubeClawMovementSubsystem( ) {

	}
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new NeutralClawCommand());
    }
    
    public void openClaw() {
//    	solenoid.set(DoubleSolenoid.Value.kForward);
    	System.out.println("Claw opened");
    }
    
    public void closeClaw() {
//    	solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void neutralClaw() {
//    	solenoid.set(DoubleSolenoid.Value.kOff);
    }
    
}

