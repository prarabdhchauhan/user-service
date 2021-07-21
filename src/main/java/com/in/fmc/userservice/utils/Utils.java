package com.in.fmc.userservice.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Utils {

	public static String secureHash(String stringTobeHashed)
	{
		log.info("In secure hash");
		String hash=Hashing.sha512().hashString(stringTobeHashed, StandardCharsets.UTF_8).toString();
		return hash;
	}
	
}
