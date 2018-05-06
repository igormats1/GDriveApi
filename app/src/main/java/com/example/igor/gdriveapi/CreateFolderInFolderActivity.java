package com.example.igor.gdriveapi;

/**
 * Created by igor.lucic on 5/4/2018.
 */

import android.util.Log;

import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;

/**
 * An activity to create a folder inside a folder.
 */
public class CreateFolderInFolderActivity extends BaseDemoActivity {
    private static final String TAG = "CreateFolderInFolder";

    @Override
    protected void onDriveClientReady() {
        pickFolder()
                .addOnSuccessListener(this,
                        driveId -> createFolderInFolder(driveId.asDriveFolder()))
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "No folder selected", e);
                    showMessage(R_str.folder_not_selected);
                    finish();
                });
    }

    private void createFolderInFolder(final DriveFolder parent) {
        MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                .setTitle("New folder")
                .setMimeType(DriveFolder.MIME_TYPE)
                .setStarred(true)
                .build();

        getDriveResourceClient()
                .createFolder(parent, changeSet)
                .addOnSuccessListener(this,
                        driveFolder -> {
                            showMessage(R_str.folder_created);
                            finish();
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to create file", e);
                    showMessage(R_str.folder_create_error);
                    finish();
                });
    }
}

