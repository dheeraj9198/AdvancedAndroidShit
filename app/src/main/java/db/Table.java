package db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by dheeraj on 15/9/15.
 */
@DatabaseTable(tableName = "Table")
public class Table {

    @DatabaseField(id = true,index = true)
    private Long id;

    @DatabaseField(canBeNull = false)
    private String naam;

    public Table(){}

    public Table(Long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
