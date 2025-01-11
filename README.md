# TymeX_Internship
# Currency Converter App

This Currency Converter App allows users to easily convert one currency to another, using real-time exchange rates fetched from the Currency Layer API.
## App Structure

The app is structured as follows:

- **UI Layer**: Built using Jetpack Compose for modern, declarative UI development.
  - `CurrencyConverterScreen`: The main screen where users input the amount, choose currencies, and perform conversions.
  - `CurrencyDropdown`: A reusable dropdown component to select currencies.
  
- **ViewModel Layer**: Handles the business logic for managing the conversion process.
  - `CurrencyConverterViewModel`: Holds state and manages interactions between the UI and the API.

- **API Integration**: Currency Layer API is used to fetch real-time and historical exchange rates.
  - Retrofit is used for API calls.
  
## Steps to Build and Run the App

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Khang2561/TymeX_Internship.git
   cd currency-converter-app
2. **Open the project in Android Studio:**

3. **Open Android Studio and select "Open an existing project."**
- Navigate to the project folder and open the project.
- Add your Currency Layer API key:

Sign up for an API key at Currency Layer.
Add your API key in the CurrencyConverterViewModel file where the API key is required.
Install dependencies: Ensure all dependencies are synced by Gradle. Android Studio will prompt you if any are missing.

4. **Run the app:**

- Select a physical or virtual device.
- Click "Run" or press Shift + F10 to launch the app.
5. **Use the app:**

- Input an amount to convert, select the "From Currency" and "To Currency", and press "Convert".

**Additional Notes**
The app uses Currency Layer API for fetching real-time exchange rates. Ensure that you have a valid API key and your app is connected to the internet.

**Challenges Encountered**
API Integration: Handling real-time data and ensuring the app behaves smoothly even with network latency.

**Video Demonstration**
You can watch a demo of the app's key features here:
