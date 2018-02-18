
package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team151.robot.commands.AutoCenterLeftCrossBaselineCommand;
import org.usfirst.frc.team151.robot.commands.AutoCenterSwitchCommand;
import org.usfirst.frc.team151.robot.commands.AutoEdgeCrossBaselineCommand;
import org.usfirst.frc.team151.robot.commands.AutoEdgeOppositeScaleCommand;
import org.usfirst.frc.team151.robot.commands.AutoEdgeSameScaleCommand;
import org.usfirst.frc.team151.robot.commands.AutoEdgeSwitchCommand;
import org.usfirst.frc.team151.robot.commands.AutoSameScaleOnlyCommand;
import org.usfirst.frc.team151.robot.commands.AutoTimedDriveCommand;
import org.usfirst.frc.team151.robot.commands.AutoTurnPIDCommand;
import org.usfirst.frc.team151.robot.commands.CloseClawCommand;
import org.usfirst.frc.team151.robot.commands.EnableElevatorPIDCommand;
import org.usfirst.frc.team151.robot.commands.TestDriveCommand;
import org.usfirst.frc.team151.robot.subsystems.CubeClawMovementSubsystem;
import org.usfirst.frc.team151.robot.subsystems.ElevatorPIDSubsystem;
import org.usfirst.frc.team151.robot.subsystems.ElevatorPistonSubsystem;
import org.usfirst.frc.team151.robot.subsystems.CubeClawWheelsSubsystem;
import org.usfirst.frc.team151.robot.subsystems.TankDriveSubsystem;
import org.usfirst.frc.team151.robot.utils.FieldData;
import org.usfirst.frc.team151.robot.utils.FieldData.Direction;
import org.usfirst.frc.team151.robot.utils.FieldData.FieldThings;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//julia crying 

	public static final TankDriveSubsystem TANK_DRIVE_SUBSYSTEM = new TankDriveSubsystem();
	public static final ElevatorPIDSubsystem ELEVATOR_PID_SUBSYSTEM = new ElevatorPIDSubsystem();
	public static final CubeClawMovementSubsystem CUBE_CLAW_MOVEMENT_SUBSYSTEM = new CubeClawMovementSubsystem();
	public static final CubeClawWheelsSubsystem CUBE_CLAW_WHEELS_SUBSYSTEM = new CubeClawWheelsSubsystem();
	public static final ElevatorPistonSubsystem ELEVATOR_PISTON_SUBSYSTEM = new ElevatorPistonSubsystem();

	public static int fieldPosition = 0;
	public static String strategy = null;
	/**
	 * The distance per pulse on the encoder, based on the wheel diameter divided by 1440 pulses per revolution
	 */
	public static final double DISTANCE_PER_PULSE = 7.65 * Math.PI / 360;
	
	public static DriverOI driverOI;
	public static CoDriverOI coDriverOI;
	
	public static boolean shooterOn = false;
	public static boolean autoReleaseOn = false;
	
	public static final double kPe = 0.2;
	public static final double kIe = 0.01;
	public static final double kDe = 0;
	
	public static final double kPd = 0.02;
	public static final double kId = 0;
	public static final double kDd = 0;
	
	public static final double kPt = 0.035;
	public static final double kIt = 0;
	public static final double kDt = 0;
	
	public static double startTime = 0;
	public static double endTime = 0;
	public static double elapsedTime = 0;
	public static boolean autoReleasePrereqOn = false;
	
	Preferences prefs;
	public static int whichRobot;
	
	public enum WhichRobot {
		DEFAULT, DA_VINCI, NEWTON, TESLA;
		
		public static WhichRobot convertIntToRobot(int robotInt) {
			for (WhichRobot robot : WhichRobot.values()) {
				if (robot.ordinal() == robotInt) {
					return robot;
				}
			}
			return null;
		}
	}
	
	Command autonomousCommand;
	SendableChooser<Integer> positionChooser = new SendableChooser<Integer>();
	SendableChooser<String> strategyChooser = new SendableChooser<String>();
	
	public static boolean autoOn = false;
	public static boolean elevatorPIDControl = false;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driverOI = new DriverOI(0);
		coDriverOI = new CoDriverOI(1);
		
		FieldData fieldData = new FieldData();
		
