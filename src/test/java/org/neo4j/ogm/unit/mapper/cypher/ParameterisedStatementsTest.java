/*
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 */

package org.neo4j.ogm.unit.mapper.cypher;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import org.neo4j.ogm.cypher.statement.ParameterisedStatement;
import org.neo4j.ogm.cypher.statement.ParameterisedStatements;
import org.neo4j.ogm.session.request.strategy.VariableDepthQuery;

import static org.junit.Assert.assertEquals;

/**
 * @author Vince Bickers
 */
public class ParameterisedStatementsTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testStatement() throws Exception {

        List<ParameterisedStatement > statements = new ArrayList<>();
        statements.add(new VariableDepthQuery().findOne(123L, 1));

        String cypher = mapper.writeValueAsString(new ParameterisedStatements(statements));

        assertEquals("{\"statements\":[{\"statement\":\"MATCH (n) WHERE id(n) = { id } WITH n MATCH p=(n)-[*0..1]-(m) RETURN collect(distinct p)\",\"parameters\":{\"id\":123},\"resultDataContents\":[\"graph\"],\"includeStats\":false}]}", cypher);

    }

}
