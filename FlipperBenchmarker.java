package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import pancakeflipperrevisited.utils.CounterMap;
import pancakeflipperrevisited.utils.CounterMapUtils;

public class FlipperBenchmarker {
	
	
	private void runAllAgainstPilesOfLength(int length) {
		
		PermutationGenerator permGenerator = new PermutationGenerator();
		List<List<Integer>> permutations = permGenerator.getAllPermutations(length);
		CounterMap<Integer> individualFlipCounts = new CounterMap<Integer>();
		List<SequencedPancakeStack> difficultSequences = new ArrayList<>();
				
		int totalFlips = 0, failures = 0, successes = 0, longest = 0;
		long before = System.currentTimeMillis();
		for(List<Integer> permutation: permutations) {
			SequenceBasedFlipper flipper = new SequenceBasedFlipper();
			SequencedPancakeStack pStack = new SequencedPancakeStack(permutation);
			
			System.out.println("\n\n-------- New permutation ---------");
			System.out.println(pStack);
			System.out.println("----------------------------------");

			OptionalInt returnOptional = flipper.flipUntilCorrectlyStacked(pStack);
			if(returnOptional.isPresent()) {
				int flipCount = returnOptional.getAsInt();
				individualFlipCounts.inc(flipCount);
				totalFlips += flipCount;
				
				if(flipCount > longest) {
					longest = flipCount;
					difficultSequences.clear();
				}
				
				if(flipCount == longest) {
					difficultSequences.add(new SequencedPancakeStack(permutation));
				}
				successes++;
			} else {
				failures++;
			}
		}
		
		double averageFlips = (double) totalFlips / (double) permutations.size();
		
		System.out.println("Total flips: " + totalFlips + ", average: " + averageFlips + ", failures: " + failures + ", successes: " + successes);
		
		long after = System.currentTimeMillis();
		long elapsed = after - before;
		System.out.println("Elapsed (clock) time: " + ((double) elapsed / 1000.0));
		System.out.println("Longest: " + longest);
		System.out.println("CounterMap:\n" + CounterMapUtils.toPrettyPrintString(individualFlipCounts));
		
		System.out.println("difficultSequences: " + difficultSequences);
					
	}
	
	public static void main(String[] args) {
		
		FlipperBenchmarker benchmarker = new FlipperBenchmarker();
		benchmarker.runAllAgainstPilesOfLength(7);
	}

}
