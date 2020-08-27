package com.msc.microservices.common.util.sql;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * sql where条件对象
 *
 * @author ty
 */
public final class Where {
    private final StringBuilder whereSql;
    WhereEnum whereEnum;
    /**
     * 是否为复合where条件
     */
    private boolean composite = false;

    public Where() {
        this("", WhereEnum.AND);
    }

    public Where(String where) {
        this(where, WhereEnum.AND);
    }

    Where(String where, WhereEnum whereEnum) {
        Validate.notNull(where, "where can't be null");
        whereSql = new StringBuilder(where);
        this.whereEnum = whereEnum;
    }

    public Where and(String and) {
        Validate.validState(StringUtils.isNotBlank(and), "and can't be empty neither blank");
        composite = true;
        whereSql.append(StringUtils.isNotBlank(whereSql.toString()) ? " AND " : "").append(and);
        return this;
    }

    public Where and(boolean condition, String and) {
        if (condition) {
            and(and);
        }
        return this;
    }

    public Where or(String or) {
        composite = true;
        whereSql.append(StringUtils.isNotBlank(whereSql.toString()) ? " OR " : "").append(or);
        return this;
    }

    public Where or(boolean condition, String or) {
        if (condition) {
            or(or);
        }
        return this;
    }

    String build() {
        if (composite) {
            return "(" + whereSql.toString() + ")";
        } else {
            return whereSql.toString();
        }
    }

    enum WhereEnum {
        /**
         * and
         */
        AND,
        /**
         * or
         */
        OR
    }
}
