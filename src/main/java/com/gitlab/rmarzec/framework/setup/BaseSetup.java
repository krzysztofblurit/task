package com.gitlab.rmarzec.framework.setup;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseSetup {
    protected WebDriver webDriver;

    @BeforeMethod
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        webDriver = driverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
