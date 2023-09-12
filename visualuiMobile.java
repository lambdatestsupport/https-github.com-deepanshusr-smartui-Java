package com.lambdatest;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;


public class visualuiMobile {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException, NoSuchAlgorithmException {

        Hashtable<String, Integer> errorColor= new Hashtable<>();
        errorColor.put("red",255);
        errorColor.put("green",0);
        errorColor.put("blue",0);

        HashMap<String,Object> output= new HashMap<String, Object>();
        output.put("errorColor",errorColor);//Output Difference error color
        output.put("transparency",0.1);// Set transparency of Output
        output.put("largeImageThreshold",1200);// the granularity to which the comparison happens(the scale or level of detail in a set of data.)Range-100-1200


        HashMap<String, Object> sm=new HashMap<String, Object>();
        sm.put("output",output);
        sm.put("scaleToSameSize",true);//scale to same size, when baseline image and comparision image is of different size, use true


        String username = "deepanshulambdatest";
        String access_key = "vvI4dCayA0O65Pzpj2mUqdOtwPIYfVlpEnX3OQtzLJDgQdAJME";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "VisualUI"+ LocalDate.now());
        capabilities.setCapability("name", "Visual Ui Testing");
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Pixel.*");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("visual",true);
        capabilities.setCapability("smartUI.project","RD_test");
        capabilities.setCapability("smartUI.build","build 1");

        driver = new RemoteWebDriver(new URL("http://" + username + ":" + access_key + "@mobile-hub.lambdatest.com/wd/hub"), capabilities);


    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;

        driver.get("https://www.lambdatest.com");
        Thread.sleep(5000);
        driver.executeScript("smartui.takeScreenshot=pic1");
        Thread.sleep(1000);

        driver.get("https://www.lambdatest.com/support/docs/");
//        Thread.sleep(5000);

        driver.executeScript("smartui.takeScreenshot=pic2");
        Thread.sleep(1000);

        driver.get("https://www.lambdatest.com/pricing");
//        Thread.sleep(5000);

        driver.executeScript("smartui.takeScreenshot=pic3");
        Thread.sleep(1000);

        Status = "passed";
        Thread.sleep(800);
        System.out.println("TestFinished");


    }


    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}
