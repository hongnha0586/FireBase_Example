package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowDataActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView rvArticles;
    ArticleViewAdapter adapter;
    List<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        db = FirebaseFirestore.getInstance();

        rvArticles = findViewById(R.id.rvArticles);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));

        articles = new ArrayList<>();
        adapter = new ArticleViewAdapter(articles);
        rvArticles.setAdapter(adapter);

        docDanhSachArticle();
    }

    private void docDanhSachArticle() {
        db.collection("articles").get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {
                                    articles.clear();

                                    for (QueryDocumentSnapshot q : task.getResult()) {
                                        Map<String, Object> data = q.getData();

                                        Article article = new Article(
                                                (String) data.get("id"),
                                                (String) data.get("title"),
                                                (String) data.get("content"),
                                                (String) data.get("image_url")
                                        );

                                        articles.add(article);
                                    }

                                    adapter.notifyDataSetChanged();

                                    if (articles.isEmpty()) {
                                        Toast.makeText(ShowDataActivity.this,
                                                "Chưa có bài viết nào",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(ShowDataActivity.this,
                                            "Lỗi đọc dữ liệu: "
                                                    + task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
    }
}
