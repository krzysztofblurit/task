package com.gitlab.rmarzec.task;
import com.gitlab.rmarzec.framework.setup.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Task3Test extends BaseSetup {
    @Test
    public void Task3Test() {
        webDriver.get("https://www.google.com/");

        WebElement acceptGoogleCookiesButton = webDriver.findElement(By.id("L2AGLb"));
        acceptGoogleCookiesButton.click();

        // Enter "HTML select tag - W3Schools" in the search bar
        WebElement searchBox = webDriver.findElement(By.name("q"));
        searchBox.sendKeys("HTML select tag - W3Schools");

        // Click on the "Szczęśliwy traf" link
        WebElement luckyButton = webDriver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@class='RNmpXc' and @value='Szczęśliwy traf']"));
        luckyButton.click();

        // Verify if the current URL is the expected one
        verifyCurrentUrl("https://www.w3schools.com/tags/tag_select.asp");

        // Accept cookies on the W3Schools page
        WebElement acceptW3SCookiesButton = webDriver.findElement(By.id("accept-choices"));
        acceptW3SCookiesButton.click();

        // Click on "Try it Yourself" button
        WebElement tryItYourselfButton = webDriver.findElement(By.xpath("//a[@target='_blank' and @href='tryit.asp?filename=tryhtml_select' and contains(@class, 'w3-btn') and contains(@class, 'w3-margin-bottom') and text()='Try it Yourself »']"));
        tryItYourselfButton.click();

        // Switch to the new window
        switchToNewWindow();

        // Wait for the example to load
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));

        // Locate the header element and print its text
        WebElement headerElement = webDriver.findElement(By.tagName("h1"));
        String headerText = headerElement.getText();
        System.out.println("Header Text: " + headerText);

        // Select "Opel" from the dropdown list
        WebElement selectElement = webDriver.findElement(By.cssSelector("select"));
        selectElement.sendKeys("Opel");

        // Capture the selected option
        WebElement selectedOption = webDriver.findElement(By.cssSelector("select option:checked"));
        String selectedOptionText = selectedOption.getText();
        System.out.println("Selected Option: " + selectedOptionText);
    }

    private void verifyCurrentUrl(String expectedUrl) {
        String currentUrl = webDriver.getCurrentUrl();
        if (!currentUrl.equals(expectedUrl)) {
            System.out.println("Current URL: " + currentUrl);
            webDriver.get(expectedUrl);
        }
    }

    private void switchToNewWindow() {
        for (String handle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(handle);
        }
    }
}
