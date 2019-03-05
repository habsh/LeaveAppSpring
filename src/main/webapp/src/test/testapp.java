import junit.framework.TestCase;

public class TestApp extends TestCase
{
        public void testID() {
            public TestApp(String empMethod) {
                super(empMethod);
            }

            public void testEmployee() {
                Employee brad = new Employee(4892,"Brad Charleston", 7524200884, "Abigail@.com", "punk rulez","",58333,29344);
                //how is Date represented in the string?
                assertEquals("4892 Brad Charleston 7524200884 Abigail@.com punk rulez  58333 29344",brad.Employee());
            }
        }

        public void setUp() throws Excecption{
            System.out.println("Setup - TestApp");
        }

        public void tearDown() throws Excecption{
            System.out.println("Teardown - Testapp");
        }

        public static Test suite() {
            TestSetup test =  new TestSetup(new TestSuite(TestApp.class))
            {
                public void setUp() throws Excecption{
                    System.out.println("Setup suite - TestApp");
                }
                public void tearDown() throws Excecption {
                    System.out.println("Teardown suite - TestApp");
                }
            };
            return test;
        }

        public void testNulls() {
            try{
                System.out.println("from TestApp - testNulls");
                Employee g = new Employee(null,null,null,null,null,null,null,null);
                fail("Expected illegal arg exception when all args are null");
            }
            catch(Exception e){
                System.out.println("Exception occurred"+e);
            }
        }
 }