/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot{
  /*
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  */

  //makes the variables 
    Joystick stick; 
    Joystick armController; 
    drivetrain DriveTrain;
    armTake arm; 
   
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    //m_chooser.addOption("My Auto", kCustomAuto);
    //SmartDashboard.putData("Auto choices", m_chooser);


    stick = new Joystick(0); 
    armController= new Joystick(1);
    //DriveTrain sets up your ports 
    DriveTrain= new drivetrain(0,4,1,3,stick);
    //arm sets the input of the motor and start for the boolean
    arm = new armTake(9, false );

    
 //   CameraServer.getInstance().startAutomaticCapture();
   // CameraServer.getInstance().startAutomaticCapture(1);
   
    new Thread(() -> {
      boolean allowCam1=false;
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("Front",0);
        camera.setResolution(640, 480);
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("back",1);
        camera1.setResolution(640, 480);

        CvSink cvSink1 = CameraServer.getInstance().getVideo(camera);
        CvSink cvSink2= CameraServer.getInstance().getVideo(camera1);
        CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 160, 120);
        
        Mat image = new Mat();
        
        while(!Thread.interrupted()) {
          if(stick.getRawButton(3)) {
            allowCam1 = !allowCam1;
          }
          
            if(allowCam1){
              cvSink2.setEnabled(false);
              cvSink1.setEnabled(true);
              cvSink1.grabFrame(image,2);
            } else{
              cvSink1.setEnabled(false);
              cvSink2.setEnabled(true);
              cvSink2.grabFrame(image, 2);     
            }
            outputStream.putFrame(image);
        }
    }).start();
   

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    /* 
     m_autoSelected = m_chooser.getSelected();
     m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
     System.out.println("Auto selected: " + m_autoSelected);
    */
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    /*switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
    */
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    //calls the drive so it will actually function
    
    boolean Break = stick.getRawButton(8);
    boolean hold= armController.getRawButton(3);
    if (!Break){

      DriveTrain.tankdrive();

      double armInput= armController.getRawAxis(3) -armController.getRawAxis(2);
      arm.speed(armInput);
      if (hold)
      {
        arm.holdPossition(true); 
      }
      else 
      {
				arm.Controlling(armController);
      }
                 } 
		else
		{
            DriveTrain.tankdrive();
    }
  

    

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
