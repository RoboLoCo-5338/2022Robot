package frc.robot.commands;
//imports all the necessary classes
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;

public class IntakeCommands {
    public static Command extendIntakeCommand() {//creates the extend intake command
        return new RunCommand(
			() -> RobotContainer.intake.extendIntake(),
			RobotContainer.intake
		);
    }  

    public static Command extendOutakeCommand() {//creates the extend outake command
        return new RunCommand(
			() -> RobotContainer.intake.extendOutake(),
			RobotContainer.intake
		);
    }  

    public static Command retractIntakeCommand() {//creates the retract intake command
        return new RunCommand(
			() -> RobotContainer.intake.extendOutake(),
			RobotContainer.intake
		);
    }  

    public static Command retractOutakeCommand() {//creates the retract outake command
        return new RunCommand(
			() -> RobotContainer.intake.extendOutake(),
			RobotContainer.intake
		);
    }  

}
