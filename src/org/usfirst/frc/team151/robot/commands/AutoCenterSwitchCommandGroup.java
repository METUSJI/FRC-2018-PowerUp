package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 *
 */
public class AutoCenterSwitchCommandGroup extends CommandGroup {

    public AutoCenterSwitchCommandGroup(int left) {
    	addSequential(new CloseClawCommand());
    	addSequential(new DriveStraightPIDCommand(30, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * -90, Robot.kPt, Robot.kIt, Robot.kDt));
//    	addParallel(new ChangeElevatorSetpointCommand(16));
    	addSequential(new DriveStraightPIDCommand(70, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * 90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(72, Robot.kPd, Robot.kId, Robot.kDd));
    }
}
