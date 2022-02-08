package frc.robot.subsystems;
//imports all the necessary bits 
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends SubsystemBase{
   private static DoubleSolenoid rightSolenoid;//creates the solenoid objects and the motor object
   private static DoubleSolenoid leftSolenoid;
   private static WPI_TalonFX intakeMotor;
   private static WPI_TalonFX intakeIndexMotor;

   public Intake(){
   /* pneumatics on the right and left go at the same time
   motor starts when the pneumatics start, might want to turn it off during climb?
   */
      DoubleSolenoid rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,1,2);// right solenoid object, ports are placeholders
      DoubleSolenoid leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,3,4);//left solenoid object, ports are placeholders
      WPI_TalonFX intakeMotor = new WPI_TalonFX(2);//intake motor
      WPI_TalonFX intakeIndexMotor = new WPI_TalonFX(3);//index motor
   }
   public void indexForward(){//moves the index forward
      intakeIndexMotor.set(ControlMode.PercentOutput, 0.5);
   }
   public void indexReverse(){//moves the index reverse
      intakeIndexMotor.set(ControlMode.PercentOutput, -0.5);
   }
   public void extend(){//extend pneumatics
      rightSolenoid.set(kForward);
      leftSolenoid.set(kForward); 
   }
   public void retract(){//retract pneumatics
      rightSolenoid.toggle();
      leftSolenoid.toggle();
   }
   public void intake(){//intake motor
      intakeMotor.set(ControlMode.PercentOutput, 0.5);//percentOutput is a placeholder
   }
   public void outake(){//outake motor
      intakeMotor.set(ControlMode.PercentOutput, -0.5);//percentOutput is a placeholder
   }
   //combos of the intake motor and the indexing motor

   public void intakeIndexForward(){
      intakeMotor.set(ControlMode.PercentOutput, 0.3);//percentOutput is a placeholder, starts the motor
      intakeIndexMotor.set(ControlMode.PercentOutput, 0.5);
   }
   public void intakeIndexReverse(){
      intakeMotor.set(ControlMode.PercentOutput, 0.3);//percentOutput is a placeholder, starts the motor
      intakeIndexMotor.set(ControlMode.PercentOutput, -0.5);
   }
   public void outakeIndexForward(){
      intakeMotor.set(ControlMode.PercentOutput, -0.3);//percentOutput is a placeholder, starts the motor 
      intakeIndexMotor.set(ControlMode.PercentOutput, 0.5);  
   }
   public void outakeIndexReverse(){
      intakeMotor.set(ControlMode.PercentOutput, -0.3);//percentOutput is a placeholder, starts the motor
      intakeIndexMotor.set(ControlMode.PercentOutput, -0.5);   
   }
}
