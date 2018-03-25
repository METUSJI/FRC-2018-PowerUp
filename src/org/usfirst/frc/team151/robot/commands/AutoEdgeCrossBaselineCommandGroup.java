package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeCrossBaselineCommandGroup extends CommandGroup {

    public AutoEdgeCrossBaselineCommandGroup() {
        addSequential(new CloseClawCommand()); //UNCOMMENT WHEN USING THE ACTUAL ROBOT
        addSequential(new DriveStraightPIDCommand(42, Robot.kPd, Robot.kId, Robot.kDd));
    }
}
