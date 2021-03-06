package com.hilarysturges.c868;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mediaManager.db";
    public static final String TABLE_MUSIC = "music";
    public static final String COLUMN_ID_Mu = "_idMu";
    public static final String COLUMN_TYPE_Mu = "typeMu";
    public static final String COLUMN_TITLE_Mu = "titleMu";
    public static final String COLUMN_ARTIST_Mu = "artistMu";
    public static final String COLUMN_PRODUCER_Mu = "producerMu";
    public static final String COLUMN_DESC_Mu = "descriptionMu";
    public static final String COLUMN_NUM_TRACKS_Mu = "numTracksMu";
    public static final String COLUMN_LENGTH_Mu = "lengthMu";
    public static final String COLUMN_TRACKS_Mu = "tracksMu";
    public static final String COLUMN_COVER_Mu = "coverMu";
    public static final String COLUMN_TSTAMP_Mu = "timestampMu";
    public static final String TABLE_TRACKS = "tracks";
    public static final String COLUMN_ID_Tr = "_idTr";
    public static final String COLUMN_TITLE_Tr = "titleTr";
    public static final String COLUMN_M_ID_Tr = "musicIdTr";
    public static final String COLUMN_TSTAMP_Tr = "timestampTr";
    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_ID_Mo = "_idMo";
    public static final String COLUMN_TYPE_Mo = "typeMo";
    public static final String COLUMN_TITLE_Mo = "titleMo";
    public static final String COLUMN_DIRE_Mo = "directorMo";
    public static final String COLUMN_DESC_Mo = "descriptionMo";
    public static final String COLUMN_LENGTH_Mo = "lengthMo";
    public static final String COLUMN_ACTORS_Mo = "actorsMo";
    public static final String COLUMN_NUM_ACT_Mo = "numActorsMo";
    public static final String COLUMN_COVER_Mo = "coverMo";
    public static final String COLUMN_RATING_Mo = "ratingMo";
    public static final String COLUMN_SEQ_Mo = "sequenceMo";
    public static final String COLUMN_TSTAMP_Mo = "timestampMo";
    public static final String TABLE_ACTORS = "actors";
    public static final String COLUMN_ID_A = "_idA";
    public static final String COLUMN_NAME_A = "nameA";
    public static final String COLUMN_M_ID_A = "mediaIdA";
    public static final String COLUMN_TSTAMP_A = "timestampA";
    public static final String TABLE_TV_SHOWS = "tvShows";
    public static final String COLUMN_ID_Tv = "_idTv";
    public static final String COLUMN_TITLE_Tv = "titleTv";
    public static final String COLUMN_TYPE_Tv = "typeTv";
    public static final String COLUMN_DIRE_Tv = "directorTv";
    public static final String COLUMN_DESC_Tv = "descriptionTv";
    public static final String COLUMN_NUM_ACT_Tv = "numActorsTv";
    public static final String COLUMN_NUM_SEA_Tv = "numSeasonsTv";
    public static final String COLUMN_COVER_Tv = "coverTv";
    public static final String COLUMN_ACTORS_Tv = "actorsTv";
    public static final String COLUMN_SEAS_Tv = "seasonsTv";
    public static final String COLUMN_SEQ_Tv = "sequenceTv";
    public static final String COLUMN_TSTAMP_Tv = "timestampTv";
    public static final String TABLE_SEASONS = "seasons";
    public static final String COLUMN_ID_S = "_idS";
    public static final String COLUMN_TITLE_S = "titleS";
    public static final String COLUMN_T_ID_S = "tvIdS";
    public static final String COLUMN_TSTAMP_S = "timestampS";
    public static final String TABLE_SEQUENCE = "sequence";
    public static final String COLUMN_ID_SEQ = "_idSeq";
    public static final String COLUMN_BLANK_SEQ = "blankSeq";

    private Calendar cal = Calendar.getInstance();
    private Date curDate = new Date(cal.getTime().getTime());

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("database created");
        String counterQuery = "CREATE TABLE  IF NOT EXISTS " + TABLE_SEQUENCE + " (" + COLUMN_ID_SEQ + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BLANK_SEQ + " TEXT);";
        sqLiteDatabase.execSQL(counterQuery);

        String musicQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_MUSIC + " (" + COLUMN_ID_Mu + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TYPE_Mu + " INTEGER, " + COLUMN_TITLE_Mu + " TEXT, " + COLUMN_ARTIST_Mu + " TEXT, "
                + COLUMN_PRODUCER_Mu + " TEXT, " + COLUMN_DESC_Mu + " TEXT, " + COLUMN_NUM_TRACKS_Mu + " INTEGER, "
                + COLUMN_TSTAMP_Mu + " DATE, "
                + COLUMN_LENGTH_Mu + " INTEGER, "+ COLUMN_COVER_Mu + " BLOB, " + COLUMN_TRACKS_Mu + " INTEGER);";
        sqLiteDatabase.execSQL(musicQuery);

        String tracksQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_TRACKS + " (" + COLUMN_ID_Tr + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE_Tr + " TEXT, "  + COLUMN_M_ID_Tr + " INTEGER, " + COLUMN_TSTAMP_Tr + " DATE, "
                + "FOREIGN KEY (" + COLUMN_M_ID_Tr + ") REFERENCES " + TABLE_MUSIC + "(" + COLUMN_ID_Mu + "));";
        sqLiteDatabase.execSQL(tracksQuery);

        String moviesQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_MOVIES + " (" + COLUMN_ID_Mo + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TYPE_Mo + " INTEGER, " + COLUMN_TITLE_Mo + " TEXT, " + COLUMN_DIRE_Mo + " TEXT, "
                + COLUMN_DESC_Mo + " TEXT, " + COLUMN_LENGTH_Mo + " INTEGER, " + COLUMN_ACTORS_Mo + " INTEGER, "
                + COLUMN_SEQ_Mo + " INTEGER, " + COLUMN_TSTAMP_Mo + " DATE, "
                + COLUMN_NUM_ACT_Mo + " INTEGER, " + COLUMN_RATING_Mo + " TEXT, " + COLUMN_COVER_Mo + " BLOB, "
                + "FOREIGN KEY (" + COLUMN_SEQ_Mo + ") REFERENCES " + TABLE_SEQUENCE + "(" + COLUMN_ID_SEQ + "));";
        sqLiteDatabase.execSQL(moviesQuery);

        String actorsQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_ACTORS + " (" + COLUMN_ID_A + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME_A + " TEXT, " + COLUMN_M_ID_A + " INTEGER, " + COLUMN_TSTAMP_A + " DATE, "
                + "FOREIGN KEY (" + COLUMN_M_ID_A + ") REFERENCES " + TABLE_SEQUENCE + "(" + COLUMN_ID_SEQ + "));";
        sqLiteDatabase.execSQL(actorsQuery);

        String tvShowsQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_TV_SHOWS + " (" + COLUMN_ID_Tv + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE_Tv + " TEXT, " + COLUMN_TYPE_Tv + " INTEGER, " + COLUMN_DIRE_Tv + " TEXT, "
                + COLUMN_NUM_ACT_Tv + " INTEGER, " + COLUMN_NUM_SEA_Tv + " INTEGER, "
                + COLUMN_ACTORS_Tv + " INTEGER, " + COLUMN_SEAS_Tv + " INTEGER, " + COLUMN_SEQ_Tv + " INTEGER, "
                + COLUMN_DESC_Tv + " TEXT," + COLUMN_COVER_Tv + " BLOB, " + COLUMN_TSTAMP_Tv + " DATE, "
                + "FOREIGN KEY (" + COLUMN_SEQ_Tv + ") REFERENCES " + TABLE_SEQUENCE + "(" + COLUMN_ID_SEQ + "));";
        sqLiteDatabase.execSQL(tvShowsQuery);

        String seasonsQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_SEASONS + " (" + COLUMN_ID_S + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE_S + " TEXT, " + COLUMN_T_ID_S + " INTEGER, " + COLUMN_TSTAMP_S + " DATE, "
                + "FOREIGN KEY (" + COLUMN_T_ID_S + ") REFERENCES " + TABLE_TV_SHOWS + "(" + COLUMN_ID_Tv + "));";
        sqLiteDatabase.execSQL(seasonsQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSIC);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACKS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTORS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TV_SHOWS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SEASONS);
        onCreate(sqLiteDatabase);
    }

    public void addDateMusic (int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_MUSIC + " SET " + COLUMN_TSTAMP_Mu + " = " + curDate + " WHERE " + COLUMN_ID_Mu + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void addDateMovie (int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_MOVIES+ " SET " + COLUMN_TSTAMP_Mo + " = " + curDate + " WHERE " + COLUMN_ID_Mo + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void addDateTVShow (int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_TV_SHOWS + " SET " + COLUMN_TSTAMP_Tv + " = " + curDate + " WHERE " + COLUMN_ID_Tv + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void addDateTrack (int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_TRACKS + " SET " + COLUMN_TSTAMP_Tr + " = " + curDate + " WHERE " + COLUMN_ID_Tr + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void addDateActor (int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_ACTORS + " SET " + COLUMN_TSTAMP_A + " = " + curDate + " WHERE " + COLUMN_ID_A + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void addDateSeason (int _id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_SEASONS + " SET " + COLUMN_TSTAMP_S + " = " + curDate + " WHERE " + COLUMN_ID_S + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void incrementSequence(String title) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BLANK_SEQ, title);
        db.insert(TABLE_SEQUENCE, null, values);
        db.close();
    }

    public int getLastSequence() {
        int sequenceId;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SEQUENCE + " ORDER BY " + COLUMN_ID_SEQ + " DESC LIMIT 1;";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        sequenceId = c.getInt(c.getColumnIndex(COLUMN_ID_SEQ));
        c.close();
        return sequenceId;
    }

    public void addMusic(Music music, int numTracks) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int tracks = 0;
        if (numTracks!=0)
            tracks++;

        values.put(COLUMN_ARTIST_Mu, music.getArtist());
        values.put(COLUMN_TITLE_Mu, music.getTitle());
        values.put(COLUMN_PRODUCER_Mu, music.getProducer());
        values.put(COLUMN_DESC_Mu, music.getDescription());
        values.put(COLUMN_LENGTH_Mu, music.getLength());
        values.put(COLUMN_NUM_TRACKS_Mu, numTracks);
        values.put(COLUMN_TYPE_Mu, music.getType());
        values.put(COLUMN_COVER_Mu, bitmapToByte(music.getCover()));
        values.put(COLUMN_TRACKS_Mu, tracks);

        db.insert(TABLE_MUSIC,null, values);
        db.close();

    }

    public void addMovie(Movie movie, int numActors, int seq_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int actors = 0;
        if (numActors!=0)
            actors++;

        values.put(COLUMN_TITLE_Mo, movie.getTitle());
        values.put(COLUMN_DIRE_Mo, movie.getDirector());
        values.put(COLUMN_DESC_Mo, movie.getDescription());
        values.put(COLUMN_LENGTH_Mo, movie.getLength());
        values.put(COLUMN_RATING_Mo, movie.getRating());
        values.put(COLUMN_NUM_ACT_Mo, numActors);
        values.put(COLUMN_TYPE_Mo, movie.getType());
        values.put(COLUMN_COVER_Mo, bitmapToByte(movie.getCover()));
        values.put(COLUMN_ACTORS_Mo, actors);
        values.put(COLUMN_SEQ_Mo, seq_id);

        db.insert(TABLE_MOVIES,null, values);
        db.close();

    }

    public void addTVShow(TVShow tvShow, int numActors, int numSeasons, int seq_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int actors = 0;
        if (numActors!=0)
            actors++;
        int seasons = 0;
        if (numSeasons!=0)
            seasons++;

        values.put(COLUMN_TITLE_Tv, tvShow.getTitle());
        values.put(COLUMN_DIRE_Tv, tvShow.getDirector());
        values.put(COLUMN_DESC_Tv, tvShow.getDescription());
        values.put(COLUMN_NUM_ACT_Tv, numActors);
        values.put(COLUMN_NUM_SEA_Tv, numSeasons);
        values.put(COLUMN_TYPE_Tv, tvShow.getType());
        values.put(COLUMN_COVER_Tv, bitmapToByte(tvShow.getCover()));
        values.put(COLUMN_ACTORS_Tv, actors);
        values.put(COLUMN_SEAS_Tv, seasons);
        values.put(COLUMN_SEQ_Tv, seq_id);

        db.insert(TABLE_TV_SHOWS,null, values);
        db.close();

    }

    public void removeMusic (int ID) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String query = "SELECT " + COLUMN_ID_Mu + " FROM " + TABLE_MUSIC + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Mu))!=null) {
                listOfIds.add(c.getInt(c.getColumnIndex(COLUMN_ID_Mu)));
            } c.moveToNext();
        } c.close();

        if (listOfIds.contains(ID)) {
            db.execSQL("DELETE FROM " + TABLE_MUSIC + " WHERE " + COLUMN_ID_Mu + " = " + ID + ";");
        } else {System.out.println("ID not found");}

        db.close();
    }

    public void removeTracks (int music_id) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String query = "SELECT " + COLUMN_M_ID_Tr + " FROM " + TABLE_TRACKS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_M_ID_Tr))!=null) {
                listOfIds.add(c.getInt(c.getColumnIndex(COLUMN_M_ID_Tr)));
            } c.moveToNext();
        } c.close();

        if (listOfIds.contains(music_id)) {
            db.execSQL("DELETE FROM " + TABLE_TRACKS + " WHERE " + COLUMN_M_ID_Tr + " = " + music_id + ";");
        } else {System.out.println("music_id not found");}

        db.close();
    }

    public void removeMovie (int ID) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String query = "SELECT " + COLUMN_ID_Mo + " FROM " + TABLE_MOVIES + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Mo))!=null) {
                listOfIds.add(c.getInt(c.getColumnIndex(COLUMN_ID_Mo)));
            } c.moveToNext();
        } c.close();

        if (listOfIds.contains(ID)) {
            db.execSQL("DELETE FROM " + TABLE_MOVIES + " WHERE " + COLUMN_ID_Mo + " = " + ID + ";");
        } else {System.out.println("ID not found");}

        db.close();
    }

    public void removeActors (int media_id) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String query = "SELECT " + COLUMN_M_ID_A + " FROM " + TABLE_ACTORS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_M_ID_A))!=null) {
                listOfIds.add(c.getInt(c.getColumnIndex(COLUMN_M_ID_A)));
            } c.moveToNext();
        } c.close();

        if (listOfIds.contains(media_id)) {
            db.execSQL("DELETE FROM " + TABLE_ACTORS + " WHERE " + COLUMN_M_ID_A + " = " + media_id + ";");
        } else {System.out.println("media_id not found");}

        db.close();
    }

    public void removeTVShow (int ID) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String query = "SELECT " + COLUMN_ID_Tv + " FROM " + TABLE_TV_SHOWS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Tv))!=null) {
                listOfIds.add(c.getInt(c.getColumnIndex(COLUMN_ID_Tv)));
            } c.moveToNext();
        } c.close();

        if (listOfIds.contains(ID)) {
            db.execSQL("DELETE FROM " + TABLE_TV_SHOWS + " WHERE " + COLUMN_ID_Tv + " = " + ID + ";");
        } else {System.out.println("ID not found");}

        db.close();
    }

    public void removeSeasons (int tvShow_id) {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Integer> listOfIds = new ArrayList<>();
        String query = "SELECT " + COLUMN_T_ID_S + " FROM " + TABLE_SEASONS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_T_ID_S))!=null) {
                listOfIds.add(c.getInt(c.getColumnIndex(COLUMN_T_ID_S)));
            } c.moveToNext();
        } c.close();

        if (listOfIds.contains(tvShow_id)) {
            db.execSQL("DELETE FROM " + TABLE_SEASONS + " WHERE " + COLUMN_T_ID_S + " = " + tvShow_id + ";");
        } else {System.out.println("music_id not found");}

        db.close();
    }

    public Music getMusic (int _id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MUSIC + " WHERE " + COLUMN_ID_Mu + " = " + _id + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if (!c.isAfterLast()) {
            Music music = new Music(c.getInt(c.getColumnIndex(COLUMN_ID_Mu)), c.getString(c.getColumnIndex(COLUMN_TITLE_Mu)), c.getString(c.getColumnIndex(COLUMN_ARTIST_Mu)), c.getString(c.getColumnIndex(COLUMN_PRODUCER_Mu)), c.getInt(c.getColumnIndex(COLUMN_LENGTH_Mu)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Mu))), c.getInt(c.getColumnIndex(COLUMN_TYPE_Mu)), c.getString(c.getColumnIndex(COLUMN_DESC_Mu)));
            c.close();
            return music;
        } else {
            c.close();
            return null;
        }
    }

    public Movie getMovie (int _id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIES + " WHERE " + COLUMN_ID_Mo + " = " + _id + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if (!c.isAfterLast()) {
            Movie movie = new Movie(c.getInt(c.getColumnIndex(COLUMN_ID_Mo)), c.getString(c.getColumnIndex(COLUMN_TITLE_Mo)), c.getString(c.getColumnIndex(COLUMN_DIRE_Mo)), c.getInt(c.getColumnIndex(COLUMN_TYPE_Mo)), c.getString(c.getColumnIndex(COLUMN_DESC_Mo)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Mo))), c.getInt(c.getColumnIndex(COLUMN_LENGTH_Mo)), c.getString(c.getColumnIndex(COLUMN_RATING_Mo)), c.getInt(c.getColumnIndex(COLUMN_SEQ_Mo)));
            c.close();
            return movie;
        } else {
            c.close();
            return null;
        }
    }

    public TVShow getTVShow (int _id) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TV_SHOWS + " WHERE " + COLUMN_ID_Tv + " = " + _id + ";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if (!c.isAfterLast()) {
            TVShow tvShow = new TVShow(c.getInt(c.getColumnIndex(COLUMN_ID_Tv)), c.getString(c.getColumnIndex(COLUMN_TITLE_Tv)), c.getString(c.getColumnIndex(COLUMN_DIRE_Tv)), c.getInt(c.getColumnIndex(COLUMN_TYPE_Tv)), c.getString(c.getColumnIndex(COLUMN_DESC_Tv)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Tv))),c.getInt(c.getColumnIndex(COLUMN_SEQ_Tv)));
            c.close();
            return tvShow;
        } else {
            c.close();
            return null;
        }
    }

    public ArrayList<Music> musicToArray() {
        ArrayList<Music> musicArray = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MUSIC + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Mu))!=null) {
                Music music = new Music(c.getInt(c.getColumnIndex(COLUMN_ID_Mu)), c.getString(c.getColumnIndex(COLUMN_TITLE_Mu)), c.getString(c.getColumnIndex(COLUMN_ARTIST_Mu)), c.getString(c.getColumnIndex(COLUMN_PRODUCER_Mu)), c.getInt(c.getColumnIndex(COLUMN_LENGTH_Mu)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Mu))), c.getInt(c.getColumnIndex(COLUMN_TYPE_Mu)), c.getString(c.getColumnIndex(COLUMN_DESC_Mu)));
                musicArray.add(music);
            } c.moveToNext();
        } c.close();

        db.close();
        return musicArray;
    }

    public ArrayList<Track> tracksToArray() {
        ArrayList<Track> tracks  = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TRACKS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Tr))!=null) {
                Track track = new Track(c.getInt(c.getColumnIndex(COLUMN_ID_Tr)), c.getString(c.getColumnIndex(COLUMN_TITLE_Tr)), c.getInt(c.getColumnIndex(COLUMN_M_ID_Tr)));
                tracks.add(track);
            } c.moveToNext();
        } c.close();

        db.close();
        return tracks;
    }

    public ArrayList<Movie> moviesToArray() {
        ArrayList<Movie> movies = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIES + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Mo))!=null) {
                Movie movie = new Movie(c.getInt(c.getColumnIndex(COLUMN_ID_Mo)), c.getString(c.getColumnIndex(COLUMN_TITLE_Mo)), c.getString(c.getColumnIndex(COLUMN_DIRE_Mo)), c.getInt(c.getColumnIndex(COLUMN_TYPE_Mo)), c.getString(c.getColumnIndex(COLUMN_DESC_Mo)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Mo))), c.getInt(c.getColumnIndex(COLUMN_LENGTH_Mo)), c.getString(c.getColumnIndex(COLUMN_RATING_Mo)), c.getInt(c.getColumnIndex(COLUMN_SEQ_Mo)));
                movies.add(movie);
            } c.moveToNext();
        } c.close();

        db.close();
        return movies;
    }

    public ArrayList<Actor> actorsToArray() {
        ArrayList<Actor> actors = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ACTORS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_A))!=null) {
                Actor actor = new Actor(c.getInt(c.getColumnIndex(COLUMN_ID_A)), c.getString(c.getColumnIndex(COLUMN_NAME_A)), c.getInt(c.getColumnIndex(COLUMN_M_ID_A)));
                actors.add(actor);
            } c.moveToNext();
        } c.close();

        db.close();
        return actors;
    }

    public ArrayList<TVShow> tvShowsToArray() {
        ArrayList<TVShow> tvShows = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TV_SHOWS+ ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_Tv))!=null) {
                TVShow tvShow = new TVShow(c.getInt(c.getColumnIndex(COLUMN_ID_Tv)), c.getString(c.getColumnIndex(COLUMN_TITLE_Tv)), c.getString(c.getColumnIndex(COLUMN_DIRE_Tv)), c.getInt(c.getColumnIndex(COLUMN_TYPE_Tv)), c.getString(c.getColumnIndex(COLUMN_DESC_Tv)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Tv))),c.getInt(c.getColumnIndex(COLUMN_SEQ_Tv)));
                tvShows.add(tvShow);
            } c.moveToNext();
        } c.close();

        db.close();
        return tvShows;
    }

    public ArrayList<Season> seasonsToArray() {
        ArrayList<Season> seasons = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SEASONS + ";";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_ID_S))!=null) {
                Season season = new Season(c.getInt(c.getColumnIndex(COLUMN_ID_S)), c.getString(c.getColumnIndex(COLUMN_TITLE_S)), c.getInt(c.getColumnIndex(COLUMN_T_ID_S)));
                seasons.add(season);
            } c.moveToNext();
        } c.close();

        db.close();
        return seasons;
    }

    public Movie getLastMovie() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIES + " ORDER BY " + COLUMN_ID_Mo + " DESC LIMIT 1;";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        Movie movie = new Movie(c.getInt(c.getColumnIndex(COLUMN_ID_Mo)), c.getString(c.getColumnIndex(COLUMN_TITLE_Mo)), c.getString(c.getColumnIndex(COLUMN_DIRE_Mo)), c.getInt(c.getColumnIndex(COLUMN_TYPE_Mo)), c.getString(c.getColumnIndex(COLUMN_DESC_Mo)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Mo))), c.getInt(c.getColumnIndex(COLUMN_LENGTH_Mo)), c.getString(c.getColumnIndex(COLUMN_RATING_Mo)), c.getInt(c.getColumnIndex(COLUMN_SEQ_Mo)));
        close();

        return movie;

    }

    public Music getLastMusic() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MUSIC + " ORDER BY " + COLUMN_ID_Mu + " DESC LIMIT 1;";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        Music music = new Music(c.getInt(c.getColumnIndex(COLUMN_ID_Mu)), c.getString(c.getColumnIndex(COLUMN_TITLE_Mu)), c.getString(c.getColumnIndex(COLUMN_ARTIST_Mu)), c.getString(c.getColumnIndex(COLUMN_PRODUCER_Mu)),c.getInt(c.getColumnIndex(COLUMN_LENGTH_Mu)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Mu))), c.getInt(c.getColumnIndex(COLUMN_TYPE_Mu)), c.getString(c.getColumnIndex(COLUMN_DESC_Mu)));
        close();

        return music;

    }

    public TVShow getLastTVShow() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TV_SHOWS + " ORDER BY " + COLUMN_ID_Tv + " DESC LIMIT 1;";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        TVShow tvShow = new TVShow(c.getInt(c.getColumnIndex(COLUMN_ID_Tv)), c.getString(c.getColumnIndex(COLUMN_TITLE_Tv)), c.getString(c.getColumnIndex(COLUMN_DIRE_Tv)), c.getInt(c.getColumnIndex(COLUMN_TYPE_Tv)), c.getString(c.getColumnIndex(COLUMN_DESC_Tv)), getBitmapFromBytes(c.getBlob(c.getColumnIndex(COLUMN_COVER_Tv))),c.getInt(c.getColumnIndex(COLUMN_SEQ_Tv)));
        close();

        return tvShow;

    }

    public Track getLastTrack() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRACKS + " ORDER BY " + COLUMN_ID_Tr + " DESC LIMIT 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        Track track = new Track(c.getInt(c.getColumnIndex(COLUMN_ID_Tr)), c.getString(c.getColumnIndex(COLUMN_TITLE_Tr)), c.getInt(c.getColumnIndex(COLUMN_M_ID_Tr)));
        c.close();
        return track;
    }

    public Actor getLastActor() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ACTORS + " ORDER BY " + COLUMN_ID_A + " DESC LIMIT 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        Actor actor = new Actor(c.getInt(c.getColumnIndex(COLUMN_ID_A)), c.getString(c.getColumnIndex(COLUMN_NAME_A)), c.getInt(c.getColumnIndex(COLUMN_M_ID_A)));
        c.close();
        return actor;
    }

    public Season getLastSeason() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_SEASONS + " ORDER BY " + COLUMN_ID_S + " DESC LIMIT 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        Season season = new Season(c.getInt(c.getColumnIndex(COLUMN_ID_S)), c.getString(c.getColumnIndex(COLUMN_TITLE_S)), c.getInt(c.getColumnIndex(COLUMN_T_ID_S)));
        c.close();
        return season;
    }

    public void addTrack(String title, int music_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE_Tr, title);
        values.put(COLUMN_M_ID_Tr, music_id);

        db.insert(TABLE_TRACKS, null, values);
        db.close();

    }

    public void addActor(String name, int media_seq_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_A, name);
        values.put(COLUMN_M_ID_A, media_seq_id);

        db.insert(TABLE_ACTORS, null, values);
        db.close();

    }

    public void addSeason(String title, int tvShow_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE_S, title);
        values.put(COLUMN_T_ID_S, tvShow_id);

        db.insert(TABLE_SEASONS, null, values);
        db.close();

    }

    public void updateMusic(int _id, String newTitle, String newArtist, String newProducer, int newLength, String newDesc) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_MUSIC + " SET " + COLUMN_TITLE_Mu + " = " + "\'" + newTitle + "\', "
                + COLUMN_ARTIST_Mu + " = " + "\'" + newArtist + "\', "
                + COLUMN_LENGTH_Mu + " = " + newLength + ", "
                + COLUMN_PRODUCER_Mu + " = " + "\'" + newProducer + "\', "
                + COLUMN_DESC_Mu + " = " + "\'" + newDesc + "\'"
                + " WHERE " + COLUMN_ID_Mu + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void updateMovie(int _id, String newTitle, String newDire, int newLength, String newRating, String newDesc) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_MOVIES + " SET " + COLUMN_TITLE_Mo + " = " + "\'" + newTitle + "\', "
                + COLUMN_DIRE_Mo + " = " + "\'" + newDire + "\', "
                + COLUMN_LENGTH_Mo + " = " + newLength + ", "
                + COLUMN_RATING_Mo + " = " + "\'" + newRating + "\', "
                + COLUMN_DESC_Mo + " = " + "\'" + newDesc + "\'"
                + " WHERE " + COLUMN_ID_Mo + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void updateTVShow(int _id, String newTitle, String newDire, String newDesc) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_TV_SHOWS + " SET " + COLUMN_TITLE_Tv + " = " + "\'" + newTitle + "\', "
                + COLUMN_DIRE_Tv + " = " + "\'" + newDire + "\', "
                + COLUMN_DESC_Tv + " = " + "\'" + newDesc + "\'"
                + " WHERE " + COLUMN_ID_Tv + " = " + _id + ";";
        db.execSQL(query);
        db.close();
    }

    public void editTrack(int _id, String newTitle) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_TRACKS + " SET " + COLUMN_TITLE_Tr + " = "
                + "\'" + newTitle + "\'" + " WHERE " + COLUMN_ID_Tr + " = " + _id + ";";
        db.execSQL(query);
        db.close();

    }

    public void editActor(int _id, String newName) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_ACTORS + " SET " + COLUMN_NAME_A + " = "
                + "\'" + newName + "\'" + " WHERE " + COLUMN_ID_A + " = " + _id + ";";
        db.execSQL(query);
        db.close();

    }

    public void editSeason(int _id, String newTitle) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_SEASONS + " SET " + COLUMN_TITLE_S + " = "
                + "\'" + newTitle + "\'" + " WHERE " + COLUMN_ID_S + " = " + _id + ";";
        db.execSQL(query);
        db.close();

    }

    public ArrayList<Actor> getMovieActors() {
        ArrayList<Actor> actors = new ArrayList<>();
        ArrayList<Integer> movie_ids = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String queryActors = "SELECT * FROM " + TABLE_ACTORS + ";" ;
        String queryMovies = "SELECT " + COLUMN_SEQ_Mo + " FROM " + TABLE_MOVIES + ";" ;

        Cursor cM = db.rawQuery(queryMovies, null);
        cM.moveToFirst();

        Cursor cA = db.rawQuery(queryActors, null);
        cA.moveToFirst();

        while (!cM.isAfterLast()) {
            if (!cM.isNull(cM.getColumnIndex(COLUMN_SEQ_Mo))) {
                movie_ids.add(cM.getInt(cM.getColumnIndex(COLUMN_SEQ_Mo)));
                cM.moveToNext();
            }
        } cM.close();

        while (!cA.isAfterLast()) {
            if (!cA.isNull(cA.getColumnIndex(COLUMN_M_ID_A))) {
                if (movie_ids.contains(cA.getInt(cA.getColumnIndex(COLUMN_M_ID_A)))) {
                    Actor actor = new Actor(cA.getInt(cA.getColumnIndex(COLUMN_ID_A)), cA.getString(cA.getColumnIndex(COLUMN_NAME_A)), cA.getInt(cA.getColumnIndex(COLUMN_M_ID_A)));
                    actors.add(actor);
                    cA.moveToNext();
                } else {
                    cA.moveToNext();
                }
            }
        } cA.close();

        return actors;
    }

    public ArrayList<Actor> getTVShowActors() {
        ArrayList<Actor> actors = new ArrayList<>();
        ArrayList<Integer> movie_ids = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String queryActors = "SELECT * FROM " + TABLE_ACTORS + ";" ;
        String queryMovies = "SELECT " + COLUMN_SEQ_Tv + " FROM " + TABLE_TV_SHOWS + ";" ;

        Cursor cT = db.rawQuery(queryMovies, null);
        cT.moveToFirst();

        Cursor cA = db.rawQuery(queryActors, null);
        cA.moveToFirst();

        while (!cT.isAfterLast()) {
            if (!cT.isNull(cT.getColumnIndex(COLUMN_SEQ_Tv))) {
                movie_ids.add(cT.getInt(cT.getColumnIndex(COLUMN_SEQ_Tv)));
                cT.moveToNext();
            }
        } cT.close();

        while (!cA.isAfterLast()) {
            if (!cA.isNull(cA.getColumnIndex(COLUMN_M_ID_A))) {
                if (movie_ids.contains(cA.getInt(cA.getColumnIndex(COLUMN_M_ID_A)))) {
                    Actor actor = new Actor(cA.getInt(cA.getColumnIndex(COLUMN_ID_A)), cA.getString(cA.getColumnIndex(COLUMN_NAME_A)), cA.getInt(cA.getColumnIndex(COLUMN_M_ID_A)));
                    actors.add(actor);
                    cA.moveToNext();
                } else {
                    cA.moveToNext();
                }
            }
        } cA.close();

        return actors;
    }

    public static Bitmap getBitmapFromBytes(byte[] bytes) {
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0 ,bytes.length);
        }
        return null;
    }

    public byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byted = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byted);
        byte[] newByte = byted.toByteArray();
        return newByte;
    }
}
