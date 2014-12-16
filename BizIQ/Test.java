package BizIQ;
import org.apache.commons.codec.binary.Base64;

public class Test{
		public  static boolean checkCharacterCode(String str, String encoding) {
			if (str == null) {
				return true;
			}

			try {
				byte[] bytes = str.getBytes(encoding);
				return str.equals(new String(bytes, encoding));
			} catch (Exception ex) {
				throw new RuntimeException("エンコード名称が正しくありません。", ex);
			}
		}

		public static boolean isWindows31j(String str) {
			return checkCharacterCode(str, "Windows-31j");
		}

		public static boolean isSJIS(String str) {
			return checkCharacterCode(str, "SJIS");
		}

		public static boolean isEUC(String str) {
			return checkCharacterCode(str, "euc-jp");
		}

		public static boolean isUTF8(String str) {
			return checkCharacterCode(str, "UTF-8");
		}
	 public static String DecodeBase64(String strB64){
	    	String Asout;
	    	
	    	//decode
	    	strB64 = strB64.replace("?=", "");
	    	
	    	int index = strB64.lastIndexOf("?");
	    	strB64 = strB64.substring(index + 1);
	    	 System.out.println(strB64);
	    	
	    	byte[] outTesterByte = strB64.getBytes(); 
	    	byte[] b = Base64.decodeBase64(outTesterByte);
	    	String strDecode = new String(b);
	    	System.out.println(strDecode);
	    	System.out.println(isWindows31j(strDecode));
	    	System.out.println(isSJIS(strDecode));
	    	System.out.println(isEUC(strDecode));
	    	System.out.println(isUTF8(strDecode));
	    	return strDecode;
	  }
	 
	public static void main(String[] args){
		String Base1 = "=?UTF-8?B?44CQ44Oq44Kv44OK44OTTkVYVOOBi+OCiQ==?=";
		String Base2 = " =?UTF-8?B?44GU55m76Yyy44Gu5pa544G444CR44OT44K444ON44K5?=";
		String Base3 ="=?Shift_Jis?B?g2yDWFNOU4F1Qml6LUlRgXaCzJNvmF6C8Iqul7mCs4K5gtyCtYLlgqQ=?=";
		String strShift;
		
		
		System.out.println(DecodeBase64(Base1));
		System.out.println(DecodeBase64(Base2));
		strShift = DecodeBase64(Base3);
	 	
		   try{
			String strOut = new String(strShift.getBytes("UTF-8"),"Shift-JIS");
			System.out.println(strOut);
		}catch (Exception e){
			System.out.println(e);
		}
	}
}