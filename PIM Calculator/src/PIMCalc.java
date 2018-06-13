/*
 * 
 * Intermod calculator algorithm which includes calculations for 5G frequencies
 * Using Canadian frequencies rather than US frequencies
 * 
 * This will only calculate the number of hits and output files with the PIM calculations
 * as well as files with where the PIM intersects with the Receiver frequencies
 * 
 * Alter Receiver and Transmission text files to alter calculations
 * 
 * Created by: Gary Jiang
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PIMCalc {
	public static void calculateHits() {
		
		/* 
		 * Variables for Calculations with minimum one 5G frequency in the 3rd order Intermod Calculations
		 * Checks against all frequency ranges
		 */
		
		//Array to hold the frequency band
		double[] gen5_1 = new double[2];
		double[] gen5_2 = new double[2];
		double[] gen5_3 = new double[2];
		double[] gen5_4 = new double[2];
		
		//Temp calculation holders
		double tempPimLow1 = 0;
		double tempPimHigh1 = 0;
		double tempPimLow2 = 0;
		double tempPimHigh2 = 0;
		double tempPimLow3 = 0;
		double tempPimHigh3 = 0;
		double tempPimLow4 = 0;
		double tempPimHigh4 = 0;
		
		//5G Transmission Frequencies
		gen5_1[0] = 3575;
		gen5_1[1] = 3600;
		gen5_2[0] = 3600;
		gen5_2[1] = 3625;
		gen5_3[0] = 3525;
		gen5_3[1] = 3550;
		gen5_4[0] = 3550;
		gen5_4[1] = 3575;
		
		//Set of array list that will capture all the PIM calculations
		//5Gen 1 PIM Variables
		ArrayList<Double> PIM1 = new ArrayList<Double>(); 
		ArrayList<Double> PIM2 = new ArrayList<Double>();
		ArrayList<Double> PIM3 = new ArrayList<Double>();
		ArrayList<Double> PIM4 = new ArrayList<Double>();
		ArrayList<Double> PIM5 = new ArrayList<Double>();
		ArrayList<Double> PIM6 = new ArrayList<Double>();
		
		//5Gen 2 PIM Variables
		ArrayList<Double> PIM7 = new ArrayList<Double>();
		ArrayList<Double> PIM8 = new ArrayList<Double>();
		ArrayList<Double> PIM9 = new ArrayList<Double>();
		ArrayList<Double> PIM10 = new ArrayList<Double>();
		ArrayList<Double> PIM11 = new ArrayList<Double>();
		ArrayList<Double> PIM12 = new ArrayList<Double>();
		
		//5Gen 3 PIM Variables
		ArrayList<Double> PIM13 = new ArrayList<Double>();
		ArrayList<Double> PIM14 = new ArrayList<Double>();
		ArrayList<Double> PIM15 = new ArrayList<Double>();
		ArrayList<Double> PIM16 = new ArrayList<Double>();
		ArrayList<Double> PIM17 = new ArrayList<Double>();
		ArrayList<Double> PIM18 = new ArrayList<Double>();
		
		//5Gen 4 PIM Variables
		ArrayList<Double> PIM19 = new ArrayList<Double>();
		ArrayList<Double> PIM20 = new ArrayList<Double>();
		ArrayList<Double> PIM21 = new ArrayList<Double>();
		ArrayList<Double> PIM22 = new ArrayList<Double>();
		ArrayList<Double> PIM23 = new ArrayList<Double>();
		ArrayList<Double> PIM24 = new ArrayList<Double>();
		
		//Output for hit locations
		List<String> whereTrans = new ArrayList<String>();
		
		/*---------End of the Variables -------------*/
		
		/*
		 * Variables for PIM calculation with all transmission frequency and can be checked
		 * against only 5G receiver frequencies or all receiver frequency bands
		 */
		
		//Temporary calculation holders
		double tempPimLow5 = 0;
		double tempPimHigh5 = 0;
		
		//Array to store the PIM Calculations
		ArrayList<Double> pimAll1 = new ArrayList<Double>(); 
		ArrayList<Double> pimAll2 = new ArrayList<Double>();
		ArrayList<Double> pimAll3 = new ArrayList<Double>();
		ArrayList<Double> pimAll4 = new ArrayList<Double>();
		ArrayList<Double> pimAll5 = new ArrayList<Double>();
		ArrayList<Double> pimAll6 = new ArrayList<Double>();
		
		//Transmission and Receiver frequency files
		List<Double> trans = new ArrayList<Double>();
		List<Double> rcv = new ArrayList<Double>();
		
		//Frequency file for only 5G frequencies
		List<Double> rcv_5G = new ArrayList<Double>();
		
		//Output for hit locations
		List<String> whereTrans1 = new ArrayList<String>();
		
		//Array list to organize the string that will be outputted
		//5Gen 1
		ArrayList<String> pimCalc1 = new ArrayList<String>();
		ArrayList<String> pimCalc2 = new ArrayList<String>();
		ArrayList<String> pimCalc3 = new ArrayList<String>();
		ArrayList<String> pimCalc4 = new ArrayList<String>();
		ArrayList<String> pimCalc5 = new ArrayList<String>();
		ArrayList<String> pimCalc6 = new ArrayList<String>();
		//5Gen 2
		ArrayList<String> pimCalc7 = new ArrayList<String>();
		ArrayList<String> pimCalc8 = new ArrayList<String>();
		ArrayList<String> pimCalc9 = new ArrayList<String>();
		ArrayList<String> pimCalc10 = new ArrayList<String>();
		ArrayList<String> pimCalc11 = new ArrayList<String>();
		ArrayList<String> pimCalc12 = new ArrayList<String>();
		//5Gen 3
		ArrayList<String> pimCalc13 = new ArrayList<String>();
		ArrayList<String> pimCalc14 = new ArrayList<String>();
		ArrayList<String> pimCalc15 = new ArrayList<String>();
		ArrayList<String> pimCalc16 = new ArrayList<String>();
		ArrayList<String> pimCalc17 = new ArrayList<String>();
		ArrayList<String> pimCalc18 = new ArrayList<String>();
		//5Gen 4
		ArrayList<String> pimCalc19 = new ArrayList<String>();
		ArrayList<String> pimCalc20 = new ArrayList<String>();
		ArrayList<String> pimCalc21 = new ArrayList<String>();
		ArrayList<String> pimCalc22 = new ArrayList<String>();
		ArrayList<String> pimCalc23 = new ArrayList<String>();
		ArrayList<String> pimCalc24 = new ArrayList<String>();
		
		//Array list to organize output string 
		ArrayList<String> pimAllCalc1 = new ArrayList<String>();
		ArrayList<String> pimAllCalc2 = new ArrayList<String>();
		ArrayList<String> pimAllCalc3 = new ArrayList<String>();
		ArrayList<String> pimAllCalc4 = new ArrayList<String>();
		ArrayList<String> pimAllCalc5 = new ArrayList<String>();
		ArrayList<String> pimAllCalc6 = new ArrayList<String>();
		
		/*-------------End of Variables----------------*/
		
		//Grabs the appropriate text files
		File transmission = new File("Transmission.txt");
		File receiver = new File("Receiver.txt");
		File receiver_5G = new File("Receiver5G.txt");
		
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		BufferedReader reader3 = null;
		
		//Grab the data from the 3 text files
		try (FileInputStream fis = new FileInputStream(transmission)) {
			reader = new BufferedReader(new FileReader(transmission));
			String text = null;
			
			while((text = reader.readLine()) != null) {
				trans.add(Double.parseDouble(text));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				
			}
		}
		
		try (FileInputStream fis = new FileInputStream(receiver)) {
			reader2 = new BufferedReader(new FileReader(receiver));
			String text2 = null;
			
			while((text2 = reader2.readLine()) != null) {
				rcv.add(Double.parseDouble(text2));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader2 != null) {
					reader2.close();
				}
			} catch (IOException e) {
				
			}
		}
		
		try (FileInputStream fis = new FileInputStream(receiver_5G)) {
			reader3 = new BufferedReader(new FileReader(receiver_5G));
			String text3 = null;
			
			while((text3 = reader3.readLine()) != null) {
				rcv_5G.add(Double.parseDouble(text3));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader3 != null) {
					reader3.close();
				}
			} catch (IOException e) {
				
			}
		}
		
		//variable to hold the counts
		int hits = 0;
		int hits_5G = 0;

		/*------------------------Calculations-------------------------*/
		//Calculate the 3rd Order Intermod with minimum one 5G frequency
		//+ + +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j=j+2) {
				//5Gen 1
				tempPimLow1 = trans.get(i) + trans.get(j) + gen5_1[0];
				tempPimHigh1 = trans.get(i+1) + trans.get(j+1) + gen5_1[1];
				//5Gen 2
				tempPimLow2 = trans.get(i) + trans.get(j) + gen5_2[0];
				tempPimHigh2 = trans.get(i+1) + trans.get(j+1) + gen5_2[1];
				//5Gen 3
				tempPimLow3 = trans.get(i) + trans.get(j) + gen5_3[0];
				tempPimHigh3 = trans.get(i+1) + trans.get(j+1) + gen5_3[1];
				//5Gen 4
				tempPimLow3 = trans.get(i) + trans.get(j) + gen5_4[0];
				tempPimHigh3 = trans.get(i+1) + trans.get(j+1) + gen5_4[1];
				//Push to the PIMs
				PIM1.add(tempPimLow1);
				PIM7.add(tempPimLow2);
				PIM13.add(tempPimLow3);
				PIM19.add(tempPimLow4);
				PIM1.add(tempPimHigh1);
				PIM7.add(tempPimHigh2);
				PIM13.add(tempPimHigh3);
				PIM19.add(tempPimHigh4);
				pimCalc1.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_1[0] + " " + gen5_1[1] + "] = ");
				pimCalc7.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_2[0] + " " + gen5_2[1] + "] = ");
				pimCalc13.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_3[0] + " " + gen5_3[1] + "] = ");
				pimCalc19.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_4[0] + " " + gen5_4[1] + "] = ");
			}
		}
		
		// - + +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j=j+2) {
				//5Gen 1
				tempPimLow1 = -trans.get(i+1) + trans.get(j) + gen5_1[0];
				tempPimHigh1 = -trans.get(i) + trans.get(j+1) + gen5_1[1];
				//5Gen 2
				tempPimLow2 = -trans.get(i+1) + trans.get(j) + gen5_2[0];
				tempPimHigh2 = -trans.get(i) + trans.get(j+1) + gen5_2[1];
				//5Gen 3
				tempPimLow3 = -trans.get(i+1) + trans.get(j) + gen5_3[0];
				tempPimHigh3 = -trans.get(i) + trans.get(j+1) + gen5_3[1];
				//5Gen 4
				tempPimLow4 = -trans.get(i+1) + trans.get(j) + gen5_4[0];
				tempPimHigh4 = -trans.get(i) + trans.get(j+1) + gen5_4[1];
				//Push to Pims
				PIM2.add(tempPimLow1);
				PIM8.add(tempPimLow2);
				PIM14.add(tempPimLow3);
				PIM20.add(tempPimLow4);
				PIM2.add(tempPimHigh1);
				PIM8.add(tempPimHigh2);
				PIM14.add(tempPimHigh3);
				PIM20.add(tempPimHigh4);
				pimCalc2.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_1[0] + " " + gen5_1[1] + "] = ");
				pimCalc8.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_2[0] + " " + gen5_2[1] + "] = ");
				pimCalc14.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_3[0] + " " + gen5_3[1] + "] = ");
				pimCalc20.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_4[0] + " " + gen5_4[1] + "] = ");
			}
		}
		
		// - - +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j=j+2) {
				//5Gen 1
				tempPimLow1 = -trans.get(i+1) - trans.get(j+1) + gen5_1[0];
				tempPimHigh1 = -trans.get(i) - trans.get(j) + gen5_1[1];
				//5Gen 2
				tempPimLow2 = -trans.get(i+1) - trans.get(j+1) + gen5_2[0];
				tempPimHigh2 = -trans.get(i) - trans.get(j) + gen5_2[1];
				//5Gen 3
				tempPimLow3 = -trans.get(i+1) - trans.get(j+1) + gen5_3[0];
				tempPimHigh3 = -trans.get(i) - trans.get(j) + gen5_3[1];
				//5Gen 4
				tempPimLow4 = -trans.get(i+1) - trans.get(j+1) + gen5_4[0];
				tempPimHigh4 = -trans.get(i) - trans.get(j) + gen5_4[1];
				//Push to Pim
				PIM3.add(tempPimLow1);
				PIM9.add(tempPimLow2);
				PIM15.add(tempPimLow3);
				PIM21.add(tempPimLow4);
				PIM3.add(tempPimHigh1);
				PIM9.add(tempPimHigh2);
				PIM15.add(tempPimHigh3);
				PIM21.add(tempPimHigh4);
				pimCalc3.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_1[0] + " " + gen5_1[1] + "] = ");
				pimCalc9.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_2[0] + " " + gen5_2[1] + "] = ");
				pimCalc15.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_3[0] + " " + gen5_3[1] + "] = ");
				pimCalc21.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_4[0] + " " + gen5_4[1] + "] = ");
			}
		}
		
		// + - +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j=j+2) {
				//5Gen 1
				tempPimLow1 = trans.get(i) - trans.get(j+1) + gen5_1[0];
				tempPimHigh1 = trans.get(i+1) - trans.get(j) + gen5_1[1];
				//5Gen 2
				tempPimLow2 = trans.get(i) - trans.get(j+1) + gen5_2[0];
				tempPimHigh2 = trans.get(i+1) - trans.get(j) + gen5_2[1];
				//5Gen 3
				tempPimLow3 = trans.get(i) - trans.get(j+1) + gen5_3[0];
				tempPimHigh3 = trans.get(i+1) - trans.get(j) + gen5_3[1];
				//5Gen 4
				tempPimLow4 = trans.get(i) - trans.get(j+1) + gen5_4[0];
				tempPimHigh4 = trans.get(i+1) - trans.get(j) + gen5_4[1];
				//Push to Pim
				PIM4.add(tempPimLow1);
				PIM10.add(tempPimLow2);
				PIM16.add(tempPimLow3);
				PIM22.add(tempPimLow4);
				PIM4.add(tempPimHigh1);
				PIM10.add(tempPimHigh2);
				PIM16.add(tempPimHigh3);
				PIM22.add(tempPimHigh4);
				pimCalc4.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_1[0] + " " + gen5_1[1] + "] = ");
				pimCalc10.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_2[0] + " " + gen5_2[1] + "] = ");
				pimCalc16.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_3[0] + " " + gen5_3[1] + "] = ");
				pimCalc22.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
						+ gen5_4[0] + " " + gen5_4[1] + "] = ");
			}
		}
		
		// + - -
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j=j+2) {
				//5Gen 1
				tempPimLow1 = trans.get(i) - trans.get(j+1) - gen5_1[1];
				tempPimHigh1 = trans.get(i+1) - trans.get(j) - gen5_1[0];
				//5Gen 2
				tempPimLow2 = trans.get(i) - trans.get(j+1) - gen5_2[1];
				tempPimHigh2 = trans.get(i+1) - trans.get(j) - gen5_2[0];
				//5Gen 3
				tempPimLow3 = trans.get(i) -  trans.get(j+1) - gen5_3[1];
				tempPimHigh3 = trans.get(i+1) - trans.get(j) - gen5_3[0];
				//5Gen 4
				tempPimLow4 = trans.get(i) - trans.get(j+1) - gen5_4[1];
				tempPimHigh4 = trans.get(i+1) - trans.get(j) - gen5_4[0];
				//Push to Pim
				PIM5.add(tempPimLow1);
				PIM11.add(tempPimLow2);
				PIM17.add(tempPimLow3);
				PIM23.add(tempPimLow4);
				PIM5.add(tempPimHigh1);
				PIM11.add(tempPimHigh2);
				PIM17.add(tempPimHigh3);
				PIM23.add(tempPimHigh4);
				pimCalc5.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_1[0] + " " + gen5_1[1] + "] = ");
				pimCalc11.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_2[0] + " " + gen5_2[1] + "] = ");
				pimCalc17.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_3[0] + " " + gen5_3[1] + "] = ");
				pimCalc23.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_4[0] + " " + gen5_4[1] + "] = ");
			}
		}
		
		// + + -
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j=j+2) {
				//5Gen 1
				tempPimLow1 = trans.get(i) + trans.get(j) - gen5_1[1];
				tempPimHigh1 = trans.get(i+1) + trans.get(j+1) - gen5_1[0];
				//5Gen 2
				tempPimLow2 = trans.get(i) + trans.get(j) - gen5_2[1];
				tempPimHigh2 = trans.get(i+1) + trans.get(j+1) - gen5_2[0];
				//5Gen 3
				tempPimLow3 = trans.get(i) + trans.get(j) - gen5_3[1];
				tempPimHigh3 = trans.get(i+1) + trans.get(j+1) - gen5_3[0];
				//5Gen 4
				tempPimLow4 = trans.get(i) + trans.get(j) - gen5_4[1];
				tempPimHigh4 = trans.get(i+1) + trans.get(j+1) - gen5_4[0];
				//Push to Pim
				PIM6.add(tempPimLow1);
				PIM12.add(tempPimLow2);
				PIM18.add(tempPimLow3);
				PIM24.add(tempPimLow4);
				PIM6.add(tempPimHigh1);
				PIM12.add(tempPimHigh2);
				PIM18.add(tempPimHigh3);
				PIM24.add(tempPimHigh4);
				pimCalc6.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_1[0] + " " + gen5_1[1] + "] = ");
				pimCalc12.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_2[0] + " " + gen5_2[1] + "] = ");
				pimCalc18.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_3[0] + " " + gen5_3[1] + "] = ");
				pimCalc24.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
						+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
						+ gen5_4[0] + " " + gen5_4[1] + "] = ");
			}
		}

		//Calculate 3rd order Intermod using all frequencies 
		//+ + +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j = j+2) {
				for(int k = j; k < trans.size(); k = k+2) {
					tempPimLow5 = trans.get(i) + trans.get(j) + trans.get(k);
					tempPimHigh5 = trans.get(i+1) + trans.get(j+1) + trans.get(k+1);
					pimAll1.add(tempPimLow5);
					pimAll1.add(tempPimHigh5);
					pimAllCalc1.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
							+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
							+ trans.get(k) + " " + trans.get(k+1) + "] = ");
				}
			}
		}
		
		// - + +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j = j+2) {
				for(int k = j; k < trans.size(); k = k+2) {
					tempPimLow5 = -trans.get(i+1) + trans.get(j) + trans.get(k);
					tempPimHigh5 = -trans.get(i) + trans.get(j+1) + trans.get(k+1);
					pimAll2.add(tempPimLow5);
					pimAll2.add(tempPimHigh5);
					pimAllCalc2.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
							+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
							+ trans.get(k) + " " + trans.get(k+1) + "] = ");
				}
			}
		}
		
		// - - +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j = j+2) {
				for(int k = j; k < trans.size(); k = k+2) {
					tempPimLow5 = -trans.get(i+1) - trans.get(j+1) + trans.get(k);
					tempPimHigh5 = -trans.get(i) - trans.get(j) + trans.get(k+1);
					pimAll3.add(tempPimLow5);
					pimAll3.add(tempPimHigh5);
					pimAllCalc3.add(" - [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
							+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
							+ trans.get(k) + " " + trans.get(k+1) + "] = ");
				}
			}
		}
		
		//+ - +
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j = j+2) {
				for(int k = j; k < trans.size(); k = k+2) {
					tempPimLow5 = trans.get(i) - trans.get(j+1) + trans.get(k);
					tempPimHigh5 = trans.get(i+1) - trans.get(j) + trans.get(k+1);
					pimAll4.add(tempPimLow5);
					pimAll4.add(tempPimHigh5);
					pimAllCalc4.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
							+ trans.get(j) + " " + trans.get(j+1) + "] + [" 
							+ trans.get(k) + " " + trans.get(k+1) + "] = ");
				}
			}
		}
		
		//+ - -
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j = j+2) {
				for(int k = j; k < trans.size(); k = k+2) {
					tempPimLow5 = trans.get(i) - trans.get(j+1) - trans.get(k+1);
					tempPimHigh5 = trans.get(i+1) - trans.get(j) - trans.get(k);
					pimAll5.add(tempPimLow5);
					pimAll5.add(tempPimHigh5);
					pimAllCalc5.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] - [" 
							+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
							+ trans.get(k) + " " + trans.get(k+1) + "] = ");
				}
			}
		}
		
		//+ + -
		for(int i = 0; i < trans.size(); i = i+2) {
			for(int j = i; j < trans.size(); j = j+2) {
				for(int k = j; k < trans.size(); k = k+2) {
					tempPimLow5 = trans.get(i) + trans.get(j) - trans.get(k+1);
					tempPimHigh5 = trans.get(i+1) + trans.get(j+1) - trans.get(k);
					pimAll6.add(tempPimLow5);
					pimAll6.add(tempPimHigh5);
					pimAllCalc6.add(" + [" + trans.get(i) + " " + trans.get(i+1) + "] + [" 
							+ trans.get(j) + " " + trans.get(j+1) + "] - [" 
							+ trans.get(k) + " " + trans.get(k+1) + "] = ");
				}
			}
		}
		
		/*-----------------------------Calculations Complete ------------------------------------------*/
		
		/*=----------------------------Begin doing comparison-----------------------------------------*/
		
		//Comparison with the method using at least one 5G frequency
		for(int x = 0; x < PIM1.size(); x = x+2) {
			for(int q = 0; q < rcv.size(); q = q+2) {
				//5Gen 1
				if(PIM1.get(x+1) <= rcv.get(q+1) && PIM1.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc1.get(x/2) + "[" + PIM1.get(x) + " " + PIM1.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM1.get(x) >= rcv.get(q) && PIM1.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc1.get(x/2) + "[" + PIM1.get(x) + " " + PIM1.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM2.get(x+1) <= rcv.get(q+1) && PIM2.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc2.get(x/2) + "[" + PIM2.get(x) + " " + PIM2.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM2.get(x) >= rcv.get(q) && PIM2.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc2.get(x/2) + "[" + PIM2.get(x) + " " + PIM2.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM3.get(x+1) <= rcv.get(q+1) && PIM3.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc3.get(x/2) + "[" + PIM3.get(x) + " " + PIM3.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM3.get(x) >= rcv.get(q) && PIM3.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc3.get(x/2) + "[" + PIM3.get(x) + " " + PIM3.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM4.get(x+1) <= rcv.get(q+1) && PIM4.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc4.get(x/2) + "[" + PIM4.get(x) + " " + PIM4.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM4.get(x) >= rcv.get(q) && PIM4.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc4.get(x/2) + "[" + PIM4.get(x) + " " + PIM4.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM5.get(x+1) <= rcv.get(q+1) && PIM5.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc5.get(x/2) + "[" + PIM5.get(x) + " " + PIM5.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM5.get(x) >= rcv.get(q) && PIM5.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc5.get(x/2) + "[" + PIM5.get(x) + " " + PIM5.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM6.get(x+1) <= rcv.get(q+1) && PIM6.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc6.get(x/2) + "[" + PIM6.get(x) + " " + PIM6.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM6.get(x) >= rcv.get(q) && PIM6.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc6.get(x/2) + "[" + PIM6.get(x) + " " + PIM6.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				//5Gen 2
				if(PIM7.get(x+1) <= rcv.get(q+1) && PIM7.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc7.get(x/2) + "[" + PIM7.get(x) + " " + PIM7.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM7.get(x) >= rcv.get(q) && PIM7.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc7.get(x/2) + "[" + PIM7.get(x) + " " + PIM7.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM8.get(x+1) <= rcv.get(q+1) && PIM8.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc8.get(x/2) + "[" + PIM8.get(x) + " " + PIM8.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM8.get(x) >= rcv.get(q) && PIM8.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc8.get(x/2) + "[" + PIM8.get(x) + " " + PIM8.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM9.get(x+1) <= rcv.get(q+1) && PIM9.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc9.get(x/2) + "[" + PIM9.get(x) + " " + PIM9.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM9.get(x) >= rcv.get(q) && PIM9.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc9.get(x/2) + "[" + PIM9.get(x) + " " + PIM9.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM10.get(x+1) <= rcv.get(q+1) && PIM10.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc10.get(x/2) + "[" + PIM10.get(x) + " " + PIM10.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM10.get(x) >= rcv.get(q) && PIM10.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc10.get(x/2) + "[" + PIM10.get(x) + " " + PIM10.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM11.get(x+1) <= rcv.get(q+1) && PIM11.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc11.get(x/2) + "[" + PIM11.get(x) + " " + PIM11.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM11.get(x) >= rcv.get(q) && PIM11.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc11.get(x/2) + "[" + PIM11.get(x) + " " + PIM11.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM12.get(x+1) <= rcv.get(q+1) && PIM12.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc12.get(x/2) + "[" + PIM12.get(x) + " " + PIM12.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM12.get(x) >= rcv.get(q) && PIM12.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc12.get(x/2) + "[" + PIM12.get(x) + " " + PIM12.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				//5Gen 3
				if(PIM13.get(x+1) <= rcv.get(q+1) && PIM13.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc13.get(x/2) + "[" + PIM13.get(x) + " " + PIM13.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM13.get(x) >= rcv.get(q) && PIM13.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc13.get(x/2) + "[" + PIM13.get(x) + " " + PIM13.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM14.get(x+1) <= rcv.get(q+1) && PIM14.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc14.get(x/2) + "[" + PIM14.get(x) + " " + PIM14.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM14.get(x) >= rcv.get(q) && PIM14.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc14.get(x/2) + "[" + PIM14.get(x) + " " + PIM14.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM15.get(x+1) <= rcv.get(q+1) && PIM15.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc14.get(x/2) + "[" + PIM15.get(x) + " " + PIM15.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM15.get(x) >= rcv.get(q) && PIM15.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc15.get(x/2) + "[" + PIM15.get(x) + " " + PIM15.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM16.get(x+1) <= rcv.get(q+1) && PIM16.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc16.get(x/2) + "[" + PIM16.get(x) + " " + PIM16.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM16.get(x) >= rcv.get(q) && PIM16.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc16.get(x/2) + "[" + PIM16.get(x) + " " + PIM16.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM17.get(x+1) <= rcv.get(q+1) && PIM17.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc17.get(x/2) + "[" + PIM17.get(x) + " " + PIM17.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM17.get(x) >= rcv.get(q) && PIM17.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc17.get(x/2) + "[" + PIM17.get(x) + " " + PIM17.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM18.get(x+1) <= rcv.get(q+1) && PIM18.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc18.get(x/2) + "[" + PIM18.get(x) + " " + PIM18.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM18.get(x) >= rcv.get(q) && PIM18.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc18.get(x/2) + "[" + PIM18.get(x) + " " + PIM18.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				//5Gen 4
				if(PIM19.get(x+1) <= rcv.get(q+1) && PIM19.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc19.get(x/2) + "[" + PIM19.get(x) + " " + PIM19.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM19.get(x) >= rcv.get(q) && PIM19.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc19.get(x/2) + "[" + PIM19.get(x) + " " + PIM19.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM20.get(x+1) <= rcv.get(q+1) && PIM20.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc20.get(x/2) + "[" + PIM20.get(x) + " " + PIM20.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM20.get(x) >= rcv.get(q) && PIM20.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc20.get(x/2) + "[" + PIM20.get(x) + " " + PIM20.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM21.get(x+1) <= rcv.get(q+1) && PIM21.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc21.get(x/2) + "[" + PIM21.get(x) + " " + PIM21.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM21.get(x) >= rcv.get(q) && PIM21.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc21.get(x/2) + "[" + PIM21.get(x) + " " + PIM21.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM22.get(x+1) <= rcv.get(q+1) && PIM22.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc22.get(x/2) + "[" + PIM22.get(x) + " " + PIM22.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM22.get(x) >= rcv.get(q) && PIM22.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc22.get(x/2) + "[" + PIM22.get(x) + " " + PIM22.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM23.get(x+1) <= rcv.get(q+1) && PIM23.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc23.get(x/2) + "[" + PIM23.get(x) + " " + PIM23.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM23.get(x) >= rcv.get(q) && PIM23.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc23.get(x/2) + "[" + PIM23.get(x) + " " + PIM23.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM24.get(x+1) <= rcv.get(q+1) && PIM24.get(x+1) >= rcv.get(q)) {
					whereTrans.add(pimCalc24.get(x/2) + "[" + PIM24.get(x) + " " + PIM24.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
				if(PIM24.get(x) >= rcv.get(q) && PIM24.get(x) < rcv.get(q+1)) {
					whereTrans.add(pimCalc24.get(x/2) + "[" + PIM24.get(x) + " " + PIM24.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits++;
				}
			}
		}
		
		//Comparison using all frequency band on receiver and transmiter
		for(int x = 0; x < pimAll1.size(); x = x+2) {
			for(int q = 0; q < rcv.size(); q = q+2) {
				//+ + + Pattern hit checks
				if(pimAll1.get(x+1) <= rcv.get(q+1) && pimAll1.get(x+1) >= rcv.get(q)) {
					whereTrans1.add(pimAllCalc1.get(x/2) + "[" + pimAll1.get(x) + " " + pimAll1.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				if(pimAll1.get(x) >= rcv.get(q) && pimAll1.get(x) < rcv.get(q+1)) {
					whereTrans1.add(pimAllCalc1.get(x/2) + "[" + pimAll1.get(x) + " " + pimAll1.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				//  + + Pattern hit checks
				if(pimAll2.get(x+1) <= rcv.get(q+1) && pimAll2.get(x+1) >= rcv.get(q)) {
					whereTrans1.add(pimAllCalc2.get(x/2) + "[" + pimAll2.get(x) + " " + pimAll2.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				if(pimAll2.get(x) >= rcv.get(q) && pimAll2.get(x) < rcv.get(q+1)) {
					whereTrans1.add(pimAllCalc2.get(x/2) + "[" + pimAll2.get(x) + " " + pimAll2.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				//    + Pattern hit checks
				if(pimAll3.get(x+1) <= rcv.get(q+1) && pimAll3.get(x+1) >= rcv.get(q)) {
					whereTrans1.add(pimAllCalc3.get(x/2) + "[" + pimAll3.get(x) + " " + pimAll3.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				if(pimAll3.get(x) >= rcv.get(q) && pimAll3.get(x) < rcv.get(q+1)) {
					whereTrans1.add(pimAllCalc3.get(x/2) + "[" + pimAll3.get(x) + " " + pimAll3.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				//+   +Pattern hit checks
				if(pimAll4.get(x+1) <= rcv.get(q+1) && pimAll4.get(x+1) >= rcv.get(q)) {
					whereTrans1.add(pimAllCalc4.get(x/2) + "[" + pimAll4.get(x) + " " + pimAll4.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				if(pimAll4.get(x) >= rcv.get(q) && pimAll4.get(x) < rcv.get(q+1)) {
					whereTrans1.add(pimAllCalc4.get(x/2) + "[" + pimAll4.get(x) + " " + pimAll4.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				//+     Pattern hit checks
				if(pimAll5.get(x+1) <= rcv.get(q+1) && pimAll5.get(x+1) >= rcv.get(q)) {
					whereTrans1.add(pimAllCalc5.get(x/2) + "[" + pimAll5.get(x) + " " + pimAll5.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				if(pimAll5.get(x) >= rcv.get(q) && pimAll5.get(x) < rcv.get(q+1)) {
					whereTrans1.add(pimAllCalc5.get(x/2) + "[" + pimAll5.get(x) + " " + pimAll5.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				//+ +   Pattern hit checks
				if(pimAll6.get(x+1) <= rcv.get(q+1) && pimAll6.get(x+1) >= rcv.get(q)) {
					whereTrans1.add(pimAllCalc6.get(x/2) + "[" + pimAll6.get(x) + " " + pimAll6.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
				if(pimAll6.get(x) >= rcv.get(q) && pimAll6.get(x) < rcv.get(q+1)) {
					whereTrans1.add(pimAllCalc6.get(x/2) + "[" + pimAll6.get(x) + " " + pimAll6.get(x+1) + "] RX: " + rcv.get(q) + " " + rcv.get(q+1));
					hits_5G++;
				}
			}
		}
		
		//Write list to file
		//5Gen 1
		PrintStream pimStream1 = null;
		PrintStream pimStream2 = null;
		PrintStream pimStream3 = null;
		PrintStream pimStream4 = null;
		PrintStream pimStream5 = null;
		PrintStream pimStream6 = null;
		//5Gen 2
		PrintStream pimStream7 = null;
		PrintStream pimStream8 = null;
		PrintStream pimStream9 = null;
		PrintStream pimStream10 = null;
		PrintStream pimStream11 = null;
		PrintStream pimStream12 = null;
		//5Gen 3
		PrintStream pimStream13 = null;
		PrintStream pimStream14 = null;
		PrintStream pimStream15 = null;
		PrintStream pimStream16 = null;
		PrintStream pimStream17 = null;
		PrintStream pimStream18 = null;
		//5Gen 4
		PrintStream pimStream19 = null;
		PrintStream pimStream20 = null;
		PrintStream pimStream21 = null;
		PrintStream pimStream22 = null;
		PrintStream pimStream23 = null;
		PrintStream pimStream24 = null;
		
		//All frequencies
		PrintStream pimAllStream1 = null;
		PrintStream pimAllStream2 = null;
		PrintStream pimAllStream3 = null;
		PrintStream pimAllStream4 = null;
		PrintStream pimAllStream5 = null;
		PrintStream pimAllStream6 = null;
		
		//Output location of the hits
		PrintStream transStream = null;
		PrintStream transAllStream = null;
		
		//Assign files to output the data into
		try {
			//5Gen 1
			pimStream1 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM1.txt");
			pimStream2 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM2.txt");
			pimStream3 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM3.txt");
			pimStream4 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM4.txt");
			pimStream5 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM5.txt");
			pimStream6 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM6.txt");
			//5Gen 2
			pimStream7 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM7.txt");
			pimStream8 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM8.txt");
			pimStream9 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM9.txt");
			pimStream10 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM10.txt");
			pimStream11 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM11.txt");
			pimStream12 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM12.txt");
			//5Gen 3
			pimStream13 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM13.txt");
			pimStream14 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM14.txt");
			pimStream15 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM15.txt");
			pimStream16 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM16.txt");
			pimStream17 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM17.txt");
			pimStream18 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM18.txt");
			//5Gen 4
			pimStream19 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM19.txt");
			pimStream20 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM20.txt");
			pimStream21 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM21.txt");
			pimStream22 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM22.txt");
			pimStream23 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM23.txt");
			pimStream24 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\PIM24.txt");
			
			//All Frequencies
			pimAllStream1 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\pimAll1.txt");
			pimAllStream2 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\pimAll2.txt");
			pimAllStream3 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\pimAll3.txt");
			pimAllStream4 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\pimAll4.txt");
			pimAllStream5 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\pimAll5.txt");
			pimAllStream6 = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\pimAll6.txt");
			
			//Location of hits
			transStream = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\whereTrans.txt");
			transAllStream = new PrintStream("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\whereAllTrans.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < PIM1.size(); i++) {
			pimStream1.println(PIM1.get(i));
			pimStream2.println(PIM2.get(i));
			pimStream3.println(PIM3.get(i));
			pimStream4.println(PIM4.get(i));
			pimStream5.println(PIM5.get(i));
			pimStream6.println(PIM6.get(i));
			pimStream7.println(PIM7.get(i));
			pimStream8.println(PIM8.get(i));
			pimStream9.println(PIM9.get(i));
			pimStream10.println(PIM10.get(i));
			pimStream11.println(PIM11.get(i));
			pimStream12.println(PIM12.get(i));
			pimStream13.println(PIM13.get(i));
			pimStream14.println(PIM14.get(i));
			pimStream15.println(PIM15.get(i));
			pimStream16.println(PIM16.get(i));
			pimStream17.println(PIM17.get(i));
			pimStream18.println(PIM18.get(i));
			pimStream19.println(PIM19.get(i));
			pimStream20.println(PIM20.get(i));
			pimStream21.println(PIM21.get(i));
			pimStream22.println(PIM22.get(i));
			pimStream23.println(PIM23.get(i));
			pimStream24.println(PIM24.get(i));
			
		}
		
		for(int i = 0; i < pimAll1.size(); i++) {
			pimAllStream1.println(pimAll1.get(i));
			pimAllStream2.println(pimAll2.get(i));
			pimAllStream3.println(pimAll3.get(i));
			pimAllStream4.println(pimAll4.get(i));
			pimAllStream5.println(pimAll5.get(i));
			pimAllStream6.println(pimAll6.get(i));
		}
		
		for(int i = 0; i < whereTrans.size(); i++) {
			transStream.println(whereTrans.get(i));
		}
		
		for(int i = 0; i < whereTrans1.size(); i++) {
			transAllStream.println(whereTrans1.get(i));
		}
		
		System.out.println("Number of hits for adding in 5G: " + hits);
		System.out.println("Number of hits for all frequencies: " + hits_5G);
	}
}
