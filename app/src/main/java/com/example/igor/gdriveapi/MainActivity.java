package com.example.igor.gdriveapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by igor.lucic on 5/4/2018.
 */

public class MainActivity extends Activity {

    private final Class[] sActivities = new Class[] {
            CreateEmptyFileActivity.class,
            CreateFileActivity.class,
            CreateFolderActivity.class,
            CreateFileInFolderActivity.class,
            CreateFolderInFolderActivity.class,
            CreateFileInAppFolderActivity.class,
            CreateFileWithCreatorActivity.class,
            RetrieveMetadataActivity.class,
            RetrieveContentsActivity.class,
            RetrieveContentsWithProgressDialogActivity.class,
            EditMetadataActivity.class,
            AppendContentsActivity.class,
            RewriteContentsActivity.class,
            PinFileActivity.class,
            InsertUpdateCustomPropertyActivity.class,
            DeleteCustomPropertyActivity.class,
            QueryFilesActivity.class,
            QueryFilesInFolderActivity.class,
            QueryNonTextFilesActivity.class,
            QuerySortedFilesActivity.class,
            QueryFilesSharedWithMeActivity.class,
            QueryFilesWithTitleActivity.class,
            QueryFilesWithCustomPropertyActivity.class,
            QueryStarredTextFilesActivity.class,
            QueryTextOrHtmlFilesActivity.class
    };








    private String[] titles = new String[] {
            "Create Empty File Activity",
            "Create File Activity",
            "Create Folder Activity",
            "Create File In Folder Activity",
            "Create Folder In Folder Activity",
            "Create File In App Folder Activity",
            "Create File With Creator Activity",
            "Retrieve Metadata Activity",
            "Retrieve Contents Activity",
            "Retrieve Contents With Progress Dialog Activity",
            "Edit Metadata Activity",
            "Append Contents Activity",
            "Rewrite Contents Activity",
            "Pin File Activity",
            "Insert Update Custom Property Activity",
            "Delete Custom Property Activity",
            "Query Files Activity.class",
            "Query Files In Folder Activity",
            "Query Non Text Files Activity",
            "Query Sorted Files Activity",
            "Query Files Shared With Me Activity",
            "Query Files With Title Activity",
            "Query Files With Custom Property Activity",
            "Query Starred Text Files Activity",
            "Query Text Or Html Files Activity"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListViewSamples = (ListView)findViewById(R.id.listViewSamples);

        mListViewSamples.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles));

        mListViewSamples.setOnItemClickListener((arg0, arg1, i, arg3) -> {
            System.out.println(i);
            System.out.println(sActivities[i]);
            Intent intent = new Intent(getBaseContext(), sActivities[i]);
            startActivity(intent);
        });

    }
}
