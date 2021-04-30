package com.groun24.quarantinder.Services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.groun24.quarantinder.Modal.Location;
import com.groun24.quarantinder.Modal.Tag;
import com.groun24.quarantinder.Modal.User;
import com.groun24.quarantinder.Modal.UserProfile;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchSuggester {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MatchManager matchManager;

    @Autowired
    BlockManager blockManager;

    @Autowired
    LocationService locationService;

    private int getAge(User user) {
        if (user.getDob() == null) {
            return -1;
        }
        LocalDate date = user.getDob().toLocalDate();
        return Period.between(date, LocalDate.now()).getYears();
    }

    /* Copyright (c) 2015, Grumlimited Ltd (Romain Gallet)
     * All rights reserved.
     * https://github.com/grumlimited/geocalc/blob/master/LICENSE.txt
     */
    private Double calculateDistance(Location la, Location lb) {
        Coordinate lattitude = Coordinate.fromDegrees(la.getLatitude());
        Coordinate longitude = Coordinate.fromDegrees(la.getLongitude());
        Point pa = Point.at(lattitude, longitude);

        lattitude = Coordinate.fromDegrees(lb.getLatitude());
        longitude = Coordinate.fromDegrees(lb.getLongitude());
        Point pb = Point.at(lattitude, longitude);

        Double distance = EarthCalc.gcdDistance(pa, pb);
        return distance;
    }
    

    private List<User> getSuitableUsers(User user, Location location) {
        List<User> otherUsers = userService.get();
        List<User> suitableUsers = new ArrayList<User>();
        for (User otherUser : otherUsers) {
            // Can't match with themself
            if (user.equals(otherUser)) {
                continue;
            }
            // Match already exists between users
            if (matchManager.get(user, otherUser) != null) {
                continue;
            }
            // Either user or otherUser has blocked the other
            if (blockManager.get(user, otherUser) != null || blockManager.get(otherUser, user) != null) {
                continue;
            }
            // The user specified a location and otherUser is not within that location
            if (location != null && !location.equals(locationService.get(otherUser.getProfile().getLocationId()))) {
                continue;
            }
            // otherUser is too old
            if (getAge(otherUser) > user.getProfile().getMaxAgePreference()) {
                continue;
            }
            // otherUser is too young
            if (getAge(otherUser) < user.getProfile().getMinAgePreference()) {
                continue;
            }
            // user is too old
            if (getAge(user) > otherUser.getProfile().getMaxAgePreference()) {
                continue;
            }
            // user is too young
            if (getAge(user) < otherUser.getProfile().getMinAgePreference()) {
                continue;
            }
            // otherUser is wrong gender
            if (!user.getProfile().getGenderPreference().equals("No preference")) {
                if (!user.getProfile().getGenderPreference().equals(otherUser.getGender())) {
                    continue;
                }
            }
            // user is wrong gender
            if (!otherUser.getProfile().getGenderPreference().equals("No preference")) {
                if (!otherUser.getProfile().getGenderPreference().equals(user.getGender())) {
                    continue;
                }
            }
            suitableUsers.add(otherUser);
        }
        return suitableUsers;
    }

    private List<Entry<UserProfile, Integer>> rankByDistance(List<Entry<UserProfile, Integer>> suggestions,
            Location location) {
        List<Entry<Entry<UserProfile, Integer>, Double>> rankedSuggestionsWithDistance = new ArrayList<Entry<Entry<
            UserProfile, Integer>, Double>>();
        for (Entry<UserProfile, Integer> suggestion : suggestions) {
            Location suggestionLocation = locationService.get(suggestion.getKey().getLocationId());
            Double distance = calculateDistance(location, suggestionLocation);
            Entry<Entry<UserProfile, Integer>, Double> suggestionWithDistance = new Pair<Entry<UserProfile, Integer>, Double>(
                    suggestion, distance);
            rankedSuggestionsWithDistance.add(suggestionWithDistance);
        }
        rankedSuggestionsWithDistance.sort(Entry.comparingByValue());
        
        List<Entry<UserProfile, Integer>> rankedSuggestions = new ArrayList<Entry<UserProfile, Integer>>();
        for (Entry<Entry<UserProfile, Integer>, Double> suggestionWithDistance : rankedSuggestionsWithDistance) {
            rankedSuggestions.add(suggestionWithDistance.getKey());
        }
        return rankedSuggestions;
    }

    private List<Entry<UserProfile, Integer>> rankBySimilarity(User user, List<User> otherUsers) {
        List<Entry<UserProfile, Integer>> rankedProfiles = new ArrayList<Entry<UserProfile, Integer>>();
        Set<Tag> tags = user.getProfile().getTags();
        for (User otherUser : otherUsers) {
            Set<Tag> otherTags = otherUser.getProfile().getTags();
            Integer similarityCount = 0;
            for (Tag tag : tags) {
                for (Tag otherTag : otherTags) {
                    if (tag.equals(otherTag)) {
                        similarityCount++;
                    }
                }
            }
            Entry<UserProfile, Integer> suggestion = new Pair<UserProfile, Integer>(otherUser.getProfile(),
                    similarityCount);
            rankedProfiles.add(suggestion);
        }
        rankedProfiles.sort(Entry.comparingByValue());
        Collections.reverse(rankedProfiles);
        return rankedProfiles;
    }

    public List<Entry<UserProfile, Integer>> getSuggestions(User user, Location location, boolean nearest) {
        List<User> suitableUsers = getSuitableUsers(user, location);
        List<Entry<UserProfile, Integer>> suggestions = rankBySimilarity(user, suitableUsers);
        if (nearest) {
            suggestions = rankByDistance(suggestions, locationService.get(user.getProfile().getLocationId()));
        }
        return suggestions;
    }
}