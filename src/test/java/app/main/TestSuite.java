package app.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import app.models.ClienteTest;
import app.models.CuentaBancariaTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ClienteTest.class, CuentaBancariaTest.class })
public class TestSuite {
}
