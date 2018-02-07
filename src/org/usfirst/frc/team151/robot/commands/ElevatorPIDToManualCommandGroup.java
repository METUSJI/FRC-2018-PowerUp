package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorPIDToManualCommandGroup extends CommandGroup {

    public ElevatorPIDToManualCommandGroup() {
    	addSequential(new DisableElevatorPIDCommand());
    	addSequential(new MoveElevatorWithJoysticksCommand());
    }
}
