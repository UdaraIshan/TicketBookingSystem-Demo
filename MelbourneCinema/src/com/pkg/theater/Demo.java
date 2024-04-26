package com.pkg.theater;

import java.util.Scanner;

public class Demo {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// declaring variables for store seats counts for multiple seat types temporary
		int standardCount = 0;
		int pensionerCount = 0;
		int frequentCount = 0;

		// get inputs form cinema hall staff
		// how many rows in the hall and date of the show
		System.out.println("How many number of rows in theater :-");
		int rows = input.nextInt();
		System.out.println("Date of the show :- ");
		String date = input.next();

		// get ticket prices 
		System.out.println("Enter the Standard seat price(AUD): ");
		double standard = input.nextDouble();
		System.out.println("Enter the Pensioner seat price(AUD): ");
		double pensioner = input.nextDouble();
		System.out.println("Enter the Frequent seat price(AUD): ");
		double frequent = input.nextDouble();

		// counting for how many tickets booked so far 
		int count = 0;
		int category;
		
		// object declaration, array creation and calling methods from Cinema class
		Cinema cinema = new Cinema(rows);
		int seat = cinema.Seats(rows);
		Cinema[] theater = new Cinema[seat];
		char[] seatStatus = cinema.getSeats();
		char seatType[] = new char[seat];

