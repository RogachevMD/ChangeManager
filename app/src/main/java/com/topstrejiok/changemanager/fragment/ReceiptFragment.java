package com.topstrejiok.changemanager.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ReceiptFragment extends Fragment {
    private static String pathToFile;
    ImageView imageView;
    private String directory = Environment.DIRECTORY_PICTURES + "/ChangeManager";
    private String key = SessionActivity.mainKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_receipt, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("KEK", directory);
        Log.d("KEK", key);
        imageView = getView().findViewById(R.id.receripHolder);
        /*if (isFileExist(SessionActivity.mainKey)) {
            Log.d("KEK", "exist");
            imageView.setImageBitmap(BitmapFactory.decodeFile(pathToFile));
        }*/
        //проверка на наличие файла и его загрузка

        if (isFileExist(key)) {
            Bitmap bitmap = BitmapFactory.decodeFile(directory + "/" + key);
            imageView.setImageBitmap(bitmap);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFileExist(key)) {
                    Log.d("KEK", "onclick exist");
                    dispatchPictureTakerAction();
                }
                dispatchPictureTakerAction();
            }
        });
    }

    private void dispatchPictureTakerAction() {
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePic.resolveActivity(getContext().getPackageManager()) != null) {
            File photoFile;
            photoFile = createPhotoFile();
            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Log.d("KEK", "dispatch " + pathToFile);
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.topstrejiok.changemanager.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePic, 1);
            }
        }
    }

    private File createPhotoFile() {
        //String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String name = key;
        File newDir = getExternalStoragePublicDirectory(directory);
        if (!newDir.exists()) {
            if (!newDir.mkdirs()) {
                Log.d("Error", "KEK");
            }
        }
        File storage = getExternalStoragePublicDirectory(directory);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storage);
        } catch (Exception e) {
            Log.d("CameraPreview", e.getMessage());
        }
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            Log.d("KEK", "onResult " + pathToFile);
            Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
            imageView.setImageBitmap(bitmap);
        }
    }

    private boolean isFileExist(String fileName) {
        File file = getExternalStoragePublicDirectory(directory + "/" + fileName);
        Log.d("KEK", "isExist " + file.getAbsolutePath());
        return file != null && !file.exists();
    }
}
