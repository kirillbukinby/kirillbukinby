package pages;

import elements.*;
import java.sql.*;

public class DatabasePage {

    private DatabaseElements databaseElements;

    public DatabasePage() throws SQLException {
        this.databaseElements = new DatabaseElements();
    }

    public DatabaseElements getDatabaseElements() {
        return databaseElements;
    }

    public void close() {
        databaseElements.close();
    }
}