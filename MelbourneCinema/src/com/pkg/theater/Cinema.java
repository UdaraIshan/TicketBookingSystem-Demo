package com.pkg.theater;

public class Cinema {
	
	// properties declaration
	private int rows;
	private int seat;
	private int empSeats;
	private double Sprice;
	private double Pprice;
	private double Fprice;
	private double RSPrice;
	private double RPPrice;
	private double RFPrice;
	private double totalRefund;
	private int standardSeatCount;
	private int pensionerSeatCount;
	private int frequentSeatCount;
	private char[] seats;
	
	// constant for seats 
	private final int seatsPerRow = 10;
	
	// constructors 
	public Cinema(int rows) {
		this.rows = rows;
		standardSeatCount = 0;
        pensionerSeatCount = 0;
        frequentSeatCount = 0;
        
        seats = new char[rows * seatsPerRow];
        for(int i = 0; i < seats.length; i++) {
        	seats[i] = '-';
        }
	}

	// getters and setters
	public char[] getSeats() {
		return seats;
	}

	public void setSeats(char[] seats) {
		this.seats = seats;
	}

	public int getStandardSeatCount() {
		return standardSeatCount;
	}

	public void setStandardSeatCount(int standardSeatCount) {
		this.standardSeatCount = standardSeatCount;
	}

	public int getPensionerSeatCount() {
		return pensionerSeatCount;
	}

	public void setPensionerSeatCount(int pensionerSeatCount) {
		this.pensionerSeatCount = pensionerSeatCount;
	}

	public int getFrequenteatCount() {
		return frequentSeatCount;
	}

	public void setFrequenteatCount(int frequenteatCount) {
		this.frequentSeatCount = frequenteatCount;
	}

	// method for calculate whole seats count in the theater
	public int Seats(int rows) {
		return seat = rows * seatsPerRow;
	}
	
	// calculate booked seats count
	public int bookedSeatsCount() {
		int bookedSeats = 0;
	    for (char seat : seats) {
	        if (seat == 'S' || seat == 'P' || seat == 'F') {
	            bookedSeats++;
	        }
	    }
	    return bookedSeats;
	}
	
	// calculate available seats count
	public int availableSeatCount(int count) {
		return empSeats = seat - count;
	}
	
	// calculate standard ticket prices for standard seats booking (for receipt)
	// calculate standard seats count booked so far
	public double standardSeatPrice(double standard, int standardCount) {
		Sprice = standard * standardCount;
		standardSeatCount += standardCount;
		return Sprice;
	}
	
	// calculate pensioner ticket prices for pensioner seats booking (for receipt)
	// calculate pensioner seats count booked so far
	public double pensionerSeatPrice(double pensioner, int pensionerCount) {
		Pprice = pensioner * pensionerCount;
		pensionerSeatCount += pensionerCount;
		return Pprice;
	}
	
	// calculate frequent ticket prices for frequent seats booking (for receipt)
	// calculate frequent seats count booked so far
	public double frequentSeatPrice(double frequent, int frequentCount) {
		Fprice = frequent * frequentCount;
		frequentSeatCount += frequentCount;
		return Fprice;
	}
	
	// calculate total amount for receipt
	public double totalPrice() {
		return (Sprice + Pprice + Fprice);
	}
	
	// calculate refund ticket prices for standard tickets
	public double refundStandard(int refund, double standard) {
		//standardSeatCount -= refund;
        return RSPrice = refund * standard;
    }

	// calculate refund ticket prices for pensioner tickets
    public double refundPensioner(int refund, double pensioner) {
        return RPPrice = refund * pensioner;
    }

    // calculate refund ticket prices for frequent tickets
    public double refundFrequent(int refund, double frequent) {
        return RFPrice = refund * frequent;
    }

    // calculate total refund amount for receipt
    public double totalRefund() {
    	totalRefund = RSPrice + RPPrice + RFPrice;
    	return totalRefund;
    }
	// calculate whole amount of all tickets sold
	public double totalIncome(double standard, double pensioner, double frequent) {
		return (standardSeatCount * standard + pensionerSeatCount * pensioner + frequentSeatCount * frequent);
	}
}
