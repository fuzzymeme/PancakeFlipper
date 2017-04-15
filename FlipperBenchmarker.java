package pancakeflipperrevisited;

import java.util.List;

public class FlipperBenchmarker {
	
	
	private void runAllAgainstPilesOfLength(int length) {
		
		PermutationGenerator permGenerator = new PermutationGenerator();
		List<List<Integer>> permutations = permGenerator.getAllPermutations(length);
				
		for(List<Integer> permutation: permutations) {
			SequenceBasedFlipper flipper = new SequenceBasedFlipper();
			SequencedPancakeStack pStack = new SequencedPancakeStack(permutation);
			
			System.out.println("\n\n-------- New permutation ---------");
			System.out.println(pStack);
			System.out.println("----------------------------------");
			
			flipper.flipUntilCorrectlyStacked(pStack);
		}
	}
	
	public static void main(String[] args) {
		
		FlipperBenchmarker benchmarker = new FlipperBenchmarker();
		benchmarker.runAllAgainstPilesOfLength(4);
	}

}
