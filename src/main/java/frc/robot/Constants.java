// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //these are placeholders
    public static final double CLIMB_LONG_IN = 0.0;
    public static final double CLIMB_LONG_OUT = 0.0;

    //CAN IDs
    public static final int RIGHT_FRONT_DRIVE = 2;
    public static final int RIGHT_REAR_DRIVE = 3;
    public static final int LEFT_FRONT_DRIVE = 0;
    public static final int LEFT_REAR_DRIVE = 1;

    public static final int INTAKE_MOTOR_ID = 4;
    public static final int INDEX_MOTOR_ID = 5;
    public static final int SHOOT_MOTOR_ID = 6; 

    // public static final int CLIMB_MOTOR_ID = 7;
    public static final int WINCH_1_ID = 7;

   public static final int[] SHORT_ARM_ID = {2, 5};
    public static final int[] LONG_ARM_HIGH_ID = {6, 1};
    public static final int[] LONG_ARM_LOW_ID = {3, 4};
    public static final int[] INTAKE_PISTON_ID = {0, 7};
    
    // Joystick IDs
    public static final int ABUTTON = 1; 
    public static final int BBUTTON = 2; 
    public static final int XBUTTON = 3; 
    public static final int YBUTTON = 4; 
    public static final int LBBUTTON = 5; 
    public static final int RBBUTTON = 6;
    public static final int BACKBUTTON = 7; 
    public static final int STARTBUTTON = 8; 
    public static final int LEFTSTICKBUTTON = 9; 
    public static final int RIGHTSTICKBUTTON = 10;
}
