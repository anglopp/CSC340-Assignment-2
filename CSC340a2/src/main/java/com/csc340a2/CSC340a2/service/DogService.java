package com.csc340a2.CSC340a2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

@Service
public class DogService {
    private static final String BASE_URL = "https://dog.ceo/api";
    private static final String DOG_API_URL = "https://dog.ceo/api/breeds/image/random";
    private final RestTemplate restTemplate = new RestTemplate();

    //Gets us a random dog image
    public String getRandomDogImage(){
        String jsonResponse = restTemplate.getForObject(DOG_API_URL, String.class);

        //Parsing the JSON to extract the Image's URL
        JSONObject jsonObject = new JSONObject(jsonResponse);

        //"message" contains the Image's URL
        return jsonObject.getString("message");
    }

    //Gets a list of all dog breeds
    public List<String> getAllBreeds(){
        String jsonResponse = restTemplate.getForObject(BASE_URL + "/breeds/list/all",String.class);
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject breedsObject = jsonObject.getJSONObject("message");

        List<String> breedList = new ArrayList<>();
        for(String breed : breedsObject.keySet()){
            if (breedsObject.getJSONArray(breed).isEmpty()){
                breedList.add(breed);
            } else {
                JSONArray subBreeds = breedsObject.getJSONArray(breed);
                for(int i = 0; i < subBreeds.length(); i++){
                    breedList.add(breed + " " + subBreeds.getString(i));
                }
            }
        }
        return breedList;
    }

    //Gets a random Image of the Specified Breed
    public String getBreedImage(String breed){
        String formattedBreed = breed.toLowerCase().replace(" ", "/");

        String url = BASE_URL + "/breed/" + formattedBreed + "/images/random";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = new JSONObject(jsonResponse);

        //Returns an Image of the Specified Breed
        return jsonObject.getString("message");
    }
}