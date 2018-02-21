package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddleCrossBaselineCommandGroup extends CommandGroup {

    public AutoMiddleCrossBaselineCommandGroup() {
    	addSequential(new CloseClawCommand());
    	addSequential(new DriveStraightPIDCommand(12, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(84, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new AutoTurnPIDCommand(-90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(84, Robot.kPt, Robot.kIt, Robot.kDt));
    	
      
    }
}
