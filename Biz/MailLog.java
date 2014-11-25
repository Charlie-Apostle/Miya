package BI;
import java.io.*;
import java.util.regex.Pattern;


// import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


class MailLog {
    public static String toDateString(String str) {
    	/*
    	 * Date: Wed, 4 Jun 2014 01:55:36 +0900 (JST)
    	 * Date: Wed,  4 Jun 2014 01:55:36 +0900 (JST)
    	 * Date: 17 Jun 2014 23:02:00 +0900
    	 * 
        */

        String[] strArray = str.split("[\\s]+",0);
        String MM, strDateTime;
        if (! Pattern.compile(",").matcher(str).find()){
        	strArray[5] = strArray[4];
        	strArray[4] = strArray[3];
        	strArray[3] = strArray[2];
        	strArray[2] = strArray[1];
        }
        
        // Jan. Feb. Mar ....
        if(strArray[1] == null){
           MM = "01";
           System.out.println(strArray[3]);
        }else if (strArray[3].equals("Jan")){
           MM = "01";
        }else if (strArray[3].equals("Feb")){
           MM = "02";
        }else if (strArray[3].equals("Mar")){
           MM = "03";
        }else if (strArray[3].equals("Apr")){
           MM = "04";
        }else if (strArray[3].equals("May")){
           MM = "05";
        }else if (strArray[3].equals("Jun")){
           MM = "06";
        }else if (strArray[3].equals("Jul")){
           MM = "07";
        }else if (strArray[3].equals("Aug")){
           MM = "08";
        }else if (strArray[3].equals("Sep")){
           MM = "09";
        }else if (strArray[3].equals("Oct")){
           MM = "10";
        }else if (strArray[3].equals("Nov")){
           MM = "11";
        }else if (strArray[3].equals("Dec")){
           MM = "12";
        }else{
           MM = "01";
        }
        strDateTime = strArray[4];
        strDateTime = strDateTime.concat("-".concat(MM).concat("-").concat(strArray[2]));
        strDateTime = strDateTime.concat(" ".concat(strArray[5]));
         // for (int i = 0; i < strArray.length; i++){
         //    System.out.println(strArray[i]);    
         //}
        return strDateTime;
    }

    public static String[] toErrorString(String str1) {
        // Adjust String
        String str = str1.replaceAll("smtp;5","smtp; 5");
        str = str.replaceAll("-5"," 5");
        String[] strArray = str.split(" ",4);
        String[] strErrArray = new String[10];

       
        strErrArray[0] = strArray[1].replace(',','.');
        strErrArray[1] = strArray[2].replace(',','.');
        if(strArray.length > 3){
            strErrArray[2] = strArray[3].replace(',','.');
        }else{
            strErrArray[2] = " ";
        }
        // System.out.println("Array:" + strErrArray[2]);    
           
        return strErrArray;
    }
    
    public static String toAddrString(String str) {
    	 String[] strArray = new String[20];
    	 str = str.replace(":<",": ");
    	 strArray = str.split(" ",0);
    	
    	try{
    		strArray[1] = strArray[1].replace("<","").replace(">", "");
    	}catch (ArrayIndexOutOfBoundsException e){
    		str = str.replace(":", " ");
    		strArray = str.split(" ",0); 	
    	}
        strArray[1] = strArray[1].replaceAll("\"","");
        if(strArray[1].equals("Biz-IQ")){
       	 System.out.println(strArray[1]);
        	return "noreply@biz-iq.jp";
        }else{
        	return strArray[1].replace(',','.');
        }
    }

    public static String toFromAddrString(String str) {
        String[] strArray = str.split(" ",0);
        // System.out.println(str);
        return strArray[1].replace(',','.');
    }
    
