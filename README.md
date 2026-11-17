
# Lab 10 – Dataflow Testing

## 1. Оюутны мэдээлэл

- Оюутны нэр: Эрдэнэтунгалаг 
- Оюутны код: B232270157
- Хичээл: F.CSA313
- Лаборатори: Lab 10 – Dataflow Testing

## 2. Орчны мэдээлэл

Энэ лабораторийн ажлыг Java болон Maven ашиглан гүйцэтгэсэн.

### Java хувилбар

```text
java version "26" 2026-03-17
Java(TM) SE Runtime Environment (build 26+35-2893)
Java HotSpot(TM) 64-Bit Server VM (build 26+35-2893, mixed mode, sharing)
```

### Maven хувилбар

```text
Apache Maven 3.9.10
Maven home: C:\Program Files\Apache\Maven\apache-maven-3.9.10
Java version: 26, vendor: Oracle Corporation
OS name: Windows 11, version: 10.0, arch: amd64
```

### Ашигласан технологи

- Java 26
- Apache Maven 3.9.10
- JUnit 5.10.2
- JaCoCo 0.8.16
- Windows 11

## 3. Part A – GCD функцийн өгөгдлийн урсгалын шинжилгээ

`DataFlowTargets.java` файлын `gcd(x, y)` функцэд
all-DU-pairs шинжилгээ хийсэн.

### DU хосуудын хүснэгт

| ID | Хувьсагч | Definition | Use | Төрөл | Тест |
|---|---|---|---|---|---|
| G1 | x | 13 | 16 | c-use | G-T1 |
| G2 | x | 13 | 20 | c-use | G-T2 |
| G3 | x | 17 | 16 | c-use | G-T1 |
| G4 | x | 17 | 20 | c-use | G-T1 |
| G5 | y | 13 | 15 TRUE | p-use | G-T1 |
| G6 | y | 13 | 15 FALSE | p-use | G-T2 |
| G7 | y | 13 | 16 | c-use | G-T1 |
| G8 | y | 13 | 17 | c-use | G-T1 |
| G9 | y | 18 | 15 TRUE | p-use | G-T1 |
| G10 | y | 18 | 15 FALSE | p-use | G-T1 |
| G11 | y | 18 | 16 | c-use | G-T1 |
| G12 | y | 18 | 17 | c-use | G-T1 |
| G13 | tmp | 16 | 18 | c-use | G-T1 |

### Тестийн тайлбар

- G-T1: gcd(12, 8) – хоёр давталт ажиллуулж, давталтын доторх DU хосуудыг шалгасан.
- G-T2: gcd(7, 0) – давталт алгасагдах үеийн DU хосуудыг шалгасан.
- G-T3: gcd(0, 5) – нэг давталт ажиллах нэмэлт тест.
## 4. Part B – Bonus функцийн өгөгдлийн урсгалын шинжилгээ

`bonus(attendance, threshold)` функцэд
def-use шинжилгээ хийсэн.

### CFG график
![alt text](image.png)

### Definition болон Use

Энд хувьсагч бүрийн definition, c-use, p-use-ийг бичнэ.

### DU хосуудын хүснэгт

Энд all-DU-pairs хүснэгтийг оруулна.

### Тестийн тайлбар

- B-T1 – B-T2: All-defs тестүүд
- B-T3 – B-T9: All-DU-pairs тестүүд

## 5. Part C – Fault Injection

### M1 – Missing Update

`streak = streak + 1` үйлдлийг устгаж туршсан.

- Нийт 11 тест ажилласан.
- 2 тест амжилтгүй болсон.
- `allDefsA` болон `longStreakBelowThreshold` тестүүд алдааг илрүүлсэн.
- M1 mutation killed болсон.

### M2 – Wrong Variable

`best = streak` мөрийг `best = present` болгон өөрчилсөн.

- Эхний 8 Bonus тест болон 3 GCD тест амжилттай ажилласан.
- Анхны тестүүд M2 алдааг илрүүлээгүй (survived).
- B-T9 нэмэлт тест зохиосны дараа алдаа илэрсэн.
- Нийт 12 тестээс 1 тест амжилтгүй болсон.
- M2 mutation killed болсон.

### M3 – Dead Definition

Ашиглагдахгүй `total` хувьсагч нэмсэн.

- Нийт 12 тест амжилттай ажилласан.
- Тестүүд өөрчлөлтийг илрүүлээгүй (survived).
- IDE дээр `Variable total is never read` гэсэн анхааруулга гарсан.
- Энэ нь хувьсагчид утга оноосон боловч дараа нь ашиглаагүй байгааг илэрхийлнэ.
- Ийм төрлийн алдааг static analysis ашиглан илрүүлэх боломжтой.

## 6. JaCoCo Coverage

### All-defs үе шат

- Instruction: 73/81 (90.1%)
- Branch: 14/16 (87.5%)
- Line: 22/24 (91.7%)

### Эцсийн үр дүн

- JaCoCo хувилбар: 0.8.16
- Instruction Coverage: 78/81 (96.3%)
- Branch Coverage: 16/16 (100%)
- Line Coverage: 23/24 (95.8%)
- Complexity: 10/11
- Method: 2/3
- Class: 1

Хамрагдаагүй нэг мөр нь автоматаар үүсдэг default constructor-той холбоотой.

Branch coverage 100% болсон нь бүх шийдвэрийн салааг тестээр ажиллуулсан гэсэн үг. Гэхдээ энэ нь бүх DU хос хамрагдсан гэсэн үг биш юм.

## 7. Дүгнэлт

Энэ лабораторийн ажлаар өгөгдлийн урсгалын шинжилгээ болон DU хосын тестийн талаар судалж, практикт хэрэгжүүллээ. GCD функцэд all-DU-pairs тест зохиож, хувьсагчийн definition болон use-ийн хамаарлыг шалгасан. Bonus функцэд эхлээд all-defs тест, дараа нь all-DU-pairs тест нэмсэн. JaCoCo ашиглан тестийн хамрах хүрээг хэмжихэд эцсийн branch coverage 100% болсон. Mutation testing хийхэд M1 болон M2 алдааг тестүүд илрүүлсэн бол M3 өөрчлөлт тестүүдэд илрээгүй. M2 алдааг илрүүлэхийн тулд B-T9 нэмэлт тест зохиосон. Энэ ажлаас branch coverage өндөр байх нь бүх өгөгдлийн урсгалын хосыг шалгасан гэсэн үг биш гэдгийг ойлгосон.

## 8. AI ашигласан тухай

Энэ лабораторийн ажлын явцад AI хэрэгслийг
кодын алдааг ойлгох, DU хосын шинжилгээг
шалгах, тайлбар боловсруулахад ашигласан.


[def]: c:\Users\User\Downloads\cfg-bonus.png