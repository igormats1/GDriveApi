/*
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.igor.gdriveapi;

import android.util.Log;

import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.Task;

/**
 * An activity to retrieve the metadata of a file.
 */
public class RetrieveMetadataActivity extends BaseDemoActivity {
    private static final String TAG = "PinFileActivity";

    @Override
    protected void onDriveClientReady() {
        pickTextFile()
                .addOnSuccessListener(this,
                        driveId -> retrieveMetadata(driveId.asDriveFile()))
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "No file selected", e);
                    showMessage(R_str.file_not_selected);
                    finish();
                });
    }
    private void retrieveMetadata(final DriveFile file) {
        // [START retrieve_metadata]
        Task<Metadata> getMetadataTask = getDriveResourceClient().getMetadata(file);
        getMetadataTask
                .addOnSuccessListener(this,
                        metadata -> {
                            showMessage(R_str.metadata_retrieved);
                            finish();
                        })
                .addOnFailureListener(this, e -> {
                    Log.e(TAG, "Unable to retrieve metadata", e);
                    showMessage(R_str.read_failed);
                    finish();
                });
        // [END retrieve_metadata]
    }
}
