// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Subsystems.Elevator.Elevator;



public class RobotContainer {

  private final Elevator elevator = new Elevator();

  private final CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    controller.povUp().onTrue(new RunCommand(() -> elevator.setPosition(0.5), elevator));
    controller.povDown().onTrue((new RunCommand(() -> elevator.setPosition(1.1), elevator)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("Oh No, we have no auto");
  }
}
