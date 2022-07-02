package tests;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ecommerce_Test3 extends BaseTest{

    static By countrySelect = By.id("android:id/text1");
    static By nameText = By.id("com.androidsample.generalstore:id/nameField");
    static By genderSelectXpath = By.xpath("//*[@text='Female']");
    static By country = By.xpath("//*[@text='Belgium']");
    static By letsShopBtn = By.className("android.widget.Button");

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


        String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"))
                .get(0)
                .getText();

        amount1 =amount1.substring(1);
        double amount1Value = Double.parseDouble(amount1);

        String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"))
                .get(1)
                .getText();

        amount2 = amount2.substring(1);
        double amount2Value = Double.parseDouble(amount2);
        double sumAmounts =amount1Value+amount2Value;
        System.out.println("Sum of products : "+sumAmounts);

        String totalProd = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
                .getText();
        totalProd = totalProd.substring(1);
        double totalValue = Double.parseDouble(totalProd);
        System.out.println("Total value of products : "+totalValue);
        Assert.assertEquals(sumAmounts,totalValue,amount1Value+amount2Value);
    }
}
