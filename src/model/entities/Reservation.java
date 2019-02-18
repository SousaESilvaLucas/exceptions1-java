package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.exceptions.DomainException;

public class Reservation {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public Reservation(){
		
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut)  {
		if (!checkOut.after(checkIn)) {
			throw new DomainException
			("Error in reservation: Check-out date must be after check-in date");

		}
		else {
			this.roomNumber = roomNumber;
			this.checkIn = checkIn;
			this.checkOut = checkOut;		
		}
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public Integer duration() {
		return (int) ((checkOut.getTime() - checkIn.getTime())/(24*60*60*1000));
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		Date today = new Date();
		if (!checkIn.after(today) || !checkOut.after(today)) {
			throw  new DomainException
			("Error in reservation: "
					+ "Reservation dates for update must be future dates");
		}
		else if (!checkOut.after(checkIn)) {
			throw new DomainException
			("Error in reservation: "
					+ "Check-out date must be after check-in date");
		}
		else {
			this.checkIn = checkIn;
			this.checkOut = checkOut;
		}
	}

	@Override
	public String toString() {
		return "Reservation: Room " + 
				roomNumber + ", check-in: " +
				sdf.format(checkIn) + ", check-out: " +
				sdf.format(checkOut) +", " + duration() +
				" nights";
	}
	
	
}
