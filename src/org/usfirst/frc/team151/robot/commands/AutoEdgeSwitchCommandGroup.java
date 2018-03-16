package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEdgeSwitchCommandGroup extends CommandGroup {

    public AutoEdgeSwitchCommandGroup(int left) {
    	addSequential(new CloseClawCommand());
//    	addSequential(new ChangeElevatorSetpointCommand(16));
    	addSequential(new DriveStraightPIDCommand(130, Robot.kPd, Robot.kId, Robot.kDd));
    	addSequential(new AutoTurnPIDCommand(left * 90, Robot.kPt, Robot.kIt, Robot.kDt));
    	addSequential(new DriveStraightPIDCommand(12, Robot.kPd, Robot.kId, Robot.kDd));
    	addParallel(new TimedSpinCubeWheelsCommand(3));
    	addSequential(new OpenClawCommand());
    }
}
