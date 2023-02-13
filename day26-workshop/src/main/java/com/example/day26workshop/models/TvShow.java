package com.example.day26workshop.models;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.example.day26workshop.Constants.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TvShow {
    
    private int id;
    private String name;
    private String url;
    private float rating; 

    public static TvShow create(Document doc) {
        TvShow tvShow = new TvShow();
        tvShow.setId(doc.getInteger(FIELD_ID));
        tvShow.setUrl(doc.getString(FIELD_URL));
        tvShow.setName(doc.getString(FIELD_NAME));
        // tvShow.s(doc.getDouble(FIELD_RATING_AVERAGE));
        return tvShow;
    }

    public static TvShow createSummary(Document doc) {
        TvShow tvShow = new TvShow();
        tvShow.setId(doc.getInteger(FIELD_ID));
        tvShow.setName(doc.getString(FIELD_NAME));
        Document d = (Document) doc.get(FIELD_RATING);

        try {
            if (d.getDouble(FIELD_AVERAGE) != null)
                tvShow.setRating(d.getDouble(FIELD_AVERAGE).floatValue());
            else
                tvShow.setRating(0.0f);
        } catch (Exception ex) {
            tvShow.setRating(d.getInteger(FIELD_AVERAGE).floatValue());

        }
        // if (d.getDouble(FIELD_AVERAGE) == null) {
        // } else {
        // }
        return tvShow;
    }

}
