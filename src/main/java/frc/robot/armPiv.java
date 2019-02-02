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
/**
 * Add your docs here.
 */
public class armPiv {
    TalonSRX TalonM;
    VictorSPX VictorM; 
    double speedMotor;
    ControlMode Arm;
    boolean ArmInvert; 
    public armPiv(int rightMotor, int leftMotor, boolean ArmInvert, int Joystick){
        TalonM = new TalonSRX(rightMotor);
        VictorM = new VictorSPX(rightMotor);
        
        speedMotor = 0.1;
        this.ArmInvert = ArmInvert; 
    }
  
    public void ArmPiv(double speedMotor){
        TalonM.set(Arm, speedMotor); 
        VictorM.follow(TalonM); 
        
    

    }

}

