package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleViewAdapter
        extends RecyclerView.Adapter<ArticleViewHolder> {

  private List<Article> articles;

  public ArticleViewAdapter(List<Article> articles) {
    this.articles = articles;
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(
          @NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.contact_list, parent, false);

    return new ArticleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(
          @NonNull ArticleViewHolder holder, int position) {

    Article article = articles.get(position);

    holder.tvArticleId.setText("ID: " + article.getId());
    holder.tvArticleTitle.setText("Tiêu đề: " + article.getTitle());
    holder.tvArticleContent.setText("Nội dung: " + article.getContent());
    holder.tvArticleImageUrl.setText(
            "URL hình ảnh: " + article.getImage_url());
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }

  public void update(List<Article> newArticles) {
    articles = newArticles;
    notifyDataSetChanged();
  }
}