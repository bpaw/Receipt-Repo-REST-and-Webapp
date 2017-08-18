package rrr.third_party.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.*;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.http.protocol.HTTP.UTF_8;

/**
 * Created by Brandon Paw on 8/13/2017.
 */
public class firebaseUtil {

    public static void main(String[] args) throws IOException {
        FileInputStream serviceAccount = new FileInputStream("Receipt Repo-3c08f34f874e.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://receipttracker-d59f5.firebaseio.com/")
                .setStorageBucket("receipttracker-d59f5.appspot.com")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object document = dataSnapshot.getValue();
                System.out.println(document);
            }

            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Database error listener ref");
            }
        });

        Bucket bucket = StorageClient.getInstance().bucket();
        System.out.println(bucket.toString());

        String blobName = "helloworld";
        InputStream content = new ByteArrayInputStream("Hello, World!".getBytes(UTF_8));
        Blob blob = bucket.create(blobName, content, "text/plain");
    }
}
