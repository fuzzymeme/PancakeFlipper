package pancakeflipperrevisited;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pancakeflipperrevisited.utils.PairTests;

@RunWith(Suite.class)
@SuiteClasses({ SequenceBasedFlipperTests.class, SequenceBasedStackTests.class, PancakeSequenceTests.class, SequencedPancakeStackTests.class, PairTests.class })
public class AllTests {

}
