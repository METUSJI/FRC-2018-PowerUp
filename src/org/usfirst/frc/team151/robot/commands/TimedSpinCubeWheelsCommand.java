package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedSpinCubeWheelsCommand extends TimedCommand {

    public TimedSpinCubeWheelsCommand(double timeout) {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.CUBE_CLAW_WHEELS_SUBSYSTEM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.CUBE_CLAW_WHEELS_SUBSYSTEM.spinWheelsAuto(0.5); 
    }

    // Called once after timeout
    protected void end() {
    	Robot.CUBE_CLAW_WHEELS_SUBSYSTEM.spinWheelsAuto(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
