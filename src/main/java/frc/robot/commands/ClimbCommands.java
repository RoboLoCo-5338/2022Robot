// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.RobotContainer;

/** Add your docs here. */
public class ClimbCommands {
    public static Command longForward = new InstantCommand(
      () -> RobotContainer.climb.longForward(),
      RobotContainer.climb
    );

    public static Command longReverse = new InstantCommand(
      () -> RobotContainer.climb.longReverse(),
      RobotContainer.climb
    );

    public static Command shortForward = new InstantCommand(
      () -> RobotContainer.climb.shortForward(),
      RobotContainer.climb
    );

    public static Command shortReverse = new InstantCommand(
      () -> RobotContainer.climb.shortReverse(),
      RobotContainer.climb
    );


    public static Command climbToPos(double position) {
      return new FunctionalCommand(
        () -> RobotContainer.climb.keepEncoderValues(), 
        () -> RobotContainer.climb.climbToPos(position), 
        (interrupt) -> RobotContainer.climb.climbPercent(0),
        () -> RobotContainer.climb.getMotorPosition() >= position, 
        RobotContainer.climb
        );
    }

    public static Command climbPercent(double speed) {
      return new RunCommand(
        () -> RobotContainer.climb.climbPercent(speed), 
        RobotContainer.climb
        );
    }

    public static Command winchToPos(double position) {
      return new FunctionalCommand(
        () -> RobotContainer.climb.keepEncoderValues(), 
        () -> RobotContainer.climb.winchToPos(position), 
        (interrupt) -> RobotContainer.climb.winchPercent(0),
        () -> RobotContainer.climb.getWinchPosition() >= position, 
        RobotContainer.climb
        );
    }

    public static Command winchPercent(double speed) {
      return new RunCommand(
        () -> RobotContainer.climb.winchPercent(speed), 
        RobotContainer.climb
        );
    }
}
