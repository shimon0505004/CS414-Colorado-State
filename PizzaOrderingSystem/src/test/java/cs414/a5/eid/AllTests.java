package cs414.a5.eid;

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
        SaverLoaderTest.class,
        KioskTest.class,
        RegisterTest.class
})
public class AllTests { }
