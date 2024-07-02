package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;

public class Main {
	
	public static void main(String[] args) {
		FlightSet flights = new FlightSet(FlightBuilder.createFlights());
		
		// Remove those that have already started
		FlightSet firstList = flights.filter(f -> {
			List<Segment> segments = f.getSegments();
			if (segments.size() < 1)
				return true;
			if (segments.get(0).getDepartureDate().compareTo(LocalDateTime.now()) >= 0)
				return true;
			
			return false;
		});
		for (Flight flight : firstList) {
			System.out.println(flight);
		}
		System.out.println();
		
		// Remove those that have incorrect segment
		FlightSet secondSet = flights.filter(f -> {
			for (Segment segment : f.getSegments() ) {
				if (segment.getArrivalDate().compareTo(segment.getDepartureDate()) < 0) {
					return false;
				}
			}
			return true;
		});
		for (Flight flight : secondSet) {
			System.out.println(flight);
		}
		System.out.println();
		
		// Remove those that spend more than 2 hours on Earth
		FlightSet thirdSet = flights.filter(f -> {
			Segment prev = null;
			long timeOnEarth = 0;
			for (Segment segment : f.getSegments() ) {
				if (prev != null) {
					timeOnEarth += ChronoUnit.SECONDS.between(prev.getArrivalDate(), segment.getDepartureDate());
				}
				prev = segment;
			}
			return timeOnEarth <= 2*3600;
		});
		for (Flight flight : thirdSet) {
			System.out.println(flight);
		}
		System.out.println();
	}

}
