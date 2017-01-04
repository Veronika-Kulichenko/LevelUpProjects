package com.levelup.table.dao.impl.xml;

import com.levelup.table.dao.DAO;
import com.levelup.table.dao.dataproviders.FileDataProvider;
import com.levelup.table.dao.impl.json.StreetJsonDAOImpl;
import com.levelup.table.entity.Entity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Veronika Kulichenko on 03.01.17.
 */
public abstract class AbstractXMLDAO<T extends Entity> implements DAO<T> {

  protected final FileDataProvider fileDataProvider;
  private static final Logger LOG = Logger.getLogger(StreetJsonDAOImpl.class.getName());
  private static long id = 1;

  private final String HEADER_XML;
  private final String TAIL_XML;

  public AbstractXMLDAO(final FileDataProvider fileDataProvider, final String entityName) {
    this.fileDataProvider = fileDataProvider;
    this.HEADER_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<" + entityName + ">";
    this.TAIL_XML = "</" + entityName + ">";
  }

  @Override
  public void create(final T t) {
    RandomAccessFile file = fileDataProvider.getDataFile();
    try {
      if ((t.getId() == null) || (t.getId() == 0L)) {
        t.setId(id++);
      }
      if (file.length() < (HEADER_XML.length() + TAIL_XML.length())) {
        file.write((HEADER_XML + "\n").getBytes());
      } else {
        file.seek(file.length() - (TAIL_XML.length()));
      }
      file.write(viewEntity(t).getBytes());
      file.write(("\n" + TAIL_XML).getBytes());

    } catch (IOException ex) {
      LOG.log(Level.INFO, "create entity error", ex);
    }
  }

  public abstract String viewEntity(T t);

  public abstract ArrayList<T> read();

  protected abstract T parseEntity(final String str);

  @Override
  public void update(final T t) {
  }

  @Override
  public void delete(final T t) {
    RandomAccessFile file = fileDataProvider.getDataFile();
    try {
      String buffer = "";
      file.seek(0);
      String str;
      int startAndEndOfStr[] = getStartAndEndOfStr(file, t);
      int start = startAndEndOfStr[0];
      int end = startAndEndOfStr[1];
      file.seek(end);
      while ((str = file.readLine()) != null) {
        buffer += str + "\n";
      }
      file.seek(start);
      file.write(buffer.getBytes());
      file.setLength(start + buffer.length() - 1);
    } catch (IOException e) {
      System.out.println("Error get info from file JSON (Street)");
    }
  }

  @Override
  public T getOneById(final long id) {
    return null;
  }

  public abstract int[] getStartAndEndOfStr(RandomAccessFile file, T t) throws IOException;

}
