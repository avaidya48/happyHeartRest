package com.example.happyheart.service;

import com.example.happyheart.object.MedicalDetails;
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


    public String login(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("user").document(user.getEmail());
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        if(document.exists()){
            User found = document.toObject(User.class);
            if(found.getPassword().equals(user.getPassword())){
                return "Login Successful";
            }else{
                return "Incorrect Password";
            }

        }
        return "User Does Not exist";
    }

    public User register(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("user").document(user.getEmail()).set(user);

        DocumentReference documentReference = dbFirestore.collection("user").document(user.getEmail());

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        User toReturn = null;
        if(document.exists()){
            toReturn = document.toObject(User.class);
        }
        return toReturn;

    }


    public String createLatestMedicalDetails(MedicalDetails medicalDetails) throws ExecutionException, InterruptedException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("medical_details").document().set(medicalDetails);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }




}
