package com.example.cst338_p2_movie_schmovies;
import static com.example.cst338_p2_movie_schmovies.MainActivity.MOVIE_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst338_p2_movie_schmovies.DB.AppDataBase;
import com.example.cst338_p2_movie_schmovies.DB.DAO;


public class gMovie_View extends AppCompatActivity {


    private TextView movieTitleField;
    private TextView movieSynopsisField;
    private ImageView movieBannerField;

    private DAO DAO;

    private String movieTitle;
    private String movieSynopsis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
        getDatabase();
        wireUpDisplay();
    }

    private void wireUpDisplay() {
        movieTitleField = findViewById(R.id.GuestMovieTitleHeader);
        movieSynopsisField = findViewById(R.id.GuestMovieSynopsis);
        movieBannerField = findViewById(R.id.GuestMovieBannerImage);
//        movieTitleField.setText(R.string.titleHolder);
//        movieSynopsisField.setText(R.string.textHolder);

        Intent mIntent = getIntent();
        int movieId = mIntent.getIntExtra(MOVIE_KEY,-1);
        Movie movie = DAO.getMovieById(movieId);

        if (movie != null) {
//            Toast.makeText(this, movie.getSynopsis(), Toast.LENGTH_SHORT).show();
            String movieName = movie.getMovieName();
            String synopsis = movie.getSynopsis();
            int image = movie.getImage();
            if (movieName != null) {
                movieTitleField.setText(movieName);
            }else {
                Toast.makeText(this, "OOPS No Title", Toast.LENGTH_SHORT).show();
            }
            if (synopsis != null) {
                movieSynopsisField.setText(synopsis);
            }else {
                Toast.makeText(this, "OOPS No text", Toast.LENGTH_SHORT).show();
            }
            if (image == 0) {
                movieBannerField.setImageResource(image);
            }else {
                Toast.makeText(this, "OOPS No image", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "OOPS No Movie", Toast.LENGTH_SHORT).show();
        }





//        movieTitle = findViewById(R.id.);
//        movieSynopsis = findViewById(R.id.)



    }

    private void getDatabase() {
        DAO= Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .MovieSchmovieDAO();

    }

    public Intent intentFactory(Context context, int movieId) {
        Intent intent = new Intent(this, gMovie_View.class);
//        intent.putExtra(User_ID_KEY, userId);
        intent.putExtra(MOVIE_KEY, movieId);
        return intent;
    }
}