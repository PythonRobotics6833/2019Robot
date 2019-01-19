/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
/**
 * Add your docs here.
 */
public class DriveTrain {
    DifferentialDrive MyDrive;
    
    PWMVictorSPX Motor1;
    PWMVictorSPX Motor2;
    PWMVictorSPX Motor3;
    PWMVictorSPX Motor4;
    //Base constructer
    public void DriveTrain()
    {
        return;
    }
    //FRC 2019 main robot
    public void DriveTrain(int VictorMotorLeft1, int VictorMotorLeft2, int VictorMotorRight1, int VictorMotorRight2, Joystick stick)
    {
        Motor1=new PWMVictorSPX(VictorMotorLeft1);
        Motor2=new PWMVictorSPX(VictorMotorLeft2);
        Motor3=new PWMVictorSPX(VictorMotorRight1);
        Motor4=new PWMVictorSPX(VictorMotorRight2);
        Motor1.setInverted(true);
        Motor2.setInverted(false);
        Motor3.setInverted(true);
        Motor4.setInverted(false);
    
        SpeedControllerGroup rightSpeedGroup = new SpeedControllerGroup(Motor3,Motor4);
        SpeedControllerGroup leftSpeedGroup = new SpeedControllerGroup(Motor1, Motor2); 

        MyDrive= new DifferentialDrive(leftSpeedGroup, rightSpeedGroup);
    }

}
