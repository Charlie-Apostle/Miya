package Selenium;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Selenium」
 */
public class GetFriendList {
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
    public void SsetUp() {
        // chromedriverのインストール場所を指定
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        
        // WebDriverのインスタンスを生成しブラウザを起動
        driver = new ChromeDriver();
        // ウィンドウサイズを指定
        int width = 1200;
        int height =1000;
        driver.manage().window().setSize(new Dimension(width,height));
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
     * メインスト処理。
     * JUnitは、「@Test」がついたメソッドをテストメソッドとして実行する。
     */
    @Test
    public void test() {
        // URLを設定
        String url = "https://facebook.com/";
        String BaseURL ="https://www.facebook.com/friends.php?id=";
        System.out.println(url);
        
        // 指定したURLのウェブページに移動
        driver.get(url);
        // Assert.assertEquals(driver.getTitle(),"てすと");
        
        // 文字列入力・クリックなどの処理
        try {
            // facebook にログインする.
            WebElement userName = driver.findElement(By.id("email"));
            userName.sendKeys("isamu_0330@hotmail.co.jp");
            // test
             //System.out.println(userName.getTagName());
            // test
            WebElement password = driver.findElement(By.id("pass"));
            password.sendKeys("12345678q");
            WebElement login = driver.findElement(By.id("loginbutton"));
            login.click();
            Thread.sleep(1000);
            
            String prof_url;
            String fbid;
            int i = 0;
            
           	Actions act = new Actions(driver);
            FriendList fl = new FriendList();
            
            while ((fbid = fl.Get_FB_Account(i)) != null) {
            	prof_url = BaseURL.concat(fbid);
            	System.out.println(prof_url);
            	driver.get(prof_url);
           	
            	List<WebElement> element;
            	// 初期画面でdefault40件表示される。
            	Thread.sleep(5000);
           	  	//System.out.println(driver.findElement(By.id("pagelet_timeline_main_column")).getText());
            	//System.out.println(driver.findElement(By.className("fsl fwb fcb")).getText());
            
           		Boolean scroll_flg = true;
            	while(scroll_flg == true){            		
            		//ページダウンを押下してスクロールさせる
            		// element = driver.findElements(By.className("uiHeaderTitle"));
            		element = driver.findElements(By.cssSelector("div[class='clearfix uiHeaderTop']"));
            		for(int j=0; j < element.size();j++){
            			// System.out.println(element.get(j).getText());
                	    if(Pattern.compile("さんについてもっと見る").matcher(element.get(j).getText()).find()){
                	    	scroll_flg = false;
                	    	System.out.println("Found!! Bottom line of friends list");
                	    	break;
                	    }
            		}
            		act.sendKeys(Keys.PAGE_DOWN).perform();
            		Thread.sleep(2000);
            	}
            	i++;
            	Thread.sleep(1000);
            
            	System.out.println("Miya-1");
       
            	// System.out.println(driver.findElement(By.id("pagelet_timeline_medley_friends")).getText());
            	//友達一覧を取得　classを指定してcontentsから取得
            	element = driver.findElements(By.cssSelector("a[class='_5q6s _8o _8t lfloat _ohe']"));
            	//element.get(0).click();
            	for(int k = 0; k < element.size(); k++){
            		System.out.println(String.valueOf(k+1).concat(":").concat(element.get(k).getAttribute("href")));
            		   // driver.get(element.get(k).getAttribute("href"));
            		  // Thread.sleep(10000);
            	}
            	///System.out.println(driver.findElement(By.className("System.out.println(driver.findElement(By.className("_5q6s _8o _8t lfloat _ohe")).getText());
         	   	System.out.println("Miya-3");
            } // while
     	   	System.out.println("Miya-3-1");
            // driver.switchTo().alert().accept();
            Thread.sleep(5000); // デモ用       
        } catch (InterruptedException e) {
        	throw new RuntimeException(e);
        } // try()
    } //test()

} // class
    