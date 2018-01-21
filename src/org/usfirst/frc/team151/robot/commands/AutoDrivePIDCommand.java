package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrivePIDCommand extends Command {

	private PIDController drivePid = null;
	boolean finish = false;
	
    public AutoDrivePIDCommand(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.TANK_DRIVE_SUBSYSTEM);
//		drivePid = new PIDController(0.01, 0.0, 0.0, new PIDSource() {
//			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
//			public double pidGet() {
//				return Robot.TANK_DRIVE_SUBSYSTEM.leftEnc.getDistance();
//			}
//
//			@Override
//			public void setPIDSourceType(PIDSourceType pidSource) {
//				m_sourceType = pidSource;
//			}
//
//			@Override
//			public PIDSourceType getPIDSourceType() {
//				return m_sourceType;
//			}
//		}, new PIDOutput() {
//			@Override
//			public void pidWrite(double d) {
//				Robot.TANK_DRIVE_SUBSYSTEM.drive(d, d);
//			}
//		}};
//		drivePid.setAbsoluteTolerance(0.5);
//		drivePid.setSetpoint(distance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
