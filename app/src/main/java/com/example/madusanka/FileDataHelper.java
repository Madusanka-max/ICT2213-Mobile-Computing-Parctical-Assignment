package com.example.madusanka;

import android.content.Context;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileDataHelper {
    private static final String STUDENTS_FILE = "quantum_students.dat";
    private static final String USERS_FILE = "quantum_users.dat";
    private static final String SEPARATOR = "|━|";
    private Context context;

    public FileDataHelper(Context context) {
        this.context = context;
    }

    public boolean saveStudent(String name, String email, String phone) {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            FileOutputStream fos = context.openFileOutput(STUDENTS_FILE, Context.MODE_APPEND);
            String data = name + SEPARATOR + email + SEPARATOR + phone + SEPARATOR + timestamp + "\n";
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveUser(String name, String age) {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            FileOutputStream fos = context.openFileOutput(USERS_FILE, Context.MODE_APPEND);
            String data = name + SEPARATOR + age + SEPARATOR + timestamp + "\n";
            fos.write(data.getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getAllStudents() {
        List<String> students = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(STUDENTS_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public String getFormattedStudents() {
        StringBuilder result = new StringBuilder();
        List<String> students = getAllStudents();
        
        if (students.isEmpty()) {
            return "[NO DATA FOUND]";
        }
        
        result.append("━━━ QUANTUM DATABASE ━━━\n\n");
        
        for (int i = 0; i < students.size(); i++) {
            String[] parts = students.get(i).split("\\" + SEPARATOR);
            if (parts.length >= 4) {
                result.append(String.format("[%03d] %s\n", i + 1, parts[0]));
                result.append(String.format("◆ EMAIL: %s\n", parts[1]));
                result.append(String.format("◆ PHONE: %s\n", parts[2]));
                result.append(String.format("◆ SAVED: %s\n", parts[3]));
                result.append("━━━━━━━━━━\n\n");
            }
        }
        
        return result.toString();
    }

    public boolean clearData() {
        try {
            FileOutputStream fos = context.openFileOutput(STUDENTS_FILE, Context.MODE_PRIVATE);
            fos.write("".getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getStudentCount() {
        return getAllStudents().size();
    }
}