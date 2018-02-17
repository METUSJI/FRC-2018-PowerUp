package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 *
 */
public class AutoCenterSwitchCommand extends CommandGroup {

    public AutoCenterSwitchCommand(int left) {
    	addSequential(new CloseClawCommand());
    	addSequential(new DriveStraightPIDCommand(35, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * -90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addParallel(new ChangeElevatorSetpointCommand(16));
    	addSequential(new DriveStraightPIDCommand(70, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * 90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(40, Robot.kPd, Robot.kId, Robot.kDd));
    	
    	//addSequential(new )~~~~ Which command is needed to release the cube? Is it the OpenClawCommand or is it the ReleaseCubeCommand??
    }
}
