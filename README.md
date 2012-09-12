## სერვისის გამოყენება

### რეესტრის მიღება

რეესტრის მისაღება შესაძლებელია ვებ-სერვერიდან. ასეთ შემთხვევაში თქვენ უნდა იყოთ ავტორიზირებული
ბილინგის მომხმარებელი და გქონდეთ მომხმარებლის სახელი და პაროლი.

რეესტის მიღება სერვერიდან ასე გამოიყურება:

```java
String username = "dimitri";
String password = "secret";
String date = "21-Jun-2012";
Reester reester = ReadingController.getReesterOverHTTP(username, password, date);
```

მუშაობის პროცესში თქვენ შეგიძლიათ შეინახოთ რეესტრი ფაილში (იხ. ქვემოთ).
რეესტრის ფაილიდან აღსადგენად გამოიყენეთ შემდეგი კოდი:


```java
InputStream in = new FileInputStream("reester.xml");
Reester reester = ReadingController.getReesterOverIO(in);
```

რეესტრის ფაილიდან აღდგენის შემთხვევაში საჭირო არაა მომხმარებლის და პაროლის მითითება.

### რეესტრის ატვირთვა

რეესტრის დამუშავების შემდეგ ის შეიძლება აიტვირთოს უკან ვებ-სერვერზე.

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

### რეესტრის შემოწმება

TODO: შესაძლებელია გაკეთება