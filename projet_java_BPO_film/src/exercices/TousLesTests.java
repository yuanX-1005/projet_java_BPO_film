package exercices;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.TestsBasiques;
import tests.TestsComposition;
import tests.TestsExtra;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestsBasiques.class, 
	TestsComposition.class, 
	TestsExtra.class })

public class TousLesTests {
}
