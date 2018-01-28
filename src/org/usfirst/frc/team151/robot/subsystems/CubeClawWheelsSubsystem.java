//package org.usfirst.frc.team151.robot.subsystems;
//
//import org.usfirst.frc.team151.robot.Robot;
//import org.usfirst.frc.team151.robot.RobotMap;
//
//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// *
// */
//public class CubeClawWheelsSubsystem extends Subsystem {
//
//	SpeedController left = null;
//	SpeedController right = null;
//	
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//	
//	public CubeClawWheelsSubsystem( ) {
//		left = new Talon(RobotMap.CUBE_LEFT_WHEEL);
//		right = new Talon(RobotMap.CUBE_RIGHT_WHEEL);
//	}
//	
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        
//    }
//    
//    public void spinWheels(double x) {
//    	left.set(x);
//    	right.set(-x);
//    }
//}
//
