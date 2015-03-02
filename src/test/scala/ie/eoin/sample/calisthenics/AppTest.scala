package ie.eoin.sample.calisthenics;

import junit.framework._;
import Assert._;

object AppTest {
    def suite: Test = {
        val suite = new TestSuite(classOf[AppTest]);
        suite
    }

    def main(args : Array[String]) {
        junit.textui.TestRunner.run(suite);
    }
}

class AppTest extends TestCase("app") {

    def testOK() = assertTrue(true);
}
