package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Ecommerce_Test5 extends BaseTest{

    static By countrySelect = By.id("android:id/text1");
    static By nameText = By.id("com.androidsample.generalstore:id/nameField");
    static By genderSelectXpath = By.xpath("//*[@text='Female']");
    static By country = By.xpath("//*[@text='Belgium']");
    static By letsShopBtn = By.className("android.widget.Button");

    public static void main(String[] args) throws MalformedURLException {

        AndroidDriver driver =  capabilities();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.hideKeyboard();

        driver.findElement(countrySelect).click();
        System.out.println(driver.findElement
                        (MobileBy.AndroidUIAutomator
                                ("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"))
                .getText()+" is Selected");

        driver.findElement(country).click();
        driver.findElement(nameText).sendKeys("TestName");
        driver.findElement(genderSelectXpath).click();
        driver.findElement(letsShopBtn).click();

         driver.findElements(MobileBy.AndroidUIAutomator
                ("new UiScrollable(new UiSelector()" +
                        ".resourceId(\"com.androidsample.generalstore:id/rvProductList\"))" +
                        ".scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0;i<count;i++){
           String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (text.equalsIgnoreCase("Jordan 6 Rings")){
            driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        String addToCartText = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        System.out.println(addToCartText);
        Assert.assertEquals("Jordan 6 Rings", addToCartText);
    }
}




