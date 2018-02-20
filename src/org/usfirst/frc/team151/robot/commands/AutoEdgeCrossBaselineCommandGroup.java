package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeCrossBaselineCommandGroup extends CommandGroup {

    public AutoEdgeCrossBaselineCommandGroup() {
        addSequential(new CloseClawCommand());
//    	addSequential(new DriveStraightPIDCommand(90, Robot.kPd, Robot.kId, Robot.kDd));
        addSequential(new DriveStraightPIDCommand(42, Robot.kPd, Robot.kId, Robot.kDd));
    	
    }
}
