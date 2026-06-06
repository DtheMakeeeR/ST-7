package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
  public static void main(String[] args) {

    System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

    WebDriver driver = new ChromeDriver();
    try {

      Task1.execute(driver);


      Task2.execute(driver);


      Task3.execute(driver);

    } catch (Exception e) {
      System.err.println("Произошла ошибка: " + e.getMessage());
      e.printStackTrace();
    } finally {

      if (driver != null) {
        driver.quit();
      }
    }
  }
}


class Task1 {
  public static void execute(WebDriver driver) {
    System.out.println("\n=== Задание №1: Генератор паролей ===");
    try {
      driver.get("https://www.calculator.net/password-generator.html");

      var passwordElement = driver.findElement(org.openqa.selenium.By.id("generated-password"));
      String password = passwordElement.getAttribute("value");
      System.out.println("Сгенерированный пароль: " + password);
    } catch (Exception e) {
      System.out.println("Не удалось получить пароль. Возможно, изменился ID элемента на странице.");
    }
  }
}