    /*
     * Subject: =?iso-2022-jp?B?VW5kZWxpdmVyYWJsZTogGyRCIVobKEJCaXotSVEbJEIhWzZcGyhC?=
     * Subject: =3D?UTF-8?B?44CQQml6LUlR44CR6Iq55r6kIA=3D=3D?=3D
    */
    public static String toSubjectString(String str) {
        // System.out.println(str);
        
    	String[] strArray = str.split(" ",0);      
    	byte[] strWork;
    	String strSubject = "";
    	String CharSet = "";
    	Boolean flg_decode = false;
    	
    	if(Pattern.compile("iso-2022").matcher(str.toLowerCase()).find()){
    		flg_decode = true;
    		CharSet = "ISO-2022-JP";
    	}else if(Pattern.compile("shift").matcher(str.toLowerCase()).find()){
    		flg_decode = true;
    		CharSet = "Shift-JIS";
    	}
    	if(strArray.length > 1){
    		strArray[1] = strArray[1].replace("?=","");
        	int index = strArray[1].lastIndexOf("?");
        	
        	if (index > 0){
        		strArray[1] = strArray[1].substring(index + 1);
        	
        		// System.out.println(strArray[1]);
        		byte[] str_Out = strArray[1].getBytes();
        		strWork = Base64.decodeBase64(str_Out);
        		
        		if (flg_decode){
        			try {
        				strSubject = new String(strWork,CharSet);
        			}catch (Exception e){
        				System.out.println(e);
        			}
        		}else{
        			strSubject = new String(strWork);
        		}
        	}else{
        		strSubject = str.substring(9);
        	}
    	}else{
    		strSubject = "";
    	}       
        return strSubject.replaceAll(" ", "");
    }
    
