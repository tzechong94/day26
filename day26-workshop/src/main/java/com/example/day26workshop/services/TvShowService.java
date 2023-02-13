package com.example.day26workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.day26workshop.models.TvShow;
import com.example.day26workshop.repositories.TvShowsRepository;

@Service
public class TvShowService {
    
    @Autowired
    private TvShowsRepository tvShowsRepo;

    public List<TvShow> findAllTvShowsByLanguage(String lang) {
        return tvShowsRepo.findTvShowsByLanguage(lang)
                .stream()
                .map(v -> TvShow.create(v))
                .toList();
    }

    public List<String> findAllTypes() {
        return tvShowsRepo.findTypes();
    }

    public List<TvShow> findAllTvShowsByType(String type) {
        return tvShowsRepo.findTvShowsByType(type)
                        .stream()
                        // .filter(v -> {
                        //     try {
                        //         TvShow.createSummary(v);
                        //         return true;
                        //     } catch (Exception ex) {
                        //         return false;
                        //     }
                        // })
                        .map(t -> TvShow.createSummary(t))
                        .toList();
    }

}
