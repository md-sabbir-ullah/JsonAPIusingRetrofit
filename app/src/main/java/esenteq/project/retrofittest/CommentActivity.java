package esenteq.project.retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {
    private TextView tvResult;
    private EditText etPostId;
    private Button btnGoNext, btnGoBack, btnCheckComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        tvResult = findViewById(R.id.tv_comment_resultID);
        btnGoBack = findViewById(R.id.btn_goBackID);
        btnGoNext = findViewById(R.id.btn_goNextID);
        etPostId = findViewById(R.id.et_comment_idofpostID);
        btnCheckComment = findViewById(R.id.btn_checkCommentID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        btnCheckComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCheckComment.setBackgroundResource(R.color.colorBlack);
                tvResult.setText("");
                if(etPostId.getText().toString().isEmpty()){
                    tvResult.setText("Please provide post-id...");
                    btnCheckComment.setBackgroundResource(R.color.colorPrimaryDark);
                    return;
                }
                int postIdInt = Integer.parseInt(etPostId.getText().toString());

                Call<List<Comment>> call = jsonPlaceHolderApi.getComments(postIdInt);
                call.enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if(!response.isSuccessful()){
                            tvResult.setText("Code: " + response.code());
                            return;
                        }

                        List<Comment> comments = response.body();
                        for(Comment comment : comments){
                            String content = "";
                            content += "Comment ID: " + comment.getId() + "\n";
                            content += "Post ID: " + comment.getPostId() + "\n";
                            content += "Name: " + comment.getName() + "\n";
                            content += "Email: " + comment.getEmail() + "\n";
                            content += "Description: " + comment.getText() + "\n\n";
                            tvResult.append(content);
                        }
                        btnCheckComment.setBackgroundResource(R.color.colorPrimaryDark);
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        tvResult.setText("Response Failure: " + t.getMessage());
                        btnCheckComment.setBackgroundResource(R.color.colorPrimaryDark);
                    }
                });
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGoBack.setBackgroundResource(R.color.colorBlack);
                startActivity(new Intent(CommentActivity.this, MainActivity.class));
                finish();
            }
        });
        btnGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGoNext.setBackgroundResource(R.color.colorBlack);
//                startActivity(new Intent(Com.this, CommentActivity.class));
//                finish();
            }
        });
    }
}
