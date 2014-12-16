package Selenium;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonTest{
	public static void main(String[] args){
		try{
			JSONArray jsonArray = new JSONArray();
		
			JSONObject jsonOneData;
			// make data
			jsonOneData = new JSONObject();
			jsonOneData.put("age", "25");
			jsonOneData.put("FirstName","Masashi");
			jsonOneData.put("LastName","Miyasaka");
			// jsonOneData.put("Name", jsonOneData);
			jsonArray.put(jsonOneData);
		
			System.out.println(jsonArray.toString());
			
			// JSONObject jsonOneData2;
			// make data
			jsonOneData = new JSONObject();
			jsonOneData.put("LastName","宮坂");
			jsonOneData.put("FirstName","首輪");
			jsonOneData.put("age", "20");
			
			jsonArray.put(jsonOneData);
			System.out.println(jsonArray.get(0).toString());
			System.out.println(jsonArray.get(1).toString());
			
		}catch (JSONException e){
			e.printStackTrace();
		}
	}
}
