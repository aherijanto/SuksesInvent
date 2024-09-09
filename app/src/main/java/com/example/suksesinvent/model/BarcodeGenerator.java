package com.example.suksesinvent.model;


import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

    public class BarcodeGenerator {
        public static BitMatrix generateBarcode(String data, int width, int height) {
            BitMatrix bitmatrix = null;
            try {
                bitmatrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, width, height);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return bitmatrix;
        }
    }

