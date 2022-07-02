package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;


public class Ecommerce_Test6 extends BaseTest{

    static By countrySelect = By.id("android:id/text1");
    static By nameText = By.id("com.androidsample.generalstore:id/nameField");
    static By genderSelectXpath = By.xpath("//*[@text='Female']");
    static By country = By.xpath("//*[@text='Belgium']");
    static By letsShopBtn = By.className("android.widget.Button");
    static By totalAmountLbl = By.id("com.androidsample.generalstore:id/totalAmountLbl");

    public static void main(String[] args) throws MalformedURLException {

        AndroidDriver driver = capabilities();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.hideKeyboard();

        driver.findElement(countrySelect).click();
        System.out.println(driver.findElement
                        (MobileBy.AndroidUIAutomator
                                ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"))
                .getText() + " is Selected");

        driver.findElement(country).click();
        driver.findElement(nameText).sendKeys("TestName");
        driver.findElement(genderSelectXpath).click();
        driver.findElement(letsShopBtn).click();

        driver.findElement(MobileBy.AndroidUIAutomator
                ("new UiScrollable(new UiSelector()" +
                        ".resourceId(\"com.androidsample.generalstore:id/rvProductList\"))" +
                        ".scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));


        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        // Validating mobile gestures of app
        WebElement checkBox = driver.findElement(By.className("android.widget.CheckBox"));
        TouchAction action = new TouchAction(driver);
        action.tap(tapOptions().withElement(element(checkBox))).perform();

        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        try {
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }

        Set<String> contexts = driver.getContextHandles();
        for (String contextsName : contexts){
            System.out.println(contextsName);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("Test", Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}
