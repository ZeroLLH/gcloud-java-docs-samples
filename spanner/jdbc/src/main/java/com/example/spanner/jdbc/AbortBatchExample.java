/*
 * Copyright 2020 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.spanner.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class AbortBatchExample {

  static void abortBatch() throws SQLException {
    // TODO(developer): Replace these variables before running the sample.
    String projectId = "my-project";
    String instanceId = "my-instance";
    String databaseId = "my-database";
    abortBatch(projectId, instanceId, databaseId);
  }

  // A batch of DML or DDL statements can be aborted using the 'ABORT BATCH' statement.
  static void abortBatch(String projectId, String instanceId, String databaseId)
      throws SQLException {
    String connectionUrl =
        String.format(
            "jdbc:cloudspanner:/projects/%s/instances/%s/databases/%s",
            projectId, instanceId, databaseId);
    try (Connection connection = DriverManager.getConnection(connectionUrl);
        Statement statement = connection.createStatement()) {
      statement.execute("START BATCH DML");
      statement.execute(
          "INSERT INTO Singers (SingerId, FirstName, LastName)\n"
              + "VALUES (14, 'Aayat', 'Curran')");
      statement.execute(
          "INSERT INTO Singers (SingerId, FirstName, LastName)\n"
              + "VALUES (15, 'Tudor', 'Mccarthy')");
      statement.execute(
          "INSERT INTO Singers (SingerId, FirstName, LastName)\n" + "VALUES (16, 'Cobie', 'Webb')");
      statement.execute("ABORT BATCH");
      System.out.println("Aborted DML batch");
    }
  }
}