		do {
			// main menu printing
			System.out.println("\n            - Main Menu -");
			System.out.println("********************************************");
			System.out.println("1. Booking seats");
			System.out.println("2. Available seats information");
			System.out.println("3. Refunding");
			System.out.println("4. Statistic Report");
			System.out.println("5. Exit");
			int option = input.nextInt();
			System.out.println();

			// using switch for options
			switch(option) {
			case 1: 
				// user input for seat count for booking
				System.out.println("How many seats do you want to book?");
				int booking = input.nextInt();

				int availableSeats = cinema.availableSeatCount(count);

				// check whether that amount of seats available or not
				if(booking > availableSeats) {
					System.out.println("Sorry we have only "+availableSeats+" seats available.\n");
				} else {
					// seat booking process
					int seatBooked = 0;
					int no = 1;
					for(int i = count; i < count + booking; i++) {

						while(seatBooked < booking) {

							theater[i] = new Cinema(seat);
							int seatNo;
							boolean validSeat = false;

							do {
								// get seat number from user 
								System.out.println("\nEnter the seat number for ticket#" + no);
								seatNo = input.nextInt();

								// error message for incorrect seat number
								if (seatNo <= 0 || seatNo > seat) {
									System.out.println("Sorry.. Invalid seat number.\n");

								// error message for reserved seat number
								} else if(seatStatus[seatNo-1] != '-') {
									System.out.println("Sorry.. This seat is reserved.");
								} else {
									validSeat = true;

									// get seat category from user
									System.out.println("\n**********************************************************************");
									System.out.println("Select the seat category for ticket #" + no);
									System.out.println("1. Standard");
									System.out.println("2. Pensioner");
									System.out.println("3. Freequent");
									category = input.nextInt();

									// error message for invalid seat category
									if(category <= 0 || category >= 4 || !validSeat) {
										System.out.println("Invalid seat category\n");
										break;
									} else {
										// seat booking....
										switch(category) {
										case 1:
											standardCount++;
											seatType[seatNo-1] = 'S';
											seatStatus[seatNo-1] = 'S';
											seatBooked++;
											availableSeats--;
											break;
										case 2:
											pensionerCount++;
											seatType[seatNo-1] = 'P';
											seatStatus[seatNo-1] = 'P';
											seatBooked++;
											availableSeats--;
											break;
										case 3:
											frequentCount++;
											seatType[seatNo-1] = 'F';
											seatStatus[seatNo-1] = 'F';
											seatBooked++;
											availableSeats--;
											break;

										}
										no++;
									}
								}
							}while(!validSeat && seatBooked == booking);
						}
					}
					// increment count (for calculate available seat count)
					count += booking;
					
					// get prices to booked seat from Cinema class
					double Sprice = cinema.standardSeatPrice(standard, standardCount);
					double Pprice = cinema.pensionerSeatPrice(pensioner, pensionerCount);
					double Fprice = cinema.frequentSeatPrice(frequent, frequentCount);
					double total = cinema.totalPrice();

					// receipt printing
					System.out.println("\n      Receipt");
					System.out.println("     ************\n");
					System.out.println("Date: " + date);
					System.out.println("Number of seats booked: " + booking);
					System.out.printf(" %d Standard @ %.2f - %.2f\n",standardCount,standard,Sprice);
					System.out.printf(" %d Pensioner @ %.2f - %.2f\n",pensionerCount,pensioner,Pprice);
					System.out.printf(" %d Frequent @ %.2f - %.2f\n",frequentCount,frequent,Fprice);
					System.out.printf("                Total: %.2f\n",total);

					// put temporary seat count back to 0
					standardCount = 0;
					pensionerCount = 0;
					frequentCount = 0;
				}
				break;

			case 2:
				// print structure of the cinema hall
				System.out.println("          -------------------------------");
				System.out.println("          |*           Screen          *|");
				System.out.println("          -------------------------------\n");

				int columnCount = 0;
				System.out.print("     ");
				for(int i = 0; i < seatStatus.length; i++) {
					System.out.print(" " + (i+1) + ":" + seatStatus[i]);
					columnCount++;

					if (columnCount == 5) {
						System.out.print("   ");
					}

					if (columnCount == 10) {
						System.out.println();
						columnCount = 0;
					}
				}

				// print available seat count
				availableSeats = cinema.availableSeatCount(count);
				System.out.println(" \nNumber of available seats: " + availableSeats + "\n");
				break;

			case 3:
				// refunding process
				// variables for refunding section
				int refundCountS = 0;
				int refundCountP = 0;
				int refundCountF = 0;
				double refundAmountS = 0;
				double refundAmountP = 0;
				double refundAmountF = 0;

				boolean receiptPrint = false;
				int refundTotalCount;

				do {
					// get seat number want to refund from user
					System.out.println("Enter seat number to refund or enter (-1) to exit:");
					int refundSeatNo = input.nextInt();

					// if -1 exit from refund
					if (refundSeatNo == -1) {
						break;
					}

					// error message for invalid seat number
					if (refundSeatNo < 1 || refundSeatNo > seat) {
						System.out.println("Invalid seat number. Please try again.\n");
						continue;
					}

					// error message for non-reserved seat number
					if (seatStatus[refundSeatNo-1] == '-') {
						System.out.println("Sorry. This seat not reserved.\n");
					} else {
						seatStatus[refundSeatNo-1] = '-';

						char type = seatType[refundSeatNo-1];

						// refunding process....
						switch(type) {
						case 'S':
							standardCount--;
							count--;
							refundCountS++;
							refundAmountS = cinema.refundStandard(1, standard);
							break;

						case 'P':
							pensionerCount--;
							count--;
							refundCountP++;
							refundAmountP = cinema.refundPensioner(1, pensioner);
							break;

						case 'F':
							frequentCount--;
							count--;
							refundCountF++;
							refundAmountF = cinema.refundFrequent(1, frequent);
							break;
						}
						receiptPrint = true;

						System.out.println("\nDo you want to refund another seat? (y/n):");
						char choice = input.next().charAt(0);

						if (choice != 'y') {
							break;
						}
					}
				} while(true);

				if (receiptPrint == true) {
					double totalRefund = cinema.totalRefund();
					refundTotalCount = refundCountS + refundCountP + refundCountF;

					// receipt printing
					System.out.println("\n      Refund Receipt");
					System.out.println("      ******************\n");
					System.out.println("Date: " + date);
					System.out.println("Number of seats refund: " + refundTotalCount);
					System.out.printf(" %d Standard @ %.2f - %.2f\n", refundCountS, standard, refundAmountS);
					System.out.printf(" %d Pensioner @ %.2f - %.2f\n", refundCountP, pensioner, refundAmountP);
					System.out.printf(" %d Frequent @ %.2f - %.2f\n", refundCountF, frequent, refundAmountF);
					System.out.printf("                Total: %.2f\n\n", totalRefund);

					// temporary variables put back to 0
					refundCountS = 0;
					refundCountP = 0;
					refundCountF = 0;
					refundAmountS = 0;
					refundAmountP = 0;
					refundAmountF = 0;

				}
				break;

			case 4:
				
				// get details from Cinema class want to calculate statistic report data and calculate them
				int bookedSeats = cinema.bookedSeatsCount();
				double percentageSold = (bookedSeats / (double) seat) * 100;
				double income = cinema.totalIncome(standard, pensioner, frequent);
				double averagePrice = bookedSeats == 0 ? 0 : (income/bookedSeats);

				// report printing
				System.out.println("\n     Statistic Report");
				System.out.println("   ************************\n");
				System.out.println("Number of seats sold :" + bookedSeats);
				System.out.println("Percentage of seats sold(%) :" + percentageSold);
				System.out.printf("Average price of the booked seat :%.2f\n", averagePrice);
				System.out.println("*********************************************\n");
				break;

			case 5:
				// exiting from the program
				System.out.println("Thank you for choosing us... ");
				System.exit(0);
				break;

			default :
				// error message for choosing invalid option
				System.out.println("Invalid input. Try again......\n");
			}
		} while(true);
	}
}
