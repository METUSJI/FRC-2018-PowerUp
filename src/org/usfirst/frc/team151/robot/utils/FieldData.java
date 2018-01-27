package org.usfirst.frc.team151.robot.utils;

public class FieldData {
	String gameData;
	
	private boolean isDataValid() {
		if (gameData.equals("")) {
			
		}
		return true; // stub
	}
	
	public boolean SwitchIsOnTheRight() {
		isDataValid();
		return (gameData.charAt(0) == 'R');
	}
	
	public boolean SwitchIsOnTheLeft() {
		isDataValid();
		return (gameData.charAt(0) == 'L');
	}
	
	public boolean ScaleIsOnTheLeft() {
		isDataValid();
		return (gameData.charAt(1) == 'L');
	}
	
	public boolean ScaleIsOnTheRight() {
		isDataValid();
		return (gameData.charAt(1) == 'R');
	}

}
