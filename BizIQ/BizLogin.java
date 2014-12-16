package BizIQ;
import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 入門課題その1:「動かしてみよう、Selenium」
 */
public class BizLogin {
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
        
        // 入門課題1のURLを取得
        // File html = new File("introwork/introWork1.html");
        // String url = "file:///" + html.getAbsolutePath();
        String url ;
        url = "https://biz-iq.jp";
        System.out.println(url);
        
        // 指定したURLのウェブページに移動
        driver.get(url);
        
        // 文字列入力・クリックなどの処理
        try {
            Thread.sleep(1000); // 各操作の間にSleepを入れる
            WebElement userName = driver.findElement(By.id("loginEmail"));
            userName.sendKeys("zoy901@charlie-s.jp");
            Thread.sleep(1000); 
            WebElement password = driver.findElement(By.id("loginPassword"));
            password.sendKeys("12345678q");
            Thread.sleep(1000); 
            WebElement login = driver.findElement(By.id("btnsubmit"));
            login.click();
            Thread.sleep(1000); 
            profile() ;
            // Display Home
            home();
            
            // driver.switchTo().alert().accept();
            // Thread.sleep(1000); // デモ用       
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void home(){
    	String url = "http://biz-iq.jp/home?refUrl=naviBar";
		System.out.println(url);
    	// 指定したURLのウェブページに移動
		try{
		driver.get(url); // show profile
		Thread.sleep(10000);
		}catch (InterruptedException e){
    		throw new RuntimeException(e);
		}
    }
    private void profile() {
    	try{
    		String url = "http://profile.biz-iq.jp?refUrl=naviBar";
    		System.out.println(url);
        	// 指定したURLのウェブページに移動
    		driver.get(url); // show profile 		
    		// Thread.sleep(1000);
    		
    		String url2 =  "https://profile.biz-iq.jp/profile/baseInfo";
    		driver.get(url2); // show mpdify profile 		
    		// Thread.sleep(1000);
    		
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
    		//Thread.sleep(1000);
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
