package com.Test.pkg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.pkg.theater.Cinema;

class CinemaTest {

	@Test
	void testSeats() {
		Cinema c1 = new Cinema(0);
		assertEquals(90, c1.Seats(9));
		assertEquals(10, c1.Seats(1));
		assertEquals(50, c1.Seats(7));
	}
	
	@Test
	void testpensionerSeatPrice() {
		Cinema c1 = new Cinema(0);
		assertEquals(200, c1.pensionerSeatPrice(4,50));
		assertEquals(25000, c1.pensionerSeatPrice(10,2500));
		assertEquals(105, c1.pensionerSeatPrice(7,10));
	}

	@Test
	void testrefundFrequent() {
		Cinema c1 = new Cinema(0);
		assertEquals(1280, c1.refundFrequent(4, 320));
		assertEquals(50, c1.refundFrequent(1, 50));
		assertEquals(100, c1.refundFrequent(7, 85.5));
	}
}
