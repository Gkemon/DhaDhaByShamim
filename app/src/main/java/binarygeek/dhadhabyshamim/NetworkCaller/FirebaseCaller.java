package binarygeek.dhadhabyshamim.NetworkCaller;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import binarygeek.dhadhabyshamim.Activity.MainActivity;
import binarygeek.dhadhabyshamim.Model.Episode;

/**
 * Created by uy on 5/28/2018.
 */

public class FirebaseCaller {

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    public static boolean calledAlready = false;

    public FirebaseCaller(){

        initializationFirebase();
    }

    public  void initializationFirebase(){

        if (!calledAlready) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
            databaseReference = firebaseDatabase.getReference();
            calledAlready = true;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

//        ArrayList<Episode> episodeArrayList = new ArrayList<>();
//        Episode episode = new Episode();
//        episode.setEpisodeName("EMON");
//        ArrayList<DhaDha> dhaDhaArrayList = new ArrayList<>();
//        DhaDha dhaDha = new DhaDha();
//        dhaDha.setDhadha("dhadha ");
//        dhaDha.setAnswer("pada");
//
//        dhaDhaArrayList.add(dhaDha);
//        dhaDhaArrayList.add(dhaDha);
//        dhaDhaArrayList.add(dhaDha);
//        dhaDhaArrayList.add(dhaDha);
//
//        episode.setDhaDhas(dhaDhaArrayList);
//        episodeArrayList.add(episode);
//        episodeArrayList.add(episode);
//        episodeArrayList.add(episode);
//        databaseReference.child("DhaDhaByEpisode").setValue(episodeArrayList);
//

    }

    public  void getTotalEpisodes() {

        databaseReference.child("DhaDhaByEpisode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Episode> EpisodeItemsList = new ArrayList<Episode>();

                for (DataSnapshot EpisodeData : dataSnapshot.getChildren()) {
                    Episode episode = new Episode();
                    episode = EpisodeData.getValue(Episode.class);
                    EpisodeItemsList.add(episode);
                    if(episode!=null){
                        episode.dhaDhas.size();
                        Log.d("GK",episode.dhaDhas.size()+" the size of dhaha "+episode.dhaDhas.get(0));
                    }
                    else {
                        Log.d("GK","null the size of dhaha");
                    }
                }

                if(EpisodeItemsList!=null){
                    MainActivity.GlodbalDataStorageForEpisode=EpisodeItemsList;
                    Log.d("GK", "Episode list size " + String.valueOf(EpisodeItemsList.size()));
                }

else
                    Log.d("GK", "Episode is null");

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("GK", "error news" + databaseError.getMessage());

            }
        });
    }

}


