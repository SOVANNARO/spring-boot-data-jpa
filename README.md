## Spring Boot Data JPA

## Connect My SQL Database
- JDBC URL
- MySQL Database Username
- MySQL Database Password
- Hibernate Properties

### spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
- Hibernate generate queries for the specific database based on the Dialect class

### spring.jpa.hibernate.auto-ddl=
- The property is used to customize the Hibernate database schema generation process
- none, create-only, create, drop, create-drop, validate, update

## spring.jpa.hibernate.auto-ddl=none
- This option disables the hdb2ddl.auto tool, so Hibernate is not going to take any action for managing
the underlying database schema.

## spring.jpa.hibernate.auto-ddl=create-only
- This option tells Hibernate to generate the database schema from the entity model.

## spring.jpa.hibernate.auto-ddl=create
- This option tells Hibernate to drop the database schema and recreate it afterward using the entity 
model as a reference.

## spring.jpa.hibernate.auto-ddl=create-drop
- This option tells Hibernate to drop the database schema and recreate it afterward using the entity
model as a reference. And upon closing the JPA EntityManagerFactory or the Hibernate SessionFactory,
the database schema will be dropped again.

## spring.jpa.hibernate.auto-ddl=validate
- This option tells Hibernate to validate the underlying database schema against the entity mappings.

## spring.jpa.show-sql=true
- configuration property in Spring Boot that enables logging of SQL statements executed by Hibernate, 
- the Java Persistence API (JPA) provider.
- When you set spring.jpa.show-sql=true in your application.properties file, Hibernate will log all SQL statements 
it executes, including **SELECT**, **INSERT**, **UPDATE**, and **DELETE** statements. This can be helpful for debugging purposes, 
as it allows you to see the exact SQL queries being executed by your application.

**Note** that this configuration only logs the SQL statements, but not the bind parameters. 
If you want to log the bind parameters as well, you can add **spring.jpa.properties.hibernate.format_sql=true** 
to your application.properties file.

## spring.jpa.properties.hibernate.show_sql=true
- configuration property in Spring Boot that enables Hibernate to log the SQL statements it executes.

Here's a breakdown of the property:

**spring.jpa.properties:** This is a prefix for configuring JPA (Java Persistence API) properties in a Spring Boot application.
**hibernate:** This is the JPA provider being used, which is Hibernate in this case.
**show_sql:** This is a Hibernate-specific property that controls whether Hibernate should log the SQL statements it executes.
**true:** This is the value assigned to the show_sql property, which enables the logging of SQL statements.
When you set spring.jpa.properties.hibernate.show_sql=true in your application.properties file, Hibernate will log all SQL statements it executes, including:

- SELECT statements 
- INSERT statements 
- UPDATE statements 
- DELETE statements 
- DDL (Data Definition Language) statements, such as CREATE TABLE or ALTER TABLE 

The logged SQL statements will include the actual SQL code, including any bind parameters and their values. This can be very helpful for debugging purposes, as it allows you to see exactly what SQL statements are being executed by Hibernate.

For example, if you have a Product entity and you execute a query to retrieve all products, the log output might look like this:

```
Hibernate:
    select
        product0_.id as id1_1_,
        product0_.name as name2_1_,
        product0_.description as descript3_1_,
        product0_.price as price4_1_
    from
        products product0_
```

Note that this property only logs the SQL statements, but not the execution time or other performance metrics. If you want to log more detailed information about the SQL statements, such as execution time or the number of rows affected, you can use other Hibernate properties, such as hibernate.format_sql or hibernate.stats.

Remember to set spring.jpa.properties.hibernate.show_sql=false in production environments to avoid logging sensitive information and to improve performance.

## 4 Primary Key Generation Strategies
1. GenerationType.AUTO
2. GenerationType.IDENTITY
3. GenerationType.SEQUENCE
4. GenerationType.TABLE

### 1. GenerationType.AUTO
The GenerationType.AUTO is the default generation type and lets the persistence provider choose the generation strategy.

```
@Id
@GneratedValue(strategy = GenerationType.AUTO)
@Colum(name = "id")
private Long id;
```

If you use Hibernate as your persistence provider, it selects a generation strategy based on the database-specific dialect.

For most populate databases, it selects GenerationType.SEQUENCE which I will explain in a further section.

### 2. GenerationType.IDENTITY
```
@Id
@GeneratedValue(strategy = Generation.IDENTITY)
@Column(name = "id")
private Long id;
```

It relies on an auto-incremented database column and lets the database generate a new value with each insert operation.

Form a database point of view, this is very efficient because the auto-increment columns are highly optimized, and
it doesn't require any additional statements.

Not good for JDBC batch operations.

### 3. GenerationType.SEQUENCE
```
@GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "product_generator")
@SequenceGenerator(name = "product_generator",
    sequenceName = "product_sequence_name",
    allocationSize = 1)
```

The GenerationType.SEQUENCE is to generate primary key values and uses a database sequence to generate unique values.

It requires additional select statements to get the next value from a database sequence. But this has no performance 
impact on most applications.

The @SequenceGenerator annotation lets you define the name of the generator, the name, and schema of the database 
sequence and the allocation size of the sequence.

### GenerationType.TABLE

```
@Id
@GeneratedValue(strategy = GenerationType.TABLE)
@Column(name = "id")
private Long id;
```

The GenerationType.TABLE gets only rarely used nowadays.

It simulates a sequence by storing and updating its current value in a database table which requires the use of pessimistic
locks which put all transactions into a sequential order.

This shows down your application, and you should, therefore, prefer the GenerationType.SEQUENCE, if you database supports
sequences, which most popular database do.