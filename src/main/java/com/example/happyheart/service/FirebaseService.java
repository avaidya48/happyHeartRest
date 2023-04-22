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



    public String getAllMedicalDetails(MedicalDetails medicalDetails) throws ExecutionException, InterruptedException
    {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("medical_details").document().set(medicalDetails);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    
     public List<MedicalDetails> getLatestMedicalDetails(String email) throws ExecutionException, InterruptedException, ParseException
    {
        List<MedicalDetails> allRecords = getAllMedicalDetails(email);
        List<MedicalDetails> toReturn = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        for(int i = 0; i < allRecords.size(); i++)
        {
            Date date = formatter.parse(allRecords.get(i).getDate());
            allRecords.get(i).set_date(date);
        }
        Collections.sort(allRecords, new Comparator<MedicalDetails>()
        {
            @Override
            public int compare(MedicalDetails m1, MedicalDetails m2)
            {
                return m1.get_date().compareTo(m2.get_date());
            }
        });

        List<MedicalDetails> latestRecords = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            latestRecords.add(allRecords.get(i));
        }

        return latestRecords;
    }


    public List<Appointment> getLatestAppointments(String email) throws ExecutionException, InterruptedException, ParseException
    {
        List<Appointment> allRecords = getAppointments(email);
        List<Appointment> toReturn = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        for(int i = 0; i < allRecords.size(); i++)
        {
            Date date = formatter.parse(allRecords.get(i).getDate());
            allRecords.get(i).set_date(date);
        }
        Collections.sort(allRecords, new Comparator<Appointment>()
        {
            @Override
            public int compare(Appointment a1, Appointment a2)
            {
                return a1.get_date().compareTo(a2.get_date());
            }
        });

        List<Appointment> latestRecords = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            LocalDate currentDate = LocalDate.now();
            LocalDate localDate = allRecords.get(i).get_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            localDate.isAfter(currentDate);
            {
                latestRecords.add(allRecords.get(i));
            }
        }
        return latestRecords;
    }



}
