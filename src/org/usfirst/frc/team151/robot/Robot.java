
package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.utils.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team151.robot.commands.AutoTurnPIDCommand;
import org.usfirst.frc.team151.robot.commands.DriveStraightEncoderCommand;
import org.usfirst.frc.team151.robot.commands.DriveStraightPIDCommand;
//import org.usfirst.frc.team151.robot.subsystems.ElevatorSubsystem;
//import org.usfirst.frc.team151.robot.subsystems.CubeClawMovementSubsystem;
//import org.usfirst.frc.team151.robot.subsystems.CubeClawWheelsSubsystem;
import org.usfirst.frc.team151.robot.subsystems.TankDriveSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final TankDriveSubsystem TANK_DRIVE_SUBSYSTEM = new TankDriveSubsystem();
//	public static final ElevatorSubsystem ELEVATOR_SUBSYSTEM = new ElevatorSubsystem();
//	public static final CubeClawMovementSubsystem CUBE_CLAW_MOVEMENT_SUBSYSTEM = new CubeClawMovementSubsystem();
//	public static final CubeClawWheelsSubsystem CUBE_CLAW_WHEELS_SUBSYSTEM = new CubeClawWheelsSubsystem();
	
	

	/**
	 * The distance per pulse on the encoder, based on the wheel diameter divided by 1440 pulses per revolution
	 */
	public static final double DISTANCE_PER_PULSE = 7.65 * Math.PI / 360;
	
	public static DriverOI driverOI;
//	public static CoDriverOI coDriverOI;
	
	public static boolean shooterOn = false;
	public static boolean autoReleaseOn = false;
	public static boolean autoReleasePrereqOn = false;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	public static boolean autoOn = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {	
		driverOI = new DriverOI(0);
//		coDriverOI = new CoDriverOI(0); //UNCOMMENT WHEN USING TWO CONTROLLERS
		
//		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
//		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		
		
		//TUNE PID CONSTANTS WHEN USING PID COMMAND 
		autonomousCommand = new AutoTurnPIDCommand(45, 0.035, 0, 0.0); //d constant was 0.002
		SmartDashboard.putNumber("Angle", Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle()); // put angle value to shuffleboard
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() { 
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autoOn = true;
			Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
			autonomousCommand.start();
		}

		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
			autoOn = false;
			Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
		}
	}
 
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
//		Scheduler.getInstance().run();
//		System.out.println("LeftCount: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.get());
//		System.out.println("RightCount: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.get());
//		Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
		System.out.println("Current Angle: " + Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
