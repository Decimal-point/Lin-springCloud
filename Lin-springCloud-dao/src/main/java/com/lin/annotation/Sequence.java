package com.lin.annotation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "sequence")
public class Sequence {
    @Id
    private String id;//主键

    @Field
    private String conllectionName;//连接名

    @Field
    private Long seqId;//序列值

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConllectionName() {
        return conllectionName;
    }

    public void setConllectionName(String conllectionName) {
        this.conllectionName = conllectionName;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }
}
