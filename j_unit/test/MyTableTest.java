package ru.fizteh.fivt.students.elina_denisova.j_unit.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.fizteh.fivt.storage.strings.Table;
import ru.fizteh.fivt.storage.strings.TableProvider;
import ru.fizteh.fivt.storage.strings.TableProviderFactory;
import ru.fizteh.fivt.students.elina_denisova.j_unit.base.MyTableProviderFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class MyTableTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    public String path = folder.newFolder("test").getAbsolutePath();
    public Table table;

    @Before
    public void initTable() throws IOException {
        TableProviderFactory factory = new MyTableProviderFactory();
        TableProvider provider = factory.create(path);
        table = provider.createTable("table");
        if (table == null) table = provider.getTable("table");
    }

   /* @Test
    public void testGetName() {
        assertEquals("table", table.getName());
    }*/

    @Test (expected = IllegalArgumentException.class)
    public void putNull() {
        table.put(null, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getNull() {
        table.get(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeNull() {
        table.remove(null);
    }

    @Test
    public void testPutAndGet() {
        table.put("1", "2");
        assertEquals("2", table.get("1"));
        assertEquals("2", table.put("1", "3"));
        assertEquals("3", table.get("1"));
    }

    @Test
    public void testPutAndRemove() {
        table.put("1", "2");
        assertEquals("2", table.remove("1"));
        assertNull(table.get("1"));
    }

    @Test
    public void testSize() {
        table.remove("1");
        table.remove("3");
        int count = table.size();
        table.put("1", "2");
        table.put("3", "4");
        table.put("3", "5");
        assertEquals(count + 2 , table.size());
        table.remove("1");
        table.remove("1");
        table.remove("3");
        assertEquals(count, table.size());
    }



    @Test
    public void testRollBack() {
        assertEquals(0, table.rollback());
        table.put("1", "2");
        table.put("2", "3");
        table.put("3", "4");
        table.remove("1");
        table.put("1", "5");
        assertEquals(5, table.rollback());
    }

    @Test
    public void testCommit() {
        assertEquals(0, table.commit());
        table.put("1", "2");
        table.put("2", "3");
        table.put("3", "4");
        table.remove("3");
        assertEquals(4, table.commit());
    }

    @Test
    public void testCommitAndRollback() {
        table.put("1", "2");
        table.put("2", "3");
        table.put("3", "4");
        assertEquals(3, table.commit());
        table.remove("1");
        table.remove("2");
        assertNull(table.get("1"));
        assertNull(table.get("2"));
        assertEquals(2, table.rollback());
        assertEquals("2", table.get("1"));
        assertEquals("3", table.get("2"));
        table.remove("1");
        assertEquals(1, table.commit());
        assertEquals(2, table.size());
        table.put("1", "2");
        assertEquals(3, table.size());
        assertEquals(1, table.rollback());
        assertEquals(2, table.size());
        assertEquals(null, table.get("1"));
    }
}
