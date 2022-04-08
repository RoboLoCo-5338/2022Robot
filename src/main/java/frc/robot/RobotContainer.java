// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoCommands;
import frc.robot.commands.ClimbCommands;
import frc.robot.subsystems.Climb;
import frc.robot.commands.IntakeCommands;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.ShooterSystem;
import frc.robot.commands.ShooterCommands;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //public static DriveSystem driveSystem = new DriveSystem();
  // public static Climb climb = new Climb();
  public static DriveSystem driveSystem = new DriveSystem();
  public static ShooterSystem shooterSystem = new ShooterSystem();
  public static Climb climb = new Climb();
  public static Intake intake = new Intake();
  
  private static Joystick controller1 = new Joystick(0);
  private static Joystick controller2 = new Joystick(1);
  
  // Initialize the drive command
    public Command defaultDrive = new RunCommand(
      () -> driveSystem.tankDriveVelocity(
        controller1.getRawAxis(1),
        controller1.getRawAxis(5)
      ),
      driveSystem
    );

    public Command toggleSlow = new InstantCommand(
      () -> driveSystem.toggleSlow(),
      driveSystem
    );

    public Command straightTrue = new InstantCommand(
      () -> driveSystem.setStraight(true),
      driveSystem
    );

    public Command straightFalse = new InstantCommand(
      () -> driveSystem.setStraight(false),
      driveSystem
    );

  // climb percent output commands for motors
  // public static Command climbPercentForward() {
  //   return new RunCommand(
  //     () -> RobotContainer.climb.climbPercent(
  //       controller2.getRawAxis(1)
  //     ), 
  //     RobotContainer.climb
  //     );
  // }

  public static Command winchPercent() {
    return new RunCommand(
      () -> RobotContainer.climb.winchPercent(
        controller2.getRawAxis(5)
      ), 
      RobotContainer.climb
      );
  }
    
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
    // drive buttons
    JoystickButton slowButton = new JoystickButton(controller1, Constants.STARTBUTTON);
    slowButton.whenPressed(toggleSlow);

    // JoystickButton straightButton = new JoystickButton(controller1, Constants.XBUTTON);
    // straightButton.whileHeld(straightTrue);
    // straightButton.whenReleased(straightFalse);

    // climb buttons
    JoystickButton longHighPiston = new JoystickButton(controller2, Constants.LBBUTTON);
    longHighPiston.whenPressed(ClimbCommands.longHighToggle);
    
    JoystickButton longLowPiston = new JoystickButton(controller2, Constants.RBBUTTON);
    longLowPiston.whenPressed(ClimbCommands.longLowToggle);

    // shooter buttons
    JoystickButton shooterButton = new JoystickButton(controller1, Constants.BBUTTON);
    shooterButton.whileHeld(ShooterCommands.shootCommand());
    shooterButton.whenReleased(ShooterCommands.stopShootCommand());

    JoystickButton shooterOperatorButton = new JoystickButton(controller2, Constants.BBUTTON);
    shooterOperatorButton.whileHeld(ShooterCommands.shootCommand());
    shooterOperatorButton.whenReleased(ShooterCommands.stopShootCommand());

    // intake + index buttons
    // controller 1
    // JoystickButton intakePneumatics = new JoystickButton(controller1, Constants.YBUTTON); 
    // intakePneumatics.whenPressed(IntakeCommands.toggleIntakePneumatics());

    // controller 2
    JoystickButton intakePneumatics2 = new JoystickButton(controller2, Constants.YBUTTON); 
    intakePneumatics2.whenPressed(IntakeCommands.intakeDown());
    intakePneumatics2.whenReleased(IntakeCommands.intakeUp());
    
    // controller 1
    JoystickButton intakeIndexForward = new JoystickButton(controller1, Constants.RBBUTTON);
    intakeIndexForward.whenPressed(IntakeCommands.intakeIndexForward());
    intakeIndexForward.whenReleased(IntakeCommands.stopIntakeMotors());
    // controller 2
    // JoystickButton intakeIndexForward2 = new JoystickButton(controller2, Constants.LBBUTTON);
    // intakeIndexForward2.whenPressed(IntakeCommands.intakeIndexForward());
    // intakeIndexForward2.whenReleased(IntakeCommands.stopIntakeMotors());

    // controller 1
    JoystickButton outakeIndexReverse = new JoystickButton(controller1, Constants.LBBUTTON);
    outakeIndexReverse.whenPressed(IntakeCommands.outakeIndexReverse());
    outakeIndexReverse.whenReleased(IntakeCommands.stopIntakeMotors());
    // controller 2
    // JoystickButton outakeIndexReverse2 = new JoystickButton(controller2, Constants.RBBUTTON);
    // outakeIndexReverse2.whenPressed(IntakeCommands.outakeIndexReverse());
    // outakeIndexReverse2.whenReleased(IntakeCommands.stopIntakeMotors());
  }

  private void configureDefaultCommands() {
    driveSystem.setDefaultCommand(defaultDrive);
    CommandScheduler scheduler = CommandScheduler.getInstance();
    scheduler.setDefaultCommand(driveSystem, defaultDrive);

    scheduler.setDefaultCommand(RobotContainer.climb, winchPercent());
    // scheduler.addButton(() -> indexUpCommand());
    // scheduler.addButton(() -> indexDownCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return AutoCommands.hangerSideAuto();
  }
}
