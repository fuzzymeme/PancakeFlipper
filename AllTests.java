package pancakeflipperrevisited;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SequenceBasedFlipperTests.class, SequenceBasedStackTests.class, PancakeSequenceTests.class, SequencedPancakeStackTests.class })
public class AllTests {

}
