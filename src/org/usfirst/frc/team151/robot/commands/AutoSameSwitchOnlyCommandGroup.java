package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSameSwitchOnlyCommandGroup extends CommandGroup {

    public AutoSameSwitchOnlyCommandGroup() {
    	addSequential(new CloseClawCommand());
//    	addParallel(new ChangeElevatorSetpointCommand(16));
//    	addSequential(new DriveStraightPIDCommand(106, Robot.kPd, Robot.kId, Robot.kDd));
//        addSequential (new OpenClawCommand());
    	
//    	addSequential(new ChangeElevatorSetpointCommand(16));
//    	addSequential(new DriveStraightPIDCommand(60, Robot.kPd, Robot.kId, Robot.kDd));
//    	addSequential(new AutoTurnPIDCommand(-90, Robot.kPt, Robot.kIt, Robot.kDt));
//    	addSequential(new DriveStraightPIDCommand(24, Robot.kPd, Robot.kId, Robot.kDd));
//    	addSequential(new AutoTurnPIDCommand(-90, Robot.kPt, Robot.kIt, Robot.kDt));
//    	addSequential(new DriveStraightPIDCommand(36, Robot.kPd, Robot.kId, Robot.kDd));
//    	addParallel(new TimedSpinCubeWheelsCommand(0.5));
//    	addSequential(new OpenClawCommand());
    	
    	addSequential(new DriveStraightPIDCommand(36, Robot.kPd, Robot.kId, Robot.kDd));
//    	addSequential(new DriveEncoderCommand(100));
    	addSequential(new TimedSpinCubeWheelsCommand(2.5));
    	addSequential(new OpenClawCommand());
    	
//    	addSequential(new DriveEncoderCommand(7.5 * Math.PI));
    }
}
