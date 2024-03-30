package generic;

import java.io.PrintWriter;

public class Statistics {
	
	// TODO add your statistics here
	public static int numberOfInstructions;
	public static int numberOfCycles;
	public static float CPI;
	public static int BranchTaken;
	public static int WrongBranchTaken;
	public static int Stalls;

	public static void printStatistics(String statFile)
	{
		try
		{
			PrintWriter writer = new PrintWriter(statFile);
			
			writer.println("Number of instructions executed = " + numberOfInstructions);
			writer.println("Number of cycles taken = " + numberOfCycles);
			writer.println("CPI = " + CPI);
			writer.println("Number of branch taken = " + BranchTaken);
			writer.println("Number of wrong instruction taken = " + WrongBranchTaken);
			writer.println("Number of stalls = " + Stalls);
			
			// TODO add code here to print statistics in the output file
			
			writer.close();
		}
		catch(Exception e)
		{
			Misc.printErrorAndExit(e.getMessage());
		}
	}
	
	// TODO write functions to update statistics
	public void setNumberOfInstructions(int numberOfInstructions) {
		Statistics.numberOfInstructions = numberOfInstructions;
	}

	public void setNumberOfCycles(int numberOfCycles) {
		Statistics.numberOfCycles = numberOfCycles;
	}

	public void setCPI(float cpi) {
		Statistics.CPI = cpi;
	}

	public void setBranchTaken(int branchTaken) {
		Statistics.BranchTaken = branchTaken;
	}

	public void setWrongBranchTaken(int wrongBranchTaken) {
		Statistics.WrongBranchTaken = wrongBranchTaken;
	}

	public void setStalls(int stalls) {
		Statistics.Stalls = stalls;
	}
}
