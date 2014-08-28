package net.conor.android.cbeeber.util.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by keegac01 on 27/08/2014.
 */
public class LocationFinder {
    public static final String[] ACCEPTABLE_COUNTRIES = {"GB","US","IE"};

    private final Geocoder geocoder;
    private LocationManager locationManager;

    public LocationFinder(final LocationManager locationManager, final Geocoder geocoder) {
        this.locationManager = locationManager;
        this.geocoder = geocoder;
    }

    public boolean isAllowedTerritory(Context context) {
        try {
            try {
                Thread.sleep(1 * 1000);
            } catch (Exception exception) {
                Log.e("cbeeber", "Exception", exception);
            }
            Location location = getLocation();
            if (location != null){
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses.size()>0){
                    Address address = addresses.get(0);
                    return isAcceptableCountry(address.getCountryCode());
                    //return (ACCEPTABLE_COUNTRY.equalsIgnoreCase(address.getCountryCode()));
                }
            }

        } catch (IOException e) {
            Log.e("CBeeber",e.getMessage());
        }

        //TODO there are cases where the location service times out, for now I am
        //falling back to using the device's locale.
        String country = context.getResources().getConfiguration().locale.getCountry();
        Log.i("CBeeber","Using Locale Country"+country);

        return isAcceptableCountry(country);

    }

    private boolean isAcceptableCountry(String country) {
        for(String acceptable:ACCEPTABLE_COUNTRIES){
            if (acceptable.equals(country)){
                return true;
            }
        }
        return false;
    }

    protected Location getLocation() {
        try {
            if (!locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                return null;
            } else {
                if (locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    return requestLocationForProvider(LocationManager.GPS_PROVIDER);

                } else {
                    return requestLocationForProvider(LocationManager.NETWORK_PROVIDER);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Location requestLocationForProvider(String provider) {
        return locationManager.getLastKnownLocation(provider);

    }

}
