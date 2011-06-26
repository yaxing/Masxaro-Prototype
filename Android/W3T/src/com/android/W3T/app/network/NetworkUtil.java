/*
 * NetworkUtil.java -- Network control class 
 *
 *  Copyright 2011 World Three Technologies, Inc. 
 *  All Rights Reserved.
 * 
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Written by Yichao Yu <yichao@Masxaro>
 * 
 *  The class is responsible for all network stuff: login/out connection,
 *  receipts retrieval and delivery.
 */

package com.android.W3T.app.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.*;

import android.content.Entity;

import com.android.W3T.app.ReceiptsView;
import com.android.W3T.app.rmanager.Receipt;
import com.android.W3T.app.user.UserProfile;

public class NetworkUtil {
	public static final String BASE_URL = "http://sweethomeforus.com/php";
	public static final String LOGIN_URL = BASE_URL + "/login.php";
	public static final String RECEIPT_OP_URL = BASE_URL + "/receiptOperation.php";
	
	private static final String METHOD_RECEIVE_ALL = ReceiptsView.RECEIVE_ALL; 
	
	private static HttpClient mClient = new DefaultHttpClient();
	
	public static boolean attemptLogin(String uname, String pwd) {   
		// Here we may want to check the network status.
		checkNetwork();

        HttpPost request;
        
        try {
        	request = new HttpPost(new URI(LOGIN_URL));
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("acc", uname));
            nameValuePairs.add(new BasicNameValuePair("pwd", pwd));
            nameValuePairs.add(new BasicNameValuePair("type", "user"));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = mClient.execute(request);
            
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            	String s = EntityUtils.toString(response.getEntity());
            	if (s.equals("1")) {
            		return true;
            	}
            	else {
            		return false;
            	}
            }
            return false;
        } catch (URISyntaxException e) {   
            e.printStackTrace();   
         
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }

	// return null means something wrong.
	public static String attemptGetReceipt(String method, String uname) {
		// Here we may want to check the network status.
		checkNetwork();
		if (method.equals(METHOD_RECEIVE_ALL)) {
			
			HttpPost request;
	        
	        try {
	        	request = new HttpPost(new URI(RECEIPT_OP_URL));
	            // Add your data
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
	            nameValuePairs.add(new BasicNameValuePair("opcode", "user_get_all_receipt"));
	            nameValuePairs.add(new BasicNameValuePair("acc", uname));
	            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            System.out.println(request.getEntity().getContent());
	            // Execute HTTP Post Request
	            HttpResponse response = mClient.execute(request);
	            
	            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {	            	
	            	return EntityUtils.toString(response.getEntity());
	            }
	            return null;
	        }
	        catch (Exception e) {
	        	
	        }	
		}		
		return null;
	}
	
	public static boolean attemptSendReceipt(String uname, Receipt r) {
		// Here we may want to check the network status.
		checkNetwork();

        HttpPost request;
        
        try {
        	request = new HttpPost(new URI(RECEIPT_OP_URL));
            // Add your data
//        	o = new JSONObject();
//        	basicInfo.put("store_account", "Starbucks");
//        	basicInfo.put("tax", 0.1);
//        	basicInfo.put("receipt_time", "now()");
//        	basicInfo.put("user_account","new");
//        	JSONObject receipt = new JSONObject();
//        	receipt.put("opcode", "new_receipt");
//        	receipt.put("receipt", null);
//        	StringEntity se = new StringEntity(receipt.toString());
//        	se.getContent();
//        	request.setEntity(se);
//        	BasicNameValuePair basicInfo[] = new BasicNameValuePair(uname, uname);
//            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//            nameValuePairs.add(new BasicNameValuePair("acc", uname));
//            nameValuePairs.add(new BasicNameValuePair("opcode", "user_get_all_receipt"));
//            HashMap<String, String> basicInfo = new HashMap<String, String>();
//            nameValuePairs.add(new BasicNameValuePair("store_account", "Starbucks"));
//            nameValuePairs.add(new BasicNameValuePair("user_account", "new"));
//            nameValuePairs.add(new BasicNameValuePair("receipt_id", "110"));
//            nameValuePairs.add(new BasicNameValuePair("tax", "0.1"));
//        	UrlEncodedFormEntity basicInfo = new UrlEncodedFormEntity(nameValuePairs);
        	
//        	List<NameValuePair> receipt = new ArrayList<NameValuePair>();
//        	receipt.add(new BasicNameValuePair("opcode", "new_receiptg"));
//        	receipt.add(new BasicNameValuePair("receipt", null));
//        	request.setEntity(new UrlEncodedFormEntity(receipt));

        	
        	
//        	request.setEntity(entity);
            // Execute HTTP Post Request
            HttpResponse response = mClient.execute(request);
            
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            	String s = EntityUtils.toString(response.getEntity());
            	System.out.println("return "+s);
//            	if (s.equals("1")) {
//            		return true;
//            	}
//            	else {
//            		return false;
//            	}
            }
            return false;
        } catch (URISyntaxException e) {   
            e.printStackTrace();   
         
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} 
		return false;
	}
	
	private static boolean checkNetwork() {
		return true;
	}
}
