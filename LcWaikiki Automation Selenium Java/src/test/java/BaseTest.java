import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


@Test
public class BaseTest {

    static WebDriver driver;
    static By kategoriSecLocator = By.className("menu-header-item");
    //static By ürünLocator = By.cssSelector("div[class='col-sm-12 uzun visible-lg visible-md'] a");
    static By ürünLocator = By.xpath("//*[@class='col-sm-12 uzun visible-lg visible-md']");
    static By ürünSecmeLocator = By.className("product-image");
    static By bedenSecmeLocator = By.xpath("//*[@id='option-size']/a[1]");
    static By boySecmeLocator = By.xpath("//*[@id='option-height']/a[1]");
    static By sepeteEkleLocator = By.id("pd_add_to_cart");
    static By sepetLocator = By.id("shopping-cart");
    static By anasayfaLocator = By.id("Path_2157");
    static By urunKoduLocator = By.xpath("//*[@class='hidden-xs']");

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.lcwaikiki.com/tr-TR/TR");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
        String expectedTitle = "LC Waikiki | İlk Alışverişte Kargo Bedava! - LC Waikiki";
        Assert.assertEquals(actualTitle,expectedTitle,"Anasayfada değilsiniz!");


        //erkek kategori sayfasına git
        List<WebElement> kategoriler = driver.findElements(kategoriSecLocator);
        kategoriler.get(1).click();
        String actualTitle2 = driver.getTitle();
        String expectedTitle2 = "Erkek Giyim - Erkek Kıyafetleri - LC Waikiki";
        Assert.assertEquals(actualTitle2,expectedTitle2,"Erkek kategorisinde değilsiniz!");


        //Erkek Denim Çok Satanlar sayfasına git
        driver.findElement(ürünLocator).click();
        String actualTitle3 = driver.getTitle();
        String expectedTitle3 = "Erkek Denim Çok Satanlar - Sayfa 1 - LC Waikiki";
        Assert.assertEquals(actualTitle3,expectedTitle3,"Erkek Denim En Çok Satanlar Sayfasında değilsiniz!");



        //3. ürüne git
        List<WebElement> ürünler = driver.findElements(ürünSecmeLocator);
        ürünler.get(2).click();
        Thread.sleep(400);


        //beden,boy seç ve ürünü sepete ekle
        driver.findElement(bedenSecmeLocator).click();
        driver.findElement(boySecmeLocator).click();
        Thread.sleep(1000);
        driver.findElement(sepeteEkleLocator).click();
        String urunKodu = driver.findElement(urunKoduLocator).getText();
        Assert.assertEquals("Ürün Kodu:",urunKodu,"Ürün sayfasında değilsiniz!");

        //sepet sayfasına git
        driver.findElement(sepetLocator).click();
        String actualTitle5 = driver.getTitle();
        String expectedTitle5 = "Sepetim - LC Waikiki";
        Assert.assertEquals(actualTitle5,expectedTitle5,"Sepet sayfasında değilsiniz!");
        Thread.sleep(1000);

        //anasayfaya geri dön
        driver.findElement(anasayfaLocator).click();
        String actualTitle6 = driver.getTitle();
        String expectedTitle6 = "LC Waikiki | İlk Alışverişte Kargo Bedava! - LC Waikiki";
        Assert.assertEquals(actualTitle6,expectedTitle6,"Anasayfada değilsiniz!");

        Thread.sleep(1000);
        driver.quit();

    }

}
