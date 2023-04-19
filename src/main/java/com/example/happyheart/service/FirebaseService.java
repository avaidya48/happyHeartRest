package com.example.happyheart.service;

import com.example.happyheart.object.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.IgnoreExtraProperties;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
@IgnoreExtraProperties
public class FirebaseService {



    public String saveUserDetails(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Users").document(user.getUserName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetails(String userName) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("Users").document(userName);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User user = null;

        if(document.exists()){
            user = document.toObject(User.class);
        }
        return user;
    }

    public Integer findRadius(String userName) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("Users").document(userName);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        User user = null;

        if(document.exists()){
            user = document.toObject(User.class);
        }
        return user.getRadius();
    }

    public String deleteUser(String userName) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("Users").document(userName).delete();
        return collectionsApiFuture.get().getUpdateTime().toString();
    }




    public static double findDistance (double LatOne, double LatTwo, double LonOne, double LonTwo)
    {
        // toRadians function converts degrees to radians.
        LonOne = Math.toRadians(LonOne);
        LonTwo = Math.toRadians(LonTwo);
        LatOne = Math.toRadians(LatOne);
        LatTwo = Math.toRadians(LatTwo);

        // Here the Haversine formula is being created
        double deltaLon = LonTwo - LonOne;
        double deltaLat = LatTwo - LatOne;
        double formula = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(LatOne) * Math.cos(LatTwo)
                * Math.pow(Math.sin(deltaLon / 2),2);
        double fOutput = 2 * Math.asin(Math.sqrt(formula));

        // Earth's Radius multiplied by OP
        double r = 3956;
        return(fOutput * r);

    }


    public String updateUserDetails(String userName) throws ExecutionException, InterruptedException {
        User user = getUserDetails(userName);
        Integer num = user.getCompletedSurveys();
        if(num!=null)
            num++;
        user.setCompletedSurveys(num);
        Integer maxSurvey = 10;
        if(num!=null && num>=maxSurvey){
            user.setEligibleForRewards(true);
        }
        saveUserDetails(user);
        return  "Done";
    }






}
