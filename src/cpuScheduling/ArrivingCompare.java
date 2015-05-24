package cpuScheduling;

import java.util.Comparator;

public class ArrivingCompare implements Comparator<Process>{

	@Override
	public int compare(Process p1, Process p2) {
		if(p1.getArrivalTime()>p2.getArrivalTime()) {
			return 1;
		}
		if(p1.getArrivalTime()<p2.getArrivalTime()) {
			return -1;
		}
		return 0;
	}
	
}
