import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Stack;

public class SimpleResistanceCalculator {

	public static void main(String[] args) {

		int totalResistors = 0;
		String response = "";
		String finalConnection="";
		String equivalentID="";
		String currentID="";
		String[] responseList;
		
		Stack<String> resistorIDList=new Stack<String>();
		List<Resistor> resistorList = new ArrayList<Resistor>();
		List<Resistor> connectList = new ArrayList<Resistor>();
		LinkedHashMap<String,String> connectionMap= new LinkedHashMap<>();
		Resistor addResistor = new Resistor();
		BuildCircuit buildCircuit = new BuildCircuit();
		
		Scanner scan = new Scanner(System.in);
		DecimalFormat sigFigs;

		sigFigs = new DecimalFormat("#.####");// 5 sig figs format

		System.out.print("Please enter the total # of resistors in the circuit: ");
		totalResistors = scan.nextInt();

		System.out.print(
				"Please enter your list of resistances for the " + totalResistors + " resistors you requested.\n\n");
		System.out.print(
				"\tFor example, 30 means R1 = 30 ohms. The resistor ID will be given in the order of your input.\n"
				+ "You may add them in succession to quickly add the resistors. \n\n");
		System.out.println("Your Input: ");

		// add new resistor object each time
		// set resistances and IDs of every new resistor
		for (int i = 1; i <= totalResistors; i++) {
			addResistor = new Resistor();
			addResistor.setResistorID(i);
			addResistor.setResistance(scan.nextInt());
			resistorList.add(addResistor);
		}

		System.out.print(
				"\nPlease enter the list of IDs and the connection type in the format (ID1 ID2 IDN, | connection type). \n\n");
		System.out.print("\tFor example, \"1 5 7, Parallel\" means R1//R5//R7, for multilayered circuits,\n"
				+ "\tthis will automatically be referred to \"R157.\" Type 157 as an example,\n"
				+ "\tIDs to add R157 to the connections.\n");

		System.out.print(
				"\nPlease start from the innermost layer and work your way out to the outermost layer. Type \"next\" at\n"
						+ "anytime between your input to indicate that you want to enter the next set of resistors in the next level.\n\n");

		scan.nextLine();

		System.out.println("Your Input: ");
		
			while (!response.equals("next")) {

				responseList = null;
				response = "";
				response = scan.nextLine();
				connectList = new ArrayList<Resistor>();
				buildCircuit = new BuildCircuit();

				if (!response.equals("next")) {
					if (response.length() > 1) {
						responseList = response.split(" ");

						// using user-listed IDs, add them to the connection list
						for (int i = 0; i < (responseList.length - 1); i++) {
							addResistor = null;
							addResistor = new Resistor();

							for (Resistor r : resistorList) {
								if (r.getResistorID() == Integer.parseInt(responseList[i])) {
									addResistor = r;
								}
							}
							connectList.add(addResistor);

						}

						if (responseList[responseList.length - 1].equalsIgnoreCase("Parallel")) {
							response = "";
							addResistor = null;
							addResistor = new Resistor();
							buildCircuit.createParallelCircuit(connectList);
							addResistor.setResistance(buildCircuit.getParallelResistance());
							for (String index : responseList) {
								if (!index.equals("Parallel")) {
									response += index;
								}
							}
							addResistor.setResistorID(Integer.parseInt(response));
							resistorList.add(addResistor);
							finalConnection="R" + addResistor.getResistorID() + " = "+ buildCircuit.getCircuitConnection("Parallel");
							System.out.println(finalConnection);
							connectionMap.put("R" + addResistor.getResistorID(),buildCircuit.getCircuitConnection("Parallel"));
							resistorIDList.push("R" + addResistor.getResistorID());
						} else if (responseList[responseList.length - 1].equalsIgnoreCase("Series")
								|| responseList[responseList.length - 1].equalsIgnoreCase("Resistor")) {
							
							response = "";
							addResistor = null;
							addResistor = new Resistor();
							buildCircuit.createSeriesCircuit(connectList);
							addResistor.setResistance(buildCircuit.getSeriesResistance());
							for (String index : responseList) {
								if (!index.equals("Series") && !index.equals("Resistor")) {
									response += index;
								}
							}
							addResistor.setResistorID(Integer.parseInt(response));
							resistorList.add(addResistor);
							finalConnection="R" + addResistor.getResistorID() + " = " + buildCircuit.getCircuitConnection("Series");
							System.out.println(finalConnection);
							connectionMap.put("R" + addResistor.getResistorID(),buildCircuit.getCircuitConnection("Series"));
							resistorIDList.push("R" + addResistor.getResistorID());
						} else {
							System.err.print(
									"Please choose Series or Parallel. You may also type \"Resistor\" to add an individual resistor.");
						}
					} else {
						System.err.println("Please Try Again.");
					}
				}

		}
		scan.close();

// split into two equations across "="
		for(int i=0;i<finalConnection.length();i++) {
			if (finalConnection.charAt(i)=='=') {
				finalConnection=finalConnection.substring(i,finalConnection.length());
				break;
			}
			else {
				equivalentID+=finalConnection.charAt(i);
			}
		}

		while(!resistorIDList.isEmpty()) {
			currentID=resistorIDList.pop();
			finalConnection=finalConnection.replace(currentID,connectionMap.get(currentID));
		}
		finalConnection= equivalentID + finalConnection;

		
		System.out.println("\n... Setting up connections ...");
		System.out.println("... Calculating resistances ...");
		System.out.println("Done!");
		System.out.println("\n--------------------------------------------");
		System.out.println("Equivalent Resistance: " + resistorList.get(resistorList.size() - 1).getResistance());

		System.out.println("Resistance List: ");
		for (Resistor r : resistorList) {
			System.out.println("\tR" + r.getResistorID() + " = " + sigFigs.format(r.getResistance()) + " ohms");
		}

		System.out.println("\nConnection List: " + finalConnection); 
		for (Map.Entry<String,String> kv:connectionMap.entrySet()) {
			System.out.println("\t" + kv.getKey() + " = " + kv.getValue());
		}

	}

}