package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Ecommerce_Test1 extends BaseTest {


    static By countrySelect = By.id("android:id/text1");
    static By nameText = By.id("com.androidsample.generalstore:id/nameField");
    static By genderSelectXpath = By.xpath("//*[@text='Female']");
    static By country = By.xpath("//*[@text='Belgium']");
    static By letsShopBtn = By.className("android.widget.Button");
    static  By toastMessage = By.xpath("/hierarchy/android.widget.Toast[1]");


    public static void main(String[] args) throws MalformedURLException {
        AndroidDriver driver =  capabilities();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.hideKeyboard();

        driver.findElement(countrySelect).click();
        System.out.println(driver.findElement
                (MobileBy.AndroidUIAutomator
                ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"))
                .getText());

        driver.findElement(country).click();

        //driver.findElement(nameText).sendKeys("TestName");
        driver.findElement(genderSelectXpath).click();

        driver.findElement(letsShopBtn).click();

        System.out.println("The error message is : "
                +driver.findElement(toastMessage).getAttribute("name"));

        String toastMessageActual = driver.findElement(toastMessage).getText();
        Assert.assertEquals("Please enter your name",toastMessageActual);
    }
}
