package com.example.cyui.demo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RegisterRoleEnum
 * @Description: 账户-角色枚举
 * @Author: huangchuang 
 * @Date: 2017年10月19日 上午10:20:25
 */ 
public enum RegisterRoleEnum
{
	ROLE_SUPER_ADMINISTRATOR("0", "超级管理员"), 
	ROLE_ADMIN("1", "管理员"), 
	ROLE_INDIVIDUAL("2", "个人"), 
	ROLE_MERCHANT("3", "商户"),
	ROLE_AGENT("4", "代理商"),
	ROLE_CUSTOM_SERVICE("5", "客服");

	private static Map<String, String> map = new HashMap<String, String>();

	static
	{
		map.put(ROLE_SUPER_ADMINISTRATOR.getKey(), ROLE_SUPER_ADMINISTRATOR.getValue());
		map.put(ROLE_ADMIN.getKey(), ROLE_ADMIN.getValue());
		map.put(ROLE_INDIVIDUAL.getKey(), ROLE_INDIVIDUAL.getValue());
		map.put(ROLE_MERCHANT.getKey(), ROLE_MERCHANT.getValue());
		map.put(ROLE_AGENT.getKey(), ROLE_AGENT.getValue());
		map.put(ROLE_CUSTOM_SERVICE.getKey(), ROLE_CUSTOM_SERVICE.getValue());
	}

	public static String getValue(String key)
	{
		if (map.containsKey(key))
		{
			return map.get(key);
		}
		return null;
	}

	private String key;
	private String value;

	private RegisterRoleEnum(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

}
