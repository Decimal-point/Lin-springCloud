package com.lin.service.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.*;
import java.util.regex.Pattern;

public class QueryMongoUtils {
    public final String DESC = "-1";
    public final String ASC = "1";
    public final String PLUS_REPLACE_TOKEN = "TOMTOP___PLUS____REPLACE___TOKEN";
    public final Integer PAGE_SIZE = Integer.valueOf(20);
    public final Integer PAGE_NUMBER = Integer.valueOf(1);
    public final Integer MAX_PAGE_SIZE = Integer.valueOf(5000);
    public final String WHERE = "$where";
    public final String OR = "$or";
    public final String AND = "$and";
    public final String GE = "$ge";
    public final String LE = "$le";
    public final String GT = "$gt";
    public final String LT = "$lt";
    public final String IN = "$in";
    public final String NOT_IN = "$not_in";
    public final String NE = "$ne";
    public final String LIKE = "$like";
    public final String LEFT_LIKE = "$left_like";
    public final String RIGHT_LIKE = "$right_like";
    public final String NOT_LIKE = "$not_like";
    public final String NOT_LEFT_LIKE = "$not_left_like";
    public final String NOT_RIGHT_LIKE = "$not_right_like";
    public final String NULL = "$null";
    public final String NOT_NULL = "$not_null";

    public Sort getRequestSort(Map<String, Object> sort) {
        String sortDirection = "-1";
        List<Sort.Order> oes = new ArrayList<>();
        if (null != sort) {
            Iterator<String> i$ = sort.keySet().iterator();
            while (i$.hasNext()) {
                String key = (String) i$.next();
                sortDirection = sort.get(key).toString();
                if (sortDirection.equals("-1")) {
                    Sort.Order o = new Sort.Order(Sort.Direction.fromString(Sort.Direction.DESC
                            .toString()), key);
                    oes.add(o);
                } else {
                    Sort.Order o = new Sort.Order(Sort.Direction.fromString(Sort.Direction.ASC
                            .toString()), key);
                    oes.add(o);
                }
            }
        }
        if (CollectionUtils.isEmpty(oes)) {
            Sort.Order o = new Sort.Order(
                    Sort.Direction.fromString(Sort.Direction.DESC.toString()), "id");
            oes.add(o);
        }
        return new Sort(oes);
    }

    public Pageable getPageRequest(Map<String, Object> pagination) {
        Integer pageSize = MAX_PAGE_SIZE;
        Integer pageNumber = PAGE_NUMBER;
        if ((null != pagination)
                && (null != pagination.get("pageSize"))
                && (StringUtils
                .isNumeric(pagination.get("pageSize").toString()))) {
            pageSize = new Integer(pagination.get("pageSize").toString());
        }
        if ((null != pagination)
                && (null != pagination.get("pageNumber"))
                && (StringUtils.isNumeric(pagination.get("pageNumber")
                .toString()))) {
            pageNumber = new Integer(pagination.get("pageNumber").toString()) - 1;
        }
        if (pageSize.intValue() > MAX_PAGE_SIZE.intValue()) {
            pageSize = MAX_PAGE_SIZE;
        }
        return new PageRequest(pageNumber, pageSize);
    }

    public List<String> getRequestFields(Map<String, Object> requestArgs) {
        List fields = new ArrayList();
        if (null != requestArgs.get("fields")) {
            fields = (List) requestArgs.get("fields");
        }
        return fields;
    }

    private Criteria _parseRequestRestrictionOr(Map<String, Object> query) {
        Criteria allOrCriteria = new Criteria();
        List<Criteria> criterias = new ArrayList<>();
        if (null != query) {
            for (String key : query.keySet()) {
                Object value = query.get(key);
                if (StringUtils.startsWith(key, "$and"))
                    criterias
                            .add(getRequestRestriction((Map<String, Object>) value));
                else {
                    criterias.addAll(_parseCriteria(key, value));
                }
            }
        }
        if (!criterias.isEmpty()) {
            allOrCriteria.orOperator((Criteria[]) criterias
                    .toArray(new Criteria[criterias.size()]));
        }

        return allOrCriteria;
    }

