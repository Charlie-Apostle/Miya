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
 * Selenium�v
 */
public class GetFriendList {
    private WebDriver driver;
    
    /**
     * ���݂̊��ɉ�����chromedriver�̃C���X�g�[���p�X���擾����B
     */
    private String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            path = "chromedriver/mac/chromedriver"; // Mac���̏ꍇ
        } else {
            path = "chromedriver/win/chromedriver.exe"; // Windows���̏ꍇ
        }
        File file = new File(path);
        return file.getAbsolutePath();
    }
    
    /**
     * ���������B
     * �u@Before�v���������\�b�h�́A�e�e�X�g���\�b�h�̊J�n�O�ɖ�����s�����B
     */
    @Before
    public void setUp() {
        // chromedriver�̃C���X�g�[���ꏊ���w��
        System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        
        // WebDriver�̃C���X�^���X�𐶐����u���E�U���N��
        driver = new ChromeDriver();
        // �E�B���h�E�T�C�Y���w��
        int width = 1200;
        int height =1000;
        driver.manage().window().setSize(new Dimension(width,height));
    }
    
    /**
     * �I�������B
     * �u@After�v���������\�b�h�́A�e�e�X�g���\�b�h�̏I����ɖ�����s�����B    
     */
    @After
    public void tearDown() {
        // �u���E�U����AWebDriver���I������
        driver.quit();
    }
    
    /**
     * ���C���X�g�����B
     * JUnit�́A�u@Test�v���������\�b�h���e�X�g���\�b�h�Ƃ��Ď��s����B
     */
    @Test
    public void test() {
        
        // URL��ݒ�
        String url = "https://facebook.com/";
        String BaseURL ="https://www.facebook.com/friends.php?id=";
        System.out.println(url);
        
        // �w�肵��URL�̃E�F�u�y�[�W�Ɉړ�
        driver.get(url);
        // Assert.assertEquals(driver.getTitle(),"�Ă���");
        
        // ��������́E�N���b�N�Ȃǂ̏���
        try {
            // facebook �Ƀ��O�C������
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
            
            while ((fbid = fl.ReadFB_Account(i)) != null) {
            	prof_url = BaseURL.concat(fbid);
            	System.out.println(prof_url);
            	driver.get(prof_url);
           	
            	List<WebElement> element;
            	// ������ʂ�default40���\�������B
            	Thread.sleep(5000);
           	  	//System.out.println(driver.findElement(By.id("pagelet_timeline_main_column")).getText());
            	//System.out.println(driver.findElement(By.className("fsl fwb fcb")).getText());
            
           		Boolean scroll_flg = true;
            	while(scroll_flg == true){            		
            		//�y�[�W�_�E�����������ăX�N���[��������
            		// element = driver.findElements(By.className("uiHeaderTitle"));
            		element = driver.findElements(By.cssSelector("div[class='clearfix uiHeaderTop']"));
            		for(int j=0; j < element.size();j++){
            			// System.out.println(element.get(j).getText());
                	    if(Pattern.compile("����ɂ��Ă����ƌ���").matcher(element.get(j).getText()).find()){
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
            	//�F�B�ꗗ���擾�@class���w�肵��contents����擾
            	element = driver.findElements(By.cssSelector("a[class='_5q6s _8o _8t lfloat _ohe']"));
            	//element.get(0).click();
            	for(int k = 0; k < element.size(); k++){
            		System.out.println(String.valueOf(k+1).concat(":").concat(element.get(k).getAttribute("href")));
            		   driver.get(element.get(k).getAttribute("href"));
            		  // Thread.sleep(10000);
            	}
            	///System.out.println(driver.findElement(By.className("System.out.println(driver.findElement(By.className("_5q6s _8o _8t lfloat _ohe")).getText());
/*
      	  		//a�@�^�O�̓��e���擾����B
           		WebElement link;
           		// link = driver.findElement(By.partialLinkText("���r �b�q"));
            	// System.out.println(link.getAttribute("href"));
            	System.out.println("Miya-2");
          		//�@�S�Ẵ����N���擾���Ă݂�
           		List<WebElement> cheese = driver.findElements(By.tagName("a"));
           		for(int j = 0; j < cheese.size(); j++){
        	   		link = driver.findElement(By.partialLinkText(cheese.get(j).getText()));
        	   		// a �^�O�̓��e
        	   		// �����ȊO��������
        	    	if(! Pattern.compile(fbid).matcher(link.getAttribute("href").toLowerCase()).find()){
        			//   System.out.println(link.getText());
        			//   System.out.println(link.getAttribute("href"));
        	   	}
*/
    	   System.out.println("Miya-3");
        } // while
            // driver.switchTo().alert().accept();
            Thread.sleep(5000); // �f���p       
        } catch (InterruptedException e) {
        	throw new RuntimeException(e);
        }
    }


    public void show_profile() {
    	// Menu-> Profile
    	String ur2 = "http://profile.biz-iq.jp?refUrl=naviBar";
    	driver.get(ur2);
    	
    	try{
    		// �v���t�B�[����ҏW����{�^��Link
    		driver.get("https://profile.biz-iq.jp/profile/baseInfo");
    		WebElement stage = driver.findElement(By.id("stageRadios"));
    		// Radio�{�^���i�]�ƈ��A�w���E�E�E�j
    		driver.findElement(By.xpath("//input[@name='stage' and @value='15']")).click();
    		// driver.findElement(By.id("stageRadios"));
    		// driver.findElement(By.
    		
            Thread.sleep(1000);	
    	}catch (InterruptedException e) {
        	throw new RuntimeException(e);
        }
    }
 /**
    public void waitForElementToLoad(final By locator, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(locator).isDisplayed();
            }
        });
    }
***/
}
