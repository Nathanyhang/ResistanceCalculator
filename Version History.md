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
	* Obselete and redundant "combination level" can be entirely deleted; it wastes too much of the user's time to input the resistance calculations.

* For next implementation(s):
	* Add try-catch statements for the most obvious user input errors
	* Reduce length of resistor IDs i.e. R123456 can be re-written as R_16, with the "_" to represent a representation of an equivalent resistor object.
	* Change all resistor IDs to String.
	* Remove "combination level" and its corresponding help text.
	* Move all instructions to either a text file, create a "Instructions" class to handle all of the text and error messages.
	* Move all/most of the algorithms to a new class, ListHandler, or etc...
	* Add the capability for the user to directly specify the resistors in a text file (optional).

## 
<ins>**Version 2**</ins>\
Date: November 16, 2019

* Fixed Issues:
	* Removed "Combination Level".

* For next implementation(s):
	* Fix all other issues and use suggestions from Version 1.