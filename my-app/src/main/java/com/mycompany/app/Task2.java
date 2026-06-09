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
      // 1. Переходим по URL сервиса, который возвращает IP в формате JSON
      driver.get("https://api.ipify.org/?format=json");

      // 2. Сервис возвращает JSON внутри тега <pre>, находим этот элемент
      WebElement preElement = driver.findElement(By.tagName("pre"));
      String jsonText = preElement.getText();

      // 3. Парсим JSON-строку в объект
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(jsonText);

      // 4. Извлекаем значение по ключу "ip"
      String ip = (String) jsonObject.get("ip");

      // 5. Выводим результат в консоль
      System.out.println("Ваш публичный IPv4 адрес: " + ip);

    } catch (Exception e) {
      System.err.println("Ошибка при получении IP: " + e.getMessage());
    }
  }
}