package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
        @Suite.SuiteClasses({
            SuiteTest1.class,
            SuiteTest2.class,
        })

public class JunitTest {
        // This class remains empty, it is used only as a holder for the above annotations
}
