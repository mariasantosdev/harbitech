package br.com.harbitech.school.category;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {
    @Test
    void heello() {
        System.setProperty("webdriver.chrome.driver", "/home/madu/Desktop/projetos/harbitech/drivers/chromedriver_linux64/chromedriver");
        WebDriver browser = new ChromeDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.quit();
    }
}
