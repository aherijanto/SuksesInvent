package com.example.suksesinvent.ui.membership;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.suksesinvent.R;
import com.example.suksesinvent.databinding.FragmentMemberBinding;
import com.example.suksesinvent.databinding.FragmentSlideshowBinding;
import com.example.suksesinvent.model.BarcodeGenerator;
import com.example.suksesinvent.model.QRCodeGenerator;
import com.google.zxing.common.BitMatrix;

public class MemberFragment extends Fragment {
    private FragmentMemberBinding binding;
    private MemberViewModel mViewModel;
    private ImageView barcodeImageView;
    private ImageView qrImageView;

    public static MemberFragment newInstance() {
        return new MemberFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMemberBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        barcodeImageView = binding.barcodeimageview;
        qrImageView = binding.QRimageview;

        String data = "MiniPOSbyMimoApps"; // Replace with your data
        int width = 300; // Replace with your desired width
        int height = 60; // Replace with your desired height

        BitMatrix bitMatrix = BarcodeGenerator.generateBarcode(data, width, height);
        Bitmap barcodeBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                barcodeBitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        barcodeImageView.setImageBitmap(barcodeBitmap);


        int width_qr = 1000;
        int height_qr = 1000;

        try {
            Bitmap qrCodeBitmap = QRCodeGenerator.generateQrCode(data,width_qr,height_qr);


            qrImageView.setImageBitmap(qrCodeBitmap);
        } catch (Exception e) {
            Log.e("QRCodeGenerator", "Error generating QR code", e);
        }
        return root;
    }
}