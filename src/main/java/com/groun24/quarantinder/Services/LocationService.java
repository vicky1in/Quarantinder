package com.groun24.quarantinder.Services;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.groun24.quarantinder.Modal.Location;
import com.groun24.quarantinder.dao.LocationCityDAO;
import com.groun24.quarantinder.dao.LocationDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    @Autowired
    private LocationDAO locationDao;

    @Autowired
    private LocationCityDAO locationCityDAO;

    @Transactional
    public List<Location> get() {
        return locationDao.get();
    }

    @Transactional
    public Location get(int id) {
        return locationDao.get(id);
    }

    @Transactional
    public void save(Location location) {
        locationDao.save(location);
    }

    @Transactional
    public void delete(int id) {
        locationDao.delete(id);
    }

    @Transactional
    public Location findByCity(String city) {
        return locationCityDAO.findByCity(city);
    }

    @Transactional
    public void populate(String path) {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(",");

                Location newLocation = new Location();
                newLocation.setCity(country[1]);
                newLocation.setCountry(country[4]);
                newLocation.setLatitude(Double.parseDouble(country[2]));
                newLocation.setLongitude(Double.parseDouble(country[3]));
                locationDao.save(newLocation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
