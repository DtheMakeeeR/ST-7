package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {

  public static void execute(WebDriver driver) {
    printIpAddress(driver);
  }

  private static void printIpAddress(WebDriver browser) {
    System.out.println("\n=== Задание №2: Определение IP-адреса ===");

    try {
      browser.get("https://api.ipify.org/?format=json");

      WebElement jsonContainer = browser.findElement(By.tagName("pre"));
      String responseText = jsonContainer.getText();

      JSONParser parser = new JSONParser();
      JSONObject data = (JSONObject) parser.parse(responseText);

      String myIp = data.get("ip").toString();

      System.out.println("Результат: " + myIp);

    } catch (Exception error) {
      System.err.println("Не удалось получить IP: " + error.getMessage());
    }
  }
}