<ins>**Version 1**</ins>\
Date: November 14, 2019

* New Features:
	* Parallel and Series equivalent resistance calculations, supporting up to 9 resistors .
	* Solves all equivalent resistances of the circuit based on how the user wants their resistors connected.
	* Labels all resistor connections in the form of an equation for all the equivalent resistors and equivalent resistance of the entire circuit.
	* Text-input based UI

* Current Issues:
	* Very easily error-ridden if user enters wrong input.
	* Resistor IDs are in "int", and often the combined equivalent resistor ID is too large and will produce a "NumericalFormatException".
	* Remove "combination level"

* For Next Implementation(s):
	* Add try-catch statements for the most obvious user input errors
	* Reduce length of resistor IDs i.e. R123456 can be re-written as R_16
	* Change all resistor IDs to String.
	* Remove "combination level" and its corresponding help text.
	* Move all instructions to either a text file, create a "Instructions" class to handle all of the text and error messages.
	* Move all/most of the algorithms to a new class, ListHandler, or etc...
	* Add the capability for the user to directly specify the resistors in a text file (optional).

<ins>**Version 2**</ins>\
Date: November 16, 2019

* Fixed Issues:
	* Removed "Combination Level".

* For Next Implementation(s):
	* Fix all other issues and use suggestions from Version 1.

<ins>**Version 3**</ins>\
Date: April 28, 2020

* New Features:
	* Major Refactor of project, grouped and divided all existing classes
	* Created two factories : CircuitFactory and LoadFactory; removed the BuildCircuit class
	* Added enum classes : LoadType and CircuitType
	* Calculator supports equivalent value calculations for one load type (resistor, capacitor, inductor)
	* Created three handlers for console input, file input, and properties (FileInputHandler is currently a placeholder)

* Fixed Issues:
	* Added error handling to user input
	* Loads can be directly referred as a string using their toString method
	* Loads can be referred with their custom tags
	* New circuits will be displayed when connecting the circuit
	* New circuits are referred with their own IDs ( P0 = Parallel Circuit, ID = 0, S1 = Series Circuit, ID = 1)
	* Changed project name to "CircuitCalculator"

* For Next Implementation(s):
	* Add a new ErrorHandler class
	* Implement FileInputHandler for text-file input
	* Optional: Implment impedances
