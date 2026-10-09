package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

  EditText etTitle, etContent, etImageUrl;
  Button btAdd, btShow;
  FirebaseFirestore db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FirebaseApp.initializeApp(this);
    db = FirebaseFirestore.getInstance();

    etTitle = findViewById(R.id.etTitle);
    etContent = findViewById(R.id.etContent);
    etImageUrl = findViewById(R.id.etImageUrl);
    btAdd = findViewById(R.id.btAdd);
    btShow = findViewById(R.id.btShow);

    btAdd.setOnClickListener(v -> themArticle());

    btShow.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
      startActivity(intent);
    });
  }

  private void themArticle() {
    String title = etTitle.getText().toString().trim();
    String content = etContent.getText().toString().trim();
    String imageUrl = etImageUrl.getText().toString().trim();

    if (title.isEmpty() || content.isEmpty()) {
      Toast.makeText(this, "Vui lòng nhập tiêu đề và nội dung",
              Toast.LENGTH_SHORT).show();
      return;
    }

    String id = db.collection("articles").document().getId();

    Article article = new Article(id, title, content, imageUrl);

    db.collection("articles").document(id).set(article)
            .addOnSuccessListener(unused -> {
              Toast.makeText(this, "Thêm bài viết thành công",
                      Toast.LENGTH_SHORT).show();

              etTitle.setText("");
              etContent.setText("");
              etImageUrl.setText("");
            })
            .addOnFailureListener(e ->
                    Toast.makeText(this, "Lỗi: " + e.getMessage(),
                            Toast.LENGTH_LONG).show());
  }
}
