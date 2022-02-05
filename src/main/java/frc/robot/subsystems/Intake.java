package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends SubsystemBase{
   private static DoubleSolenoid rightSolenoid;
   private static DoubleSolenoid leftSolenoid;
   private static WPI_TalonFX intakeMotor;

   public Intake(){
   //pneumatics on the right and left
   //both need to go at the same time
   //motor, starts when the pneumatics start
      DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);// right solenoid object, ports are placeholders
      DoubleSolenoid leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,3,4);//left solenoid object, ports are placeholders
      WPI_TalonFX intakeMotor = new WPI_TalonFX(2);
   }
    
   public void extendIntake(){
      //the controller name and button are placeholders
         rightSolenoid.set(kForward);
         leftSolenoid.set(kForward); 
         intakeMotor.set(ControlMode.PercentOutput, 0.5);//percentOutput is a placeHolder    
   }

   public void extendOutake(){
      rightSolenoid.set(kForward);
      leftSolenoid.set(kForward); 
      intakeMotor.set(ControlMode.PercentOutput, -0.5);//percentOutput is a placeHolder
   }

   public void retractIntake(){
         rightSolenoid.toggle();
         leftSolenoid.toggle();
         intakeMotor.set(ControlMode.PercentOutput, 0.3);//percentOutput is a placeholder, starts the motor
   }
   public void retractOutake(){
      rightSolenoid.toggle();
      leftSolenoid.toggle();
      intakeMotor.set(ControlMode.PercentOutput, -0.3);//percentOutput is a placeholder, starts the motor   
   }
}
