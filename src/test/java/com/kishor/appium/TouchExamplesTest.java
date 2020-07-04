package com.kishor.appium;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class TouchExamplesTest {

	private static AndroidDriver<AndroidElement> driver;
	private static TouchAction touchAction;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Launching driver...");
		driver = BaseDriver.getDriver("emulator");
		touchAction = new TouchAction(driver);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Quitting driver...");
		driver.quit();
		
	}
	
	@Before
	public void beforeTest() {
		System.out.println("########### Before Test, Launching App ############ ");
		driver.launchApp();
	}
	
	@After
	public void afterTest() {
		System.out.println("########### After Test, Closing App ############ ");
		driver.closeApp();
	}

	@Test
	public void testTapAction() {
		System.out.println("Testing Tap actions...");
		AndroidElement views = driver.findElementByXPath(
				"//android.widget.TextView[@text='Views']");
		views.click();
		System.out.println("Clicked views...");
		WebElement expandableList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandableList))).perform();
		System.out.println("Tapped expandable list!");
	}
	
	@Test
	public void testLongPressAction() {
		System.out.println("Testing long press actions...");
		AndroidElement views = driver.findElementByXPath(
				"//android.widget.TextView[@text='Views']");
		views.click();
		System.out.println("Clicked views...");
		WebElement expandableList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandableList))).perform();
		System.out.println("Tapped expandable list!");
		driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
		
		WebElement peopleNames = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(
				ElementOption.element(peopleNames)).withDuration(Duration.ofSeconds(2))).release().perform();
		assertTrue("Long press unsuccessful!", driver.findElementById("android:id/title").isDisplayed());
	}
	
	@Test
	public void testSwipeAction() {
		System.out.println("Testing Swipe actions...");
		AndroidElement views = driver.findElementByXPath(
				"//android.widget.TextView[@text='Views']");
		views.click();
		System.out.println("Clicked views...");
		//Click Data widgets
		driver.findElementByXPath(
				"//android.widget.TextView[@text='Date Widgets']").click();
		
		//Click Inline
		driver.findElementByXPath(
				"//android.widget.TextView[@text='2. Inline']").click();
		
		//Select Hour as 9
		driver.findElementByXPath(
				"//*[@content-desc='9']").click();
		
		WebElement from = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement to = driver.findElementByXPath("//*[@content-desc='45']");

		touchAction.longPress(LongPressOptions.longPressOptions()
				.withElement(ElementOption.element(from))
				.withDuration(Duration.ofSeconds(2)))
				.moveTo(ElementOption.element(to))
				.release().perform();
	}
	
	@Test
	public void testScrollAction() {
		System.out.println("Testing Scroll action...");
		AndroidElement views = driver.findElementByXPath(
				"//android.widget.TextView[@text='Views']");
		views.click();
		System.out.println("Clicked views...");
		
		AndroidElement webView = driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
		webView.click();
		
	}
	
	
	

}
