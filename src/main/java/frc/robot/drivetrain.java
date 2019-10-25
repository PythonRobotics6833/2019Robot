/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
/**
 * Add your docs here.
 */
public class drivetrain {
    DifferentialDrive MyDrive;
    //declare all your variables 
    PWMVictorSPX Motor1;
    PWMVictorSPX Motor2;
    PWMVictorSPX Motor3;
    PWMVictorSPX Motor4;
    Joystick joystick;
    private double left_c;
    private double right_c;
    private double v_speedLimiter;
    //3 is the right bumper on the Controller 
    private int speedLimiterAxis=3;
    //Base constructer
   

    
    //FRC 2019 main robot
    //integers in there are used to Determine ports in Robot Class 
    public drivetrain(int VictorMotorLeft1, int VictorMotorLeft2, int VictorMotorRight1, int VictorMotorRight2, Joystick stick)
    {
        //sets up joystick 
        joystick=stick;
        //makes the variable mototrs equal the integers in the constructor 
        Motor1=new PWMVictorSPX(VictorMotorLeft1);
        Motor2=new PWMVictorSPX(VictorMotorLeft2);
        Motor3=new PWMVictorSPX(VictorMotorRight1);
        Motor4=new PWMVictorSPX(VictorMotorRight2);
        //Inverts Motors, they usually go backwards...
        Motor1.setInverted(false);
        Motor2.setInverted(false);
        Motor3.setInverted(false);
        Motor4.setInverted(false);
        //SpeedGroups to make a 4 Motor drive, can be used with 6, does not need it if only using 2 motors 
        SpeedControllerGroup leftSpeedGroup = new SpeedControllerGroup(Motor1, Motor2); 
        SpeedControllerGroup rightSpeedGroup = new SpeedControllerGroup(Motor3,Motor4);
        
        //attatches the Speedgroups to the variable for the DriveTrain
        MyDrive= new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);
    }
    public void tankdrive()
    {   
        //Joystick raw axis is right bumper for Logitech
        v_speedLimiter = joystick.getRawAxis(speedLimiterAxis);
        //1 is the left stick value 
        left_c =  -joystick.getRawAxis(1)/(1.75-v_speedLimiter);
        //5 is the right stick value
        right_c = -joystick.getRawAxis(5)/(1.75-v_speedLimiter);
        //attaches the limiters to the controls 
        MyDrive.tankDrive(left_c, right_c);
        //the speed limiter halfs the speed unless pushed by "right bumper"
    }
    public void tankDrive2()
    {
        
         v_speedLimiter= joystick.getRawAxis(speedLimiterAxis);
         right_c = -joystick.getRawAxis(5)/(2-v_speedLimiter);
         //makes it single stick drive, it still needs the variables 
         MyDrive.tankDrive(right_c, right_c);
    }

    //autonomous code 
    public void Auto() 
    {

        //it just runs the motors at .1 speed until auto is off
      boolean Switch = false;
        if(Switch == true)
        {
        Motor1.set(.1);
        Motor2.set(.1);
        Motor3.set(-.1);
        Motor4.set(-.1);
        }
        else if (Switch == false)
        {
            Motor1.set(0);
            Motor2.set(0);
            Motor3.set(0);
            Motor4.set(0);
            
        }

    }
}   
