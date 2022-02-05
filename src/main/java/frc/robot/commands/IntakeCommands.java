package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;

public class IntakeCommands {

    public static Command extendIntakeCommand() {
        return new RunCommand(
			() -> RobotContainer.intake.extendIntake(),
			RobotContainer.intake
		);
    }  

    public static Command extendOutakeCommand() {
        return new RunCommand(
			() -> RobotContainer.intake.extendOutake(),
			RobotContainer.intake
		);
    }  

    public static Command retractIntakeCommand() {
        return new RunCommand(
			() -> RobotContainer.intake.extendOutake(),
			RobotContainer.intake
		);
    }  

    public static Command retractOutakeCommand() {
        return new RunCommand(
			() -> RobotContainer.intake.extendOutake(),
			RobotContainer.intake
		);
    }  

}
