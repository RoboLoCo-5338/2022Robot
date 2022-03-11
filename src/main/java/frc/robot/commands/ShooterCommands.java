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
public class ShooterCommands {
    public static Command shootCommand() {
        return new InstantCommand(
            () -> RobotContainer.shooterSystem.shoot(), 
            RobotContainer.shooterSystem
        );
    }

    public static Command stopShootCommand() {
        return new InstantCommand(
            () -> RobotContainer.shooterSystem.stopShoot(),
            RobotContainer.shooterSystem
        );
    }

    public static Command shootTimeCommand() {
        return new FunctionalCommand(
            () -> RobotContainer.shooterSystem.startTime(), 
            () -> RobotContainer.shooterSystem.shoot(), 
            (interupt) -> RobotContainer.shooterSystem.stopShoot(), 
            () -> RobotContainer.shooterSystem.getTime() > 5000, 
            RobotContainer.shooterSystem
        );
    }
}
