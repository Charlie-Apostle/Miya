package Selenium;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.SystemUtils;

class FriendList {
 	private String[] fbid ={
			/*
			"550694306",
			"680171822",
			"655268890",
			"697702329",
			"727888809",
			"100002069773214",
			"100002077317359",
			"100002120433942",
			"100006889091278"
			"259552347571869" // isamu_ishihara
			 "100001650247953" //Miyasaka
			 "100001409430955" // Yasu
			 */
			"100001650247953" //Miyasaka
	   	};
 	
	public String Get_FB_Account(int no){
    	if(no < fbid.length){
    		return fbid[no];
    	}else{
    		return null;
    	}
    }
}
