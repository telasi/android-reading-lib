package telasi.android.reading.model;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.xmlpull.v1.XmlSerializer;

public class ReesterSerializer {

  void shortReesterSerialization(XmlSerializer xps, Writer writter, Reester reester) throws IOException {
    xps.setOutput(writter);
    xps.startDocument("UTF-8", true);
    xps.startTag("", "reester");
    // id
    xps.startTag("", "id");
    xps.text(String.valueOf(reester.getId()));
    xps.endTag("", "id");
    // items
    xps.startTag("", "items");
    for (ReesterItem item : reester.getItems()) {
      xps.startTag("", "item");
      // item id
      xps.startTag("", "id");
      xps.text(String.valueOf(item.getId()));
      xps.endTag("", "id");
      // item reading
      xps.startTag("", "reading");
      xps.text(String.valueOf(item.getReading().getReading()));
      xps.endTag("", "reading");
      xps.endTag("", "item");
    }
    xps.endTag("", "items");
    xps.endDocument();
  }

  void shortReesterSerialization(XmlSerializer xps, OutputStream out, Reester reester) throws IOException {
    try {
      shortReesterSerialization(xps, new OutputStreamWriter(out), reester);
    } finally {
      out.close();
    }
  }

  String shortReesterSerialization(XmlSerializer xps, Reester reester) throws IOException {
    StringWriter w = new StringWriter();
    shortReesterSerialization(xps, w, reester);
    return w.toString();
  }

}
