package com.topstrejiok.changemanager.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class CameraPreview extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {


    public CameraPreview(Context context) {
        super(context);
    }



    @Override
    public void onClick(View view) {
        dispatchPictureTakerAction();
    }

    private void dispatchPictureTakerAction() {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getContext().getPackageManager())!= null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if (photoFile!=null){
                String pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.topstrejiok.changemanager.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                ((Activity)getContext()).startActivityForResult(takePic, 1);
            }
        }
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storage = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storage);
        }catch (Exception e){
            Log.d("CameraPreview",e.getMessage());
        }
        return image;
    }
}
