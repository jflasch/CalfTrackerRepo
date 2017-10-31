package com.example.brett.calftracker;

/**
 * Simple class to save a message and a date entered, used in calf.notes,
 * 
 * @author JT
 *
 */
public class Note {
	private String message;
	private Easy_Date dateEntered;
	
	/**
	 * @param message is the message user enters in the note
	 * @param dateEntered is the date the message was entered
	 */
	public Note(String message, Easy_Date dateEntered) {
		super();
		this.message = message;
		this.dateEntered = dateEntered;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the dateEntered
	 */
	public Easy_Date getDateEntered() {
		return dateEntered;
	}

	/**
	 * @param dateEntered the dateEntered to set
	 */
	public void setDateEntered(Easy_Date dateEntered) {
		this.dateEntered = dateEntered;
	}
}
