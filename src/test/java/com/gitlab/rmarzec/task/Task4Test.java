import com.gitlab.rmarzec.framework.setup.BaseSetup;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task4Test extends BaseSetup {

    @Test
    public void Task4Test() {
        // Navigate to YouTube
        webDriver.get("https://www.youtube.com/");

        // Accept cookies if present
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement acceptYTCookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='eom-button-row style-scope ytd-consent-bump-v2-lightbox']//button[contains(., 'Zaakceptuj wszystko')]")));
        acceptYTCookiesButton.click();

        // List to store YouTube video tiles
        List<YTTile> ytTileList = new ArrayList<>();

        // Wait for video tiles to load
        // Wait for video tiles to load
        By videoTileLocator = By.xpath("//div[@id='dismissible']//a[@id='thumbnail']");
        List<WebElement> videoTiles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(videoTileLocator));

        // Get the first 12 video tiles
        List<WebElement> tileElements = videoTiles.subList(0, Math.min(12, videoTiles.size()));

        for (WebElement tileElement : tileElements) {
            // Get title
            WebElement titleElement = tileElement.findElement(By.xpath(".//h3[@class='style-scope ytd-rich-grid-media']/a[@id='video-title-link']/yt-formatted-string[@id='video-title']"));
            String title = titleElement.getText();

            // Get channel name
            WebElement channelElement = tileElement.findElement(By.xpath(".//div[@id='byline-container']/ytd-channel-name[@id='channel-name']/div[@id='container']/div[@id='text-container']/yt-formatted-string[@id='text']"));
            String channel = channelElement.getText();

            // Check if it's a live broadcast and get the length
            String length;
            WebElement lengthElement = tileElement.findElement(By.xpath(".//ytd-thumbnail-overlay-time-status-renderer[@class='style-scope ytd-thumbnail']/div[@id='time-status']/span[@id='text']"));
            length = lengthElement.getText().trim();

            // Add to the list
            YTTile ytTile = new YTTile();
            ytTile.setTitle(title);
            ytTile.setChannel(channel);
            ytTile.setLength(length);
            ytTileList.add(ytTile);
        }

        // Print titles and durations for non-live videos
        for (YTTile ytTile : ytTileList) {
            if (!ytTile.getLength().equalsIgnoreCase("live")) {
                System.out.println("Title: " + ytTile.getTitle());
                System.out.println("Duration: " + ytTile.getLength());
                System.out.println("-----");
            }
        }
    }
}
