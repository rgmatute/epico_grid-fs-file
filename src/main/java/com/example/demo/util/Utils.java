package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;

import org.bson.internal.Base64;

public class Utils {
	
	public static String getFileExtension(String filename) {
		String[] fileNamArray = filename.split("\\.");
		return fileNamArray[fileNamArray.length - 1].toUpperCase();
	}
	
	public static String getFileExtensionV2(String filename) {
		int lastPoint=filename.lastIndexOf('.');
		return filename.substring(lastPoint+1, filename.length()).toUpperCase();
	}
	
	public static String inputStreamToBase64(InputStream in) {
		try {
            return Base64.encode(in.readAllBytes());
        } catch (IOException e) {
            return "";
        }
    }
	
	public static void console(String title, Object message) {
        consoleTitle(title, message);
    }

    public static void consoleTitle(String title, Object message) {
        println(characterGenerate("*", 20) + title + characterGenerate("*", 20));
        println(message);
        println(characterGenerate("*", (40 + title.length())));
    }

    public static void consoleTitle(String title, Object message, int lengthCharacter) {
        println(characterGenerate("*", (lengthCharacter / 2)) + title + characterGenerate("*", (lengthCharacter / 2)));
        println(message);
        println(characterGenerate("*", (lengthCharacter + title.length())));
    }

    public static void println(Object message) {
        System.out.println(message);
    }
    
    public static String characterGenerate(String character, int length) {
        String r = "";
        for (int i = 0; i < length; i++) {
            r += character;
        }
        return r;
    }

}
