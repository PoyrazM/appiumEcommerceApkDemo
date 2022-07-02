package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Ecommerce_Test2 extends BaseTest{

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

            WebElement jordan6 = driver.findElement(MobileBy.AndroidUIAutomator
                            ("new UiScrollable(new UiSelector()" +
                            ".resourceId(\"com.androidsample.generalstore:id/rvProductList\"))" +
                            ".scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

            if (jordan6.isDisplayed())
            {
                System.out.println("Jordan 6 Rings Found : "+jordan6.isEnabled());
                driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
            }

            WebElement addToCartBtn = driver.findElement
                    (By.id("com.androidsample.generalstore:id/appbar_btn_cart"));


            if (addToCartBtn.isDisplayed())
            {
                System.out.println("Add To Cart Button : "+addToCartBtn.isEnabled());
                addToCartBtn.click();
            }

            WebElement totalPurchaseMessage = driver.findElement
                    (By.xpath("//*[@text='Total Purchase Amount:  ']"));

            Assert.assertEquals
                    ("Total Purchase Amount:  ",totalPurchaseMessage.getText());


            if (driver != null) {driver.quit();}
        }
    }




