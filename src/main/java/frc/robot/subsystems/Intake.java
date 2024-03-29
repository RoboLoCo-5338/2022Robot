package frc.robot.subsystems;
//imports all the necessary bits 
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
public class Intake extends SubsystemBase{
   private static DoubleSolenoid intakeSolenoid;
   private static WPI_TalonFX intakeMotor;
   private static WPI_TalonFX indexMotor;

   private double time = 0;

   public Intake(){
   /* pneumatics on the right and left go at the same time
   motor starts when the pneumatics start, might want to turn it off during climb?
   */
      intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,Constants.INTAKE_PISTON_ID[0],Constants.INTAKE_PISTON_ID[1]);//ports are placeholders
      intakeSolenoid.set(Value.kReverse);
      
      intakeMotor = new WPI_TalonFX(Constants.INTAKE_MOTOR_ID);
      indexMotor = new WPI_TalonFX(Constants.INDEX_MOTOR_ID);
      indexMotor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 100);
   }
   public void indexForward(){//moves the index forward
      indexMotor.set(ControlMode.PercentOutput, 0.3);
   }
   public void indexReverse(){//moves the index reverse
      indexMotor.set(ControlMode.PercentOutput, -0.3);
   }
   public void toggleIntakePneumatics(){//toggle pneumatics
      intakeSolenoid.toggle();
   }
   public void intakeDown() {
      intakeSolenoid.set(Value.kForward);
   }
   public void intakeUp() {
      intakeSolenoid.set(Value.kReverse);
   }
   public void intake(){//intake motor
     intakeMotor.set(ControlMode.PercentOutput, 0.5);//percentOutput is a placeholder
   }
   public void outake(){//outake motor
     intakeMotor.set(ControlMode.PercentOutput, -0.5);//percentOutput is a placeholder
   }
   public void stopIndex(){
      indexMotor.set(ControlMode.PercentOutput, 0);
   }
   public void stopIntake(){
     intakeMotor.set(ControlMode.PercentOutput, 0);//percentOutput is a placeholder
   }
   
   //combos of the intake motor and the indexing motor

   public void stopIntakeMotors() {
      stopIntake();
      stopIndex();
   }

   public void startTime() {
      this.time = System.currentTimeMillis();
   }

   public double getTime() {
      return this.time;
   }

   public void intakeIndexForward(){
      intakeMotor.set(ControlMode.PercentOutput, 0.5);//percentOutput is a placeholder, starts the motor
      indexMotor.set(ControlMode.PercentOutput, 0.5);
   }

   public void outakeIndexReverse(){
      intakeMotor.set(ControlMode.PercentOutput, -0.5);//percentOutput is a placeholder, starts the motor
      indexMotor.set(ControlMode.PercentOutput, -0.5);   
   }
}
