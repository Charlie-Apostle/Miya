package BizIQ;
import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BI_FindCompany {
    private WebDriver driver;
    
    /**
     * 現在の環境に応じたchromedriverのインストールパスを取得する。
     */
    private String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            path = "chromedriver/mac/chromedriver"; // Mac環境の場合
        } else {
            path = "chromedriver/win/chromedriver.exe"; // Windows環境の場合
        }
        File file = new File(path);
        return file.getAbsolutePath();
    }
    
    /**
     * 初期処理。
     * 「@Before」をつけたメソッドは、各テストメソッドの開始前に毎回実行される。
     */
    @Before
    public void setUp() {
        // chromedriverのインストール場所を指定
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        
        // WebDriverのインスタンスを生成しブラウザを起動
         driver = new ChromeDriver();
        // driver = new FirefoxDriver();
                

    }
    
    /**
     * 終了処理。
     * 「@After」をつけたメソッドは、各テストメソッドの終了後に毎回実行される。    
     */
    @After
    public void tearDown() {
        // ブラウザを閉じ、WebDriverを終了する
        driver.quit();
    }
    
    /**
     * メインとなるテスト処理。
     * JUnitは、「@Test」がついたメソッドをテストメソッドとして実行する。
     */
    @Test
    public void test() {
        
        // BI PlatformのURLを設定
        String url = "http://bi.biz-iq.jp:8080/bizbi/login.jsp";
        System.out.println(url);
        
        // 指定したURLのウェブページに移動
        driver.get(url);
        
        // 文字列入力・クリックなどの処理
        try {
            Thread.sleep(1000); // 各操作の間にSleepを入れる
            WebElement userName = driver.findElement(By.name("j_username"));
            userName.sendKeys("miyasaka");
            Thread.sleep(1000); 
            WebElement password = driver.findElement(By.name("j_password"));
            password.sendKeys("miyasaka");
            Thread.sleep(1000); 
            WebElement login = driver.findElement(By.name("submit"));
            login.click();
            Thread.sleep(1000);
            
            /********/
            if (driver instanceof JavascriptExecutor){
            	// ((JavascriptExecutor)driver).executeScript("alert('Hello world');");
            	//  Thread.sleep(1000);
              	((JavascriptExecutor)driver).executeScript("showReport('company_company_detail.rptdesign');");
                Thread.sleep(100000);
            }
            Thread.sleep(1000);
            
            // List<WebElement> link;
            //WebElement link;
            //link = driver.findElement(By.linkText("Company"));
            //link.click();
/*******/
           // System.out.println(link);
           Thread.sleep(1000);
           
            // profile() ;
            // Display Home
            
            // driver.switchTo().alert().accept();
            // Thread.sleep(1000); // デモ用
        }catch (NoSuchElementException e){
        	System.out.println("Miya");
        	throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void profile() {
    	try{
    		String url = "http://profile.biz-iq.jp?refUrl=naviBar";
    		System.out.println(url);
        	// 指定したURLのウェブページに移動
    		driver.get(url); // show profile 		
    		Thread.sleep(1000);
    		
    		String url2 =  "https://profile.biz-iq.jp/profile/baseInfo";
    		driver.get(url2); // show mpdify profile 		
    		Thread.sleep(1000);
    		
    		// modify profile
    		WebElement str_RFamilyName = driver.findElement(By.id("romanFamilyName"));
    		str_RFamilyName.clear();
    		Thread.sleep(500);
    		str_RFamilyName.sendKeys("Miyasaka");
    		
    		WebElement str_RFirstName = driver.findElement(By.id("romanFirstName"));
    		str_RFirstName.clear();
    		Thread.sleep(500);
    		str_RFirstName.sendKeys("Masashi");
    		
    		WebElement sel_pref = driver.findElement(By.id("region"));
    		Select ClickThis = new Select(sel_pref);
    		ClickThis.selectByVisibleText("山形県");
    		Thread.sleep(10000);
    		ClickThis.selectByVisibleText("長野県");
    		//B. Select sel_pref = new Select(driver.findElement(By.id("region")));
    		//B. sel_pref.selectByVisibleText("山形県");
    		//A. WebElement sel_pref = driver.findElement(By.id("region"));
    		//A.　sel_pref.sendKeys("秋田県");
    		Thread.sleep(1000);
    		// submit
    		WebElement btn_save = driver.findElement(By.id("btnSave"));
            btn_save.click();
            
    		// WebElement userName = driver.findElement(By.id("loginEmail"));
    		// userName.sendKeys("zoy901@charlie-s.jp");   	
    	}catch (InterruptedException e){
    		throw new RuntimeException(e);
    	}
    }
}
