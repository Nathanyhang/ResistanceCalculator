# Parallel and Series Equivalent Resistance Calculator

Author: Nathan Ng\
Date Created: November 17, 2019\
Current Version: V1

*Note 1: This is a WIP repository.*\
*Note 2: This repository is compiled in Eclipse IDE.*

## Features

* Parallel and Series equivalent resistance calculations, supporting up to 9 resistors. 
* Solves all equivalent resistances of the circuit based on how the user wants their resistors connected.
* Labels all resistor connections in the form of an equation for all the equivalent resistors and equivalent resistance of the entire circuit.
* Text-input based UI


## Instructions

*TODO: Insert sample circuit here*


** The main program of this project is labelled as SimpleResistanceCalculator.java, located in src. **
1. Enter your list of resistances **in order** as the resistance IDs are automatically labelled with respect to the order of your input.
2. Enter your resistor connections in the following format:

		ID ID ID ConnectionType

  *Where ID is your resistor ID and ConnectionType is either denoting a Parallel or Series circuit. A single resistor can be added as a single-resistor series Circuit.*

3. Type "next" when you have completed all connections.
4. The following will be outputted to the screen:
	* Equivalent resistance in ohms.
	* All resistor IDs and their respective resistances
	* Final equivalent resistance equations with "//" denoting parallel and "+" denoting Series
	* All generated resistance equations while the user were entering their connections.