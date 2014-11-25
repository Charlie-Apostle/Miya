Skip to content
 This repository
Explore
Gist
Blog
Help
Charlie Software Charlie-Apostle
 
0  Watch 
  Star 0
 Fork 0Charlie-Apostle/Miya
 tree: 9e641a07bb  Miya / Biz / SplitFacebookFriend.java
Charlie-Apostle 31 minutes ago Java for Biz Project
0 contributors
58 lines (53 sloc)  1.701 kb RawBlameHistory  
package BizIQ;
import java.io.*;
import java.util.*;
/*
 * FBLIST Format
 * User-ID,FB_FriendID|FB_FriendID|FB_FriendID|FB_FriendID|FB_FriendID|....
 */
class SplitFacebookFriend {
    public static void main(String[] args) {
        try {
            FileReader in = new FileReader("c:\\tmp\\tmp\\6.csv");
            BufferedReader br = new BufferedReader(in);
            File file = new File("c:\\tmp\\tmp\\6_out.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            
            String line;

            while ((line = br.readLine()) != null) {           	
            	 System.out.println(line);
            	GetToken1(line,bw);
            }
            br.close();
            in.close();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void GetToken1(String line,BufferedWriter bw){
    	try{
    		StringTokenizer st = new StringTokenizer(line,",");   		
    		String strToken;
    		
    		String strid = st.nextToken();
    		System.out.println(strid);	
    		String strUid = st.nextToken();
    		System.out.println(strUid);	
    		String strfb_id = st.nextToken();
    		System.out.println(strfb_id);
    		
    		StringTokenizer stfb = new StringTokenizer(st.nextToken(),"|");
    		while (stfb.hasMoreTokens()){
    			strToken = stfb.nextToken();
    			String s = strUid.concat(",".concat(strfb_id).concat(","));
    			s = s.concat(strToken);
    			System.out.println(s);
    			bw.write(s);
    			bw.newLine();
    		}
    	}catch(Exception e){
    		System.out.println(e);
    	}
    	// catch (IOException e){
    	//	System.out.println(e);
    	//}
    }

}
Status API Training Shop Blog About
c 2014 GitHub, Inc. Terms Privacy Security Contact
