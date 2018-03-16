
package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team151.robot.commands.AutoCenterSwitchCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoEdgeCrossBaselineCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoEdgeOppositeScaleCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoEdgeSameScaleCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoEdgeSwitchCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoMiddleCrossBaselineCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoSameSwitchOnlyCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutoTimedDriveCommand;
import org.usfirst.frc.team151.robot.commands.AutoTimedDriveStopCommandGroup;
import org.usfirst.frc.team151.robot.commands.DriveEncoderCommand;
import org.usfirst.frc.team151.robot.commands.DriveStraightPIDCommand;
import org.usfirst.frc.team151.robot.subsystems.CubeClawMovementSubsystem;
//import org.usfirst.frc.team151.robot.subsystems.ElevatorPIDSubsystem;
//import org.usfirst.frc.team151.robot.subsystems.ElevatorPistonSubsystem;
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

	public static final TankDriveSubsystem TANK_DRIVE_SUBSYSTEM = new TankDriveSubsystem();
//	public static final ElevatorPIDSubsystem ELEVATOR_PID_SUBSYSTEM = new ElevatorPIDSubsystem();
	public static final CubeClawMovementSubsystem CUBE_CLAW_MOVEMENT_SUBSYSTEM = new CubeClawMovementSubsystem();
	public static final CubeClawWheelsSubsystem CUBE_CLAW_WHEELS_SUBSYSTEM = new CubeClawWheelsSubsystem();
//	public static final ElevatorPistonSubsystem ELEVATOR_PISTON_SUBSYSTEM = new ElevatorPistonSubsystem();

	public static boolean elevatorPrint = false;
	public static boolean autoPrint = false;
	public static boolean drivePrint = false;

	public static int fieldPosition = 0;
	public static String strategy = null;
	
	/**
	 * The distance per pulse on the encoder, based on the wheel diameter divided by
	 * 360 pulses per  ,llpp-     revolution
	 */
//	public static final double DISTANCE_PER_PULSE = 7.5 * Math.PI / 2048 / 2; //actual code
	public static final double DISTANCE_PER_PULSE =  6 * Math.PI / 360; //test robot

	public static DriverOI driverOI;
	public static CoDriverOI coDriverOI;

	public static boolean shooterOn = false;
	public static boolean autoReleaseOn = false;

