/*
 * BasicInfo.java -- BasicInfo Structure 
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
 *  This class is what the receipt looks like and some setter-getter operations on its
 *  several fields.
 */

package com.android.W3T.app.rmanager;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BasicInfo {
	public static final String TAG = "BasicInfo";
	
	private static final boolean FROM_DB = ReceiptsManager.FROM_DB;
	
	public static final String PARAM_RECEIPT_ID = "id";
	public static final String PARAM_RECEIPT_TIME = "receipt_time";
	public static final String PARAM_RECEIPT_TAX = "tax";
	public static final String PARAM_RECEIPT_TOTAL = "total_cost";
	public static final String PARAM_RECEIPT_STORE_NAME = "store_name";
	public static final String PARAM_RECEIPT_STORE_ACC = "store_account";
	public static final String PARAM_RECEIPT_CURRENCY = "currency_mark";
	public static final String PARAM_RECEIPT_EXTRA_COST = "extra_cost";
	public static final String PARAM_RECEIPT_SUBTOTAL_COST = "sub_total_cost";
	public static final String PARAM_RECEIPT_CUTDOWN_COST = "cut_down_cost";
	public static final String PARAM_RECEIPT_SOURCE = "source";
	public static final String PARAM_RECEIPT_RECEIPT_ID = "store_define_id";
	
	private String mId;
	private String mReceiptId;		// optional when null, it's N/A
	private String mTime;
	private String mStoreName;
	private String mStoreAcc;		// optional. not null when
	private String mTax;
	private String mTotal;
	private String mCurrency;
	private String mExtraCost;		// optional
	private String mSubCost;		// optional
	private String mCutDownCost;	// optional
	private String mSource;
	
	public BasicInfo() {
		mId = null;
		mReceiptId = null;
		mTime = null;
		mStoreName = null;
		mTax = null;
		mTotal = null;
		mCurrency = null;
		mStoreAcc = null;
		mExtraCost = null;
		mSubCost = null;
		mCutDownCost = null;
		mSource = null;
	}
	
	public BasicInfo(JSONObject basic, boolean where) {
		try {
			Log.i(TAG, "basic json "+basic);
			
			// the receipts from nfc device have no id.
			if (where == FROM_DB) {
				mId = basic.getString(PARAM_RECEIPT_ID);
			}
			else {
				mId = "-1";		// -1 means the receipt is from NFC and not synced yet.
			}
			Log.i(TAG, "get id"+mId);
			
			if (where == FROM_DB) {
				mTime = basic.getString(PARAM_RECEIPT_TIME);
			}
			else {
				mTime = "not synced yet";
			}
			Log.i(TAG, "get time"+mTime);
			
			mStoreName = basic.getString(PARAM_RECEIPT_STORE_NAME);
			Log.i(TAG, "get store name"+mStoreName);
			
			mTotal = basic.getString(PARAM_RECEIPT_TOTAL);
			Log.i(TAG, "get total"+mTotal);
			
			mTax = basic.getString(PARAM_RECEIPT_TAX);
			Log.i(TAG, "get tax"+mTax);
			
			mCurrency = basic.getString(PARAM_RECEIPT_CURRENCY);
			Log.i(TAG, "get currency"+mCurrency);
			
			mSource = basic.getString(PARAM_RECEIPT_SOURCE);
			Log.i(TAG, "get source"+mSource);
			
			if (!basic.isNull(PARAM_RECEIPT_EXTRA_COST)) {
				mExtraCost = basic.getString(PARAM_RECEIPT_EXTRA_COST);
				Log.i(TAG, "get extra cost"+mExtraCost);
			}
			
			if (!basic.isNull(PARAM_RECEIPT_RECEIPT_ID)) {
				mReceiptId = basic.getString(PARAM_RECEIPT_RECEIPT_ID);
				Log.i(TAG, "get store define id"+mReceiptId);
			}
			else {
				mReceiptId = "N/A";
				Log.i(TAG, "get store define id"+mReceiptId);
			}
			
			if (!basic.isNull(PARAM_RECEIPT_SUBTOTAL_COST)) {
				mSubCost = basic.getString(PARAM_RECEIPT_SUBTOTAL_COST);
				Log.i(TAG, "get subtotal cost"+mSubCost);
			}
			
			if (!basic.isNull(PARAM_RECEIPT_CUTDOWN_COST)) {
				mCutDownCost = basic.getString(PARAM_RECEIPT_CUTDOWN_COST);
				Log.i(TAG, "get cut down cost"+mCutDownCost);
			}
			
			if (!basic.isNull(PARAM_RECEIPT_STORE_ACC)) {
				mStoreAcc = basic.getString(PARAM_RECEIPT_STORE_ACC);
				Log.i(TAG, "get store acc"+mStoreAcc);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void setId(String id) {
		mId = id;
	}
	
	public String getId() {
		return mId;
	}
	
	public void setTime(String time) {
		mTime = time;
	}
	
	public String getTime() {
		return mTime;
	}
	
	public void setStoreName(String sn) {
		mStoreName = sn;
	}
	
	public String getStoreName() {
		return mStoreName;
	}
	
	public void setTotal(String total) {
		mTotal = total;
	}
	
	public String getTotal() {
		return mTotal;
	}
	
	public void setTax(String tax) {
		mTax = tax;
	}
	
	public String getTax() {
		return mTax;
	}
	
	public void setCurrency(String currency) {
		mCurrency = currency;
	}
	
	public String getCurrency() {
		return mCurrency;
	}
	
	public void setStoreAcc(String storeacc) {
		mStoreAcc = storeacc;
	}
	
	public String getStoreAcc() {
		return mStoreAcc;
	}
	
	public void setExtraCost(String ec) {
		mExtraCost = ec;
	}
	
	public String getExtraCost() {
		return mExtraCost;
	}
	
	public void setSubCost(String sc) {
		mSubCost = sc;
	}
	
	public String getSubCost() {
		return mSubCost;
	}
	
	public void setCutDownCost(String cdc) {
		mCutDownCost = cdc;
	}
	
	public String getCutDownCost() {
		return mCutDownCost;
	}
	
	public void setSource(String source) {
		mSource = source;
	}
	
	public String getSource() {
		return mSource;
	}
	
	public void setReceiptId(String rid) {
		mReceiptId = rid;
	}
	
	public String getReceiptId() {
		return mReceiptId;
	}
}
