package frc.robot.subsystems;
//imports all the necessary bits 
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends SubsystemBase{
   private static DoubleSolenoid rightSolenoid;//creates the solenoid objects and the motor object
   private static DoubleSolenoid leftSolenoid;
   // private static WPI_TalonFX intakeMotor;
   private static WPI_TalonFX intakeIndexMotor;

   public Intake(){
   /* pneumatics on the right and left go at the same time
   motor starts when the pneumatics start, might want to turn it off during climb?
   */
      rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,4,5);// right solenoid object, ports are placeholders
      leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,6,7);//left solenoid object, ports are placeholders
      // intakeMotor = new WPI_TalonFX(Constants.INTAKE_MOTOR_ID);
      intakeIndexMotor = new WPI_TalonFX(Constants.INDEX_MOTOR_ID);
   }
   public void indexForward(){//moves the index forward
      intakeIndexMotor.set(ControlMode.PercentOutput, 0.3);
   }
   public void indexReverse(){//moves the index reverse
      intakeIndexMotor.set(ControlMode.PercentOutput, -0.3);
   }
   public void toggleIntakePneumatics(){//toggle pneumatics
      rightSolenoid.toggle();
      leftSolenoid.toggle();
   }
   // public void intake(){//intake motor
   //    intakeMotor.set(ControlMode.PercentOutput, 0.5);//percentOutput is a placeholder
   // }
   // public void outake(){//outake motor
   //    intakeMotor.set(ControlMode.PercentOutput, -0.5);//percentOutput is a placeholder
   // }
   public void stopIndex(){
      intakeIndexMotor.set(ControlMode.PercentOutput, 0);
   }
   // public void stopIntake(){
   //    intakeMotor.set(ControlMode.PercentOutput, 0);//percentOutput is a placeholder
   // }
   
   //combos of the intake motor and the indexing motor

   public void stopIntakeMotors() {
      // stopIntake();
      stopIndex();
   }

   // public void intakeIndexForward(){
   //    intakeMotor.set(ControlMode.PercentOutput, 0.3);//percentOutput is a placeholder, starts the motor
   //    intakeIndexMotor.set(ControlMode.PercentOutput, 0.5);
   // }
   // public void intakeIndexReverse(){
   //    intakeMotor.set(ControlMode.PercentOutput, 0.3);//percentOutput is a placeholder, starts the motor
   //    intakeIndexMotor.set(ControlMode.PercentOutput, -0.5);
   // }
   // public void outakeIndexForward(){
   //    intakeMotor.set(ControlMode.PercentOutput, -0.3);//percentOutput is a placeholder, starts the motor 
   //    intakeIndexMotor.set(ControlMode.PercentOutput, 0.5);  
   // }
   // public void outakeIndexReverse(){
   //    intakeMotor.set(ControlMode.PercentOutput, -0.3);//percentOutput is a placeholder, starts the motor
   //    intakeIndexMotor.set(ControlMode.PercentOutput, -0.5);   
   // }
}
