//package org.usfirst.frc.team151.robot.commands;
//
//import org.usfirst.frc.team151.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class OpenClawCommand extends Command {
//
//	private boolean isOpen = false;
//	
//    public OpenClawCommand() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.CUBE_CLAW_MOVEMENT_SUBSYSTEM);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	Robot.CUBE_CLAW_MOVEMENT_SUBSYSTEM.openClaw();
//    	isOpen = true;
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return isOpen;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
