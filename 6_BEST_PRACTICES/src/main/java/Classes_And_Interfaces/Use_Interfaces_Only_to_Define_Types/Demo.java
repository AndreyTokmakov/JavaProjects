/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Interfaces best practice example:
*
* @name    : FilesInMemoryStorage.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 19, 2020
****************************************************************************/ 

package Classes_And_Interfaces.Use_Interfaces_Only_to_Define_Types;

// 1. The constant interface pattern is a poor use of interfaces.

final class PhysicalConstants {
	private PhysicalConstants() {
		// Prevents instantiation
	}
	
	// Avogadro's number (1/mol)
	public static final double AVOGADROS_NUMBER = 6.02214199e23;
	
	// Boltzmann constant (J/K)	
	public static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
	
	// Mass of the electron (kg)
	public static final double ELECTRON_MASS = 9.10938188e-31;
}


public class Demo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