    private List<Criteria> _parseCriteria(String key, Object value) {
        if ("id".equals(key)) {
            key = "_id";
        }
        List<Criteria> criterias = new ArrayList<>();
        Map<String, Object> compareValue;
        if ((value instanceof Map)) {
            compareValue = (Map<String, Object>) value;
            for (String compare : compareValue.keySet()) {
                Object _compareValue = compareValue.get(compare);
                if ("$ge".equals(compare))
                    criterias.add(Criteria.where(key).gte(_compareValue));
                else if ("$le".equals(compare))
                    criterias.add(Criteria.where(key).lte(_compareValue));
                else if ("$gt".equals(compare))
                    criterias.add(Criteria.where(key).gt(_compareValue));
                else if ("$lt".equals(compare))
                    criterias.add(Criteria.where(key).lt(_compareValue));
                else if ("$in".equals(compare))
                    criterias.add(Criteria.where(key).in(
                            (Collection<Object>) _compareValue));
                else if ("$like".equals(compare))
                    criterias.add(Criteria.where(key).regex(
                            Pattern.compile(
                                    Pattern.quote((String) _compareValue), 2)));
                else if ("$left_like".equals(compare))
                    criterias
                            .add(Criteria
                                    .where(key)
                                    .regex(Pattern.compile(
                                            Pattern.quote((String) _compareValue
                                                    + "$"), 2)));
                else if ("$right_like".equals(compare))
                    criterias
                            .add(Criteria.where(key).regex(
                                    Pattern.compile(
                                            Pattern.quote("^"
                                                    + (String) _compareValue),
                                            2)));
                else if ("$not_like".equals(compare))
                    criterias.add(Criteria
                            .where(key)
                            .not()
                            .regex(Pattern.compile(
                                    Pattern.quote((String) _compareValue), 2)));
                else if ("$left_like".equals(compare))
                    criterias
                            .add(Criteria
                                    .where(key)
                                    .not()
                                    .regex(Pattern.compile(
                                            Pattern.quote((String) _compareValue
                                                    + "$"), 2)));
                else if ("$right_like".equals(compare))
                    criterias
                            .add(Criteria
                                    .where(key)
                                    .not()
                                    .regex(Pattern.compile(
                                            Pattern.quote("^"
                                                    + (String) _compareValue),
                                            2)));
                else if ("$ne".equals(compare))
                    criterias.add(Criteria.where(key).ne(_compareValue));
                else if ("$null".equals(compare))
                    criterias.add(Criteria.where(key).is(null));
                else if ("$not_null".equals(compare))
                    criterias.add(Criteria.where(key).not().is(null));
                else if ("$not_in".equals(compare))
                    criterias.add(Criteria.where(key).not()
                            .in((Collection<Object>) _compareValue));
                else if ("$where".equals(compare))
                    criterias.add(Criteria.where("$where").is(_compareValue));
            }
        } else {
            criterias.add(Criteria.where(key).is(value));
        }

        return criterias;
    }

    public Criteria getRequestRestriction(Map<String, Object> query) {
        Criteria allCriteria = new Criteria();
        List<Criteria> criterias = new ArrayList<Criteria>();
        if (null != query) {
            for (String key : query.keySet()) {
                if ("$or".equals(key)) {
                    Map<String, Object> orValues = (Map<String, Object>) query
                            .get(key);
                    criterias.add(_parseRequestRestrictionOr(orValues));
                } else {
                    Object value = query.get(key);
                    criterias.addAll(_parseCriteria(key, value));
                }
            }
        }
        if (!criterias.isEmpty()) {
            allCriteria.andOperator((Criteria[]) criterias
                    .toArray(new Criteria[criterias.size()]));
        }

        return allCriteria;
    }

    public Update _getRequestUpdate(Map<String, Object> updates) {
        Update update = new Update();
        updates.remove("id");
        updates.remove("_id");
        for (String key : updates.keySet()) {
            update.set(key, updates.get(key));
        }
        return update;
    }

}