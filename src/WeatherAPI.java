import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherAPI{
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String city;
            do {
                System.out.println("=============================");
                System.out.println("Enter the city name");
                city = scanner.nextLine();

                if (city.equalsIgnoreCase("quit")) break;

                JSONObject cityLocation = (JSONObject) getLocation(city);
                assert cityLocation != null;
                double latitude = (double) cityLocation.get("latitude");
                double longitude = (double) cityLocation.get("longitude");

                displayWeather(latitude, longitude);

            }while (!city.equalsIgnoreCase("quit"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static JSONObject getLocation(String city) {
        city = city.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        try {
            HttpURLConnection apiConection = fetchApiResponse(urlString);

            if (apiConection.getResponseCode() != 200) {
                System.out.println("Something went wrong");
                return null;
            }
            String jsonResponse = readApiResponse(apiConection);

            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String readApiResponse(HttpURLConnection apiConnection){
            try{
                StringBuilder resultsJson = new StringBuilder();

                Scanner scanner = new Scanner(apiConnection.getInputStream());

                while(scanner.hasNext()){
                    resultsJson.append(scanner.nextLine());
                }
                scanner.close();

                return resultsJson.toString();

            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    private static HttpURLConnection fetchApiResponse(String urlString){
            try{
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection)  url.openConnection();

                conn.setRequestMethod("GET");

                return conn;
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    private static void displayWeather(double latitude, double longitude) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,wind_speed_10m&wind_speed_unit=mph&temperature_unit=fahrenheit&precipitation_unit=inch";
            HttpURLConnection apiConection = fetchApiResponse(url);

            if (apiConection.getResponseCode() != 200) {
                System.out.println("Something went wrong getting weather");
                return;
            }

            String jsonResponse = readApiResponse(apiConection);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            JSONObject currentWeather = (JSONObject) jsonObject.get("current");

            String time = (String) currentWeather.get("time");
            System.out.println("Current Time: " + time);

            double temperature = (double) currentWeather.get("temperature_2m");
            System.out.println("Current Temperature: " + temperature + "farenheit");

            double apparentTemperature = (double) currentWeather.get("apparent_temperature");
            System.out.println("Apparent Temperature: " + apparentTemperature + "farenheit");

            double windSpeed = (double) currentWeather.get("wind_speed_10m");
            System.out.println("Current Wind Speed: " + windSpeed + "mph");

            long relativeHumidity = (long) currentWeather.get("relative_humidity_2m");
            System.out.println("Current Relative Humidity: " + relativeHumidity);

            double precipitation = (double) currentWeather.get("precipitation");
            System.out.println("Current Precipitation: " + precipitation + "inches");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



