/**
* @Project: hft-mic
* @Package com.hft.util
* FileName：MD5Util.java
* Version：v1.0
* date：2016年9月2日
* Copyright © 2016 Shanghai huifutong Network Technology Co.,Ltd All Rights Reserved
*/

package com.example.cyui.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* FileName: MD5Util.java
* @Description:MD5加密工具类
* @author: Hubert 
* @version: v1.0
* @create at: 2016年9月2日 下午3:35:03
* @reviewer:
* @review at:
*
* Revision history:
* date        author      version     content
* ------------------------------------------------------------
* 2016年9月2日    Hubert    v1.0        XXXX
*
* Copyright © 2016 Shanghai huifutong Technology Co.,Ltd All Rights Reserved
*/
public class MD5Util
{
	/*** 
	 * MD5加密 生成32位md5码
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr)
	{
		MessageDigest md5 = null;
		byte[] byteArray = null;
		try
		{
			md5 = MessageDigest.getInstance("MD5");
			byteArray = inStr.getBytes("UTF-8");
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++)
		{
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
			{
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String toMD5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e)
		{
			return null;
		}
	}
	
	public static String string2MD5(String source)
	{
		String result = null;
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			try
			{
				messageDigest.update(source.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			byte tempByte[] = messageDigest.digest();
			int i;
			StringBuffer buffer = new StringBuffer();
			for (int offset = 0; offset < tempByte.length; offset++)
			{
				i = tempByte[offset];
				if (i < 0)
				{
					i += 256;
				}
				if (i < 16)
				{
					buffer.append("0");
				}
				buffer.append(Integer.toHexString(i));
			}
			result = buffer.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
