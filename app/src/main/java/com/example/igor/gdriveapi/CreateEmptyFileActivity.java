package com.example.igor.gdriveapi;

import android.util.Log;

import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;

/**
 * Created by igor.lucic on 5/4/2018.
 */

public class CreateEmptyFileActivity extends BaseDemoActivity {

    private static final String TAG = "CreateEmptyFileActivity";

    @Override
    protected void onDriveClientReady() {
        createEmptyFile();
    }

    private void createEmptyFile() {
        // [START create_empty_file]
        getDriveResourceClient()
                .getRootFolder()
                .continueWithTask(task -> {
                    DriveFolder parentFolder = task.getResult();
                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle("New file")
                            .setMimeType("text/plain")
                            .setStarred(true)
                            .build();
                    return getDriveResourceClient().createFile(parentFolder, changeSet, null);
                })
                .addOnSuccessListener(this,
                        driveFile -> {
                            showMessage(R_str.file_created);
                            finish();
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to create file", e);
                    showMessage(R_str.file_create_error);
                    finish();
                });
        // [END create_empty_file]
    }

}
