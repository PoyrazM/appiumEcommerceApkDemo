package tests;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import io.appium.java_client.touch.LongPressOptions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Ecommerce_Test4 extends BaseTest{

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

        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        double sum = 0;
        for (int i = 0;i<count;i++) {
          String amount = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).
                  get(i).getText();
          double amountLoop = getAmount(amount);
          sum = sum + amountLoop;
        }
        System.out.println("Sum of products : "+sum);

        String totalProd = driver.findElement(totalAmountLbl).getText();
        double totalValue = getAmount(totalProd);

        System.out.println("Total value of products : "+totalValue);
        Assert.assertEquals(sum,totalValue,sum);

        // Validating mobile gestures of app
        WebElement checkBox = driver.findElement(By.className("android.widget.CheckBox"));
        TouchAction action = new TouchAction(driver);
        action.tap(tapOptions().withElement(element(checkBox))).perform();

        WebElement termsOfConditions = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        action.longPress(longPressOptions()
                        .withElement(element(termsOfConditions))
                        .withDuration(ofSeconds(2)))
                        .release().perform();

        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        driver.quit();
    }

    public static double getAmount(String value){
        value = value.substring(1);
        double amountValue = Double.parseDouble(value);
        return amountValue;
    }

}
