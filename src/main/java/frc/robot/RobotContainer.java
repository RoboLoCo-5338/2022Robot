// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbCommands;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveSystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //public static DriveSystem driveSystem = new DriveSystem();
  public static Climb climb = new Climb();
  public static DriveSystem driveSystem = new DriveSystem();
  private Joystick controller1 = new Joystick(0);
  private Joystick controller2 = new Joystick(1);

  // Initialize the drive command
    public Command defaultDrive = new RunCommand(
      () -> driveSystem.tankDriveVelocity(
        this.controller1.getRawAxis(1),
        this.controller1.getRawAxis(5)
      ),
      driveSystem
    );

    public Command toggleSlow = new RunCommand(
      () -> driveSystem.toggleSlow(),
      driveSystem
    );

    
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // JoystickButton slowButton = new JoystickButton(controller1, Constants.ABUTTON);
    // slowButton.whenPressed(toggleSlow);
    JoystickButton longForward = new JoystickButton(controller2, Constants.XBUTTON);
    longForward.whenReleased(ClimbCommands.longForward);
    JoystickButton longReverse = new JoystickButton(controller2, Constants.YBUTTON);
    longReverse.whenReleased(ClimbCommands.longReverse);

    //change things down here when needed 

  //   JoystickButton shortForward = new JoystickButton(controller2, Constants.BBUTTON);
  //   shortForward.whenPressed(ClimbCommands.shortForward);
  //   JoystickButton shortReverse = new JoystickButton(controller2, Constants.ABUTTON);
  //   shortReverse.whenPressed(ClimbCommands.shortReverse);
  //   JoystickButton climbMotor = new JoystickButton(controller2, Constants.RBBUTTON);
  //   climbMotor.whenPressed(ClimbCommands.climbToPos(1000)); // TODO: 1000 is random value, needs to be tested
  //   JoystickButton climbWinch = new JoystickButton(controller2, Constants.LBBUTTON);
  //   climbWinch.whenPressed(ClimbCommands.winchToPos(1000)); // TODO: 1000 is random value, needs to be tested
  }

  private void configureDefaultCommands() {
    driveSystem.setDefaultCommand(defaultDrive);
    CommandScheduler scheduler = CommandScheduler.getInstance();
    scheduler.setDefaultCommand(driveSystem, defaultDrive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
