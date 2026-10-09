package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

  TextView tvArticleId;
  TextView tvArticleTitle;
  TextView tvArticleContent;
  TextView tvArticleImageUrl;

  public ArticleViewHolder(View itemView) {
    super(itemView);

    tvArticleId = itemView.findViewById(R.id.tvArticleId);
    tvArticleTitle = itemView.findViewById(R.id.tvArticleTitle);
    tvArticleContent = itemView.findViewById(R.id.tvArticleContent);
    tvArticleImageUrl = itemView.findViewById(R.id.tvArticleImageUrl);
  }
}
