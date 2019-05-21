/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/**
 * Add your docs here.
 */
public class Climber {
    //Variables 
    PWMVictorSPX ClimbMotor; 
    Joystick joystick;
    double MotorSpeed;

    public Climber(int VictorMotorLeft1, Joystick ClimbStick)
    {
        //setups them up to be called by the int
        joystick=ClimbStick;
        ClimbMotor=new PWMVictorSPX(VictorMotorLeft1);
    }
    public void climbCon(){ 
            //Binds the left joystick to moving the climber
            ClimbMotor.setSpeed(joystick.getRawAxis(1));
          
    }

}



