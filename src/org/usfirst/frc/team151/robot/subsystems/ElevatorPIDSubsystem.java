package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.MoveElevatorWithJoysticksCommand;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class ElevatorPIDSubsystem extends PIDSubsystem {
	private double TOTAL_HEIGHT = 27.0;
	private SpeedController elevator = null;
	Potentiometer height = new AnalogPotentiometer(RobotMap.ELEVATOR_ANALOG_INPUT, TOTAL_HEIGHT);
	
	private boolean print = false;
	
	public ElevatorPIDSubsystem() {
		super(Robot.kPe, Robot.kIe, Robot.kDe);
		elevator = new VictorSP(RobotMap.ELEVATOR_MOTOR);
		disable();
	}

	private double lastHeight = 0;
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		String time = "";
		double height = getHeight();
		//TODO take out printing stuff
		if((Math.abs(getSetpoint() - lastHeight) < (0.01 * getSetpoint())) 
				&& (Math.abs(getSetpoint() - height) < (0.01 * getSetpoint()))) {
			System.out.println("inside if");
			Robot.endTime = System.nanoTime();
			Robot.elapsedTime = (Robot.endTime - Robot.startTime) / 1000000000;
			time = "Elapsed time: " + Robot.elapsedTime;
		}
		if(print)
			System.out.println("Height: " + getHeight() + getSetpoint());
		lastHeight = height;
		return getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
//		System.out.println("Height in usePIDOutput: " + getHeight() + "\t Current setpoint: " + getSetpoint());
//		System.out.println();
		if(output < 0)
			output = 0;
		elevator.set(output);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void changeSetpoint(double setpoint) {
		setSetpoint(setpoint);
	}
	
	public void deltaSetpoint(double deltaSetpoint) {
		setSetpointRelative(deltaSetpoint);
	}
	
	public void manualElevator(OI oi) {
		double speed = -oi.getJoystick().getRawAxis(RobotMap.RIGHT_JOYSTICK_VERTICAL_AXIS);
		System.out.println("Height (manual): " + Robot.ELEVATOR_PID_SUBSYSTEM.getHeight());
		elevator.set(speed);
	}
	
	public double getHeight() {
		return height.get() * 1.14678899083;
	}
	
}
