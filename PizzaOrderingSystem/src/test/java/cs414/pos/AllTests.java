package cs414.pos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        AddressTest.class,
        CardTest.class,
        CustomerTest.class,
		EmployeeTest.class,
        ItemTest.class,
        LoginInfoTest.class,
		MainTest.class,
        OrderItemTest.class,
        OrderTest.class,
        StoreTest.class,
        SaverLoaderTest.class
})
public class AllTests { }
