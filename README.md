Java Weather API Client
A command-line weather application that fetches and displays current weather data for any city using the Open-Meteo API.

Features
City-based Weather Lookup: Enter any city name to get current weather information

Real-time Data: Fetches live weather data from Open-Meteo API

Comprehensive Weather Metrics:

Temperature (Fahrenheit)

Apparent temperature (Feels-like)

Wind speed (MPH)

Relative humidity (%)

Precipitation (inches)

User-Friendly Interface: Simple text-based interface with clear prompts

Continuous Operation: Runs until user explicitly quits

How It Works
The application prompts the user for a city name

It first calls the Open-Meteo Geocoding API to convert the city name to coordinates

Then it uses those coordinates to fetch weather data from the Open-Meteo Weather API

Finally, it displays the formatted weather information to the user

Code Structure
Main Components:
Main Method: Handles user input and program flow

getLocation(): Converts city name to geographic coordinates

fetchApiResponse(): Establishes connection to the API

readApiResponse(): Reads and processes API response

displayWeather(): Formats and displays weather information

Dependencies
json-simple: For parsing JSON responses from the API

Usage
Compile the Java file:

bash
javac -cp .:json-simple-1.1.1.jar WeatherAPI.java
Run the program:

bash
java -cp .:json-simple-1.1.1.jar WeatherAPI
Enter a city name when prompted, or type "quit" to exit

Example Output
text
=============================
Enter the city name
New York
Current Time: 2023-08-10T14:30
Current Temperature: 75.2farenheit
Apparent Temperature: 78.5farenheit
Current Wind Speed: 8.3mph
Current Relative Humidity: 65
Current Precipitation: 0.0inches
API Reference
This application uses the following APIs from Open-Meteo:

Geocoding API: https://geocoding-api.open-meteo.com/v1/search

Weather Forecast API: https://api.open-meteo.com/v1/forecast

Error Handling
The application includes basic error handling for:

Invalid city names

API connection issues

JSON parsing errors

Potential Enhancements
Add error messages for invalid city names

Implement a graphical user interface (GUI)

Add weather forecasting for multiple days

Include more weather metrics (UV index, visibility, etc.)

Add unit conversion options (Celsius/Fahrenheit)

Implement location caching to reduce API calls

License
This project is provided for educational purposes. Please check Open-Meteo's terms of service before using this code for production purposes.
