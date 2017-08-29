package rrr.third_party.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.*;

import java.io.*;

import static org.apache.http.protocol.HTTP.UTF_8;

/**
 * Created by Brandon Paw on 8/13/2017.
 */
public class firebaseUtil {

    public static Bucket bucket;

    public firebaseUtil() throws IOException {
        System.out.println("\nInitializing the Firebase Util\n");
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("Receipt Repo-3c08f34f874e.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://receipttracker-d59f5.firebaseio.com/")
                .setStorageBucket("receipttracker-d59f5.appspot.com")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);

        bucket = StorageClient.getInstance().bucket();
    }

    private static byte[] pdfToByteArray(String path) {
        InputStream inputStream = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream(path);

            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    public static String addPhoto(String accountId, String receiptId, byte[] pic) throws IOException {
        System.out.println("Adding photo!!!");
        String blobName = "user/" + accountId + "/" + receiptId;
//        FileInputStream content = new FileInputStream("brandon_paw_resume.pdf");
//        byte[] pdf = pdfToByteArray("brandon_paw_resume.pdf");
        Blob blob = bucket.create(blobName, pic, "image/jpg");
        return blobName;
    }

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

        bucket = StorageClient.getInstance().bucket();
        System.out.println(bucket.toString());

        String blobName = "helloworld";
        InputStream content = new ByteArrayInputStream("Hello, World!".getBytes(UTF_8));
        Blob blob = bucket.create(blobName, content, "text/plain");
    }
}
