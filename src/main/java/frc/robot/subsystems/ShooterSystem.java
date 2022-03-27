// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;

public class ShooterSystem extends SubsystemBase{
 
  private WPI_TalonFX shooterMotor;
  private double time = 0;
  private final double MAX_OUTPUT = 0.6;
 
  public ShooterSystem() {
    shooterMotor = new WPI_TalonFX(Constants.SHOOT_MOTOR_ID);
    this.shooterMotor.setNeutralMode(NeutralMode.Brake);    
    this.shooterMotor.setInverted(true);   
  }

  public void shoot(){
    this.shooterMotor.set(ControlMode.PercentOutput, MAX_OUTPUT);    
  }

  public void shootReverse(){
    this.shooterMotor.set(ControlMode.PercentOutput, -MAX_OUTPUT);    
  }

  public void stopShoot(){
    this.shooterMotor.set(ControlMode.PercentOutput, 0);    
  }

  public void startTime() {
    this.time = System.currentTimeMillis();
  }

  public double getTime() {
    return this.time;
  }
}
