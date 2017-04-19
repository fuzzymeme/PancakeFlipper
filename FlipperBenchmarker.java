package pancakeflipperrevisited;


import java.util.List;
import java.util.OptionalInt;

public class FlipperBenchmarker {
	
	
	private void runAllAgainstPilesOfLength(int length) {
		
		PermutationGenerator permGenerator = new PermutationGenerator();
		List<List<Integer>> permutations = permGenerator.getAllPermutations(length);
				
		int totalFlips = 0;
		for(List<Integer> permutation: permutations) {
			SequenceBasedFlipper flipper = new SequenceBasedFlipper();
			SequencedPancakeStack pStack = new SequencedPancakeStack(permutation);
			
			System.out.println("\n\n-------- New permutation ---------");
			System.out.println(pStack);
			System.out.println("----------------------------------");

			OptionalInt returnOptional = flipper.flipUntilCorrectlyStacked(pStack);
			if(returnOptional.isPresent()) {
				totalFlips += returnOptional.getAsInt();
			}
		}
		
		double averageFlips = (double) totalFlips / (double) permutations.size();
		
		System.out.println("Total flips: " + totalFlips + ", average: " + averageFlips);
	}
	
	public static void main(String[] args) {
		
		FlipperBenchmarker benchmarker = new FlipperBenchmarker();
		benchmarker.runAllAgainstPilesOfLength(4);
	}

}
