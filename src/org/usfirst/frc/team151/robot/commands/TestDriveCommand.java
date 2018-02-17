package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestDriveCommand extends CommandGroup {

    public TestDriveCommand() {
    	addSequential(new AutoTurnPIDCommand(90, 0.035, 0, 0.0));
//    	addSequential(new DriveStraightPIDCommand(96, 0.02, 0, 0.0));
    //	addSequential(new FinishAutoCommand());
    }
}
