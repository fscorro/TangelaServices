package com.tangela.domain.provider.orientdb;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;
import static org.joda.time.format.DateTimeFormat.forPattern;

@Component
public class QueryGenerator {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String BELONG_TO = "BelongTo";
    public static final String CLOSED_ROUND = "ClosedRound";
    public static final String FUNDING_TO = "FundingTo";
    public static final String LOCATION = "Location";
    public static final String MARKET = "Market";
    public static final String RESIDE_IN = "ResideIn";
    public static final String ROLE_IN = "RoleIn";
    public static final String ROLE_IN_STARTUP = "RoleInStartup";
    public static final String ROUND = "Round";
    public static final String STARTUP = "Startup";
    public static final String USER = "User";
    public static final String WORK_IN = "WorkIn";

    private String getStartups(List<String> markets, List<String> locations, Integer quality, DateTime createdAt) {
        boolean and = false;

        StringBuilder sb = new StringBuilder("select %s from " + QueryGenerator.STARTUP);

        if(markets != null && markets.size() > 0) {
            sb.append(format(" where out('" + QueryGenerator.WORK_IN + "').name IN [%s]", listToString(markets)));
            and = true;
        }

        if(locations != null && locations.size() > 0) {
            if(and) {
                sb.append(" and");
            } else {
                sb.append(" where");
            }
            sb.append(format(" out('" + QueryGenerator.RESIDE_IN + "').name IN (%s)", getCitiesFromLocation(locations)));
            and = true;
        }

        if(quality != null) {
            if(and) {
                sb.append(" and");
            } else {
                sb.append(" where");
            }
            sb.append(format(" quality >= %d", quality));
            and = true;
        }

        if(createdAt != null) {
            if(and) {
                sb.append(" and");
            } else {
                sb.append(" where");
            }
            sb.append(format(" createdAt >= '%s'", forPattern(PATTERN).print(createdAt)));
        }

        System.out.println(sb.toString());

        return sb.toString();
    }

    public String getStartupsQuery(List<String> markets, List<String> locations, Integer quality, DateTime createdAt) {
        String query = getStartups(markets, locations, quality, createdAt);
        return format(query, "*");
    }

    public String getUsersFromStartupsQuery(List<String> markets, List<String> locations, Integer quality, DateTime createdAt) {
        String query = getStartups(markets, locations, quality, createdAt);
        return format(query, "expand(in('" + QueryGenerator.ROLE_IN + "'))");
    }

    public String getDocumentByAngelIdAndClass(final Long angelId, String clazz) {
        return format("select * from %s where angelId = %d", clazz, angelId);
    }

    public String getCitiesFromLocation(List<String> locations) {
        return format("select name from (traverse in(\"BelongTo\") from (select * from Location where name IN [%s]) MAXDEPTH 3) where @class=\"City\"", listToString(locations));
    }

    private String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for(String item: list) {
            sb.append("'" + item + "',");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
