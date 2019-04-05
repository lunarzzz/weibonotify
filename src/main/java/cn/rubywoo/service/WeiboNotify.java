package cn.rubywoo.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * @ClassNameWeiboNotify
 * @Description TODO
 * @Author apple
 * @Date 2019/4/5 3:07 PM
 * @Version 1.0
 */
public class WeiboNotify {

    private static String username;

    private static Properties properties;

    private static String first;

    private static OkHttpClient client = new OkHttpClient();

    static {
        InputStream stream = ClassLoader.getSystemResourceAsStream("config.yml");
        properties = new Properties();
        try {
            properties.load(stream);
            System.setProperty("webdriver.chrome.driver", "/driver/chromedriver.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void  notifyWechat() throws InterruptedException, MalformedURLException {
        // remote webdriver
        while (true) {
            try {
                WebDriver webDriver = new ChromeDriver();

//                WebDriver webDriver = new RemoteWebDriver(new URL(properties.getProperty("webDriver.url")), new ChromeOptions());
                while (true) {
                    webDriver.get(properties.getProperty("weibo.url"));
                    String title = webDriver.getTitle();
                    System.out.println(title);
                    Thread.sleep(10000);
                    // Username
                    String newUsername = webDriver.findElement(By.className("username")).getText();
                    if (WeiboNotify.username == null) {
                        WeiboNotify.username = newUsername;
                    } else if (!WeiboNotify.username.equals(newUsername)) {
                        send("username[" + username + "] is change", newUsername);
                    }

                    //
                    String s = webDriver.findElements(By.className("WB_text")).stream().map(WebElement::getText).findFirst().get();
                    if (first == null) {
                        first = s;
                    } else if (!s.equals(first)){
                        send("weibo has been updated", s);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
        }
    }


    private static void send(String title, String info) {
        Request request= new Request.Builder().get().url(properties.getProperty("weixin.url") + "?text=" + title + "&desp=" + info).build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
