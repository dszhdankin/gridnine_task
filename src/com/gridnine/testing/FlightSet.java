package com.gridnine.testing;

import java.util.*;
import java.util.function.*;

public class FlightSet extends ArrayList<Flight>{
	
	public FlightSet(final Flight... flights) {
		super(Arrays.asList(flights));
	}
	
	public FlightSet(final Collection<Flight> flights) {
		super(flights);
	}
	
	public FlightSet() {
		
	}

	public FlightSet filter(final Predicate<Flight> predicate) {
		FlightSet result = new FlightSet();
		for (Flight flight : this) {
			if (predicate.test(flight)) {
				result.add(flight);
			}
		}
		return result;
	}

}
