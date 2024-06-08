## Spring Boot Data JPA

## Query generation from method names
![img.png](img.png)

## Query creation/generation from method names work
![img_1.png](img_1.png)

## Query Method Structure
![img_2.png](img_2.png)

## Rules to create query methods
1. The name of our query method must start with one of the following prefixes: **find...By,**, **read...By**,
**query...By**, **count...By**, and **get...By**.

**Examples**: findByName, readByName, queryByName, getByName
2. If we want to limit the number of return query results, we can add the First or the Top keyword before the first By
word.

Examples: findFirstByName, readFirst2ByName, findTop10ByName

`SELECT * FROM products limit 10;`

3. If we want to select unique results, we have to add the Distinct keyword before the first By word.

**Examples**: findDistinctByName or findNameDistinctBy

`select distinct id, name, description, price, sku from products where name in ('product 1', 'product 2')`

4. Combine property expression with AND and OR.

**Examples**: findByNameOrDescription, findByNameAndDescription

`select * from product where name = "product 1" or description = "product 1 description";`

## Returning Values From Query Methods

A query method can return only one result or more the one result.

1. If we are writing a query that should return only one result, we can return the following types:
- **Basic type**. Our query method will return the found basic type or null.
- **Entity**. Our query method will return an entity object or null.
- **Guava/Java 8 Optional<T>**. Our query method will return an Optional that contains the found object or an empty Optional.

```
public Product findByName(String name);
public Optional<Product> findById(long id);
```

2. If we are writing a query method that should return more then one result, we can return the following types:
- **List<T>**. Our query method will return a list that contains the query results or an empty list.
- **Stream<T>**. Our query method will return a Stream that can be used to access the query results or and empty Stream.

```
List<Product> findByPriceGreaterThen(BigDecimal price);
Stream<Product findByPriceLesThen(BigDecimal price);
```

## Query Method - Find by single field name
### Write query method to find or retrieve a product by name.
```
select * from products where name = "product 1";
public Product findByName(String name)
```

### Write a query method to find or retrieve a product by id
```
select * from products where id = 1;
public Optinal<Product> findById(long id);
```

## Query method - Find by multiple field names
### Write a query method to find or retrieve a product by name or description
```
select * from product where name = "product 1" or description = "product 1 desc"
public List<Product> findByNameOrDescription(String name, String descrition)
```

### Write a query method to find or retrieve a product by name and description
```
select * from products where name = "product 1" and description = "product 1 desc"
public List<Product> findByNameAndDescription(String name, String Description)
```

## Query method - Find by Distinct
### Write a query method a find or retrieve a unique product by name
```
select distinct id, active, date_created, description, image_url, last_updated, name, price, sku from
product where name = "product 1";
```
```
public Product findDistinctByName(String title);
```

## Query method - Find by GreaterThen
### Write a query method to find or retrieve products whose price is greater then given price as method parameter
```
select id, active, date_created, descripion, image_url, last_updated, name, price, sku from products where 
price > 100;
```
```
List<Product> findByPriceGreaterThen(BigDecimal price);
```

## Query method - Find by LessThen
### Write a query method to find or retrieve products whose price is less then given price as method parameter
```
select id, active, date_created, descripion, image_url, last_updated, name, price, sku from products where 
price < 200;
```
```
List<Product> findByPriceLessThen(BigDecimal price);
```

## Query method - Find by Containing
### Write a query method to find or retrieve filtered products then match the given text(contains check)
```
select id, active, date_created, descripion, image_url, last_updated, name, price, sku from products where 
name like '%product%';
```
```
List<Product> findByNameContaining(String price);
```

## Query method - Find by Like
### Write a query method to find or retrieve products for a specified pattern in a column(SQL LIKE condition)
```
select id, active, date_created, descripion, image_url, last_updated, name, price, sku from products where 
name like '%product%';
```
```
List<Product> findByNameLike(String price);
```

## Query method - Between
### Write a query method to find or retrieve products base on the price range (start price and end price)
```
select id, active, date_created, descripion, image_url, last_updated, name, price, sku from products where 
price between 100 and 300
```
```
List<Product> findByPriceBetween(BigDecimal, startPrice, BigDecimal, endPrice);
```

## Query method - Between (By Date)
### Write a query method to find or retrieve products based on the start date and end date
```
select id, active, date_created, description, image_url, last_updated, name, price, sku from products where
date_created between '2022-01-28 22:54:15' and '2022-01-28 22:59:23'
```
```
List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
```

## Query method - In
### Write a query method to find or retrieve products based on the multiple values (specify multiple values in a SQL where clause)
```
select id, active, date_created, description, image_url, last_updated, name, price, sku from products where
name in ('product 1', 'product 2')
```
```
List<Product> findByNameIn(List<String> names);
```

## Query method - Limiting Query Results
Spring Data JPA support keyword 'first' or 'top' to limit the query results.

**Example:** findFirstByName(), findTop5BySku()

An optional numeric value can be appended after 'top' or 'first' to limit the maximum number of results to be returned
to be returned (e.g findTop3By...). If this number is not used then only one entity is returned.

There's no difference between the keyword 'first' and 'top'