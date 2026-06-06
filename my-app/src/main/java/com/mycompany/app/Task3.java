package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Task3 {
  public static void execute(WebDriver driver) {
    System.out.println("\n=== Задание №3: Прогноз погоды (Нижний Новгород) ===");

    String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    try {
      driver.get(url);
      WebElement preElement = driver.findElement(By.tagName("pre"));
      String jsonText = preElement.getText();

      JSONParser parser = new JSONParser();
      JSONObject fullResponse = (JSONObject) parser.parse(jsonText);


      JSONObject hourlyData = (JSONObject) fullResponse.get("hourly");
      JSONArray times = (JSONArray) hourlyData.get("time");
      JSONArray temperatures = (JSONArray) hourlyData.get("temperature_2m");
      JSONArray rains = (JSONArray) hourlyData.get("rain");


      System.out.printf("%-4s | %-20s | %-11s | %s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
      System.out.println("--------------------------------------------------------");


      java.io.File resultDir = new java.io.File("result");
      if (!resultDir.exists()) resultDir.mkdir();

      try (PrintWriter fileWriter = new PrintWriter(new FileWriter("result/forecast.txt"))) {

        fileWriter.printf("%-4s | %-20s | %-11s | %s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
        fileWriter.println("--------------------------------------------------------");


        for (int i = 0; i < times.size(); i++) {
          String time = (String) times.get(i);

          String formattedTime = time.replace("T", " ");
          double temp = (double) temperatures.get(i);
          double rain = (double) rains.get(i);


          System.out.printf("%-4d | %-20s | %-11.1f | %.1f%n", i+1, formattedTime, temp, rain);


          fileWriter.printf("%-4d | %-20s | %-11.1f | %.1f%n", i+1, formattedTime, temp, rain);
        }
      }

      System.out.println("\nТаблица прогноза успешно сохранена в 'result/forecast.txt'");

    } catch (Exception e) {
      System.err.println("Ошибка при получении или парсинге погоды: " + e.getMessage());
      e.printStackTrace();
    }
  }
}