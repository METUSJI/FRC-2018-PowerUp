//package org.usfirst.frc.team151.robot.subsystems;
//
//import org.usfirst.frc.team151.robot.RobotMap;
//import org.usfirst.frc.team151.robot.commands.StartElevatorPistonCommand;
//
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// *
// */
//public class ElevatorPistonSubsystem extends Subsystem {
//	
//	Solenoid sol = null;
//
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//	
//	public ElevatorPistonSubsystem() {
//		sol = new Solenoid(RobotMap.PISTON_RELAY);
//	}
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    	setDefaultCommand(new StartElevatorPistonCommand());
//    }
//    
//    public void setOn() {
//    	sol.set(true);
//    }
//    
//    public void setOff() {
//    	sol.set(false);
//    }
//}
//
