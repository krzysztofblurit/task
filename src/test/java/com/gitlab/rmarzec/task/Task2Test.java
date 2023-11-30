package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.setup.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class Task2Test extends BaseSetup {
    @Test
    public void Task2Test() {
        webDriver.get("https://pl.wikipedia.org/wiki/Wiki");

        // Explicitly wait for the Language Button to be clickable
        WebDriverWait wait = new WebDriverWait(webDriver, 10); // 10 seconds timeout
        By xpathSelector = By.xpath("//*[@id='p-lang-btn']");
        WebElement languageButton = wait.until(ExpectedConditions.elementToBeClickable(xpathSelector));
        languageButton.click();

        // Find all language sections
        List<WebElement> languageSections = webDriver.findElements(By.cssSelector("div.uls-lcd-region-section"));

        // Iterate through each language section
        for (WebElement languageSection : languageSections) {
            // Extract the region from the data-region attribute
            String region = languageSection.getAttribute("data-region");

            // Find all language items within the section
            List<WebElement> languageItems = languageSection.findElements(By.cssSelector("li.interlanguage-link"));

            // Iterate through the language items
            for (WebElement languageItem : languageItems) {
                // Get the language name
                String languageName = languageItem.getText();

                // Get the language URL for English
                String languageUrl = "";
                if (languageName.equals("English")) {
                    languageUrl = languageItem.findElement(By.tagName("a")).getAttribute("href");
                }

                // Print language name, region, and URL for English
                System.out.println("Region: " + region);
                System.out.println("Language: " + languageName);
                if (!languageUrl.isEmpty()) {
                    System.out.println("URL: " + languageUrl);
                }
            }
        }
    }
}
