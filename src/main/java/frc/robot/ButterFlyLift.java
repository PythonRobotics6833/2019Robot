/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import edu.wpi.first.wpilibj.Joystick;

/**
 * Add your docs here.
 */
public class ButterFlyLift
 {
     TalonSRX Bottom;  
     TalonSRX Middle;
     double speedBot;
     double speedMid;
     ControlMode Up;
     Joystick Player2;  
    public ButterFlyLift(int Talon1, int Talon2){
        Bottom = new TalonSRX(Talon1);
        Middle = new TalonSRX(Talon2);
        
        speedBot = 0.1;
        speedMid = 0.1; 
                                                }

    public void speed (double speedBot, double speedMid){
        Bottom.set(Up, speedBot);
        Middle.set(Up, speedMid);

                                                        }
    }
