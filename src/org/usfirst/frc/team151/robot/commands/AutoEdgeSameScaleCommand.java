package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeSameScaleCommand extends CommandGroup {

    public AutoEdgeSameScaleCommand(int left) {
    	addSequential(new CloseClawCommand());
    	addParallel(new ChangeElevatorSetpointCommand(65));
    	addSequential(new DriveStraightPIDCommand(286, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * 90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(286, Robot.kPd, Robot.kId, Robot.kDd));
    }
}
