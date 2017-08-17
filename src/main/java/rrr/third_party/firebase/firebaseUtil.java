package rrr.third_party.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.cloud.StorageClient;

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
        FileInputStream serviceAccount = new FileInputStream("receipttracker-d59f5-firebase-adminsdk-op20c-d2f0d0acda.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://receipttracker-d59f5.firebaseio.com/")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        System.out.println("I guess we initialized firebase then?");

        Bucket bucket = StorageClient
                .getInstance(app)
                .bucket("test");

        Storage storage = bucket.getStorage();
        System.out.println("Bucket is " + storage.toString());
        System.out.println("Bucket get policy is " + storage.getIamPolicy(bucket.getName()));

        InputStream content = new ByteArrayInputStream("Hello, World!".getBytes(UTF_8));
        Blob blob = bucket.create("hello world", content, "text/plain");
    }
}
