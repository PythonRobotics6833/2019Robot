/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.*;

/**
 * Add your docs here.
 */
public class Tilt {
//joystick for manipulator
Joystick rightStick;
PWMVictorSPX victor1;
double speed;
double speedLimiter = 2.3;


public Tilt(int victor2, Joystick stick)
{
    victor1 = new PWMVictorSPX(victor2);
    this.rightStick = stick;
    speed = 1;
}
public void stickMove()
{
    boolean hold = rightStick.getRawButton(6);
    if(hold == true)
    {
          victor1.setSpeed(-.05); 
          System.out.println("Hold");
    }
    else
    {
victor1.setSpeed(rightStick.getRawAxis(5)/speedLimiter);
    }
}

}
 