//		chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		positionChooser.addObject("Left", 1); // I have no idea what the command or number is supposed to be here - Andrew
		positionChooser.addDefault("Middle", 2);
		positionChooser.addObject("Right", 3);
		
		strategyChooser.addDefault("Brick", "brick");
		strategyChooser.addObject("Switch", "switch");
		strategyChooser.addObject("Scale", "scale");
		strategyChooser.addObject("Pass Auto Line", "pass");
		strategyChooser.addObject("Skewed Switch", "skewed");
		strategyChooser.addObject("Timed Drive", "timed drive");
		
		SmartDashboard.putData("Position Chooser", positionChooser);
		SmartDashboard.putData("Strategy Chooser", strategyChooser);
		SmartDashboard.putData("AutoTurn", new AutoTurnPIDCommand(45, 0, 0, 0));
		SmartDashboard.putData("Switch", new EnableElevatorPIDCommand());
		
		prefs = Preferences.getInstance();
		whichRobot = prefs.getInt("whichRobot", 0);
		
		SmartDashboard.putString("RoboRio Name", WhichRobot.convertIntToRobot(whichRobot).toString());
		
//		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
		
		if (strategyChooser.getSelected().equals("Switch") && positionChooser.getSelected().equals("Middle")) {
			if (fieldData.checkFieldPosition(FieldThings.SWITCH, Direction.LEFT)) {
				autonomousCommand = new AutoCenterSwitchCommand(1);
			}
			else if (fieldData.checkFieldPosition(FieldThings.SWITCH, Direction.RIGHT)) {
				autonomousCommand = new AutoCenterSwitchCommand(-1);
			}
			else {
				autonomousCommand = new TestDriveCommand();
			}
		}
		else if (positionChooser.getSelected().equals("Middle") && strategyChooser.getSelected().equals("Pass Auto Line")) {
			autonomousCommand = new AutoCenterLeftCrossBaselineCommand();
		}
		else if (positionChooser.getSelected().equals("Left") && strategyChooser.getSelected().equals("Scale")) {
			if (fieldData.checkFieldPosition(FieldThings.SCALE, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeSameScaleCommand(1);
			}
			else if (fieldData.checkFieldPosition(FieldThings.SCALE, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeOppositeScaleCommand(1);
			}
		}
		else if (positionChooser.getSelected().equals("Left") && strategyChooser.getSelected().equals("Switch")) {
			if (fieldData.checkFieldPosition(FieldThings.SWITCH, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeSwitchCommand(1);
			}
			else if (fieldData.checkFieldPosition(FieldThings.SWITCH, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeCrossBaselineCommand();
			}
		}
		else if (positionChooser.getSelected().equals("Left") && strategyChooser.getSelected().equals("Pass Auto Line")) {
			autonomousCommand = new AutoEdgeCrossBaselineCommand();
		}
		else if (positionChooser.getSelected().equals("Right") && strategyChooser.getSelected().equals("Scale")) {
			if (fieldData.checkFieldPosition(FieldThings.SCALE, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeSameScaleCommand(-1);
			}
			else if (fieldData.checkFieldPosition(FieldThings.SCALE, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeOppositeScaleCommand(-1);
			}
			else {
				autonomousCommand = new AutoEdgeCrossBaselineCommand();
			}
		}
		else if (positionChooser.getSelected().equals("Right") && strategyChooser.getSelected().equals("Switch")) {
			if (fieldData.checkFieldPosition(FieldThings.SWITCH, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeSwitchCommand(-1);
			}
			else if (fieldData.checkFieldPosition(FieldThings.SWITCH, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeCrossBaselineCommand();
			}
		}
		else if (positionChooser.getSelected().equals("Right") && strategyChooser.getSelected().equals("Pass Auto Line")) {
			autonomousCommand = new AutoEdgeCrossBaselineCommand();
		}
		else if (positionChooser.getSelected().equals("Center") && strategyChooser.getSelected().equals("Skewed Switch")) {
			autonomousCommand = new AutoSameScaleOnlyCommand();
		}
		else if (strategyChooser.getSelected().equals("Brick")) {
			autonomousCommand = new CloseClawCommand();
		}
		else if (strategyChooser.getSelected().equals("Timed Drive")) {
			autonomousCommand = new AutoTimedDriveCommand(2.5);
		}
		SmartDashboard.putNumber("Angle", Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle()); // put angle value to shuffleboard
	}

	public Command chooseAutonomousCommand(int position, String strategy) {
		return null;
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		ELEVATOR_PID_SUBSYSTEM.disable();
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

		System.out.println("whichRobot: " + whichRobot);
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
			Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
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
			if(RobotMap.hasGyro)
				Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
		}
		autoOn = false;
	}
 
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//TODO uncomment
		Scheduler.getInstance().run();	
//		System.out.println("Current Angle: " + Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
