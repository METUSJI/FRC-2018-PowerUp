package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeCrossBaselineCommandGroup extends CommandGroup {

    public AutoEdgeCrossBaselineCommandGroup() {
//        addSequential(new CloseClawCommand());
        addSequential(new DriveStraightPIDCommand(42, Robot.kPd, Robot.kId, Robot.kDd));
        
        //for testing only, erase the below commands for competition
//    	addSequential(new TimedTurnCommand(1.5));
//    	addSequential(new DriveStraightPIDCommand(30, Robot.kPd, Robot.kId, Robot.kDd));
    }
}
