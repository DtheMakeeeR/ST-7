package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {
  public static void execute(WebDriver driver) {
    System.out.println("\n=== Задание №2: Мой IP-адрес ===");
    try {
      driver.get("https://api.ipify.org/?format=json");


      WebElement preElement = driver.findElement(By.tagName("pre"));
      String jsonText = preElement.getText();


      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(jsonText);
      String ip = (String) jsonObject.get("ip");

      System.out.println("Ваш публичный IPv4 адрес: " + ip);
    } catch (Exception e) {
      System.err.println("Ошибка при получении IP: " + e.getMessage());
    }
  }
}