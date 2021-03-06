## სერვისის გამოყენება

### კონფიგურაცია

სერვისის გამოყენების წინ საჭიროა მისი კონფიგურაციის განსაზღვრა.

კონფიგურაციის ძირითადი პარამეტრებია სერვისის ჰოსტი და პორტი.

```java
import telasi.android.reading.model.Config;

Config.setHost("service.telasi.ge");
Config.setPort("80");
```

### რეესტრის სიის მეღება

რეესტრის სიის მიღება კონკრეტულ თანამშრომელზე არის შესაძლებელი შემდეგი მეთოდის გამოყენებით:

```java
String username = "dimitri";
String password = "secret";
int page = 2;
int inspectorId = 6967;
List<Reester> reesters = ReadingController.getReestersOverHTTP(username, password, inspectorId, page);
```

### რეესტრის მიღება

რეესტრის მისაღება შესაძლებელია ვებ-სერვერიდან. ასეთ შემთხვევაში თქვენ უნდა იყოთ ავტორიზირებული
ბილინგის მომხმარებელი და გქონდეთ მომხმარებლის სახელი და პაროლი.

რეესტის მიღება სერვერიდან ასე გამოიყურება:

```java
String username = "dimitri";
String password = "secret";
int route_id = 80;
Reester reester = ReadingController.getReesterOverHTTP(username, password, route_id);
```

მუშაობის პროცესში თქვენ შეგიძლიათ შეინახოთ რეესტრი ფაილში (იხ. ქვემოთ).
რეესტრის ფაილიდან აღსადგენად გამოიყენეთ შემდეგი კოდი:

```java
InputStream in = new FileInputStream("reester.xml");
Reester reester = ReadingController.getReesterOverIO(in);
```

რეესტრის ფაილიდან აღდგენის შემთხვევაში საჭირო არაა მომხმარებლის და პაროლის მითითება.

### რეესტრის ატვირთვა

რეესტრის დამუშავების შემდეგ ის უბრუნდება დასამუშავებლად ვებ-სერვერს.

```java
String username = "dimitri";
String password = "secret";
Reester reester = ReadingController.sendReesterOverHTTP(reester, username, password);
```

### რეესტრის ფაილში შენახვა

რეესტრის დამუშავების პროცესში შეიძლება შევინახოთ ფაილში:

```java
OutputStream out = new FileOutputStream("reester.xml");
ReadingController.saveReesterOverIO(reester, out);
```

### რეესტრის ჩანაწერების შემოწმება

რეესტრის ჩანაწერების შემოწმება არის შესაძლებელი შემდეგი მეთოდების გამოყენებით:

```java
ReesterItem item = reester.getItems()[index]; // ჩანაწერის მიღება
item.isReadingEntered(); // => ჩანაწერი შეტანილია
item.isAboveMax(); // => მაქსიმალური ხარჯი გადაცილებულია
item.isBelowMin(); // => მინიმალური ხარჯი ვერ შესრულდა
```
