package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeOppositeScaleCommand extends CommandGroup {

    public AutoEdgeOppositeScaleCommand(int left) {
    	addSequential(new CloseClawCommand());
    	addSequential(new DriveStraightPIDCommand(190.5, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * -90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(150, Robot.kPd, Robot.kId, Robot.kDd));
//    	addSequential(new AutoTurnPIDCommand(left * -90, Robot.kPt, Robot.kIt, Robot.kDt));
    }
}
