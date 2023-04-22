package com.example.happyheart.service;

import com.example.happyheart.object.Appointment;
import com.example.happyheart.object.MedicalDetails;
import com.example.happyheart.object.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.IgnoreExtraProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public String register(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("user").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public String createAppointment(Appointment appointment) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("appointments").document().set(appointment);
        return collectionsApiFuture.get().getUpdateTime().toString();

    }

    public List<MedicalDetails> getMedicalDetails(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference medData = dbFirestore.collection("medical_details");
        Query query = medData.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<MedicalDetails> toReturn = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            toReturn.add(document.toObject(MedicalDetails.class));
        }
        return toReturn;
    }

    public List<Appointment> getAppointments(String email) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference medData = dbFirestore.collection("appointments");
        Query query = medData.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Appointment> toReturn = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            toReturn.add(document.toObject(Appointment.class));
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
