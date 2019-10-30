package com.api.demo.RestApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.JsonArray;

public class GenericMethods {

	public static String[] SplitString(String str) {
		String[] StrA = str.split("\\|");
		return StrA;
	}

	public static List<String> VerifyPaths(String respBody) {
		List<String> AL = new ArrayList<String>();
		boolean flag = false;
		JSONObject jsonObject = new JSONObject(respBody);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("poster_path")) {
					if (!(objectInArray.isNull(elementName))) {
						flag = isValidurl(objectInArray.getString(elementName));
						if (!flag) {
							AL.add(objectInArray.getString("title"));
						}
					}
				}
			}
		}
		return AL;

	}

	public static boolean VerifySortlogic(String respBody) {
		int x = 0;
		int y = 0;
		boolean flag = true;
		JSONObject jsonObject = new JSONObject(respBody);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("genre_ids")) {
					if (!(objectInArray.isNull(elementName))) {
						x = 1;
						y = 1;
					} else {
						if (x == 1 && y == 1) {
							flag = false;
							return flag;
						} else {
							y = 1;
						}
					}
				}
			}
		}
		return flag;

	}

	public static boolean VerifyGenereSum(String respBody) {
		int sum = 0;
		int count = 0;
		boolean flag = true;
		JSONObject jsonObject = new JSONObject(respBody);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("genre_ids")) {
					if (!(objectInArray.isNull(elementName))) {
						JSONArray array = objectInArray.optJSONArray(elementName);
						if (array != null) {
							int[] numbers = new int[array.length()];
							for (int j = 0; j < array.length(); j++) {
								numbers[j] = array.optInt(j);
								sum = sum + numbers[j];
							}
						}
						if (sum > 400) {
							count++;
						}
						sum = 0;
					}
				}
			}
			if (count > 7) {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean verifyImages(String respBody) {
		Map<String, Integer> HM = new HashMap<String, Integer>();
		boolean flag = false;
		JSONObject jsonObject = new JSONObject(respBody);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("poster_path")) {
					if (!(objectInArray.isNull(elementName))) {
						String image = posterToImage(objectInArray.getString(elementName));
						if (HM.containsKey(image)) {
							HM.put(image, HM.get(image) + 1);
						} else {
							HM.put(image, 1);
						}
					}
				}
			}
		}
		flag = isDuplicate(HM);
		return flag;
	}

	public static boolean verifyPalindrome(String respBody) {
		boolean flag = false;
		JSONObject jsonObject = new JSONObject(respBody);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("title")) {
					if (!(objectInArray.isNull(elementName))) {
						flag = containsPalindrome(objectInArray.getString(elementName));
						{
							if (flag) {
								return flag;
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifysubString(String respBody) {
		int count = 0;
		boolean flag = false;
		List<String> AList = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject(respBody);
		JSONArray jsonArray = jsonObject.getJSONArray("results");
		for (int i = 0, size = jsonArray.length(); i < size; i++) {
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("title")) {
					if (!(objectInArray.isNull(elementName))) {
						AList.add(objectInArray.getString(elementName));
					}
				}
			}
			for (String elementName : elementNames) {
				if (elementName.equalsIgnoreCase("title")) {
					for (int j = 0; j < AList.size(); j++) {
						if(!(AList.get(j).equalsIgnoreCase(objectInArray.getString(elementName))))
						{
						if ((AList.get(j).contains(objectInArray.getString(elementName)))) {
							count++;
							if (count >= 2) {
								flag = true;
								return flag;
							}
							break;
						}
						}
					}
				}
			}
		}
		return flag;
	}

	public static boolean isDuplicate(Map<String, Integer> hM) {
		boolean flg = true;
		for (String key : hM.keySet()) {
			if (hM.get(key) > 1) {
				flg = false;
			}
		}
		return flg;
	}

	public static String posterToImage(String url) {
		String image = "";
		String[] removeparam = url.split("\\?");
		try {
			String[] temp = removeparam[0].split("/");
			image = temp[temp.length - 1];

		} catch (Exception ex) {

		}

		return image;
	}

	public static boolean isValidurl(String url) {
		boolean flag = false;
		if (url.contains("https://www.dropbox.com")) {
			flag = true;
		}
		return flag;
	}

	public static boolean containsPalindrome(String title) {
		boolean flag = false;
		if (title.contains(" ")) {
			String[] words = title.split(" ");
			for (int i = 0; i < words.length; i++) {
				StringBuffer buffer = new StringBuffer(words[i]);
				String rev = buffer.reverse().toString();
				if (rev.equalsIgnoreCase(words[i])) {
					flag = true;
					return flag;
				}

			}
		}
		return flag;
	}
}
