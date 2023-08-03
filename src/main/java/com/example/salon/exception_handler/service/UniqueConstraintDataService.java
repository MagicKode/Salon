package com.example.salon.exception_handler.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.metamodel.Type;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class UniqueConstraintDataService {

    private DataSource dataSource;
    private SessionFactory sessionFactory;

    private static final Map<String, String> tableAndEntityNames = new HashMap<>();
    private static final Map<String, Map<String, List<String>>> allUniqueConstraintsFromDb = new HashMap<>();
    private static final String SCHEMA = "public";
    public static final String UNDEFINED = "undefined";

    static {
        tableAndEntityNames.put(UNDEFINED, UNDEFINED);
        allUniqueConstraintsFromDb.put(UNDEFINED, Map.of(UNDEFINED, Collections.singletonList(UNDEFINED)));
    }

    @PostConstruct
    private void postConstruct() {
        getAllUniqueConstraintsDataFromDb();
    }

    public static Map<String, String> getTableAndEntityNames() {
        return tableAndEntityNames;
    }

    public static Map<String, Map<String, List<String>>> getAllUniqueConstraintsFromDb() {
        return allUniqueConstraintsFromDb;
    }

    private void getAllUniqueConstraintsDataFromDb() {
        MetamodelImplementor metamodel = (MetamodelImplementor) sessionFactory.getMetamodel();
        Set<Class<?>> entityClasses = metamodel.getEntities().stream()
                .map(Type::getJavaType)
                .collect(Collectors.toSet());
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            for (Class<?> entityClass : entityClasses) {
                AbstractEntityPersister entityClassMetadata = (AbstractEntityPersister) metamodel.entityPersister(entityClass.getName());
                String tableName = entityClassMetadata.getTableName();
                tableAndEntityNames.put(tableName, entityClass.getSimpleName());
                Map<String, String> dbAndEntityFieldNames = getDbAndEntityFieldNames(entityClassMetadata);
                Map<String, List<String>> currentTableConstraints = getCurrentTableConstraints(databaseMetaData, dbAndEntityFieldNames, tableName);
                allUniqueConstraintsFromDb.put(entityClass.getSimpleName(), currentTableConstraints);
            }
        } catch (SQLException e) {
            log.error("Exception in UniqueConstraintVerifyService.findAllUniqueConstraintsFromDb(): ", e);
        }
    }

    private static Map<String, List<String>> getCurrentTableConstraints(DatabaseMetaData databaseMetaData,
                                                                        Map<String, String> dbAndEntityFieldNames,
                                                                        String tableName) throws SQLException {
        ResultSet resultSet = databaseMetaData.getIndexInfo(null, SCHEMA, tableName, true, true);
        Map<String, List<String>> currentTableConstraints = new HashMap<>();
        while (resultSet.next()) {
            String indexName = resultSet.getString("index_name");
            String columnNameFromDb = resultSet.getString("column_name");
            String entityFieldName = dbAndEntityFieldNames.get(columnNameFromDb);

            if (indexName.endsWith("_pkey")) {
                continue;
            }

            List<String> columnNames = new ArrayList<>();
            columnNames.add(entityFieldName);

            currentTableConstraints.compute(indexName, (key, value) -> {
                if (value == null) {
                    return columnNames;
                }
                value.add(entityFieldName);
                return value;
            });
        }
        return currentTableConstraints;
    }

    private static Map<String, String> getDbAndEntityFieldNames(AbstractEntityPersister entityClassMetadata) {
        String[] entityFieldNames = entityClassMetadata.getPropertyNames();
        Map<String, String> dbAndEntityFieldNames = new HashMap<>();
        for (String entityFieldName : entityFieldNames) {
            String[] columnNamesFromDb = entityClassMetadata.getPropertyColumnNames(entityFieldName);
            dbAndEntityFieldNames.put(columnNamesFromDb[0], entityFieldName);
        }
        return dbAndEntityFieldNames;
    }
}
