/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Joystick;
/**
 * Add your docs here.
 */
public class Arm {
    TalonSRX TalonM;
    VictorSPX VictorM; 
    Joystick P2; 
    double speedMotor;
    double Window;
    PWMVictorSPX Enclose;
    ControlMode Arm;
    public Arm(int rightMotor, int leftMotor, int Close, int Joystick){
        TalonM = new TalonSRX(rightMotor);
        VictorM = new VictorSPX(rightMotor);
        Enclose = new PWMVictorSPX(Close);
        
        speedMotor = 0.1;
        Window = 0.1;       
    }
    public void speed(double speedMotor){
        TalonM.set(Arm, speedMotor); 
        VictorM.follow(TalonM); 
        Enclose.set(Window);
    
    }

}

