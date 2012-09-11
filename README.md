## სერვისის გამოყენება

### რეესტრის მიღება

რეესტრის მისაღებად გამოიყენეთ ` ReadingController`  კლასი:

```java
// იმპორტი
import telasi.android.reading.model.*;

// რეესტრის მიღება
XMLPullParser xpp = XML.getXmlPullParser();
String username = "dimitri";
String password = "secret";
String date = "21-Jun-2012";
Reester reester = ReadingController.getReesterOverHTTP(xpp, username, password, date);
```

### რეესტრის ატვირთვა

TODO: დასასრულებელია

### რეესტრის შემოწმება

TODO: შესაძლებელია გაკეთება
