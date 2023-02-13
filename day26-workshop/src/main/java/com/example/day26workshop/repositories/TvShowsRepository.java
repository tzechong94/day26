package com.example.day26workshop.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import static com.example.day26workshop.Constants.*;


@Repository
public class TvShowsRepository {
    

    @Autowired
    private MongoTemplate template;

    /*
     * db.tv.find( { language: "English "})
     */

     public List<Document> findTvShowsByLanguage(String lang) {
        //create a criteria
        // language: {$regex: 'english', $options: i}
        Criteria criteria = Criteria.where(FIELD_LANGUAGE).regex(lang, "i"); 

        // create a query
        Query query = Query.query(criteria);
        
        // perform the query
        return template.find(query, Document.class, COLLECTION_TV);
     }

     public List<String> findTypes() {
        List<String> types = template.findDistinct(
            new Query(), FIELD_TYPE, COLLECTION_TV, String.class);
        return types;
    }

    // db.tv.find({
    //     type: {
    //         $regex: 'Animation',
    //         $options: 'i'
    //     }
    // })
    // .sort({"rating.average": -1})
    // .projection({_id: 0, id: 1, name: 1, "rating.average": 1})
    // .limit(20)

    public List<Document> findTvShowsByType(String type) {
        return findTvShowsByType(type, 20, 0);
    }
    public List<Document> findTvShowsByType(String type, int limit, int skip) {
        Criteria criteria = Criteria.where(FIELD_TYPE).regex(type, "i");
        Query query = Query.query(criteria)
                        .with(Sort.by(Direction.DESC, FIELD_RATING_AVERAGE))
                        .limit(limit)
                        .skip(skip);
        
        query.fields()
            .exclude(FIELD_UNDERSCORE_ID)
            .include(FIELD_ID, FIELD_NAME, FIELD_RATING_AVERAGE);

        return template.find(query, Document.class, COLLECTION_TV);
    }
}
