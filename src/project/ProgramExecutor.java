package project;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ProgramExecutor {

	public static void main(String args[]) throws IOException {

		// read input
		for (int filenumber = 1; filenumber < 11; filenumber++) {
			File file = new File(filenumber+".in");
			FileReader fileReader = new FileReader("D:\\2018-19\\Assignment_2\\2.2\\input\\"+file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			//System.out.println("file "+filenumber+" read");
			Integer count = Integer.valueOf(line.trim());
	
			List<WorkerInput> workerInputs = new LinkedList<WorkerInput>();
			while (count > 0) {
				line = bufferedReader.readLine();
				String[] tokens = line.trim().split(" ");
				int start = Integer.valueOf(tokens[0]);
				int end = Integer.valueOf(tokens[1]);
				workerInputs.add(new WorkerInput(start, end));
				count--;
			}
			bufferedReader.close();

			// calculate output
			List<Integer> effectiveLosses = new LinkedList<Integer>();
			Collections.sort(workerInputs);

			int numberOfWrokers = workerInputs.size();
			int totalCoveredTime = 0;

			for (count = 0; count < numberOfWrokers; count++) {

				WorkerInput currentWorker = workerInputs.get(count);
				WorkerInput previousWorker = (count == 0) ? null : workerInputs.get(count - 1);
				WorkerInput nextWorker = (count == numberOfWrokers - 1) ? null : workerInputs.get(count + 1);

				totalCoveredTime = totalCoveredTime + getIndividualCoverage(currentWorker, previousWorker, nextWorker);
				effectiveLosses.add(getEffectiveLoss(currentWorker, previousWorker, nextWorker));
			}

			Collections.sort(effectiveLosses);
			int outPut = totalCoveredTime - effectiveLosses.get(0);
			// write output
			
			BufferedWriter bw = null;
			FileWriter fw = null;
			//System.out.println("file "+filenumber+" created");
			fw = new FileWriter("D:\\2018-19\\Assignment_2\\2.2\\output\\"+filenumber+".out");
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(outPut));
			bw.close();
			fw.close();
		}
		//System.out.println("End of program");
	}
	private static int getIndividualCoverage(WorkerInput currentWorker, WorkerInput previousWorker,
			WorkerInput nextWorker) {

		int startPoint = currentWorker.getStart();
		int endPoint = currentWorker.getEnd();

		if (previousWorker != null) {
			if (startPoint < previousWorker.getEnd()) {
				startPoint = previousWorker.getEnd();
			}
		}

		return endPoint - startPoint;
	}

	private static int getEffectiveLoss(WorkerInput currentWorker, WorkerInput previousWorker, WorkerInput nextWorker) {

		int startPoint = currentWorker.getStart();
		int endPoint = currentWorker.getEnd();

		if (previousWorker != null) {
			if (startPoint < previousWorker.getEnd()) {
				startPoint = previousWorker.getEnd();
			}
		}

		if (nextWorker != null) {
			if (endPoint > nextWorker.getStart()) {
				endPoint = nextWorker.getStart();
			}
		}

		return endPoint - startPoint;
	}
}