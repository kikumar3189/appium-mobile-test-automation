package com.kishor.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseDriver {
	/*
	 * First test in mobile to launch an app
	 * Pre-req: 
	 * 1. Appium server should be started at http://127.0.0.1:4723 
	 * 2. Emulator with Device name Pixel_2_Pie_API_28 should be started
	 * 3. APK file original.apk should be present on classpath
	 * 
	 */
	
	private static AndroidDriver<AndroidElement> driver;

	public static AndroidDriver<AndroidElement> getDriver(String deviceType) {
		
		File app = new File("src//test//resources//original.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if(deviceType.equalsIgnoreCase("real")) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		}else {
			// Run tests on emulator
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_Pie_API_28");
		}
		
		
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "10");
		try {
			driver = new AndroidDriver<AndroidElement>(
					new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

}
