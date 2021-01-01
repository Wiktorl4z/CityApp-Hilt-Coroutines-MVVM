package pl.futuredev.capstoneproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;



import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Instrumented test1.test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("pl.futuredev.capstoneproject", appContext.getPackageName());
    }
}
