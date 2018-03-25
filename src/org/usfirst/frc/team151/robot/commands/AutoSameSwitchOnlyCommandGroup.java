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
//    	addSequential(new DriveStraightPIDCommand(102, Robot.kPd, Robot.kId, Robot.kDd));
//    	addSequential(new TimedSpinCubeWheelsCommand(2.5));
//        addSequential (new OpenClawCommand());
    	
//    	addSequential(new DriveEncoderCommand(100));
    }
}