//	public static final double kPe = 0.2;
//	public static final double kIe = 0.01;
//	public static final double kDe = 0;
//
//	public static final double kPd = 0.04;
//	public static final double kId = 0;
//	public static final double kDd = 0;

	public static final double kPt = 0.035;
	public static final double kIt = 0;
	public static final double kDt = 0.006;
	
	public static final double kPe = 0.2;
	public static final double kIe = 0.01;
	public static final double kDe = 0;

	public static double kPd = 0.04;
	public static double kId = 0;
	public static double kDd = 0;
	
	public static double startTime = 0;
	public static double endTime = 0;
	public static double elapsedTime = 0;
	public static boolean autoReleasePrereqOn = false;
	
	public static double driveEncoder1 = 0;
	public static double driveEncoder2 = 0;

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
	SendableChooser<String> positionChooser = new SendableChooser<String>();
	SendableChooser<String> strategyChooser = new SendableChooser<String>();

	public static boolean autoOn = false;
	public static boolean elevatorPIDControl = false;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		driverOI = new DriverOI(0);
		coDriverOI = new CoDriverOI(1);

		
		positionChooser.addObject("Left", "Left"); 
		positionChooser.addDefault("Middle", "Middle");
		positionChooser.addObject("Right", "Right");

		strategyChooser.addDefault("Brick", "Brick");
		strategyChooser.addObject("Switch", "Switch");
		strategyChooser.addObject("Scale", "Scale");
		strategyChooser.addObject("Pass Auto Line", "Pass Auto Line");
		strategyChooser.addObject("Skewed Switch", "Skewed Switch");
		strategyChooser.addObject("Timed Drive", "Timed Drive");

		SmartDashboard.putData("Position Chooser", positionChooser);
		SmartDashboard.putData("Strategy Chooser", strategyChooser);

		prefs = Preferences.getInstance();
		whichRobot = prefs.getInt("whichRobot", 0);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

		SmartDashboard.putString("RoboRio Name", WhichRobot.convertIntToRobot(whichRobot).toString());

		// UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);

		SmartDashboard.putNumber("Angle", Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle()); // put angle value to
		// shuffleboard
		prefs.putDouble("kPd", kPd);
		prefs.putDouble("kId", kId);
		prefs.putDouble("kDd", kDd);
		
		prefs.putDouble("Initial Speed", driveEncoder1);
		prefs.putDouble("Second Speed", driveEncoder2);
	}
	//
	// public Command chooseAutonomousCommand(int position, String strategy) {
	// return null;
	// }

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {
//		ELEVATOR_PID_SUBSYSTEM.disable();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		kPd = prefs.getDouble("kPd", 0);
		kId = prefs.getDouble("kId", 0);
		kDd = prefs.getDouble("kDd", 0);
		
//		System.out.println("kPd" + kPd);
//		System.out.println("kId" + kId);
//		System.out.println("kDd" + kDd);
		
		FieldData.gameData = "";
		FieldData.isDataValid();
		
		String strategy = strategyChooser.getSelected();
		String pos = positionChooser.getSelected();
		
		if (strategy.equals("Brick")) {
			autonomousCommand = null;
		}
		else if (strategy.equals("Timed Drive")) {
			autonomousCommand = new AutoTimedDriveStopCommandGroup();
		}
		else if (strategy.equals("Pass Auto Line") && positionChooser.getSelected().equals("Middle")) {
			autonomousCommand = new AutoMiddleCrossBaselineCommandGroup();
		}
		else if (strategy.equals("Pass Auto Line") && (pos.equals("Left") || pos.equals("Right"))) {
			autonomousCommand = new AutoEdgeCrossBaselineCommandGroup();
		}
		else if (pos.equals("Middle") && strategy.equals("Skewed Switch")) {
//			autonomousCommand = new AutoSameSwitchOnlyCommandGroup();
//			autonomousCommand = new DriveEncoderCommand(36, driveEncoder1, driveEncoder2);
			autonomousCommand = new DriveStraightPIDCommand(24, Robot.kPd, Robot.kId, Robot.kDd);
		}
		else if (strategy.equals("Switch") && pos.equals("Right")) {
			if (FieldData.checkFieldPosition(FieldThings.SWITCH, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeSwitchCommandGroup(-1);
			}
			else if (FieldData.checkFieldPosition(FieldThings.SWITCH, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeCrossBaselineCommandGroup();
			}
			else {
				autonomousCommand = null;
			}
		}
		else if (strategy.equals("Switch") && pos.equals("Middle")) {
			if (FieldData.checkFieldPosition(FieldThings.SWITCH, Direction.LEFT)) {
				autonomousCommand = new AutoCenterSwitchCommandGroup(1);
			}
			else if (FieldData.checkFieldPosition(FieldThings.SWITCH, Direction.RIGHT)) {
				autonomousCommand = new AutoCenterSwitchCommandGroup(-1);
			}
			else {
				autonomousCommand = null;
			}
		}
		else if (strategy.equals("Switch") && pos.equals("Left")) {
			if (FieldData.checkFieldPosition(FieldThings.SWITCH, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeSwitchCommandGroup(1);
			}
			else if (FieldData.checkFieldPosition(FieldThings.SWITCH, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeCrossBaselineCommandGroup();
			}
			else {
				autonomousCommand = null;
			}
		}
		else if (strategy.equals("Scale") && pos.equals("Left")) {
			if (FieldData.checkFieldPosition(FieldThings.SCALE, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeSameScaleCommandGroup(1);
			}
			else if (FieldData.checkFieldPosition(FieldThings.SCALE, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeOppositeScaleCommandGroup(1);
			}
			else {
				autonomousCommand = null;
			}
		}
		else if (strategy.equals("Scale") && pos.equals("Right")) {
			if (FieldData.checkFieldPosition(FieldThings.SCALE, Direction.RIGHT)) {
				autonomousCommand = new AutoEdgeSameScaleCommandGroup(-1);
			}
			else if (FieldData.checkFieldPosition(FieldThings.SCALE, Direction.LEFT)) {
				autonomousCommand = new AutoEdgeOppositeScaleCommandGroup(-1);
			}
			else {
				autonomousCommand = new AutoEdgeCrossBaselineCommandGroup();
			}
		}
		else {
			autonomousCommand = null;
		}

//		System.out.println("Strategy: " + strategy);
//		System.out.println("Position: " + pos);
		
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
		Robot.TANK_DRIVE_SUBSYSTEM.resetEncoders();
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
			if (RobotMap.hasGyro)
				Robot.TANK_DRIVE_SUBSYSTEM.gyro.reset();
		}
		autoOn = false;
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("Left Pulses: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.get() + "Distance: " + Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.getDistance());
		System.out.println("Right Pulses: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.get()  + "Distance: " + Robot.TANK_DRIVE_SUBSYSTEM.rightEnc.getDistance());
		System.out.println("Gyro angle: " + Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle());
//		System.out.println("Averaged Distance travelled: " + Robot.TANK_DRIVE_SUBSYSTEM.getEncoder());
//		System.out.println("Gyro angle: " + Robot.TANK_DRIVE_SUBSYSTEM.gyro.getAngle());
	}

	/**  
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
