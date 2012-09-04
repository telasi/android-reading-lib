## სერვისის გამოყენება

### რეესტრის მიღება

რეესტრის მისაღებად გამოიყენეთ ` ReadingController`  კლასი:

```java
// იმპორტი
import telasi.android.reading.*;

// რეესტრის მიღება
XMLPullParser xpp = XML.getXmlPullParser();
int inspectorId = 1000;
String date = "21-Jun-2012";
Reester reester = ReadingController.getReesterOverHTTP(xpp, date, inspectorId);
```

### რეესტრის ატვირთვა

TODO: დასასრულებელია

### რეესტრის შემოწმება

TODO: შესაძლებელია გაკეთება
