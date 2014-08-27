package net.conor.android.cbeeber.location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by keegac01 on 27/08/2014.
 */
public class LocationFinder {

    private LocationManager locationManager;
    private final Geocoder geocoder;

    public LocationFinder(LocationManager locationManager, Geocoder geocoder) {
        this.locationManager = locationManager;
        this.geocoder = geocoder;
    }

    public boolean isUK(){
        try {
            Location location = getLocation();
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Address address = addresses.get(0);
            return (address.getCountryCode().equalsIgnoreCase("gb"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Location getLocation() {
        try {
            if (!locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                return null;
            } else {
                if (locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER) ) {
                    return requestLocationForProvider(LocationManager.GPS_PROVIDER);

                }else {
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
