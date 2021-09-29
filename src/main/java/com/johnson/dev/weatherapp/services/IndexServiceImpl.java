/*
package com.johnson.dev.weatherapp.services;

import com.johnson.dev.weatherapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ForecastService forecastService;

    private String[] countries = new String[]{"United States", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
            "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina",
            "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso",
            "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile",
            "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the",
            "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti",
            "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
            "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
            "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece",
            "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands",
            "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq",
            "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of",
            "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
            "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of",
            "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
            "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco",
            "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
            "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau",
            "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar",
            "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
            "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
            "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands",
            "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland",
            "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand",
            "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda",
            "Ukraine", "United Arab Emirates", "United Kingdom", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu",
            "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen",
            "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};

    @Override
    public String getIndex(String location, String country, Model model) {
        String fullLocation = location+","+country;
        LocationAndForecast forecastAndLoc = null;
        model.addAttribute("countries", countries);
        if (location != null && country != null){
            forecastAndLoc = forecastService.getForecastAndLocation(fullLocation);
        }
        if (forecastAndLoc != null) {
            GeocodedLocation geocodedLocation = forecastAndLoc.getLocation();
            model.addAttribute("location", geocodedLocation.getDisplayName());

            Forecast forecast = forecastAndLoc.getForecast();
            CurrentForecast currentForecast = forecast.getCurrent();
            List<Weather> weather = currentForecast.getWeather();

            List<DailyForecast> dailyForecasts = forecast.getDaily();
            List<HourForecast> hourlyForecasts = forecast.getHourly();

            model.addAttribute("dailyForecasts", dailyForecasts);
            model.addAttribute("currentTemp", kelvinToFahrenheit(currentForecast.getTemp()));
            model.addAttribute("description", weather.get(0).getDescription());
            model.addAttribute("currentDate", unixDateConverter(currentForecast.getDt(), "MM-dd-yyyy hh:mm a"));
            model.addAttribute("currentSunrise", unixDateConverter(currentForecast.getSunrise(), "hh:mm a"));
            model.addAttribute("currentSunset", unixDateConverter(currentForecast.getSunset(), "hh:mm a"));

        }
        return "index";
    }

    public String unixDateConverter(Long unixTime, String pattern){
        Date date = new Date(unixTime*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat(pattern);
        jdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        String formattedDate = jdf.format(date);
        return formattedDate;
    }

    public int kelvinToFahrenheit(Double kelvinTemp){
        return (int)((kelvinTemp * 9/5) - 459.67);
    }
}
*/