    public static void main(String[] args) {
        // String str_date ="^Arrival-Date";
        String str_date = "^Date";
        String str_error = "^Diagnostic-Code";
        String str_to = "^to:";
        String str_from = "^From:";
        String str_subject = "^Subject:";
        
        //ディレクトリ指定
             String path = "C:\\tmp\\tmp\\201406";
             // String path = "C:\\tmp\\new";
        try{
            File dir = new File(path);
            File wfile = new File("c:\\tmp\\out_201406.csv");
            // File wfile = new File("c:\\tmp\\out_new.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(wfile));
       
            //フルパスで取得
            // System.out.println("--ファイル一覧(フルパス)--");
            File[] files1 = dir.listFiles();
            // System.out.println("Files" + files1.length);
            for (int i = 0; i < files1.length; i++) {
                Boolean find_error = false;
                Boolean normal_mail = false;
                String[] str_ErrArray = {"","","",""};
                String str_MySQL_date = "";
                String str_Mail_Addr_To = "";
                String str_ErrMessage = "";
                String str_Subject = "";
               File file = files1[i];
               if (files1[i].isFile()){
            	   System.out.println(file);
                   /* debug
                  if (i > 140 && i < 145){
                      System.out.println(i);
                      System.out.println(file);
                  }
                  */
            	  // save file name
            	  String str_FileName = file.getName();
                  FileReader in = new FileReader(file);
                  BufferedReader br = new BufferedReader(in);
                  String line;
                  while ((line = br.readLine()) != null) {
                	  line = line.replaceAll("\t", " ");
                       // System.out.println(line);
               		   // Arrival-Date
                	  if (Pattern.compile(str_date).matcher(line).find()){
               			   str_MySQL_date = toDateString(line);
               			   // System.out.println(line);
               			   // System.out.println(str_MySQL_date);
               		   }
               		   // Diagnostic-Code
                	  if (Pattern.compile(str_error).matcher(line).find()){
              			   normal_mail = true;
               			   find_error = true;
               			   str_ErrMessage = line.replace(',','.');
               			   str_ErrArray = toErrorString(line);
               			   //System.out.println(str_ErrArray[0]);
               			   // System.out.println(str_ErrArray[1]);
               			   // System.out.println(str_ErrArray[2]);
               		   }
               		   if(find_error){
               			   // To: Mail address
               			  if (Pattern.compile(str_to).matcher(line.toLowerCase()).find()){
               				   str_Mail_Addr_To = toAddrString(line);
               				   // System.out.println(str_Mail_Addr);
               			   }
               			   // Subject
               			   if (Pattern.compile(str_subject).matcher(line).find()){
               				   str_Subject = toSubjectString(line);
               				   while ((line = br.readLine()) != null) {
               					if (Pattern.compile("^=").matcher(line.replace(" ", "")).find()){
               						str_Subject = str_Subject.concat(toSubjectString(line));
               					}else{
               						// System.out.println("Break");
               						break ;
               					}
               					   
               				   }
               			   }
                	   }
                  } //while
                  br.close();
                  
       		   	  if (normal_mail == false){
       		   	      FileReader in2 = new FileReader(file);
                      BufferedReader br2 = new BufferedReader(in2);
                      while ((line = br2.readLine()) != null) {
                    	  line = line.replaceAll("\t", " ");
                    	// System.out.println(line);
                    	  if (Pattern.compile(str_date).matcher(line).find()){
                    		  // System.out.println(line);
                    		  str_MySQL_date = toDateString(line);
                    	  }
             			   // Subject
                    	  if (Pattern.compile(str_subject).matcher(line).find()){
              				   str_Subject = toSubjectString(line);
              				   while ((line = br2.readLine()) != null) {
              					   line = line.replaceAll("\t", "");
              					   // System.out.println(line);
              					   if (Pattern.compile("^=").matcher(line.replace(" ", "")).find()){
              						   str_Subject = str_Subject.concat(toSubjectString(line));
              					   }else{
              						   break ;
              					   }
              				   }
              			   }
                    	  
                    	  if (Pattern.compile(str_to).matcher(line.toLowerCase()).find()){
                    		   if( line.indexOf("@") == -1){
                    			   line = br2.readLine();
                    			   line = line.replaceAll("\t", " ");
                    			   if( line.indexOf("@") != -1){
                    				   str_Mail_Addr_To = line;
                      				   str_Mail_Addr_To = toAddrString(line);
                      				   str_Mail_Addr_To = str_Mail_Addr_To.replace("<", "").replace(">","");
                    			   }else{
                    				   str_Mail_Addr_To = line;
                    			   }
                    		   }else{
                    			   // From address ->　To Address
                    			   str_Mail_Addr_To = toAddrString(line);
                    			   str_Mail_Addr_To = str_Mail_Addr_To.replace("<", "").replace(">","");
                    			   //System.out.println(str_Mail_Addr_To);
                    			   //System.out.println(line);
                    		   }
              			   }
                      }
                      br2.close();
       		   	  }
                  
                  /* csv出力用のデータ作成
                                                  日付+メアド＋エラー種別＋エラーコード＋残り文字＋元のエラー内容
                  */
       		   	 // メアドのドメインだけ抜き出す。
              	 int index = str_Mail_Addr_To.lastIndexOf("@");
              	 String str_Domain;
            	 if (index > 0){
            		str_Domain  = str_Mail_Addr_To.substring(index + 1);
            	 }else{
            		 str_Domain = "";
            	 }
                  String str_csv = str_MySQL_date.concat(","); 
                	 str_csv = str_csv.concat(str_Mail_Addr_To.concat(","));
                     str_csv = str_csv.concat(str_Domain.concat(","));
                     str_csv = str_csv.concat(str_ErrArray[0].concat(","));
                     str_csv = str_csv.concat(str_ErrArray[1].concat(","));
                     str_csv = str_csv.concat(str_ErrArray[2].concat(","));
                     str_csv = str_csv.concat(str_FileName.replaceAll(",", ".").concat(","));
                     str_csv = str_csv.concat(str_ErrMessage.concat(","));
                     str_csv = str_csv.concat(str_Subject.replace(","," "));
                  System.out.println(str_csv);
                  bw.write(str_csv);
                  bw.newLine();
                  
               }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
