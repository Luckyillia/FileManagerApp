package com.example.filemanagerapp;

import android.os.Bundle;

import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFile;
    private FileAdapter adapter;
    private List<File> fileList;
    private TextView textInFile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textInFile = findViewById(R.id.textInFile);

        rvFile  = findViewById(R.id.rvFile);
        fileList = new ArrayList<>();
        adapter     = new FileAdapter(fileList);
        rvFile.setAdapter(adapter);
        rvFile.setLayoutManager(new LinearLayoutManager(this));
    }

    private boolean checkValues (String text){
        return text.isEmpty();
    }
    private void saveToFile(String dane, String FILE_NAME) {
        FileOutputStream fos = null;

        try {
            // Otwórz strumień wyjściowy do pliku w pamięci wewnętrznej
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(dane.getBytes());

            Toast.makeText(this, "Zapisano do pliku " + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd zapisu!", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close(); // Zawsze zamykaj strumień!
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void loadNote(String FILE_NAME) {
        FileInputStream fis = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // Otwórz strumień wejściowy z pliku w pamięci wewnętrznej
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            textInFile.setText(stringBuilder.toString());
            Toast.makeText(this, "Wczytano z pliku " + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd odczytu lub plik nie istnieje!", Toast.LENGTH_SHORT).show();
        } finally {
            if (fis != null) {
                try {
                    fis.close(); // Zawsze zamykaj strumień!
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}