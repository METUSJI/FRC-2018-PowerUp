package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoysticksCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TankDriveSubsystem extends Subsystem {
	
	private SpeedController leftRear = null;
	private SpeedController leftFront = null;
	private SpeedController rightRear = null;
	private SpeedController rightFront = null;
	
	private SpeedControllerGroup right = null;
	private SpeedControllerGroup left = null;
	
	public Encoder leftEnc = null;
	public Encoder rightEnc = null;
	
//	private ADXRS450_Gyro gyro = null;
	
	private DifferentialDrive drive = null;
	
	public TankDriveSubsystem() {
		leftRear = new Talon(RobotMap.DRIVE_LEFT_REAR);
		leftFront = new Talon(RobotMap.DRIVE_LEFT_FRONT);
		rightRear = new Talon(RobotMap.DRIVE_RIGHT_REAR);
		rightFront = new Talon(RobotMap.DRIVE_RIGHT_FRONT);
	
		right = new SpeedControllerGroup(rightFront, rightRear);
		left = new SpeedControllerGroup(leftFront, leftRear);
		
		right.setInverted(true);
		left.setInverted(true);
		
		drive = new DifferentialDrive(left, right);
		
		leftEnc = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A, 
				RobotMap.LEFT_DRIVE_ENCODER_B, false, EncodingType.k4X);
		rightEnc = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A, 
				RobotMap.RIGHT_DRIVE_ENCODER_B, false, EncodingType.k4X);
		
		leftEnc.setDistancePerPulse(Robot.DISTANCE_PER_PULSE);
		rightEnc.setDistancePerPulse(Robot.DISTANCE_PER_PULSE);
		
		leftEnc.reset();
		
//		gyro = new ADXRS450_Gyro();
//		gyro.calibrate();
//		gyro.reset();
	}
	
	/**
	 * Set the default command to DriveWithJoysticksCommand so that the drive train can always be driven with joysticks
	 */
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveWithJoysticksCommand());
	}
	
	/**
	 * Drive the robot based on user input
	 * @param oi The OI (operator interface) the driving is based on
	 */
	public void drive(OI oi) {
		double left = 0.75 * deadzone(oi, RobotMap.LEFT_JOYSTICK_VERTICAL_AXIS);
		double right = 0.80 * deadzone(oi, RobotMap.RIGHT_JOYSTICK_VERTICAL_AXIS);
		
		drive(left, right);
	}
	
	public void driveArcade(OI oi) {
		double throttle = deadzone(oi, RobotMap.LEFT_JOYSTICK_VERTICAL_AXIS);
		double turn = deadzone(oi, RobotMap.RIGHT_JOYSTICK_LATERAL_AXIS);
		
		if(throttle != 0)
			turn = turn * Math.abs(throttle);
		
		double initLeft = throttle - turn;
		double initRight = throttle + turn;
		
		double left = initLeft + skim(initRight);
		double right = initRight + skim(initLeft);
		
		drive(left, right);
	}
	
	private double skim(double speed) {
		//Maximum PWM range is -1<=x<=1, so make up for that
		if(speed > 1.0) {
			return -(speed - 1.0);
		} else if(speed < -1.0) {
			return -(speed + 1.0);
		}
		return 0;
	}
	
	/**
	 * Drive based on two distinct motor power percentages
	 * @param left The percentage of full power from <code>-1</code> to <code>1</code> for the left side of the drive train
	 * @param right The percentage of full power from <code>-1</code> to <code>1</code> for the right side of the drive train
	 */
	public void drive(double left, double right) {
		drive.tankDrive(left, right);
	}
	
	/**
	 * A deadzone to prevent random movement when no user is interacting with the OI
	 * @param oi The OI that controls the drive train
	 * @param axis The axis of the OI that drive train power is based on
	 * @return A motor power percentage double. <br>If the joystick is in the deadzone, return 0.<br>Else, return the joystick position.
	 */
	private double deadzone(OI oi, int axis) {
		double rawAxis = oi.getJoystick().getRawAxis(axis);
		if(rawAxis > 0.03 || rawAxis < -0.03) {
			return rawAxis;
		} else {
			return 0;
		}
	}
	
	/**
	 * Return the linear distance travelled by the robot.
	 * @return The linear distance travelled by the robot by averaging the distance recorded by the two encoders.
	 */
	public double getDistanceTraveled() {
		return leftEnc.getDistance();
	}
	
	/**
	 * The angle of the robot.
	 * @return The angle of the robot based on the gyro.
	 */
//	public double getAngle() {
//		return gyro.getAngle();
//	}
//	
	public void resetEncoders() {
		leftEnc.reset();
		rightEnc.reset();
	}
//	
//	public void resetGyro() {
//		gyro.reset();
//	}
//	
//	public void resetAll() {
//		leftEnc.reset();
//		gyro.reset();
//	}
	
	public double getEncoder() {
		return (leftEnc.getDistance() + rightEnc.getDistance()) / 2;
	}

}
