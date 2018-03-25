package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddleCrossBaselineCommandGroup extends CommandGroup {

    public AutoMiddleCrossBaselineCommandGroup() {
    	addSequential(new DriveStraightPIDCommand(82, Robot.kPt, Robot.kIt, Robot.kDt));
    }
}
