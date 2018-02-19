package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class ElevatorPIDSubsystem extends PIDSubsystem {
	private static final double TOTAL_HEIGHT = 28.0;
	private static final double EMPIRICAL_SCALE = 1.13765642776;
	private static final double OFFSET = -1.19;

//	DoubleSolenoid brake = new DoubleSolenoid(RobotMap.BRAKE_FORWARD_CHANNEL, RobotMap.BRAKE_REVERSE_CHANNEL);
	private SpeedController elevator = null;
	Potentiometer height = new AnalogPotentiometer(RobotMap.ELEVATOR_ANALOG_INPUT);
	private DigitalInput lowerSwitch = null;
	private DigitalInput upperSwitch = null;
	private double lastManualHeight = 0;

	public ElevatorPIDSubsystem() {
		super(Robot.kPe, Robot.kIe, Robot.kDe);
		lowerSwitch = new DigitalInput(RobotMap.LOWER_ELEVATOR_SWITCH);
		upperSwitch = new DigitalInput(RobotMap.UPPER_ELEVATOR_SWITCH);
		setAbsoluteTolerance(0.5);
		elevator = new Talon(RobotMap.ELEVATOR_MOTOR);
		disable();
	}

	@Override
	protected double returnPIDInput() {
		if(Robot.elevatorPrint)
			System.out.println("Height: " + getHeight() + getSetpoint());
		return getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		if(output < 0)
			output = 0;
		/*
		 * With a switch wired to normally open, all to switch.get() returns true in a floating circuit 
		 * (if nothing is connected or if switch is open) because it is a pull-up switch. 
		 * Invert the boolean to get expected result
		 */

		/*
		 * for use with the real robot with an aluminum elevator, when gravity isn't enough to pull it down
		 */
		//		if((output < 0 && !lowerSwitch.get()) ||
		//				(output > 0 && !upperSwitch.get()))
		//			output = 0;

		Value setSol = Value.kReverse;

		/*
		 * for use with the prototype, made of wood, where it's heavy enough for gravity to pull it down
		 */

		//cases are split up to make debugging easier
		if(output < 0 && !lowerSwitch.get()) {
			output = 0;
			setSol = Value.kForward;
		} else if(output > 0 && !upperSwitch.get()) {
			output = 0;
			setSol = Value.kForward;
		} else if(onTarget()) {
			output = 0;
			setSol = Value.kForward;
		} else {
			setSol = Value.kReverse;
		}

//		brake.set(setSol);
		elevator.set(output);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void changeSetpoint(double setpoint) {
		setSetpoint(setpoint);
	}

	public void deltaSetpoint(double deltaSetpoint) {
		//	setSetpointRelative(deltaSetpoint);
		double newSetpoint = getSetpoint() + deltaSetpoint;
		setSetpoint(newSetpoint);
	}

	public void manualElevator(OI oi) {
		double speed = -oi.getJoystick().getRawAxis(RobotMap.RIGHT_JOYSTICK_VERTICAL_AXIS);
		System.out.println("Height (manual): " + Robot.ELEVATOR_PID_SUBSYSTEM.getHeight());
		if(Robot.elevatorPrint)
			System.out.println("Height (manual): " + Robot.ELEVATOR_PID_SUBSYSTEM.getHeight());

		/*
		 * for use with the real robot with an aluminum elevator, when gravity isn't enough to pull it down
		 */
		//		if((output < 0 && !lowerSwitch.get()) ||
		//				(output > 0 && !upperSwitch.get()))
		//			output = 0;

		/*
		 * for use with the prototype, made of wood, where it's heavy enough for gravity to pull it down
		 */
		if((speed < 0) ||
				(speed > 0 && !upperSwitch.get()))
			speed = 0;

		lastManualHeight = getHeight();

		elevator.set(speed);
	}

	public double getLastManualHeight() {
		return lastManualHeight;
	}

	/*
	 * The potentiometer output is calculated using the following formula: 
	 * (Analog Input Voltage/Analog Supply Voltage) * FullScale + Offset
	 */

	public double getHeight() {
		return height.get() * TOTAL_HEIGHT * EMPIRICAL_SCALE + OFFSET;
	}

}
