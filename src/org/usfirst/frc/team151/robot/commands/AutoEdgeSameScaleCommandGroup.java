package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeSameScaleCommandGroup extends CommandGroup {

    public AutoEdgeSameScaleCommandGroup(int left) {
    	addSequential(new CloseClawCommand());
    	addSequential(new ChangeElevatorSetpointCommand(65));
//    	addSequential(new DriveStraightPIDCommand(286, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new DriveStraightPIDCommand(36, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * 90, Robot.kPt, Robot.kIt, Robot.kDt));
//    	addSequential(new DriveStraightPIDCommand(286, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new DriveStraightPIDCommand(36, Robot.kPd, Robot.kId, Robot.kDd));
    }
}
