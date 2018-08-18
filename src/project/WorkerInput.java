package project;
public class WorkerInput implements Comparable<WorkerInput> {

	private int start;
	private int end;

	public WorkerInput(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(WorkerInput worker) {

		if (this.start < worker.start) {
			return -1;
		}

		if (this.start > worker.start) {
			return 1;
		}

		return 0;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}
