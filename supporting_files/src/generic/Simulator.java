package generic;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import processor.Clock;
import processor.Processor;

public class Simulator {

	static Processor processor;
	static boolean simulationComplete;

	public static void setupSimulation(String assemblyProgramFile, Processor p) {
		Simulator.processor = p;
		loadProgram(assemblyProgramFile);

		simulationComplete = false;
	}

	static void loadProgram(String assemblyProgramFile) {
		/*
		 * TODO
		 * 1. load the program into memory according to the program layout described
		 * in the ISA specification
		 * 2. set PC to the address of the first instruction in the main
		 */

		try (DataInputStream hex = new DataInputStream(new FileInputStream(assemblyProgramFile))) {
			int byteRead;
			byteRead=hex.readInt();
			int NumberOfLocalVariables = byteRead;

			int address = 0;
			while(hex.available()>0){
				byteRead=hex.readInt();
				processor.getMainMemory().setWord(address, byteRead);

				if(address==NumberOfLocalVariables){
					processor.getRegisterFile().setProgramCounter(address);
				}
				address++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * 3. set the following registers:
		 * x0 = 0
		 * x1 = 65535
		 * x2 = 65535
		 */
		processor.getRegisterFile().setValue(0, 0);
		processor.getRegisterFile().setValue(1, 65535);
		processor.getRegisterFile().setValue(2, 65535);
		// processor.getRegisterFile().setValue(3, 3);
		// processor.getRegisterFile().setValue(4, 4);
	}

	public static void simulate() {
		while (simulationComplete == false) {
			processor.getIFUnit().performIF();
			processor.getOFUnit().performOF();
			processor.getEXUnit().performEX();
			processor.getMAUnit().performMA();
			processor.getRWUnit().performRW();
			Clock.incrementClock();
		}

		// TODO
		// set statistics

		Statistics stats = new Statistics();
		stats.setNumberOfCycles(10);
		stats.setNumberOfInstructions(10);
	}

	public static void setSimulationComplete(boolean value) {
		simulationComplete = value;
	}
}
