# Country API Documentation

## Overview

This Spring Boot application provides an API for retrieving country information. It exposes a single endpoint to fetch a list of countries based on
various filters.

## API Endpoint Details

### GET /countries

Fetches a list of countries based on the provided filters.

#### Parameters:

- **name** (optional)
    - Type: `String`
    - Description: Filters the list by the name of the country. It may support partial matching.

- **population** (optional)
    - Type: `BigDecimal`
    - Description: the upper limit of the population size, in millions. For instance, when a user specifies "population=42.2", the API will return a
      list of countries which population is less than 42.2 million.

- **order** (optional)
    - Type: `Sort.Order`
    - Allowed Values: `ASC` (Ascending), `DESC` (Descending)
    - Default: `ASC`
    - Description: Determines the sorting order of the returned list based on the country names.

- **page_size** (optional)
    - Type: `int`
    - Range: 1 to 1000
    - Default: 250
    - Description: Specifies the maximum number of countries to return. Helps in paginating large result sets.

#### Response:

- **200 OK**
    - Description: Successfully fetched the list of countries based on the provided filters.
    - Body:
        - A list of `CountryDto` objects, with each object representing details about a country.

- **400 BAD REQUEST**
    - Description: Invalid request parameters, such as invalid values or combination of filters.

- **500 INTERNAL SERVER ERROR**
    - Description: Unexpected error on the server side.

- **503 INTERNAL SERVER ERROR**
    - Description: Service is overloaded.

#### Example Request:

```http
GET /countries?name=Can&order=DESC&page_size=100
```

This would retrieve a list of countries with names that might start with "Can", ordered in descending order, with a maximum of 100 results.

---

## Models

### CountryDto

Represents details about a country, providing comprehensive data including geographical, political, and cultural attributes.

#### Fields:

- **name** (`CountryName`)
    - Represents the common and official names of the country.

- **tld** (`List<String>`)
    - Top-Level Domains associated with the country.

- **cca2** (`String`)
    - 2-character country code, typically used in ISO 3166-1 alpha-2 standard.

- **ccn3** (`String`)
    - 3-number country code.

- **cca3** (`String`)
    - 3-character country code, typically used in ISO 3166-1 alpha-3 standard.

- **cioc** (`String`)
    - Represents the International Olympic Committee's country code.

- **independent** (`Boolean`)
    - Indicates if the country is independent.

- **status** (`String`)
    - Provides information about the country's current status (e.g., "sovereign state", "territory").

- **unMember** (`Boolean`)
    - Indicates if the country is a member of the United Nations.

- **capital** (`List<String>`)
    - List of the country's capital cities.

- **altSpellings** (`List<String>`)
    - Alternative spellings or names for the country.

- **region** (`String`)
    - Geographical region the country is located in (e.g., "Asia", "Africa").

- **subregion** (`String`)
    - More specific geographical subregion the country is located in.

- **languages** (`Map<String, String>`)
    - Key-value pairs indicating the languages spoken in the country. Keys are language codes, and values are the respective language names.

- **latlng** (`List<BigDecimal>`)
    - Contains two values: latitude and longitude of the country.

- **landlocked** (`Boolean`)
    - Indicates if the country is landlocked.

- **borders** (`List<String>`)
    - List of countries (codes or names) that share a border with this country.

- **area** (`BigDecimal`)
    - Total land area of the country in square kilometers.

- **flag** (`String`)
    - URL or representation of the country's flag.

- **maps** (`Map<String, String>`)
    - Maps associated with the country. Could be key-value pairs of types of maps and their respective URLs or representations.

- **population** (`BigDecimal`)
    - Total population of the country.

- **gini** (`Map<String, BigDecimal>`)
    - Represents the Gini coefficient values for the country across different years or metrics. The Gini coefficient measures income inequality within
      a country.

- **fifa** (`String`)
    - FIFA code for the country.

- **timezones** (`List<String>`)
    - List of time zones applicable to the country.

- **continents** (`List<String>`)
    - List of continents the country is part of.

- **startOfWeek** (`String`)
    - Indicates which day is considered the start of the week in this country (e.g., "Sunday", "Monday").

---

### CountryName

Represents the common and official names of a country.

#### Fields:

- **common** (`String`)
    - The common name of the country. It's the name most people use in everyday speech.

- **official** (`String`)
    - The official, formal name of the country.

---

## Examples

1. **Retrieve All Countries with Default Settings**

```http
GET /countries
```

2. **Search for Countries by Name**

  ```http
  GET /countries?name=India
  ```

3. **Filter Countries by Population**
   Searching all countries where population size is less than 10 million.

  ```http
  GET /countries?population=10
  ```

4. **Sort Countries in Descending Order**

```http
GET /countries?order=DESC
```

5. **Limit the Number of Results Returned**

  ```http
  GET /countries?page_size=50
  ```

6. **Search for Countries by Name and Sort in Descending Order**

  ```http
  GET /countries?name=Bra&order=DESC
  ```

This could retrieve countries with names starting with "Bra" like "Brazil", "Bratislava" etc., sorted in descending order.

7. **Filter by Population (in millions) and Limit the Number of Results**

  ```http
  GET /countries?population=50&page_size=5
  ```

8. **Search by Name, Filter by Population (in millions), and Sort Results**

  ```http
  GET /countries?name=Sw&population=80&order=DESC
  ```

This could retrieve countries with names starting with "Sw" and with a population greater than 8 million, sorted in descending order.

9. **Sort Results in Ascending Order with a Page Size Limit**

  ```http
  GET /countries?order=ASC&page_size=100
  ```

10. **Comprehensive Example with All Parameters**

  ```http
  GET /countries?name=Can&population=35&order=ASC&page_size=10
  ```

This would retrieve countries with names that might start with "Can", have a population greater than 35 million, ordered in ascending order, with a
maximum of 10 results.

---

## How to start
To start the project you have to simply run
```bash
mvn spring-boot:run
```
