package com.example.igor.gdriveapi;

/**
 * Created by igor.lucic on 5/4/2018.
 */

import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.OpenFileActivityOptions;
import com.google.android.gms.tasks.Task;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * An activity that illustrates how to use the creator
 * intent to create a new file. The creator intent allows the user
 * to select the parent folder and the title of the newly
 * created file.
 */
public class CreateFileWithCreatorActivity extends BaseDemoActivity {
    private static final String TAG = "CreateFileWithCreator";
    private static final int REQUEST_CODE_CREATE_FILE = 2;

    @Override
    protected void onDriveClientReady() {
        createFileWithIntent();
    }

    private void createFileWithIntent() {
        // [START create_file_with_intent]
        Task<DriveContents> createContentsTask = getDriveResourceClient().createContents();
        createContentsTask
                .continueWithTask(task -> {
                    DriveContents contents = task.getResult();
                    OutputStream outputStream = contents.getOutputStream();
                    try (Writer writer = new OutputStreamWriter(outputStream)) {
                        writer.write("Hello World!");
                    }

                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle("New file")
                            .setMimeType("text/plain")
                            .setStarred(true)
                            .build();

                    CreateFileActivityOptions createOptions =
                            new CreateFileActivityOptions.Builder()
                                    .setInitialDriveContents(contents)
                                    .setInitialMetadata(changeSet)
                                    .build();
                    return getDriveClient().newCreateFileActivityIntentSender(createOptions);
                })
                .addOnSuccessListener(this,
                        intentSender -> {
                            try {
                                startIntentSenderForResult(
                                        intentSender, REQUEST_CODE_CREATE_FILE, null, 0, 0, 0);
                            } catch (IntentSender.SendIntentException e) {
                                Log.e(TAG, "Unable to create file", e);
                                showMessage(R_str.file_create_error);
                                finish();
                            }
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to create file", e);
                    showMessage(R_str.file_create_error);
                    finish();
                });
        // [END create_file_with_intent]
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CREATE_FILE) {
            if (resultCode != RESULT_OK) {
                Log.e(TAG, "Unable to create file");
                showMessage(R_str.file_create_error);
            } else {
                DriveId driveId =
                        data.getParcelableExtra(OpenFileActivityOptions.EXTRA_RESPONSE_DRIVE_ID);
                showMessage(R_str.file_created);
            }
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
