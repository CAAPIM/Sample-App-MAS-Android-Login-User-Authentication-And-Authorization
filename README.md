# Secure API Sample App

**Required:**
* Latest version of Android Studio
* Device with Passcode and/or Fingerprint locks enabled

## Get Started
1. In Android Studio, open the project 'MASSecureAPISample'.
2. In the CA OAuth Manager, create an app, and export the msso_config file (https://you_server_name:8443/oauth/manager). For help with this file, see [Android Guide](https://www.ca.com/us/developers/mas/docs.html?id=1).
3. Copy the contents of the exported msso_config to src/main/assets/msso_config.json.
4. In Policy Manager, import the policy retrieveFlights.xml as /retrieveFlights 
a. Publish Web API -> Enter Service name as retrieveFlights and Gateway URL as retrieveFlights -> Finish  
b. File -> Import Policy (into your newly created api) -> Select retrieveFlights.xml
5. Build and Deploy the app to a device.
6. Login to app with a valid user and it will retreive the flight info from the database as a json
