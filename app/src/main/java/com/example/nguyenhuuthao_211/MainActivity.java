package com.example.nguyenhuuthao_211;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText edtID, edtInputName, edtGia, edtSoLuong;
    Button btnTinhTien, btnAdd, btnThongKe;
    RadioButton rbVip;
    ListView lvCafe, lvKhach, lvKhachVip;
    ArrayAdapter<String> adapter = null;
    ArrayAdapter<String> adapterVip = null;
    ArrayAdapter<String> adapterNormal = null;
    ArrayList<String> arrCafe = new ArrayList<String >();

    ArrayList<String> arrVip = new ArrayList<String >();
    ArrayList<String> arrNormal = new ArrayList<String >();
    int soLuong;
    float gia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        btnTinhTien.setOnClickListener(v->{
            try {
                soLuong = Integer.parseInt(String.valueOf(edtSoLuong.getText()));
                gia = Float.parseFloat(String.valueOf(edtGia.getText()));
                Toast.makeText(this, "Thành tiền: " + String.valueOf(thanhTien()),Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Khong de trong",Toast.LENGTH_LONG).show();
            }

        });

        btnAdd.setOnClickListener(v->{
            addWork();
            setDataCafe();
        });

        btnThongKe.setOnClickListener(v -> {
            openDialog(Gravity.CENTER);
        });

    }

    private void getWidget() {
        edtID = findViewById(R.id.edtID);
        edtInputName = findViewById(R.id.edtInputName);
        btnTinhTien = findViewById(R.id.button1);
        btnAdd = findViewById(R.id.button2);
        btnThongKe = findViewById(R.id.button3);
        rbVip = findViewById(R.id.rbSecond);
        edtGia = findViewById(R.id.edtGia);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        lvCafe = findViewById(R.id.lvCafe);

    }

    private float thanhTien() {
        if (rbVip.isChecked()) {
            return (float) (soLuong*gia*0.8);
        } else {
            return soLuong*gia;
        }
    }

    private void setDataCafe() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrCafe);
        lvCafe.setAdapter(adapter);

        edtInputName.setText("");
        edtGia.setText("");
        edtSoLuong.setText("");
        edtID.setText("");
        rbVip.setChecked(false);
    }

    private void addWork() {
        if (rbVip.isChecked()) {
            arrVip.add("Mã bàn: " + edtID.getText() + " - Tên cafe" + edtInputName.getText() + " - Giá" + gia + " - Số lượng" + soLuong + " - "  + "Khách Vip" + "Doanh thu: " + thanhTien());
            arrCafe.add("Mã bàn: " + edtID.getText() + " - Tên cafe" + edtInputName.getText() + " - Giá" + gia + " - Số lượng" + soLuong + " - "  + "Khách Vip");
        } else {
            arrCafe.add(edtID.getText() + " - " + edtInputName.getText() + gia + soLuong);
            arrNormal.add("Mã bàn: " + edtID.getText() + " - Tên cafe" + edtInputName.getText() + " - Giá" + gia + " - Số lượng" + soLuong + " - "  + thanhTien());
        }
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void openDialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        lvKhach = dialog.findViewById(R.id.lvKhach);
        lvKhachVip = dialog.findViewById(R.id.lvKhachVip);

        adapterVip = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrVip);
        lvKhachVip.setAdapter(adapterVip);
        adapterNormal = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrNormal);
        lvKhach.setAdapter(adapterNormal);

        dialog.show();
    }

}