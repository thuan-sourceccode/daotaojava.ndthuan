package com.spring.project.web.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.spring.project.web.log.AppLogger;
import com.spring.project.web.log.LoggerFactory;
import com.spring.project.web.log.LoggerType;
import com.spring.project.web.utility.StringUtils;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class AbstractRepository {

    @Autowired
    @Qualifier("dataSource")
    protected DataSource dataSource;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected static final AppLogger SQL_LOGGER = LoggerFactory.getLogger(LoggerType.SQL);

  
    protected List<String> listAttributeName(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
    }

  
    protected String getSimpleNameTable(Class<?> clazz) {
        return StringUtils.camelCaseToSnakeCase(clazz.getSimpleName()).toLowerCase();
    }

 
    protected String attributeNamesForSelect(Class<?> clazz) {
        List<String> listAttribute = listAttributeName(clazz);
        if (listAttribute == null || listAttribute.isEmpty()) {
            return "";
        }
        String attributeNames = listAttribute.stream().map(StringUtils::camelCaseToSnakeCase)
                .collect(Collectors.joining(","));
        return attributeNames;
    }

   
    protected String attributeNamesForSelectWithPrefix(Class<?> clazz, @NotNull String prefix, String... nameNotUses) {
        List<String> listAttribute = listAttributeName(clazz);
        if (listAttribute == null || listAttribute.isEmpty()) {
            return "";
        }
        //Covert CamelCase to SnakeCase
        String attributeNames = listAttribute.stream()
                .filter(s -> {
                    if (nameNotUses == null) {
                        return true;
                    }
                    for (String name : nameNotUses) {
                        if (name.equals(s)) {
                            return false;
                        }
                    }
                    return true;
                })
                .map(s -> prefix + "." + StringUtils.camelCaseToSnakeCase(s))
                .collect(Collectors.joining(","));
        return attributeNames;
    }

   
    protected String attributeNamesForSelect(Class<?> clazz, List<String> nameNotUses) {
        if (nameNotUses == null || nameNotUses.isEmpty()) {
            return attributeNamesForSelect(clazz);
        }
        List<String> listAttribute = listAttributeName(clazz);
        if (listAttribute.isEmpty()) return "";
        //Covert CamelCase to SnakeCase
        String attributeNames = listAttribute.stream().filter(attr -> {
            for (String name : nameNotUses) {
                if (name.equals(attr)) {
                    return false;
                }
            }
            return true;
        }).map(StringUtils::camelCaseToSnakeCase)
                .collect(Collectors.joining(";"));
        return attributeNames;
    }

    
    protected String attributeNames(Class<?> clazz, String first, String last) {
        List<String> listAttribute = listAttributeName(clazz);
        if (CollectionUtils.isEmpty(listAttribute)) {
            return "";
        }

        String attributeNames = listAttribute.stream().map(attr -> {
                    String snakeCaseAttr = StringUtils.camelCaseToSnakeCase(attr);
                    if (first != null) {
                        snakeCaseAttr = first + snakeCaseAttr;
                    }

                    if (last != null) {
                        snakeCaseAttr = snakeCaseAttr + last;
                    }

                    return snakeCaseAttr;
                }
        ).collect(Collectors.joining(","));

        return attributeNames;
    }

 
    protected String attributeNames(Class<?> clazz, List<String> nameNotUses, String first, String last) {
        if (nameNotUses == null || nameNotUses.isEmpty()) {
            return attributeNames(clazz, first, last);
        }
        List<String> listAttribute = listAttributeName(clazz);
        if (listAttribute == null || listAttribute.isEmpty()) {
            return "";
        }
        String attributeNames = listAttribute.stream().filter(attr -> {
            for (String name : nameNotUses) {
                if (name.equals(attr)) {
                    return false;
                }
            }
            return true;
        }).map(attr -> {
                    String snakeCaseAttr = StringUtils.camelCaseToSnakeCase(attr);
                    if (first != null) {
                        snakeCaseAttr = first + snakeCaseAttr;
                    }

                    if (last != null) {
                        snakeCaseAttr = snakeCaseAttr + last;
                    }

                    return snakeCaseAttr;
                }
        ).collect(Collectors.joining(","));

        return attributeNames;
    }

}
