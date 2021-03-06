package com.calftracker.project.models;
import java.util.ArrayList;

public class Vaccine
{
	private String name;
	private ArrayList<Vacc_Range> toBeAdministered;
	private double dosage;
	private String dosageUnits;
	private String methodOfAdministration;
	private ArrayList<Calf> vaccineCalves;

	/**
	 * @param name
	 * @param dosage
	 * @param dosageUnits
	 * @param methodOfAdministration
	 * @param toBeAdministered
	 */

	public Vaccine(String name, ArrayList<Vacc_Range> toBeAdministered, double dosage,
				   String dosageUnits, String methodOfAdministration) {
		super();
		this.name = name;
		this.toBeAdministered = toBeAdministered;
		this.dosage = dosage;
		this.dosageUnits = dosageUnits;
		this.methodOfAdministration = methodOfAdministration;
		this.vaccineCalves = new ArrayList<>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the toBeAdministered
	 */
	public ArrayList<Vacc_Range> getToBeAdministered() {
		return toBeAdministered;
	}

	/**
	 * @param toBeAdministered the toBeAdministered to set
	 */
	public void setToBeAdministered(ArrayList<Vacc_Range> toBeAdministered) {
		this.toBeAdministered = toBeAdministered;
	}

	/**
	 * @return the dosage
	 */
	public double getDosage() {
		return dosage;
	}

	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(double dosage) {
		this.dosage = dosage;
	}

	/**
	 * @return the dosageUnits
	 */
	public String getDosageUnits() {
		return dosageUnits;
	}

	/**
	 * @param dosageUnits the dosageUnits to set
	 */
	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}

	/**
	 * @return the methodOfAdministration
	 */
	public String getMethodOfAdministration() {
		return methodOfAdministration;
	}

	/**
	 * @param methodOfAdministration the methodOfAdministration to set
	 */
	public void setMethodOfAdministration(String methodOfAdministration) {
		this.methodOfAdministration = methodOfAdministration;
	}

	/**
	 *
	 * @param name
	 * @param dosage
	 * @param dosageUnits
	 * @param toBeAdministered
	 * @param methodOfAdministration
	 * @return Vaccine the new vaccine created
	 */
	public Vaccine createVaccine(String name, Double dosage, String dosageUnits,
								 ArrayList<Vacc_Range> toBeAdministered, String methodOfAdministration){
		return new Vaccine(name,toBeAdministered,dosage,dosageUnits,methodOfAdministration);
	}

	public ArrayList<Calf> getVaccineCalves() {
		return vaccineCalves;
	}

	public void setVaccineCalves(ArrayList<Calf> vaccineCalves) {
		this.vaccineCalves = vaccineCalves;
	}
}
