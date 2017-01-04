package com.levelup.table.dao.impl.xml;

import com.levelup.table.dao.dataproviders.FileDataProvider;
import com.levelup.table.dao.impl.csv.AbstractCSVNDAO;
import com.levelup.table.dao.impl.json.StreetJsonDAOImpl;
import com.levelup.table.entity.Street;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Veronika Kulichenko on 03.01.17.
 */
public class StreetXMLDAOImpl extends AbstractXMLDAO<Street> {

  private static final String OPEN_TAG = "\t\t<street>\n";
  private static final String CLOSE_TAG = "\t\t</street>\n";
  private static final Logger LOG = Logger.getLogger(StreetJsonDAOImpl.class.getName());

  public StreetXMLDAOImpl(final FileDataProvider fileDataProvider) {
    super(fileDataProvider, "street_list");
  }

  @Override
  public String viewEntity(final Street street) {
    return String.format("\t<street>\n\t\t<id>%d</id>\n\t\t<streetName>%s</streetName>\n\t</street>", street.getId(), street.getStreetName());
  }

  @Override
  public ArrayList<Street> read() {
    RandomAccessFile file = fileDataProvider.getDataFile();
    ArrayList<Street> streetList = new ArrayList<Street>();
    String str = "";
    try {
      while ((str = file.readLine()) != null) {
       if(str.contains("<street>")){
         Street street = new Street();
       } else if(str.contains("<id>")){
          str.replace("<id>","").replace("</id>", "").replace("\t\t", "");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return streetList;
  }

  @Override
  protected Street parseEntity(final String str) {
    String[] arr = str.replace("{id:", "").replace(" streetName:", "").replace("}", "").split(",");
    return new Street(Long.parseLong(arr[0]), arr[1]);
  }

  @Override
  public int[] getStartAndEndOfStr(final RandomAccessFile file, final Street street) throws IOException {
    int[] arr = new int[2];
    int start = 0;
    int end = 0;
    boolean found = false;
    String str = "";
    while ((str = file.readLine()) != null) {
      if (str.contains("<id>" + street.getId() + "</id>")) {
        found = true;
        start -= OPEN_TAG.length();
        arr[0] = start;
      }
      if (!found) {
        start += str.length() + 1;
      } else if(!str.contains("</street>")) {
        if(end == 0){
          end = start + OPEN_TAG.length() + str.length() + 1;
        } else {
          end += str.length() + 1;
        }
      } else {
        end += CLOSE_TAG.length();
        arr[1] = end;
        break;
      }
    }
    return arr;
  }
}

