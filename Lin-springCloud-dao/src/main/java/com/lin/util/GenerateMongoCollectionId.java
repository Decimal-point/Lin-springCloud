package com.lin.util;
import com.lin.annotation.Sequence;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class GenerateMongoCollectionId {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Long getNextId(Class<?> clazz) {
        return nextId(_getCollectionName(clazz));
    }

    public String getNextId(String seq_name) {
        String sequence_collection = "seq";
        String sequence_field = "seq";

        DBCollection seq = this.mongoTemplate
                .getCollection(sequence_collection);

        DBObject query = new BasicDBObject();
        query.put("_id", seq_name);

        DBObject change = new BasicDBObject(sequence_field, Integer.valueOf(1));
        DBObject update = new BasicDBObject("$inc", change);

        DBObject res = seq.findAndModify(query, new BasicDBObject(),
                new BasicDBObject(), false, update, true, true);
        return res.get(sequence_field).toString();
    }

    private String _getCollectionName(Class<?> clazz) {
        String className = clazz.getName();
        Integer lastIndex = Integer.valueOf(className.lastIndexOf("."));
        className = className.substring(lastIndex.intValue() + 1);

        return StringUtils.uncapitalize(className);
    }


    public Long nextId(String collName) {
        Query query = new Query(Criteria.where("collectionName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        Sequence seqId = mongoTemplate.findAndModify(query, update, options, Sequence.class);
        return seqId.getSeqId();
    }


}
