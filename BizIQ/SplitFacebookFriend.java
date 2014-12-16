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
            	 // System.out.println(line);
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
    	String[] strArray = line.split(",");
    	
    	// System.out.println(strArray[0]); // Seq. No.
    	// System.out.println(strArray[1]); // biz User_id
    	// System.out.println(strArray[2]); // facebook_id 
    	// System.out.println(strArray[3]); // facebook_id list  xxx|yyy|zzz
    	// System.out.println(strArray[4]); // create data 7777-mm-dd hh:mi:ss 

    	try{
    		String strList = strArray[3].replace("|", ",");
    		String strListArray[] = strList.split(",");
    		System.out.println(String.format("%d",strListArray.length));
    		String s = strArray[1].concat(",".concat(strArray[2].concat(",")));
    		for(int idx = 0 ; idx < strListArray.length;idx++){
    			String strPut = s.concat(strListArray[idx]);
    			bw.write(strPut);
    			bw.newLine();
    			System.out.println(strPut);
    		}
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
}