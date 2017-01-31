package com.daepstein.marvel.marvelcharacters.Interface;

import com.daepstein.marvel.marvelcharacters.model.CharacterModel;
import com.daepstein.marvel.marvelcharacters.model.ServiceResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by daepstein on 1/30/2017.
 */

public interface RetroInterface {

    @GET("/v1/public/characters")
    Call<ServiceResponse<CharacterModel>> listCharacters(@Query("limit") int limit
            , @Query("offset") int offset
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature
            , @Query("name") String name
            , @Query("modifiedSince") Date modifiedSince
            , @Query("comics") String comics
            , @Query("series") String series
            , @Query("events") String events
            , @Query("orderBy") String orderBy);


    @GET("/v1/public/characters")
    Call<ServiceResponse<CharacterModel>> listCharactersShort(@Query("limit") int limit
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);

    @GET("/v1/public/characters")
    Call<ServiceResponse<CharacterModel>> listCharactersFull( @Query("limit") int limit
            , @Query("offset") int offset
            ,@Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature);


}
