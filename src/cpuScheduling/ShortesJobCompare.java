package cpuScheduling;

import java.util.Comparator;

public class ShortesJobCompare implements Comparator<Process> {

	@Override
	public int compare(Process p1, Process p2) {
		if(p1.getBurstTime()>p2.getBurstTime())
			return 1;
		if(p1.getBurstTime()<p2.getBurstTime())
			return -1;
		return 0;
	}
	
}
