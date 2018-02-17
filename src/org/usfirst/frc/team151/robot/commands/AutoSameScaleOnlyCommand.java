package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSameScaleOnlyCommand extends CommandGroup {

    public AutoSameScaleOnlyCommand() {
    	addSequential(new CloseClawCommand());
    	addParallel(new ChangeElevatorSetpointCommand(16));
    	addSequential(new DriveStraightPIDCommand(106, Robot.kPd, Robot.kId, Robot.kDd));
        addSequential (new OpenClawCommand());
    }
}
